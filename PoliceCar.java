package Chicago;

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.util.Random;

public class PoliceCar {
	int x, dx, y, dy;

	int ddx, ddy;
	
	Driveable d;
	Image image;
	
	
	public PoliceCar(Driveable d, int x, int y, int dx, int dy) {
		this.d = d;
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		image = ImageBank.getImage("policecar.png");
	}

	static private int printcnt = 10;
	
	class Direction  {
		public int x;
		public int y;
		public Direction(int x, int y) {
			this.x = x;
			this.y = y;
			assertMe();
		}
		private void assertMe() {
			assert x != 0 || y != 0;
			assert x == 0 || x == 1 || x== -1;
			assert y == 0 || y == 1 || y == -1;
		}
		public void turnLeft() {
			assertMe();
			if(x == 0 && y == 1) { x = 1; y = 0;}
			else if(x == 1 && y == 0) { x = 0; y = -1;}
			else if(x == 0 && y == -1) { x = -1; y = 0;}
			else if(x == -1 && y == 0) { x = 0; y = 1;}
			assertMe();
		}
		public void turnRight() {
			assertMe();
			if(x == 0 && y == 1) { x = -1; y = 0;}
			else if(x == 1 && y == 0) { x = 0; y = 1;}
			else if(x == 0 && y == -1) { x = 1; y = 0;}
			else if(x == -1 && y == 0) { x = 0; y = -1;}
			assertMe();
		}
	}

	private boolean can_go_at(int x, int y) {
		return d.isStreet(new Rectangle(x, y, 32, 32));	
	}

	
	private int inhibit = 32;

	private boolean can_go_left() {
			Direction left = new Direction(dx,dy);
		left.turnLeft();
		 boolean ret = can_go_at(left.x * 32 + x, left.y * 32 + y);
		return ret;
	}


	private boolean can_go_right() {	
		Direction right = new Direction(dx,dy);
		right.turnRight();
		boolean ret = can_go_at(right.x * 32 + x, right.y * 32 + y);
		return ret;
	}
	
	private boolean turn_left_or_right() {
	
		boolean has_turned;
		Random random = new Random();
		int r = random.nextInt(2);
		boolean keep_right;
		if(r == 0) { keep_right = false; } else { keep_right = true; }
		Direction d = new Direction(dx,dy);
		//has_turned = true;

		if(keep_right && can_go_right()) {
			d.turnRight();
			has_turned = true;
		} else if(can_go_left()) {
			d.turnLeft();
			has_turned = true;
		} else {
			has_turned = false;
		}

		dx = d.x;
		dy = d.y;
		
		return has_turned;
		
	}
	
	public void move() {
	
		if(inhibit > 0) { inhibit--; };

		boolean can_go_straight = can_go_at(x + dx, y + dy);

		Random random = new Random();
		int r = random.nextInt(17);//(4);
		//r = 1;

		boolean has_turned = false;
		if(r == 0 && inhibit == 0) {
			has_turned = turn_left_or_right();
		}
		if(!has_turned && !can_go_straight) {
		System.out.println("cannot cont");
			has_turned = turn_left_or_right();
		}
		if(has_turned) { inhibit = 32; }

		if( !has_turned && !can_go_straight) {
			System.out.println("last...");
			Direction d = new Direction(dx,dy);
			d.turnLeft();
			d.turnLeft();
			dx = d.x;
			dy = d.y;

		}
			
			
		/* Perform the move */
		//System.out.println("dx=" + dx + " dy=" + dy + " x=" + x + " y=" + y);
		x += dx;
		y += dy;
	//System.out.println("ddx");
	//	x += ddx;
	//	y += ddy;
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
		
		if (key == KeyEvent.VK_SPACE) {
			move();	
		}

}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT)
			ddx = 0;

		if (key == KeyEvent.VK_RIGHT)
			ddx = 0;


		if (key == KeyEvent.VK_UP)
			ddy = 0;

		if (key == KeyEvent.VK_DOWN)
			ddy = 0;

	}
}
