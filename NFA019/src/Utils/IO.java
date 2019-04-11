package Utils;

import java.util.Scanner;

public class IO {
	
	Scanner sc = new Scanner(System.in);
	
	public void affiche(String msg) {
		System.out.print(msg);
	}
	
	public int lireEntier(String s, int min, int max) {
		int data;
		affiche(s+"\n");
		data = sc.nextInt();
		while (data < min || data > max) {
			affiche("Saisie invalide");
			data = sc.nextInt();
		}
		sc.nextLine();
		return data;	
	}
	
	public String lireString(String s) {
		affiche(s);
		String z = sc.nextLine();
		return z;
	}

}
