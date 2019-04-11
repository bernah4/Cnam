package Serie23;

import Utils.IO;
import Serie22.*;


public class ClientJava23Commande {

	static IO es = new IO();
	

	public static void main(String[] args) {

		GestionTableArticle23 gta = new GestionTableArticle23();
		GestionTableDesCommandes23 gtc = new GestionTableDesCommandes23();
		TableArticles22 tabArt = new TableArticles22();
		TableDesCommandes22 tabCde = new TableDesCommandes22();
		String msg = "\n ---------------------- MENU GENERAL ---------------------- \n"
				+ "\t gestion table des articles .................... 1\n"
				+ "\t gestion des commandes      .................... 2\n"
				+ "\t finir                      .................... 0\n" + "votre choix ?";

		int choix;
		do {
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
		} while (choix != 0);

		es.affiche("------------- A bientot chez croquette-corp ! -------------");

	}

	public static int menuChoix(String msg, int min, int max) {
		es.affiche(msg);
		return es.lireEntier("", min, max);
	}
	
	
}
