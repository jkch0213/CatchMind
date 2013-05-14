package Server;
import java.io.*;
import java.net.*;
import java.util.LinkedList;
import java.util.Vector;

import javax.print.attribute.standard.Severity;

class Client extends Thread 
{
	protected Server svr;
	protected Socket socket;
	protected DataInputStream dis;
	protected DataOutputStream dos;
	private String id;				// client id�� "user1,user2,...(���Ӽ���)" �̴�.
	private int roomnum;			// ���ȣ�� ��Ÿ���� ���� �ʱⰪ -1(����)
	private String gameId;

	String state;					// �غ������� ���� ���¸� ��Ÿ��
	
	
	
	public Client(Server svr, Socket s) throws IOException
	{
		this.svr = svr;
		this.socket = s;
		dis = new DataInputStream(socket.getInputStream());
		dos = new DataOutputStream(socket.getOutputStream());

		this.id = this.socket.getInetAddress()+"_"+svr.getCount();	//client id�� "ip�ּ�_���� ����" �̴�.
		this.id = "user"+svr.getCount();
		roomnum = -1;

		state = "";		// ���� ������ �غ����� ���� ����
		
		
		try {
			dos.writeUTF("[ShowID]"+ id);	//������ client���� ID�� ����
			
			
			dos.writeUTF("[Information] "+ id + " �� ������ ȯ���մϴ�.");	//������ client���� ID�� ���� ������ ����
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
	
	public void sendToMe(String msg)		// this Ŭ������ ����ϰ� �ִ� client���� ����
	{
		try {
			this.dos.writeUTF(msg+" ");
			System.out.println(msg+"x");
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

				if(msg.startsWith("[Chat] ")) 	//[Chat] ���� �����ϴ� �޽����� ���� �濡�ִ� ����� ä�� (������ ���ȣ�� -1)
				{
					svr.clientcontroller.sendToRoom(roomnum, "[Chat] [ " + gameId + " ] : " + msg.substring(7));
						//[Chat] �� ������ id + �޽����� ä��â�� ����
				}
				else if(msg.startsWith("[login] "))
				{
					setGameId(msg.substring(8));
					msg="[login] "+id;
					sendToMe(msg);
					dos.writeUTF("[Roomlist]"+ svr.roomcontroller.totalRoom());	//roomlist�� ��� Room��ü �� ������ �޾Ƽ� ������ client�� ����
					svr.clientcontroller.updateIDlist();
					
				}
				else if(msg.startsWith("[MakeRoom] "))
				{
					
					
					msg = "[MakeRoom]" + svr.getRoomController().makeRoom(this,msg); 	//RoomCotroller�� ���� Room��ü�� ����
					sendToMe("[MadeRoom] "+id);
					svr.clientcontroller.sendToAll(msg);	//���� Room��ü�� ������ ��� client���� ����
				    svr.clientcontroller.sendToAll("[RoomSize]"+svr.getRoomController().RoomSize());
					msg = svr.roomcontroller.getPlayerIDlist(roomnum);		// ���� IDlist�� ������Ʈ
					svr.clientcontroller.sendToAll("[Roomlist]"+ svr.roomcontroller.totalRoom());
//					sendToMe("[PlayerIDlist] "+ msg);
//					sendToMe("[Information] "+ roomnum + "�� ���� ��������ϴ�.");
				}
				else if(msg.startsWith("[EnterRoom] "))
				{
					this.roomnum = Integer.parseInt(msg.substring(12));		// ���ȣ�� ������ ���ȣ�� ����
					msg += "\t"+svr.getRoomController().enterRoom(this);	// client�� ������ ���� playerlist�� �߰�
					svr.clientcontroller.sendToAll(msg);	// �濡 �����Ͽ� ����� ���� ������ ��� client���� ����
					
					msg="[SetGamePanel]";
					sendToMe(msg);
					//msg = svr.roomcontroller.getPlayerIDlist(roomnum);		// ���� �����Ͽ� ����� IDlist�� ������Ʈ
				//	svr.clientcontroller.sendToRoom(roomnum,"[PlayerIDlist] "+ msg);	
				//	sendToMe("[Information] "+roomnum+"�� ���� ���� �Ͽ����ϴ�.");
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
		} catch (IOException ex) { 
			System.out.println(id+" ("+ this.socket.getInetAddress() + ") " + "���� ����");
		}
		finally {
			svr.clientcontroller.clientlist.removeElement(this);	// ���� ������ this Client�� list���� ����
			System.out.println("���� ������ ��: "+svr.clientcontroller.getClientlist().size());
			svr.clientcontroller.updateIDlist();	// ������ ��� client���� ���� ���� client ID�� ������ IDlist����� ������Ʈ

			try {
				dis.close();
				dos.close();
				socket.close();
			} catch (IOException e) { e.printStackTrace(); }
		}  

	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

}