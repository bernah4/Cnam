package Utils;

import java.util.Scanner;

public class ClientJava2DateUser {

	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		int jour;
		int mois;
		int annee;
		
		affiche("Entrez votre date de naissance sous forme jj/mm/aaaa");
		do{
			jour = lireEntier("Entrez un jour valide svp", 1, 31);
			mois = lireEntier("Entrez un mois valide svp", 1, 12);
			annee = lireEntier("Entrez une annee valide svp", 0, Integer.MAX_VALUE);
			if (!DateUser.verifdate(jour, mois, annee))
				affiche("Date invalide");
			else if (!DateUser.verifnaissance(jour, mois, annee))
				affiche("Date de naissance invalide !");
		}while(!DateUser.verifdate(jour, mois, annee) || !DateUser.verifnaissance(jour, mois, annee));
		
		DateUser an = new DateUser(jour, mois, annee);
		DateUser an2 = new DateUser(1, 1, annee+1);
		affiche(""+an2.estSuperieur(an));
		affiche(""+an.avant(an2));
		//affiche(""+an.avant(an2));
		//affiche("Vous avez "+an.age()+" ans !");
		//affiche("Vous etes ne un "+ an.jourDeSemaine());
		}
	
	public static void affiche(String msg) {
		System.out.println(msg);
	}
	
	public static int lireEntier(String s, int min, int max) {
		int data;
		System.out.println(s);
		data = sc.nextInt();
		while (data < min || data > max) {
			System.out.println("Saisie invalide");
			data = sc.nextInt();
		}
		return data;	
	}
}
