package Serie21;

import java.util.Vector;

public class TableLigneDeCommande21 {
	
	private Vector<LigneDeCommande> tabCom = new Vector<LigneDeCommande>();
	
	public TableLigneDeCommande21 ()
	{}

	public Vector<LigneDeCommande> getTabCom() {
		return tabCom;
	}

	public void setTabCom(Vector<LigneDeCommande> tabCom) {
		this.tabCom = tabCom;
	}
	
	public String toString()
	{
		String ret = "";
		for(LigneDeCommande ldc : tabCom)
			ret = ret+ldc.toString()+"\n";
		return ret;
	}
	
	public LigneDeCommande retourner(int code) {
		for(LigneDeCommande ldc : tabCom) {
			if(ldc.getCodearticle() == code)
				return ldc;
		}
		return null;
	}
	
	public LigneDeCommande retournerIndice(int indice) {
		return tabCom.get(indice);
	}
	
	public void ajouter(LigneDeCommande ldc) {
		tabCom.add(ldc);
	}
	
	public void supprimer(int code) {
		for (int i = 0; i < taille() ; i++) {
			if (tabCom.get(i).getCodearticle() == code)
				tabCom.remove(i);
		}
	}
	
	public int taille() {
		return tabCom.size();
	}
	
	public String facturer(TableArticles21 tabArt) {
		float prixHT = 0;
		String corp = "";
		String entete ="----------------------------------------------------- COMMANDE NºXXXX ----------------------------------------------------- \n\n"
					  +"code article\t"+"\tdesignation\t"+"\tPrix unitaire\t"+"\tquantite\t"+"\tsous total HT\t"+"sous total TTC"+"\n";
		for(LigneDeCommande ldc : this.getTabCom()) {
			corp = corp + ldc.facturer(tabArt)+"\n";	
			prixHT = prixHT+ ldc.prixTotal(tabArt);
		}
		float prixTVA= (int)(0.196*prixHT*100)/100.0f;
		String fin ="---------------------------------------------------------------------------------------------------------------------------\n"
		+"\t\t\t\t\t\t\t\t\t\t\t\t\ttotal HT  = "+prixHT+"\n"
		+"\t\t\t\t\t\t\t\t\t\t\t\t\tTVA 19,6% = "+prixTVA+"\n"
		+"\t\t\t\t\t\t\t\t\t\t\t\t\ttotal TTC = "+(prixHT+prixTVA)+"\n\n\n";
		return entete+corp+fin;
	}
}
