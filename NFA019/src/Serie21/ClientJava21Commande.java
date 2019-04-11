package Serie21;

import Utils.IO;

public class ClientJava21Commande {

	static IO es = new IO();

	public static void main(String[] args) {

		TableArticles21 tabArt = new TableArticles21();
		TableLigneDeCommande21 cde = new TableLigneDeCommande21();
		String msg = "\n ---------------------- MENU GENERAL ---------------------- \n"
				+ "\t gestion table des articles .................... 1\n"
				+ "\t gestion d'une commande     .................... 2\n"
				+ "\t finir                      .................... 0\n" + "votre choix ?";

		int choix;
		do {
			choix = menuChoix(msg, 0, 2);
			switch (choix) {
			case 1:
				gestionTableArticles(tabArt, cde);
				break;
			case 2:
				gestionTableDeCommande(cde, tabArt);
				break;
			case 0:
				break;
			}
		} while (choix != 0);

		es.affiche("-------- A bientot chez croquette-corp ! ---------");

	}

	public static int menuChoix(String msg, int min, int max) {
		es.affiche(msg);
		return es.lireEntier("", min, max);
	}

	public static void gestionTableDeCommande(TableLigneDeCommande21 cde, TableArticles21 tabArt) {

		int choix;
		String msg = "\n --------------- MENU DE GESTION DE LA COMMANDE --------------- \n"
				+ "\t ajouter un artcile a la commande    ............... 1\n"
				+ "\t supprimer un article de la command  ............... 2\n"
				+ "\t afficher la commande en  cours      ............... 3\n"
				+ "\t editer la facture                   ............... 4\n"
				+ "\t finir                               ............... 0\n" + "votre choix ?\n\n";

		do {
			choix = menuChoix(msg, 0, 4);
			switch (choix) {
			case 1:
				creerCommande(cde, tabArt);
				break;
			case 2:
				retirerArticleDeCommande(cde);
				break;
			case 3:
				afficheCommande(cde);
				break;
			case 4:
				facturerCommande(cde, tabArt);
				break;
			case 0:
				break;
			}
		} while (choix != 0);
	}

	public static void retirerArticleDeCommande(TableLigneDeCommande21 cde) {

		es.affiche(cde.toString() + "\n");
		int code = es.lireEntier("choisisssez l'article a supprimer de votre commande:\n", 0, Integer.MAX_VALUE);
		supprimerArticleDeCommande(cde, code);
	}

	public static void supprimerArticleDeCommande(TableLigneDeCommande21 cde, int code) {
		if (cde.retourner(code) == null)
			es.affiche("produit inexistant dans la commande nº"/* +cde */ + " !\n");
		else {
			es.affiche("Produit code " + code + " supprime de la commande nº"/* +cde */ + "\n");
			cde.supprimer(code);
		}
	}

	public static void facturerCommande(TableLigneDeCommande21 cde, TableArticles21 tabArt) {

		if (cde.taille() == 0)
			es.affiche("Commande vide !\n\n");
		else
			es.affiche(cde.facturer(tabArt));
	}

	public static void creerCommande(TableLigneDeCommande21 cde, TableArticles21 tabArt) {

		LigneDeCommande element = saisieLigneDeCommande(tabArt);
		if (element != null) {
			LigneDeCommande exist = cde.retourner(element.getCodearticle());
			if (exist == null) {
				cde.ajouter(element);
				es.affiche("article ajoute au panier !\n\n");
			} else {
				exist.setQuantitecommande(exist.getQuantitecommande() + element.getQuantitecommande());
				es.affiche("article additione aux articles existant !\n\n");
			}
		} else
			es.affiche("article saisie inexistant\n\n");
	}

	public static LigneDeCommande saisieLigneDeCommande(TableArticles21 tabArt) {

		afficheArticle(tabArt);
		int code;
		int qte;
		code = es.lireEntier("entrez le code de l'article que vous voulez acheter", 0, Integer.MAX_VALUE);
		if (tabArt.retourner(code) != null) {
			qte = es.lireEntier("entrez la quantite souhaite", 0, Integer.MAX_VALUE);
			return (new LigneDeCommande(code, qte));
		} else
			return null;
	}

	public static void afficheCommande(TableLigneDeCommande21 cde) {
		if (cde.taille() == 0)
			es.affiche("aucun article dans la commande\n\n");
		else
			es.affiche(cde.toString());
	}

	public static void gestionTableArticles(TableArticles21 tabArt, TableLigneDeCommande21 cde) {

		int choix;
		String msg = "\n ---------- MENU DE GESTION DE LA TABLE DES ARTICLES ---------- \n"
				+ "\t ajouter un article             .................... 1\n"
				+ "\t supprimer un article           .................... 2\n"
				+ "\t afficher la table des articles .................... 3\n"
				+ "\t finir                          .................... 0\n" + "votre choix ?\n\n";

		do {
			choix = menuChoix(msg, 0, 3);
			switch (choix) {
			case 1:
				ajouterArticle(tabArt);
				break;
			case 2:
				supprimerArticle(tabArt, cde);
				break;
			case 3:
				afficheArticle(tabArt);
				break;
			case 0:
				break;
			}
		} while (choix != 0);
	}

	public static void afficheArticle(TableArticles21 tabArt) {
		if (tabArt.taille() == 0)
			es.affiche("aucun article en stock\n");
		else
			es.affiche(tabArt.toString() + "\n");
	}

	public static void ajouterArticle(TableArticles21 tabArt) {

		Article art = saisieArticle(tabArt);
		if (art != null) {
			tabArt.ajouter(art);
			es.affiche("article ajoute a la table !\n");
		} else
			es.affiche("article deja existant\n");
	}

	public static Article saisieArticle(TableArticles21 tabArt) {

		int code;
		String design;
		int prix;
		code = es.lireEntier("entrez le code de l'article que vous voulez ajouter a la table", 0, Integer.MAX_VALUE);
		if (tabArt.retourner(code) == null) {
			design = es.lireString("entrez le descriptif de l'article");
			prix = es.lireEntier("entrez le prix unitaire", 0, Integer.MAX_VALUE);
			return (new Article(code, design, prix));
		} else
			return null;
	}

	public static void supprimerArticle(TableArticles21 tabArt, TableLigneDeCommande21 cde) {

		es.affiche(tabArt.toString() + "\n");
		int code = es.lireEntier("choisisssez l'article a supprimer de la table:\n", 0, Integer.MAX_VALUE);
		if (tabArt.retourner(code) == null)
			es.affiche("produit inexistant dans la table !\n");
		else {
			es.affiche("Produit code " + code + " supprime ! \n");
			tabArt.supprimer(code);
			supprimerArticleDeCommande(cde, code);
		}
	}

}
