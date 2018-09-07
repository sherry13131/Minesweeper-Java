package com.game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Board extends JPanel {

    private Rectangle selectedCell = null;

	public Board() {

        initBoard();
        
	
        addMouseListener(new MouseAdapter() {
            
        	@Override
		    public void mouseClicked(MouseEvent e) {
		        int w = getWidth();
		        int h = getHeight();

		        System.out.println(w);
		        System.out.println(h);
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
			                    selectedCell = cell;
			                    repaint();
			                    break;
			                }
			            }
			        }
		        } else {
		        	System.out.println("nothing there");
		        }
		    }
        });
	}

	private void initBoard() {
        
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
		if (selectedCell != null) {
            g.setColor(Color.BLUE);
            ((Graphics2D) g).fill(selectedCell);
        }
	}
}