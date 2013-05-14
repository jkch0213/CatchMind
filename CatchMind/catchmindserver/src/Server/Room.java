package Server;

import java.util.Vector;

public class Room 
{
	private String number;		// ���ȣ
	private String title;		// ������
	private String max;			// �濡 ���� �� �ִ� �� �ο� ��
	
	private String condition;		// �濡 ���� �� �ο� / �� �ο� ������ String

	protected Vector<Client> playerlist;		// �濡 ������ client��

	private int count;			// ���� ���� ��� count

	public Room(Client player, String number, String title, String max)
	{
		playerlist = new Vector<Client>();
		playerlist.add(player);		//�濡 ������ client�� list�� �߰��Ѵ�.

		this.number = number;
		this.title = title;
		this.max = max;
		
		this.condition = playerlist.size() + " / " + max;

		count = 0;
	}

	public String getNumber()
	{
		return number;
	}

	public String getString() 
	{
		return number + "\t" + title + "\t" + condition ;
	}
	public void updateCondition()
	{
		if (playerlist.size() == Integer.parseInt(max)) this.condition = "Full";	// �濡 ������ ���� max���� ������ Full�� ��Ÿ��
		else if (playerlist.size() == 0) this.condition = "null";		// �濡�� ��� �����ϸ� null�� ǥ��
		else this.condition = playerlist.size() + " / " + max;		// max���� ���� ������ ������ �� / max�� ��Ÿ��
	}
	public String getCondition()
	{
		return condition;
	}

	
	
	public String getPlayerIDlist()		// ���� ������ ��� playerlist�� ���¸� ��ȯ
	{
		String result = number +"  �� : " + title + "\n";
		
		result += "���� �ο� : " + max+"  ("+playerlist.size()+"�� ����)"+ "\n\n";

		for(int i=0;i<playerlist.size();i++)
		{
//			result += playerlist.get(i).getID()+ " / " +playerlist.get(i).team +"�� / "+playerlist.get(i).state+"\n";
		}
		return result;
	}
	
	public String getRoomNum()
	{
		String result =number ;
		return result;
	}
	public String getRoomTitle()
	{
		String result =title ;
		
		
		return result;
	}
	public String getRoomPeopleCount()
	{
		String result = "";
		result+=max+"  /"+playerlist.size()+"�� ����";
		return result;
	}

    public String getRoomIdList()
    {
    	String result ="";
    	for(int i=0;i<playerlist.size();i++)
		{
			result += playerlist.get(i).getGameId()+ "/";
		}
    	return result;
    }


	public String nextTurn()		// ���� ���� player ID�� ��ȯ
	{
		int turn = count % playerlist.size();		// ���� count�� playerlist����ŭ ���� �������� ���
		count++;

		return playerlist.get(turn).getID();		// �ش� ���� ID�� ��ȯ
	}
}
