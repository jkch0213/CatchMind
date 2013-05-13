package Client;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.*;

import javax.swing.border.*;
/**
 * 
 * @author Administrator ???4??client
 */
public class CanvasPanel extends JPanel implements MouseMotionListener {
	private Socket socket;
	private PrintWriter writer;
	private BufferedReader reader;

	private Image img = null;
	private Graphics gImg = null;

	private int x = 0;
	private int y = 0;

	public CanvasPanel(Socket socket) {
		// TODO Auto-generated constructor stub
		this.setSize(new Dimension(500,350));
		this.setBorder(new TitledBorder(new EtchedBorder(),"CatchMind"));

		addMouseMotionListener(this);
		/*
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});*/

		setBounds(0, 0, 500, 350);
		setVisible(true);

		img = createImage(500, 350);
		//gImg = img.getGraphics();
		repaint();
		
		try {
			writer = new PrintWriter(new OutputStreamWriter(
					socket.getOutputStream()));

		} catch (Exception e) {
			
		}
	}
	
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		gImg.drawLine(x, y, e.getX(), e.getY());
		x = e.getX();
		y = e.getY();
		repaint();
		
		writer.println("DRAW:" + x + "_" + y);
		writer.flush();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		x = e.getX();
		y = e.getY();
		writer.println("MOVE:" + x + "_" + y);
		writer.flush();
	}
}
