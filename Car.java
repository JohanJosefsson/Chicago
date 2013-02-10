package Chicago;

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Car {
	int x, dx, y, dy;

	Driveable d;
	Image image;
	
	//ImageIcon ii = new ImageIcon("C:/Users/Johan/java/Chicago/res/car.png");
	
	public Car(Driveable d) {
		this.d = d;
		x = 65;
		y = 182;
		image = ImageBank.getImage("car.png");//ii.getImage();
	}

	static private int printcnt = 10;
	public void move() {

		int newx = x + dx;
		int newy = y + dy;

		if(d.isStreet(new Rectangle(newx, newy, 32, 32))) {
			x = newx;
			y = newy;
		//	System.out.println("dx=" + dx + " dy=" + dy + " x=" + x + " y=" + y);
			}
			
	}

	
	public Rectangle getRectangle() {
		return new Rectangle(x, y, 32, 32);
	}

	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public int getdx() {
		return dx;
	}
	
	public Image getImage() {
		return image;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT)
		{		dx = -1;
				}
		
		if (key == KeyEvent.VK_RIGHT) {
			dx = 1;
		}
		
		if (key == KeyEvent.VK_UP) {
			dy = -1;
		}
		
		if (key == KeyEvent.VK_DOWN) {
			dy = 1;	
		}

}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT)
			dx = 0;

		if (key == KeyEvent.VK_RIGHT)
			dx = 0;


		if (key == KeyEvent.VK_UP)
			dy = 0;

		if (key == KeyEvent.VK_DOWN)
			dy = 0;

	}
}
