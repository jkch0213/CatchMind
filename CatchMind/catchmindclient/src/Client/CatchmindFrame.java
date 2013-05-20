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

import Login.LoginPanel;
import Login.SignUpFrame;



public class CatchmindFrame extends JFrame implements Runnable, ActionListener
{

	private int WIDTH = 1000;
	private int HEIGHT = 700;
	private StringTokenizer tokenizer1;
	private String[] tokens1 = new String[200];		//�ִ� 200���� �����

	private StringTokenizer tokenizer2;
	private String[] tokens2 = new String[20];
	
	
	private static RoomInformationFrame roomInfoframe;
	
	MyInformation myInformation;
	RoomPanel roompanel;		// �� ����� ���� �����ϰų� ���� ���� �� �ִ� �г�
	ChatPanel chatpanel;		// ������ client�� ä���� �� �� �ִ� �г�
	IDlistPanel idlistpanel;		// �������� ID�� �˷��ִ� �г�
	InformationPanel informationpanel;		// ���� �� ���¸� �˷� �ִ� �г�
	GamePanel gamepanel;		// ������ �����ϴ� �г�
	LoginPanel loginPanel;		//�α��� ������ �����ִ� ������
	SignUpFrame signUp;			//ȸ������ ������ �����ִ� ������
	String state;			//â������ �ߴ°� �����ϱ�����....���߿� �ڼ��� �ڵ�
	
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
		
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);		// ���� ��� X��ư�� ������ �������� ������ ����

		this.setTitle("Catchmind Game");
		this.setSize(WIDTH,HEIGHT);

		LoginRoom();
		idlistpanel = new IDlistPanel();
		Thread Catchmind;
		Catchmind = new Thread(this);	// ������ ����
		Catchmind.start();

	}
	public void LoginRoom(){
		
		loginPanel = new LoginPanel();
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
		
		exit = new JButton("����");	
		roomMake = new JButton("�游���");
		join= new JButton("�����ϱ�");
		itemShop = new JButton("���Ӿ�����");
		avatar = new JButton("�ƹ�Ÿ��");
		roomInfo = new JButton("������");
		myInfo = new JButton("������");
		
		exit.addActionListener(this);
		roomMake.addActionListener(this);
		join.addActionListener(this);
		itemShop.addActionListener(this);
		avatar.addActionListener(this);
		roomInfo.addActionListener(this);
		myInfo.addActionListener(this);

		home = new JButton("�ڷ�");	
		home.addActionListener(this);

		
		myInformation = new MyInformation();
		roompanel = new RoomPanel();
		chatpanel = new ChatPanel();
		
		informationpanel = new InformationPanel();	
		gamepanel = new GamePanel();
		
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
				
				if(line.startsWith("[Chat]"))		// [ / �� �����ϴ� �޽����� ä��â�� �޽����� ���
				{
					System.out.println(line);
					chatpanel.chatarea.append(line.substring(7)+"\n");
				}
				else if(line.startsWith("[ShowID]"))
				{
//					myInformation.setNumId(line.substring(8));
//					myInformation.setGameId(line.substring(8));
				}
				else if(line.startsWith("[login]"))
				{
					System.out.println(line);
					removeLoginPanel();
					WaitRoom();
					repaint();
					
				}
				else if(line.startsWith("[MadeRoom] "))
				{
					System.out.println(line);
					removeWaitRoom();
					repaint();
					
				}
				if(line.startsWith("[MakeRoom]"))
				{
					
				}
				if(line.startsWith("[RoomSize]"))
				{
					int j=roompanel.tmodel.getRowCount();
					
						System.out.println(j+"j");
						roompanel.tmodel.fireTableRowsDeleted(0, j);
					
				}
				if(line.startsWith("[Roomlist]"))	// [Roomlist] ���� �����ϴ� �޽����� room ���̺� ����
				{
					
					System.out.println(line+"roomlistline");
					tokenizer1 = new StringTokenizer(line.substring(10),"\n");		//row�� \n�� �������� ����
					int i =0;

					while(tokenizer1.hasMoreTokens())
					{
						tokens1[i] = tokenizer1.nextToken();	// ���� row���� tokens1�� ���� ����

						tokenizer2 = new StringTokenizer(tokens1[i],"\t");		// ���� row�� col��\t�� �������� ����
						int j =0;
						System.out.println(i+"tokens1[i]:"+tokens2[i]);

						while(tokenizer2.hasMoreTokens())
						{
							tokens2[j] = tokenizer2.nextToken();
							System.out.println(j+"tokens2[j]:"+tokens2[j]);
							j++;
							
						}		
						System.out.println("tokens2:"+tokens2);
						roompanel.tmodel.addRow(tokens2);	// �� ��� ���̺� �Ѱ��� row�� �߰�	
						i++;
					}		
					saveRowNum=i;
				}
				if(line.startsWith("[EnterRoom]"))
				{
					tokenizer2 = new StringTokenizer(line.substring(13),"\t");		// /t�� �������� col�� ����
					int i =0;

					while(tokenizer2.hasMoreTokens())
					{
						tokens2[i] = tokenizer2.nextToken();		// tokens[0] ���ȣ	 tokens[1] ���� �� ��Ȳ
						i++;
					}
					for(int row=0;row<roompanel.tmodel.getRowCount();row++)		// roomtable�� row����ŭ �㺹
					{
						if(tokens2[0].equals(roompanel.tmodel.getValueAt(row, 0)))	// ���ȣ�� 0��° column���� ������
						{
							roompanel.tmodel.setValueAt(tokens2[1], row, 2);		// �ش� ���� �ο��� ��Ȳ�� ����
						}
					}
					
				}
				if(line.startsWith("[SetGamePanel]"))
				{
					System.out.println(line);
					removeWaitRoom();
					repaint();
				}
				
				if(line.startsWith("[RoomTitle]"))
				{
					System.out.println(line);
					System.out.println(line.substring(11));
					setRoomTitle(line.substring(11));  
					
				}
				
				if(line.startsWith("[RoomPeopleCount]"))
				{
					setPeopleCount(line.substring(17));
					
				}
				
				if(line.startsWith("[RoomIdList]"))
				{
					setIdList(line.substring(12));
//			       CatchmindDriver.getDos().writeUTF("[EndSetRoomInfo]");

					
				}
				if(line.startsWith("[RoomNum]"))
				{
					setRoomNum(line.substring(9));
				}
				if(line.startsWith("[EndSetInfo]"))
				{
//					getRoomInfoframe().setVisible(true);
				}
				
				
				if(line.startsWith("[Clientlist]"))	// [Clientlist] �� �����ϴ� �޽����� ������ ����� ���
				{
					idlistpanel.idlistarea.setText("");
					idlistpanel.idlistarea.append(line.substring(12)+"\n");
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
			int row = -1;
			row = roompanel.roomtable.getSelectedRow();		// ������ ���� �������� ���� ���
			if (row == -1)
			{
				JOptionPane.showMessageDialog(join, "���� �������ּ���",
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
			row = roompanel.roomtable.getSelectedRow();		// ������ ���� �������� ���� ���
			if (row == -1)
			{
				JOptionPane.showMessageDialog(join, "���� �������ּ���",
						"ID Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(roompanel.roomtable.getValueAt(row, 2).equals("Full"))	// �� ���°� Full�̸� ���� �Ұ�
			{
				JOptionPane.showMessageDialog(join, "���̲�á���ϴ�",
						"ID Error", JOptionPane.ERROR_MESSAGE);
			}
			else	// �� ���°� Full�� �ƴϸ� ������ ������ ��û
			{
				msg = "[EnterRoom] " + roompanel.roomtable.getValueAt(row, 0);		//������ ���ȣ�� ����
				System.out.println("msg");
				try {
					CatchmindDriver.getDos().writeUTF(msg);			//[Enter Room] ���ȣ �޽����� ������ ����
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
