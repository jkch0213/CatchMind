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
	GamePanel gamePanel;		// ������ �����ϴ� �г�
	LoginPanel loginPanel;		//�α��� ������ �����ִ� ������
	SignUp signUp;			//ȸ������ ������ �����ִ� ������
	String state;			//â������ �ߴ°� �����ϱ�����....���߿� �ڼ��� �ڵ�

	JButton home;
	JButton exit;
	JButton roomMake;
	JButton join;
	JButton itemShop;
	JButton avatar;
	JButton roomInfo;
	JButton myInfo;
	
	
	public CatchmindFrame(Socket socket)
	{	
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);		// ���� ��� X��ư�� ������ �������� ������ ����

		this.setTitle("Catchmind Game");
//		this.setSize(new Dimension(WIDTH,HEIGHT));
		this.setSize(WIDTH,HEIGHT);
		
		// ���濡 �ִ� ��ɼ� ��ư
		roomMake = new JButton("�游���");
		join= new JButton("�����ϱ�");
		itemShop = new JButton("���Ӿ�����");
		avatar = new JButton("�ƹ�Ÿ��");
		roomInfo = new JButton("������");
		myInfo = new JButton("������");
		home = new JButton("�ڷ�");	
		exit = new JButton("����");	
		
		// ��ư Ŭ���� �� ����ǵ��� ������ �߰�
		roomMake.addActionListener(this);
		join.addActionListener(this);
		itemShop.addActionListener(this);
		avatar.addActionListener(this);
		roomInfo.addActionListener(this);
		myInfo.addActionListener(this);
		home.addActionListener(this);
		exit.addActionListener(this);
		
		// �гε� ��ü ����
		myInformation = new MyInformation();
		roompanel = new RoomPanel();
		chatpanel = new ChatPanel();
		idlistpanel = new IDlistPanel();
		informationpanel = new InformationPanel();	
		loginPanel = new LoginPanel();
		gamePanel = new GamePanel();

		this.setLayout(null);
		
		exit.setBounds(850,620,95,30);
		this.add(exit);

		// �α��� ȭ�� �ѷ��ֱ�
		//EnterLoginRoom();
		
		// �α����� �ȵǸ� ��� while �Լ� ���� 
		
		//LoginRoomExit();
		//EnterWaitRoom();
		EnterGameRoom();
		
		Thread Catchmind;
		Catchmind = new Thread(this);	// ������ ����
		Catchmind.start();
		
	}
	
	public void EnterLoginRoom(){
		loginPanel.setBounds(300,250,380,180);
		this.add(loginPanel);
	}
	public void ExitLoginRoom(){
		this.remove(loginPanel);
	}
	
	public void EnterWaitRoom(){
		roomMake.setBounds(50,20,95,30);
		join.setBounds(150,20,95,30);
		roomInfo.setBounds(250,20,95,30);
		myInfo.setBounds(650,20,95,30);
		avatar.setBounds(750,20,95,30);
		itemShop.setBounds(850,20,115,30);
		
		roompanel.setBounds(50, 70, 500, 280);
		chatpanel.setBounds(50, 400, 500, 230);	
//		idlistpanel.setBounds(570, 20, 200, 270);	
//		informationpanel.setBounds(520, 300, 250, 210);	
//		this.add(idlistpanel);

		this.add(roomMake);
		this.add(join);
		this.add(roomInfo);
		this.add(itemShop);
		this.add(avatar);
		this.add(myInfo);
		this.add(chatpanel);
		this.add(roompanel);
		
	}
	public void ExitWaitRoom(){
		this.remove(roomMake);
		this.remove(join);
		this.remove(roomInfo);
		this.remove(itemShop);
		this.remove(avatar);
		this.remove(myInfo);
		this.remove(roompanel);
		this.remove(chatpanel);
	}
	
	public void EnterGameRoom(){
		gamePanel.setBounds(30, 20, 920, 400);
		chatpanel.setBounds(30, 430, 500, 230);	
		this.add(gamePanel);
		this.add(chatpanel);

	}
	public void ExitGameRoom(){
		this.remove(gamePanel);
		this.remove(chatpanel);
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
		else if(event.getSource()==roomInfo)
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
