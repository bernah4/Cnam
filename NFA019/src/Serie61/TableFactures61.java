package Serie61;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.TreeMap;
import MesInterfaces.InterfaceStruct;
import Utils.DateUser;

public class TableFactures61 implements InterfaceStruct<String, Facture61>, Serializable{
	
	private TreeMap<String,Facture61> tabFact = new TreeMap<String,Facture61>();
	
	public TreeMap<String, Facture61> getTabFact() {
		return tabFact;
	}

	public void setTabFact(TreeMap<String, Facture61> tabFact) {
		this.tabFact = tabFact;
	}

	public void ajouter(Facture61 facture)
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
	
	public Facture61 retourner(String cle)
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
		for (Facture61 fact : tabFact.values())
			ret = ret + fact.toString() +"\n";
		return ret;
	}
	
	// pour le hashtrable
	/*public void autoPurge(int n)
	{
		DateUser datecourante = new DateUser();
		DateUser compare;
		Enumeration<Facture61> enumTabFact  = tabFact.elements();
		while(enumTabFact.hasMoreElements())
		{
			Facture61 fact = enumTabFact.nextElement();		
			compare = fact.getDateFacture().ajouterNombreDeJour(n);
			if (datecourante.estSuperieur(compare))
				supprimer(fact.getNumcde());
		}
	}*/
	
	public void autoPurge(int n)
	{
		DateUser datecourante = new DateUser();
		DateUser compare;
		String key = tabFact.firstKey();
		while(key != null)
		{
			Facture61 fact = retourner(key);		
			compare = fact.getDateFacture().ajouterNombreDeJour(n);
			if (datecourante.estSuperieur(compare))
				supprimer(key);
			key = tabFact.higherKey(key);
		}
	}
	
	public boolean plusExist(int n)
	{
		DateUser datecourante = new DateUser();
		DateUser compare;
		for(Facture61 fact : tabFact.values())
		{		
			compare = fact.getDateFacture().ajouterNombreDeJour(n);
			if (datecourante.estSuperieur(compare))
				return true;
		}
		return false;
	}
	
	public String[] label()
	{
		String[] ret = {"Nº facture", "Date facture"};
		return ret;
	}
	
	public String[][] data()
	{
		int i = taille();
		int j = 0;
		String[][] ret = new String[i][];
		for (Facture61 fa : tabFact.values())
		{
			ret[j] = fa.listespec();
			j++;
		}
		return ret;
	}

}
