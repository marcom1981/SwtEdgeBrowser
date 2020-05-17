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

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.browser.*;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Widget;

public class BrowserView extends Composite {
    Browser proxy;
    WebBrowserView webBrowser;
    int userStyle;
    boolean isClosing;

    static int DefaultType = SWT.DEFAULT;

    static final String NO_INPUT_METHOD = "org.eclipse.swt.internal.gtk.noInputMethod"; //$NON-NLS-1$
    static final String PROPERTY_DEFAULTTYPE = "org.eclipse.swt.browser.DefaultType"; //$NON-NLS-1$

    /**
     * Constructs a new instance of this class given its parent
     * and a style value describing its behavior and appearance.
     * <p>
     * The style value is either one of the style constants defined in
     * class <code>SWT</code> which is applicable to instances of this
     * class, or must be built by <em>bitwise OR</em>'ing together
     * (that is, using the <code>int</code> "|" operator) two or more
     * of those <code>SWT</code> style constants. The class description
     * lists the style constants that are applicable to the class.
     * Style bits are also inherited from superclasses.
     * </p>
     *
     * @param parent a widget which will be the parent of the new instance (cannot be null)
     * @param style  the style of widget to construct
     * @throws IllegalArgumentException <ul>
     *                                  <li>ERROR_NULL_ARGUMENT - if the parent is null</li>
     *                                  </ul>
     * @throws SWTException             <ul>
     *                                  <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the parent</li>
     *                                  </ul>
     * @throws SWTError                 <ul>
     *                                  <li>ERROR_NO_HANDLES if a handle could not be obtained for browser creation</li>
     *                                  </ul>
     * @see Widget#getStyle
     * @since 3.0
     */
    public BrowserView(Composite parent, int style) {
        super(checkParent(parent), checkStyle(style));
        userStyle = style;

        String platform = SWT.getPlatform();
        if ("gtk".equals(platform)) { //$NON-NLS-1$
            parent.getDisplay().setData(NO_INPUT_METHOD, null);
        }

        style = getStyle();
        webBrowser = new EdgeBrowser();
        if (webBrowser != null) {
            webBrowser.setBrowser(this);
            webBrowser.create(parent, style);
            return;
        } else {
            this.proxy = new Browser(parent, style);
        }
        dispose();
        SWT.error(SWT.ERROR_NO_HANDLES);
    }

    static Composite checkParent(Composite parent) {
        String platform = SWT.getPlatform();
        if (!"gtk".equals(platform)) return parent; //$NON-NLS-1$

        /*
         * Note.  Mozilla provides all IM support needed for text input in web pages.
         * If SWT creates another input method context for the widget it will cause
         * indeterminate results to happen (hangs and crashes). The fix is to prevent
         * SWT from creating an input method context for the  Browser widget.
         */
        if (parent != null && !parent.isDisposed()) {
            Display display = parent.getDisplay();
            if (display != null) {
                if (display.getThread() == Thread.currentThread()) {
                    display.setData(NO_INPUT_METHOD, "true"); //$NON-NLS-1$
                }
            }
        }
        return parent;
    }

    static int checkStyle(int style) {
        String platform = SWT.getPlatform();
        if (DefaultType == SWT.DEFAULT) {
            /*
             * Some Browser clients that explicitly specify the native renderer to use
             * (by creating a Browser with style SWT.MOZILLA or SWT.WEBKIT) may also
             * need to specify that all "default" Browser instances (those created with
             * style SWT.NONE) should use this renderer as well.  This may be needed in
             * order to avoid incompatibilities that can arise from having multiple
             * native renderers loaded within the same process.  A client can do this by
             * setting the "org.eclipse.swt.browser.DefaultType" java system property to
             * a value like "mozilla" or "webkit".
             */

            /*
             * Plug-ins need an opportunity to set the org.eclipse.swt.browser.DefaultType
             * system property before the first Browser is created.  To facilitate this,
             * reflection is used to reference non-existent class
             * org.eclipse.swt.browser.BrowserInitializer the first time a Browser is created.
             * A client wishing to use this hook can do so by creating a fragment of
             * org.eclipse.swt that implements this class and sets the system property in its
             * static initializer.
             */
            try {
                Class.forName("org.eclipse.swt.browser.BrowserInitializer"); //$NON-NLS-1$
            } catch (ClassNotFoundException e) {
                /* no fragment is providing this class, which is the typical case */
            }

            String value = System.getProperty(PROPERTY_DEFAULTTYPE);
            if (value != null) {
                int index = 0;
                int length = value.length();
                do {
                    int newIndex = value.indexOf(',', index);
                    if (newIndex == -1) {
                        newIndex = length;
                    }
                    String current = value.substring(index, newIndex).trim();
                    if (current.equalsIgnoreCase("mozilla")) { //$NON-NLS-1$
                        DefaultType = SWT.MOZILLA;
                        break;
                    } else if (current.equalsIgnoreCase("webkit")) { //$NON-NLS-1$
                        DefaultType = SWT.WEBKIT;
                        break;
                    } else if (current.equalsIgnoreCase("ie") && "win32".equals(platform)) { //$NON-NLS-1$ //$NON-NLS-2$
                        DefaultType = SWT.NONE;
                        break;
                    }
                    index = newIndex + 1;
                } while (index < length);
            }
            if (DefaultType == SWT.DEFAULT) {
                DefaultType = SWT.NONE;
            }
        }

        if ((style & (SWT.MOZILLA | SWT.WEBKIT)) == 0) {
            style |= DefaultType;
        }

        if ((style & (SWT.MOZILLA | SWT.WEBKIT)) == (SWT.MOZILLA | SWT.WEBKIT)) {
            style &= ~SWT.WEBKIT;
        }
        if ((style & SWT.MOZILLA) != 0 || (style & SWT.WEBKIT) != 0) {
            return style;
        }

        if ("win32".equals(platform)) { //$NON-NLS-1$
            /*
             * For IE on win32 the border is supplied by the embedded browser, so remove
             * the style so that the parent Composite will not draw a second border.
             */
            return style & ~SWT.BORDER;
        }
        return style;
    }

