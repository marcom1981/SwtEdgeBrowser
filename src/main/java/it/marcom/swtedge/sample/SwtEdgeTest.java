package it.marcom.swtedge.sample;


import it.marcom.swtedge.BrowserView;
import it.marcom.swtedge.BrowserViewFunction;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;



public class SwtEdgeTest {
    static BrowserView browser;
    static String[] titles;
    static int index;

    public static void main(String[] args) {
        Display display = new Display();
        final Shell shell = new Shell(display);
        shell.setText("SWT Edge Browser - Test Viwer");
        shell.setLayout(new GridLayout());
        Composite compTools = new Composite(shell, SWT.NONE);
        GridData data = new GridData(GridData.FILL_HORIZONTAL);
        compTools.setLayoutData(data);
        compTools.setLayout(new GridLayout(2, false));

        ToolBar navBar = new ToolBar(compTools, SWT.NONE);
        navBar.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.HORIZONTAL_ALIGN_END));
        final ToolItem execute = new ToolItem(navBar, SWT.PUSH);
        execute.setText("Execute Script");

        final ToolItem evaluate = new ToolItem(navBar, SWT.PUSH);
        evaluate.setText("Evaluate Script");

        final ToolItem navigate = new ToolItem(navBar, SWT.PUSH);
        navigate.setText("Navigate");

        Composite comp = new Composite(shell, SWT.NONE);
        data = new GridData(GridData.FILL_BOTH);
        comp.setLayoutData(data);
        comp.setLayout(new FillLayout());

        try {
            browser = new BrowserView(comp, SWT.NONE);
        } catch (SWTError e) {
            MessageBox messageBox = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
            messageBox.setMessage("Closing application. The Browser could not be initialized.");
            messageBox.setText("Fatal error - application terminated");
            messageBox.open();
            System.exit(-1);
        }


//        BrowserViewFunction function = new BrowserViewFunction(browser,"prova"){
//            @Override
//            public Object function(Object[] arguments) {
//                return super.function(arguments);
//            }
//        };
//        WebViewNativeCallback fn = new WebViewNativeCallback() {
//            @Override
//            public String invoke(String s, long l) {
//                //WebViewNative.webview_eval(peer,"alert(\"provata\")",null);
//                return "\"provata\"";
//            }
//        };
//        edge.bind("prova", fn);

//        browser.setUrl("https://www.google.it");

        browser.setText("<!doctype html>\n" +
                "		<html>\n" +
                "			<body>\n" +
                "           <script>\n" +
                "           function add(a,b){\n" +
                "               return (a + b);\n" +
                "           }\n" +
                "           function callFunction2(){\n" +
                "               return callFunction();"+
                "           }\n" +
                "           async function callFunction(){\n" +
                "               var res = await prova();\n" +
                "               return res;"+
                "           }\n" +
                "           window.onload = function() {\n" +
                "					document.getElementById(\"browser\").innerText =`Browser Info:, ${navigator.userAgent}`;\n" +
                "				};\n" +
                "           </script>" +
                "               <div>" +
                "                   <p id=\"browser\"></p>" +
                "               </div>" +
                "               <div>" +
                "                   <p>Test</p>" +
                "                   <button onClick=\"addCall(4,5)\">Test Script</button>" +
                "               </div>" +
                "           </body>\n" +
                "            \n" +
                "		</html>");


        BrowserViewFunction function = new BrowserViewFunction(browser,"addCall"){
            @Override
            public Object function (Object[] arguments){
                System.out.println("Arg1 "+(Double)arguments[0]);
                System.out.println("Arg2 "+(Double)arguments[1]);
                System.out.println((Double)arguments[0]+(Double)arguments[1]);
                return (Double)arguments[0]+(Double)arguments[1];
            }
        };

        execute.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                browser.execute("alert(add(2,3));");
            }
        });


        evaluate.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                System.out.println(browser.evaluate("JSON.stringify(add(2,3));"));
            }
        });

        navigate.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                browser.setUrl("https://www.google.it");
            }
        });


        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) display.sleep();
        }
        display.dispose();
    }
}
