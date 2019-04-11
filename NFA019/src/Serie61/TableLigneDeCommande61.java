package Serie61;

import java.io.Serializable;
import java.util.Vector;

import MesInterfaces.InterfaceStruct;
import Utils.DateUser;;

public class TableLigneDeCommande61<TypeCode> implements InterfaceStruct<Integer,LigneDeCommande61>, Serializable{

	private TypeCode numcommande;
	private String numclient;
	private DateUser datecommande = new DateUser();
	private DateUser datefacture;
	private float valeurHT = 0f;
	private boolean etatfacture = false;
	
	private Vector<LigneDeCommande61> tabCom = new Vector<LigneDeCommande61>();

	public TableLigneDeCommande61() {
	}

	public TableLigneDeCommande61(TypeCode numcommande, Vector<LigneDeCommande61> tabCom) {
		super();
		this.numcommande = numcommande;
		this.tabCom = tabCom;
	}


	public TypeCode getNumcommande() {
		return numcommande;
	}

	public String getNumclient() {
		return numclient;
	}

	public DateUser getDatecommande() {
		return datecommande;
	}

	public DateUser getDatefacture() {
		return datefacture;
	}

	public float getValeurHT() {
		return valeurHT;
	}

	public boolean isEtatfacture() {
		return etatfacture;
	}

	public Vector<LigneDeCommande61> getTabCom() {
		return tabCom;
	}

	public void setNumcommande(TypeCode numcommande) {
		this.numcommande = numcommande;
	}

	public void setNumclient(String numclient) {
		this.numclient = numclient;
	}

	public void setDatecommande(DateUser datecommande) {
		this.datecommande = datecommande;
	}

	public void setDatefacture(DateUser datefacture) {
		this.datefacture = datefacture;
	}

	public void setValeurHT(float valeurHT) {
		this.valeurHT = valeurHT;
	}

	public void setEtatfacture(boolean etatfacture) {
		this.etatfacture = etatfacture;
	}

	public void setTabCom(Vector<LigneDeCommande61> tabCom) {
		this.tabCom = tabCom;
	}

	public String toString() {
		String ret = "Commande nº" + numcommande +" || Client "+numclient+" || Date commande " + datecommande + " || Etat de la commande :";
		if (etatfacture == true)
		{
			ret = ret + " payee || Date facturation" + datefacture ;
		}
		else 
			ret = ret + " non payee ";
		ret = ret + " || Montant totale :" +valeurHT + "\n"; 
		for (LigneDeCommande61 ldc : tabCom)
			ret = ret + ldc.toString() + "\n";
		return ret;
	}

	public LigneDeCommande61 retourner(Integer code) {
		for (LigneDeCommande61 ldc : tabCom) {
			if (ldc.getCodearticle() == code)
				return ldc;
		}
		return null;
	}

	public LigneDeCommande61 retournerIndice(Integer indice) {
		return tabCom.get(indice);
	}

	public void ajouter(LigneDeCommande61 ldc) {
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
	
	public float prixTotal(TableArticles61 tabArt)
	{
		float prixHT = 0f;
		for (LigneDeCommande61 ldc : this.getTabCom()) {
			prixHT = prixHT + ldc.prix(tabArt);
		}
		return prixHT;
	}
	
	public String facturer(TableArticles61 tabArt) {
		float prixHT = prixTotal(tabArt);
		String corp = "";
		String entete = "--------------------------- COMMANDE Nº"+numcommande+"------- passee le "+datecommande+"  ------- par "+numclient+" ---------------------------------------------- \n\n"
				+ "code article\t" + "\tdesignation\t" + "\tPrix unitaire\t" + "\tquantite\t" + "\tsous total HT\t"
				+ "sous total TTC" + "\n";
		for (LigneDeCommande61 ldc : this.getTabCom()) {
			corp = corp + ldc.facturer(tabArt) + "\n";
		}
		float prixTVA = (int) (0.196 * prixHT * 100) / 100.0f;
		String fin = "---------------------------------------------------------------------------------------------------------------------------\n"
				+ "\t\t\t\t\t\t\t\t\t\t\t\t\ttotal HT  = " + prixHT + "\n" + "\t\t\t\t\t\t\t\t\t\t\t\t\tTVA 19,6% = "
				+ prixTVA + "\n" + "\t\t\t\t\t\t\t\t\t\t\t\t\ttotal TTC = " + (prixHT + prixTVA) + "\n\n\n";
		return entete + corp + fin;
	}
	
	public String[] label()
	{
		String[] ret = {"Nº produit", "Designation", "Prix unitaire", "Type", "Qte -> reduc", "Reduction", "Achat", 
				"Prix HT", "Prix TTC"};
		return ret;
	}
	
	public String[][] data(TableArticles61 tabArt)
	{
		int i = taille();
		int j = 0;
		String[][] ret = new String[i][];
		for (LigneDeCommande61 ldc : tabCom)
		{
			ret[j] = ldc.facturerList(tabArt);
			j++;
		}
		return ret;
	}
	
	public String[] listspec()
	{
		if (etatfacture == true)
		{
			String[] ret = { ""+numcommande, numclient, "" + datecommande , "" +etatfacture, ""+datefacture
					, ""+valeurHT};
			return ret;
		}
		else 
		{
			String[] ret = { ""+numcommande, numclient, "" + datecommande , "" +etatfacture, "_"
					, ""+valeurHT};
			return ret;
		}
	}
	
}