    @Override
    protected void checkWidget() {
        super.checkWidget();
    }

    /**
     * Clears all session cookies from all current Browser instances.
     *
     * @since 3.2
     */
    public static void clearSessions() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the value of a cookie that is associated with a URL.
     * Note that cookies are shared amongst all Browser instances.
     *
     * @param name the cookie name
     * @param url  the URL that the cookie is associated with
     * @return the cookie value, or <code>null</code> if no such cookie exists
     * @throws IllegalArgumentException <ul>
     *                                  <li>ERROR_NULL_ARGUMENT - if the name is null</li>
     *                                  <li>ERROR_NULL_ARGUMENT - if the url is null</li>
     *                                  </ul>
     * @since 3.5
     */
    public static String getCookie(String name, String url) {
        throw new UnsupportedOperationException();
    }

    /**
     * Sets a cookie on a URL.  Note that cookies are shared amongst all Browser instances.
     * <p>
     * The <code>value</code> parameter must be a cookie header string that
     * complies with <a href="http://www.ietf.org/rfc/rfc2109.txt">RFC 2109</a>.
     * The value is passed through to the native browser unchanged.
     * <p>
     * Example value strings:
     * <code>foo=bar</code> (basic session cookie)
     * <code>foo=bar; path=/; domain=.eclipse.org</code> (session cookie)
     * <code>foo=bar; expires=Thu, 01-Jan-2030 00:00:01 GMT</code> (persistent cookie)
     * <code>foo=; expires=Thu, 01-Jan-1970 00:00:01 GMT</code> (deletes cookie <code>foo</code>)
     *
     * @param value the cookie value
     * @param url   the URL to associate the cookie with
     * @return <code>true</code> if the cookie was successfully set and <code>false</code> otherwise
     * @throws IllegalArgumentException <ul>
     *                                  <li>ERROR_NULL_ARGUMENT - if the value is null</li>
     *                                  <li>ERROR_NULL_ARGUMENT - if the url is null</li>
     *                                  </ul>
     * @since 3.5
     */
    public static boolean setCookie(String value, String url) {
        throw new UnsupportedOperationException();
    }

    /**
     * Adds the listener to the collection of listeners who will be
     * notified when authentication is required.
     * <p>
     * This notification occurs when a page requiring authentication is
     * encountered.
     * </p>
     *
     * @param listener the listener which should be notified
     * @throws IllegalArgumentException <ul>
     *                                  <li>ERROR_NULL_ARGUMENT - if the listener is null</li>
     *                                  </ul>
     * @throws SWTException             <ul>
     *                                  <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong thread</li>
     *                                  <li>ERROR_WIDGET_DISPOSED when the widget has been disposed</li>
     *                                  </ul>
     * @since 3.5
     */
    public void addAuthenticationListener(AuthenticationListener listener) {
        if (proxy != null) {
            proxy.addAuthenticationListener(listener);
        } else {
            checkWidget();
            if (listener == null) SWT.error(SWT.ERROR_NULL_ARGUMENT);
            webBrowser.addAuthenticationListener(listener);
        }

    }

    /**
     * Adds the listener to the collection of listeners who will be
     * notified when the window hosting the receiver should be closed.
     * <p>
     * This notification occurs when a javascript command such as
     * <code>window.close</code> gets executed by a <code>Browser</code>.
     * </p>
     *
     * @param listener the listener which should be notified
     * @throws IllegalArgumentException <ul>
     *                                  <li>ERROR_NULL_ARGUMENT - if the listener is null</li>
     *                                  </ul>
     * @throws SWTException             <ul>
     *                                  <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong thread</li>
     *                                  <li>ERROR_WIDGET_DISPOSED when the widget has been disposed</li>
     *                                  </ul>
     * @since 3.0
     */
    public void addCloseWindowListener(CloseWindowListener listener) {
        if (proxy != null) {
            proxy.addCloseWindowListener(listener);
        } else {
            checkWidget();
            if (listener == null) SWT.error(SWT.ERROR_NULL_ARGUMENT);
            webBrowser.addCloseWindowListener(listener);
        }

    }

