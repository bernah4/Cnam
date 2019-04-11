package MesInterfaces;

public interface InterfaceGestion<TypeStruct> {

	public void menuGeneral(Object ...obj) throws Exception;
	public int menuChoix(String msg, int min, int max) throws Exception;
	public void affiche(Object ...obj);
	public void creer(Object ...obj) throws Exception;
	public void supprimer(Object ...obj) throws Exception;
	public void modifier(Object ...obj) throws Exception;

}
