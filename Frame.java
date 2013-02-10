package Chicago;

import javax.swing.*;

public class Frame {

	public Frame(){
		JFrame frame = new JFrame();
		frame.add(new Board());	
		frame.setTitle("The Roaring Twenties");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,470);//(1036,461);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
	public static void main(String[] args){
	System.out.println("Hello World!"); // Display the string.
		new Frame();
	}
}
