package it.marcom.swtedge;

import ca.weblite.webview.WebViewCLIClient;
import ca.weblite.webview.WebViewNative;
import ca.weblite.webview.WebViewNativeCallback;
import ca.weblite.webview.WebViewServer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.internal.Callback;
import org.eclipse.swt.internal.LONG;
import org.eclipse.swt.internal.win32.MSG;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;

import java.awt.*;

public class EdgeBrowser extends WebBrowserView {
    // Opening the webview
    private WebViewCLIClient webview = null;
    private Frame frame;
    private long peer;
    Composite parent;

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
        WebViewServer server = null;


        peer = WebViewNative.webviewCreate(0, parent.handle);
        WebViewNativeCallback fn = new WebViewNativeCallback() {
            @Override
            public void invoke(String s, long l) {
                System.out.println("prova");
            }
        };
        WebViewNative.webview_bind(peer, "prova", fn, peer);
        WebViewNative.webview_set_bounds(peer, 0, 0, parent.getSize().x, parent.getSize().y, 0);

        parent.addListener(SWT.Resize, new Listener() {
            @Override
            public void handleEvent(org.eclipse.swt.widgets.Event event) {
                WebViewNative.webview_set_bounds(peer, 0, 0, parent.getSize().x, parent.getSize().y, 0);
            }
        });

//        WebViewNative.webview_navigate(peer, "https://www.google.it");
//        browser.handle = WebViewNative.webview_get_window(peer);

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
    public boolean execute(String s) {
        WebViewNative.webview_eval(peer, s);
        return true;
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
        WebViewNative.webview_navigate(peer, "data:text/html," + s);
        return true;
    }

    @Override
    public boolean setUrl(String s, String s1, String[] strings) {
        WebViewNative.webview_navigate(peer, s);
        return true;
    }

    @Override
    public void stop() {

    }
}
