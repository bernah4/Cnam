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
import javax.swing.JTextField;

import IPane.IoIcon;
import Serie61.*;

public class FrameArtModifier2 extends JFrame implements ActionListener {

	private TableArticles61 tabArt;
	private TableDesCommandes61 tabCde;
	private int codeart;
	private JButton valid;
	private JButton cancel;
	private IoIcon es = new IoIcon();
	private JComboBox<String> choix;
	private JTextField designation = new JTextField(1);
	private JTextField pu = new JTextField(1);
	private JLabel desitxt;
	private JLabel putxt;

	public FrameArtModifier2(TableArticles61 tabArt, TableDesCommandes61 tabCde, int art) {

		this.tabArt = tabArt;
		this.tabCde = tabCde;
		this.codeart = art;
		setLayout(new GridBagLayout());
		setSize(450, 190);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Modifier - 2 - Article");
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weighty = 0.15;
		c.weightx = 0.1;
		ImageIcon test = new ImageIcon(this.getClass().getResource("good-icon-mini.png"));
		JLabel id = new JLabel("Renseigner les champs a modifier :");
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
		String [] st = {"","Designation","Prix unitaire", "Designation & Prix unitaire"};
		choix = new JComboBox<String>(st);
		choix.setSelectedIndex(0);
		this.add(choix, c);
		choix.addActionListener(this);
		
		designation.setEditable(false);
		pu.setEditable(false);
		
		c.gridy = 2;
		c.gridx = 0;		
		c.gridwidth = 1;
		c.weightx = 0;
		desitxt = new JLabel("Designation :");
		desitxt.setForeground(Color.LIGHT_GRAY);
		this.add(desitxt, c);
		c.gridx = 1;
		c.gridwidth = 3;
		c.weightx = 1;
		this.add(designation, c);
		
		c.gridy = 3;
		c.gridx = 0;		
		c.gridwidth = 1;
		c.weightx = 0;
		putxt = new JLabel("Prix unitaire :");
		putxt.setForeground(Color.LIGHT_GRAY);
		this.add(putxt, c);
		c.gridx = 1;
		c.gridwidth = 3;
		c.weightx = 1;
		this.add(pu, c);
		
		c.gridy = 4;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.VERTICAL;
		valid = new JButton("Valider");
		cancel = new JButton("Annuler");
		valid.setEnabled(false);
		valid.addActionListener(this);
		cancel.addActionListener(this);
		c.gridx = 2;
		c.anchor = GridBagConstraints.LINE_END;
		this.add(cancel, c);
		c.gridx = 3;
		c.anchor = GridBagConstraints.LINE_START;
		this.add(valid, c);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent event) {

		if (event.getActionCommand() == "Annuler") 
		{
			setVisible(false);
			new FrameGestionTableDesArticle61(tabArt, tabCde);
		} 
		else if (event.getActionCommand() == "Valider") 
		{
			String typ = (String)choix.getSelectedItem();
			setVisible(false);
			try {
				if (typ.equals("Designation"))
				{
					if (designation.getText().equals(""))
					{
						setVisible(false);
						es.affiche("Le champ 'designation' doit etre informe !", new ImageIcon(this.getClass().getResource("alert-icon.png")));
						new FrameArtModifier2(tabArt, tabCde, codeart);
						return ;
					}
					tabArt.retourner(codeart).setDesignation(designation.getText());
					es.affiche("Designation modifie !", new ImageIcon(this.getClass().getResource("valid-icon.png")));
				}
				else if (typ.equals("Prix unitaire"))
				{
					tabArt.retourner(codeart).setPrixunitaire(Float.parseFloat(pu.getText()));
					es.affiche("Prix unitaire modifie !", new ImageIcon(this.getClass().getResource("valid-icon.png")));
				}
				else
				{
					if (designation.getText().equals(""))
					{
						setVisible(false);
						es.affiche("Le champ 'designation' doit etre informe !", new ImageIcon(this.getClass().getResource("alert-icon.png")));
						new FrameArtModifier2(tabArt, tabCde, codeart);
						return ;
					}
					tabArt.retourner(codeart).setDesignation(designation.getText());
					tabArt.retourner(codeart).setPrixunitaire(Float.parseFloat(pu.getText()));
					es.affiche("Designation & Prix unitaire modifies !", new ImageIcon(this.getClass().getResource("valid-icon.png")));
				}
				new FrameGestionTableDesArticle61(tabArt, tabCde);
			}
			 catch (NumberFormatException ne) {
				setVisible(false);
				es.affiche("Entrez une valeur numerique pour le champ 'Prix unitaire' !", new ImageIcon(this.getClass().getResource("alert-icon.png")));
				new FrameArtModifier2(tabArt, tabCde, codeart);
			}
	
		}
		else 
		{
			String typ = (String)choix.getSelectedItem();
			if (typ.equals(""))
			{
				designation.setEditable(false);
				pu.setEditable(false);
				desitxt.setForeground(Color.LIGHT_GRAY);
				putxt.setForeground(Color.LIGHT_GRAY);
				valid.setEnabled(false);
			}
			else if (typ.equals("Designation"))
			{
				designation.setEditable(true);
				pu.setEditable(false);
				desitxt.setForeground(Color.BLACK);
				putxt.setForeground(Color.LIGHT_GRAY);
				valid.setEnabled(true);
			}
			else if (typ.equals("Prix unitaire"))
			{
				designation.setEditable(false);
				pu.setEditable(true);
				desitxt.setForeground(Color.LIGHT_GRAY);
				putxt.setForeground(Color.BLACK);
				valid.setEnabled(true);
			}
			else
			{
				designation.setEditable(true);
				pu.setEditable(true);
				desitxt.setForeground(Color.BLACK);
				putxt.setForeground(Color.BLACK);
				valid.setEnabled(true);
			}
		}
	}
}
