package Client;
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


public class RoomPanel extends JPanel implements ActionListener{

	private int WIDTH = 750;
	private int HEIGHT = 280;
	
	JButton ready;		// 게임 준비
	JButton cancel;		// 준비 취소
	
	
	TextArea idlistarea;		// 방에 있는 IDlist
	public RoomPanel()
	{
		this.setSize(new Dimension(WIDTH,HEIGHT));
		this.setBorder(new TitledBorder(new EtchedBorder(),"방목록"));
		
//		setBounds(100, 100, 500, 300);
		setVisible(true);
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
