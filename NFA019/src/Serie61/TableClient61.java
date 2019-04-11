package Serie61;

import java.io.Serializable;
import java.util.TreeMap;

import MesInterfaces.InterfaceStruct;

public class TableClient61 implements InterfaceStruct<String, Client61>, Serializable{
	private TreeMap<String, Client61> tabClient = new TreeMap<String, Client61>();
	
	public TableClient61()
	{
		Client61 c1 = new Client61("DUPONT-Martin", "Paris", 0);
		Client61 c2 = new Client61("TREMBLAY-Jean-Pierre", "Montreal", 0);
		Client61 c3 = new Client61("DUPRE-Marie", "Nante", 1);
		ajouter(c1);
		ajouter(c2);
		ajouter(c3);
	}
	
	public TreeMap<String, Client61> getTabClient() {
		return tabClient;
	}

	public void setTabClient(TreeMap<String, Client61> tabClient) {
		this.tabClient = tabClient;
	}

	public void ajouter(Client61 client) {
		tabClient.put(client.getNumclient(), client);
	}
	
	public void supprimer(String numclient)
	{
		tabClient.remove(numclient);
	}
	
	public int taille()
	{
		return tabClient.size();
	}
	
	public Client61 retourner(String numclient)
	{
		return tabClient.get(numclient);
	}
	
	public String toString()
	{
		String ret = "";
		for (Client61 cl : tabClient.values())
			ret = ret + cl.toString()+"\n";
		
		return ret;
	}

	public String cle()
	{
		String ret = " Liste des clients : \n";
		for (String numcl: tabClient.keySet())
			ret = ret + numcl+"\n";
		
		return ret;
	}
	
	public String[] listNum()
	{
		
		
		int i = taille();
		int j = 0;
		String[] ret = new String[i];
		for (Client61 cl : tabClient.values())
		{
			ret[j] = cl.getNumclient();
			j++;
		}
		return ret;
	}
	
	public String[] label()
	{
		String[] ret = {"Nº client", "Chiffre Affaire", "Nombre commande", "Nom", "Lieu", "Date inscription"};
		return ret;
	}
	
	public String[][] data()
	{
		int i = taille();
		int j = 0;
		String[][] ret = new String[i][];
		for (Client61 cl : tabClient.values())
		{
			ret[j] = cl.listespec();
			j++;
		}
		return ret;
	}

}