    /**
     * Adds the listener to the collection of listeners who will be
     * notified when the current location has changed or is about to change.
     * <p>
     * This notification typically occurs when the application navigates
     * to a new location with {@link #setUrl(String)} or when the user
     * activates a hyperlink.
     * </p>
     *
     * @param listener the listener which should be notified
     * @throws IllegalArgumentException <ul>
     *                                  <li>ERROR_NULL_ARGUMENT - if the listener is null</li>
     *                                  </ul>
     * @throws SWTException             <ul>
     *                                  <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong thread</li>
     *                                  <li>ERROR_WIDGET_DISPOSED when the widget has been disposed</li>
     *                                  </ul>
     * @since 3.0
     */
    public void addLocationListener(LocationListener listener) {
        if (proxy != null) {
            proxy.addLocationListener(listener);
        } else {
            checkWidget();
            if (listener == null) SWT.error(SWT.ERROR_NULL_ARGUMENT);
            webBrowser.addLocationListener(listener);
        }

    }

    /**
     * Adds the listener to the collection of listeners who will be
     * notified when a new window needs to be created.
     * <p>
     * This notification occurs when a javascript command such as
     * <code>window.open</code> gets executed by a <code>Browser</code>.
     * </p>
     *
     * @param listener the listener which should be notified
     * @throws IllegalArgumentException <ul>
     *                                  <li>ERROR_NULL_ARGUMENT - if the listener is null</li>
     *                                  </ul>
     * @throws SWTException             <ul>
     *                                  <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong thread</li>
     *                                  <li>ERROR_WIDGET_DISPOSED when the widget has been disposed</li>
     *                                  </ul>
     * @since 3.0
     */
    public void addOpenWindowListener(OpenWindowListener listener) {
        if (proxy != null) {
            proxy.addOpenWindowListener(listener);
        } else {
            checkWidget();
            if (listener == null) SWT.error(SWT.ERROR_NULL_ARGUMENT);
            webBrowser.addOpenWindowListener(listener);
        }

    }

    /**
     * Adds the listener to the collection of listeners who will be
     * notified when a progress is made during the loading of the current
     * URL or when the loading of the current URL has been completed.
     *
     * @param listener the listener which should be notified
     * @throws IllegalArgumentException <ul>
     *                                  <li>ERROR_NULL_ARGUMENT - if the listener is null</li>
     *                                  </ul>
     * @throws SWTException             <ul>
     *                                  <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong thread</li>
     *                                  <li>ERROR_WIDGET_DISPOSED when the widget has been disposed</li>
     *                                  </ul>
     * @since 3.0
     */
    public void addProgressListener(ProgressListener listener) {
        if (proxy != null) {
            proxy.addProgressListener(listener);
        } else {
            checkWidget();
            if (listener == null) SWT.error(SWT.ERROR_NULL_ARGUMENT);
            webBrowser.addProgressListener(listener);
        }

    }

    /**
     * Adds the listener to the collection of listeners who will be
     * notified when the status text is changed.
     * <p>
     * The status text is typically displayed in the status bar of
     * a browser application.
     * </p>
     *
     * @param listener the listener which should be notified
     * @throws IllegalArgumentException <ul>
     *                                  <li>ERROR_NULL_ARGUMENT - if the listener is null</li>
     *                                  </ul>
     * @throws SWTException             <ul>
     *                                  <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong thread</li>
     *                                  <li>ERROR_WIDGET_DISPOSED when the widget has been disposed</li>
     *                                  </ul>
     * @since 3.0
     */
    public void addStatusTextListener(StatusTextListener listener) {
        if (proxy != null) {
            proxy.addStatusTextListener(listener);
        } else {
            checkWidget();
            if (listener == null) SWT.error(SWT.ERROR_NULL_ARGUMENT);
            webBrowser.addStatusTextListener(listener);
        }

    }

    /**
     * Adds the listener to the collection of listeners who will be
     * notified when the title of the current document is available
     * or has changed.
     *
     * @param listener the listener which should be notified
     * @throws IllegalArgumentException <ul>
     *                                  <li>ERROR_NULL_ARGUMENT - if the listener is null</li>
     *                                  </ul>
     * @throws SWTException             <ul>
     *                                  <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong thread</li>
     *                                  <li>ERROR_WIDGET_DISPOSED when the widget has been disposed</li>
     *                                  </ul>
     * @since 3.0
     */
    public void addTitleListener(TitleListener listener) {
        if (proxy != null) {
            proxy.addTitleListener(listener);
        } else {
            checkWidget();
            if (listener == null) SWT.error(SWT.ERROR_NULL_ARGUMENT);
            webBrowser.addTitleListener(listener);
        }

    }

    /**
     * Adds the listener to the collection of listeners who will be
     * notified when a window hosting the receiver needs to be displayed
     * or hidden.
     *
     * @param listener the listener which should be notified
     * @throws IllegalArgumentException <ul>
     *                                  <li>ERROR_NULL_ARGUMENT - if the listener is null</li>
     *                                  </ul>
     * @throws SWTException             <ul>
     *                                  <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong thread</li>
     *                                  <li>ERROR_WIDGET_DISPOSED when the widget has been disposed</li>
     *                                  </ul>
     * @since 3.0
     */
    public void addVisibilityWindowListener(VisibilityWindowListener listener) {
        if (proxy != null) {
            proxy.addVisibilityWindowListener(listener);
        } else {
            checkWidget();
            if (listener == null) SWT.error(SWT.ERROR_NULL_ARGUMENT);
            webBrowser.addVisibilityWindowListener(listener);
        }

    }

