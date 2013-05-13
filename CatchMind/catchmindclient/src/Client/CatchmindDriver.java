package Client;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class CatchmindDriver 
{

	private static String serverip;
	private static int port;
	private static Socket socket;
	private static DataInputStream dis;
	private static DataOutputStream dos;
	private static CatchmindFrame frame;
	
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		serverip= "localhost";			//������ �ּ�
		port = 9999;					//����� ��Ʈ ��ȣ		

		try {
			socket = new Socket(CatchmindDriver.getServerIp(), CatchmindDriver.getPort());


			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());

			frame= new CatchmindFrame(socket);

			frame.setVisible(true);
			

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "���� ���� ���� (IP�� ��Ʈ�� Ȯ�����ּ���.)");
		}

	}

	public static void exit() throws IOException
	{
		dis.close();
		dos.close();
		socket.close();
	}
	public static String getServerIp()
	{
		return serverip;
	}

	public static int getPort()
	{
		return port;
	}

	public static Socket getSocket()
	{
		return socket;
	}

	public static DataInputStream getDis()
	{
		return dis;
	}

	public static DataOutputStream getDos()
	{
		return dos;
	}

	public static CatchmindFrame getFrame()
	{
		return frame;
	}
}
