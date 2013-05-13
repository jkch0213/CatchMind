package Client;
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


public class ChatPanel extends JPanel implements ActionListener
{

	private int WIDTH = 700;
	private int HEIGHT = 500;
	
	public TextArea chatarea;
	private TextField inputmsg;
	
	
	public ChatPanel()
	{
		this.setSize(new Dimension(WIDTH,HEIGHT));
		this.setBorder(new TitledBorder(new EtchedBorder(),"채팅"));

		chatarea = new TextArea(); 			// 메시지를 보여주는 영역
		chatarea.setEditable(false);

		inputmsg = new TextField();         // 보낼 메시지를 입력 하는 field
		inputmsg.addActionListener(this);

		this.setLayout(null);

		chatarea.setBounds(10, 20, 480, 180);
		inputmsg.setBounds(10, 200, 480, 20);

		this.add(chatarea);
		this.add(inputmsg);	
	}
	
	
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		String msg;

		if(event.getSource()==inputmsg)
		{
			msg=inputmsg.getText();
			try {
				CatchmindDriver.getDos().writeUTF("[Chat] "+msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
			inputmsg.setText("");
		}
		
	}

}
