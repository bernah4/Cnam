package Serie22;

import java.util.Enumeration;
//import java.util.Enumeration;
import java.util.Hashtable;
//import java.util.Set;

public class TableDesCommandes22 {
	
	private Hashtable<Integer,TableLigneDeCommande22<Integer>> tabDesCom = new Hashtable<Integer,TableLigneDeCommande22<Integer>>();

	
	public Hashtable<Integer, TableLigneDeCommande22<Integer>> getTabDesCom() {
		return tabDesCom;
	}

	public void setTabDesCom(Hashtable<Integer, TableLigneDeCommande22<Integer>> tabDesCom) {
		this.tabDesCom = tabDesCom;
	}

	public String toString() {
		String ret = "Liste des Commandes \n\n";
		for (TableLigneDeCommande22<Integer> cde : tabDesCom.values())
			ret = ret + cde.toString()+"\n";
		return ret;
	}
	
	public String cle() {
		String ret = "Liste des NumCommandes \n";
		for (Integer cle : tabDesCom.keySet())
			ret = ret + cle.toString()+"\n";
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
	public void ajouter (TableLigneDeCommande22<Integer> cde) {
		tabDesCom.put(cde.getNumcommande(), cde);
	}
	
	public void supprimer (Integer num) {
		tabDesCom.remove(num);
	}
	
	public TableLigneDeCommande22<Integer> retourner(Integer code){
		return tabDesCom.get(code);
	}
	
	public int taille() {
		return tabDesCom.size();
	}
	
	public void purge(int code) {
		Enumeration<TableLigneDeCommande22<Integer>> enumTabCde  = tabDesCom.elements();
		while(enumTabCde.hasMoreElements())
		{
			TableLigneDeCommande22<Integer> cde = enumTabCde.nextElement();
			cde.supprimer(code);
			if(cde.taille() == 0)
				supprimer(cde.getNumcommande());
		}
	}
}
