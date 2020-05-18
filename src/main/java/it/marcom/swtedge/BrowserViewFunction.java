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

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import it.marcom.swtedge.nat.WebViewNativeCallback;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.browser.LocationListener;

import java.util.Objects;
import java.util.Random;

/**
 * Instances of this class represent java-side "functions" that
 * are invokable from javascript.  Browser clients define these
 * functions by subclassing <code>BrowserFunction</code> and
 * overriding its <code>function(Object[])</code> method.  This
 * method will be invoked whenever javascript running in the
 * Browser makes a call with the function's name.
 *
 * <p>
 * Application code must explicitly invoke the
 * <code>BrowserFunction.dispose()</code> method to release the
 * resources managed by each instance when those instances are no
 * longer required.  Since there is usually a correlation between
 * the registering of BrowserFunction(s) in a Browser and the
 * loading of a page in the Browser that is aware of these
 * functions, the <code>LocationListener.changed()</code> listener
 * is often a good place to do this.
 * </p><p>
 * Note that disposing a Browser automatically disposes all
 * BrowserFunctions associated with it.
 * </p>
 *
 * @see #function(Object[])
 * @see LocationListener#changed(LocationEvent)
 * @since 1.0
 */
public class BrowserViewFunction {
    BrowserView browser;
    String name;
    BrowserFunction proxyFuction;

    /**
     * Constructs a new instance of this class, which will be invokable
     * by javascript running in the specified Browser.  The function will
     * be accessible in the top-level window and all child frames.  To
     * create a function with a reduced scope of accessibility use the
     * <code>BrowserFunction</code> constructor that accepts frame names
     * instead.
     * <p>
     * You must dispose the BrowserFunction when it is no longer required.
     * A common place to do this is in a <code>LocationListener.changed()</code>
     * listener.
     * </p>
     *
     * @param browser the browser whose javascript can invoke this function
     * @param name    the name that javascript will use to invoke this function
     * @throws IllegalArgumentException <ul>
     *                                  <li>ERROR_NULL_ARGUMENT - if the browser is null</li>
     *                                  <li>ERROR_NULL_ARGUMENT - if the name is null</li>
     *                                  </ul>
     * @throws SWTException             <ul>
     *                                  <li>ERROR_WIDGET_DISPOSED - if the browser has been disposed</li>
     *                                  <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     *                                  </ul>
     * @see #dispose()
     * @see #BrowserViewFunction(BrowserView, String, boolean, String[])
     * @see LocationListener#changed(LocationEvent)
     */
    public BrowserViewFunction(BrowserView browser, String name) {
        this(browser, name, true, null, true);
    }

    /**
     * Constructs a new instance of this class, which will be invokable
     * by javascript running in the specified Browser.  The accessibility
     * of the function to the top-level window and its child frames is
     * determined by the <code>top</code> and <code>frameNames</code>
     * arguments.  To create a function that is globally accessible to
     * the top-level window and all child frames use the
     * <code>BrowserFunction</code> constructor that does not accept frame
     * names instead.
     * <p>
     * You must dispose the BrowserFunction when it is no longer required.
     * A common place to do this is in a <code>LocationListener.changed()</code>
     * listener.
     * </p>
     *
     * @param browser    the browser whose javascript can invoke this function
     * @param name       the name that javascript will use to invoke this function
     * @param top        <code>true</code> if the function should be accessible to the
     *                   top-level window and <code>false</code> otherwise
     * @param frameNames the names of the child frames that the function should
     *                   be accessible in
     * @throws IllegalArgumentException <ul>
     *                                  <li>ERROR_NULL_ARGUMENT - if the browser is null</li>
     *                                  <li>ERROR_NULL_ARGUMENT - if the name is null</li>
     *                                  </ul>
     * @throws SWTException             <ul>
     *                                  <li>ERROR_WIDGET_DISPOSED - if the browser has been disposed</li>
     *                                  <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     *                                  </ul>
     * @see #dispose()
     * @see #BrowserViewFunction(BrowserView, String)
     * @see LocationListener#changed(LocationEvent)
     * @since 1.0
     */
    public BrowserViewFunction(BrowserView browser, String name, boolean top, String[] frameNames) {
        this(browser, name, top, frameNames, true);
    }

