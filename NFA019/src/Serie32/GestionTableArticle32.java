package Serie32;

import Serie21.Article;
import Serie22.TableArticles22;
import Serie22.TableDesCommandes22;
import IPane.IOargs;
import MesExceptions.AbandonException;
import MesExceptions.MenuPrecedentException;
import MesExceptions.RetourException;

public class GestionTableArticle32 {

	IOargs es = new IOargs();

	private int menuChoix(String msg, int min, int max) throws AbandonException,RetourException{
		try {
		return (Integer) es.saisie(msg, min, max);
		}
		catch (MenuPrecedentException mp) {
			throw new RetourException();
		}
	}

	public void menuGeneral(TableArticles22 tabArt, TableDesCommandes22 tabCde) throws AbandonException {

		int choix;
		String msg = "\n ---------- MENU DE GESTION DE LA TABLE DES ARTICLES ---------- \n"
				+ "\t ajouter un article             .................... 1\n"
				+ "\t supprimer un article           .................... 2\n"
				+ "\t afficher la table des articles .................... 3\n"
				+ "\t modifier la table des articles .................... 4\n"
				+ "\t finir                          .................... 0\n" + "votre choix ?\n\n";

		do {
			try {
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
			} catch (MenuPrecedentException mp) {
				choix = -1;
			} catch (RetourException rt) {
				choix = 0;
			}
		} while (choix != 0);
	}

	public void affiche(TableArticles22 tabArt) {
		if (tabArt.taille() == 0)
			es.affiche("aucun article en stock\n");
		else
			es.affiche(tabArt.toString() + "\n");
	}

	public void creer(TableArticles22 tabArt) throws AbandonException,MenuPrecedentException {

		Article art = saisieArticle(tabArt);
		if (art != null) {
			tabArt.ajouter(art);
			es.affiche("article ajoute a la table !\n");
		} else
			es.affiche("article deja existant\n");
	}

	public Article saisieArticle(TableArticles22 tabArt) throws AbandonException,MenuPrecedentException {

		int code;
		String design;
		float prixu;
		code = (Integer) es.saisie("entrez le code de l'article que vous voulez ajouter a la table", 0);
		if (tabArt.retourner(code) == null) {
			design = (String) es.saisie("entrez le descriptif de l'article", true);
			prixu = (float) es.saisie("entrez le prix unitaire", 0f);
			return (new Article(code, design, prixu));
		} else
			return null;
	}

	public void supprimer(TableArticles22 tabArt, TableDesCommandes22 tabCde) throws AbandonException,MenuPrecedentException {

		es.affiche(tabArt.toString() + "\n");
		int code = (Integer) es.saisie("choisisssez l'article a supprimer de la table:\n", 0);
		if (tabArt.retourner(code) == null)
			es.affiche("produit inexistant dans la table !\n");
		else {
			es.affiche("Produit code " + code + " supprime du stock ! \n");
			tabArt.supprimer(code);
			tabCde.purge(code);
		}
	}

	public void modifier(TableArticles22 tabArt) throws AbandonException,MenuPrecedentException {
		int code = (Integer) es.saisie(tabArt.toString() + "\n choisisssez l'article a modifier de la table: \n", 0);
		if (tabArt.retourner(code) == null)
			es.affiche("produit inexistant dans la table !\n");
		else {
			modifarticle(tabArt, code);
		}
	}

	public void modifarticle(TableArticles22 tabArt, int code) throws AbandonException,MenuPrecedentException {
		int choix = (Integer) es.saisie(tabArt.retourner(code).toString()
				+ "\n modifier designation : 1 \n modifier prix unitaire : 2 \n finir : 0", 0, 2);
		if (choix == 1) {
			String designation = (String) es.saisie("Entrez la nouvelle designation ", true);
			tabArt.retourner(code).setDesignation(designation);
		}
		if (choix == 2) {
			String prixunitaire = (String) es.saisie("Entrez le nouveau prix");
			tabArt.retourner(code).setPrixunitaire(Float.parseFloat(prixunitaire));
		}
	}

}
