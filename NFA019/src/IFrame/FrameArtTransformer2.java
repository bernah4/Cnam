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

public class FrameArtTransformer2 extends JFrame implements ActionListener {

	private TableArticles61 tabArt;
	private TableDesCommandes61 tabCde;
	private int codeart;
	private JButton valid;
	private JButton cancel;
	private IoIcon es = new IoIcon();
	private JComboBox<String> choix;
	private JTextField qtemin = new JTextField(1);
	private JTextField reduc = new JTextField(1);
	private JLabel qtetxt;
	private JLabel reductxt;

	public FrameArtTransformer2(TableArticles61 tabArt, TableDesCommandes61 tabCde, int art) {

		this.tabArt = tabArt;
		this.tabCde = tabCde;
		this.codeart = art;
		setLayout(new GridBagLayout());
		setSize(450, 190);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Transformer - 2 - Article");
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
		JLabel txt = new JLabel("Type d'article :");
		this.add(txt, c);
		c.gridx = 1;
		c.weightx = 1;
		c.gridwidth = 3;
		String [] st = {"Normal","Promo", "Lot"};
		choix = new JComboBox<String>(st);
		choix.setSelectedIndex(0);
		this.add(choix, c);
		choix.addActionListener(this);
		
		qtemin.setEditable(false);
		reduc.setEditable(false);
		
		c.gridy = 2;
		c.gridx = 0;		
		c.gridwidth = 1;
		c.weightx = 0;
		qtetxt = new JLabel("Quatite min/reduc :");
		qtetxt.setForeground(Color.LIGHT_GRAY);
		this.add(qtetxt, c);
		c.gridx = 1;
		c.gridwidth = 3;
		c.weightx = 1;
		this.add(qtemin, c);
		
		c.gridy = 3;
		c.gridx = 0;		
		c.gridwidth = 1;
		c.weightx = 0;
		reductxt = new JLabel("Reduction % :");
		reductxt.setForeground(Color.LIGHT_GRAY);
		this.add(reductxt, c);
		c.gridx = 1;
		c.gridwidth = 3;
		c.weightx = 1;
		this.add(reduc, c);
		
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
			AbstractArticle abart = tabArt.retourner(codeart);
			try {
				if (typ.equals("Normal"))
				{
					Article61 art = new Article61(abart.getCodearticle(), abart.getDesignation(), abart.getPrixunitaire());
					tabArt.ajouter(art);
				}
				else if (typ.equals("Promo"))
				{
					ArticlePromotion61 art = new ArticlePromotion61(abart.getCodearticle(), abart.getDesignation(), 
							abart.getPrixunitaire(), Integer.parseInt(qtemin.getText()), Float.parseFloat(reduc.getText()));
					tabArt.ajouter(art);
				}
				else
				{
					ArticleLot61 art = new ArticleLot61(abart.getCodearticle(), abart.getDesignation(), 
							abart.getPrixunitaire(), Integer.parseInt(qtemin.getText()), Float.parseFloat(reduc.getText()));
					tabArt.ajouter(art);
				}
				es.affiche("Article transforme !", new ImageIcon(this.getClass().getResource("valid-icon.png")));
				new FrameGestionTableDesArticle61(tabArt, tabCde);
			}
			 catch (NumberFormatException ne) {
				setVisible(false);
				es.affiche("Entrez une valeur numerique pour le champ 'Prix unitaire' !", new ImageIcon(this.getClass().getResource("alert-icon.png")));
				new FrameArtTransformer2(tabArt, tabCde, codeart);
			}
	
		}
		else 
		{
			String typ = (String)choix.getSelectedItem();
			if (typ.equals("Normal"))
			{
				qtemin.setEditable(false);
				reduc.setEditable(false);
				qtetxt.setForeground(Color.LIGHT_GRAY);
				reductxt.setForeground(Color.LIGHT_GRAY);
			}
			else
			{
				qtemin.setEditable(true);
				reduc.setEditable(true);
				qtetxt.setForeground(Color.BLACK);
				reductxt.setForeground(Color.BLACK);
			}
		}
	}
}
