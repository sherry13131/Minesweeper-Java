package com.game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Board extends JPanel {

    private Rectangle selectedCell = null;
    private MainGame newgame;
    private int theWidth, theHeight;
    
    private int gameover = 0;
    
    private Image bomb;
    private Image bomb0;
    private Image bomb1;
    private Image bomb2;
    private Image bomb3;
    private Image bomb4;
    private Image bomb5;
    private Image bomb6;
    private Image bomb7;
    private Image bomb8;
    private Image lost;
    private Image win;
    
	public Board() {

        initBoard();
        newgame = new MainGame();
        
        // change box element
        addMouseListener(new MouseAdapter() {
            
        	@Override
		    public void mouseReleased(MouseEvent e) {
        		if (newgame.gameover == 0) {
			        int w = getWidth();
			        int h = getHeight();
			        
			        System.out.println(w);
			        System.out.println(h);
			        theWidth = w;
			        theHeight = h;
	        		System.out.println(e.getPoint());
			        if ((e.getPoint().getX() > 100 && e.getPoint().getX() < 1100) && (e.getPoint().getY() > 100 && e.getPoint().getY() < 900)) {
				        selectedCell = null;
				        for (int col = 0; col < 24 && selectedCell == null; col++) {
				            for (int row = 0; row < 19; row++) {
				                int x = (w / 23) * col;
				                int y = (h / 19) * row;
				                Rectangle cell = new Rectangle(x, y, w / 23, h / 19);
				                if (cell.contains(e.getPoint())) {
				                    System.out.println("In");
				                    System.out.println(x + ", " + y);
				                    
				                    int success = newgame.clicked(x, y);
				                    
				                    // if a bomb is clicked
				                    if (success == -1) {
				                    	//endGame;
//				                    	newgame.endGame();
				                    } else {
				                    	// check if all boxes opened
				                    	if (newgame.remaining == 0) {
				                    		newgame.gameover = -1;
//				                    		newgame.endGame();
				                    	}
				                    }
				                    
				                    repaint();
				                    break;
				                }
				            }
				        }
			        } else {
			        	System.out.println("nothing there");
			        }
        		}
		    }
        });
	}

	private void initBoard() {
        
		loadImage();
		
		JFrame frame = new JFrame();
		frame.setLocationRelativeTo(null);
		frame.setBackground(Color.LIGHT_GRAY);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setVisible(true);     
    }


    @Override
	public void paintComponent(Graphics g) {

        super.paintComponent(g);		
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(100, 100, 1000, 800);
//		g.clearRect(100, 100, 50, 50);
		g.setColor(Color.BLACK);
		for (int i = 0; i < 21 ; i++) {
			g.drawLine(100+50*i, 100, 100+50*i, 900);
		}
		for (int i = 0; i < 17; i++) {
			g.drawLine(100, 100+50*i, 1100, 100+50*i);
		}
		update(g);

        if (newgame.gameover == 1) {
        	g.drawImage(lost, 250, 300, 700, 400, null);
        	// add restart button
        	addRestartButton();
        }
        if (newgame.gameover == -1) {
        	g.drawImage(win, 250, 300, 700, 400, null);
        }
	}
    
    @Override
    public void update(Graphics g) {
        String name = new String("bomb");
        g.setColor(Color.BLUE);
        for (int i = 0; i < 20; i++) {
        	for (int j = 0; j < 16; j++) {
        		// if clicked, show block data
        		if (newgame.clickdata[j][i] > 0) {
        			System.out.println((i*50+100) + " " + (j*50+100));
//        			cell = new Rectangle(i*50+100, j*50+100, theWidth / 23, theHeight / 19);
        			
        			// if the block is bomb
        			if(newgame.bombs[j][i] == -1) {
        				g.drawImage(bomb, i*50+100, j*50+100, 50, 50, null);
        				newgame.gameover = 1;
        			} else if(newgame.bombs[j][i] == 0) {
        				g.drawImage(bomb0, i*50+100, j*50+100, 50, 50, null);
        			} else if(newgame.bombs[j][i] == 1) {
        				g.drawImage(bomb1, i*50+100, j*50+100, 50, 50, null);
        			} else if(newgame.bombs[j][i] == 2) {
        				g.drawImage(bomb2, i*50+100, j*50+100, 50, 50, null);
        			} else if(newgame.bombs[j][i] == 3) {
        				g.drawImage(bomb3, i*50+100, j*50+100, 50, 50, null);
        			} else if(newgame.bombs[j][i] == 4) {
        				g.drawImage(bomb4, i*50+100, j*50+100, 50, 50, null);
        			} else if(newgame.bombs[j][i] == 5) {
        				g.drawImage(bomb5, i*50+100, j*50+100, 50, 50, null);
        			} else if(newgame.bombs[j][i] == 6) {
        				g.drawImage(bomb6, i*50+100, j*50+100, 50, 50, null);
        			} else if(newgame.bombs[j][i] == 7) {
        				g.drawImage(bomb7, i*50+100, j*50+100, 50, 50, null);
        			} else if(newgame.bombs[j][i] == 8) {
        				g.drawImage(bomb8, i*50+100, j*50+100, 50, 50, null);
        			}
//        	        ((Graphics2D) g).fill(cell);
        		}
        	}
        }
    }
    
    private void loadImage() {
        
    	ImageIcon ii = new ImageIcon("res/bomb.png");
    	bomb = ii.getImage();
    	ii = new ImageIcon("res/bomb0.png");
        bomb0 = ii.getImage();
    	ii = new ImageIcon("res/bomb1.png");
        bomb1 = ii.getImage();
    	ii = new ImageIcon("res/bomb2.png");
        bomb2 = ii.getImage();
    	ii = new ImageIcon("res/bomb3.png");
        bomb3 = ii.getImage();
    	ii = new ImageIcon("res/bomb4.png");
        bomb4 = ii.getImage();
    	ii = new ImageIcon("res/bomb5.png");
        bomb5 = ii.getImage();
    	ii = new ImageIcon("res/bomb6.png");
        bomb6 = ii.getImage();
    	ii = new ImageIcon("res/bomb7.png");
        bomb7 = ii.getImage();
    	ii = new ImageIcon("res/bomb8.png");
        bomb8 = ii.getImage();
        ii = new ImageIcon("res/lost.png");
        lost = ii.getImage();
        ii = new ImageIcon("res/win.png");
        win = ii.getImage();
    }
    
    public void restart() {
    	newgame.restart();
    	repaint();
    }
    
    private void addRestartButton() {
        JButton restartbutton = new JButton("Restart");

        setLayout(null);
        restartbutton.setBounds(530, 650, 150, 40);
        add(restartbutton);

        restartbutton.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                System.out.println("clicked restart");
                restartbutton.setVisible(false);
                restart();
            }
        });  

        restartbutton.setVisible(true);
    }
}
