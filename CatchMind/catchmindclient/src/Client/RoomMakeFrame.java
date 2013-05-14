package Client;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;



public class RoomMakeFrame extends JFrame implements  ActionListener
{

	
	private int WIDTH = 400;
	private int HEIGHT =300;
	
	
	RoomPanel roompanel;		// �� ����� ���� �����ϰų� ���� ���� �� �ִ� �г�
	ChatPanel chatpanel;		// ������ client�� ä���� �� �� �ִ� �г�
	IDlistPanel idlistpanel;		// �������� ID�� �˷��ִ� �г�
	InformationPanel informationpanel;		// ���� �� ���¸� �˷� �ִ� �г�
	GamePanel gamepanel;		// ������ �����ϴ� �г�
	String state;//â������ �ߴ°� �����ϱ�����....���߿� �ڼ��� �ڵ�

	JComboBox selectNum;
	DefaultComboBoxModel nummodel;
	JLabel[] InputLabel;
	JTextField Titletext;
	
	JScrollPane roomscroll;
	String num;
	String msg;
	
	JButton exit;
	JButton enter;
	
	
	
	 public RoomMakeFrame(){	
		 this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
		 String major[]={"3","4",
					"5","6"};

		this.setTitle("�游���");
//		this.setSize(new Dimension(WIDTH,HEIGHT));
		this.setSize(WIDTH,HEIGHT);
		
		
		exit = new JButton("���");	
		enter = new JButton("Ȯ��");	
		
	
		
		
		
		exit.addActionListener(this);
		enter.addActionListener(this);
		
		
	

		this.setLayout(null);
		

		nummodel = new DefaultComboBoxModel();
		selectNum = new JComboBox(nummodel);		//�ο��� �޺�
		nummodel.addElement("3");
		nummodel.addElement("4");
		nummodel.addElement("5");
		nummodel.addElement("6");
		
		num = (String)nummodel.getElementAt(0);
		selectNum.addActionListener(this);	
//		this.add( s2 );
		InputLabel = new JLabel[2];
		InputLabel[0] = new JLabel("�� �� ��  : ");
		InputLabel[1] = new JLabel("��     ��  : ");
		exit.setBounds(200,125,95,30);
		enter.setBounds(100,125,95,30);

		Titletext = new JTextField(20);
		
		InputLabel[0].setBounds(50, 50, 80, 20);
		Titletext.setBounds(200, 50, 120, 20);
		InputLabel[1].setBounds(50, 80, 80, 20);
		selectNum.setBounds(200, 80, 120, 20);
		
		
		
		
		this.add(InputLabel[0]);
		this.add(Titletext);
		this.add(InputLabel[1]);
		this.add(selectNum);
		this.add(exit);
		this.add(enter);
		
		

		
		
	}

//		SoundEffect.init();



	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		if(event.getSource() == selectNum)
		{  
			num = (String)nummodel.getElementAt(selectNum.getSelectedIndex());	//�ο� �� �޺��ڽ� ���� String�� num ����
			System.out.println("���ο�:"+num);
		}
		if(event.getSource() == exit)
		{
			
			this.setVisible(false);
					// ��������� ��
		}
		else if(event.getSource() == enter){
			if(Titletext.getText().equals(""))
			{
				JOptionPane.showMessageDialog(enter, "�������� �Է��ϼ���",
						"ID Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				
				msg= "[MakeRoom] "+ Titletext.getText() + "\t" +num ;
				System.out.println("���ο�:"+num);
				try {
					CatchmindDriver.getDos().writeUTF(msg);
					setVisible(false);
				} catch (IOException e) {
					e.printStackTrace();
				}
				Titletext.setText("");
							
			}
			
			
			
		
			
		}
		
		
		
		
		

	}

}

