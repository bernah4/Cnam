package Serie52;

import java.io.Serializable;
import java.util.TreeMap;

import MesInterfaces.InterfaceStruct;

public class TableFactures52 implements InterfaceStruct<String, Facture52>, Serializable{
	
	private TreeMap<String,Facture52> tabFact = new TreeMap<String,Facture52>();
	
	public void ajouter(Facture52 facture)
	{
		tabFact.put(facture.getNumcde(), facture);
	}
	
	public void supprimer(String cle)
	{
		tabFact.remove(cle);
	}
	
	public int taille()
	{
		return tabFact.size();
	}
	
	public Facture52 retourner(String cle)
	{
		return tabFact.get(cle);
	}
	
	public String cle()
	{
		String ret = "Liste des factures \n";
		for (String num : tabFact.keySet())
			ret = ret + num +"\n";
		return ret;
	}
	
	public String toString()
	{
		String ret = "";
		for (Facture52 fact : tabFact.values())
			ret = ret + fact.toString() +"\n";
		return ret;
	}

}
