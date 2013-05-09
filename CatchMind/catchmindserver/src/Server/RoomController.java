package Server;

import java.util.StringTokenizer;
import java.util.Vector;

public class RoomController 
{
	protected Vector<Room> roomlist;
	private StringTokenizer tokenizer;
	private String[] tokens = new String[5];
	private int roomnum;

	public RoomController()
	{
		roomlist = new Vector<Room>();
		roomnum = 0;
	}
	
	public int indexRoom(int roomnum)			// ���ȣ�� ���� list�� ���ؽ��� ��ȯ
	{
		for(int i=0;i<roomlist.size();i++)
		{
			if(roomlist.get(i).getNumber().equals(roomnum+"")) return i;
		}
		return -1;		// ���ȣ�� ������ -1 �������� ��ȯ
	}
	
	

	public String enterRoom(Client player)
	{
		int i = indexRoom(player.getRoomnum());			// �ش� ���ȣ�� roomlist�� index�� �޾ƿ� 
		roomlist.get(i).playerlist.add(player);			// �濡 ������ client�� playerlist�� �߰�
		roomlist.get(i).updateCondition();				// �ο� ��Ȳ�� ����

		return roomlist.get(i).getCondition();			// ���� ���� ��Ȳ�� ��ȯ
	}
	
	public String exitRoom(Client player)
	{
		int i = indexRoom(player.getRoomnum());			// �ش� ���ȣ�� roomlist�� index�� �޾ƿ� 
		roomlist.get(i).playerlist.remove(player);			// client�� playerlist���� ����
		roomlist.get(i).updateCondition();				// �ο� ��Ȳ�� ����

		if(roomlist.get(i).getCondition().equals("null"))		//���� ������Ȳ�� null�̸� ����Ʈ���� ���� ����.
		{
			roomlist.remove(i);
			return "null";
		}
		return roomlist.get(i).getCondition();		// ���� ���� ��Ȳ�� ��ȯ
	}
	
	public Vector<Room> getRoomlist()
	{
		return roomlist;
	}


	
	

	public String nextTurn(int roomnum)
	{
		int i = indexRoom(roomnum);
		return roomlist.get(i).nextTurn();	// �ش� ���� �������� ���̵� �޾ƿ�
	}
	
	public String getPlayerIDlist(int roomnum)
	{
		int i = indexRoom(roomnum);
		return roomlist.get(i).getPlayerIDlist();	// �ش� ���� ������ ��� playerlist�� ���¸� ��ȯ
	}
	
	
}
