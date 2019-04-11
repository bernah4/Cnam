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

public class FrameGestionTableDesArticle61 extends JFrame implements ActionListener {
	
	private TableArticles61 tabArt;
	private TableDesCommandes61 tabCde;
	
	private JButton add;
	private JButton sup;
	private JButton show;
	private JButton showprom;
	private JButton modif;
	private JButton transfo;
	private JButton finish;
 
	private IoIcon es = new IoIcon();
	
	public FrameGestionTableDesArticle61(TableArticles61 tabArt, TableDesCommandes61 tabCde)
	{
	
		this.tabArt = tabArt;
		this.tabCde = tabCde;
		setLayout(new GridBagLayout());
		setSize(350, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		GridBagConstraints c = new GridBagConstraints();
		setTitle("ARTICLES");
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.2;
		ImageIcon test = new ImageIcon(this.getClass().getResource("good-icon.png"));
		JLabel id = new JLabel("Menu de gestion de la table des articles :");
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
		add = new JButton("Ajouter un article");
		this.add(add, c);
		add.addActionListener(this);
		c.gridy = 2;
		sup = new JButton("Supprimer un article");
		this.add(sup, c);
		sup.addActionListener(this);
		c.gridy = 3;
		show = new JButton("Afficher la table des articles");
		this.add(show, c);
		show.addActionListener(this);
		c.gridy = 4;
		showprom = new JButton("Afficher la table des articles en promotion");
		this.add(showprom, c);
		showprom.addActionListener(this);
		c.gridy = 5;
		modif = new JButton("Modifier un article");
		this.add(modif, c);
		modif.addActionListener(this);
		c.gridy = 6;
		transfo = new JButton("Transformer un article");
		this.add(transfo, c);
		transfo.addActionListener(this);
		c.gridy = 7;
		finish = new JButton("Fin");
		this.add(finish, c);
		finish.addActionListener(this);
		
		setLocationRelativeTo(null);
		setVisible(true);	
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand() == "Ajouter un article") {
			setVisible(false);
			new FrameArtAjouter(tabArt, tabCde);
		}
		if (event.getActionCommand() == "Supprimer un article") {
			setVisible(false);
			new FrameArtSupprimer(tabArt, tabCde);
		}
		if (event.getActionCommand() == "Afficher la table des articles") {
			setVisible(false);
			if (tabArt.taille() == 0)
			{
				es.affiche("aucun article en stock\n", new ImageIcon(this.getClass().getResource("alert-icon.png")));
				new FrameGestionTableDesArticle61(tabArt, tabCde);
			}
			else
				new FrameArtAffiche(tabArt, tabCde);
		}
		if (event.getActionCommand() == "Afficher la table des articles en promotion") {
			setVisible(false);
			if (tabArt.taille() == 0)
			{
				es.affiche("aucun article en stock\n", new ImageIcon(this.getClass().getResource("alert-icon.png")));
				new FrameGestionTableDesArticle61(tabArt, tabCde);
			}
			else
				new FrameArtAffichePromo(tabArt, tabCde);
		}
		if (event.getActionCommand() == "Modifier un article") {
			setVisible(false);
			new FrameArtModifier(tabArt, tabCde);
		}
		if (event.getActionCommand() == "Transformer un article") {
			setVisible(false);
			new FrameArtTransformer(tabArt, tabCde);
		}
		if (event.getActionCommand() == "Fin") {
			setVisible(false);
			new FrameZClientJava61(tabArt, tabCde);
			
			
		}
	}

}
