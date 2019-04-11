package Serie53;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;

import MesInterfaces.InterfaceStruct;
import Utils.DateUser;

public class TableFactures53 implements InterfaceStruct<String, Facture53>, Serializable{
	
	private Hashtable<String,Facture53> tabFact = new Hashtable<String,Facture53>();
	
	public void ajouter(Facture53 facture)
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
	
	public Facture53 retourner(String cle)
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
		for (Facture53 fact : tabFact.values())
			ret = ret + fact.toString() +"\n";
		return ret;
	}
	
	public void autoPurge(int n)
	{
		DateUser datecourante = new DateUser();
		DateUser compare;
		Enumeration<Facture53> enumTabFact  = tabFact.elements();
		while(enumTabFact.hasMoreElements())
		{
			Facture53 fact = enumTabFact.nextElement();		
			compare = fact.getDateFacture().ajouterNombreDeJour(n);
			if (datecourante.estSuperieur(compare))
				supprimer(fact.getNumcde());
		}
	}
	
	public boolean plusExist(int n)
	{
		DateUser datecourante = new DateUser();
		DateUser compare;
		for(Facture53 fact : tabFact.values())
		{		
			compare = fact.getDateFacture().ajouterNombreDeJour(n);
			if (datecourante.estSuperieur(compare))
				return true;
		}
		return false;
	}

}
