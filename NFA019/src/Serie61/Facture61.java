package Serie61;

import java.io.Serializable;

import Utils.DateUser;

public class Facture61 implements Serializable{
	
	private String numcde;
	private DateUser dateFacture;
	private String txt;
	private String[][] data;
	private int size;
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String[][] getData() {
		return data;
	}

	public void setData(String[][] data) {
		this.data = data;
	}

	public Facture61()
	{}
	
	public String getNumcde() {
		return numcde;
	}
	
	public DateUser getDateFacture() {
		return dateFacture;
	}

	public String getTxt() {
		return txt;
	}

	public void setNumcde(String numcde) {
		this.numcde = numcde;
	}

	public void setDateFacture(DateUser dateFacture) {
		this.dateFacture = dateFacture;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public String toString()
	{
		String ret = "";
		ret = "Date de facturation :"+dateFacture+"\n"+txt;
		return ret;
	}
	
	public String[] listespec()
	{
		String[] ret = {""+getNumcde(), ""+getDateFacture()};
		return ret;

	}
	
	public void data(TableLigneDeCommande61<String> cde, TableArticles61 tabArt)
	{
		int i = cde.taille()+7;
		int j = 0;
		float prixHT = cde.prixTotal(tabArt);
		float prixTVA = (int) (0.196 * prixHT * 100) / 100.0f;
		String[][] ret = new String[i][];
		String[] begin = {"", "Commande nº "+cde.getNumcommande(), "", "Fait le", ""+cde.getDatefacture(), "",
				"Client nº", ""+cde.getNumclient(), ""};
		ret[j] = begin;
		j++;
		String[] et = {"************", "***********************************", "************", "*********", "**************", "***********", "***********", 
				"***********", "***********"};
		ret[j] = et;
		j++;
		ret[j] = cde.label();
		j++;
		for (LigneDeCommande61 ldc : cde.getTabCom())
		{
			ret[j] = ldc.facturerList(tabArt);
			j++;
		}
		ret[j] = et;
		j++;
		String[] ht = {"", "", "", "", "", "", "Total HT", 
				""+prixHT, ""};
		ret[j] = ht;
		j++;
		String[] tva = {"", "", "", "", "", "", "TVA", 
				""+prixTVA, ""};
		ret[j] = tva;
		j++;
		String[] ttc = {"", "", "", "", "", "", "Total TTC", 
				""+(prixHT+prixTVA), ""};
		ret[j] = ttc;
		j++;
		this.size = j;
		this.data = ret;
	}
	
}
