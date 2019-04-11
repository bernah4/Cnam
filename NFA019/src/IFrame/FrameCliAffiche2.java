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
import Serie61.*;

public class FrameCliAffiche2 extends JFrame implements ActionListener {

	private TableDesCommandes61 tabCde;
	private TableClient61 tabClient;
	private JButton valid;

	public FrameCliAffiche2(TableClient61 tabClient, TableDesCommandes61 tabCde, String num) {

		this.tabClient = tabClient;
		this.tabCde = tabCde;
		this.tabClient = tabClient;
		String[][] data = tabCde.dataClient(num);
		String[] label = tabCde.label();
		setLayout(new GridBagLayout());
		setSize(600, 150+20*tabCde.tailleClient(num));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Affiche 2 - Client");
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weighty = 0.15;
		c.weightx = 0.1;
		ImageIcon test = new ImageIcon(this.getClass().getResource("client-icon-mini.png"));
		JLabel id = new JLabel("Fiche detail du client nº "+num);
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
			new FrameGestionTableDesClient61(tabClient, tabCde);
		}
	}
}
