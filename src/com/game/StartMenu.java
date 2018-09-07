package com.game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartMenu extends JPanel {

	public StartMenu() {
		initMenu();
	}
	
	private void initMenu() {
		JFrame frame = new JFrame("Minesweeper");

		frame.setSize(1182, 1000);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);

		JButton startbutton = new JButton("Start");
		startbutton.setBounds(550,510,100,40);
		frame.add(startbutton);
		JButton helpbutton = new JButton("Help");
		helpbutton.setBounds(550,570, 100, 40);
		frame.add(helpbutton);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		startbutton.addMouseListener(new MouseAdapter() {
		            
        	@Override
		    public void mouseReleased(MouseEvent e) {
//        		System.out.println("hihi");

                frame.setTitle("Minesweeper");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);

        		frame.setSize(1176, 1000);
        		frame.setContentPane(new Board());
        		frame.invalidate();
        		frame.validate();
        	}
		});
	}
}
