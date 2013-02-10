package Chicago;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.*;
import java.io.IOException;





public class Board extends JPanel implements ActionListener, Driveable {
	private Car car;
	private PoliceCar pc;
	private /*public*/ BufferedImage img;
	private /*public*/ BufferedImage bustedImg;
	private Timer time;
	private boolean isBusted = false;

		private int bkgHeight;
		private int bkgWidth;

	public Board() {
		car = new Car(this);

		pc = new PoliceCar(this, 700, 219, -1, 0);
		addKeyListener(new AL());
		setFocusable(true);

		img = ImageBank.getImage("background.png");
		bustedImg = ImageBank.getImage("busted.png");


		time = new Timer(5, this);
		time.start();
		bkgHeight = img.getHeight();
		bkgWidth = img.getWidth();
		System.out.println("h=" + bkgHeight + " w=" + bkgWidth);
	}

	private void checkForCollision() {
		if(car.getRectangle().intersects(pc.getRectangle())) {
			isBusted = true;
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if(isBusted) { return; }
	
		car.move();
		pc.move();
		
		checkForCollision();
		
		repaint();
		
	}
	
	
	
	static private final int STREET_COLOR = -7864299;
	
	static private int printcnt = 10;
	private Rectangle r[] = new Rectangle[4];
	private int index = 0;
	public boolean isStreet(Rectangle r) {
//		System.out.println(" " + bkgHeight + " " + bkgWidth + " " + r.y + " " + r.x + " " + r.height + " " +r.width);
		this.r[index++] = r;
		index %=4;
		return(
			r.y + r.height <= bkgHeight &&
			r.x + r.width <= bkgWidth &&
			r.x >=0 &&
			r.y >= 00 &&
			img.getRGB(r.x, r.y) == STREET_COLOR &&
			img.getRGB(r.x + r.width, r.y) == STREET_COLOR &&
			img.getRGB(r.x, r.y + r.height) == STREET_COLOR &&
			img.getRGB(r.x + r.width, r.y + r.height) == STREET_COLOR
		);
	}
	
	private int getCarPaintX() {
		int x = car.getX();
		if(x < 250) {
			return x;
		}
		else {
			return 250;
		}
	}
		
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;

		if(!isBusted) {
			g2d.drawImage(img, +1 * getCarPaintX() - car.getX(), 0, null);
			g2d.drawImage(car.getImage(), getCarPaintX(), car.getY() , null);
			g2d.drawImage(pc.getImage(), pc.getX() + getCarPaintX()- car.getX(), pc.getY() , null);
			} else {
			g2d.drawImage(bustedImg, 0, 0 ,null);
		}
		
		
//	if(!(r.x == 65 && r.y == 182)){System.out.println("paint() r.x, r.y, r.width, r.height " + r.x +" " + " " + r.y + " " + r.width + " " + r.height);}
		if(r!=null) {
			//for(int i = 0; i < 4; i++) {g2d.drawRect(r[i].x, r[i].y, r[i].width, r[i].height);}
		}
	//	g2d.drawRect (10, 10, 200, 200);

		}

	private class AL extends KeyAdapter {
		public void keyReleased(KeyEvent e) {
			car.keyReleased(e);
//			pc.keyReleased(e);
		}

		public void keyPressed(KeyEvent e) {
			car.keyPressed(e);
//			pc.keyPressed(e);
		}
	}

}
