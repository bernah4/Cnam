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
import Utils.DateUser;

public class FrameGestionTableDesCommandes61 extends JFrame implements ActionListener {
	
	private TableArticles61 tabArt;
	private TableDesCommandes61 tabCde;
	private TableClient61 tabClient;
	
	private JButton add;
	private JButton sup;
	private JButton show;
	private JButton showprom;
	private JButton modif;
	private JButton finish;
	private String numCde;
 
	private IoIcon es = new IoIcon();
	
	public FrameGestionTableDesCommandes61(TableArticles61 tabArt, TableDesCommandes61 tabCde, TableClient61 tabClient)
	{
	
		this.tabArt = tabArt;
		this.tabCde = tabCde;
		this.tabClient = tabClient;
		this.numCde = prochainNumero(tabCde);
		setLayout(new GridBagLayout());
		setSize(350, 350);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		GridBagConstraints c = new GridBagConstraints();
		setTitle("COMMANDES");
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.2;
		ImageIcon test = new ImageIcon(this.getClass().getResource("product-icon.png"));
		JLabel id = new JLabel("Menu de gestion des commandes :");
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
		add = new JButton("Creer la commande nº "+numCde);
		this.add(add, c);
		add.addActionListener(this);
		c.gridy = 2;
		sup = new JButton("Supprimer une commande");
		this.add(sup, c);
		sup.addActionListener(this);
		c.gridy = 3;
		show = new JButton("Afficher une commande");
		this.add(show, c);
		show.addActionListener(this);
		c.gridy = 4;
		showprom = new JButton("Afficher toutes les commandes");
		this.add(showprom, c);
		showprom.addActionListener(this);
		c.gridy = 5;
		modif = new JButton("Modifier une commande");
		this.add(modif, c);
		modif.addActionListener(this);
		c.gridy = 6;
		finish = new JButton("Fin");
		this.add(finish, c);
		finish.addActionListener(this);
		
		setLocationRelativeTo(null);
		setVisible(true);	
	}

	public String prochainNumero(TableDesCommandes61 tabCde) {
		
		int numero = 1;
		DateUser date = new DateUser();
		String cle = ""+date.getAnnee() + date.getMois()+ date.getJour();
		String ret;
		do{
			ret = cle + numero;
			numero++;		
		}while(tabCde.retourner(ret) != null);
		return ret;
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == add) {
			setVisible(false);
			new FrameCdeAjouter(tabArt, tabCde, tabClient, numCde);
		}
		if (event.getActionCommand() == "Supprimer une commande") {
			setVisible(false);
			if (tabCde.tailleFacture() == 0)
			{
				es.affiche("Aucune comande deja facturee en cours !", new ImageIcon(this.getClass().getResource("alert-icon.png")));
				new FrameGestionTableDesCommandes61(tabArt, tabCde, tabClient);
			}
			else
				new FrameCdeSupprimer(tabArt, tabCde, tabClient);
		}
		if (event.getActionCommand() == "Afficher une commande") {
			setVisible(false);
			if (tabCde.taille() == 0)
			{
				es.affiche("aucune commande en cours\n", new ImageIcon(this.getClass().getResource("alert-icon.png")));
				new FrameGestionTableDesCommandes61(tabArt, tabCde, tabClient);
			}
			else
				new FrameCdeAffiche(tabArt, tabCde, tabClient);
		}
		if (event.getActionCommand() == "Afficher toutes les commandes") {
			setVisible(false);
			if (tabCde.taille() == 0)
			{
				es.affiche("aucune commande en cours\n", new ImageIcon(this.getClass().getResource("alert-icon.png")));
				new FrameGestionTableDesCommandes61(tabArt, tabCde, tabClient);
			}
			else
				new FrameCdeAfficheTout(tabArt, tabCde, tabClient, tabCde.getTabDesCom().firstKey());
		}
		if (event.getActionCommand() == "Modifier une commande") {
			setVisible(false);
			new FrameCdeModifier(tabArt, tabCde, tabClient);
		}
		if (event.getActionCommand() == "Fin") {
			setVisible(false);
			new FrameZClientJava61(tabArt, tabCde, tabClient);
		}
	}

}
