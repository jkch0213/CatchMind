package Server;

import java.io.IOException;
import java.util.Vector;

public class ClientController 
{
	protected Vector<Client> clientlist;
	
	public ClientController() throws IOException
	{
		clientlist = new Vector<Client>();
	}
	
	public Vector<Client> getClientlist()
	{
		return clientlist;
	}

	public void sendTo(int i, String msg)		// �ش� Client ���� �޽����� ����
	{
		try {
			clientlist.get(i).getDos().writeUTF(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendToAll(String msg)		// ������ ��� Client ���� �޽����� ����
	{
		for(int i=0;i<clientlist.size();i++)
		{
			sendTo(i,msg);
		}
	}
	
	public void sendToRoom(int roomnum, String msg)		// ���� �濡 �ִ� ����� ä�� (ä���� ���ȣ�� -1)
	{
		for(int i=0;i<clientlist.size();i++)
		{
			if(clientlist.get(i).getRoomnum() == roomnum)
			{
				sendTo(i,msg);
			}
		}
	}
	
	public void sendToEnemy(int roomnum, String msg)		// ���� �濡 �ִ� ����� ä�� (ä���� ���ȣ�� -1)
	{
		for(int i=0;i<clientlist.size();i++)
		{
			if(clientlist.get(i).getRoomnum() == roomnum)
			{
				sendTo(i,msg);
			}
		}
	}

	public void sendToOne(String id, String msg)		// �ش� ���̵� ���� ������� �޽����� ����
	{
		for(int i=0;i<clientlist.size();i++)
		{
			if(clientlist.get(i).getID().equals(id))
			{
				sendTo(i,msg);
			}
		}
	}
	
	public void updateIDlist()				//������ ����� ��� client���� ����
	{
		String msg = "[Clientlist] ������ �� : " + clientlist.size()+"\n\n";

		for(int i=0;i<clientlist.size();i++)
		{
			msg+=clientlist.get(i).getGameId()+"\n";		//��� ������ ���
		}
		sendToAll(msg);
	}
}
