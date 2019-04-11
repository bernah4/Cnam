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

public class FrameCdeAffiche2 extends JFrame implements ActionListener {

	private TableArticles61 tabArt;
	private TableDesCommandes61 tabCde;
	private TableClient61 tabClient;
	private JTextField val = new JTextField(1);
	private JButton valid;

	public FrameCdeAffiche2(TableArticles61 tabArt, TableDesCommandes61 tabCde, TableClient61 tabClient, String num) {

		this.tabArt = tabArt;
		this.tabCde = tabCde;
		this.tabClient = tabClient;
		String[][] data = tabCde.retourner(num).data(tabArt);
		String[] label = tabCde.retourner(num).label();
		setLayout(new GridBagLayout());
		setSize(800, 150+20*tabCde.retourner(num).taille());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Affiche 2 - Commande");
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weighty = 0.15;
		c.weightx = 0.1;
		ImageIcon test = new ImageIcon(this.getClass().getResource("product-icon-mini.png"));
		JLabel id = new JLabel("Commande nº "+num);
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
		this.add(table.getTableHeader(), c);
		c.gridy = 2;
		this.add(table, c);
		c.gridy = 3;
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
			new FrameGestionTableDesCommandes61(tabArt, tabCde, tabClient);
		}
	}
}
