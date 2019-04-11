package Serie41;

import MesExceptions.AbandonException;
import MesExceptions.MenuPrecedentException;
import MesExceptions.RetourException;
import Serie21.Article;
import IConsole.IO;

public class GestionTableLigneDeCommande41 {

	IO es = new IO();
	
	private int menuChoix(String msg, int min, int max) throws AbandonException,RetourException{
		try {
			return (Integer) es.saisie(msg, min, max);
			}
			catch (MenuPrecedentException mp) {
				throw new RetourException();
			}
	}
	
	public void menuGeneral(TableLigneDeCommande41<Integer> cde, TableArticles41 tabArt) throws AbandonException{

		int choix;
		String msg = "\n --------------- MENU DE GESTION DE LA COMMANDE --------------- \n"
				+ "\t ajouter un artcile a la commande    ............... 1\n"
				+ "\t supprimer un article de la commande ............... 2\n"
				+ "\t afficher la commande en  cours      ............... 3\n"
				+ "\t editer la facture                   ............... 4\n"
				+ "\t modifier la facture                 ............... 5\n"
				+ "\t finir                               ............... 0\n" + "votre choix ?\n\n";

		do {
			try {
			choix = menuChoix(msg, 0, 5);
			switch (choix) {
			case 1:
				creer(cde, tabArt);
				break;
			case 2:
				supprimer(cde);
				break;
			case 3:
				afficher(cde);
				break;
			case 4:
				facturer(cde, tabArt);
				break;
			case 5:
				modifier(cde);
				break;
			case 0:
				break;
			}
			}catch (MenuPrecedentException mp) {
				choix = -1;
			} catch (RetourException rt) {
				choix = 0;
			}
		} while (choix != 0);
	}

	public void supprimer(TableLigneDeCommande41<Integer> cde) throws AbandonException,MenuPrecedentException{

		es.affiche(cde.toString() + "\n");
		int code = es.saisie("choisisssez l'article a supprimer de votre commande:\n", 0);
		supprimerArticleDeCommande(cde, code);
	}

	public void supprimerArticleDeCommande(TableLigneDeCommande41<Integer> cde, int code) {
		if (cde.retourner(code) == null)
			es.affiche("produit inexistant dans la commande nº"+cde.getNumcommande()+ " !\n");
		else {
			es.affiche("Produit code " + code + " supprime de la commande nº"+cde.getNumcommande()+ "\n");
			cde.supprimer(code);
		}
	}

	public void facturer(TableLigneDeCommande41<Integer> cde, TableArticles41 tabArt) {

		if (cde.taille() == 0)
			es.affiche("Commande vide !\n\n");
		else
			es.affiche(cde.facturer(tabArt));
	}

	public void creer(TableLigneDeCommande41<Integer> cde, TableArticles41 tabArt) throws AbandonException,MenuPrecedentException{

		LigneDeCommande41 element = saisieLigneDeCommande(tabArt);
		if (element != null) {
			LigneDeCommande41 exist = cde.retourner(element.getCodearticle());
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

	public LigneDeCommande41 saisieLigneDeCommande(TableArticles41 tabArt) throws AbandonException,MenuPrecedentException{

		int code;
		int qte;
		code = es.saisie(tabArt.toString()+"\n entrez le code de l'article que vous voulez acheter", 0);
		Article art = tabArt.retourner(code);
		if (art != null) {
			if (art instanceof ArticlePromotion41)
				es.affiche("Cet article est en promo ! A partir de "+((ArticlePromotion41)art).getQtemin() +" articles achetes, une reduction de "+((ArticlePromotion41)art).getReduc()+"%\n");
			qte = es.saisie("entrez la quantite souhaite", 0);
			return (new LigneDeCommande41(code, qte));
		} else
			return null;
	}

	public void afficher(TableLigneDeCommande41<Integer> cde) {
		if (cde.taille() == 0)
			es.affiche("aucun article dans la commande\n\n");
		else
			es.affiche(cde.toString());
	}
	
	public void modifier(TableLigneDeCommande41<Integer> cde) throws AbandonException,MenuPrecedentException{

		int code = es.saisie(cde.toString()+"\n entrez le code de l'article que vous voulez modifier", 0);
		if (cde.retourner(code) != null) {
			int qte = es.saisie("Entrez la nouvelle quantite souhaite", 0);
			if (qte > 0)
				cde.retourner(code).setQuantitecommande(qte);
			else 
				supprimerArticleDeCommande(cde, code);
			es.affiche("article modifie !\n\n");
		} else
			es.affiche("article saisie inexistant\n\n");
	}
}
