package Server;
import java.io.*;
import java.net.*;
import java.util.LinkedList;
import java.util.Vector;

class Client extends Thread 
{
	protected Server svr;
	protected Socket socket;
	protected DataInputStream dis;
	protected DataOutputStream dos;
	private String id;				// client id는 "user1,user2,...(접속순서)" 이다.
	private int roomnum;			// 방번호를 나타내는 변수 초기값 -1(대기실)

	String state;					// 준비중인지 현재 상태를 나타냄
	String team;					// 팀을 나타내는 변수 Blue Red
	String ships;					// 배를 놓은 좌표를 받음
	
	public Client(Server svr, Socket s) throws IOException
	{
		this.svr = svr;
		this.socket = s;
		dis = new DataInputStream(socket.getInputStream());
		dos = new DataOutputStream(socket.getOutputStream());

		this.id = this.socket.getInetAddress()+"_"+svr.getCount();	//client id는 "ip주소_접속 순서" 이다.
		this.id = "user"+svr.getCount();
		roomnum = -1;

		state = "";		// 게임 시작을 준비하지 않은 상태
		ships = "";
		
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
			this.dos.writeUTF(msg);
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
					svr.clientcontroller.sendToRoom(roomnum, "[Chat] [ " + id + " ] : " + msg.substring(7));
						//[Chat] 을 제외한 id + 메시지를 채팅창에 보냄
				}
				else if(msg.startsWith("[GetNumID] "))
				{
					
				}

				
				
			}
		} catch (IOException ex) { 
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

}