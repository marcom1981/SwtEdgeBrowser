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
package it.marcom.swtedge.nat;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Random;


/**
 * @author MarcoM
 */
public class NativeEdge {
    String currentNavigationData;
    private static final String WEBVIEW_TMPLIB_PREFIX = "webview";
    private static Random rnd = new Random();

    static {
        File loader = null;
        File webview = null;
        try {
            if (System.getProperty("sun.arch.data.model").equals("32")) {
                webview = extractFromResourcePath("/it/marcom/swtedge/nat/x86/webview.dll", "webview.dll", NativeEdge.class.getClassLoader());
                loader = extractFromResourcePath("/it/marcom/swtedge/nat/x86/WebView2Loader.dll", "WebView2Loader.dll", NativeEdge.class.getClassLoader());
            } else {
                webview = extractFromResourcePath("/it/marcom/swtedge/nat/x64/webview.dll", "webview.dll", NativeEdge.class.getClassLoader());
                loader = extractFromResourcePath("/it/marcom/swtedge/nat/x64/WebView2Loader.dll", "WebView2Loader.dll", NativeEdge.class.getClassLoader());
            }
        } catch (Exception ex) {
            throw new Error("Ubable to extract Library");
        }
        //Unsadisfated link error return to caller
        System.load(loader.getAbsolutePath());
        System.load(webview.getAbsolutePath());

    }

    private long edgePointer;


    public NativeEdge() throws NativeEdgeException {
        this(0);
    }

    public NativeEdge(long parentWindow) throws NativeEdgeException{
        edgePointer = create(0, parentWindow);
        if(getWindow(edgePointer)==0){
            destroy(edgePointer);
            edgePointer=0;
            throw new NativeEdgeException();
        }
    }


    public void bind(String name, WebViewNativeCallback fn) {
        bind(edgePointer, name, fn, edgePointer);
        navigate(currentNavigationData);
    }

    public void setBounds(int x, int y, int width, int height, int flags) {
        setBounds(edgePointer, x, y, width, height, flags);
    }


    public void eval(String js, EvaluationCallBack callBack) {
        eval(edgePointer, js, callBack);
    }

    public void initScript(String js) {
        init(edgePointer, js);
    }

    public void reload(){
        navigate(currentNavigationData);
    }

    public void navigate(String url) {
        currentNavigationData=url;
        navigate(edgePointer, url);
    }

    @Override
    public void finalize() throws Throwable {
        if(edgePointer!=0) {
            destroy(edgePointer);
        }
        super.finalize();
    }

    private native static long create(int debug, long window);


    private native static void destroy(long w);

    private native static void run(long w);

    private native static void terminate(long w);


    private native static void dispatch(long w, Runnable callback, long arg);


    private native static long getWindow(long w);

    private native static void setTitle(long w, String title);

    private native static void setBounds(long w, int x, int y, int width, int height, int flags);


    private native static void navigate(long w, String url);


    private native static void init(long w, String js);


    private native static void eval(long w, String js, EvaluationCallBack callBack);


    private native static void bind(long w, String name,
                                    WebViewNativeCallback fn, long arg);


