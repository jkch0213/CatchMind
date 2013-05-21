package Server;
import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.print.attribute.standard.Severity;
import DBController.Login;

class Client extends Thread 
{
	protected Server svr;
	protected Socket socket;
	protected DataInputStream dis;
	protected DataOutputStream dos;
	protected String id;				// client id는 "user1,user2,...(접속순서)" 이다.
	protected String pass;
	protected String name;
	protected String reginum;
	private int roomnum;			// 방번호를 나타내는 변수 초기값 -1(대기실)
	private String gameId;
	private String ReValue;
	
	Login loginController;
	StringTokenizer tokenizer1;
	String[] user = new String[10];
	
	String state;					// 준비중인지 현재 상태를 나타냄
	
	public Client(Server svr, Socket s) throws IOException, SQLException
	{
		this.svr = svr;
		this.socket = s;
		dis = new DataInputStream(socket.getInputStream());
		dos = new DataOutputStream(socket.getOutputStream());

		this.id = this.socket.getInetAddress()+"_"+svr.getCount();	//client id는 "ip주소_접속 순서" 이다.
		this.id = "user"+svr.getCount();
		roomnum = -1;

		state = "";		// 게임 시작을 준비하지 않은 상태
		loginController = new Login();
		
		try {
			dos.writeUTF("[ShowID]"+ id);	//접속한 client에게 ID를 전송
			
			
			dos.writeUTF("[Information] "+ id + " 님 접속을 환영합니다.");	//접속한 client에게 ID와 접송 정보를 보냄
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.start();
	}

	public void setRoomnum(int roomnum)
	{
		this.roomnum = roomnum;
	}

	public int getRoomnum()
	{
		return roomnum;
	}

	public String getID()
	{
		return id;
	}

	public DataOutputStream getDos()
	{
		return dos;
	}
	
	public void sendToMe(String msg)		// this 클래스와 통신하고 있는 client에게 보냄
	{
		try {
			this.dos.writeUTF(msg+" ");
			System.out.println(msg+" send to Client");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		String msg = null;
		
		try {   
			while(true)
			{
				msg = dis.readUTF();   
				System.out.println(id+" ("+ this.socket.getInetAddress() + ") " + msg);

				if(msg.startsWith("[Chat] ")) 	//[Chat] 으로 시작하는 메시지면 같은 방에있는 사람과 채팅 (대기실은 방번호가 -1)
				{
					svr.clientcontroller.sendToRoom(roomnum, "[Chat] [ " + gameId + " ] : " + msg.substring(7));
						//[Chat] 을 제외한 id + 메시지를 채팅창에 보냄
				}
				else if(msg.startsWith("[Login] "))
				{
					tokenizer1 = new StringTokenizer(msg.substring(8),"\t");
					int i=0;
					while(tokenizer1.hasMoreTokens()){
						user[i] = tokenizer1.nextToken();
						i++;
					}
					id = user[0];
					pass = user[1];
					ReValue = Login.Login(id,pass); 
					
					if(ReValue.equals("true")){
						msg="[Login]" + id;
						sendToMe(msg);
						dos.writeUTF("[Roomlist]"+ svr.roomcontroller.totalRoom());	//roomlist에 모든 Room객체 의 정보를 받아서 접속한 client에 보냄
						svr.clientcontroller.updateIDlist();
					}
					if(ReValue.equals("false")){
						msg="[LoginFail]";
						sendToMe(msg);
					}
				}
				/*** 13.05.17 Modified ***/
				else if(msg.startsWith("[LoginIDCheck] ")){
					id = msg.substring(15); 
					ReValue = Login.IDCheck(id);
					
					if(ReValue.equals("true")){
						msg = "[LoginIDCheck]true";
						sendToMe(msg);
					}else{
						msg = "[LoginIDCheck]false";
						sendToMe(msg);
					}
				}
				else if(msg.startsWith("[LoginFindID] ")){
					tokenizer1 = new StringTokenizer(msg.substring(14),"\t");
					int i=0;
					while(tokenizer1.hasMoreTokens()){
						user[i] = tokenizer1.nextToken();
						i++;
					}
					
					System.out.println(user[0] + user[1]);
					name = user[0];
					reginum = user[1];
					ReValue = Login.FindID(name,reginum);
					
					if(!ReValue.equals(null)){
						msg = "[LoginFindID]" + ReValue;
						sendToMe(msg);
					}else{
						msg = "[LoginFindID]";
						sendToMe(msg);
					}
				}
				else if(msg.startsWith("[LoginFindPass] ")){
					tokenizer1 = new StringTokenizer(msg.substring(16),"\t");
					int i=0;
					while(tokenizer1.hasMoreTokens()){
						user[i] = tokenizer1.nextToken();
						i++;
					}
					name = user[0];
					id = user[1];
					reginum = user[2];
					ReValue = Login.FindPass(name,id,reginum);
					if(!ReValue.equals(null)){
						msg = "[LoginFindPass]" + ReValue;
						sendToMe(msg);
					}else{
						msg = "[LoginFindPass]";
						sendToMe(msg);
					}
				}
				else if(msg.startsWith("[LoginSignUp] ")){
					tokenizer1 = new StringTokenizer(msg.substring(14),"\t");
					int i=0;
					while(tokenizer1.hasMoreTokens()){
						user[i] = tokenizer1.nextToken();
						i++;
					}
					name = user[0];
					id = user[1];
					pass = user[2];
					reginum = user[3];
					// 테스트
					System.out.println(name + id + pass + reginum);
					
					ReValue = Login.SignUp(name,id,pass,reginum);
					
					if(!ReValue.equals("true")){
						msg = "[LoginSignUp]";
						sendToMe(msg);
					}
				}
				/*** 13.05.17 Modified ***/
				else if(msg.startsWith("[MakeRoom] "))
				{
					
					
					msg = "[MakeRoom]" + svr.getRoomController().makeRoom(this,msg); 	//RoomCotroller를 통해 Room객체를 만듬
					sendToMe("[MadeRoom] "+id);
					svr.clientcontroller.sendToAll(msg);	//만든 Room객체의 정보를 모든 client에게 보냄
				    svr.clientcontroller.sendToAll("[RoomSize]"+svr.getRoomController().RoomSize());
					msg = svr.roomcontroller.getPlayerIDlist(roomnum);		// 방의 IDlist를 업데이트
					svr.clientcontroller.sendToAll("[Roomlist]"+ svr.roomcontroller.totalRoom());
//					sendToMe("[PlayerIDlist] "+ msg);
//					sendToMe("[Information] "+ roomnum + "번 방을 만들었습니다.");
				}
				else if(msg.startsWith("[EnterRoom] "))
				{
					this.roomnum = Integer.parseInt(msg.substring(12));		// 방번호를 입장한 방번호로 변경
					msg += "\t"+svr.getRoomController().enterRoom(this);	// client를 입장한 방의 playerlist에 추가
					svr.clientcontroller.sendToAll(msg);	// 방에 입장하여 변경된 방의 정보를 모든 client에게 보냄
					
					msg="[SetGamePanel]";
					sendToMe(msg);
					//msg = svr.roomcontroller.getPlayerIDlist(roomnum);		// 내가 입장하여 변경된 IDlist를 업데이트
				//	svr.clientcontroller.sendToRoom(roomnum,"[PlayerIDlist] "+ msg);	
				//	sendToMe("[Information] "+roomnum+"번 방을 입장 하였습니다.");
				}
				else if(msg.startsWith("[RequestInfor]"))
				{
					int tempnum;
					tempnum=Integer.parseInt(msg.substring(14));
					msg ="[RoomTitle]"+ svr.roomcontroller.getRoomTitle(tempnum);
					sendToMe(msg);
					msg ="[RoomPeopleCount]"+ svr.roomcontroller.getRoomPeopleCount(tempnum);
					sendToMe(msg);
					msg ="[RoomIdList]"+ svr.roomcontroller.getRoomIdList(tempnum);
					sendToMe(msg);
					msg ="[RoomNum]" +svr.roomcontroller.getRoomNum(tempnum);
					sendToMe(msg);
				}
				else if(msg.startsWith("[EndSetRoomInfo]"))
				{
					sendToMe(msg);
				}
			}
		} catch (IOException | SQLException ex) { 
			System.out.println(id+" ("+ this.socket.getInetAddress() + ") " + "접속 해지");
		}
		finally {
			svr.clientcontroller.clientlist.removeElement(this);	// 접속 해제한 this Client를 list에서 제거
			System.out.println("현재 접속자 수: "+svr.clientcontroller.getClientlist().size());
			svr.clientcontroller.updateIDlist();	// 접속한 모든 client에게 접속 해제 client ID를 제외한 IDlist결과를 업데이트

			try {
				dis.close();
				dos.close();
				socket.close();
			} catch (IOException e) { e.printStackTrace(); }
		}  
	}

	public void setId(String id) {
		this.id = id;
	}
}