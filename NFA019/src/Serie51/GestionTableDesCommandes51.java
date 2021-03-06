package Serie51;

import Connexions.ConnectionFichiers;
import IPane.IO;
import MesExceptions.AbandonException;
import MesExceptions.MenuPrecedentException;
import MesExceptions.RetourException;
import MesInterfaces.InterfaceGestion;
import Utils.DateUser;

public class GestionTableDesCommandes51 implements InterfaceGestion<TableDesCommandes51>{
	
	IO es = new IO();
	private ConnectionFichiers<TableDesCommandes51> fichin;
	
	public GestionTableDesCommandes51(String nomPhy) {
		fichin = new ConnectionFichiers<TableDesCommandes51>(nomPhy);
	}
	
	public TableDesCommandes51 recuperer() {
		
		TableDesCommandes51 tabCde = fichin.lire();
		if(tabCde == null)
			tabCde = new TableDesCommandes51();
		return tabCde;
	}
	
	public void sauvegarder(TableDesCommandes51 tabCde) {
		
		fichin.ecrire(tabCde, false);
	}
	
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
		TableDesCommandes51 tabCde = (TableDesCommandes51)obj[0];
		TableArticles51 tabArt = (TableArticles51)obj[1];
		do {
			try {
			String numCde = prochainNumero(tabCde);
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
				sauvegarder(tabCde);
				break;
			case 2:
				supprimer(tabCde);
				sauvegarder(tabCde);
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
				sauvegarder(tabCde);
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
	
		TableDesCommandes51 tabCde = (TableDesCommandes51)obj[0];
		TableArticles51 tabArt = (TableArticles51)obj[1];
		String numCde = (String) obj[2];
		TableLigneDeCommande51<String> cde = new TableLigneDeCommande51<String>();
		cde.setNumcommande(numCde);
		GestionTableLigneDeCommande51 gtldc = new GestionTableLigneDeCommande51();
		gtldc.menuGeneral(cde, tabArt);
		if(cde.taille() != 0)
			tabCde.ajouter(cde);
	}
	
	public String prochainNumero(TableDesCommandes51 tabCde) {
		
		int numero = 1;
		DateUser date = new DateUser();
		String cle = ""+date.getAnnee() + date.getMois()+ date.getJour();
		String ret;
		do{
			ret = cle + numero;
			numero++;		
		}while(tabCde.retourner(cle) != null);
		return ret;
	}
	
	public void supprimer(Object ...obj) throws AbandonException,MenuPrecedentException {
		
		TableDesCommandes51 tabCde = (TableDesCommandes51)obj[0];
		if(tabCde.taille() != 0)
		{
			String num = es.saisie(tabCde.cle()+"quelle commande voulez vous supprimer ?\n");
			if (tabCde.retourner(num) != null)
				tabCde.supprimer(num);
			else
				es.affiche("Commande introuvable !");
		}
		else
			es.affiche("Aucune comande en cours !");
	}
	
	public void afficherUneCde(Object ...obj) throws AbandonException,MenuPrecedentException {
		
		TableDesCommandes51 tabCde = (TableDesCommandes51)obj[0];
		if(tabCde.taille() != 0)
		{
			String num = es.saisie(tabCde.cle()+"quelle commande voulez vous visualiser ?\n");
			TableLigneDeCommande51<String> cde = tabCde.retourner(num);
			if ( cde != null) {
				GestionTableLigneDeCommande51 gtldc1 = new GestionTableLigneDeCommande51();
				gtldc1.affiche(cde);
			}
			else
				es.affiche("Commande introuvable !");
		}
		else
			es.affiche("Aucune comande en cours !");
	}
	
	public void affiche(Object ...obj) {
		
		TableDesCommandes51 tabCde = (TableDesCommandes51)obj[0];
		if(tabCde.taille() != 0)
		{
			es.affiche("Liste des commandes \n\n"+tabCde.toString());
		}
		else
			es.affiche("Aucune comande en cours !");
	}
	
	public void facturer(TableDesCommandes51 tabCde,TableArticles51 tabArt) throws AbandonException,MenuPrecedentException {
		
		if(tabCde.taille() != 0)
		{
			String num = es.saisie(tabCde.cle()+"quelle commande voulez vous facturer ?");
			TableLigneDeCommande51<String> cde = tabCde.retourner(num);
			if (cde != null) {
				
				GestionTableLigneDeCommande51 gtldc2 = new GestionTableLigneDeCommande51();
				gtldc2.facturer(cde, tabArt);
			}
			else
				es.affiche("Commande introuvable !");
		}
		else
			es.affiche("Aucune comande en cours !");
	}
	
	public void modifier(Object ...obj) throws AbandonException,MenuPrecedentException {
		
		TableDesCommandes51 tabCde = (TableDesCommandes51)obj[0];
		TableArticles51 tabArt = (TableArticles51)obj[1];
		if(tabCde.taille() != 0)
		{
			String num = es.saisie(tabCde.cle()+"quelle commande voulez vous modifier ?");
			TableLigneDeCommande51<String> cde = tabCde.retourner(num);
			if (cde != null) {
				GestionTableLigneDeCommande51 gtldc3 = new GestionTableLigneDeCommande51();
				gtldc3.menuGeneral(cde,tabArt);
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
