package IFrame;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import IPane.IoIcon;
import MesExceptions.AbandonException;
import Serie61.*;

public class FrameZClientJava61 extends JFrame implements ActionListener {

	static IoIcon es = new IoIcon();

	GestionTableArticle61 gta = new GestionTableArticle61("TableArticles.data");
	GestionTableDesCommandes61 gtc = new GestionTableDesCommandes61("TableDesCommandes.data");
	GestionTableFactures61 gtf = new GestionTableFactures61("TableFacture.data");
	GestionTableClient61 gtcl = new GestionTableClient61("TableClient.data");
	TableArticles61 tabArt = gta.recuperer();
	TableDesCommandes61 tabCde = gtc.recuperer();
	TableFactures61 tabFact = gtf.recuperer();
	TableClient61 tabClient = gtcl.recuperer();
	private JButton gtab;
	private JButton gtcb;
	private JButton gtfb;
	private JButton gtclb;
	private JButton finish;

	public FrameZClientJava61() {

		gta.updateArticle(tabArt);
		gtc.autoPurge(tabCde, 3);
		gtc.purgeOld(tabCde, 1);
		gtf.purgeOld(tabFact, 1);
		frameclient();	
	}
	
	public FrameZClientJava61(TableArticles61 tabArt, TableDesCommandes61 tabCde) {
		
		this.tabCde = tabCde;
		this.tabArt = tabArt;
		gta.sauvegarder(tabArt);
		gtc.sauvegarder(tabCde);
		frameclient();	
	}
	
	public FrameZClientJava61(TableArticles61 tabArt, TableDesCommandes61 tabCde, TableClient61 tabClient) {
		
		this.tabArt = tabArt;
		this.tabClient = tabClient;
		this.tabCde = tabCde;
		gta.sauvegarder(tabArt);
		gtc.sauvegarder(tabCde);
		gtcl.sauvegarder(tabClient);
		frameclient();	
	}
	
	public FrameZClientJava61(TableClient61 tabClient, TableDesCommandes61 tabCde) {
		
		this.tabClient = tabClient;
		this.tabCde = tabCde;
		gtc.sauvegarder(tabCde);
		gtcl.sauvegarder(tabClient);
		frameclient();	
	}
	
public FrameZClientJava61(TableArticles61 tabArt, TableDesCommandes61 tabCde, TableFactures61 tabFact) {
		
		this.tabArt = tabArt;
		this.tabFact = tabFact;
		this.tabCde = tabCde;
		gta.sauvegarder(tabArt);
		gtc.sauvegarder(tabCde);
		gtf.sauvegarder(tabFact);
		frameclient();	
	}

	public void frameclient()
	{
		setLayout(new GridBagLayout());
		setSize(300, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.2;
		ImageIcon test = new ImageIcon(this.getClass().getResource("shop-icon.png"));
		JLabel id = new JLabel("Bienvenue chez croquette corp !");
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
		gtab = new JButton("Gestion des articles");
		this.add(gtab, c);
		gtab.addActionListener(this);
		c.gridy = 2;
		gtcb = new JButton("Gestion des commandes");
		this.add(gtcb, c);
		gtcb.addActionListener(this);
		c.gridy = 3;
		gtfb = new JButton("Gestion des factures");
		this.add(gtfb, c);
		gtfb.addActionListener(this);
		c.gridy = 4;
		gtclb = new JButton("Gestion des clients");
		this.add(gtclb, c);
		gtclb.addActionListener(this);
		c.gridy = 5;
		finish = new JButton("Fin");
		this.add(finish, c);
		finish.addActionListener(this);

		setLocationRelativeTo(null);
		setVisible(true);	
	}
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand() == "Gestion des articles") {
			setVisible(false);
			gta.menuGeneral(tabArt, tabCde);
		}
		if (event.getActionCommand() == "Gestion des commandes") {
			setVisible(false);
			gtc.menuGeneral(tabCde, tabArt, tabClient);	
		}
		if (event.getActionCommand() == "Gestion des factures") {
			setVisible(false);
			gtf.menuGeneral(tabFact, tabCde, tabArt);		
		}
		if (event.getActionCommand() == "Gestion des clients") {
			setVisible(false);
			gtcl.menuGeneral(tabClient, tabCde);
		}
		if (event.getActionCommand() == "Fin") {
			setVisible(false);
			fin();
		}
	}
	
	public void fin()
	{
		gta.sauvegarder(tabArt);
		gtc.sauvegarder(tabCde);
		gtf.sauvegarder(tabFact);
		gtcl.sauvegarder(tabClient);
		es.affiche(" A bientot chez croquette-corp ! ", new ImageIcon(this.getClass().getResource("shop-icon.png")));
		System.exit(0);	
	}

}