    BrowserViewFunction(BrowserView browser, String name, boolean top, String[] frameNames, boolean create) {
        super();
        this.browser = browser;
        this.name = name;
        if (!browser.isProxy()) {

            WebViewNativeCallback callback = new WebViewNativeCallback() {
                @Override
                public String invoke(String arg2, long wv) {
                    Object[] arguments = JsUtils.jsonArgsToObjects(arg2);
                    Object object = functionProxy(arguments);
                    String jsonReturn = "null";
                    try{
                        jsonReturn = JsUtils.objectToJson(object);
                    }catch(Exception ex){
                        jsonReturn = "null";
                    }
                    return jsonReturn;
                }
            };

            ((EdgeBrowser) browser.webBrowser).getEdge().bind(name + "Promise", callback);

            //Add javascript function to call banck on EDGE all is a promise.
            //For compatibiliti with old browser regist a function not a primese no return value allowed.
            ((EdgeBrowser) browser.webBrowser).getEdge().initScript("function "+name+"(...args){addCallPromise(args).then(function(res) {})}");
            //Reload init Script.
            ((EdgeBrowser) browser.webBrowser).getEdge().reload();
        } else {
            proxyFuction = new BrowserFunction(browser.proxy, name) {
                @Override
                public Object function(Object[] arguments) {
                    return functionProxy(arguments);
                }
            };
        }

    }


    /**
     * Subclasses should override this method.  This method is invoked when
     * the receiver's function is called from javascript.  If all of the
     * arguments that are passed to the javascript function call are of
     * supported types then this method is invoked with the argument values
     * converted as follows:
     * <p>
     * javascript null or undefined -> <code>null</code>
     * javascript number -> <code>java.lang.Double</code>
     * javascript string -> <code>java.lang.String</code>
     * javascript boolean -> <code>java.lang.Boolean</code>
     * javascript array whose elements are all of supported types -> <code>java.lang.Object[]</code>
     * <p>
     * If any of the javascript arguments are of unsupported types then the
     * function invocation will fail and this method will not be called.
     * <p>
     * This method must return a value with one of these supported java types to
     * the javascript caller.  Note that <code>null</code> values are converted
     * to javascript's <code>null</code> value (not <code>undefined</code>), and
     * instances of any <code>java.lang.Number</code> subclass will be converted
     * to a javascript number.
     *
     * @param arguments the javascript arguments converted to java equivalents
     * @return the value to return to the javascript caller
     * @throws SWTException <ul>
     *                      <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong thread</li>
     *                      <li>ERROR_FUNCTION_DISPOSED when the BrowserFunction has been disposed</li>
     *                      </ul>
     */
    public Object function(Object[] arguments) {
        return null;
    }


    private Object functionProxy(Object[] arguments) {
        return function(arguments);
    }
    /**
     * Returns the Browser whose pages can invoke this BrowserFunction.
     *
     * @return the Browser associated with this BrowserFunction
     * @throws SWTException <ul>
     *                      <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong thread</li>
     *                      <li>ERROR_FUNCTION_DISPOSED when the BrowserFunction has been disposed</li>
     *                      </ul>
     */
    public BrowserView getBrowser() {
        return browser;
    }

    /**
     * Returns the name that javascript can use to invoke this BrowserFunction.
     *
     * @return the BrowserFunction's name
     * @throws SWTException <ul>
     *                      <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong thread</li>
     *                      <li>ERROR_FUNCTION_DISPOSED when the BrowserFunction has been disposed</li>
     *                      </ul>
     */
    public String getName() {
        return name;
    }


}
