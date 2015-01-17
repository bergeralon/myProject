package View;

import java.util.Observable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

import Algorithm.Action;
import Algorithm.SearchDomain;
import Domain.MazeDomain;
import Model.MyModel;
import Model.Solution;
import View.gui.ClientGUI;
import View.gui.DomainSelectionDialog;
import View.gui.FigureDrawer;
import View.gui.NumberInputDialog;
import View.gui.PropertiesShell;
import clientServer.MyProperties;

public class MyConsoleView extends Observable implements View {

	private String action;

	@Override
	public void start() {
		 MyProperties m = new MyProperties();

		Display display = new Display();

		final Shell shell = new Shell(display);
		
//		DomainSelectionDialog dialog = new DomainSelectionDialog(new Shell());
//        dialog.setText("Select domain");
//        dialog.open();
        
        int size = 10;//new NumberInputDialog(shell).open("Please enter the size of the maze:");
        int walls = 50;//new NumberInputDialog(shell).open("Please enter number of walls:");
		action = "SelectDomain Maze:" + size+","+size+","+walls;
		this.setChanged();
		this.notifyObservers();
		
		MazeDomain domain = (MazeDomain) MyModel.getDomain();
		
		shell.setSize(330, 410);
		shell.setText(m.program_name);
		addMenu(shell);
		
		
		ClientGUI gui = new ClientGUI(shell,domain, 305, 335, new FigureDrawer() {

			@Override
			public void drawFigure(Composite comp) {
				Label label = new Label (comp, SWT.BORDER);
				Image image = new Image(comp.getDisplay(), "lib/figure.png");
				label.setImage (image);
				label.setBounds(0, 0, comp.getSize().x, comp.getSize().y);
			}

			@Override
			public void drawGoal(Composite comp) {
				Label label = new Label (comp, SWT.BORDER);
				Image image = new Image(comp.getDisplay(), "lib/door.png");
				label.setImage (image);
				label.setBounds(0, 0, comp.getSize().x, comp.getSize().y);
			}
		});
		gui.setBounds(5, 5, 305, 305);
		
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();

	}

	private void addMenu(final Shell shell) {
		Menu bar = new Menu (shell, SWT.BAR);
		shell.setMenuBar (bar);
		
		MenuItem fileItem = new MenuItem (bar, SWT.CASCADE);
		fileItem.setText ("&File");
		
		
		Menu submenu = new Menu (shell, SWT.DROP_DOWN);
		fileItem.setMenu (submenu);
		
		MenuItem propertiesItem = new MenuItem (submenu, SWT.PUSH);
		propertiesItem.addListener (SWT.Selection, new Listener () {
			@Override
			public void handleEvent (Event e) {
				PropertiesShell.open(shell.getDisplay());
				shell.setText(new MyProperties().program_name);
			}
		});
		propertiesItem.setText ("Show properties");
		
		MenuItem item = new MenuItem (submenu, SWT.PUSH);
		item.addListener (SWT.Selection, new Listener () {
			@Override
			public void handleEvent (Event e) {
				shell.close();
			}
		});
		item.setText ("Exit");
	}

	@Override
	public void displayCurrentState() {
		System.out.println("This is the current view:");

	}

	@Override
	public void displaySolution(Solution solution) {
		for (Action a : solution.getActions())
			System.out.println(a);
	}

	@Override
	public String getUserAction() {
		return action;
	}

	@Override
	public void showMessage(String msg) {
		System.out.println(msg);
	}

}
