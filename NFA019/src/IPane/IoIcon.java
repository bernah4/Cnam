package IPane;



import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import MesExceptions.AbandonException;
import MesExceptions.MenuPrecedentException;
import MesInterfaces.InterfaceIO;

public class IoIcon {

	public void affiche(String msg, Icon icon) {
		JOptionPane.showMessageDialog(null, msg, "", JOptionPane.ERROR_MESSAGE, icon);
	}

	public boolean saisieOuiNon(String msg) {
		return (JOptionPane.showConfirmDialog(null, msg) == 0);
	}

	public int saisie(String s, int min, int max) throws AbandonException, MenuPrecedentException {

		do {
			try {
				String valeur = JOptionPane.showInputDialog(null, s);
				if (valeur == null)
					throw new AbandonException();
				int data = Integer.parseInt(valeur);
				if (data >= min && data <= max)
					return data;
				throw new MenuPrecedentException();
			} catch (NumberFormatException ie) {
				affiche("Saisie non numerique ! Recommencez !", new ImageIcon("Icon/alert-icon.png"));
			} catch (AbandonException ae) {
				boolean abandon = saisieOuiNon("Voulez-vous abandonner ? o/n");
				if (abandon)
					throw ae;
				boolean menu = saisieOuiNon("Voulez-vous revenir au menu precedent ? o/n");
				if (menu)
					throw new MenuPrecedentException();
				else
					affiche("Ressaisissez svp !", new ImageIcon("Icon/alert-icon.png"));
			}catch (MenuPrecedentException ae) {
				if (max < Integer.MAX_VALUE)
					affiche("La valeur doit etre > a"+min+" et <"+ max, new ImageIcon("Icon/alert-icon.png"));
				else
					affiche("La valeur doit etre > a"+min, new ImageIcon("Icon/alert-icon.png"));
			}
		} while (true);
	}

	public int saisie(String s, int min) throws AbandonException, MenuPrecedentException {
		
		return saisie(s, min, Integer.MAX_VALUE);
	}

	public float saisie(String s, float min, float max) throws AbandonException, MenuPrecedentException {

		do {
			try {
				String valeur = JOptionPane.showInputDialog(null, s);
				if (valeur == null)
					throw new AbandonException();
				float data = Float.parseFloat(valeur);
				if (data >= min && data <= max)
					return data;
				throw new MenuPrecedentException();
			} catch (NumberFormatException ie) {
				affiche("Saisie non numerique ! Recommencez !", new ImageIcon("Icon/alert-icon.png"));
			} catch (AbandonException ae) {
				boolean abandon = saisieOuiNon("Voulez-vous abandonner ? o/n");
				if (abandon)
					throw ae;
				boolean menu = saisieOuiNon("Voulez-vous revenir au menu precedent ? o/n");
				if (menu)
					throw new MenuPrecedentException();
				else
					affiche("Ressaisissez svp !", new ImageIcon("Icon/alert-icon.png"));
			}catch (MenuPrecedentException ae) {
				if (max < Integer.MAX_VALUE)
					affiche("La valeur doit etre > a"+min+" et <"+ max, new ImageIcon("Icon/alert-icon.png"));
				else
					affiche("La valeur doit etre > a"+min, new ImageIcon("Icon/alert-icon.png"));
			}
		} while (true);
	}

	public float saisie(String s, float min) throws AbandonException, MenuPrecedentException {

		return saisie(s, min, Float.MAX_VALUE);
	}

	public String saisie(String s) throws AbandonException, MenuPrecedentException {
		return saisie(s, false);
	}

	public String saisie(String s, boolean bol) throws AbandonException, MenuPrecedentException {

		do {
			try {
				String saisie = JOptionPane.showInputDialog(null, s);
				if (saisie == null)
					throw new AbandonException();
				else if (saisie.isEmpty() && bol == true)
					throw new MenuPrecedentException();
				else
					return saisie;
			} catch (AbandonException ae) {
				boolean abandon = saisieOuiNon("Voulez-vous abandonner ? o/n");
				if (abandon)
					throw ae;
				boolean menu = saisieOuiNon("Voulez-vous revenir au menu precedent ? o/n");
				if (menu)
					throw new MenuPrecedentException();
				else
					affiche("Ressaisissez svp !", new ImageIcon("Icon/alert-icon.png"));
			}catch (MenuPrecedentException ae) {
				affiche("Saisie obligatoire", new ImageIcon("Icon/alert-icon.png"));
			}
		} while (true);
	}
}
