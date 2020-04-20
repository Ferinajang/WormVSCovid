package snakegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import snakegame.music;


public class Gameplay extends JPanel implements KeyListener,ActionListener {
	
	private int[] snakeXlength = new int[750];
	private int[] snakeYlength = new int[750];
	
	private int moves = 0;

	
	private boolean left = false ; 
	private boolean right = false ;
	private boolean up = false ; 
	private boolean down = false ; 
	
	
	private ImageIcon titleImage;
	private ImageIcon bgImage;
	private ImageIcon snakeImage;
	private ImageIcon rightMouth;
	private ImageIcon leftMouth;
	private ImageIcon upMouth;
	private ImageIcon over;
	
	private ImageIcon downMouth;
	private ImageIcon enemyimage ; 
	
	private Random rand = new Random();
	
	private int xpos = rand.nextInt(34);
	private int ypos = rand.nextInt(23);

	
	private int lengthofsnake = 1;
	
	
	private Timer timer;
	private int delay =100;
	
	//enemy position 
	private int [] enemyX = { 25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
	private int [] enemyY = {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};

	private int score = 0;
	
	
	public Gameplay() {
		
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay,this);
		timer.start();
		
		
		
	}
	
	public void paint(Graphics g) {
		
		
		if(moves == 0) {
			snakeXlength[2] = 0;
			snakeXlength[1] = 0;
			snakeXlength[0] = 0;
			
			snakeYlength[2] = 200;
			snakeYlength[1] = 200;
			snakeYlength[0] = 200;
		
		}

		// title image
		titleImage = new ImageIcon("titlesnake.gif");
		titleImage.paintIcon(this, g, 0 , 0);
		
		
		// bg game
	
		
		bgImage = new ImageIcon("bg.png");
		bgImage.paintIcon(this, g, 0 , 100);
		
		
		
		
		// draw scores
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.BOLD ,20 ));
		g.drawString("Score: " + score , 780, 160); 
		
		// draw length 
		g.setColor((Color.white));
		g.setFont(new Font("arial", Font.BOLD , 20));
		g.drawString("Length: " + lengthofsnake,780,180 );
		
		// snake image
		rightMouth = new ImageIcon("rightmouth.png");
		rightMouth.paintIcon(this, g, snakeXlength[0], snakeYlength[0]);
		
		for(int a = 0 ; a < lengthofsnake;a++) {
			if(a == 0 && right) {
				rightMouth = new ImageIcon("rightmouthpng");
				rightMouth.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);		
			}
			
			if(a == 0 && left) {
				leftMouth = new ImageIcon("leftmouth.png");
				leftMouth.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);		
			}
			
			if(a == 0 && down) {
				downMouth = new ImageIcon("downmouth.png");
				downMouth.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);		
			}
			
			if(a == 0 && up) {
				upMouth = new ImageIcon("upmouth.png");
				upMouth.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);		
			}
			
			if(a!=0){
				snakeImage = new ImageIcon("snakeimage.png");
				snakeImage.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);		
			}
			
		}
		
				enemyimage = new ImageIcon("enemy.png");
		
		
		
		if((enemyX[xpos] == snakeXlength[0] && enemyY[ypos] == snakeYlength[0])) {
			score ++ ; 
			lengthofsnake++;
			String eat = "eat.wav";
			music musiceat = new music();
			musiceat.playMusic(eat);
			
			xpos = rand.nextInt(34);
			ypos = rand.nextInt(22);
			
		}
		//enemyimage
		enemyimage.paintIcon(this,g,enemyX[xpos], enemyY[ypos]);
		
		//over
		for(int b = 1; b < lengthofsnake; b++) {
			if(snakeXlength[b] == snakeXlength[0] && snakeYlength[b] == snakeYlength[0]) {
				right = false;
				left = false ;
				up = false ;
				down = false;
				
				over = new ImageIcon("over.png");
				over.paintIcon(this, g, 230 , 230);
				
				
			}
		}
		
		g.dispose();
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		
		if(right) {
			for(int r = lengthofsnake -1 ; r>= 0;r--) {
				snakeYlength[r+1] = snakeYlength[r];
			}
			
			for(int r = lengthofsnake ;r>= 0 ; r--) {
				if(r==0) {
					snakeXlength[r] = snakeXlength[r] +25;
				}
				else {
					snakeXlength[r] = snakeXlength[r-1];
				}
				if(snakeXlength[r] > 850) {
					snakeXlength[r]= 25;	
					
				}
				
			}
			repaint();
		} 
		if(left) {
			for(int r = lengthofsnake -1 ; r>= 0;r--) {
				snakeYlength[r+1] = snakeYlength[r];
			}
			
			for(int r = lengthofsnake ;r>= 0 ; r--) {
				if(r==0) {
					snakeXlength[r] = snakeXlength[r]-25;
				}
				else {
					snakeXlength[r] = snakeXlength[r-1];
				}
				if(snakeXlength[r] < 25) {
					snakeXlength[r]= 850;	
					
				}
				
			}
			repaint();
			
		}
		if(up) {
			for(int r = lengthofsnake -1 ; r>= 0;r--) {
				snakeXlength[r+1] = snakeXlength[r];
			}
			
			for(int r = lengthofsnake ;r>= 0 ; r--) {
				if(r==0) {
					snakeYlength[r] = snakeYlength[r]- 25;
				}
				else {
					snakeYlength[r] = snakeYlength[r-1];
				}
				if(snakeYlength[r] < 75) {
					snakeYlength[r] = 625;	
					
				}
				
			}
			repaint();
			
		}
		if(down) {
			for(int r = lengthofsnake -1 ; r>= 0;r--) {
				snakeXlength[r+1] = snakeXlength[r];
			}
			
			for(int r = lengthofsnake ;r>= 0 ; r--) {
				if(r==0) {
					snakeYlength[r] = snakeYlength[r]+ 25;
				}
				else {
					snakeYlength[r] = snakeYlength[r-1];
				}
				if(snakeYlength[r] > 625) {
					snakeYlength[r]= 75;	
					
				}
				
			}
			repaint();
			
		}
	}

	
	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode()== KeyEvent.VK_SPACE) {
			moves = 0;
			score = 0 ;
			lengthofsnake = 3;
			repaint();
			
			
		}
		if(e.getKeyCode()== KeyEvent.VK_RIGHT) {
			
			moves ++ ;
			right = true;
			if(!left) {
				right = true;	
			}
			else {
				right = false;
				left = true;
				
			}
			up = false;
			down = false;
			
		}
		if(e.getKeyCode()== KeyEvent.VK_LEFT) {
			
			moves ++ ;
			left = true;
			if(!right) {
				left = true;	
			}
			else {
				left = false;
				right = true;
				
			}
			up = false;
			down = false;
			
		}
		if(e.getKeyCode()== KeyEvent.VK_UP) {
			
			moves ++ ;
			up = true;
			if(!down) {
				up = true;	
			}
			else {
				up = false;
				down = true;
				
			}
			right = false;
			left = false;
			
		}
		if(e.getKeyCode()== KeyEvent.VK_DOWN) {
			
			moves ++ ;
		down= true;
			if(!up) {
				down = true;	
			}
			else {
				down = false;
				up = true;
				
			}
			right = false;
			left = false;
			
		}
		
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {

		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {

		
	}

	

}