    /**
     * Navigate to the previous session history item.
     *
     * @return <code>true</code> if the operation was successful and <code>false</code> otherwise
     * @throws SWTException <ul>
     *                      <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong thread</li>
     *                      <li>ERROR_WIDGET_DISPOSED when the widget has been disposed</li>
     *                      </ul>
     * @see #forward
     * @since 3.0
     */
    public boolean back() {
        if (proxy != null) {
            return proxy.back();
        } else {
            checkWidget();
            return webBrowser.back();
        }

    }


    /**
     * Executes the specified script.
     * <p>
     * Executes a script containing javascript commands in the context of the current document.
     * If document-defined functions or properties are accessed by the script then this method
     * should not be invoked until the document has finished loading (<code>ProgressListener.completed()</code>
     * gives notification of this).
     *
     * @param script the script with javascript commands
     * @return <code>true</code> if the operation was successful and <code>false</code> otherwise
     * @throws IllegalArgumentException <ul>
     *                                  <li>ERROR_NULL_ARGUMENT - if the script is null</li>
     *                                  </ul>
     * @throws SWTException             <ul>
     *                                  <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong thread</li>
     *                                  <li>ERROR_WIDGET_DISPOSED when the widget has been disposed</li>
     *                                  </ul>
     * @see ProgressListener#completed(ProgressEvent)
     * @since 3.1
     */
    public boolean execute(String script) {
        if (proxy != null) {
            return proxy.execute(script);
        } else {
            checkWidget();
            if (script == null) SWT.error(SWT.ERROR_NULL_ARGUMENT);
            return webBrowser.execute(script);
        }

    }

    /**
     * Attempts to dispose the receiver, but allows the dispose to be vetoed
     * by the user in response to an <code>onbeforeunload</code> listener
     * in the Browser's current page.
     *
     * @return <code>true</code> if the receiver was disposed, and <code>false</code> otherwise
     * @throws SWTException <ul>
     *                      <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *                      <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     *                      </ul>
     * @see #dispose()
     * @since 3.6
     */
    public boolean close() {
        if (proxy != null) {
            return proxy.close();
        } else {
            checkWidget();
            if (webBrowser.close()) {
                isClosing = true;
                dispose();
                isClosing = false;
                return true;
            }
            return false;
        }

    }

    /**
     * Returns the result, if any, of executing the specified script.
     * <p>
     * Evaluates a script containing javascript commands in the context of
     * the current document.  If document-defined functions or properties
     * are accessed by the script then this method should not be invoked
     * until the document has finished loading (<code>ProgressListener.completed()</code>
     * gives notification of this).
     * </p><p>
     * If the script returns a value with a supported type then a java
     * representation of the value is returned.  The supported
     * javascript -> java mappings are:
     * <ul>
     * <li>javascript null or undefined -> <code>null</code></li>
     * <li>javascript number -> <code>java.lang.Double</code></li>
     * <li>javascript string -> <code>java.lang.String</code></li>
     * <li>javascript boolean -> <code>java.lang.Boolean</code></li>
     * <li>javascript array whose elements are all of supported types -> <code>java.lang.Object[]</code></li>
     * </ul>
     * <p>
     * An <code>SWTException</code> is thrown if the return value has an
     * unsupported type, or if evaluating the script causes a javascript
     * error to be thrown.
     *
     * @param script the script with javascript commands
     * @return the return value, if any, of executing the script
     * @throws IllegalArgumentException <ul>
     *                                     <li>ERROR_NULL_ARGUMENT - if the script is null</li>
     *                                  </ul>
     * @throws SWTException             <ul>
     *                                     <li>ERROR_FAILED_EVALUATE when the script evaluation causes a javascript error to be thrown</li>
     *                                     <li>ERROR_INVALID_RETURN_VALUE when the script returns a value of unsupported type</li>
     *                                     <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong thread</li>
     *                                     <li>ERROR_WIDGET_DISPOSED when the widget has been disposed</li>
     *                                  </ul>
     * @see Browser#evaluate(String, boolean)
     * @see ProgressListener#completed(ProgressEvent)
     * @since 3.5
     */
    public Object evaluate(String script) throws SWTException {
        if (proxy != null) {
            return proxy.evaluate(script);
        } else {
            checkWidget();
            return evaluate(script, false);
        }

    }

