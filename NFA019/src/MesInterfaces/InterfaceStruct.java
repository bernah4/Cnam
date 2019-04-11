package MesInterfaces;

public interface InterfaceStruct<Cle, Metier> {
	
	public void ajouter(Metier metier);
	public void supprimer(Cle cle);
	public int taille();
	public Metier retourner(Cle cle);
	public String toString();

}
