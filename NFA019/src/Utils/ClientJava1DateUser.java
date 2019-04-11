package Utils;

import java.util.Scanner;

public class ClientJava1DateUser {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		int jour;
		int mois;
		int annee;
		
		affiche("Entrez une date sous forme jj/mm/aaaa");
		do{
			jour = lireEntier("Entrez un jour valide svp", 1, 31);
			mois = lireEntier("Entrez un mois valide svp", 1, 12);
			annee = lireEntier("Entrez une annee valide svp", 0, Integer.MAX_VALUE);
			if (!DateUser.verifdate(jour, mois, annee))
				affiche("Date invalide");
		}while(!DateUser.verifdate(jour, mois, annee));
		
		DateUser an = new DateUser(jour, mois, annee);
		affiche("Date de test = "+an.toString());
		DateUser an2 = an.newlendemain();
		DateUser an3 = an.newhier();
		affiche("demain = "+an2.toString());
		affiche("hier = "+an3.toString());
		//System.out.println(DateUser.verifdate(31, 10, 1991));

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
