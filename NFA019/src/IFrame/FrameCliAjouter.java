package IFrame;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

import IPane.IoIcon;
import Serie61.Client61;
import Serie61.LigneDeCommande61;
import Serie61.TableArticles61;
import Serie61.TableClient61;
import Serie61.TableDesCommandes61;
import Serie61.TableLigneDeCommande61;

public class FrameCliAjouter extends JFrame implements ActionListener {
	
	private TableDesCommandes61 tabCde;
	private TableClient61 tabClient;
	private JTextField val = new JTextField(1);
	private JTextField lieu = new JTextField(1);
	private JButton valid;
	private JButton cancel;
	private IoIcon es = new IoIcon();
	
	public FrameCliAjouter(TableClient61 tabClient, TableDesCommandes61 tabCde) {
		
		this.tabCde = tabCde;
		this.tabClient = tabClient;
		setLayout(new GridBagLayout());
		setSize(800, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Ajouter - Client");
		GridBagConstraints c = new GridBagConstraints();
		
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weighty = 0.15;
		c.weightx = 0.15;
		ImageIcon test = new ImageIcon(this.getClass().getResource("client-icon-mini.png"));
		JLabel id = new JLabel("Renseignez les champs du nouveau client :");
		JLabel image = new JLabel(test);
		this.add(image, c);
		c.gridx = 2;
		c.gridwidth = 3;
		c.weightx = 0.85;
		this.add(id, c);
		c.gridy = 2;
		c.gridx = 0;
		c.gridwidth = 1;
		c.weighty = 0.2;
		c.weightx = 0;
		JLabel txt = new JLabel("Nom au format NOM-Prenom :");
		this.add(txt, c);
		c.gridx = 1;
		c.gridwidth = 3;
		c.weightx = 1;
		this.add(val, c);
		c.gridy = 4;
		c.gridx = 0;
		c.gridwidth = 1;
		c.weighty = 0.2;
		c.weightx = 0;
		JLabel txt2 = new JLabel("Lieu de residence :");
		this.add(txt2, c);
		c.gridx = 1;
		c.gridwidth = 3;
		c.weightx = 1;
		this.add(lieu, c);
		c.gridy = 5;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.VERTICAL;
		valid = new JButton("Valider");
		cancel = new JButton("Annuler");
		valid.addActionListener(this);
		cancel.addActionListener(this);
		c.gridx = 2;
		c.anchor = GridBagConstraints.LINE_END;
		this.add(cancel, c);
		c.gridx = 3;
		c.anchor = GridBagConstraints.LINE_START;
		valid.setBackground(Color.BLUE);
		this.add(valid, c);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent event) {
		
		if (event.getActionCommand() == "Annuler") {
			setVisible(false);
			new FrameGestionTableDesClient61(tabClient, tabCde);
		}
		else if (event.getActionCommand() == "Valider") 
		{
			String num = val.getText();
			String artlieu = lieu.getText();
			setVisible(false);
			int numero = 0;
			String ret = "";
			do {
				ret = num.substring(0, 3) + numero;
				numero++;
			}while (tabClient.retourner(ret) != null);
			Client61 client = new Client61(num, artlieu, ret);
			tabClient.ajouter(client);
			es.affiche("Client ajoute !", new ImageIcon(this.getClass().getResource("valid-icon.png")));
			new FrameGestionTableDesClient61(tabClient, tabCde);
		}	
	}
}
