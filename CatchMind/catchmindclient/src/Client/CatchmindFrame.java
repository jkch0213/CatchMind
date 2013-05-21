package Client;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Login.FindIDFrame;
import Login.FindPassFrame;
import Login.LoginPanel;
import Login.SignUpFrame;



public class CatchmindFrame extends JFrame implements Runnable, ActionListener
{

	private int WIDTH = 1000;
	private int HEIGHT = 700;
	private StringTokenizer tokenizer1;
	private String[] tokens1 = new String[200];		//최대 200개의 방까지

	private StringTokenizer tokenizer2;
	private String[] tokens2 = new String[20];
	
	
	private static RoomInformationFrame roomInfoframe;
	
	MyInformation myInformation;
	RoomPanel roompanel;		// 방 목록을 보고 입장하거나 방을 만들 수 있는 패널
	ChatPanel chatpanel;		// 접속한 client와 채팅을 할 수 있는 패널
	IDlistPanel idlistpanel;		// 접속중인 ID를 알려주는 패널
	InformationPanel informationpanel;		// 정보 및 상태를 알려 주는 패널
	GamePanel gamepanel;		// 게임을 진행하는 패널
	LoginPanel loginPanel;		//로그인 페이지 보여주는 프레임
	SignUpFrame signUp;			//회원가입 페이지 보여주는 프레임
	String state;			//창여러개 뜨는걸 방지하기위해....나중에 자세히 코딩
	
	JButton home;
	JButton exit;
	JButton roomMake;
	JButton join;
	JButton itemShop;
	JButton avatar;
	JButton roomInfo;
	JButton myInfo;
	
	int saveRowNum;
	private static String title="";
	private static String peopleCount="";
	private static String idList="";
	private static String roomNum="";
	
	public CatchmindFrame(Socket socket)
	{	
		
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);		// 우측 상단 X버튼을 눌러도 프레임이 닫히지 않음