    /**
     * Returns the result, if any, of executing the specified script.
     * <p>
     * Evaluates a script containing javascript commands.
     * When <code>trusted</code> is <code>true</code> script is executed in the context of Chrome
     * with Chrome security privileges.
     * When <code>trusted</code> is <code>false</code> script is executed in the context of the
     * current document with normal privileges.
     * </p><p>
     * If document-defined functions or properties are accessed by the script then
     * this method should not be invoked until the document has finished loading
     * (<code>ProgressListener.completed()</code> gives notification of this).
     * </p><p>
     * If the script returns a value with a supported type then a java
     * representation of the value is returned.  The supported
     * javascript -> java mappings are:
     * <ul>
     * <li>javascript null or undefined -> <code>null</code></li>
     * <li>javascript number -> <code>java.lang.Double</code></li>
     * <li>javascript string -> <code>java.lang.String</code></li>
     * <li>javascript boolean -> <code>java.lang.Boolean</code></li>
     * <li>javascript array whose elements are all of supported types -> <code>java.lang.Object[]</code></li>
     * </ul>
     * An <code>SWTException</code> is thrown if the return value has an
     * unsupported type, or if evaluating the script causes a javascript
     * error to be thrown.
     * </p><p>
     * Note: Chrome security context is applicable only to Browsers with style <code>SWT.Mozilla</code>.
     * </p>
     *
     * @param script  the script with javascript commands
     * @param trusted <code>true> or <code>false</code> depending on the security context to be used
     * @return the return value, if any, of executing the script
     * @throws IllegalArgumentException <ul>
     *                                     <li>ERROR_NULL_ARGUMENT - if the script is null</li>
     *                                  </ul>
     * @throws SWTException             <ul>
     *                                     <li>ERROR_FAILED_EVALUATE when the script evaluation causes a javascript error to be thrown</li>
     *                                     <li>ERROR_INVALID_RETURN_VALUE when the script returns a value of unsupported type</li>
     *                                     <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong thread</li>
     *                                     <li>ERROR_WIDGET_DISPOSED when the widget has been disposed</li>
     *                                  </ul>
     * @see ProgressListener#completed(ProgressEvent)
     */
    public Object evaluate(String script, boolean trusted) throws SWTException {
        if (proxy != null) {
            return proxy.evaluate(script, trusted);
        } else {
            checkWidget();
            if (script == null) SWT.error(SWT.ERROR_NULL_ARGUMENT);
            return webBrowser.evaluate(script, trusted);
        }


    }

    /**
     * Navigate to the next session history item.
     *
     * @return <code>true</code> if the operation was successful and <code>false</code> otherwise
     * @throws SWTException <ul>
     *                      <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong thread</li>
     *                      <li>ERROR_WIDGET_DISPOSED when the widget has been disposed</li>
     *                      </ul>
     * @see #back
     * @since 3.0
     */
    public boolean forward() {
        if (proxy != null) {
            return proxy.forward();
        } else {
            checkWidget();
            return webBrowser.forward();
        }

    }

    /**
     * Returns the type of native browser being used by this instance.
     * Examples: "ie", "mozilla", "voyager", "webkit"
     *
     * @return the type of the native browser
     * @since 3.5
     */
    public String getBrowserType() {
        if (proxy != null) {
            return proxy.getBrowserType();
        } else {
            checkWidget();
            return webBrowser.getBrowserType();
        }

    }

    /**
     * Returns <code>true</code> if javascript will be allowed to run in pages
     * subsequently viewed in the receiver, and <code>false</code> otherwise.
     * Note that this may not reflect the javascript enablement on the currently-
     * viewed page if <code>setJavascriptEnabled()</code> has been invoked during
     * its lifetime.
     *
     * @return the receiver's javascript enabled state
     * @throws SWTException <ul>
     *                      <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *                      <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     *                      </ul>
     * @see #setJavascriptEnabled
     * @since 3.5
     */
    public boolean getJavascriptEnabled() {
        if (proxy != null) {
            return proxy.getJavascriptEnabled();
        } else {
            checkWidget();
            return webBrowser.jsEnabledOnNextPage;
        }

    }

    @Override
    public int getStyle() {
        if (proxy != null) {
            return proxy.getStyle();
        } else {
            /*
             * If SWT.BORDER was specified at creation time then getStyle() should answer
             * it even though it is removed for IE on win32 in checkStyle().
             */
            return super.getStyle() | (userStyle & SWT.BORDER);
        }

    }

    /**
     * Returns a string with HTML that represents the content of the current page.
     *
     * @return HTML representing the current page or an empty <code>String</code>
     * if this is empty
     * @throws SWTException <ul>
     *                      <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong thread</li>
     *                      <li>ERROR_WIDGET_DISPOSED when the widget has been disposed</li>
     *                      </ul>
     * @since 3.4
     */
    public String getText() {
        if (proxy != null) {
            return proxy.getText();
        } else {
            /*
             * If SWT.BORDER was specified at creation time then getStyle() should answer
             * it even though it is removed for IE on win32 in checkStyle().
             */
            checkWidget();
            return webBrowser.getText();
        }
    }

    /**
     * Returns the current URL.
     *
     * @return the current URL or an empty <code>String</code> if there is no current URL
     * @throws SWTException <ul>
     *                      <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong thread</li>
     *                      <li>ERROR_WIDGET_DISPOSED when the widget has been disposed</li>
     *                      </ul>
     * @see #setUrl
     * @since 3.0
     */
    public String getUrl() {
        if (proxy != null) {
            return proxy.getUrl();
        } else {
            /*
             * If SWT.BORDER was specified at creation time then getStyle() should answer
             * it even though it is removed for IE on win32 in checkStyle().
             */
            checkWidget();
            return webBrowser.getUrl();
        }
    }

