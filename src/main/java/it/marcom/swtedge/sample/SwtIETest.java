package it.marcom.swtedge.sample;


import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.browser.*;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;


public class SwtIETest {
    static Browser browser;
    static String[] titles;
    static int index;

    public static void main(String[] args) {
        Display display = new Display();
        final Shell shell = new Shell(display);
        shell.setText("SWT IE Browser - Test Viwer");
        shell.setLayout(new GridLayout());
        Composite compTools = new Composite(shell, SWT.NONE);
        GridData data = new GridData(GridData.FILL_HORIZONTAL);
        compTools.setLayoutData(data);
        compTools.setLayout(new GridLayout(2, false));

        ToolBar navBar = new ToolBar(shell, SWT.NONE);
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
            browser = new Browser(comp, SWT.NONE);
        } catch (SWTError e) {
            MessageBox messageBox = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
            messageBox.setMessage("Closing application. The Browser could not be initialized.");
            messageBox.setText("Fatal error - application terminated");
            messageBox.open();
            System.exit(-1);
        }



        browser.setText("<!doctype html>\n" +
                "		<html>\n" +
                "			<body>\n" +
                "           <script>" +
                "           function add(a,b){" +
                "               return a+b;"+
                "           }"+
                "           window.onload = function() {\n" +
                "                   var text = \"Browser Info:\" +navigator.userAgent;" +
                "					document.getElementById(\"browser\").innerText =text\n" +
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



        BrowserFunction function = new BrowserFunction(browser,"addCall"){
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
                System.out.println(browser.evaluate("return(add(2,3));"));
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
