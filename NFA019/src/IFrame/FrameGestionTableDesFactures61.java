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

public class FrameGestionTableDesFactures61 extends JFrame implements ActionListener {
	
	private TableArticles61 tabArt;
	private TableDesCommandes61 tabCde;
	private TableFactures61 tabFact;
	
	private JButton add;
	private JButton sup;
	private JButton show;
	private JButton showprom;
	private JButton finish;
	private String numCde;
 
	private IoIcon es = new IoIcon();
	
	public FrameGestionTableDesFactures61(TableArticles61 tabArt, TableDesCommandes61 tabCde, TableFactures61 tabFact)
	{
	
		this.tabArt = tabArt;
		this.tabCde = tabCde;
		this.tabFact= tabFact;
		setLayout(new GridBagLayout());
		setSize(350, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		GridBagConstraints c = new GridBagConstraints();
		setTitle("FACTURES");
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.2;
		ImageIcon test = new ImageIcon(this.getClass().getResource("money-icon.png"));
		JLabel id = new JLabel("Menu de gestion des factures :");
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
		add = new JButton("Creer une facture");
		this.add(add, c);
		add.addActionListener(this);
		c.gridy = 2;
		sup = new JButton("Supprimer une facture");
		this.add(sup, c);
		sup.addActionListener(this);
		c.gridy = 3;
		show = new JButton("Afficher une facture");
		this.add(show, c);
		show.addActionListener(this);
		c.gridy = 4;
		showprom = new JButton("Afficher toutes les factures");
		this.add(showprom, c);
		showprom.addActionListener(this);
		c.gridy = 5;
		finish = new JButton("Fin");
		this.add(finish, c);
		finish.addActionListener(this);
		
		setLocationRelativeTo(null);
		setVisible(true);	
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == add) {
			setVisible(false);
			if (tabCde.tailleNonFacture() == 0) {
				es.affiche("Aucune comande non facturee en cours !", new ImageIcon(this.getClass().getResource("alert-icon.png")));
				new FrameGestionTableDesFactures61(tabArt, tabCde, tabFact);
			}
			else
				new FrameFacAjouter(tabArt, tabCde, tabFact);
		}
		if (event.getActionCommand() == "Supprimer une facture") {
			setVisible(false);
			if (tabFact.taille() == 0)
			{
				es.affiche("Aucune facture en cours !", new ImageIcon(this.getClass().getResource("alert-icon.png")));
				new FrameGestionTableDesFactures61(tabArt, tabCde, tabFact);
			}
			else
				new FrameFacSupprimer(tabArt, tabCde, tabFact);
		}
		if (event.getActionCommand() == "Afficher une facture") {
			setVisible(false);
			if (tabFact.taille() == 0)
			{
				es.affiche("aucune facture en cours !", new ImageIcon(this.getClass().getResource("alert-icon.png")));
				new FrameGestionTableDesFactures61(tabArt, tabCde, tabFact);
			}
			else
				new FrameFacAffiche(tabArt, tabCde, tabFact);
		}
		if (event.getActionCommand() == "Afficher toutes les factures") {
			setVisible(false);
			if (tabFact.taille() == 0)
			{
				es.affiche("aucune facture en cours !", new ImageIcon(this.getClass().getResource("alert-icon.png")));
				new FrameGestionTableDesFactures61(tabArt, tabCde, tabFact);
			}
			else
				new FrameFacAfficheTout(tabArt, tabCde, tabFact, tabFact.getTabFact().firstKey());
		}
		if (event.getActionCommand() == "Fin") {
			setVisible(false);
			new FrameZClientJava61(tabArt, tabCde, tabFact);
		}
	}

}
