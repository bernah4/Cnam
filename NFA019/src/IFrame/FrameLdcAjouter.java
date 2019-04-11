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
import Serie61.LigneDeCommande61;
import Serie61.TableArticles61;
import Serie61.TableClient61;
import Serie61.TableDesCommandes61;
import Serie61.TableLigneDeCommande61;

public class FrameLdcAjouter extends JFrame implements ActionListener {
	
	private TableArticles61 tabArt;
	private TableDesCommandes61 tabCde;
	private TableClient61 tabClient;
	private TableLigneDeCommande61<String> cde;
	private JTextField val = new JTextField(1);
	private JTextField qte = new JTextField(1);
	private JButton valid;
	private JButton cancel;
	private IoIcon es = new IoIcon();
	
	public FrameLdcAjouter(TableArticles61 tabArt, TableDesCommandes61 tabCde, TableClient61 tabClient, TableLigneDeCommande61<String> cde) {
		this.tabArt = tabArt;
		this.tabCde = tabCde;
		this.tabClient = tabClient;
		this.cde = cde;
		String [][] data = tabArt.data();
		String[] label = tabArt.label();
		setLayout(new GridBagLayout());
		setSize(600, 200+20*tabArt.taille());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Ajouter - LigneDeCommande");
		GridBagConstraints c = new GridBagConstraints();
		
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weighty = 0.15;
		c.weightx = 0.15;
		ImageIcon test = new ImageIcon(this.getClass().getResource("cart-icon-mini.png"));
		JLabel id = new JLabel("Choisissez l'article a ajouter :");
		JLabel image = new JLabel(test);
		this.add(image, c);
		c.gridx = 2;
		c.gridwidth = 3;
		c.weightx = 0.85;
		this.add(id, c);
		JTable table = new JTable(data, label);
		c.gridy = 1;
		c.gridx = 0;
		c.gridwidth = 4;
		c.weighty = 0.7;
		table.setGridColor(Color.LIGHT_GRAY);
		table.getColumnModel().getColumn(0).setMaxWidth(75);
		table.getColumnModel().getColumn(1).setMaxWidth(690);
		table.getColumnModel().getColumn(2).setMaxWidth(75);
		table.getColumnModel().getColumn(3).setMaxWidth(60);
		table.getColumnModel().getColumn(4).setMaxWidth(110);
		table.getColumnModel().getColumn(5).setMaxWidth(70);
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
		c.gridx = 0;
		c.gridwidth = 1;
		c.weighty = 0.2;
		c.weightx = 0;
		JLabel txt2 = new JLabel("Quantite :");
		this.add(txt2, c);
		c.gridx = 1;
		c.gridwidth = 3;
		c.weightx = 1;
		this.add(qte, c);
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
			new FrameGestionTableDesLignesDeCommandes61(tabArt, tabCde, tabClient, cde);
		}
		else if (event.getActionCommand() == "Valider") 
		{
			String num = val.getText();
			String artqte = qte.getText();
			setVisible(false);
			try {
				int code = Integer.parseInt(num);
				int nb = Integer.parseInt(artqte);
				setVisible(false);
				if (tabArt.retourner(code) == null) 
				{
					es.affiche("Article inexistant dans la table des articles !\n", new ImageIcon(this.getClass().getResource("alert-icon.png")));
					new FrameLdcSupprimer(tabArt, tabCde, tabClient, cde);
				} 
				else 
				{
					LigneDeCommande61 elem = new LigneDeCommande61(code, nb);
					LigneDeCommande61 exist = cde.retourner(elem.getCodearticle());
					if (exist == null) 
					{
						cde.ajouter(elem);
						es.affiche("article ajoute au panier !", new ImageIcon(this.getClass().getResource("valid-icon.png")));
					} 
					else 
					{
						exist.setQuantitecommande(exist.getQuantitecommande() + elem.getQuantitecommande());
						es.affiche("article additione aux articles existant !", new ImageIcon(this.getClass().getResource("valid-icon.png")));
					}
					cde.setValeurHT(cde.prixTotal(tabArt));
					new FrameGestionTableDesLignesDeCommandes61(tabArt, tabCde, tabClient, cde);
				}
			}
			 catch (NumberFormatException ne) {
				setVisible(false);
				es.affiche("Entrez une valeur numerique !", new ImageIcon(this.getClass().getResource("alert-icon.png")));
				new FrameLdcSupprimer(tabArt, tabCde, tabClient, cde);
			}
		}	
	}
}
