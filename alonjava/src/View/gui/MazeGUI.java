package View.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;

import Model.Solution;
import View.MyConsoleView;
import Algorithm.Action;
import Domain.MazeDomain;

/**
 * all GUI settings including figure movement are here.
 * @author Alon
 *
 */

public class MazeGUI extends Canvas {

	private static int size;
	private int width;
	private int height;
	private FigureDrawer fd;
	private MazeDomain domain;
	private Image image;
	private MyConsoleView view;
	private Button[][] buttons;
	private Composite figure;
	private Composite door;
	private int figureX;
	private int figureY;

	public MazeGUI(Composite parent, final MazeDomain domain, int width, int height1, FigureDrawer fd, MyConsoleView myConsoleView) {
		super(parent, SWT.NONE);
		this.domain = domain;
		this.width = width;
		this.view = myConsoleView;
		this.height = height1 - 30;
		this.fd = fd;
		size = domain.getMaze().length;
		figureX = domain.getFigureState().getI();
		figureY = domain.getFigureState().getJ();

		Composite mainComposite = new Composite(this, SWT.BORDER);
		mainComposite.setSize(width, height);
		image = new Image(parent.getDisplay(), "lib/wall.png");

		buttons = new Button[size][];
		for (int i = 0; i < size; i++) {
			buttons[i] = new Button[size];
			for (int j = 0; j < size; j++) {
				final Button button = new Button(mainComposite, SWT.TOGGLE);
				if (i == figureX && j == figureY)
				{
					figure = new Composite(mainComposite, SWT.NONE);
					figure.setBounds(j * width / size, i * width / size, width / size, width / size);
					fd.drawFigure(figure);
					button.setVisible(false);
				}
				if (i == size - 1 && j == size - 1)
				{
					door = new Composite(mainComposite, SWT.NONE);
					door.setBounds(j * width / size, i * width / size, width / size, width / size);
					fd.drawGoal(door);
					continue;
				}
				
				buttons[i][j] = button;
				addKeysListener(button);
				button.setBounds(j * width / size, i * width / size, width / size, width / size);
				button.setImage(getImage(i,j));
				if (domain.getMaze()[i][j].getIsWall())
					button.setSelection(true);
				final int xi= i;
				final int yj= j;
				button.addSelectionListener(new SelectionListener() {
					int x = xi;
					int y = yj;
					@Override
					public void widgetSelected(SelectionEvent arg0) {
						if (button.getSelection()) {
							button.setImage(image);
							domain.getMaze()[x][y].setIsWall(true);
						} else {
							button.setImage(null);
							domain.getMaze()[x][y].setIsWall(false);
						}
					}
					
					@Override
					public void widgetDefaultSelected(SelectionEvent arg0) {
					}
				});
			}
		}
		
		Button button = new Button(parent, SWT.PUSH);
		button.setText("Solve with AStar");
		button.setBounds(width/2, height + 10, 90, 30);
		button.addSelectionListener(new SelectionListener()
		{
			
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				view.solveWithAStar();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0)
			{
			}
		});
		
		button = new Button(parent, SWT.PUSH);
		button.setText("Solve with BFS");
		button.setBounds(width/2-100, height + 10 , 90, 30);
		button.addSelectionListener(new SelectionListener()
		{
			
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				view.solveWithBFS();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0)
			{
			}
		});
		
	}

	/**
	 * @param button
	 */
	public void addKeysListener(final Button button)
	{
		button.addKeyListener(new KeyListener()
		{
			
			@Override
			public void keyReleased(KeyEvent arg0)
			{
				if (arg0.keyCode == SWT.ARROW_RIGHT)
				{
					if (figureY >= buttons.length - 1)
						return;
					if ((figureX == buttons.length - 1 && figureY == buttons.length - 2))
					{
						figure.setBounds((figureY + 1) * width / size, figureX * width / size, width / size, width / size);
						buttons[figureX][figureY].setVisible(true);
						showSuccess();
						return;
					}
					if (buttons[figureX][figureY + 1].getSelection())
					{
						return;
					}
					buttons[figureX][figureY].setVisible(true);
					domain.getMaze()[figureX][figureY].setFigure(false);
					figureY++;
				}
				if (arg0.keyCode == SWT.ARROW_LEFT)
				{
					if (figureY < 1)
						return;
					if (buttons[figureX][figureY - 1].getSelection())
					{
						return;
					}
					buttons[figureX][figureY].setVisible(true);
					domain.getMaze()[figureX][figureY].setFigure(false);
					figureY--;
				}
				if (arg0.keyCode == SWT.ARROW_UP)
				{
					if (figureX < 1)
						return;
					if (buttons[figureX - 1][figureY].getSelection())
					{
						return;
					}
					buttons[figureX][figureY].setVisible(true);
					domain.getMaze()[figureX][figureY].setFigure(false);
					figureX--;
				}
				if (arg0.keyCode == SWT.ARROW_DOWN)
				{
					if (figureX >= buttons.length - 1)
					{
						return;
					}
					if ((figureY == buttons.length - 1 && figureX == buttons.length - 2))
					{
						figure.setBounds(figureY * width / size, (figureX + 1) * width / size, width / size, width / size);
						buttons[figureX][figureY].setVisible(true);
						showSuccess();
						return;
					}
					if (buttons[figureX + 1][figureY].getSelection())
					{
						return;
					}
					buttons[figureX][figureY].setVisible(true);
					domain.getMaze()[figureX][figureY].setFigure(false);
					figureX++;
				}
				buttons[figureX][figureY].setVisible(false);
				figure.setBounds(figureY * width / size, figureX * width / size, width / size, width / size);
				domain.getMaze()[figureX][figureY].setFigure(true);
			}
			
			@Override
			public void keyPressed(KeyEvent arg0)
			{
			}
		});
	}

	protected void showSuccess()
	{
		MessageBox messageBox = new MessageBox(figure.getShell(),
				SWT.ICON_INFORMATION);
		messageBox.setMessage("Pizza Found :)");
		messageBox.setText("Great Success!");
		messageBox.open();
		figure.getShell().close();
	}

	private Image getImage(int i, int j) {
		if (domain.getMaze()[i][j].getIsWall())
		{
			return image;
		}
		else
		{
			return null;
		}
	}

	public void displaySolution(Solution solution)
	{
		int x = domain.getFigureState().getJ();
		int y = domain.getFigureState().getI();
		for (Action a : solution.getActions())
		{
			if (x == buttons[0].length - 1 && y == buttons.length - 1)
				break;
			if (a == null)
				continue;
			if (a.toString().equals("right"))
				x++;
			if (a.toString().equals("left"))
				x--;
			if (a.toString().equals("down"))
				y++;
			if (a.toString().equals("up"))
				y--;
			try
			{
				buttons[y][x].setImage(new Image(buttons[y][x].getDisplay(), "lib/dot20.png"));
				
			} catch (Exception e)
			{
			}
		}
	}
}
