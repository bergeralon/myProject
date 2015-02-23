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
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import Algorithm.Action;
import Domain.MazeDomain;
import Model.MyModel;
import Model.Solution;
import View.gui.DomainSelectionDialog;
import View.gui.FigureDrawer;
import View.gui.MazeGUI;
import View.gui.NumberInputDialog;
import View.gui.PropertiesShell;
import clientServer.MyProperties;

/**
 * GUI settings of the board according the user's selections.
 * @author Alon
 *
 */

public class MyConsoleView extends Observable implements View
{

	private String action;
	private MazeGUI gui;
	private int size;
	private int walls;
	boolean more = true;

	@Override
	public void start()
	{
		MyProperties m = new MyProperties();

		Display display = new Display();


//		DomainSelectionDialog dialog = new DomainSelectionDialog(new Shell());
//		dialog.setText("Select domain");
//		dialog.open();


		while (true)
		{
			final Shell shell = new Shell(display);
			shell.setSize(530, 610);
			shell.setText(m.program_name);
			addMenu(shell);
			
//			size = new NumberInputDialog(shell)
//					.open("Please enter the size of the maze:");
//			walls = new NumberInputDialog(shell)
//					.open("Please enter number of walls:");
			
			size = 15;
			walls = 80;
		
			action = "SelectDomain Maze:" + size + "," + size + "," + walls;
			
			this.setChanged();
			this.notifyObservers();

			MazeDomain domain = (MazeDomain) MyModel.getDomain();
			gui = new MazeGUI(shell, domain, 505, 535, new FigureDrawer()
			{

				@Override
				public void drawFigure(Composite comp)
				{
					Label label = new Label(comp, SWT.BORDER);
					Image image = new Image(comp.getDisplay(), "lib/figure32.png");
					label.setImage(image);
					label.setBounds(0, 0, comp.getSize().x, comp.getSize().y);
				}

				@Override
				public void drawGoal(Composite comp)
				{
					Label label = new Label(comp, SWT.BORDER);
					Image image = new Image(comp.getDisplay(), "lib/pizza32.png");
					label.setImage(image);
					label.setBounds(0, 0, comp.getSize().x, comp.getSize().y);
				}
			}, this);
			gui.setBounds(5, 5, 505, 505);

			shell.open();

			while (!shell.isDisposed())
			{
				if (!display.readAndDispatch())
					display.sleep();
			}
			MessageBox messageBox = new MessageBox(new Shell(display), SWT.ICON_QUESTION
					| SWT.YES | SWT.NO);
			messageBox.setMessage("New Game?");
			messageBox.setText("Exiting Application");
			int response = messageBox.open();
			if (response == SWT.NO)
				more = false;
			if (!more)
				break;
		}

		display.dispose();

	}

	private void addMenu(final Shell shell)
	{
		Menu bar = new Menu(shell, SWT.BAR);
		shell.setMenuBar(bar);

		MenuItem fileItem = new MenuItem(bar, SWT.CASCADE);
		fileItem.setText("&File");

		Menu submenu = new Menu(shell, SWT.DROP_DOWN);
		fileItem.setMenu(submenu);

		MenuItem propertiesItem = new MenuItem(submenu, SWT.PUSH);
		propertiesItem.addListener(SWT.Selection, new Listener()
		{
			@Override
			public void handleEvent(Event e)
			{
				PropertiesShell.open(shell.getDisplay());
				shell.setText(new MyProperties().program_name);
			}
		});
		propertiesItem.setText("Show properties");

		MenuItem item = new MenuItem(submenu, SWT.PUSH);
		item.addListener(SWT.Selection, new Listener()
		{
			@Override
			public void handleEvent(Event e)
			{
				shell.close();
			}
		});
		item.setText("Exit");
	}

	@Override
	public void displayCurrentState()
	{
		System.out.println("This is the current view:");

	}

	@Override
	public void displaySolution(final Solution solution)
	{
		Display.getDefault().asyncExec(new Runnable()
		{
			@Override
			public void run()
			{
				if (solution == null || solution.getActions().size() < 1)
				{
					MessageBox messageBox = new MessageBox(gui.getShell(),
							SWT.ICON_ERROR);
					messageBox.setMessage("Pizza Is Unreachable...");
					messageBox.setText("Sorry...");
					messageBox.open();
				}
				for (Action a : solution.getActions())
					System.out.println(a);
				gui.displaySolution(solution);
			}
		});
	}

	@Override
	public String getUserAction()
	{
		return action;
	}

	@Override
	public void showMessage(String msg)
	{
		System.out.println(msg);
	}

	public void solveWithAStar()
	{
		action = "SelectAlgorithm AStar";
		this.setChanged();
		this.notifyObservers();
		action = "SolveDomain";
		this.setChanged();
		this.notifyObservers();
	}

	public void solveWithBFS()
	{
		action = "SelectAlgorithm BFS";
		this.setChanged();
		this.notifyObservers();
		action = "SolveDomain";
		this.setChanged();
		this.notifyObservers();

	}

}
