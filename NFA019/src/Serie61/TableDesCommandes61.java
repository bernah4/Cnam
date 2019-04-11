package Serie61;

import java.io.Serializable;
import java.util.Enumeration;
//import java.util.Enumeration;
import java.util.TreeMap;
//import java.util.Set;

import MesInterfaces.InterfaceStruct;
import Utils.DateUser;

public class TableDesCommandes61 implements InterfaceStruct<String,TableLigneDeCommande61<String>>, Serializable{
	
	private TreeMap<String,TableLigneDeCommande61<String>> tabDesCom = new TreeMap<String,TableLigneDeCommande61<String>>();

	
	public TreeMap<String, TableLigneDeCommande61<String>> getTabDesCom() {
		return tabDesCom;
	}

	public void setTabDesCom(TreeMap<String, TableLigneDeCommande61<String>> tabDesCom) {
		this.tabDesCom = tabDesCom;
	}

	public String toString() {
		String ret = "Liste des Commandes \n\n";
		for (TableLigneDeCommande61<String> cde : tabDesCom.values())
			ret = ret + cde.toString()+"\n";
		return ret;
	}
	
	public String cle() {
		String ret = "Liste des NumCommandes \n";
		for (String cle : tabDesCom.keySet())
			ret = ret + cle.toString()+"\n";
		return ret;
	}
	
	public String cleNonFacture() {
		String ret = "Liste des NumCommandes non facturees \n";
		for (TableLigneDeCommande61<String> cde : tabDesCom.values())
			if (!cde.isEtatfacture())
				ret = ret + cde.getNumcommande()+"\n";
		return ret;
	}
	
	public String cleFacture() {
		String ret = "Liste des NumCommandes facturees \n";
		for (TableLigneDeCommande61<String> cde : tabDesCom.values())
			if (cde.isEtatfacture())
				ret = ret + cde.getNumcommande()+"\n";
		return ret;
	}
	/*
	public String cle() {
		String ret = "Liste des NumCommandes";
		Enumeration<Integer> enumcle = tabDesCom.keys();
		while(enumcle.hasMoreElements())
			ret = ret+enumcle.nextElement().toString();
		return ret;
	}
	
	public Set<Integer> cle2(){
		return tabDesCom.keySet();
	}
	*/
	public void ajouter (TableLigneDeCommande61<String> cde) {
		tabDesCom.put(cde.getNumcommande(), cde);
	}
	
	public void supprimer (String num) {
		tabDesCom.remove(num);
	}
	
	public TableLigneDeCommande61<String> retourner(String code){
		return tabDesCom.get(code);
	}
	
	public int taille() {
		return tabDesCom.size();
	}
	
	public int tailleFacture() {
		int ret = 0;
		for (TableLigneDeCommande61<String> cde : tabDesCom.values())
			if (cde.isEtatfacture())
				ret = ret + 1;
		return ret;
	}
	
	public int tailleNonFacture() {
		int ret = 0;
		for (TableLigneDeCommande61<String> cde : tabDesCom.values())
			if (!cde.isEtatfacture())
				ret = ret + 1;
		return ret;
	}
	
	public int tailleClient(String numclient) {
		int ret = 0;
		for (TableLigneDeCommande61<String> cde : tabDesCom.values())
			if (cde.getNumclient().equals(numclient))
				ret = ret + 1;
		return ret;
	}
	
	public String cdeClient(String numclient)
	{
		String ret = "";
		String key = tabDesCom.firstKey();
		while(key != null)
		{
			TableLigneDeCommande61<String> cde = retourner(key);
			if (cde.getNumclient().equals(numclient))
				ret = ret + cde.toString();
			key = tabDesCom.higherKey(key);
		}
		return ret;
	}
	
	public void purgeClient(String numclient)
	{
		String key = tabDesCom.firstKey();
		while(key != null)
		{
			TableLigneDeCommande61<String> cde = retourner(key);
			if (cde.getNumclient().equals(numclient))
				supprimer(key);
			key = tabDesCom.higherKey(key);
		}
	}
	
	public void purge(int code) {
		
		String key = tabDesCom.firstKey();
		while(key != null)
		{
			TableLigneDeCommande61<String> cde = retourner(key);
			cde.supprimer(code);
			if(cde.taille() == 0)
				supprimer(key);
			key = tabDesCom.higherKey(key);
		}
	}
	
	public void autoPurge(int n, int mode)
	{
		DateUser datecourante = new DateUser();
		DateUser compare;
		String key = tabDesCom.firstKey();
		while(key != null)
		{
			TableLigneDeCommande61<String> cde = retourner(key);
			compare = cde.getDatecommande().ajouterNombreDeJour(n);
			if ((datecourante.estSuperieur(compare) && !cde.isEtatfacture() && mode == 1)
					|| (datecourante.estSuperieur(compare) && cde.isEtatfacture() && mode == 2))
				supprimer(key);
			key = tabDesCom.higherKey(key);
		}
	}
	
	public boolean plusExist(int n)
	{
		DateUser datecourante = new DateUser();
		DateUser compare;
		for(TableLigneDeCommande61<String> cde : tabDesCom.values())
		{
			compare = cde.getDatecommande().ajouterNombreDeJour(n);
			if (datecourante.estSuperieur(compare))
					return true;
		}
		return false;
	}
	
	public String[] label()
	{
		String[] ret = {"Nº commande", "Nº client", "Date commande", "Etat commande", "Date facturation", "Montant total HT"};
		return ret;
	}
	
	public String[][] data()
	{
		int i = taille();
		int j = 0;
		String[][] ret = new String[i][];
		for (TableLigneDeCommande61<String> cde : tabDesCom.values())
		{
			ret[j] = cde.listspec();
			j++;
		}
		return ret;
	}
	
	public String[][] dataClient(String numclient)
	{
		int i = tailleClient(numclient);
		int j = 0;
		String[][] ret = new String[i][];
		for (TableLigneDeCommande61<String> cde : tabDesCom.values())
		{
			if (cde.getNumclient().equals(numclient))
			{
				ret[j] = cde.listspec();
				j++;
			}
		}
		return ret;
	}
	
	public String[][] dataFact()
	{
		int i = tailleFacture();
		int j = 0;
		String[][] ret = new String[i][];
		for (TableLigneDeCommande61<String> cde : tabDesCom.values())
		{
			if (cde.isEtatfacture())
			{
				ret[j] = cde.listspec();
				j++;
			}
		}
		return ret;
	}
	
	public String[][] dataNonFact()
	{
		int i = tailleNonFacture();
		int j = 0;
		String[][] ret = new String[i][];
		for (TableLigneDeCommande61<String> cde : tabDesCom.values())
		{
			if (!cde.isEtatfacture())
			{
				ret[j] = cde.listspec();
				j++;
			}
		}
		return ret;
	}
	
}
