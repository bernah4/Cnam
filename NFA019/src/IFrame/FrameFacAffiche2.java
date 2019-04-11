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

public class FrameFacAffiche2 extends JFrame implements ActionListener {

	private TableArticles61 tabArt;
	private TableDesCommandes61 tabCde;
	private TableFactures61 tabFact;
	private JButton valid;

	public FrameFacAffiche2(TableArticles61 tabArt, TableDesCommandes61 tabCde, TableFactures61 tabFact, String num) {

		this.tabArt = tabArt;
		this.tabCde = tabCde;
		this.tabFact = tabFact;
		String[][] data = tabFact.retourner(num).getData();
		String[] label = tabCde.retourner(num).label();
		setLayout(new GridBagLayout());
		setSize(800, 100+20*tabFact.retourner(num).getSize());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Affiche 2 - Facture");
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weighty = 0.15;
		c.weightx = 0.1;
		ImageIcon test = new ImageIcon(this.getClass().getResource("money-icon-mini.png"));
		JLabel id = new JLabel("Detail facture");
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
		table.getColumnModel().getColumn(0).setMaxWidth(75);
		table.getColumnModel().getColumn(1).setMaxWidth(500);
		table.getColumnModel().getColumn(2).setMaxWidth(75);
		table.getColumnModel().getColumn(3).setMaxWidth(60);
		table.getColumnModel().getColumn(4).setMaxWidth(110);
		table.getColumnModel().getColumn(5).setMaxWidth(70);
		table.getColumnModel().getColumn(6).setMaxWidth(70);
		table.getColumnModel().getColumn(7).setMaxWidth(70);
		table.getColumnModel().getColumn(8).setMaxWidth(75);
		this.add(table, c);
		c.gridy = 2;
		c.gridwidth = 1;
		c.weighty = 0.15;
		c.fill = GridBagConstraints.VERTICAL;
		valid = new JButton("Valider");
		valid.addActionListener(this);
		c.gridx = 3;
		this.add(valid, c);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent event) {

		if (event.getActionCommand() == "Valider") 
		{
			setVisible(false);
			new FrameGestionTableDesFactures61(tabArt, tabCde, tabFact);
		}
	}
}
