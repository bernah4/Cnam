package IFrame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import IPane.IoIcon;;

public class FrameIdentification extends JFrame implements ActionListener{
	
	private JTextField login;
	private JPasswordField pswd;
	private JButton valid;
	private IoIcon es = new IoIcon();

	public FrameIdentification()
	{
		JPanel panel1;
		JPanel panel2;
		JPanel panel3;
		setLayout(new GridLayout(3, 2));
		setSize(300,150);
		setTitle("Identification");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JLabel id = new JLabel("Login");
		login = new JTextField(10);
		panel1 = new JPanel();
		panel1.add(id);
		panel1.add(login);
		this.add(panel1);
		
		JLabel mdp = new JLabel("Password");
		pswd = new JPasswordField(10);
		panel2 = new JPanel();
		panel2.add(mdp);
		panel2.add(pswd);
		this.add(panel2);
		
		panel3 = new JPanel();
		valid = new JButton("Valider");
		panel3.add(valid);
		this.add(panel3);
		valid.addActionListener(this);

		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent event)
	{
		if (event.getSource() == valid)
		{
			String log = login.getText();
			char[] pswdr = pswd.getPassword();
			String pswdstring = convert(pswdr);
			if (valid(log, pswdstring))
			{
				setVisible(false);
				new FrameZClientJava61();
			}
			else
			{
				es.affiche("Valeus incorectes !",new ImageIcon(this.getClass().getResource("alert-icon.png")));
				raz();
			}
		}
	}
	
	public String convert(char[] tt)
	{
		String ret = "";
		for(int i = 0; i< tt.length; i++)
			ret = ret+tt[i];
		return ret;
	}
	
	public boolean valid(String name, String mdpas)
	{
		return name.equals("") && mdpas.equals("");
	}
	
	public void raz()
	{
		login.setText("");
		pswd.setText("");
	}

}
