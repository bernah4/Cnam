package Serie52;

import IPane.IO;
import MesExceptions.AbandonException;
import MesExceptions.MenuPrecedentException;
import MesExceptions.RetourException;

public class ClientJava52 {

	static IO es = new IO();

	public static void main(String[] args) {

		GestionTableArticle52 gta = new GestionTableArticle52("TableArticles.data");
		GestionTableDesCommandes52 gtc = new GestionTableDesCommandes52("TableDesCommandes.data");
		GestionTableFactures52 gtf = new GestionTableFactures52("TableFacture.data");
		TableArticles52 tabArt = gta.recuperer();
		TableDesCommandes52 tabCde = gtc.recuperer();
		TableFactures52 tabFact = gtf.recuperer();
		String msg = "\n ---------------------- MENU GENERAL ---------------------- \n"
				+ "\t gestion table des articles .................... 1\n"
				+ "\t gestion des commandes      .................... 2\n"
				+ "\t gestion des factures       .................... 3\n"
				+ "\t finir                      .................... 0\n" + "votre choix ?";

		int choix;
		do {
			try {
				choix = menuChoix(msg, 0, 3);
				switch (choix) {
				case 1:
					gta.menuGeneral(tabArt, tabCde);
					break;
				case 2:
					gtc.menuGeneral(tabCde, tabArt);
					break;
				case 3:
					gtf.menuGeneral(tabFact, tabCde, tabArt);
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
					choix = 0;
			}
		} while (choix != 0);

		gta.sauvegarder(tabArt);
		gtc.sauvegarder(tabCde);
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
