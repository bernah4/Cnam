package Serie53;

import java.io.Serializable;
import java.util.TreeMap;

import MesInterfaces.InterfaceStruct;

public class TableClient53 implements InterfaceStruct<String, Client53>, Serializable{
	private TreeMap<String, Client53> tabClient = new TreeMap<String, Client53>();
	
	public TableClient53()
	{
		Client53 c1 = new Client53("DUPONT-Martin", "Paris", 0);
		Client53 c2 = new Client53("TREMBLAY-Jean-Pierre", "Montreal", 0);
		Client53 c3 = new Client53("DUPRE-Marie", "Nante", 1);
		ajouter(c1);
		ajouter(c2);
		ajouter(c3);
	}
	
	public void ajouter(Client53 client) {
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
	
	public Client53 retourner(String numclient)
	{
		return tabClient.get(numclient);
	}
	
	public String toString()
	{
		String ret = "";
		for (Client53 cl : tabClient.values())
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

}
