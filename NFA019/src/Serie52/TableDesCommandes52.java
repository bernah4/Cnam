package Serie52;

import java.io.Serializable;
import java.util.Enumeration;
//import java.util.Enumeration;
import java.util.Hashtable;
//import java.util.Set;

import MesInterfaces.InterfaceStruct;

public class TableDesCommandes52 implements InterfaceStruct<String,TableLigneDeCommande52<String>>, Serializable{
	
	private Hashtable<String,TableLigneDeCommande52<String>> tabDesCom = new Hashtable<String,TableLigneDeCommande52<String>>();

	
	public Hashtable<String, TableLigneDeCommande52<String>> getTabDesCom() {
		return tabDesCom;
	}

	public void setTabDesCom(Hashtable<String, TableLigneDeCommande52<String>> tabDesCom) {
		this.tabDesCom = tabDesCom;
	}

	public String toString() {
		String ret = "Liste des Commandes \n\n";
		for (TableLigneDeCommande52<String> cde : tabDesCom.values())
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
		for (TableLigneDeCommande52<String> cde : tabDesCom.values())
			if (!cde.isEtatfacture())
				ret = ret + cde.getNumcommande()+"\n";
		return ret;
	}
	
	public String cleFacture() {
		String ret = "Liste des NumCommandes facturees \n";
		for (TableLigneDeCommande52<String> cde : tabDesCom.values())
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
	public void ajouter (TableLigneDeCommande52<String> cde) {
		tabDesCom.put(cde.getNumcommande(), cde);
	}
	
	public void supprimer (String num) {
		tabDesCom.remove(num);
	}
	
	public TableLigneDeCommande52<String> retourner(String code){
		return tabDesCom.get(code);
	}
	
	public int taille() {
		return tabDesCom.size();
	}
	
	public int tailleFacture() {
		int ret = 0;
		for (TableLigneDeCommande52<String> cde : tabDesCom.values())
			if (cde.isEtatfacture())
				ret = ret + 1;
		return ret;
	}
	
	public int tailleNonFacture() {
		int ret = 0;
		for (TableLigneDeCommande52<String> cde : tabDesCom.values())
			if (!cde.isEtatfacture())
				ret = ret + 1;
		return ret;
	}
	
	public void purge(int code) {
		Enumeration<TableLigneDeCommande52<String>> enumTabCde  = tabDesCom.elements();
		while(enumTabCde.hasMoreElements())
		{
			TableLigneDeCommande52<String> cde = enumTabCde.nextElement();
			cde.supprimer(code);
			if(cde.taille() == 0)
				supprimer(cde.getNumcommande());
		}
	}
}
