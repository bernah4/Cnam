package Serie42;

import java.util.Vector;

import MesInterfaces.InterfaceStruct;
import Utils.DateUser;;

public class TableLigneDeCommande42<TypeCode> implements InterfaceStruct<Integer,LigneDeCommande42>{

	TypeCode numcommande;
	DateUser datecommande = new DateUser();
	private Vector<LigneDeCommande42> tabCom = new Vector<LigneDeCommande42>();

	public TableLigneDeCommande42() {
	}

	public TableLigneDeCommande42(TypeCode numcommande, DateUser datecommande, Vector<LigneDeCommande42> tabCom) {
		super();
		this.numcommande = numcommande;
		this.datecommande = datecommande;
		this.tabCom = tabCom;
	}

	public TypeCode getNumcommande() {
		return numcommande;
	}

	public DateUser getDatecommande() {
		return datecommande;
	}

	public Vector<LigneDeCommande42> getTabCom() {
		return tabCom;
	}

	public void setNumcommande(TypeCode numcommande) {
		this.numcommande = numcommande;
	}

	public void setDatecommande(DateUser datecommande) {
		this.datecommande = datecommande;
	}

	public void setTabCom(Vector<LigneDeCommande42> tabCom) {
		this.tabCom = tabCom;
	}

	public String toString() {
		String ret = "Commande nº" + numcommande +" Date commande " + datecommande + "\n";
		for (LigneDeCommande42 ldc : tabCom)
			ret = ret + ldc.toString() + "\n";
		return ret;
	}

	public LigneDeCommande42 retourner(Integer code) {
		for (LigneDeCommande42 ldc : tabCom) {
			if (ldc.getCodearticle() == code)
				return ldc;
		}
		return null;
	}

	public LigneDeCommande42 retournerIndice(Integer indice) {
		return tabCom.get(indice);
	}

	public void ajouter(LigneDeCommande42 ldc) {
		tabCom.add(ldc);
	}

	public void supprimer(Integer code) {
		for (int i = 0; i < taille(); i++) {
			if (tabCom.get(i).getCodearticle() == code)
				tabCom.remove(i);
		}
	}

	public int taille() {
		return tabCom.size();
	}
	
	public String facturer(TableArticles42 tabArt) {
		float prixHT = 0;
		String corp = "";
		String entete = "----------------------------------------------------- COMMANDE NºXXXX ----------------------------------------------------- \n\n"
				+ "code article\t" + "\tdesignation\t" + "\tPrix unitaire\t" + "\tquantite\t" + "\tsous total HT\t"
				+ "sous total TTC" + "\n";
		for (LigneDeCommande42 ldc : this.getTabCom()) {
			corp = corp + ldc.facturer(tabArt) + "\n";
			prixHT = prixHT + ldc.prix(tabArt);
		}
		float prixTVA = (int) (0.196 * prixHT * 100) / 100.0f;
		String fin = "---------------------------------------------------------------------------------------------------------------------------\n"
				+ "\t\t\t\t\t\t\t\t\t\t\t\t\ttotal HT  = " + prixHT + "\n" + "\t\t\t\t\t\t\t\t\t\t\t\t\tTVA 19,6% = "
				+ prixTVA + "\n" + "\t\t\t\t\t\t\t\t\t\t\t\t\ttotal TTC = " + (prixHT + prixTVA) + "\n\n\n";
		return entete + corp + fin;
	}
}
