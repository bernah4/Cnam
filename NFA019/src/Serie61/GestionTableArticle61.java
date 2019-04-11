package Serie61;

import Connexions.ConnectionFichiers;
import IFrame.FrameGestionTableDesArticle61;
import IPane.IO;
import MesExceptions.AbandonException;
import MesExceptions.MenuPrecedentException;
import MesExceptions.RetourException;
import MesInterfaces.InterfaceGestion;
import Utils.DateUser;

public class GestionTableArticle61 {
	
	IO es = new IO();
	private ConnectionFichiers<TableArticles61> fichin;
	
	public GestionTableArticle61(String nomPhy) {
		fichin = new ConnectionFichiers<TableArticles61>(nomPhy);
	}
	
	public TableArticles61 recuperer() {
		
		TableArticles61 tabArt = fichin.lire();
		if (tabArt == null)
			tabArt = new TableArticles61();
		return tabArt;
	}
	
	public void sauvegarder(TableArticles61 tabArt) {
		
		fichin.ecrire(tabArt, false);
	}
	
	
	public int menuChoix(String msg, int min, int max) throws AbandonException,RetourException{
		try {
			return (Integer) es.saisie(msg, min, max);
			}
			catch (MenuPrecedentException mp) {
				throw new RetourException();
			}
	}
	
	public void menuGeneral(Object ...obj){
		
		TableArticles61 tabArt = (TableArticles61)obj[0];
		TableDesCommandes61 tabCde = (TableDesCommandes61)obj[1];
		new FrameGestionTableDesArticle61(tabArt, tabCde);
		/*int choix;
		
		String msg = "\n ---------- MENU DE GESTION DE LA TABLE DES Article42S ---------- \n"
				+ "\t ajouter un article             .................... 1\n"
				+ "\t supprimer un article           .................... 2\n"
				+ "\t afficher la table des articles .................... 3\n"
				+ "\t afficher la table des articles en promotion ....... 4\n"
				+ "\t modifier un article            .................... 5\n"
				+ "\t transformer                    .................... 6\n"
				+ "\t finir                          .................... 0\n" + "votre choix ?\n\n";

		do {
			try {
			choix = menuChoix(msg, 0, 6);
			switch (choix) {
			case 1:
				creer(tabArt);
				sauvegarder(tabArt);
				break;
			case 2:
				supprimer(tabArt, tabCde);
				sauvegarder(tabArt);
				break;
			case 3:
				affiche(tabArt);
				break;
			case 4:
				affichepromo(tabArt);
				break;
			case 5:
				modifier(tabArt);
				sauvegarder(tabArt);
				break;
			case 6:
				transformer(tabArt);
				sauvegarder(tabArt);
				break;
			case 0:
				break;}
			} catch (MenuPrecedentException mp) {
				choix = -1;
			} catch (RetourException rt) {
				choix = 0;
			}
		} while (choix != 0);*/
	}
	
