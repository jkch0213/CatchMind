package Client;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;


public class CatchmindFrame extends JFrame implements Runnable, ActionListener
{

	private int WIDTH = 1000;
	private int HEIGHT = 700;
	
	MyInformation myInformation;
	RoomPanel roompanel;		// �� ����� ���� �����ϰų� ���� ���� �� �ִ� �г�
	ChatPanel chatpanel;		// ������ client�� ä���� �� �� �ִ� �г�
	IDlistPanel idlistpanel;		// �������� ID�� �˷��ִ� �г�
	InformationPanel informationpanel;		// ���� �� ���¸� �˷� �ִ� �г�
	GamePanel gamepanel;		// ������ �����ϴ� �г�
	LoginPanel loginPanel;		//�α��� ������ �����ִ� ������
	SignUp signUp;			//ȸ������ ������ �����ִ� ������
	String state;			//â������ �ߴ°� �����ϱ�����....���߿� �ڼ��� �ڵ�

	JButton home;
	JButton exit;
	JButton roomMake;
	JButton join;
	JButton itemShop;
	JButton avatar;
	JButton roomImfo;
	JButton myInfo;
	
	
	public CatchmindFrame(Socket socket)
	{	
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);		// ���� ��� X��ư�� ������ �������� ������ ����

		this.setTitle("Catchmind Game");
//		this.setSize(new Dimension(WIDTH,HEIGHT));
		this.setSize(WIDTH,HEIGHT);
		
		LoginRoom();
		
		//while(loginPanel.LoginCheck());
		//WaitRoom();
		
		
	}
	public void LoginRoom(){
		
		loginPanel = new LoginPanel();
		this.setLayout(null);
		
		loginPanel.setBounds(200,150,380,180);
		this.add(loginPanel);
		
	}
	
	public void WaitRoom(){
		
		exit = new JButton("����");	
		roomMake = new JButton("�游���");
		join= new JButton("�����ϱ�");
		itemShop = new JButton("���Ӿ�����");
		avatar = new JButton("�ƹ�Ÿ��");
		roomImfo = new JButton("������");
		myInfo = new JButton("������");
		
		
		
		exit.addActionListener(this);
		roomMake.addActionListener(this);
		join.addActionListener(this);
		itemShop.addActionListener(this);
		avatar.addActionListener(this);
		roomImfo.addActionListener(this);
		myInfo.addActionListener(this);

		home = new JButton("�ڷ�");	
		home.addActionListener(this);

		myInformation = new MyInformation();
		roompanel = new RoomPanel();
		chatpanel = new ChatPanel();
		idlistpanel = new IDlistPanel();
		informationpanel = new InformationPanel();	
		gamepanel = new GamePanel();

		this.setLayout(null);

		exit.setBounds(800,620,95,30);
		roomMake.setBounds(100,20,95,30);
		join.setBounds(200,20,95,30);
		roomImfo.setBounds(300,20,95,30);
		itemShop.setBounds(680,20,115,30);
		avatar.setBounds(800,20,95,30);
		myInfo.setBounds(580,20,95,30);

		roompanel.setBounds(100, 70, 500, 280);
//		home.setBounds(670,520,80,30);
//		gamepanel.setBounds(100, 100, 500, 300);
		chatpanel.setBounds(100, 350, 500, 250);	
//		idlistpanel.setBounds(570, 20, 200, 270);	
//		informationpanel.setBounds(520, 300, 250, 210);	
//		this.add(roompanel);
//		this.add(chatpanel);
//		this.add(idlistpanel);
//		this.add(informationpanel);

		Thread Catchmind;
		Catchmind = new Thread(this);	// ������ ����
		Catchmind.start();
		this.add(exit);
		
		this.add(roomMake);
		this.add(join);
		this.add(roomImfo);
		this.add(itemShop);
		this.add(avatar);
		this.add(myInfo);
		
		this.add(gamepanel);
		this.add(chatpanel);
		this.add(roompanel);
		
	}


//		SoundEffect.init();
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String line;
		
		try {  
			while(true)
			{
				line = CatchmindDriver.getDis().readUTF();
				if(line.startsWith("[Chat]"))		// [ / �� �����ϴ� �޽����� ä��â�� �޽����� ���
				{
					chatpanel.chatarea.append(line.substring(7)+"\n");
				}
				else if(line.startsWith("[ShowID]"))
				{
					myInformation.setNumId(line.substring(8));
					myInformation.setGameId(line.substring(8));
				}
				
				
			}
		} catch (IOException e) {  
			System.out.println("������ ������ ���������ϴ�.");
		}
	
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		if(event.getSource() == exit)
		{
			try {
				CatchmindDriver.exit();		// ������ ������ ������ : �����Լ��� socket, dis, dos�� close
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.setVisible(false);			// ���� ��ư�� ������ �������� ����
//			SoundEffect.BGM.stop();			// ��������� ��
		}
		
		if(event.getSource()== roomMake){
			RoomMakeFrame ro= new RoomMakeFrame();
			ro.setBounds(300, 150, 400, 200);
			ro.setResizable(false);
			ro.setVisible(true);
		}
		else if(event.getSource()==roomImfo)
		{
			JFrame roomImfoFrame= new JFrame();
			roomImfoFrame.setBounds(300, 150, 400, 400);
			roomImfoFrame.setResizable(false);
			roomImfoFrame.setVisible(true);
		}
		else if(event.getSource()==join)
		{
			JFrame JoinFrame= new JFrame();
			JoinFrame.setBounds(300, 150, 400, 400);
			JoinFrame.setResizable(false);
			JoinFrame.setVisible(true);
			
		}
		else if(event.getSource()==myInfo)
		{
			InfoFrame info= new InfoFrame();
			info.setBounds(300, 150, 400, 500);
			info.setResizable(false);
			info.setVisible(true);
			
		}
		

	}

}
