package Client;


import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import javax.swing.*;
import javax.swing.border.*;

public class LoginPanel extends JPanel implements ActionListener{
	SignUpFrame signUp;
	
	private int WIDTH = 380;
	private int HEIGHT = 180;
	private boolean LoginCheck=false; //로그인되어있는지 확인하는 변수
	public TextField IDText;
	public TextField PasswordText;
	JLabel IDLabel;
	JLabel PasswordLabel;
	JButton ForgotID;
	JButton ForgotPS;
	JButton Login;
	JButton SignUp;
	
	public LoginPanel(){
		this.setBorder(new TitledBorder(new EtchedBorder(),"Login"));
		this.setSize(new Dimension(WIDTH,HEIGHT));
		this.setLayout(null);
		
		signUp = new SignUpFrame();
		IDLabel = new JLabel("ID",10);
		PasswordLabel = new JLabel("Password",10);
		IDText = new TextField(20);
		PasswordText = new TextField(20);
		Login = new JButton("Login");
		ForgotID = new JButton("ID 찾기");
		ForgotPS = new JButton("비밀번호 찾기");
		SignUp = new JButton("SignUp");
		
		IDLabel.setBounds(50, 45, 70, 30);
		IDText.setBounds(140, 50, 100, 20);
		PasswordLabel.setBounds(50, 75, 70, 30);
		PasswordText.setBounds(140, 80, 100, 20);
		Login.setBounds(270, 80, 80, 20);
		ForgotID.setBounds( 40, 120, 80, 20);
		ForgotPS.setBounds(130, 120, 120, 20);
		SignUp.setBounds(270, 120, 80, 20);
		
		// 패널에 컴포넌트들 추가하기
		this.add(IDLabel);
		this.add(PasswordLabel);
		this.add(ForgotID);
		this.add(ForgotPS);
		this.add(IDText);
		this.add(PasswordText);
		this.add(Login);
		this.add(SignUp);
		
		
		//ActionListener 생성
		SignUp.addActionListener(this);	
		Login.addActionListener(this);
		ForgotID.addActionListener(this);
		ForgotPS.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Component event = (Component)e.getSource();
			
			// 회원가입을 클릭했을 때
			if(event == SignUp){
				signUp.setVisible(true);
			
			// 아이디 찾기를 클릭 했을 때
			}else if(event == ForgotID ){
				DB_Controller.Login.FindID();
			// 비밀번호 찾기를 클릭 했을 때
			}else if(event == ForgotPS ){
				DB_Controller.Login.FindPassword();
				
				
			}else if(event == Login){
				String ID = IDText.getText().trim();
				String PW = PasswordText.getText().trim();
				//아이디 입력 텍스트에 빈칸인지 확인하고 빈칸이면 메시지 출력
				if(ID.length() == 0 || ID == null ){
					JOptionPane.showMessageDialog(Login, "아이디를 입력하세요",
							"ID Error", JOptionPane.ERROR_MESSAGE);
				// 비밀번호 텍스트가 빈칸인지 확인하고 빈칸이면 메시지 출력
				}else if(PW.length() == 0 || PW == null){
					JOptionPane.showMessageDialog(Login, "비밀번호를 입력하세요",
							"Password Error", JOptionPane.ERROR_MESSAGE);
				}else{
				// DB로 ID,Password 확인하는 작업
				// DB가 완성될때까지 임시로 아무값만 넣으면 로그인
					
				LoginCheck =true;
				}
			}
	}

	protected boolean IsLogin() {
		// TODO Auto-generated method stub
		if(LoginCheck == true)	return true;
		else return false;
	}

}
