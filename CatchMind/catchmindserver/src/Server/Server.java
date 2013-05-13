package Server;
import java.net.*;

public class Server 
{
	protected ClientController clientcontroller;
	protected RoomController roomcontroller;
	
	public static int inPort=9999;
	public static ServerSocket server = null;
	private int count;
	
	public static void main(String[] args) { 

		Server svr = new Server();  

	}

	public Server () {  
		try {
			server = new ServerSocket (inPort);
			
			clientcontroller = new ClientController();
			roomcontroller = new RoomController();

			System.out.println("Server start..");

			// ServerSocket listening
			while(true) {	
				Socket socket = server.accept();

				count++;
				System.out.println(count + " ��°  ������ : " + socket.getInetAddress());

				Client client = new Client(this,socket);		//���ο� client�� �����ϸ� ��� clientlist�� �߰�
				clientcontroller.getClientlist().addElement(client);
				
				System.out.println("���� ������ ��: "+clientcontroller.getClientlist().size());

				clientcontroller.updateIDlist();	//���ο� client�� �����ϸ� ��� client���� ������ ID list�� ������Ʈ
			}
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	public RoomController getRoomController()
	{
		return roomcontroller;
	}
	
	public int getCount()
	{
		return count;
	}

} // class

