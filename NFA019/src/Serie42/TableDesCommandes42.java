package Serie42;

import java.util.Enumeration;
//import java.util.Enumeration;
import java.util.Hashtable;
//import java.util.Set;

import MesInterfaces.InterfaceStruct;

public class TableDesCommandes42 implements InterfaceStruct<Integer,TableLigneDeCommande42<Integer>>{
	
	private Hashtable<Integer,TableLigneDeCommande42<Integer>> tabDesCom = new Hashtable<Integer,TableLigneDeCommande42<Integer>>();

	
	public Hashtable<Integer, TableLigneDeCommande42<Integer>> getTabDesCom() {
		return tabDesCom;
	}

	public void setTabDesCom(Hashtable<Integer, TableLigneDeCommande42<Integer>> tabDesCom) {
		this.tabDesCom = tabDesCom;
	}

	public String toString() {
		String ret = "Liste des Commandes \n\n";
		for (TableLigneDeCommande42<Integer> cde : tabDesCom.values())
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
	public void ajouter (TableLigneDeCommande42<Integer> cde) {
		tabDesCom.put(cde.getNumcommande(), cde);
	}
	
	public void supprimer (Integer num) {
		tabDesCom.remove(num);
	}
	
	public TableLigneDeCommande42<Integer> retourner(Integer code){
		return tabDesCom.get(code);
	}
	
	public int taille() {
		return tabDesCom.size();
	}
	
	public void purge(int code) {
		Enumeration<TableLigneDeCommande42<Integer>> enumTabCde  = tabDesCom.elements();
		while(enumTabCde.hasMoreElements())
		{
			TableLigneDeCommande42<Integer> cde = enumTabCde.nextElement();
			cde.supprimer(code);
			if(cde.taille() == 0)
				supprimer(cde.getNumcommande());
		}
	}
}
