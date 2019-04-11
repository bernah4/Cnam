package Utils;

import java.io.Serializable;
import java.util.Calendar;

public class DateUser implements Serializable {

	private int jour;
	private int mois;
	private int annee;

	public DateUser(int jour, int mois, int annee) {
		this.jour = jour;
		this.mois = mois;
		this.annee = annee;
	}

	public DateUser() {

		Calendar datSys = Calendar.getInstance();
		jour = datSys.get(Calendar.DAY_OF_MONTH);
		mois = datSys.get(Calendar.MONTH) + 1;
		annee = datSys.get(Calendar.YEAR);
	}

	public int getJour() {
		return jour;
	}

	public int getMois() {
		return mois;
	}

	public int getAnnee() {
		return annee;
	}

	public void setJour(int jour) {
		this.jour = jour;
	}

	public void setMois(int mois) {
		this.mois = mois;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public String toString() {
		return (jour + "/" + mois + "/" + annee);
	}

	public DateUser ajouterNombreDeJour(int nb) {
		int jour;
		int mois;
		int annee;
		
		jour = this.jour;
		mois = this.mois;
		annee = this.annee;
		jour = jour + nb;
		while (jour > maxJour(mois,annee))
		{
			jour = jour - maxJour(mois, annee);
			mois = mois + 1;
			if (mois > 12)
			{
				mois = 1;
				annee = annee + 1;
			}
			
		}
		return (new DateUser(jour, mois, annee));
	}

	public boolean estSuperieur(DateUser date) {
		int jour;
		int mois;
		int annee;
		int ret;
		
		jour = this.jour - date.getJour();
		mois = convertMoistoJour(this.mois, this.annee) - convertMoistoJour(date.getMois(), date.getAnnee()) ;
		annee =  this.annee -  date.getAnnee();
		
	
		ret = jour + mois + convertAnneetoJour(annee);
		return (ret >= 0);
	}

	public static int convertMoistoJour(int mois, int annee)
	{
		int ret = 0;
		
		for (int j = 1; j < mois; j++)
			ret = ret + maxJour(j,annee);
		
		return ret;
	}
	
	public static int convertAnneetoJour(int annee)
	{
		int ret = 0;
		for(int i = 0; i < annee; i++)
		{
			for (int j = 1; j < 13; j++)
				ret = ret + maxJour(j,annee);
		}
		return ret;
	}
	
	public static boolean verifdate(int jour, int mois, int annee) {
		return (jour <= maxJour(mois, annee));
	}

	public static boolean verifnaissance(int jour, int mois, int annee) {

		DateUser test = new DateUser();
		if (annee > test.getAnnee())
			return (false);
		if (annee == test.getAnnee() && mois > test.getMois())
			return (false);
		if (annee == test.getAnnee() && mois == test.getMois() && jour > test.getJour())
			return (false);
		return (true);
	}

	private static int maxJour(int mois, int annee) {
		switch (mois) {
		case 4:
		case 6:
		case 9:
		case 11:
			return 30;
		case 2:
			if (verifbis(annee))
				return 29;
			else
				return 28;
		default:
			return 31;
		}
	}

	public static boolean verifbis(int a) {
		return ((a % 4 == 0 && a % 100 != 0) || (a % 400 == 0));
	}

	public void hier() {
		int max = maxJour(this.mois - 1, this.annee);
		if (this.jour > 2)
			this.jour = this.jour - 1;
		else {
			this.jour = max;
			this.mois = this.mois - 1;
			if (this.mois < 1) {
				this.jour = 31;
				this.mois = 12;
				this.annee = this.annee - 1;
			}
		}
	}

	public DateUser newhier() {
		int jour;
		int mois;
		int annee;
		int max = maxJour(this.mois - 1, this.annee);
		if (this.jour > 2) {
			jour = this.jour - 1;
			mois = this.mois;
			annee = this.annee;
		} else {
			jour = max;
			mois = this.mois - 1;
			annee = this.annee;
			if (mois < 1) {
				jour = 31;
				mois = 12;
				annee = this.annee - 1;
			}
		}
		return (new DateUser(jour, mois, annee));
	}

	public void lendemain() {
		int max = maxJour(this.mois, this.annee);
		if (this.jour < max)
			this.jour = this.jour + 1;
		else {
			this.jour = 1;
			this.mois = this.mois + 1;
			if (this.mois > 12) {
				this.mois = 1;
				this.annee = this.annee + 1;
			}
		}
	}

	public DateUser newlendemain() {
		int jour;
		int mois;
		int annee;
		int max = maxJour(this.mois, this.annee);
		if (this.jour < max) {
			jour = this.jour + 1;
			mois = this.mois;
			annee = this.annee;
		} else {
			jour = 1;
			mois = this.mois + 1;
			annee = this.annee;
			if (mois > 12) {
				mois = 1;
				annee = this.annee + 1;
			}
		}
		return (new DateUser(jour, mois, annee));
	}

	public int age() {
		DateUser today = new DateUser();
		int agecalc;
		agecalc = today.getAnnee() - this.annee;
		if (this.mois == today.getMois()) {
			if (this.jour > today.getJour())
				agecalc--;
		} else if (this.mois > today.getMois())
			agecalc--;
		return agecalc;

	}

	public boolean avant(DateUser apres) {
		if (this.annee > apres.getAnnee())
			return (false);
		if (this.mois > apres.getMois())
			return (false);
		if (this.jour > apres.getJour())
			return (false);
		return (true);
	}

	private int Zeller() {

		int jz, mz, anneez, az, sz, z;
		jz = this.jour;
		anneez = this.annee;

		if (this.mois > 2)
			mz = this.mois - 2;
		else {
			mz = this.mois + 10;
			anneez--;
		}
		az = anneez % 100;
		sz = anneez / 100;

		z = ((int) (2.6 * mz - 0.2) + jz + az + az / 4 + sz / 4 - 2 * sz) % 7;

		if (z < 0)
			z = z + 7;
		return z;
	}

	public String jourDeSemaine() {

		int j = this.Zeller();
		switch (j) {
		case 0:
			return ("Dimanche");
		case 1:
			return ("Lundi");
		case 2:
			return ("Mardi");
		case 3:
			return ("Mercredi");
		case 4:
			return ("Jeudi");
		case 5:
			return ("Vendredi");
		case 6:
			return ("Samedi");
		default:
			return ("");
		}

	}

}