	/*public void transformer(TableArticles61 tabArt) throws AbandonException,MenuPrecedentException {
		int code = es.saisie(tabArt.toString() + "\n choisisssez l'article a transformer dans la table: \n", 0);
		AbstractArticle art = tabArt.retourner(code);
		if (art == null)
			es.affiche("produit inexistant dans la table !\n");
		else {
			boolean promo = art instanceof ArticlePromotion61;
			boolean lot = art instanceof ArticleLot61;
			String desi = art.getDesignation();
			float pu = art.getPrixunitaire();			
			int choix = es.saisie("transformer en : \n 1. lot \n 2. promotion \n 3. normal \n", 0, 3);
			if ((choix == 1 && !lot) || (choix == 2 && !promo))
			{
				int qte = es.saisie("\n qte mini pour appliquer la promo: \n", 0);
				float reduc = es.saisie("\n pourcentage de reduction: \n", 0);
				if (choix == 1)
					art = new ArticleLot61(code, desi, pu, qte, reduc);
				else if (choix == 2 )
					art = new ArticlePromotion61(code, desi, pu, qte, reduc);
			}
			else if (choix == 3)
				art = new Article61(code, desi, pu);
			else
			{
				es.affiche("Error !");
				return;
			}
			tabArt.ajouter(art);
						
		}
	}

	
	public void affiche(Object ...obj) {
		TableArticles61 tabArt = (TableArticles61)obj[0];
		if (tabArt.taille() == 0)
			es.affiche("aucun article en stock\n");
		else
			es.affiche(tabArt.toString() + "\n");
	}

	public void affichepromo(TableArticles61 tabArt) {
		if (tabArt.taille() == 0)
			es.affiche("aucun article en stock\n");
		else
			es.affiche(tabArt.toStringPromo() + "\n");
	}

	public void creer(Object ...obj) throws AbandonException,MenuPrecedentException {

		TableArticles61 tabArt = (TableArticles61)obj[0];
		Article61 art = saisieArticle42(tabArt);
		if (art != null) {
			tabArt.ajouter(art);
			es.affiche("Article ajoute a la table !\n");
		} else
			es.affiche("Article deja existant\n");
	}

	public Article61 saisieArticle42(TableArticles61 tabArt) throws AbandonException,MenuPrecedentException {

		int code;
		String design;
		String prixu;
		code = es.saisie("entrez le code de l'article que vous voulez ajouter a la table", 0);
		if (tabArt.retourner(code) == null) {
			design = es.saisie("entrez le descriptif de l'article");
			prixu = es.saisie("entrez le prix unitaire");
			return (new Article61(code, design, Float.parseFloat(prixu)));
		} else
			return null;
	}

	public void supprimer(Object ...obj) throws AbandonException,MenuPrecedentException {

		TableArticles61 tabArt = (TableArticles61)obj[0];
		TableDesCommandes61 tabCde = (TableDesCommandes61)obj[1];
		es.affiche(tabArt.toString() + "\n");
		int code = es.saisie("choisisssez l'article a supprimer de la table:\n", 0);
		if (tabArt.retourner(code) == null)
			es.affiche("produit inexistant dans la table !\n");
		else {
			es.affiche("Produit code " + code + " supprime du stock ! \n");
			tabArt.supprimer(code);
			tabCde.purge(code);
		}
	}
	
	public void modifier(Object ...obj) throws AbandonException,MenuPrecedentException {
		
		TableArticles61 tabArt = (TableArticles61)obj[0];
		int code = es.saisie(tabArt.toString() + "\n choisisssez l'article a modifier de la table: \n", 0);
		if (tabArt.retourner(code) == null)
			es.affiche("produit inexistant dans la table !\n");
		else {
			modifArticle42(tabArt, code);
		}
	}
	
	
	public void modifArticle42(TableArticles61 tabArt,int code) throws AbandonException,MenuPrecedentException {
		int choix = es.saisie(tabArt.retourner(code).toString() + "\n modifier designation : 1 \n modifier prix unitaire : 2 \n finir : 0", 0, 2);
		if (choix == 1)
		{
			String designation = es.saisie("Entrez la nouvelle designation ", true);
			tabArt.retourner(code).setDesignation(designation);
		}
		if (choix == 2)
		{
			String prixunitaire = es.saisie("Entrez le nouveau prix");
			tabArt.retourner(code).setPrixunitaire(Float.parseFloat(prixunitaire));
		}
	}
	*/
	public void updateArticle(TableArticles61 tabArt)
	{
		DateUser date = new DateUser();
		for (AbstractArticle art : tabArt.getTabArt().values())
		{
			if(art instanceof ArticleLot61)
			{
				if (date.estSuperieur(((ArticleLot61) art).getEnd()))
					transformerUpdate(art, tabArt);
			}
		}
	}
	
	public void transformerUpdate(AbstractArticle art, TableArticles61 tabArt)
	{
		int code = art.getCodearticle();
		String desi = art.getDesignation();
		float pu = art.getPrixunitaire();
		art = new Article61(code, desi, pu);
		tabArt.ajouter(art);	
	}
}
