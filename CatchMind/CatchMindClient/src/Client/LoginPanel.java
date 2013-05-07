package Client;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

public class LoginPanel extends JPanel {
	SignUp signUp;
	ButtonListener BL;
	
	private int WIDTH = 380;
	private int HEIGHT = 180;
	
	JLabel IDLabel;
	JLabel PasswordLabel;
	JLabel ForgotID;
	JLabel ForgotPS;
	TextField IDText;
	TextField PasswordText;
	JButton Login;
	JButton SignUp;
	
	public LoginPanel(){
		this.setBorder(new TitledBorder(new EtchedBorder(),"Login"));
		this.setSize(new Dimension(WIDTH,HEIGHT));
		this.setLayout(null);
		
		signUp = new SignUp();

		IDLabel = new JLabel("ID",10);
		PasswordLabel = new JLabel("Password",10);
		IDText = new TextField(20);
		PasswordText = new TextField(20);
		Login = new JButton("Login");
		ForgotID = new JLabel("Forgot ID");
		ForgotPS = new JLabel("ForgotPassword");
		SignUp = new JButton("SignUp");
		
		IDLabel.setBounds(50, 45, 70, 30);
		IDText.setBounds(140, 50, 100, 20);
		PasswordLabel.setBounds(50, 75, 70, 30);
		PasswordText.setBounds(140, 80, 100, 20);
		Login.setBounds(250, 80, 80, 20);
		ForgotID.setBounds( 50, 115, 100, 30);
		ForgotPS.setBounds(140, 115, 100, 30);
		SignUp.setBounds(250, 120, 80, 20);
		
		this.add(IDLabel);
		this.add(PasswordLabel);
		this.add(ForgotID);
		this.add(ForgotPS);
		this.add(IDText);
		this.add(PasswordText);
		this.add(Login);
		this.add(SignUp);
		SignUp.setVisible(true);
		
		BL = new ButtonListener();
		//ActionListener »ý¼º
		SignUp.addActionListener(BL);		
	}
	
	public class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			signUp.setVisible(true);

			
		}
	}

}
