package Serie42;

import IPane.IO;
import MesExceptions.AbandonException;
import MesExceptions.MenuPrecedentException;
import MesExceptions.RetourException;
import MesInterfaces.InterfaceGestion;

public class GestionTableDesCommandes42 implements InterfaceGestion<TableDesCommandes42>{
	
	IO es = new IO();
	
	public int menuChoix(String msg, int min, int max) throws AbandonException,RetourException {
		try {
			return (Integer) es.saisie(msg, min, max);
			}
			catch (MenuPrecedentException mp) {
				throw new RetourException();
			}
	}

	public void menuGeneral(Object ...obj) throws AbandonException {

		int choix;
		TableDesCommandes42 tabCde = (TableDesCommandes42)obj[0];
		TableArticles42 tabArt = (TableArticles42)obj[1];
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
				afficherUneCde(tabCde);
				break;
			case 4:
				affiche(tabCde);
				break;
			case 5:
				facturer(tabCde, tabArt);
				break;
			case 6:
				modifier(tabCde, tabArt);
				break;
			case 0:
				break;}
		} catch (MenuPrecedentException mp) {
			choix = -1;
		} catch (RetourException rt) {
			choix = 0;}
		} while (choix != 0);
	}
	
	public void creer(Object ...obj) throws AbandonException,MenuPrecedentException {
	
		TableDesCommandes42 tabCde = (TableDesCommandes42)obj[0];
		TableArticles42 tabArt = (TableArticles42)obj[1];
		int numCde = (Integer) obj[2];
		TableLigneDeCommande42<Integer> cde = new TableLigneDeCommande42<Integer>();
		cde.setNumcommande(numCde);
		GestionTableLigneDeCommande42 gtldc = new GestionTableLigneDeCommande42();
		gtldc.menuGeneral(cde, tabArt);
		if(cde.taille() != 0)
			tabCde.ajouter(cde);
	}
	
	public int prochainNumero(TableDesCommandes42 tabCde) {
		
		int numero = 1;
		while(tabCde.retourner(numero) != null)
			numero++;
		return numero;
	}
	
	public void supprimer(Object ...obj) throws AbandonException,MenuPrecedentException {
		
		TableDesCommandes42 tabCde = (TableDesCommandes42)obj[0];
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
	
	public void afficherUneCde(Object ...obj) throws AbandonException,MenuPrecedentException {
		
		TableDesCommandes42 tabCde = (TableDesCommandes42)obj[0];
		if(tabCde.taille() != 0)
		{
			int num = es.saisie(tabCde.cle()+"quelle commande voulez vous visualiser ?\n", 0);
			TableLigneDeCommande42<Integer> cde = tabCde.retourner(num);
			if ( cde != null) {
				GestionTableLigneDeCommande42 gtldc1 = new GestionTableLigneDeCommande42();
				gtldc1.affiche(cde);
			}
			else
				es.affiche("Commande introuvable !");
		}
		else
			es.affiche("Aucune comande en cours !");
	}
	
	public void affiche(Object ...obj) {
		
		TableDesCommandes42 tabCde = (TableDesCommandes42)obj[0];
		if(tabCde.taille() != 0)
		{
			es.affiche("Liste des commandes \n\n"+tabCde.toString());
		}
		else
			es.affiche("Aucune comande en cours !");
	}
	
	public void facturer(TableDesCommandes42 tabCde,TableArticles42 tabArt) throws AbandonException,MenuPrecedentException {
		
		if(tabCde.taille() != 0)
		{
			int num = es.saisie(tabCde.cle()+"quelle commande voulez vous facturer ?", 0);
			TableLigneDeCommande42<Integer> cde = tabCde.retourner(num);
			if (cde != null) {
				GestionTableLigneDeCommande42 gtldc2 = new GestionTableLigneDeCommande42();
				gtldc2.facturer(cde, tabArt);
			}
			else
				es.affiche("Commande introuvable !");
		}
		else
			es.affiche("Aucune comande en cours !");
	}
	
	public void modifier(Object ...obj) throws AbandonException,MenuPrecedentException {
		
		TableDesCommandes42 tabCde = (TableDesCommandes42)obj[0];
		TableArticles42 tabArt = (TableArticles42)obj[1];
		if(tabCde.taille() != 0)
		{
			int num = es.saisie(tabCde.cle()+"quelle commande voulez vous modifier ?", 0);
			TableLigneDeCommande42<Integer> cde = tabCde.retourner(num);
			if (cde != null) {
				GestionTableLigneDeCommande42 gtldc3 = new GestionTableLigneDeCommande42();
				gtldc3.menuGeneral(cde, tabArt);
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
