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
import Serie61.TableArticles61;
import Serie61.TableDesCommandes61;

public class FrameArtAffichePromo extends JFrame implements ActionListener {
	
	private TableArticles61 tabArt;
	private TableDesCommandes61 tabCde;
	private JButton valid;
	
	public FrameArtAffichePromo(TableArticles61 tabArt, TableDesCommandes61 tabCde) {
		this.tabArt = tabArt;
		this.tabCde = tabCde;
		String [][] data = tabArt.dataPromo();
		String[] label = tabArt.label();
		setLayout(new GridBagLayout());
		setSize(600, 150+20*tabArt.taillePromo());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("AfficherPromo - Article");
		GridBagConstraints c = new GridBagConstraints();
		
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weighty = 0.1;
		c.weightx = 0.15;
		ImageIcon test = new ImageIcon(this.getClass().getResource("good-icon-mini.png"));
		JLabel id = new JLabel("Table des articles en promotion :");
		JLabel image = new JLabel(test);
		this.add(image, c);
		c.gridx = 2;
		c.gridwidth = 3;
		c.weightx = 0.85;
		this.add(id, c);
		JTable table = new JTable(data, label);
		c.gridy = 1;
		c.gridx = 0;
		c.gridwidth = 4;
		c.weighty = 0.7;
		table.setGridColor(Color.LIGHT_GRAY);
		table.getColumnModel().getColumn(0).setMaxWidth(75);
		table.getColumnModel().getColumn(1).setMaxWidth(690);
		table.getColumnModel().getColumn(2).setMaxWidth(75);
		table.getColumnModel().getColumn(3).setMaxWidth(60);
		table.getColumnModel().getColumn(4).setMaxWidth(110);
		table.getColumnModel().getColumn(5).setMaxWidth(70);
		this.add(table.getTableHeader(), c);
		c.gridy = 2;
		this.add(table, c);
		c.gridy = 3;
		c.gridwidth = 1;
		c.weighty = 0.1;
		c.fill = GridBagConstraints.VERTICAL;
		valid = new JButton("Valider");
		c.gridx = 3;
		this.add(valid, c);
		valid.addActionListener(this);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent event) {
		
		if (event.getActionCommand() == "Valider") {
			setVisible(false);
			new FrameGestionTableDesArticle61(tabArt, tabCde);
		}
		
	}

}
