package Serie22;

import Utils.IO;
import Serie21.Article;
import Serie21.LigneDeCommande;;


public class ClientJava22Commande {

	static IO es = new IO();

	public static void main(String[] args) {

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
				gestionTableArticles(tabArt, tabCde);
				break;
			case 2:
				gestionDesCommandes(tabCde, tabArt);
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

	public static void gestionDesCommandes(TableDesCommandes22 tabCde, TableArticles22 tabArt) {

		int choix;

		do {
			int numCde = prochainNumero(tabCde);
			String msg = "\n --------------- MENU DE GESTION DES COMMANDES --------------- \n"
					+ "\t Creer la commande nº"+numCde+"          ............... 1\n"
					+ "\t supprimer une commande         ............... 2\n"
					+ "\t afficher une commande          ............... 3\n"
					+ "\t afficher toutes les commandes  ............... 4\n"
					+ "\t facturer une commande          ............... 5\n"
					+ "\t modifier une commande          ............... 6\n"
					+ "\t finir                          ............... 0\n" + "votre choix ?\n\n";
			choix = menuChoix(msg, 0, 6);
			switch (choix) {
			case 1:
				creerUneCommande(tabCde, tabArt, numCde);
				break;
			case 2:
				supprimerUneCommande(tabCde);
				break;
			case 3:
				visualiserCommande(tabCde);
				break;
			case 4:
				visualiserAllCommande(tabCde);
				break;
			case 5:
				facturerUneCommande(tabCde, tabArt);
				break;
			case 6:
				modifierUneCommande(tabCde, tabArt);
				break;
			case 0:
				break;
			}
		} while (choix != 0);
	}
	
	public static void creerUneCommande(TableDesCommandes22 tabCde, TableArticles22 tabArt, int numCde) {
	
		TableLigneDeCommande22<Integer> cde = new TableLigneDeCommande22<Integer>();
		cde.setNumcommande(numCde);
		gestionTableDeCommande(cde, tabArt);
		if(cde.taille() != 0)
			tabCde.ajouter(cde);
	}
	
	public static int prochainNumero(TableDesCommandes22 tabCde) {
		
		int numero = 1;
		while(tabCde.retourner(numero) != null)
			numero++;
		return numero;
	}
	
	public static void supprimerUneCommande(TableDesCommandes22 tabCde) {
		
		if(tabCde.taille() != 0)
		{
			int num = es.lireEntier(tabCde.cle()+"quelle commande voulez vous supprimer ?\n", 0, Integer.MAX_VALUE);
			if (tabCde.retourner(num) != null)
				tabCde.supprimer(num);
			else
				es.affiche("Commande introuvable !");
		}
		else
			es.affiche("Aucune comande en cours !");
	}
	
	public static void visualiserCommande(TableDesCommandes22 tabCde) {
		
		if(tabCde.taille() != 0)
		{
			int num = es.lireEntier(tabCde.cle()+"quelle commande voulez vous visualiser ?\n", 0, Integer.MAX_VALUE);
			if (tabCde.retourner(num) != null)
				es.affiche(tabCde.retourner(num).toString());
			else
				es.affiche("Commande introuvable !");
		}
		else
			es.affiche("Aucune comande en cours !");
	}
	
	public static void visualiserAllCommande(TableDesCommandes22 tabCde) {
		
		if(tabCde.taille() != 0)
		{
			es.affiche("Liste des commandes \n\n"+tabCde.toString());
		}
		else
			es.affiche("Aucune comande en cours !");
	}
	
	public static void facturerUneCommande(TableDesCommandes22 tabCde,TableArticles22 tabArt) {
		
		if(tabCde.taille() != 0)
		{
			int num = es.lireEntier(tabCde.cle()+"quelle commande voulez vous facturer ?", 0, Integer.MAX_VALUE);
			if (tabCde.retourner(num) != null)
				es.affiche(tabCde.retourner(num).facturer(tabArt));
			else
				es.affiche("Commande introuvable !");
		}
		else
			es.affiche("Aucune comande en cours !");
	}
	
	public static void modifierUneCommande(TableDesCommandes22 tabCde, TableArticles22 tabArt) {
		
		if(tabCde.taille() != 0)
		{
			int num = es.lireEntier(tabCde.cle()+"quelle commande voulez vous modifier ?", 0, Integer.MAX_VALUE);
			if (tabCde.retourner(num) != null)
				gestionTableDeCommande(tabCde.retourner(num), tabArt);
			else
				es.affiche("Commande introuvable !");
		}
		else
			es.affiche("Aucune comande en cours !");
	}
	
	public static void gestionTableDeCommande(TableLigneDeCommande22<Integer> cde, TableArticles22 tabArt) {

		int choix;
		String msg = "\n --------------- MENU DE GESTION DE LA COMMANDE --------------- \n"
				+ "\t ajouter un artcile a la commande    ............... 1\n"
				+ "\t supprimer un article de la commande ............... 2\n"
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

	public static void retirerArticleDeCommande(TableLigneDeCommande22<Integer> cde) {

		es.affiche(cde.toString() + "\n");
		int code = es.lireEntier("choisisssez l'article a supprimer de votre commande:\n", 0, Integer.MAX_VALUE);
		supprimerArticleDeCommande(cde, code);
	}

	public static void supprimerArticleDeCommande(TableLigneDeCommande22<Integer> cde, int code) {
		if (cde.retourner(code) == null)
			es.affiche("produit inexistant dans la commande nº"+cde.getNumcommande()+ " !\n");
		else {
			es.affiche("Produit code " + code + " supprime de la commande nº"+cde.getNumcommande()+ "\n");
			cde.supprimer(code);
		}
	}

	public static void facturerCommande(TableLigneDeCommande22<Integer> cde, TableArticles22 tabArt) {

		if (cde.taille() == 0)
			es.affiche("Commande vide !\n\n");
		else
			es.affiche(cde.facturer(tabArt));
	}

	public static void creerCommande(TableLigneDeCommande22<Integer> cde, TableArticles22 tabArt) {

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

	public static LigneDeCommande saisieLigneDeCommande(TableArticles22 tabArt) {

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

	public static void afficheCommande(TableLigneDeCommande22<Integer> cde) {
		if (cde.taille() == 0)
			es.affiche("aucun article dans la commande\n\n");
		else
			es.affiche(cde.toString());
	}

	public static void gestionTableArticles(TableArticles22 tabArt, TableDesCommandes22 tabCde) {

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
				supprimerArticle(tabArt, tabCde);
				break;
			case 3:
				afficheArticle(tabArt);
				break;
			case 0:
				break;
			}
		} while (choix != 0);
	}

	public static void afficheArticle(TableArticles22 tabArt) {
		if (tabArt.taille() == 0)
			es.affiche("aucun article en stock\n");
		else
			es.affiche(tabArt.toString() + "\n");
	}

	public static void ajouterArticle(TableArticles22 tabArt) {

		Article art = saisieArticle(tabArt);
		if (art != null) {
			tabArt.ajouter(art);
			es.affiche("article ajoute a la table !\n");
		} else
			es.affiche("article deja existant\n");
	}

	public static Article saisieArticle(TableArticles22 tabArt) {

		int code;
		String design;
		String prixu;
		code = es.lireEntier("entrez le code de l'article que vous voulez ajouter a la table", 0, Integer.MAX_VALUE);
		if (tabArt.retourner(code) == null) {
			design = es.lireString("entrez le descriptif de l'article");
			prixu = es.lireString("entrez le prix unitaire");
			return (new Article(code, design, Float.parseFloat(prixu)));
		} else
			return null;
	}

	public static void supprimerArticle(TableArticles22 tabArt, TableDesCommandes22 tabCde) {

		es.affiche(tabArt.toString() + "\n");
		int code = es.lireEntier("choisisssez l'article a supprimer de la table:\n", 0, Integer.MAX_VALUE);
		if (tabArt.retourner(code) == null)
			es.affiche("produit inexistant dans la table !\n");
		else {
			es.affiche("Produit code " + code + " supprime du stock ! \n");
			tabArt.supprimer(code);
			tabCde.purge(code);
		}
	}

}
