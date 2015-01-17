package View.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class DomainSelectionDialog extends Dialog
{
    private Shell shell;

    public DomainSelectionDialog(Shell parent)
    {
        // Pass the default styles here
        this(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
        shell = parent;
    }

    public DomainSelectionDialog(Shell parent, int style)
    {
        // Let users override the default styles
        super(parent, style);
        shell = parent;
    }

    public void open()
    {
        shell.setText(getText());
        createContents(shell);
        shell.pack();
        shell.open();
        Display display = getParent().getDisplay();
        while (!shell.isDisposed())
        {
            if (!display.readAndDispatch())
            {
                display.sleep();
            }
        }
    }

    /**
     * Creates the dialog's contents
     * 
     * @param shell
     *            the dialog window
     */
    private void createContents(final Shell shell)
    {
        shell.setLayout(new GridLayout(3, true));

        // Show the message
        Label label = new Label(shell, SWT.NONE);
        label.setText("What domain do you want?");
        GridData data = new GridData();
        data.horizontalSpan = 3;
        label.setLayoutData(data);

        Button preview = new Button(shell, SWT.PUSH);
        preview.setText("Maze");
        data = new GridData(SWT.FILL, SWT.END, true, true);
        preview.setLayoutData(data);
        preview.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent event)
            {
            	 shell.close();
            }
        });

        Button delete = new Button(shell, SWT.PUSH);
        delete.setText("Parking lot");
        data = new GridData(SWT.FILL, SWT.END, true, true);
        delete.setLayoutData(data);
        delete.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent event)
            {
            	shell.close();
            }
        });

        shell.setDefaultButton(preview);
    }

}