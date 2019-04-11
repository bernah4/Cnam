package Serie53;

import IPane.IO;
import MesExceptions.AbandonException;
import MesExceptions.MenuPrecedentException;
import MesExceptions.RetourException;

public class ClientJava53 {

	static IO es = new IO();

	public static void main(String[] args) {

		GestionTableArticle53 gta = new GestionTableArticle53("TableArticles.data");
		GestionTableDesCommandes53 gtc = new GestionTableDesCommandes53("TableDesCommandes.data");
		GestionTableFactures53 gtf = new GestionTableFactures53("TableFacture.data");
		GestionTableClient53 gtcl = new GestionTableClient53("TableClient.data");
		TableArticles53 tabArt = gta.recuperer();
		TableDesCommandes53 tabCde = gtc.recuperer();
		TableFactures53 tabFact = gtf.recuperer();
		TableClient53 tabClient = gtcl.recuperer();
		String msg = "\n ---------------------- MENU GENERAL ---------------------- \n"
				+ "\t gestion table des articles .................... 1\n"
				+ "\t gestion des commandes      .................... 2\n"
				+ "\t gestion des factures       .................... 3\n"
				+ "\t gestion des clients      	.................... 4\n"
				+ "\t finir                      .................... 0\n" + "votre choix ?";

		int choix;
		gta.updateArticle(tabArt);
		gtc.autoPurge(tabCde, 3);
		gtc.purgeOld(tabCde, 1);
		gtf.purgeOld(tabFact, 1);
		do {
			try {
				choix = menuChoix(msg, 0, 4);
				switch (choix) {
				case 1:
					gta.menuGeneral(tabArt, tabCde);
					break;
				case 2:
					gtc.menuGeneral(tabCde, tabArt, tabClient);
					break;
				case 3:
					gtf.menuGeneral(tabFact, tabCde, tabArt);
					break;
				case 4:
					gtcl.menuGeneral(tabClient, tabCde);
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
		gtf.sauvegarder(tabFact);
		gtcl.sauvegarder(tabClient);
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
