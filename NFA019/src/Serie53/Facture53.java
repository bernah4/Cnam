package Serie53;

import java.io.Serializable;

import Utils.DateUser;

public class Facture53 implements Serializable{
	
	private String numcde;
	private DateUser dateFacture;
	private String txt;
	
	public Facture53()
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
	
}
