package Serie21;

import java.util.Vector;

public class TableArticles21 {

	private Vector<Article> tabArt = new Vector<Article>();
	
	public TableArticles21() {
		Article hdd = new Article (1, "disaue dur 500Go", 50);
		Article cartemere = new Article (4, "carte mere tx38", 150);
		Article cartereseaux = new Article (5, "carte reseau 10Gbs", 60);
		Article ram = new Article (18, "8Go DDR4 cl11 2300Mhz", 90);

		
		tabArt.add(hdd);
		tabArt.add(cartemere);
		tabArt.add(cartereseaux);
		tabArt.add(ram);

	}

	public Vector<Article> getTabArt() {
		return tabArt;
	}

	public void setTabArt(Vector<Article> tabArt) {
		this.tabArt = tabArt;
	}
	
	public String toString() {
		String ret = "";
		for (Article art : tabArt)
			ret = ret + art.toString()+ "\n";
		return ret;
	}
	
	public Article retourner(int code) {
		for (Article art : tabArt) {
			if (art.getCodearticle() == code)
				return art;
		}
		return null;
	}
	
	public Article retournerIndice(int indice) {
		return tabArt.get(indice);
	}
	
	public void supprimer(int code) {
		for (int i = 0; i < taille() ; i++) {
			if (tabArt.get(i).getCodearticle() == code)
				tabArt.remove(i);
		}
	}
	
	public void ajouter(Article a) {
		tabArt.add(a);
	}
	
	public int taille() {
		return tabArt.size();
	}
	
}
