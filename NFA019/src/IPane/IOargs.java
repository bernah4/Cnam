package IPane;

import MesExceptions.AbandonException;
import MesExceptions.MenuPrecedentException;

import javax.swing.*;

public class IOargs {

	public void affiche(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	public boolean saisieOuiNon(String msg) {
		return (JOptionPane.showConfirmDialog(null, msg) == 0);
	}

	public Object saisie(Object... obj) throws AbandonException,MenuPrecedentException {
		int nbParam = obj.length;
		String msg = (String) obj[0];
		String data = null;
		if (nbParam == 0 || nbParam > 3)
			throw new MenuPrecedentException();
		do {
			try {
				data = JOptionPane.showInputDialog(null, msg);
				if (nbParam > 1 && obj[1] instanceof Integer) {
					int min = (Integer) obj[1];
					int max = Integer.MAX_VALUE;
					if (nbParam == 3)
						max = (Integer) obj[2];
					int valeur;
					valeur = Integer.parseInt(data);
					if (valeur >= min && valeur <= max)
						return valeur;
					else if (nbParam == 2)
						affiche("La valeur doit etre superieure a " + min);
					else if (nbParam == 3)
						affiche("La valeur doit etre comprise entre " + min + " et " + max);
				} else if (nbParam > 1 && obj[1] instanceof Float) {
					float min = (Float) obj[1];
					float max = Float.MAX_VALUE;
					if (nbParam == 3)
						max = (Float) obj[2];
					float valeur;
					valeur = Float.parseFloat(data);
					if (valeur >= min && valeur <= max)
						return valeur;
					else if (nbParam == 2)
						affiche("La valeur doit etre superieure a " + min);
					else if (nbParam == 3)
						affiche("La valeur doit etre comprise entre " + min + " et " + max);
				} else {
					if (data == null) {
						boolean abandon = saisieOuiNon("Voulez-vous abandoner ? o/n");
						if (abandon)
							throw new AbandonException();
						boolean menu = saisieOuiNon("Voulez-vous revenir au menu precedent ? o/n");
						if (menu)
							throw new MenuPrecedentException();
					}
					if (nbParam > 1 && (Boolean) obj[1] == true && data.isEmpty())
						affiche("Saisie obligatoire");

					else
						return data;
				}
			} catch (NumberFormatException ne) {
				if (data == null) {
					boolean abandon = saisieOuiNon("Voulez-vous quitter l'application ? o/n");
					if (abandon)
						throw new AbandonException();
					boolean menu = saisieOuiNon("Voulez-vous revenir au menu precedent ? o/n");
					if (menu)
						throw new MenuPrecedentException();
				} else if (data.isEmpty())
					affiche("Veuillez saisir qq chose !");
				else
					affiche("Saisie non numerique ! Recommencez !");
			}
		} while (true);
	}
}
