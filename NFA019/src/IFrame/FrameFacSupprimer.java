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
import Utils.DateUser;

public class FrameFacSupprimer extends JFrame implements ActionListener {

	private TableArticles61 tabArt;
	private TableDesCommandes61 tabCde;
	private TableFactures61 tabFact;
	private JTextField val = new JTextField(1);
	private JButton valid;
	private JButton cancel;
	private IoIcon es = new IoIcon();

	public FrameFacSupprimer(TableArticles61 tabArt, TableDesCommandes61 tabCde, TableFactures61 tabFact) {

		this.tabArt = tabArt;
		this.tabCde = tabCde;
		this.tabFact = tabFact;
		String[][] data = tabCde.dataFact();
		String[] label = tabCde.label();
		setLayout(new GridBagLayout());
		setSize(600, 175+20*tabCde.tailleFacture());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Supprimer - Facture");
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weighty = 0.15;
		c.weightx = 0.1;
		ImageIcon test = new ImageIcon(this.getClass().getResource("money-icon-mini.png"));
		JLabel id = new JLabel("Choisisssez la facture a supprimer :");
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
		table.getColumnModel().getColumn(0).setMaxWidth(100);
		table.getColumnModel().getColumn(1).setMaxWidth(100);
		table.getColumnModel().getColumn(2).setMaxWidth(100);
		table.getColumnModel().getColumn(3).setMaxWidth(100);
		table.getColumnModel().getColumn(4).setMaxWidth(100);
		table.getColumnModel().getColumn(5).setMaxWidth(100);
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
			new FrameGestionTableDesFactures61(tabArt, tabCde, tabFact);
		} 
		else if (event.getActionCommand() == "Valider") 
		{
			String num = val.getText();
			setVisible(false);
			Facture61 fact = tabFact.retourner(num);
			if (fact != null) {
				DateUser verif = new DateUser();
				if (verif.estSuperieur(fact.getDateFacture().ajouterNombreDeJour(7)))
				{
					tabFact.supprimer(num);
					es.affiche("Facture nº "+num+" supprimee !", new ImageIcon(this.getClass().getResource("valid-icon.png")));
					new FrameGestionTableDesFactures61(tabArt, tabCde, tabFact);
				}
				else
				{
					es.affiche("Vous ne pouvez pas effacer une facture de moins de 7 jours !", new ImageIcon(this.getClass().getResource("alert-icon.png")));
					new FrameFacSupprimer(tabArt, tabCde, tabFact);
				}
			} 
			else
			{
				es.affiche("Facture inexistante dans la table !", new ImageIcon(this.getClass().getResource("alert-icon.png")));
				new FrameFacSupprimer(tabArt, tabCde, tabFact);
			}
							
		}
	}
}
