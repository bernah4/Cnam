package Serie22;

import java.util.TreeMap;
import Serie21.Article;

public class TableArticles22 {

	private TreeMap<Integer,Article> tabArt = new TreeMap<Integer,Article>();
	
	public TableArticles22() {
		Article hdd = new Article (1, "disaue dur 500Go", 50);
		Article cartemere = new Article (4, "carte mere tx38", 150);
		Article cartereseaux = new Article (5, "carte reseau 10Gbs", 60);
		Article ram = new Article (18, "8Go DDR4 cl11 2300Mhz", 90);

		
		tabArt.put(hdd.getCodearticle(),hdd);
		tabArt.put(cartemere.getCodearticle(),cartemere);
		tabArt.put(cartereseaux.getCodearticle(),cartereseaux);
		tabArt.put(ram.getCodearticle(),ram);

	}

	public TreeMap<Integer,Article> getTabArt() {
		return tabArt;
	}

	public void setTabArt(TreeMap<Integer,Article> tabArt) {
		this.tabArt = tabArt;
	}
	
	public String toString() {
		String ret = "";
		for (Article art : tabArt.values())
			ret = ret + art.toString()+ "\n";
		return ret;
	}
	
	public String cle() {
		String ret ="";
		for(Integer key : tabArt.keySet())
			ret = ret + key.toString() + "\n";
		return ret;
	}
	
	public Article retourner(int code) {
		return tabArt.get(code);
	}
	
	public void supprimer(int code) {
		tabArt.remove(code);
	}

	
	public void ajouter(Article a) {
		tabArt.put(a.getCodearticle(),a);
	}
	
	public int taille() {
		return tabArt.size();
	}
	
}
