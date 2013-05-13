package Client;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class GamePanel extends JPanel implements ActionListener
{
	private int WIDTH = 900;
	private int HEIGHT = 400;
	
	JButton ready;		// ���� �غ�
	JButton cancel;		// �غ� ���
	CanvasPanel canvasPanel;
	
	TextArea idlistarea;		// �濡 �ִ� IDlist
	public GamePanel()
	{
		this.setSize(new Dimension(WIDTH,HEIGHT));
		this.setBorder(new TitledBorder(new EtchedBorder(),"CatchMind"));
		
		canvasPanel = new CanvasPanel(CatchmindDriver.getSocket());
		canvasPanel.setBounds(200, 20, 500, 350);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void Init()
	{

	}

}
