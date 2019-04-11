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

public class FrameArtAjouter extends JFrame implements ActionListener{
	
	private TableArticles61 tabArt;
	private TableDesCommandes61 tabCde;
	private JTextField code = new JTextField(1);
	private JTextField designation = new JTextField(1);
	private JTextField pu = new JTextField(1);
	private JTextField qte = new JTextField(1);
	private JTextField reduc = new JTextField(1);
	private JButton valid;
	private JButton cancel;
	private JComboBox<String> type;
	private JLabel jreduc;
	private JLabel jqte;
	private IoIcon es = new IoIcon();
	
	public FrameArtAjouter(TableArticles61 tabArt, TableDesCommandes61 tabCde) {
	
		this.tabArt = tabArt;
		this.tabCde = tabCde;
		setLayout(new GridBagLayout());
		setSize(450, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Ajouter - Article");
		GridBagConstraints c = new GridBagConstraints();
		
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weighty = 0.15;
		c.weightx = 0;
		ImageIcon test = new ImageIcon(this.getClass().getResource("good-icon-mini.png"));
		JLabel id = new JLabel("Remplissez les differents champs de l'article a creer :");
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
		JLabel txt = new JLabel("Code produit :");
		this.add(txt, c);
		c.gridx = 1;
		c.gridwidth = 3;
		c.weightx = 1;
		this.add(code, c);

		c.gridy = 2;
		c.gridx = 0;		
		c.gridwidth = 1;
		c.weightx = 0;
		txt = new JLabel("Designation :");
		this.add(txt, c);
		c.gridx = 1;
		c.gridwidth = 3;
		c.weightx = 1;
		this.add(designation, c);
		
		c.gridy = 3;
		c.gridx = 0;		
		c.gridwidth = 1;
		c.weightx = 0;
		txt = new JLabel("Prix unitaire :");
		this.add(txt, c);
		c.gridx = 1;
		c.gridwidth = 3;
		c.weightx = 1;
		this.add(pu, c);
		
		c.gridy = 4;
		c.gridx = 0;		
		c.gridwidth = 1;
		c.weightx = 0;
		txt = new JLabel("Type :");
		this.add(txt, c);
		c.gridx = 1;
		c.weightx = 1;
		c.gridwidth = 3;
		String [] st = {"Normal", "Promo", "Lot"};
		type = new JComboBox<String>(st);
		type.setSelectedIndex(0);
		this.add(type, c);
		type.addActionListener(this);
		
		qte.setEditable(false);
		reduc.setEditable(false);
		c.gridy = 5;
		c.gridx = 0;		
		c.gridwidth = 1;
		c.weightx = 0;
		jqte = new JLabel("Qte -> reduc :");
		jqte.setForeground(Color.LIGHT_GRAY);
		this.add(jqte, c);
		c.gridx = 1;
		c.gridwidth = 3;
		c.weightx = 1;
		this.add(qte, c);
		
		c.gridy = 6;
		c.gridx = 0;		
		c.gridwidth = 1;
		c.weightx = 0;
		jreduc = new JLabel(" % de reduction :");
		jreduc.setForeground(Color.LIGHT_GRAY);
		this.add(jreduc, c);
		c.gridx = 1;
		c.gridwidth = 3;
		c.weightx = 1;
		this.add(reduc, c);
		
		c.gridy = 7;
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
		
		if(event.getActionCommand() == "Annuler")
		{
			setVisible(false);
			new FrameGestionTableDesArticle61(tabArt, tabCde);
		}
		else if (event.getActionCommand() == "Valider")
		{
			String desi = designation.getText();
			if (desi.equals(""))
			{
				setVisible(false);
				es.affiche("Le champ 'designation' doit etre informe !", new ImageIcon(this.getClass().getResource("alert-icon.png")));
				new FrameArtAjouter(tabArt, tabCde);
				return ;
			}
			String typ = (String) type.getSelectedItem();
			try 
			{
				int num = Integer.parseInt(code.getText());
				float fpu = Float.parseFloat(pu.getText());
				AbstractArticle art = tabArt.retourner(num);
				setVisible(false);
				if (art != null)
				{
					es.affiche("produit ayant le meme code artcle existant dans la table !\n", new ImageIcon(this.getClass().getResource("alert-icon.png")));
					new FrameArtAjouter(tabArt, tabCde);
				}
				else 
				{
					if (typ.equals("Normal"))
					{
						Article61 newart = new Article61(num, desi, fpu);
						tabArt.ajouter(newart);
						es.affiche("Nouvel article Normal ajoute !", new ImageIcon(this.getClass().getResource("valid-icon.png")));
					}
					else if (typ.equals("Promo"))
					{
						int qtem = Integer.parseInt(qte.getText()); 
						float red = Float.parseFloat(reduc.getText());
						ArticlePromotion61 newart = new ArticlePromotion61(num, desi, fpu, qtem, red);
						tabArt.ajouter(newart);
						es.affiche("Nouvel article Promo ajoute !", new ImageIcon(this.getClass().getResource("valid-icon.png")));
					}
					else if (typ.equals("Lot"))
					{
						int qtem = Integer.parseInt(qte.getText()); 
						float red = Float.parseFloat(reduc.getText());
						ArticleLot61 newart = new ArticleLot61(num, desi, fpu, qtem, red);
						tabArt.ajouter(newart);
						es.affiche("Nouvel article Normal ajoute !", new ImageIcon(this.getClass().getResource("valid-icon.png")));
					}
					new FrameGestionTableDesArticle61(tabArt, tabCde);	
				}
			}
			catch (NumberFormatException ne)
			{
				setVisible(false);
				es.affiche("Entrez des valeurs numerique pour les champs autres que 'designation' !", new ImageIcon(this.getClass().getResource("alert-icon.png")));
				new FrameArtAjouter(tabArt, tabCde);
			}
		}
		else 
		{
			String typ = (String)type.getSelectedItem();
			if (typ.equals("Normal"))
			{
				qte.setEditable(false);
				reduc.setEditable(false);
				jreduc.setForeground(Color.LIGHT_GRAY);
				jqte.setForeground(Color.LIGHT_GRAY);
			}
			else
			{
				qte.setEditable(true);
				reduc.setEditable(true);
				jreduc.setForeground(Color.BLACK);
				jqte.setForeground(Color.BLACK);
			}
		}
	}
}
