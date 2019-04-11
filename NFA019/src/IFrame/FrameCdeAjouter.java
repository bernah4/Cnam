package IFrame;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

import IPane.IoIcon;
import Serie61.*;

public class FrameCdeAjouter extends JFrame implements ActionListener {

	private TableArticles61 tabArt;
	private TableDesCommandes61 tabCde;
	private TableClient61 tabClient;
	private String numcde;
	private JButton valid;
	private JButton cancel;
	private JComboBox<String> choix;
	private IoIcon es = new IoIcon();

	public FrameCdeAjouter(TableArticles61 tabArt, TableDesCommandes61 tabCde, TableClient61 tabClient, String numcde) {

		this.tabArt = tabArt;
		this.tabCde = tabCde;
		this.tabClient = tabClient;
		this.numcde = numcde;
		setLayout(new GridBagLayout());
		setSize(450, 150);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Ajouter - Commande");
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weighty = 0.15;
		c.weightx = 0.1;
		ImageIcon test = new ImageIcon(this.getClass().getResource("product-icon-mini.png"));
		JLabel id = new JLabel("Choisisssez le client qui effectue la commande:");
		JLabel image = new JLabel(test);
		this.add(image, c);
		c.gridx = 2;
		c.weightx = 1;
		c.gridwidth = 3;
		this.add(id, c);
		
		c.gridy = 1;
		c.gridx = 0;		
		c.gridwidth = 1;
		c.weightx = 0;
		JLabel txt = new JLabel("Champ a modifier");
		this.add(txt, c);
		c.gridx = 1;
		c.weightx = 1;
		c.gridwidth = 3;
		choix = new JComboBox<String>(tabClient.listNum());
		choix.setSelectedIndex(0);
		this.add(choix, c);
		choix.addActionListener(this);
		
		c.gridy = 2;
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

		if (event.getActionCommand() == "Annuler") 
		{
			setVisible(false);
			new FrameGestionTableDesCommandes61(tabArt, tabCde, tabClient);
		} 
		else if (event.getActionCommand() == "Valider") 
		{
			String numcl = (String)choix.getSelectedItem();
			setVisible(false);
			TableLigneDeCommande61<String> cde = new TableLigneDeCommande61<String>();
			cde.setNumcommande(numcde);
			cde.setNumclient(numcl);
			new FrameGestionTableDesLignesDeCommandes61(tabArt, tabCde, tabClient, cde);			
		}
	}
}