    /**
     * Returns the JavaXPCOM <code>nsIWebBrowser</code> for the receiver, or <code>null</code>
     * if it is not available.  In order for an <code>nsIWebBrowser</code> to be returned all
     * of the following must be true: <ul>
     * <li>the receiver's style must be <code>SWT.MOZILLA</code></li>
     * <li>the classes from JavaXPCOM &gt;= 1.8.1.2 must be resolvable at runtime</li>
     * <li>the version of the underlying XULRunner must be &gt;= 1.8.1.2</li>
     * </ul>
     *
     * @return the receiver's JavaXPCOM <code>nsIWebBrowser</code> or <code>null</code>
     * @since 3.3
     */
    public Object getWebBrowser() {
        if (proxy != null) {
            return proxy.getUrl();
        } else {
            /*
             * If SWT.BORDER was specified at creation time then getStyle() should answer
             * it even though it is removed for IE on win32 in checkStyle().
             */
            checkWidget();
            return webBrowser.getWebBrowser();
        }

    }

    /**
     * Returns <code>true</code> if the receiver can navigate to the
     * previous session history item, and <code>false</code> otherwise.
     *
     * @return the receiver's back command enabled state
     * @throws SWTException <ul>
     *                      <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *                      <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     *                      </ul>
     * @see #back
     */
    public boolean isBackEnabled() {
        if (proxy != null) {
            return proxy.isBackEnabled();
        } else {
            /*
             * If SWT.BORDER was specified at creation time then getStyle() should answer
             * it even though it is removed for IE on win32 in checkStyle().
             */
            checkWidget();
            return webBrowser.isBackEnabled();
        }

    }

    @Override
    public boolean isFocusControl() {
        if (proxy != null) {
            return proxy.isFocusControl();
        } else {
            checkWidget();
            if (webBrowser.isFocusControl()) return true;
            return super.isFocusControl();
        }

    }

    /**
     * Returns <code>true</code> if the receiver can navigate to the
     * next session history item, and <code>false</code> otherwise.
     *
     * @return the receiver's forward command enabled state
     * @throws SWTException <ul>
     *                      <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *                      <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     *                      </ul>
     * @see #forward
     */
    public boolean isForwardEnabled() {
        if (proxy != null) {
            return proxy.isForwardEnabled();
        } else {
            checkWidget();
            return webBrowser.isForwardEnabled();
        }

    }

    /**
     * Refresh the current page.
     *
     * @throws SWTException <ul>
     *                      <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong thread</li>
     *                      <li>ERROR_WIDGET_DISPOSED when the widget has been disposed</li>
     *                      </ul>
     * @since 3.0
     */
    public void refresh() {
        if (proxy != null) {
            proxy.refresh();
        } else {
            checkWidget();
            webBrowser.refresh();
        }

    }

    /**
     * Removes the listener from the collection of listeners who will
     * be notified when authentication is required.
     *
     * @param listener the listener which should no longer be notified
     * @throws IllegalArgumentException <ul>
     *                                  <li>ERROR_NULL_ARGUMENT - if the listener is null</li>
     *                                  </ul>
     * @throws SWTException             <ul>
     *                                  <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong thread</li>
     *                                  <li>ERROR_WIDGET_DISPOSED when the widget has been disposed</li>
     *                                  </ul>
     * @since 3.5
     */
    public void removeAuthenticationListener(AuthenticationListener listener) {
        if (proxy != null) {
            proxy.removeAuthenticationListener(listener);
        } else {
            checkWidget();
            if (listener == null) SWT.error(SWT.ERROR_NULL_ARGUMENT);
            webBrowser.removeAuthenticationListener(listener);
        }

    }

    /**
     * Removes the listener from the collection of listeners who will
     * be notified when the window hosting the receiver should be closed.
     *
     * @param listener the listener which should no longer be notified
     * @throws IllegalArgumentException <ul>
     *                                  <li>ERROR_NULL_ARGUMENT - if the listener is null</li>
     *                                  </ul>
     * @throws SWTException             <ul>
     *                                  <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong thread</li>
     *                                  <li>ERROR_WIDGET_DISPOSED when the widget has been disposed</li>
     *                                  </ul>
     * @since 3.0
     */
    public void removeCloseWindowListener(CloseWindowListener listener) {
        if (proxy != null) {
            proxy.removeCloseWindowListener(listener);
        } else {
            checkWidget();
            if (listener == null) SWT.error(SWT.ERROR_NULL_ARGUMENT);
            webBrowser.removeCloseWindowListener(listener);
        }

    }

    /**
     * Removes the listener from the collection of listeners who will
     * be notified when the current location is changed or about to be changed.
     *
     * @param listener the listener which should no longer be notified
     * @throws IllegalArgumentException <ul>
     *                                  <li>ERROR_NULL_ARGUMENT - if the listener is null</li>
     *                                  </ul>
     * @throws SWTException             <ul>
     *                                  <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong thread</li>
     *                                  <li>ERROR_WIDGET_DISPOSED when the widget has been disposed</li>
     *                                  </ul>
     * @since 3.0
     */
    public void removeLocationListener(LocationListener listener) {
        if (proxy != null) {
            proxy.removeLocationListener(listener);
        } else {
            checkWidget();
            if (listener == null) SWT.error(SWT.ERROR_NULL_ARGUMENT);
            webBrowser.removeLocationListener(listener);
        }

    }

