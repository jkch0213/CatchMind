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
import javax.swing.table.DefaultTableModel;


public class RoomPanel extends JPanel implements ActionListener{

	private int WIDTH = 750;
	private int HEIGHT = 280;
	
	JTable roomtable;
	DefaultTableModel tmodel;
	JScrollPane roomscroll;
	String data[][];
	
	JButton ready;		// ���� �غ�
	JButton cancel;		// �غ� ���
	
	
	TextArea idlistarea;		// �濡 �ִ� IDlist
	public RoomPanel()
	{
		this.setSize(new Dimension(WIDTH,HEIGHT));
		this.setBorder(new TitledBorder(new EtchedBorder(),"����"));
		
		String col[] = {"��ȣ","����","�ο�"};		//Table�� column

		tmodel = new DefaultTableModel(data,col)
		{
			public boolean isCellEditable(int r, int c)
			{
				return false;			//Table�� Cell�� ���� �Ұ��� �ϵ��� ��
			}
		};
		

		roomtable = new JTable(tmodel);
		roomscroll = new JScrollPane(roomtable); 	// ���̺� ����

		roomtable.getColumnModel().getColumn(0).setPreferredWidth(20);
		roomtable.getColumnModel().getColumn(1).setPreferredWidth(150);
		roomtable.getColumnModel().getColumn(2).setPreferredWidth(40);
		// ���̺� Į�� ũ�� ����
		roomtable.getTableHeader().setReorderingAllowed(false);			//���̺� �÷��� �̵��� ����
		roomtable.getTableHeader().setResizingAllowed(false);		// ���̺� �÷��� ����� ����
		roomtable.getColumnSelectionAllowed();
		roomscroll.setBounds(20, 20, 450, 250);
		this.add(roomscroll);
//		setBounds(100, 100, 500, 300);
//		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void Init()
	{

	}
	
}
