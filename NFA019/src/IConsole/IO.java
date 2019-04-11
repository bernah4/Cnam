package IConsole;

import java.util.InputMismatchException;
import java.util.Scanner;
import MesExceptions.AbandonException;
import MesExceptions.MenuPrecedentException;
import MesInterfaces.InterfaceIO;

public class IO implements InterfaceIO{

	private Scanner sc = new Scanner(System.in);

	public void affiche(String msg) {
		System.out.print(msg);
	}

	public boolean saisieOuiNon(String msg)
	{
		affiche(msg);
		char test = sc.next().charAt(0);
		return (test == 'o' || test == 'O'); 	
	}
	
	public int saisie(String s, int min, int max) throws AbandonException,MenuPrecedentException{
		int data;
		affiche(s + "\n");
		do {
			try {
				data = sc.nextInt();
				sc.nextLine();
				if (data >= min && data <= max) 	
					return data;
				throw new AbandonException();
			} catch (InputMismatchException ie) {
				affiche("Saisie non numerique ! Recommencez !");
				sc.nextLine();
			}
			catch (AbandonException ae) {
				boolean abandon = saisieOuiNon("Voulez-vous abandonner ? o/n");
				if (abandon)
					throw ae;
				boolean menu = saisieOuiNon("Voulez-vous revenir au menu precedent ? o/n");
				if (menu)
					throw new MenuPrecedentException();
				else
					affiche("Ressaisissez svp !");
			}
		} while (true);
	}

	public int saisie(String s, int min) throws AbandonException,MenuPrecedentException{
		return saisie( s, min, Integer.MAX_VALUE);
	}
	
	public float saisie(String s, float min, float max) throws AbandonException,MenuPrecedentException{
		float data;
		affiche(s + "\n");
		do {
			try {
				data = sc.nextInt();
				sc.nextLine();
				if (data >= min && data <= max) 	
					return data;
				throw new AbandonException();
			} catch (InputMismatchException ie) {
				affiche("Saisie non numerique ! Recommencez !");
				sc.nextLine();
			}
			catch (AbandonException ae) {
				boolean abandon = saisieOuiNon("Voulez-vous abandonner ? o/n");
				if (abandon)
					throw ae;
				boolean menu = saisieOuiNon("Voulez-vous revenir au menu precedent ? o/n");
				if (menu)
					throw new MenuPrecedentException();
				else
					affiche("Ressaisissez svp !");
			}
		} while (true);
	}

	public float saisie(String s, float min) throws AbandonException,MenuPrecedentException{
		return saisie( s, min, Float.MAX_VALUE);
	}
	
	public String saisie(String s, boolean ok) throws AbandonException,MenuPrecedentException
	{
		do {
			
		affiche(s);
		try {
		String saisie = sc.nextLine();
		if (saisie.equals("") && ok == true)
				throw new MenuPrecedentException();
		if (saisie.equals(""))
			throw new AbandonException();
		}catch (AbandonException ae) {
			boolean abandon = saisieOuiNon("Voulez-vous abandonner ? o/n");
			if (abandon)
				throw ae;
			boolean menu = saisieOuiNon("Voulez-vous revenir au menu precedent ? o/n");
			if (menu)
				throw new MenuPrecedentException();
			else
				affiche("Ressaisissez svp !");
		}catch (MenuPrecedentException ae) {
			affiche("Saisie obligatoire");
		}
		}while(true);
	}
	
	public String saisie(String s) throws AbandonException,MenuPrecedentException{
		return saisie(s, false);
	}

}
