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
	

	
	private int WIDTH = 750;
	private int HEIGHT = 280;
	
	JButton ready;		// ���� �غ�
	JButton cancel;		// �غ� ���
	
	
	TextArea idlistarea;		// �濡 �ִ� IDlist
	public GamePanel()
	{
	
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void Init()
	{

	}
	public static void main(String[] args) {
		new GamePanel();
		
	}
}
