package Client;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;

import DB_Controller.SignUp;

public class SignUpFrame extends JFrame implements ActionListener{
	
	public static final String NAMEText = null;
	private int WIDTH = 380;
	private int HEIGHT = 400;
	
	SignUp signUp;
	
	JLabel NameLabel;
	JLabel IDLabel;
	JLabel PassLabel;
	JLabel RPassLabel;
	JLabel RegiNumLabel;
	TextField NameText;
	public static TextField IDText;
	public TextField PassText;
	public TextField RPassText;
	public TextField RegiNumText;
	JButton IDCheck;
	JButton SignUp;
	JButton Close;
	
	int ID_CHECK;
	
	public SignUpFrame(){
		new TitledBorder(new EtchedBorder(),"Sign-Up");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);		// 우측 상단 X버튼을 눌러도 프레임이 닫히지 않음

		this.setSize(new Dimension(WIDTH,HEIGHT));

		this.setLayout(null);
		
		NameLabel = new JLabel("이름",10);
		IDLabel = new JLabel("아이디",10);
		PassLabel = new JLabel("비밀번호",10);
		RPassLabel = new JLabel("비밀번호 확인",10);
		RegiNumLabel = new JLabel("주민등록번호",10);
		NameText = new TextField(20);
		IDText = new TextField(20);
		PassText = new TextField(20);
		RPassText = new TextField(20);
		RegiNumText = new TextField(20);
		IDCheck = new JButton("중복확인");
		SignUp = new JButton("회원가입");
		Close = new JButton("닫기");
		
		NameLabel.setBounds(50, 45, 90, 30);
		IDLabel.setBounds(50, 85, 90, 30);
		PassLabel.setBounds(50, 125, 90, 30);
		RPassLabel.setBounds(50, 165, 90, 30);
		RegiNumLabel.setBounds(50, 205, 90, 30);
		NameText.setBounds(150, 50, 90, 20);
		IDText.setBounds(150, 90, 90, 20);
		PassText.setBounds(150, 130, 90, 20);
		RPassText.setBounds(150, 170, 90, 20);
		RegiNumText.setBounds(150, 210, 90, 20);
		IDCheck.setBounds(250, 90, 90, 20);
		SignUp.setBounds(80, 300, 90, 30);
		Close.setBounds(190, 300, 90, 30);
		
		this.add(NameLabel);
		this.add(IDLabel);
		this.add(PassLabel);
		this.add(RPassLabel);
		this.add(RegiNumLabel);
		this.add(NameText);
		this.add(IDText);
		this.add(PassText);
		this.add(RPassText);
		this.add(RegiNumText);
		this.add(IDCheck);
		this.add(SignUp);
		this.add(Close);
		
		IDCheck.addActionListener(this);
		SignUp.addActionListener(this);
		Close.addActionListener(this);
		
	}

	public void actionPerformed(ActionEvent e)
	{	
		Component event = (Component)e.getSource();
		
		if(event == Close)
		{
			this.setVisible(false);		
		}
		else if(event == IDCheck)
		{
			ID_CHECK = 1;
			// DB에서 ID 중복되는지 확인하는 부분
			DB_Controller.Login.IDCheck();
			
		}
		// 회원가입 버튼을 눌렀을 때
		else if(event == SignUp)
		{
			String n = NameText.getText().trim();
			String p = PassText.getText().trim();
			String rp = RPassText.getText().trim();
			String regnum = RegiNumText.getText().trim();
			
			//이름이 빈칸인지 확인
			if(n.length() == 0 || n == null){
			JOptionPane.showMessageDialog(NameText, "이름을 입력하세요.",
					"이름 입력", JOptionPane.INFORMATION_MESSAGE);
			}
			// 아이디 중복 체크했는지 확인
			else if(ID_CHECK == 0){
				JOptionPane.showMessageDialog(IDCheck, "아이디가 중복되는지 확인하세요",
						"아이디 중복 체크", JOptionPane.INFORMATION_MESSAGE);
			}
			// 비밀번호란이 빈 칸인지 확인
			else if(p.length() == 0 || p == null){
				JOptionPane.showMessageDialog(PassText, "비밀번호를 입력하세요",
						"비밀번호 확인", JOptionPane.INFORMATION_MESSAGE);
			}
			// 비밀번호 확인란이 빈 칸인지 확인
			else if(rp.length() == 0 || rp == null){
				JOptionPane.showMessageDialog(PassText, "비밀번호 확인을 입력하세요",
						"비밀번호 확인", JOptionPane.INFORMATION_MESSAGE);
			}
			// 비밀번호 두 개가 동일한지 확인
			else if(p != rp){
				JOptionPane.showMessageDialog(PassText, "비밀번호가 일치하지 않습니다.",
						"비밀번호 확인", JOptionPane.INFORMATION_MESSAGE);
			}
			// 주민등록번호가 6자리인지 확인
			else if(regnum.length() != 6 || regnum == null){
				JOptionPane.showMessageDialog(PassText, "주민번호 처음 6자리를 입력하세요",
						"주민등록번호 확인", JOptionPane.INFORMATION_MESSAGE);
			}
			//아이디 입력 텍스트에 빈칸인지 확인하고 빈칸이면 메시지 출력
			signUp = new SignUp();
		}
		
	}
}