		this.setTitle("Catchmind Game");
		this.setSize(WIDTH,HEIGHT);
		loginPanel = new LoginPanel();
		exit = new JButton("종료");	
		roomMake = new JButton("방만들기");
		join= new JButton("참여하기");
		itemShop = new JButton("게임아이템");
		avatar = new JButton("아바타샵");
		roomInfo = new JButton("방정보");
		myInfo = new JButton("내정보");
		home = new JButton("뒤로");	
		myInformation = new MyInformation();
		roompanel = new RoomPanel();
		chatpanel = new ChatPanel();
		informationpanel = new InformationPanel();	
		gamepanel = new GamePanel();
		
		
		LoginRoom();
		idlistpanel = new IDlistPanel();
		Thread Catchmind;
		Catchmind = new Thread(this);	// 스레드 생성
		Catchmind.start();

	}
	public void LoginRoom(){
		
		this.setLayout(null);
		
		loginPanel.setBounds(200,150,380,180);
		this.add(loginPanel);
		
	}
	private void removeLoginPanel() {
		// TODO Auto-generated method stub
		this.remove(loginPanel);
		this.repaint();
	}
	
	public void WaitRoom(){
		
		exit.addActionListener(this);
		roomMake.addActionListener(this);
		join.addActionListener(this);
		itemShop.addActionListener(this);
		avatar.addActionListener(this);
		roomInfo.addActionListener(this);
		myInfo.addActionListener(this);
		home.addActionListener(this);

		this.setLayout(null);

		exit.setBounds(800,620,95,30);
		roomMake.setBounds(100,20,95,30);
		join.setBounds(200,20,95,30);
		roomInfo.setBounds(300,20,95,30);
		itemShop.setBounds(680,20,115,30);
		avatar.setBounds(800,20,95,30);
		myInfo.setBounds(580,20,95,30);
		roompanel.setBounds(100, 70, 500, 280);
//		home.setBounds(670,520,80,30);
//		gamepanel.setBounds(100, 100, 500, 300);
		chatpanel.setBounds(100, 350, 500, 250);	
		idlistpanel.setBounds(630, 70, 265, 280);	
//		informationpanel.setBounds(520, 300, 250, 210);	
		
//		this.add(roompanel);
//		this.add(chatpanel);
		this.add(idlistpanel);
//		this.add(informationpanel);
		this.add(exit);
		this.add(roomMake);
		this.add(join);
		this.add(roomInfo);
		this.add(itemShop);
		this.add(avatar);
		this.add(myInfo);
		this.add(gamepanel);
		this.add(chatpanel);
		this.add(roompanel);
		
	}

	private void removeWaitRoom() {
		// TODO Auto-generated method stub
		this.remove(roomMake);
		this.remove(join);
		this.remove(roomInfo);
		this.remove(itemShop);
		this.remove(avatar);
		this.remove(myInfo);
		this.remove(gamepanel);
		this.remove(roompanel);
	}

	public String getTitle() {
		return title;
	}

	public void setRoomTitle(String title) {
		this.title = title;
	}

	public String getPeopleCount() {
		return peopleCount;
	}

	public void setPeopleCount(String peopleCount) {
		this.peopleCount = peopleCount;
	}

	public String getIdList() {
		return idList;
	}

	public void setIdList(String idList) {
		this.idList = idList;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public String getRoomNum() {
		return roomNum;
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
					System.out.println(line);
					chatpanel.chatarea.append(line.substring(7)+"\n");
				}
				else if(line.startsWith("[ShowID]"))
				{
//					myInformation.setNumId(line.substring(8));
//					myInformation.setGameId(line.substring(8));
				}
				else if(line.startsWith("[Login]"))
				{
					removeLoginPanel();
					WaitRoom();
					JOptionPane.showMessageDialog(SignUpFrame.IDText, line.substring(7) + "님 반갑습니다.^^");
					repaint();
				}
				else if(line.startsWith("[LoginFail]")){
					JOptionPane.showMessageDialog(LoginPanel.loginPanel, "아이디나 비밀번호가 맞지 않습니다. " 
							,"Login Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(line.startsWith("[MadeRoom] "))
				{
					System.out.println(line);
					removeWaitRoom();
					repaint();
					
				}
				else if(line.startsWith("[MakeRoom]"))
				{
					
				}
				else if(line.startsWith("[RoomSize]"))
				{
					int j=roompanel.tmodel.getRowCount();
					
						System.out.println(j+"j");
						roompanel.tmodel.fireTableRowsDeleted(0, j);
					
				}
				else if(line.startsWith("[Roomlist]"))	// [Roomlist] 으로 시작하는 메시지면 room 테이블 변경
				{
					
					System.out.println(line+"roomlistline");
					tokenizer1 = new StringTokenizer(line.substring(10),"\n");		//row를 \n을 기준으로 나눔
					int i =0;

					while(tokenizer1.hasMoreTokens())
					{
						tokens1[i] = tokenizer1.nextToken();	// 나눈 row들을 tokens1에 각각 저장

						tokenizer2 = new StringTokenizer(tokens1[i],"\t");		// 나눈 row를 col을\t를 기준으로 나눔
						int j =0;
						System.out.println(i+"tokens1[i]:"+tokens2[i]);

						while(tokenizer2.hasMoreTokens())
						{
							tokens2[j] = tokenizer2.nextToken();
							System.out.println(j+"tokens2[j]:"+tokens2[j]);
							j++;
						}		
						System.out.println("tokens2:"+tokens2);
						roompanel.tmodel.addRow(tokens2);	// 방 목록 테이블에 한개의 row씩 추가	
						i++;
					}
					saveRowNum=i;
				}
				else if(line.startsWith("[EnterRoom]"))
				{
					tokenizer2 = new StringTokenizer(line.substring(13),"\t");		// /t를 기준으로 col을 구별
					int i =0;

					while(tokenizer2.hasMoreTokens())
					{
						tokens2[i] = tokenizer2.nextToken();		// tokens[0] 방번호	 tokens[1] 입장 수 현황
						i++;
					}
					for(int row=0;row<roompanel.tmodel.getRowCount();row++)		// roomtable의 row수만큼 밤복
					{
						if(tokens2[0].equals(roompanel.tmodel.getValueAt(row, 0)))	// 방번호와 0번째 column값이 같으면
						{
							roompanel.tmodel.setValueAt(tokens2[1], row, 2);		// 해당 방의 인원수 현황을 변경
						}
					}
					
				}
				else if(line.startsWith("[SetGamePanel]"))
				{
					System.out.println(line);
					removeWaitRoom();
					repaint();
				}
				
				else if(line.startsWith("[RoomTitle]"))
				{
					System.out.println(line);
					System.out.println(line.substring(11));
					setRoomTitle(line.substring(11));  
					
				}
				
				else if(line.startsWith("[RoomPeopleCount]"))
				{
					setPeopleCount(line.substring(17));
					
				}
				
				else if(line.startsWith("[RoomIdList]"))
				{
					setIdList(line.substring(12));
//			       CatchmindDriver.getDos().writeUTF("[EndSetRoomInfo]");

					
				}
				else if(line.startsWith("[RoomNum]"))
				{
					setRoomNum(line.substring(9));
				}
				else if(line.startsWith("[EndSetInfo]"))
				{
//					getRoomInfoframe().setVisible(true);
				}
				
				
				else if(line.startsWith("[Clientlist]"))	// [Clientlist] 로 시작하는 메시지면 접속자 목록을 출력
				{
					idlistpanel.idlistarea.setText("");
					idlistpanel.idlistarea.append(line.substring(12)+"\n");
				}
				/* ---------------------- 2013.05.20 월  ---------------------- */
				else if(line.startsWith("[LoginIDCheck]")){
					if(line.substring(14).equals("true ")){
						System.out.println("현재 아이디로 가입이 가능합니다");
						JOptionPane.showMessageDialog(SignUpFrame.IDText, "현재 아이디로 가입이 가능합니다");
					}
					else if(line.substring(14).equals("false ")){
						JOptionPane.showMessageDialog(SignUpFrame.IDText, "현재 아이디로 가입이 불가능합니다. " +
								"다른 아이디를 입력하세요","ID Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				else if(line.startsWith("[LoginSignUp]")){
					JOptionPane.showMessageDialog(SignUpFrame.signUpFrame, "가입이 완료되었습니다.");
					
				}
				else if(line.startsWith("[LoginFindPass]")){
					if(line.substring(15).trim() != null){
						JOptionPane.showMessageDialog(FindPassFrame.findPassFrame,
							"비밀번호는 : " + line.substring(15) + "입니다.");
					}else if(line.substring(15).trim() == null){
						JOptionPane.showMessageDialog(FindPassFrame.findPassFrame,
								"입력한 정보가 올바르지 않습니다. 다시 한 번 확인해주세요");
					}
				}
				else if(line.startsWith("[LoginFindID]")){
					if(line.substring(13).trim() != null){
						JOptionPane.showMessageDialog(FindIDFrame.findIDFrame,
							"아이디는 : " + line.substring(13) + "입니다.");
					}else if(line.substring(13).trim() == null){
						JOptionPane.showMessageDialog(FindIDFrame.findIDFrame,
								"입력한 정보가 올바르지 않습니다. 다시 한 번 확인해주세요.");
					}
				}
				/* ------------------------ 2013.05.20 월  ------------------------ */

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
			int row = -1;
			row = roompanel.roomtable.getSelectedRow();		// 입장할 방을 선택하지 않은 경우
			if (row == -1)
			{
				JOptionPane.showMessageDialog(join, "방을 선택해주세요",
						"ID Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
			
			try {
				RoomInformationFrame roomInfoframe = new RoomInformationFrame();
				CatchmindDriver.getDos().writeUTF("[RequestInfor]"+ roompanel.roomtable.getValueAt(row, 0));
				for(int i=1;i<100;i++);
				roomInfoframe.setResizable(false);
				roomInfoframe.setBounds(300, 150, 400, 500);
				roomInfoframe.setVisible(true);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
		else if(event.getSource() == join)
		{
			String msg;
			int row = -1;
			row = roompanel.roomtable.getSelectedRow();		// 입장할 방을 선택하지 않은 경우
			if (row == -1)
			{
				JOptionPane.showMessageDialog(join, "방을 선택해주세요",
						"ID Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(roompanel.roomtable.getValueAt(row, 2).equals("Full"))	// 방 상태가 Full이면 입장 불가
			{
				JOptionPane.showMessageDialog(join, "방이꽉찼습니다",
						"ID Error", JOptionPane.ERROR_MESSAGE);
			}
			else	// 방 상태가 Full이 아니면 서버에 입장을 요청
			{
				msg = "[EnterRoom] " + roompanel.roomtable.getValueAt(row, 0);		//선택한 방번호를 받음
				System.out.println("msg");
				try {
					CatchmindDriver.getDos().writeUTF(msg);			//[Enter Room] 방번호 메시지를 서버로 보냄
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}
		else if(event.getSource()==myInfo)
		{
			InfoFrame info= new InfoFrame();
			info.setBounds(300, 150, 400, 500);
			info.setResizable(false);
			info.setVisible(true);
			
		}

	}
	public static RoomInformationFrame getRoomInfoframe() {
		return roomInfoframe;
	}
	public static void setRoomInfoframe(RoomInformationFrame roomInfoframe) {
		CatchmindFrame.roomInfoframe = roomInfoframe;
	}

}
