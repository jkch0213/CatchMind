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
	
	public int indexRoom(int roomnum)			// 방번호를 통해 list의 인텍스를 반환
	{
		for(int i=0;i<roomlist.size();i++)
		{
			if(roomlist.get(i).getNumber().equals(roomnum+"")) return i;
		}
		return -1;		// 방번호가 없으면 -1 오류값을 반환
	}
	
	

	public String enterRoom(Client player)
	{
		int i = indexRoom(player.getRoomnum());			// 해당 방번호로 roomlist의 index를 받아옴 
		roomlist.get(i).playerlist.add(player);			// 방에 입장한 client를 playerlist에 추가
		roomlist.get(i).updateCondition();				// 인원 형황을 변경

		return roomlist.get(i).getCondition();			// 방의 입장 현황을 반환
	}
	
	public String exitRoom(Client player)
	{
		int i = indexRoom(player.getRoomnum());			// 해당 방번호로 roomlist의 index를 받아옴 
		roomlist.get(i).playerlist.remove(player);			// client를 playerlist에서 제거
		roomlist.get(i).updateCondition();				// 인원 형황을 변경

		if(roomlist.get(i).getCondition().equals("null"))		//만약 입장현황이 null이면 리스트에서 방을 지움.
		{
			roomlist.remove(i);
			return "null";
		}
		return roomlist.get(i).getCondition();		// 방의 입장 현황을 반환
	}
	
	public Vector<Room> getRoomlist()
	{
		return roomlist;
	}


	
	

	public String nextTurn(int roomnum)
	{
		int i = indexRoom(roomnum);
		return roomlist.get(i).nextTurn();	// 해당 방의 다음턴의 아이디를 받아옴
	}
	
	public String getPlayerIDlist(int roomnum)
	{
		int i = indexRoom(roomnum);
		return roomlist.get(i).getPlayerIDlist();	// 해당 방의 정보와 모든 playerlist의 상태를 반환
	}
	
	
}
