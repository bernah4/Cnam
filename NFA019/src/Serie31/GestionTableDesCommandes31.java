package Serie31;

import Serie22.TableArticles22;
import Serie22.TableDesCommandes22;
import Serie22.TableLigneDeCommande22;
import IConsole.IO;
import MesExceptions.AbandonException;
import MesExceptions.MenuPrecedentException;
import MesExceptions.RetourException;

public class GestionTableDesCommandes31 {
	
	IO es = new IO();
	
	private int menuChoix(String msg, int min, int max) throws AbandonException,RetourException {
		try {
			return (Integer) es.saisie(msg, min, max);
			}
			catch (MenuPrecedentException mp) {
				throw new RetourException();
			}
	}

	public void menuGeneral(TableDesCommandes22 tabCde, TableArticles22 tabArt) throws AbandonException {

		int choix;

		do {
			try {
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
				creer(tabCde, tabArt, numCde);
				break;
			case 2:
				supprimer(tabCde);
				break;
			case 3:
				afficher(tabCde);
				break;
			case 4:
				afficherTout(tabCde);
				break;
			case 5:
				facturer(tabCde, tabArt);
				break;
			case 6:
				modifier(tabCde, tabArt);
				break;
			case 0:
				break;
			}
			}
			catch (MenuPrecedentException mp) {
				choix = -1;
			} catch (RetourException rt) {
				choix = 0;
			}
		} while (choix != 0);
	}
	
	public void creer(TableDesCommandes22 tabCde, TableArticles22 tabArt, int numCde) throws AbandonException {
	
		TableLigneDeCommande22<Integer> cde = new TableLigneDeCommande22<Integer>();
		cde.setNumcommande(numCde);
		GestionTableLigneDeCommande31 gtldc = new GestionTableLigneDeCommande31();
		gtldc.menuGeneral(cde, tabArt);
		if(cde.taille() != 0)
			tabCde.ajouter(cde);
	}
	
	public int prochainNumero(TableDesCommandes22 tabCde) {
		
		int numero = 1;
		while(tabCde.retourner(numero) != null)
			numero++;
		return numero;
	}
	
	public void supprimer(TableDesCommandes22 tabCde) throws AbandonException,MenuPrecedentException {
		
		if(tabCde.taille() != 0)
		{
			int num = es.saisie(tabCde.cle()+"quelle commande voulez vous supprimer ?\n", 0);
			if (tabCde.retourner(num) != null)
				tabCde.supprimer(num);
			else
				es.affiche("Commande introuvable !");
		}
		else
			es.affiche("Aucune comande en cours !");
	}
	
	public void afficher(TableDesCommandes22 tabCde) throws AbandonException,MenuPrecedentException {
		
		if(tabCde.taille() != 0)
		{
			int num = es.saisie(tabCde.cle()+"quelle commande voulez vous visualiser ?\n", 0);
			TableLigneDeCommande22<Integer> cde = tabCde.retourner(num);
			if ( cde != null) {
				GestionTableLigneDeCommande31 gtldc1 = new GestionTableLigneDeCommande31();
				gtldc1.afficher(cde);
			}
			else
				es.affiche("Commande introuvable !");
		}
		else
			es.affiche("Aucune comande en cours !");
	}
	
	public void afficherTout(TableDesCommandes22 tabCde) {
		
		if(tabCde.taille() != 0)
		{
			es.affiche("Liste des commandes \n\n"+tabCde.toString());
		}
		else
			es.affiche("Aucune comande en cours !");
	}
	
	public void facturer(TableDesCommandes22 tabCde,TableArticles22 tabArt) throws AbandonException,MenuPrecedentException {
		
		if(tabCde.taille() != 0)
		{
			int num = es.saisie(tabCde.cle()+"quelle commande voulez vous facturer ?", 0);
			TableLigneDeCommande22<Integer> cde = tabCde.retourner(num);
			if (cde != null) {
				GestionTableLigneDeCommande31 gtldc2 = new GestionTableLigneDeCommande31();
				gtldc2.afficher(cde);
			}
			else
				es.affiche("Commande introuvable !");
		}
		else
			es.affiche("Aucune comande en cours !");
	}
	
	public void modifier(TableDesCommandes22 tabCde, TableArticles22 tabArt) throws AbandonException,MenuPrecedentException {
		
		if(tabCde.taille() != 0)
		{
			int num = es.saisie(tabCde.cle()+"quelle commande voulez vous modifier ?", 0);
			TableLigneDeCommande22<Integer> cde = tabCde.retourner(num);
			if (cde != null) {
				GestionTableLigneDeCommande31 gtldc3 = new GestionTableLigneDeCommande31();
				gtldc3.afficher(cde);
				if (cde.taille() == 0)
					tabCde.supprimer(num);
			}
			else
				es.affiche("Commande introuvable !");
		}
		else
			es.affiche("Aucune comande en cours !");
	}
}
