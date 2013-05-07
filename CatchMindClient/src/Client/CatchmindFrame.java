package Client;

import java.awt.*;

import javax.swing.*;

public class CatchmindFrame extends JFrame {
	LoginPanel loginPanel;
	
	private int WIDTH = 800;
	private int HEIGHT = 600;
	
	public CatchmindFrame(){
		this.setTitle("CatchMind Game");
		this.setSize(new Dimension(WIDTH,HEIGHT));
		
		loginPanel = new LoginPanel();
		
		this.setLayout(null);
		
		loginPanel.setBounds(200,150,380,180);
		
		this.add(loginPanel);
		
	}
}
