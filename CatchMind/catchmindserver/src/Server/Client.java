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
	private String id;				// client id�� "user1,user2,...(���Ӽ���)" �̴�.
	private int roomnum;			// ���ȣ�� ��Ÿ���� ���� �ʱⰪ -1(����)

	String state;					// �غ������� ���� ���¸� ��Ÿ��
	String team;					// ���� ��Ÿ���� ���� Blue Red
	String ships;					// �踦 ���� ��ǥ�� ����
	
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
		ships = "";
		
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

				if(msg.startsWith("[Chat] ")) 	//[Chat] ���� �����ϴ� �޽����� ���� �濡�ִ� ����� ä�� (������ ���ȣ�� -1)
				{
					svr.clientcontroller.sendToRoom(roomnum, "[Chat] [ " + id + " ] : " + msg.substring(7));
						//[Chat] �� ������ id + �޽����� ä��â�� ����
				}
				else if(msg.startsWith("[GetNumID] "))
				{
					
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

}