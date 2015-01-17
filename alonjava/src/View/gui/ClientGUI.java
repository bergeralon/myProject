package View.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import Domain.MazeDomain;

public class ClientGUI extends Canvas {

	private static int size;
	private int width;
	private int height;
	private FigureDrawer fd;
	private MazeDomain domain;
	private Image image;

	public ClientGUI(Composite parent, final MazeDomain domain, int width, int height1, FigureDrawer fd) {
		super(parent, SWT.NONE);
		this.domain = domain;
		this.width = width;
		this.height = height1 - 30;
		this.fd = fd;
		size = domain.getMaze().length;

		Composite mainComposite = new Composite(this, SWT.BORDER);
		mainComposite.setSize(width, height);
		image = new Image(parent.getDisplay(), "lib/wall.png");

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (i == 0 && j == 0)
				{
					Composite comp = new Composite(mainComposite, SWT.NONE);
					comp.setBounds(j * width / size, i * width / size, width / size, width / size);
					fd.drawFigure(comp);
					continue;
				}
				if (i == size - 1 && j == size - 1)
				{
					Composite comp = new Composite(mainComposite, SWT.NONE);
					comp.setBounds(j * width / size, i * width / size, width / size, width / size);
					fd.drawGoal(comp);
					continue;
				}
				
				final Button button = new Button(mainComposite, SWT.TOGGLE);
				button.setBounds(j * width / size, i * width / size, width / size, width / size);
				button.setImage(getImage(i,j));
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
		button = new Button(parent, SWT.PUSH);
		button.setText("Solve with BFS");
		button.setBounds(width/2-100, height + 10 , 90, 30);
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
}
