package View.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import clientServer.MyProperties;

public class PropertiesShell {

	public static void open(Display display) {

		final MyProperties m = new MyProperties();
		
		final Shell s = new Shell(display);
		s.setText("Properties");
		s.setSize(250, 200);

		s.setLayout(new FormLayout());

		final Label l1 = new Label(s, SWT.RIGHT);
		l1.setText("Program name");
		FormData fd = new FormData();
		fd.top = new FormAttachment(10, 10);
		fd.left = new FormAttachment(0, 10);
		fd.bottom = new FormAttachment(30, 0);
		fd.right = new FormAttachment(40, 0);
		l1.setLayoutData(fd);

		final Label l2 = new Label(s, SWT.RIGHT);
		l2.setText("Server ip");
		fd = new FormData();
		fd.top = new FormAttachment(l1, 5);
		fd.left = new FormAttachment(0, 10);
		fd.right = new FormAttachment(40, 0);
		l2.setLayoutData(fd);
		
		final Label l3 = new Label(s, SWT.RIGHT);
		l3.setText("Server port");
		fd = new FormData();
		fd.top = new FormAttachment(l2, 5);
		fd.left = new FormAttachment(0, 10);
		fd.right = new FormAttachment(40, 0);
		l3.setLayoutData(fd);

		final Text t1 = new Text(s, SWT.BORDER | SWT.SINGLE);
		fd = new FormData();
		fd.top = new FormAttachment(l1, 0, SWT.TOP);
		fd.left = new FormAttachment(l1, 10);
		t1.setLayoutData(fd);
		t1.setText(m.program_name);

		final Text t2 = new Text(s, SWT.BORDER | SWT.SINGLE);
		fd = new FormData();
		fd.top = new FormAttachment(l2, 0, SWT.TOP);
		fd.left = new FormAttachment(l2, 10);
		t2.setLayoutData(fd);
		t2.setText(m.ip);
		
		final Text t3 = new Text(s, SWT.BORDER | SWT.SINGLE);
		fd = new FormData();
		fd.top = new FormAttachment(l3, 0, SWT.TOP);
		fd.left = new FormAttachment(l3, 10);
		t3.setLayoutData(fd);
		t3.setText("" + m.port);
		
		Button save = new Button(s, SWT.PUSH);
		save.setSize(100, 50);
		save.setText("Save");
		fd = new FormData();
		fd.top = new FormAttachment(l3, 0, SWT.TOP);
		fd.left = new FormAttachment(l3, 80);
		save.setLayoutData(fd);
		save.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				int port = 0;
				try{
					port = Integer.valueOf(t3.getText());
				}
				catch (NumberFormatException e)
				{
					MessageBox messageBox = new MessageBox(s, SWT.ICON_ERROR);
				    messageBox.setMessage(t3.getText() + " - is not a valid port number!");
				    messageBox.setText("Error");
				    messageBox.open();
					return;
				}
				
				m.program_name = t1.getText();
				m.ip = t2.getText();
				m.port = port;
				
				m.save();
				MessageBox messageBox = new MessageBox(s, SWT.ICON_INFORMATION);
			    messageBox.setMessage("Data saved successfuly");
			    messageBox.setText("OK");
			    messageBox.open();
			    s.close();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
		

		s.open();
		while (!s.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}

}