    private static File extractFromResourcePath(String name, String libname, ClassLoader loader) throws IOException {

        if (loader == null) {
            loader = Thread.currentThread().getContextClassLoader();
            // Context class loader is not guaranteed to be set
            if (loader == null) {
                loader = NativeEdge.class.getClassLoader();
            }
        }


        String resourcePath = name;
        if (resourcePath.startsWith("/")) {
            resourcePath = resourcePath.substring(1);
        }
        URL url = loader.getResource(resourcePath);

        if (url == null) {
            String path = System.getProperty("java.class.path");
            if (loader instanceof URLClassLoader) {
                path = Arrays.asList(((URLClassLoader) loader).getURLs()).toString();
            }
            throw new IOException("Native library (" + resourcePath + ") not found in resource path (" + path + ")");
        }

        File lib = null;
        if (url.getProtocol().toLowerCase().equals("file")) {
            try {
                lib = new File(new URI(url.toString()));
            } catch (URISyntaxException e) {
                lib = new File(url.getPath());
            }

            if (!lib.exists()) {
                throw new IOException("File URL " + url + " could not be properly decoded");
            }
        } else {
            InputStream is = loader.getResourceAsStream(resourcePath);
            if (is == null) {
                throw new IOException("Can't obtain InputStream for " + resourcePath);
            }

            FileOutputStream fos = null;
            try {
                // Suffix is required on windows, or library fails to load
                // Let Java pick the suffix, except on windows, to avoid
                // problems with Web Start.
                File dir = new File(getTempDir().getAbsolutePath() + File.separator + "webview" + rnd.nextInt());
                dir.mkdirs();
                dir.deleteOnExit();
                lib = new File(dir.getAbsolutePath() + File.separator + libname);
                lib.deleteOnExit();
                fos = new FileOutputStream(lib);
                int count;
                byte[] buf = new byte[8192];
                while ((count = is.read(buf, 0, buf.length)) > 0) {
                    fos.write(buf, 0, count);
                }
            } catch (IOException e) {
                throw new IOException("Failed to create temporary file for " + name + " library: " + e.getMessage());
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                }
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                    }
                }
            }
        }
        return lib;
    }

    private static File extractFromResourcePath(String resourcePath, String file_name, ClassLoader loader, File temp_dir) throws IOException {

        if (loader == null) {
            loader = Thread.currentThread().getContextClassLoader();
            // Context class loader is not guaranteed to be set
            if (loader == null) {
                loader = NativeEdge.class.getClassLoader();
            }
        }

        if (resourcePath.startsWith("/")) {
            resourcePath = resourcePath.substring(1);
        }
        URL url = loader.getResource(resourcePath);

        if (url == null) {
            String path = System.getProperty("java.class.path");
            if (loader instanceof URLClassLoader) {
                path = Arrays.asList(((URLClassLoader) loader).getURLs()).toString();
            }
            throw new IOException("Native library (" + resourcePath + ") not found in resource path (" + path + ")");
        }

        File lib = null;
        if (url.getProtocol().toLowerCase().equals("file")) {
            try {
                lib = new File(new URI(url.toString()));
            } catch (URISyntaxException e) {
                lib = new File(url.getPath());
            }

            if (!lib.exists()) {
                throw new IOException("File URL " + url + " could not be properly decoded");
            }
        } else {
            InputStream is = loader.getResourceAsStream(resourcePath);
            if (is == null) {
                throw new IOException("Can't obtain InputStream for " + resourcePath);
            }

            FileOutputStream fos = null;
            try {
                // Suffix is required on windows, or library fails to load
                // Let Java pick the suffix, except on windows, to avoid
                // problems with Web Start.

                lib = new File(temp_dir.getAbsolutePath() + File.separator + file_name);

                fos = new FileOutputStream(lib);
                int count;
                byte[] buf = new byte[2040];
                while ((count = is.read(buf, 0, buf.length)) > 0) {
                    fos.write(buf, 0, count);
                }
            } catch (IOException e) {
                throw new IOException("Failed to create temporary file for " + resourcePath + " library: " + e.getMessage());
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                }
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                    }
                }
            }
        }
        return lib;
    }


    private static File getTempDir() throws IOException {
        File jnatmp;
        String prop = System.getProperty("jna.tmpdir");
        if (prop != null) {
            jnatmp = new File(prop);
            jnatmp.mkdirs();
        } else {
            File tmp = new File(System.getProperty("java.io.tmpdir"));
            // Loading DLLs via System.load() under a directory with a unicode
            // name will fail on windows, so use a hash code of the user's
            // name in case the user's name contains non-ASCII characters
            jnatmp = new File(tmp, "jna-" + System.getProperty("user.name").hashCode());
            jnatmp.mkdirs();
            if (!jnatmp.exists() || !jnatmp.canWrite()) {
                jnatmp = tmp;
            }
        }
        if (!jnatmp.exists()) {
            throw new IOException("JNA temporary directory '" + jnatmp + "' does not exist");
        }
        if (!jnatmp.canWrite()) {
            throw new IOException("JNA temporary directory '" + jnatmp + "' is not writable");
        }
        return jnatmp;
    }



}
