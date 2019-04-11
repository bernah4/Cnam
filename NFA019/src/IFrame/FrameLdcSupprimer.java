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
import Serie61.*;

public class FrameLdcSupprimer extends JFrame implements ActionListener {

	private TableArticles61 tabArt;
	private TableDesCommandes61 tabCde;
	private TableClient61 tabClient;
	private TableLigneDeCommande61<String> cde;
	private JTextField val = new JTextField(1);
	private JButton valid;
	private JButton cancel;
	private IoIcon es = new IoIcon();

	public FrameLdcSupprimer(TableArticles61 tabArt, TableDesCommandes61 tabCde, TableClient61 tabClient, TableLigneDeCommande61<String> cde) {

		this.tabArt = tabArt;
		this.tabCde = tabCde;
		this.tabClient = tabClient;
		this.cde = cde;
		String[][] data = cde.data(tabArt);
		String[] label = cde.label();
		setLayout(new GridBagLayout());
		setSize(800, 175+20*cde.taille());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Supprimer - LigneDeCommande");
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weighty = 0.15;
		c.weightx = 0.1;
		ImageIcon test = new ImageIcon(this.getClass().getResource("cart-icon-mini.png"));
		JLabel id = new JLabel("Choisisssez l'article a supprimer de la commande :");
		JLabel image = new JLabel(test);
		this.add(image, c);
		c.gridx = 2;
		c.weightx = 1;
		c.gridwidth = 3;
		this.add(id, c);
		JTable table = new JTable(data, label);
		c.gridy = 1;
		c.gridx = 0;
		c.weighty = 0.65;
		c.gridwidth = 4;
		table.setGridColor(Color.LIGHT_GRAY);
		table.getColumnModel().getColumn(0).setMaxWidth(75);
		table.getColumnModel().getColumn(1).setMaxWidth(500);
		table.getColumnModel().getColumn(2).setMaxWidth(75);
		table.getColumnModel().getColumn(3).setMaxWidth(60);
		table.getColumnModel().getColumn(4).setMaxWidth(110);
		table.getColumnModel().getColumn(5).setMaxWidth(70);
		table.getColumnModel().getColumn(6).setMaxWidth(70);
		table.getColumnModel().getColumn(7).setMaxWidth(70);
		table.getColumnModel().getColumn(8).setMaxWidth(75);
		this.add(table.getTableHeader(), c);
		c.gridy = 2;
		this.add(table, c);
		c.gridy = 3;
		c.gridwidth = 1;
		c.weighty = 0.2;
		c.weightx = 0;
		JLabel txt = new JLabel("Votre choix :");
		this.add(txt, c);
		c.gridx = 1;
		c.gridwidth = 3;
		c.weightx = 1;
		this.add(val, c);
		c.gridy = 4;
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
			new FrameGestionTableDesLignesDeCommandes61(tabArt, tabCde, tabClient, cde);
		} 
		else if (event.getActionCommand() == "Valider") 
		{
			String num = val.getText();
			setVisible(false);
			try {
				int code = Integer.parseInt(num); 
				setVisible(false);
				if (cde.retourner(code) == null) 
				{
					es.affiche("Article inexistant dans la commande !\n", new ImageIcon(this.getClass().getResource("alert-icon.png")));
					new FrameLdcSupprimer(tabArt, tabCde, tabClient, cde);
				} 
				else 
				{
					cde.supprimer(code);
					cde.setValeurHT(cde.prixTotal(tabArt));
					es.affiche("Article code "+code+" supprime de la commande !", new ImageIcon(this.getClass().getResource("valid-icon.png")));
					new FrameGestionTableDesLignesDeCommandes61(tabArt, tabCde, tabClient, cde);
				}
			} catch (NumberFormatException ne) {
				setVisible(false);
				es.affiche("Entrez une valeur numerique !", new ImageIcon(this.getClass().getResource("alert-icon.png")));
				new FrameLdcSupprimer(tabArt, tabCde, tabClient, cde);
			}
		}
	}
}
