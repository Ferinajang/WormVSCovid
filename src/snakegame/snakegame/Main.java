package snakegame;

import java.awt.Color;

import javax.swing.JFrame;

public class Main {
	

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Gameplay gameplay = new Gameplay();
		
		frame.setTitle("Worm vs Covid");
		frame.setBounds(10,10,905,730);
		frame.setBackground(Color.DARK_GRAY);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(gameplay);
		
		frame.setVisible(true);
		
		//
		String filePath = "bg1.wav";
		music musicObject = new music();
		musicObject.playMusic(filePath);
		
		 
		
		

	}

}
