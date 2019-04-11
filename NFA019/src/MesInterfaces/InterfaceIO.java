package MesInterfaces;

import MesExceptions.AbandonException;
import MesExceptions.MenuPrecedentException;

public interface InterfaceIO {

	public boolean saisieOuiNon(String msg);
	public void affiche(String msg) ;
	
	public int saisie(String s, int min, int max) throws AbandonException,MenuPrecedentException;
	public int saisie(String s, int min) throws AbandonException,MenuPrecedentException;
	
	public float saisie(String s, float min, float max) throws AbandonException,MenuPrecedentException;
	public float saisie(String s, float min) throws AbandonException,MenuPrecedentException;
	
	public String saisie(String s) throws AbandonException,MenuPrecedentException;
	public String saisie(String s, boolean bol) throws AbandonException, MenuPrecedentException ;
}
