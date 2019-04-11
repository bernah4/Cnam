package IFrame;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import IPane.IoIcon;
import Serie61.*;

public class FrameGestionTableDesLignesDeCommandes61 extends JFrame implements ActionListener {
	
	private TableArticles61 tabArt;
	private TableDesCommandes61 tabCde;
	private TableClient61 tabClient;
	private TableLigneDeCommande61<String> cde;
	
	private JButton add;
	private JButton sup;
	private JButton show;
	private JButton modif;
	private JButton finish;
	private IoIcon es = new IoIcon();
	
	public FrameGestionTableDesLignesDeCommandes61(TableArticles61 tabArt, TableDesCommandes61 tabCde, TableClient61 tabClient, TableLigneDeCommande61<String> cde)
	{
	
		this.tabArt = tabArt;
		this.tabCde = tabCde;
		this.tabClient = tabClient;
		this.cde = cde;
		setLayout(new GridBagLayout());
		setSize(350, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		GridBagConstraints c = new GridBagConstraints();
		setTitle("UNE COMMANDE");
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.2;
		ImageIcon test = new ImageIcon(this.getClass().getResource("cart-icon.png"));
		JLabel id = new JLabel("Menu de gestion de la commande "+cde.getNumcommande()+" :");
		JLabel image = new JLabel(test);
		this.add(image, c);
		c.gridx = 2;
		c.gridwidth = 3;
		c.weightx = 0.8;
		this.add(id, c);
		c.fill = GridBagConstraints.BOTH;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 12;
		add = new JButton("Ajouter un article a la commande");
		this.add(add, c);
		add.addActionListener(this);
		c.gridy = 2;
		sup = new JButton("Supprimer un article a la commande");
		this.add(sup, c);
		sup.addActionListener(this);
		c.gridy = 3;
		show = new JButton("Afficher la commande");
		this.add(show, c);
		show.addActionListener(this);
		c.gridy = 4;
		modif = new JButton("Modifier un article a la commande");
		this.add(modif, c);
		modif.addActionListener(this);
		c.gridy = 5;
		finish = new JButton("Fin");
		this.add(finish, c);
		finish.addActionListener(this);
		
		setLocationRelativeTo(null);
		setVisible(true);	
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand() == "Ajouter un article a la commande") {
			setVisible(false);
			new FrameLdcAjouter(tabArt, tabCde, tabClient, cde);
		}
		if (event.getActionCommand() == "Supprimer un article a la commande") {
			setVisible(false);
			if (cde.taille() == 0)
			{
				es.affiche("Aucun article dans la commande !", new ImageIcon(this.getClass().getResource("alert-icon.png")));
				new FrameGestionTableDesLignesDeCommandes61(tabArt, tabCde, tabClient, cde);
			}
			else
				new FrameLdcSupprimer(tabArt, tabCde, tabClient, cde);
		}
		if (event.getActionCommand() == "Afficher la commande") {
			setVisible(false);
			if (cde.taille() == 0)
			{
				es.affiche("Aucun article dans la commande !", new ImageIcon(this.getClass().getResource("lert-icon.png")));
				new FrameGestionTableDesLignesDeCommandes61(tabArt, tabCde, tabClient, cde);
			}
			else
				new FrameLdcAffiche(tabArt, tabCde, tabClient, cde);
		}
		if (event.getActionCommand() == "Modifier un article a la commande") {
			setVisible(false);
			new FrameLdcModifier(tabArt, tabCde, tabClient, cde);
		}
		if (event.getActionCommand() == "Fin") {
			setVisible(false);
			if (cde.taille() > 0)
			{
				tabCde.ajouter(cde);
				Client61 cl = tabClient.retourner(cde.getNumclient());
				cl.setChiffre_affaire(cl.getChiffre_affaire()+(cde.getValeurHT()*1.196f*100)/100.0f);
				cl.setNb_cde(cl.getNb_cde() + 1);
			}
			new FrameGestionTableDesCommandes61(tabArt, tabCde, tabClient);
		}
	}

}
