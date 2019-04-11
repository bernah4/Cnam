package Serie23;

import Serie21.Article;
import Serie22.TableArticles22;
import Serie22.TableDesCommandes22;
import Utils.IO;

public class GestionTableArticle23 {
	
	IO es = new IO();
	
	private int menuChoix(String msg, int min, int max) {
		es.affiche(msg);
		return es.lireEntier("", min, max);
	}
	
	public void menuGeneral(TableArticles22 tabArt, TableDesCommandes22 tabCde) {

		int choix;
		String msg = "\n ---------- MENU DE GESTION DE LA TABLE DES ARTICLES ---------- \n"
				+ "\t ajouter un article             .................... 1\n"
				+ "\t supprimer un article           .................... 2\n"
				+ "\t afficher la table des articles .................... 3\n"
				+ "\t modifier la table des articles .................... 4\n"
				+ "\t finir                          .................... 0\n" + "votre choix ?\n\n";

		do {
			choix = menuChoix(msg, 0, 4);
			switch (choix) {
			case 1:
				creer(tabArt);
				break;
			case 2:
				supprimer(tabArt, tabCde);
				break;
			case 3:
				affiche(tabArt);
				break;
			case 4:
				modifier(tabArt);
				break;
			case 0:
				break;
			}
		} while (choix != 0);
	}

	public void affiche(TableArticles22 tabArt) {
		if (tabArt.taille() == 0)
			es.affiche("aucun article en stock\n");
		else
			es.affiche(tabArt.toString() + "\n");
	}

	public void creer(TableArticles22 tabArt) {

		Article art = saisieArticle(tabArt);
		if (art != null) {
			tabArt.ajouter(art);
			es.affiche("article ajoute a la table !\n");
		} else
			es.affiche("article deja existant\n");
	}

	public Article saisieArticle(TableArticles22 tabArt) {

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

	public void supprimer(TableArticles22 tabArt, TableDesCommandes22 tabCde) {

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
	
	public void modifier(TableArticles22 tabArt) {
		int code = es.lireEntier(tabArt.toString() + "\n choisisssez l'article a modifier de la table: \n", 0, Integer.MAX_VALUE);
		if (tabArt.retourner(code) == null)
			es.affiche("produit inexistant dans la table !\n");
		else {
			modifarticle(tabArt, code);
		}
	}
	
	
	public void modifarticle(TableArticles22 tabArt,int code) {
		int choix = es.lireEntier(tabArt.retourner(code).toString() + "\n modifier designation : 1 \n modifier prix unitaire : 2 \n finir : 0", 0, 2);
		if (choix == 1)
		{
			String designation = es.lireString("Entrez la nouvelle designation ");
			tabArt.retourner(code).setDesignation(designation);
		}
		if (choix == 2)
		{
			String prixunitaire = es.lireString("Entrez le nouveau prix");
			tabArt.retourner(code).setPrixunitaire(Float.parseFloat(prixunitaire));
		}
	}

}
