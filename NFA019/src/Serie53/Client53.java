package Serie53;

import java.io.Serializable;

import Utils.DateUser;

public class Client53 implements Serializable{
	private String numclient;
	private float chiffre_affaire;
	private int nb_cde;
	private String name;
	private String lieu;
	private DateUser date;
	
	public Client53(String name, String lieu, int numero)
	{
		this.chiffre_affaire = 0;
		this.nb_cde = 0;
		this.name = name;
		this.lieu = lieu;
		this.date = new DateUser();
		this.numclient = name.substring(0, 3) + numero;
	}

	public String getNumclient() {
		return numclient;
	}

	public float getChiffre_affaire() {
		return chiffre_affaire;
	}

	public int getNb_cde() {
		return nb_cde;
	}

	public String getName() {
		return name;
	}

	public String getLieu() {
		return lieu;
	}

	public DateUser getDate() {
		return date;
	}

	public void setNumclient(String numclient) {
		this.numclient = numclient;
	}

	public void setChiffre_affaire(float chiffre_affaire) {
		this.chiffre_affaire = chiffre_affaire;
	}

	public void setNb_cde(int nb_cde) {
		this.nb_cde = nb_cde;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public void setDate(DateUser date) {
		this.date = date;
	}
	
	public String toString()
	{
		String ret = "Client nº: "+this.numclient+" ("+this.name+") from : "+this.lieu+" create : "+ this.date+"\n"
				+"\tchiffre d'affaire : "+ this.chiffre_affaire+"\n"
				+"\tnombre de commande : "+ this.nb_cde;
		return ret;
	}

}
