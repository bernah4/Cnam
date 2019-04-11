package Serie52;

import MesExceptions.AbandonException;
import MesExceptions.MenuPrecedentException;
import MesExceptions.RetourException;
import MesInterfaces.InterfaceGestion;
import IPane.IO;

public class GestionTableLigneDeCommande52 implements InterfaceGestion<TableLigneDeCommande52<Integer>>{

	IO es = new IO();
	
	public int menuChoix(String msg, int min, int max) throws AbandonException,RetourException{
		try {
			return (Integer) es.saisie(msg, min, max);
			}
			catch (MenuPrecedentException mp) {
				throw new RetourException();
			}
	}
	
	public void menuGeneral(Object ...obj) throws AbandonException{

		int choix;
		TableLigneDeCommande52<String> cde = (TableLigneDeCommande52<String>)obj[0];
		TableArticles52 tabArt = (TableArticles52)obj[1];
		String msg = "\n --------------- MENU DE GESTION DE LA COMMANDE --------------- \n"
				+ "\t ajouter un artcile a la commande    ............... 1\n"
				+ "\t supprimer un article de la commande ............... 2\n"
				+ "\t afficher la commande en  cours      ............... 3\n"
				+ "\t modifier la commande                ............... 4\n"
				+ "\t finir                               ............... 0\n" + "votre choix ?\n\n";

		do {
			try {
			choix = menuChoix(msg, 0, 4);
			switch (choix) {
			case 1:
				creer(cde, tabArt);
				break;
			case 2:
				supprimer(cde, tabArt);
				break;
			case 3:
				affiche(cde);
				break;
			case 4:
				modifier(cde, tabArt);
				break;
			case 0:
				break;}
			} catch (MenuPrecedentException mp) {
				choix = -1;
			} catch (RetourException rt) {
				choix = 0;
			}
		} while (choix != 0);
	}

	public void supprimer(Object ...obj) throws AbandonException,MenuPrecedentException{

		TableLigneDeCommande52<String> cde = (TableLigneDeCommande52<String>)obj[0];
		TableArticles52 tabArt = (TableArticles52)obj[1];
		int code = es.saisie(cde.toString()+" choisisssez l'article a supprimer de votre commande:\n", 0);
		supprimerArticleDeCommande(cde, code);
		cde.setValeurHT(cde.prixTotal(tabArt));
	}

	public void supprimerArticleDeCommande(TableLigneDeCommande52<String> cde, int code) {
		if (cde.retourner(code) == null)
			es.affiche("produit inexistant dans la commande nº"+cde.getNumcommande()+ " !\n");
		else {
			es.affiche("Produit code " + code + " supprime de la commande nº"+cde.getNumcommande()+ "\n");
			cde.supprimer(code);
		}
	}

	/*public void facturer(TableLigneDeCommande52<String> cde, TableArticles52 tabArt) {

		if (cde.taille() == 0)
			es.affiche("Commande vide !\n\n");
		else
			es.affiche(cde.facturer(tabArt));
	}*/

	public void creer(Object ...obj) throws AbandonException,MenuPrecedentException{

		TableLigneDeCommande52<String> cde = (TableLigneDeCommande52<String>)obj[0];
		TableArticles52 tabArt = (TableArticles52)obj[1];
		LigneDeCommande52 element = saisieLigneDeCommande(tabArt);
		if (element != null) {
			LigneDeCommande52 exist = cde.retourner(element.getCodearticle());
			if (exist == null) {
				cde.ajouter(element);
				es.affiche("article ajoute au panier !\n\n");
			} else {
				exist.setQuantitecommande(exist.getQuantitecommande() + element.getQuantitecommande());
				es.affiche("article additione aux articles existant !\n\n");
			}
			cde.setValeurHT(cde.prixTotal(tabArt));
		} else
			es.affiche("article saisie inexistant\n\n");
	}

	public LigneDeCommande52 saisieLigneDeCommande(TableArticles52 tabArt) throws AbandonException,MenuPrecedentException{

		int code;
		int qte;
		code = es.saisie(tabArt.toString()+"\n entrez le code de l'article que vous voulez acheter", 0);
		AbstractArticle art = tabArt.retourner(code);
		if (art != null) {
			if (art instanceof ArticlePromotion52)
				es.affiche("Cet article est en promo ! A partir de "+((ArticlePromotion52)art).getQtemin() +" articles achetes, une reduction de "+((ArticlePromotion52)art).getReduc()+"%\n");
			qte = es.saisie("entrez la quantite souhaite", 0);
			return (new LigneDeCommande52(code, qte));
		} else
			return null;
	}

	public void affiche(Object ...obj) {
		
		TableLigneDeCommande52<String> cde = (TableLigneDeCommande52<String>)obj[0];
		if (cde.taille() == 0)
			es.affiche("aucun article dans la commande\n\n");
		else
			es.affiche(cde.toString());
	}
	
	public void modifier(Object ...obj) throws AbandonException,MenuPrecedentException{
		
		TableLigneDeCommande52<String> cde = (TableLigneDeCommande52<String>)obj[0];
		TableArticles52 tabArt = (TableArticles52)obj[1];
		int code = es.saisie(cde.toString()+"\n entrez le code de l'article que vous voulez modifier", 0);
		if (cde.retourner(code) != null) {
			int qte = es.saisie("Entrez la nouvelle quantite souhaite", 0);
			if (qte > 0)
				cde.retourner(code).setQuantitecommande(qte);
			else 
				supprimerArticleDeCommande(cde, code);
			cde.setValeurHT(cde.prixTotal(tabArt));
			es.affiche("article modifie !\n\n");
		} else
			es.affiche("article saisie inexistant\n\n");
	}
}
