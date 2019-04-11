package Serie22;

import java.util.Vector;
import Serie21.LigneDeCommande;
import Utils.DateUser;;

public class TableLigneDeCommande22<TypeCode> {

	TypeCode numcommande;
	DateUser datecommande = new DateUser();
	private Vector<LigneDeCommande> tabCom = new Vector<LigneDeCommande>();

	public TableLigneDeCommande22() {
	}

	public TableLigneDeCommande22(TypeCode numcommande, DateUser datecommande, Vector<LigneDeCommande> tabCom) {
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

	public Vector<LigneDeCommande> getTabCom() {
		return tabCom;
	}

	public void setNumcommande(TypeCode numcommande) {
		this.numcommande = numcommande;
	}

	public void setDatecommande(DateUser datecommande) {
		this.datecommande = datecommande;
	}

	public void setTabCom(Vector<LigneDeCommande> tabCom) {
		this.tabCom = tabCom;
	}

	public String toString() {
		String ret = "Commande nº" + numcommande +" Date commande " + datecommande + "\n";
		for (LigneDeCommande ldc : tabCom)
			ret = ret + ldc.toString() + "\n";
		return ret;
	}

	public LigneDeCommande retourner(int code) {
		for (LigneDeCommande ldc : tabCom) {
			if (ldc.getCodearticle() == code)
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
		for (int i = 0; i < taille(); i++) {
			if (tabCom.get(i).getCodearticle() == code)
				tabCom.remove(i);
		}
	}

	public int taille() {
		return tabCom.size();
	}
	
	public String facturer(TableArticles22 tabArt) {
		float prixHT = 0;
		String corp = "";
		String entete = "----------------------------------------------------- COMMANDE NºXXXX ----------------------------------------------------- \n\n"
				+ "code article\t" + "\tdesignation\t" + "\tPrix unitaire\t" + "\tquantite\t" + "\tsous total HT\t"
				+ "sous total TTC" + "\n";
		for (LigneDeCommande ldc : this.getTabCom()) {
			corp = corp + ldc.newfacturer(tabArt) + "\n";
			prixHT = prixHT + ldc.newprixTotal(tabArt);
		}
		float prixTVA = (int) (0.196 * prixHT * 100) / 100.0f;
		String fin = "---------------------------------------------------------------------------------------------------------------------------\n"
				+ "\t\t\t\t\t\t\t\t\t\t\t\t\ttotal HT  = " + prixHT + "\n" + "\t\t\t\t\t\t\t\t\t\t\t\t\tTVA 19,6% = "
				+ prixTVA + "\n" + "\t\t\t\t\t\t\t\t\t\t\t\t\ttotal TTC = " + (prixHT + prixTVA) + "\n\n\n";
		return entete + corp + fin;
	}
}