    /**
     * Removes the listener from the collection of listeners who will
     * be notified when a new window needs to be created.
     *
     * @param listener the listener which should no longer be notified
     * @throws IllegalArgumentException <ul>
     *                                  <li>ERROR_NULL_ARGUMENT - if the listener is null</li>
     *                                  </ul>
     * @throws SWTException             <ul>
     *                                  <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong thread</li>
     *                                  <li>ERROR_WIDGET_DISPOSED when the widget has been disposed</li>
     *                                  </ul>
     * @since 3.0
     */
    public void removeOpenWindowListener(OpenWindowListener listener) {
        if (proxy != null) {
            proxy.removeOpenWindowListener(listener);
        } else {
            checkWidget();
            if (listener == null) SWT.error(SWT.ERROR_NULL_ARGUMENT);
            webBrowser.removeOpenWindowListener(listener);
        }

    }

    /**
     * Removes the listener from the collection of listeners who will
     * be notified when a progress is made during the loading of the current
     * URL or when the loading of the current URL has been completed.
     *
     * @param listener the listener which should no longer be notified
     * @throws IllegalArgumentException <ul>
     *                                  <li>ERROR_NULL_ARGUMENT - if the listener is null</li>
     *                                  </ul>
     * @throws SWTException             <ul>
     *                                  <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong thread</li>
     *                                  <li>ERROR_WIDGET_DISPOSED when the widget has been disposed</li>
     *                                  </ul>
     * @since 3.0
     */
    public void removeProgressListener(ProgressListener listener) {
        if (proxy != null) {
            proxy.removeProgressListener(listener);
        } else {
            checkWidget();
            if (listener == null) SWT.error(SWT.ERROR_NULL_ARGUMENT);
            webBrowser.removeProgressListener(listener);
        }

    }

    /**
     * Removes the listener from the collection of listeners who will
     * be notified when the status text is changed.
     *
     * @param listener the listener which should no longer be notified
     * @throws IllegalArgumentException <ul>
     *                                  <li>ERROR_NULL_ARGUMENT - if the listener is null</li>
     *                                  </ul>
     * @throws SWTException             <ul>
     *                                  <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong thread</li>
     *                                  <li>ERROR_WIDGET_DISPOSED when the widget has been disposed</li>
     *                                  </ul>
     * @since 3.0
     */
    public void removeStatusTextListener(StatusTextListener listener) {
        if (proxy != null) {
            proxy.removeStatusTextListener(listener);
        } else {
            checkWidget();
            if (listener == null) SWT.error(SWT.ERROR_NULL_ARGUMENT);
            webBrowser.removeStatusTextListener(listener);
        }

    }

    /**
     * Removes the listener from the collection of listeners who will
     * be notified when the title of the current document is available
     * or has changed.
     *
     * @param listener the listener which should no longer be notified
     * @throws IllegalArgumentException <ul>
     *                                  <li>ERROR_NULL_ARGUMENT - if the listener is null</li>
     *                                  </ul>
     * @throws SWTException             <ul>
     *                                  <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong thread</li>
     *                                  <li>ERROR_WIDGET_DISPOSED when the widget has been disposed</li>
     *                                  </ul>
     * @since 3.0
     */
    public void removeTitleListener(TitleListener listener) {
        if (proxy != null) {
            proxy.removeTitleListener(listener);
        } else {
            checkWidget();
            if (listener == null) SWT.error(SWT.ERROR_NULL_ARGUMENT);
            webBrowser.removeTitleListener(listener);
        }

    }

    /**
     * Removes the listener from the collection of listeners who will
     * be notified when a window hosting the receiver needs to be displayed
     * or hidden.
     *
     * @param listener the listener which should no longer be notified
     * @throws IllegalArgumentException <ul>
     *                                  <li>ERROR_NULL_ARGUMENT - if the listener is null</li>
     *                                  </ul>
     * @throws SWTException             <ul>
     *                                  <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong thread</li>
     *                                  <li>ERROR_WIDGET_DISPOSED when the widget has been disposed</li>
     *                                  </ul>
     * @since 3.0
     */
    public void removeVisibilityWindowListener(VisibilityWindowListener listener) {
        if (proxy != null) {
            proxy.removeVisibilityWindowListener(listener);
        } else {
            checkWidget();
            if (listener == null) SWT.error(SWT.ERROR_NULL_ARGUMENT);
            webBrowser.removeVisibilityWindowListener(listener);
        }

    }

    /**
     * Sets whether javascript will be allowed to run in pages subsequently
     * viewed in the receiver.  Note that setting this value does not affect
     * the running of javascript in the current page.
     *
     * @param enabled the receiver's new javascript enabled state
     * @throws SWTException <ul>
     *                      <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *                      <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     *                      </ul>
     * @since 3.5
     */
    public void setJavascriptEnabled(boolean enabled) {
        if (proxy != null) {
            proxy.setJavascriptEnabled(enabled);
        } else {
            checkWidget();
            webBrowser.jsEnabledOnNextPage = enabled;
        }

    }

