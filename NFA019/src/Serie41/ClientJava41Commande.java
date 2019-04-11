package Serie41;

import IConsole.IO;
import MesExceptions.AbandonException;
import MesExceptions.MenuPrecedentException;
import MesExceptions.RetourException;

public class ClientJava41Commande {

	static IO es = new IO();

	public static void main(String[] args) {

		GestionTableArticle41 gta = new GestionTableArticle41();
		GestionTableDesCommandes41 gtc = new GestionTableDesCommandes41();
		TableArticles41 tabArt = new TableArticles41();
		TableDesCommandes41 tabCde = new TableDesCommandes41();
		String msg = "\n ---------------------- MENU GENERAL ---------------------- \n"
				+ "\t gestion table des articles .................... 1\n"
				+ "\t gestion des commandes      .................... 2\n"
				+ "\t finir                      .................... 0\n" + "votre choix ?";

		int choix;
		do {
			try {
				choix = menuChoix(msg, 0, 2);
				switch (choix) {
				case 1:
					gta.menuGeneral(tabArt, tabCde);
					break;
				case 2:
					gtc.menuGeneral(tabCde, tabArt);
					break;
				case 0:
					break;
				}
			} catch (AbandonException ae) {
				choix = 0;
			}catch (RetourException mp) {
				choix = -1;
				boolean quit = es.saisieOuiNon("Pas de menu precedent ! Quitter l'application ?");
				if (quit)
					choix = 0;}
		} while (choix != 0);

		es.affiche("------------- A bientot chez croquette-corp ! -------------");

	}

	public static int menuChoix(String msg, int min, int max) throws AbandonException,RetourException {
		try {
			return (Integer) es.saisie(msg, min, max);
			}
			catch (MenuPrecedentException mp) {
				throw new RetourException();
			}
	}

}
