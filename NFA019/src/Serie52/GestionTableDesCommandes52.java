package Serie52;

import Connexions.ConnectionFichiers;
import IPane.IO;
import MesExceptions.AbandonException;
import MesExceptions.MenuPrecedentException;
import MesExceptions.RetourException;
import MesInterfaces.InterfaceGestion;
import Utils.DateUser;

public class GestionTableDesCommandes52 implements InterfaceGestion<TableDesCommandes52>{
	
	IO es = new IO();
	private ConnectionFichiers<TableDesCommandes52> fichin;
	
	public GestionTableDesCommandes52(String nomPhy) {
		fichin = new ConnectionFichiers<TableDesCommandes52>(nomPhy);
	}
	
	public TableDesCommandes52 recuperer() {
		
		TableDesCommandes52 tabCde = fichin.lire();
		if(tabCde == null)
			tabCde = new TableDesCommandes52();
		return tabCde;
	}
	
	public void sauvegarder(TableDesCommandes52 tabCde) {
		
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
		TableDesCommandes52 tabCde = (TableDesCommandes52)obj[0];
		TableArticles52 tabArt = (TableArticles52)obj[1];
		do {
			try {
			String numCde = prochainNumero(tabCde);
			String msg = "\n --------------- MENU DE GESTION DES COMMANDES --------------- \n"
					+ "\t Creer la commande nº"+numCde+"          ............... 1\n"
					+ "\t supprimer une commande         ............... 2\n"
					+ "\t afficher une commande          ............... 3\n"
					+ "\t afficher toutes les commandes  ............... 4\n"
					+ "\t modifier une commande          ............... 5\n"
					+ "\t finir                          ............... 0\n" + "votre choix ?\n\n";
			choix = menuChoix(msg, 0, 5);
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
	
		TableDesCommandes52 tabCde = (TableDesCommandes52)obj[0];
		TableArticles52 tabArt = (TableArticles52)obj[1];
		String numCde = (String) obj[2];
		TableLigneDeCommande52<String> cde = new TableLigneDeCommande52<String>();
		cde.setNumcommande(numCde);
		GestionTableLigneDeCommande52 gtldc = new GestionTableLigneDeCommande52();
		gtldc.menuGeneral(cde, tabArt);
		if(cde.taille() != 0)
			tabCde.ajouter(cde);
	}
	
	public String prochainNumero(TableDesCommandes52 tabCde) {
		
		int numero = 1;
		DateUser date = new DateUser();
		String cle = ""+date.getAnnee() + date.getMois()+ date.getJour();
		String ret;
		do{
			ret = cle + numero;
			numero++;		
		}while(tabCde.retourner(ret) != null);
		return ret;
	}
	
	public void supprimer(Object ...obj) throws AbandonException,MenuPrecedentException {
		
		TableDesCommandes52 tabCde = (TableDesCommandes52)obj[0];
		if(tabCde.tailleFacture() != 0)
		{
			String num = es.saisie(tabCde.cleFacture()+"quelle commande voulez vous supprimer ?\n");
			TableLigneDeCommande52<String> cde = tabCde.retourner(num);
			if (cde != null)
			{
				if(cde.isEtatfacture())
				{
					DateUser verif = new DateUser();
					if (verif.estSuperieur(cde.getDatefacture().ajouterNombreDeJour(7)))
						tabCde.supprimer(num);
					else
						es.affiche("Vous ne pouvez pas supprimer une commande facturee de moins de 7 jours !");
				}
				else
					es.affiche("Commande non facturee !");
			}
			else
				es.affiche("Commande introuvable !");
		}
		else
			es.affiche("Aucune comande deja facturee en cours !");
	}
	
	public void afficherUneCde(Object ...obj) throws AbandonException,MenuPrecedentException {
		
		TableDesCommandes52 tabCde = (TableDesCommandes52)obj[0];
		if(tabCde.taille() != 0)
		{
			String num = es.saisie(tabCde.cle()+"quelle commande voulez vous visualiser ?\n");
			TableLigneDeCommande52<String> cde = tabCde.retourner(num);
			if ( cde != null) {
				GestionTableLigneDeCommande52 gtldc1 = new GestionTableLigneDeCommande52();
				gtldc1.affiche(cde);
			}
			else
				es.affiche("Commande introuvable !");
		}
		else
			es.affiche("Aucune comande en cours !");
	}
	
	public void affiche(Object ...obj) {
		
		TableDesCommandes52 tabCde = (TableDesCommandes52)obj[0];
		if(tabCde.taille() != 0)
		{
			es.affiche("Liste des commandes \n\n"+tabCde.toString());
		}
		else
			es.affiche("Aucune comande en cours !");
	}
	
	/*public void facturer(TableDesCommandes52 tabCde,TableArticles52 tabArt) throws AbandonException,MenuPrecedentException {
		
		if(tabCde.taille() != 0)
		{
			String num = es.saisie(tabCde.cle()+"quelle commande voulez vous facturer ?");
			TableLigneDeCommande52<String> cde = tabCde.retourner(num);
			if (cde != null) {
				GestionTableLigneDeCommande52 gtldc2 = new GestionTableLigneDeCommande52();
				gtldc2.facturer(cde, tabArt);
			}
			else
				es.affiche("Commande introuvable !");
		}
		else
			es.affiche("Aucune comande en cours !");
	}*/
	
	public void modifier(Object ...obj) throws AbandonException,MenuPrecedentException {
		
		TableDesCommandes52 tabCde = (TableDesCommandes52)obj[0];
		TableArticles52 tabArt = (TableArticles52)obj[1];
		if(tabCde.taille() != 0)
		{
			String num = es.saisie(tabCde.cleNonFacture()+"quelle commande voulez vous modifier ?");
			TableLigneDeCommande52<String> cde = tabCde.retourner(num);
			if (cde != null) {
				if(cde.isEtatfacture() == false)
				{
					GestionTableLigneDeCommande52 gtldc3 = new GestionTableLigneDeCommande52();
					gtldc3.menuGeneral(cde,tabArt);
					if (cde.taille() == 0)
						tabCde.supprimer(num);
				}
				else
					es.affiche("Impossible de modifier une commande deja facturee !");
			}
			else
				es.affiche("Commande introuvable !");
		}
		else
			es.affiche("Aucune comande en cours !");
	}
}
