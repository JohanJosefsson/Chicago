package Chicago;

import javax.swing.JApplet;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;




//jar cvf chicago.jar Chicago
public class ChicagoApplet extends JApplet {

	static 	BufferedImage img=null ;

    //Called when this applet is loaded into the browser.
    public void init() {
	
//	/*Resourcerer.*/setCodeBase(getCodeBase());

		
		addImage("background.png");
		addImage("busted.png");
		addImage("policecar.png");
		addImage("car.png");

        //Execute a job on the event-dispatching thread; creating this applet's GUI.
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
				    createGUI();
                }
            });
        } catch (Exception e) { 
            System.err.println("createGUI didn't complete successfully");
			e.printStackTrace();
        }
    }


private void addImage(String name){

		try {
			URL url = new URL(getCodeBase(), "res/"+name);
			img = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageBank.addImage(name, img);

	}
    
	
    private void createGUI() {
        Board board = new Board();		
        board.setOpaque(true); 
        setContentPane(board);
		setVisible(true);
	
    }        

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
/*	
	
	
	static private URL codeBase;
	static private boolean isApplet;
	
	static void setCodeBase(URL codeBase_){
	System.out.println("setCodeBase: "+codeBase_);
		isApplet = true;
		codeBase = codeBase;
	}
	
	static BufferedImage getImg(){
		if(img == null){
		System.out.println("hamtarfr fran filsuust");
			try{
				img = ImageIO.read(new File("C:/Users/Johan/java/Chicago/res/background.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}		
		}


		
		

		
		return img;
	}
	
	static BufferedImage getBustedImg(){
		BufferedImage bustedImg = null;
		try{
			bustedImg = ImageIO.read(new File("C:/Users/Johan/java/Chicago/res/busted.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bustedImg;

*/


	
	
	
	
	
	
	
	
	
	}



class ImageBank {


//		private /*public * / BufferedImage img;
//	private /*public * / BufferedImage bustedImg;
	static private HashMap<String, BufferedImage> images;
	static boolean isApplet;
	
	static BufferedImage getImage(String name){
		BufferedImage img = null;
		if(isApplet){
			img = images.get(name);
		}else{
			try{
				img = ImageIO.read(new File("C:/Users/Johan/java/Chicago/res/"+name));
			} catch (IOException e) {
				e.printStackTrace();
			}		
		}
				System.out.println("...gives image: " + name);
		return img;
	}
	
	static void addImage(String name, BufferedImage img){
		if(images==null){
			images = new HashMap<String, BufferedImage>();
			isApplet = true;
		}
		System.out.println("...adding inage "+name);
		images.put(name, img);
	}
}

