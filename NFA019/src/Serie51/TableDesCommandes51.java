package Serie51;

import java.io.Serializable;
import java.util.Enumeration;
//import java.util.Enumeration;
import java.util.Hashtable;
//import java.util.Set;

import MesInterfaces.InterfaceStruct;

public class TableDesCommandes51 implements InterfaceStruct<String,TableLigneDeCommande51<String>>, Serializable{
	
	private Hashtable<String,TableLigneDeCommande51<String>> tabDesCom = new Hashtable<String,TableLigneDeCommande51<String>>();

	
	public Hashtable<String, TableLigneDeCommande51<String>> getTabDesCom() {
		return tabDesCom;
	}

	public void setTabDesCom(Hashtable<String, TableLigneDeCommande51<String>> tabDesCom) {
		this.tabDesCom = tabDesCom;
	}

	public String toString() {
		String ret = "Liste des Commandes \n\n";
		for (TableLigneDeCommande51<String> cde : tabDesCom.values())
			ret = ret + cde.toString()+"\n";
		return ret;
	}
	
	public String cle() {
		String ret = "Liste des NumCommandes \n";
		for (String cle : tabDesCom.keySet())
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
	public void ajouter (TableLigneDeCommande51<String> cde) {
		tabDesCom.put(cde.getNumcommande(), cde);
	}
	
	public void supprimer (String num) {
		tabDesCom.remove(num);
	}
	
	public TableLigneDeCommande51<String> retourner(String code){
		return tabDesCom.get(code);
	}
	
	public int taille() {
		return tabDesCom.size();
	}
	
	public void purge(int code) {
		Enumeration<TableLigneDeCommande51<String>> enumTabCde  = tabDesCom.elements();
		while(enumTabCde.hasMoreElements())
		{
			TableLigneDeCommande51<String> cde = enumTabCde.nextElement();
			cde.supprimer(code);
			if(cde.taille() == 0)
				supprimer(cde.getNumcommande());
		}
	}
}
