/**
 * MIT License
 * <p>
 * Copyright (c) 2020 Marco Monacelli
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package it.marcom.swtedge;

import it.marcom.swtedge.nat.EvaluationCallBack;
import it.marcom.swtedge.nat.NativeEdge;
import it.marcom.swtedge.nat.WebViewNativeCallback;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.internal.Callback;
import org.eclipse.swt.internal.LONG;
import org.eclipse.swt.internal.win32.MSG;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;

import java.awt.*;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class EdgeBrowser extends WebBrowserView {
    // Opening the webview

    private Frame frame;

    Composite parent;
    private NativeEdge edge = null;
    private static String HHOOK = "WEBVIEW_HHOOK"; //$NON-NLS-1$
    private static String HHOOKMSG = "WEBVIEW_HHOOK_MSG";

    @Override
    public boolean back() {
        return false;
    }


    @Override
    public void create(Composite composite, int i) {
        this.parent = composite;


        boolean oauth;

        String onLoad;
        boolean useMessageBoundaries = false;


        edge = new NativeEdge(parent.handle);
        WebViewNativeCallback fn = new WebViewNativeCallback() {
            @Override
            public String invoke(String s, long l) {
                //WebViewNative.webview_eval(peer,"alert(\"provata\")",null);
                return "\"provata\"";
            }
        };
        edge.bind("prova", fn);
        edge.setBounds(0, 0, parent.getSize().x, parent.getSize().y, 0);

        parent.addListener(SWT.Resize, new Listener() {
            @Override
            public void handleEvent(org.eclipse.swt.widgets.Event event) {
                edge.setBounds(0, 0, parent.getSize().x, parent.getSize().y, 0);
            }
        });

        initMsgHook(browser.getDisplay());


    }

    private static void initMsgHook(Display display) {
        if (display.getData(HHOOK) != null) return;
        final Callback callback = new Callback(EdgeBrowser.class, "getMsgProc", 3); //$NON-NLS-1$
        long address = callback.getAddress();
        if (address == 0) SWT.error(SWT.ERROR_NO_MORE_CALLBACKS);
        int threadId = OS.GetCurrentThreadId();
        final long hHook = OS.SetWindowsHookEx(OS.WH_GETMESSAGE, address, 0, threadId);
        if (hHook == 0) {
            callback.dispose();
            return;
        }
        display.setData(HHOOK, new LONG(hHook));
        display.setData(HHOOKMSG, new MSG());
        display.disposeExec(() -> {
            if (hHook != 0) OS.UnhookWindowsHookEx(hHook);
            if (callback != null) callback.dispose();
        });
    }

    static long getMsgProc(long code, long wParam, long lParam) {
        Display display = Display.getCurrent();
        if (display == null) return 0;
        LONG hHook = (LONG) display.getData(HHOOK);

        return OS.CallNextHookEx(hHook.value, (int)/*64*/code, wParam, lParam);
    }

    @Override
    public Object evaluate(String script) throws SWTException {
        EvaluateFuture future = new EvaluateFuture();
        edge.eval(script, future);
        try {
            boolean trovato = false;
            while (true) {
                try {
                    return future.getFuture().get(1, TimeUnit.MILLISECONDS);
                } catch (TimeoutException e) {
                    browser.getDisplay().readAndDispatch();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean execute(String script) {

        //Da decomentare e capire se è sincrona o asincrona
        //Per ora facciamo asincrona
        edge.eval(script, null);
//        EvaluateFuture future = new EvaluateFuture();
//        edge.eval(script, future);
//        try {
//            boolean trovato = false;
//            while(true) {
//                try {
//                    future.getFuture().get(1, TimeUnit.MILLISECONDS);
//                    break;
//                } catch (TimeoutException e) {
//                    browser.getDisplay().readAndDispatch();
//                }
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

        return true;
//        EvaluateFuture future = new EvaluateFuture();
//        WebViewNative.webview_eval(peer, script, new EvaluationCallBack() {
//            @Override
//            public void invoke(int i, String s) {
//                System.out.println("Status " + i);
//                System.out.println("Value " + s);
//            }
//        });
//        browser.getDisplay().readAndDispatch();
//        return true;
    }

    @Override
    public boolean forward() {
        return false;
    }

    @Override
    public String getBrowserType() {
        return null;
    }

    @Override
    public String getText() {
        return null;
    }

    @Override
    public String getUrl() {
        return null;
    }

    @Override
    public boolean isBackEnabled() {
        return false;
    }

    @Override
    public boolean isForwardEnabled() {
        return false;
    }

    @Override
    public void refresh() {

    }

    @Override
    public boolean setText(String s, boolean b) {
        try {
            edge.navigate("data:text/html," + URLEncoder.encode(s, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean setUrl(String s, String s1, String[] strings) {
        edge.navigate(s);
        return true;
    }

    @Override
    public void stop() {

    }

    private class EvaluateFuture implements EvaluationCallBack {

        CompletableFuture<String> future = new CompletableFuture<String>();

        @Override
        public void invoke(int i, String s) {
            if (i == 0) {
                future.complete(s);
            } else {
                future.cancel(false);
            }
        }

        public CompletableFuture<String> getFuture() {
            return future;
        }
    }
}
