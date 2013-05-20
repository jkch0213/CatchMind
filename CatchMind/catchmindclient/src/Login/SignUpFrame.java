package Login;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.border.*;

import Client.CatchmindDriver;
import DBController.SignUp;

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
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);		// ���� ��� X��ư�� ������ �������� ������ ����

		this.setSize(new Dimension(WIDTH,HEIGHT));

		this.setLayout(null);
		
		NameLabel = new JLabel("�̸�",10);
		IDLabel = new JLabel("���̵�",10);
		PassLabel = new JLabel("��й�ȣ",10);
		RPassLabel = new JLabel("��й�ȣ Ȯ��",10);
		RegiNumLabel = new JLabel("�ֹε�Ϲ�ȣ",10);
		NameText = new TextField(20);
		IDText = new TextField(20);
		PassText = new TextField(20);
		RPassText = new TextField(20);
		RegiNumText = new TextField(20);
		IDCheck = new JButton("�ߺ�Ȯ��");
		SignUp = new JButton("ȸ������");
		Close = new JButton("�ݱ�");
		
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
		String name = NameText.getText().trim();
		String id = IDText.getText().trim();
		String pass = PassText.getText().trim();
		String rpass = RPassText.getText().trim();
		String reginum = RegiNumText.getText().trim();
		
		if(event == Close)
		{
			this.setVisible(false);		
		}
		else if(event == IDCheck)
		{
			ID_CHECK = 1;
			// Server�� id�� ���� �ߺ��Ǵ� id�� �ִ��� Ȯ��
			try {
				CatchmindDriver.getDos().writeUTF("[loginIDCheck] " + id);
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		// ȸ������ ��ư�� ������ ��
		else if(event == SignUp)
		{
			//�̸��� ��ĭ���� Ȯ��
			if(name.length() == 0 || name == null){
			JOptionPane.showMessageDialog(NameText, "�̸��� �Է��ϼ���.",
					"�̸� �Է�", JOptionPane.INFORMATION_MESSAGE);
			}
			//���̵� ��ĭ���� Ȯ��
			else if(id.length() == 0 || id == null){
			JOptionPane.showMessageDialog(NameText, "���̵� �Է��ϼ���.",
					"���̵� �Է�", JOptionPane.INFORMATION_MESSAGE);
			}
			// ���̵� �ߺ� üũ�ߴ��� Ȯ��
			else if(ID_CHECK == 0){
				JOptionPane.showMessageDialog(IDCheck, "���̵� �ߺ��Ǵ��� Ȯ���ϼ���",
						"���̵� �ߺ� üũ", JOptionPane.INFORMATION_MESSAGE);
			}
			// ��й�ȣ���� �� ĭ���� Ȯ��
			else if(pass.length() == 0 || pass == null){
				JOptionPane.showMessageDialog(PassText, "��й�ȣ�� �Է��ϼ���",
						"��й�ȣ Ȯ��", JOptionPane.INFORMATION_MESSAGE);
			}
			// ��й�ȣ Ȯ�ζ��� �� ĭ���� Ȯ��
			else if(rpass.length() == 0 || rpass == null){
				JOptionPane.showMessageDialog(PassText, "��й�ȣ Ȯ���� �Է��ϼ���",
						"��й�ȣ Ȯ��", JOptionPane.INFORMATION_MESSAGE);
			}
			// ��й�ȣ �� ���� �������� Ȯ��
			else if(!pass.equals(rpass)){
				JOptionPane.showMessageDialog(PassText, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.",
						"��й�ȣ Ȯ��", JOptionPane.INFORMATION_MESSAGE);
			}
			// �ֹε�Ϲ�ȣ�� 6�ڸ����� Ȯ��
			else if(reginum.length() != 6 || reginum == null){
				JOptionPane.showMessageDialog(PassText, "�ֹι�ȣ ó�� 6�ڸ��� �Է��ϼ���",
						"�ֹε�Ϲ�ȣ Ȯ��", JOptionPane.INFORMATION_MESSAGE);
			}
			//���̵� �Է� �ؽ�Ʈ�� ��ĭ���� Ȯ���ϰ� ��ĭ�̸� �޽��� ���
			else {
				try {
					CatchmindDriver.getDos().writeUTF("[loginSignUp] " + name);
					CatchmindDriver.getDos().writeUTF("[loginSignUp] " + id);
					CatchmindDriver.getDos().writeUTF("[loginSignUp] " + pass);
					CatchmindDriver.getDos().writeUTF("[loginSignUp] " + reginum);

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
	}
}