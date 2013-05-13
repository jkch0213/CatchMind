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
	RoomPanel roompanel;		// 방 목록을 보고 입장하거나 방을 만들 수 있는 패널
	ChatPanel chatpanel;		// 접속한 client와 채팅을 할 수 있는 패널
	IDlistPanel idlistpanel;		// 접속중인 ID를 알려주는 패널
	InformationPanel informationpanel;		// 정보 및 상태를 알려 주는 패널
	GamePanel gamePanel;		// 게임을 진행하는 패널
	LoginPanel loginPanel;		//로그인 페이지 보여주는 프레임
	SignUp signUp;			//회원가입 페이지 보여주는 프레임
	String state;			//창여러개 뜨는걸 방지하기위해....나중에 자세히 코딩

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
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);		// 우측 상단 X버튼을 눌러도 프레임이 닫히지 않음

		this.setTitle("Catchmind Game");
//		this.setSize(new Dimension(WIDTH,HEIGHT));
		this.setSize(WIDTH,HEIGHT);
		
		// 대기방에 있는 기능성 버튼
		roomMake = new JButton("방만들기");
		join= new JButton("참여하기");
		itemShop = new JButton("게임아이템");
		avatar = new JButton("아바타샵");
		roomInfo = new JButton("방정보");
		myInfo = new JButton("내정보");
		home = new JButton("뒤로");	
		exit = new JButton("종료");	
		
		// 버튼 클릭할 때 실행되도록 리스너 추가
		roomMake.addActionListener(this);
		join.addActionListener(this);
		itemShop.addActionListener(this);
		avatar.addActionListener(this);
		roomInfo.addActionListener(this);
		myInfo.addActionListener(this);
		home.addActionListener(this);
		exit.addActionListener(this);
		
		// 패널들 객체 생성
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

		// 로그인 화면 뿌려주기
		//EnterLoginRoom();
		
		// 로그인이 안되면 계속 while 함수 실행 
		
		//LoginRoomExit();
		//EnterWaitRoom();
		EnterGameRoom();
		
		Thread Catchmind;
		Catchmind = new Thread(this);	// 스레드 생성
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
				if(line.startsWith("[Chat]"))		// [ / 로 시작하는 메시지면 채팅창에 메시지를 출력
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
			System.out.println("서버와 연결이 끊어졌습니다.");
		}
	
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		if(event.getSource() == exit)
		{
			try {
				CatchmindDriver.exit();		// 서버와 접속을 해지함 : 메인함수의 socket, dis, dos를 close
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.setVisible(false);			// 종료 버튼을 누르면 프레임을 닫음
//			SoundEffect.BGM.stop();			// 배경음악을 끔
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
