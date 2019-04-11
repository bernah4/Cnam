package Serie53;

import java.io.Serializable;
import java.util.Enumeration;
//import java.util.Enumeration;
import java.util.Hashtable;
//import java.util.Set;

import MesInterfaces.InterfaceStruct;
import Utils.DateUser;

public class TableDesCommandes53 implements InterfaceStruct<String,TableLigneDeCommande53<String>>, Serializable{
	
	private Hashtable<String,TableLigneDeCommande53<String>> tabDesCom = new Hashtable<String,TableLigneDeCommande53<String>>();

	
	public Hashtable<String, TableLigneDeCommande53<String>> getTabDesCom() {
		return tabDesCom;
	}

	public void setTabDesCom(Hashtable<String, TableLigneDeCommande53<String>> tabDesCom) {
		this.tabDesCom = tabDesCom;
	}

	public String toString() {
		String ret = "Liste des Commandes \n\n";
		for (TableLigneDeCommande53<String> cde : tabDesCom.values())
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
		for (TableLigneDeCommande53<String> cde : tabDesCom.values())
			if (!cde.isEtatfacture())
				ret = ret + cde.getNumcommande()+"\n";
		return ret;
	}
	
	public String cleFacture() {
		String ret = "Liste des NumCommandes facturees \n";
		for (TableLigneDeCommande53<String> cde : tabDesCom.values())
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
	public void ajouter (TableLigneDeCommande53<String> cde) {
		tabDesCom.put(cde.getNumcommande(), cde);
	}
	
	public void supprimer (String num) {
		tabDesCom.remove(num);
	}
	
	public TableLigneDeCommande53<String> retourner(String code){
		return tabDesCom.get(code);
	}
	
	public int taille() {
		return tabDesCom.size();
	}
	
	public int tailleFacture() {
		int ret = 0;
		for (TableLigneDeCommande53<String> cde : tabDesCom.values())
			if (cde.isEtatfacture())
				ret = ret + 1;
		return ret;
	}
	
	public int tailleNonFacture() {
		int ret = 0;
		for (TableLigneDeCommande53<String> cde : tabDesCom.values())
			if (!cde.isEtatfacture())
				ret = ret + 1;
		return ret;
	}
	
	public String cdeClient(String numclient)
	{
		String ret = "";
		Enumeration<TableLigneDeCommande53<String>> enumTabCde  = tabDesCom.elements();
		while(enumTabCde.hasMoreElements())
		{
			TableLigneDeCommande53<String> cde = enumTabCde.nextElement();
			if (cde.getNumclient().equals(numclient))
				ret = ret + cde.toString();
		}
		return ret;
	}
	
	public void purgeClient(String numclient)
	{
		Enumeration<TableLigneDeCommande53<String>> enumTabCde  = tabDesCom.elements();
		while(enumTabCde.hasMoreElements())
		{
			TableLigneDeCommande53<String> cde = enumTabCde.nextElement();
			if (cde.getNumclient().equals(numclient))
				supprimer(cde.getNumcommande());
		}
	}
	
	public void purge(int code) {
		Enumeration<TableLigneDeCommande53<String>> enumTabCde  = tabDesCom.elements();
		while(enumTabCde.hasMoreElements())
		{
			TableLigneDeCommande53<String> cde = enumTabCde.nextElement();
			cde.supprimer(code);
			if(cde.taille() == 0)
				supprimer(cde.getNumcommande());
		}
	}
	
	public void autoPurge(int n, int mode)
	{
		DateUser datecourante = new DateUser();
		DateUser compare;
		Enumeration<TableLigneDeCommande53<String>> enumTabCde  = tabDesCom.elements();
		while(enumTabCde.hasMoreElements())
		{
			TableLigneDeCommande53<String> cde = enumTabCde.nextElement();
			compare = cde.getDatecommande().ajouterNombreDeJour(n);
			if ((datecourante.estSuperieur(compare) && !cde.isEtatfacture() && mode == 1)
					|| (datecourante.estSuperieur(compare) && cde.isEtatfacture() && mode == 2))
				supprimer(cde.getNumcommande());
		}
	}
	
	public boolean plusExist(int n)
	{
		DateUser datecourante = new DateUser();
		DateUser compare;
		for(TableLigneDeCommande53<String> cde : tabDesCom.values())
		{
			compare = cde.getDatecommande().ajouterNombreDeJour(n);
			if (datecourante.estSuperieur(compare))
					return true;
		}
		return false;
	}
}
