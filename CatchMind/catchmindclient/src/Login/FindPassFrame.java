package Login;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.TextField;
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

public class FindPassFrame extends JFrame implements ActionListener {
	private int WIDTH = 300;
	private int HEIGHT = 150;
	
	JLabel NameLabel;
	JLabel IDLabel;
	JLabel RegiNumLabel;
	JButton FindPass;
	TextField NameText;
	TextField IDText;
	TextField RegiNumText;
	
	public FindPassFrame(){
		new TitledBorder(new EtchedBorder(), "비밀번호 찾기");
		this.setSize(new Dimension(WIDTH, HEIGHT));
		this.setLayout(null);
		
		JLabel NameLabel = new JLabel("이름");
		TextField NameText = new TextField();
		JLabel IDLabel = new JLabel("아이디");
		TextField IDText = new TextField();
		JLabel RegiNumLabel = new JLabel("주민번호");
		TextField RegiNumText = new TextField();
		JButton FindPass = new JButton("ID찾기");
		
		NameLabel.setBounds(20, 15, 70, 30);
		NameText.setBounds(90, 20, 80, 20);
		IDLabel.setBounds(20, 45, 70, 30);
		IDText.setBounds(90, 50, 80, 20);
		RegiNumLabel.setBounds( 20, 75, 70, 30);
		RegiNumText.setBounds(90, 80, 80, 20);
		FindPass.setBounds(190, 80, 80, 20);
		
		this.add(NameLabel);
		this.add(NameText);
		this.add(IDLabel);
		this.add(IDText);
		this.add(RegiNumLabel);
		this.add(RegiNumText);
		this.add(FindPass);
		FindPass.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		Component event = (Component) e.getSource();
		
		String name = NameText.getText().trim();
		String id = IDText.getText().trim();
		String reginum = RegiNumText.getText().trim();
		
		if(event == FindPass){
			//서버에게 비밀번호 찾기 보내기
			try {
				CatchmindDriver.getDos().writeUTF("[loginFindPass] " + name);
				CatchmindDriver.getDos().writeUTF("[loginFindPass] " + id);
				CatchmindDriver.getDos().writeUTF("[loginFindPass] " + reginum);
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
