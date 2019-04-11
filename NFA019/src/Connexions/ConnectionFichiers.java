package Connexions;

import java.io.*;
import IConsole.IO;

public class ConnectionFichiers<TypeStruct> {

	private String NomPhysiqueDeLaTable;
	private IO io = new IO();

	public ConnectionFichiers(String nom) {
		this.NomPhysiqueDeLaTable = nom;
	}

	@SuppressWarnings("unchecked")
	public TypeStruct lire() {

		TypeStruct tab = null;
		try {
			FileInputStream fis = new FileInputStream(NomPhysiqueDeLaTable);
			ObjectInputStream ois = new ObjectInputStream(fis);
			tab = (TypeStruct) ois.readObject();
			ois.close();
		} catch (FileNotFoundException fnf) {
			io.affiche("Fichier introuvable\n");
		} catch (IOException ioe) {
			io.affiche("Probleme de lecture\n");
		} catch (ClassNotFoundException cnf) {
			io.affiche("Incompatible type\n");
		}
		return tab;
	}
	
	public void ecrire(TypeStruct tab, boolean ok) {

		try {
			FileOutputStream fos = new FileOutputStream(NomPhysiqueDeLaTable, ok);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(tab);
			oos.close();
		} catch (IOException ioe) {
			io.affiche("Probleme d'ecriture\n");
		} 
	}

}