    /**
     * Renders a string containing HTML.  The rendering of the content occurs asynchronously.
     * The rendered page will be given trusted permissions; to render the page with untrusted
     * permissions use <code>setText(String html, boolean trusted)</code> instead.
     * <p>
     * The html parameter is Unicode-encoded since it is a java <code>String</code>.
     * As a result, the HTML meta tag charset should not be set. The charset is implied
     * by the <code>String</code> itself.
     *
     * @param html the HTML content to be rendered
     * @return true if the operation was successful and false otherwise.
     * @throws IllegalArgumentException <ul>
     *                                  <li>ERROR_NULL_ARGUMENT - if the html is null</li>
     *                                  </ul>
     * @throws SWTException             <ul>
     *                                  <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong thread</li>
     *                                  <li>ERROR_WIDGET_DISPOSED when the widget has been disposed</li>
     *                                  </ul>
     * @see #setText(String, boolean)
     * @see #setUrl
     * @since 3.0
     */
    public boolean setText(String html) {
        if (proxy != null) {
            return proxy.setText(html);
        } else {
            checkWidget();
            return setText(html, true);
        }

    }

    /**
     * Renders a string containing HTML.  The rendering of the content occurs asynchronously.
     * The rendered page can be given either trusted or untrusted permissions.
     * <p>
     * The <code>html</code> parameter is Unicode-encoded since it is a java <code>String</code>.
     * As a result, the HTML meta tag charset should not be set. The charset is implied
     * by the <code>String</code> itself.
     * <p>
     * The <code>trusted</code> parameter affects the permissions that will be granted to the rendered
     * page.  Specifying <code>true</code> for trusted gives the page permissions equivalent
     * to a page on the local file system, while specifying <code>false</code> for trusted
     * gives the page permissions equivalent to a page from the internet.  Page content should
     * be specified as trusted if the invoker created it or trusts its source, since this would
     * allow (for instance) style sheets on the local file system to be referenced.  Page
     * content should be specified as untrusted if its source is not trusted or is not known.
     *
     * @param html    the HTML content to be rendered
     * @param trusted <code>false</code> if the rendered page should be granted restricted
     *                permissions and <code>true</code> otherwise
     * @return <code>true</code> if the operation was successful and <code>false</code> otherwise.
     * @throws IllegalArgumentException <ul>
     *                                  <li>ERROR_NULL_ARGUMENT - if the html is null</li>
     *                                  </ul>
     * @throws SWTException             <ul>
     *                                  <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong thread</li>
     *                                  <li>ERROR_WIDGET_DISPOSED when the widget has been disposed</li>
     *                                  </ul>
     * @see #setText(String)
     * @see #setUrl
     * @since 3.6
     */
    public boolean setText(String html, boolean trusted) {
        if (proxy != null) {
            return proxy.setText(html, trusted);
        } else {
            checkWidget();
            if (html == null) SWT.error(SWT.ERROR_NULL_ARGUMENT);
            return webBrowser.setText(html, trusted);
        }

    }

    /**
     * Begins loading a URL.  The loading of its content occurs asynchronously.
     *
     * @param url the URL to be loaded
     * @return true if the operation was successful and false otherwise.
     * @throws IllegalArgumentException <ul>
     *                                  <li>ERROR_NULL_ARGUMENT - if the url is null</li>
     *                                  </ul>
     * @throws SWTException             <ul>
     *                                  <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong thread</li>
     *                                  <li>ERROR_WIDGET_DISPOSED when the widget has been disposed</li>
     *                                  </ul>
     * @see #getUrl
     * @see #setUrl(String, String, String[])
     * @since 3.0
     */
    public boolean setUrl(String url) {
        if (proxy != null) {
            return proxy.setUrl(url);
        } else {
            checkWidget();
            return setUrl(url, null, null);
        }

    }

    /**
     * Begins loading a URL.  The loading of its content occurs asynchronously.
     * <p>
     * If the URL causes an HTTP request to be initiated then the provided
     * <code>postData</code> and <code>header</code> arguments, if any, are
     * sent with the request.  A value in the <code>headers</code> argument
     * must be a name-value pair with a colon separator in order to be sent
     * (for example: "<code>user-agent: custom</code>").
     *
     * @param url      the URL to be loaded
     * @param postData post data to be sent with the request, or <code>null</code>
     * @param headers  header lines to be sent with the request, or <code>null</code>
     * @return <code>true</code> if the operation was successful and <code>false</code> otherwise.
     * @throws IllegalArgumentException <ul>
     *                                  <li>ERROR_NULL_ARGUMENT - if the url is null</li>
     *                                  </ul>
     * @throws SWTException             <ul>
     *                                  <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong thread</li>
     *                                  <li>ERROR_WIDGET_DISPOSED when the widget has been disposed</li>
     *                                  </ul>
     * @since 3.6
     */
    public boolean setUrl(String url, String postData, String[] headers) {
        if (proxy != null) {
            return proxy.setUrl(url, postData, headers);
        } else {
            checkWidget();
            if (url == null) SWT.error(SWT.ERROR_NULL_ARGUMENT);
            return webBrowser.setUrl(url, postData, headers);
        }

    }

    /**
     * Stop any loading and rendering activity.
     *
     * @throws SWTException <ul>
     *                      <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong thread</li>
     *                      <li>ERROR_WIDGET_DISPOSED when the widget has been disposed</li>
     *                      </ul>
     * @since 3.0
     */
    public void stop() {
        if (proxy != null) {
            proxy.stop();
        } else {
            checkWidget();
            webBrowser.stop();
        }

    }
}
