package Login;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import Client.CatchmindDriver;

public class FindIDFrame extends JFrame implements ActionListener  {
	
	private int WIDTH = 300;
	private int HEIGHT = 150;
	
	JLabel NameLabel;
	JLabel RegiNumLabel;
	JButton FindID;
	TextField NameText;
	TextField RegiNumText;
	
	public FindIDFrame(){
		new TitledBorder(new EtchedBorder(), "ID 찾기");
		this.setSize(new Dimension(WIDTH, HEIGHT));
		this.setLayout(null);
		
		JLabel NameLabel = new JLabel("이름");
		TextField NameText = new TextField();
		JLabel RegiNumLabel = new JLabel("주민번호");
		TextField RegiNumText = new TextField();
		JButton FindID = new JButton("ID찾기");
		
		NameLabel.setBounds(20, 15, 70, 30);
		NameText.setBounds(90, 20, 80, 20);
		RegiNumLabel.setBounds( 20, 55, 70, 30);
		RegiNumText.setBounds(90, 60, 80, 20);
		FindID.setBounds(190, 60, 80, 20);
		
		this.add(NameLabel);
		this.add(NameText);
		this.add(RegiNumLabel);
		this.add(RegiNumText);
		this.add(FindID);
		FindID.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		Component event = (Component) e.getSource();
		
		String name = NameText.getText().trim();
		String reginum = RegiNumText.getText().trim();
		
		if(event == FindID){
			//서버에게 이름과 주민번호를 보내 아이디를 찾음
			try {
				CatchmindDriver.getDos().writeUTF("[loginFindPass] " + name);
				CatchmindDriver.getDos().writeUTF("[loginFindPass] " + reginum);
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
