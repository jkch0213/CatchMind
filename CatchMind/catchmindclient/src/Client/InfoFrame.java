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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class InfoFrame extends JFrame implements  ActionListener
{

	

	
	
	
	MyInformation myInformation;
	JComboBox selectNum;
	DefaultComboBoxModel nummodel;
	JLabel[] InputLabel;
	JTextField Titletext;
	
	JScrollPane roomscroll;
	String num;

	String msg;
	
	JButton exit;
	JButton enter;
	
	
	
	 public InfoFrame(){	
		 myInformation = new MyInformation();
		 this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
		 

		this.setTitle("������");
//		this.setSize(new Dimension(WIDTH,HEIGHT));
//		this.setSize(WIDTH,HEIGHT);
		
		
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
		InputLabel = new JLabel[7];
		InputLabel[0] = new JLabel("User  I D :");
		InputLabel[1] = new JLabel("L e v e l :");
		InputLabel[2] = new JLabel("E   X   P :");
		InputLabel[3] = new JLabel("-Introduce-");
		InputLabel[4] = new JLabel(myInformation.getGameId());
		
		InputLabel[5] = new JLabel(""+myInformation.getLevel());
		InputLabel[6] = new JLabel(""+myInformation.getExp());
		
		
		
		
		
		exit.setBounds(200,400,95,30);
		enter.setBounds(100,400,95,30);
		JTextArea content= new JTextArea(50,10);
//		Titletext = new JTextField(500);
		
		InputLabel[0].setBounds(50, 50, 80, 20);
		content.setBounds(100, 170, 200,200);
		InputLabel[1].setBounds(50, 80, 80, 20);
		selectNum.setBounds(200, 80, 120, 20);
		InputLabel[2].setBounds(50, 110, 80, 20);
		InputLabel[3].setBounds(50, 140, 80, 20);
		InputLabel[4].setBounds(200, 50, 80, 20);
		InputLabel[5].setBounds(200, 80, 80, 20);
		InputLabel[6].setBounds(200, 110, 80, 20);
		
		
		
		
		this.add(InputLabel[0]);
		this.add(content);
		this.add(InputLabel[1]);
//		this.add(selectNum);
		this.add(exit);
		this.add(enter);
		this.add(InputLabel[2]);
		this.add(InputLabel[3]);
		this.add(InputLabel[4]);
		this.add(InputLabel[5]);
		this.add(InputLabel[6]);
		

		
		
	}

//		SoundEffect.init();



	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		if(event.getSource() == exit)
		{
			
			this.setVisible(false);
					// ��������� ��
		}
		
		
		
		
		

	}

}