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

public class FrameGestionTableDesClient61 extends JFrame implements ActionListener {
	
	private TableClient61 tabClient;
	private TableDesCommandes61 tabCde;
	
	private JButton add;
	private JButton sup;
	private JButton show;
	private JButton showprom;
	private JButton modif;
	private JButton finish;
 
	private IoIcon es = new IoIcon();
	
	public FrameGestionTableDesClient61(TableClient61 tabClient, TableDesCommandes61 tabCde)
	{
	
		this.tabClient = tabClient;
		this.tabCde = tabCde;
		setLayout(new GridBagLayout());
		setSize(350, 350);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		GridBagConstraints c = new GridBagConstraints();
		setTitle("CLIENT");
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.2;
		ImageIcon test = new ImageIcon(this.getClass().getResource("client-icon.png"));
		JLabel id = new JLabel("Menu de gestion de la table des clients :");
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
		add = new JButton("Ajouter un compte client");
		this.add(add, c);
		add.addActionListener(this);
		c.gridy = 2;
		sup = new JButton("Supprimer un compte client");
		this.add(sup, c);
		sup.addActionListener(this);
		c.gridy = 3;
		show = new JButton("Afficher un compte client");
		this.add(show, c);
		show.addActionListener(this);
		c.gridy = 4;
		showprom = new JButton("Afficher tout les comptes clients");
		this.add(showprom, c);
		showprom.addActionListener(this);
		c.gridy = 5;
		modif = new JButton("Modifier un compte client");
		this.add(modif, c);
		modif.addActionListener(this);
		c.gridy = 6;
		finish = new JButton("Fin");
		this.add(finish, c);
		finish.addActionListener(this);
		
		setLocationRelativeTo(null);
		setVisible(true);	
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand() == "Ajouter un compte client") {
			setVisible(false);
			new FrameCliAjouter(tabClient, tabCde);
		}
		if (event.getActionCommand() == "Supprimer un compte client") {
			setVisible(false);
			new FrameCliSupprimer(tabClient, tabCde);
		}
		if (event.getActionCommand() == "Afficher un compte client") {
			setVisible(false);
			if (tabClient.taille() == 0)
			{
				es.affiche("Aucun compte client ! ", new ImageIcon(this.getClass().getResource("alert-icon.png")));
				new FrameGestionTableDesClient61(tabClient, tabCde);
			}
			else
				new FrameCliAffiche(tabClient, tabCde);
		}
		if (event.getActionCommand() == "Afficher tout les comptes clients") {
			setVisible(false);
			if (tabClient.taille() == 0)
			{
				es.affiche("Aucun compte client ! ", new ImageIcon(this.getClass().getResource("alert-icon.png")));
				new FrameGestionTableDesClient61(tabClient, tabCde);
			}
			else
				new FrameCliAfficheTout(tabClient , tabCde, tabClient.getTabClient().firstKey());
		}
		if (event.getActionCommand() == "Modifier un compte client") {
			setVisible(false);
			new FrameCliModifier(tabClient, tabCde);
		}
		if (event.getActionCommand() == "Fin") {
			setVisible(false);
			new FrameZClientJava61(tabClient, tabCde);
			
			
		}
	}

}
