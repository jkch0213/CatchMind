package Client;

import java.awt.Dimension;
import java.awt.TextArea;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class IDlistPanel extends JPanel
{
	private int WIDTH = 200;
	private int HEIGHT = 270;

	TextArea idlistarea;

	public IDlistPanel()
	{

		this.setSize(new Dimension(WIDTH,HEIGHT));
		this.setBorder(new TitledBorder(new EtchedBorder(),"����ID"));

		idlistarea = new TextArea(); 			// �޽����� �����ִ� ����
		idlistarea.setEditable(false);

		this.setLayout(null);

		idlistarea.setBounds(10, 20, 250, 230);

		this.add(idlistarea);
	}
}