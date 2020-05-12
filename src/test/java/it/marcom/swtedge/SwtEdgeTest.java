package it.marcom.swtedge;


import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.browser.*;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import java.io.File;
import java.io.FilenameFilter;
import java.net.MalformedURLException;


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
        final ToolItem back = new ToolItem(navBar, SWT.PUSH);
        back.setText("Back");
        back.setEnabled(false);
        final ToolItem forward = new ToolItem(navBar, SWT.PUSH);
        forward.setText("Forward");
        forward.setEnabled(false);

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

        browser.setUrl("https://www.google.it");
        back.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                browser.back();
            }
        });


        forward.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                browser.forward();
            }
        });

        final LocationListener locationListener = new LocationListener() {
            public void changed(LocationEvent event) {
                Browser browser = (Browser)event.widget;
                back.setEnabled(browser.isBackEnabled());
                forward.setEnabled(browser.isForwardEnabled());
            }
            public void changing(LocationEvent event) {
            }
        };
        /* Build a table of contents. Open each HTML file
         * found in the given folder to retrieve their title.
         */
        final TitleListener tocTitleListener = new TitleListener() {
            public void changed(TitleEvent event) {
                titles[index] = event.title;
            }
        };

        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) display.sleep();
        }
        display.dispose();
    }
}
