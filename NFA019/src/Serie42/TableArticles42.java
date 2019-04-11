package Serie42;


import java.util.TreeMap;

import MesInterfaces.InterfaceStruct;

public class TableArticles42 implements InterfaceStruct<Integer,AbstractArticle>{

	private TreeMap<Integer,AbstractArticle> tabArt = new TreeMap<Integer,AbstractArticle>();
	
	public TableArticles42() {
		AbstractArticle hdd = new Article42 (1, "disaue dur 500Go", 50);
		AbstractArticle cartemere = new Article42 (4, "carte mere tx38", 150);
		AbstractArticle cartereseaux = new Article42 (5, "carte reseau 10Gbs", 60);
		AbstractArticle ram = new Article42 (18, "8Go DDR4 cl11 2300Mhz", 90);
		AbstractArticle ecran_oled = new ArticlePromotion42(12, "Ecran oled top moumoute", 300, 10, (float) 35);
		AbstractArticle cle_usb = new ArticlePromotion42(15, "Cle USB 3.0 50Go", 40, 20, (float) 60);

		ajouter(hdd);
		ajouter(cartemere);
		ajouter(cartereseaux);
		ajouter(ram);
		ajouter(ecran_oled);
		ajouter(cle_usb);

	}

	public TreeMap<Integer,AbstractArticle> getTabArt() {
		return tabArt;
	}

	public void setTabArt(TreeMap<Integer,AbstractArticle> tabArt) {
		this.tabArt = tabArt;
	}
	
	public String toString() {
		String ret = "";
		for (AbstractArticle art : tabArt.values())
			ret = ret + art.toString()+ "\n";
		return ret;
	}
	
	public String toStringPromo() {
		String ret = "";
		for (AbstractArticle art : tabArt.values())
		{
			if(art instanceof ArticlePromotion42)
				ret = ret + art.toString()+ "\n";
		}
		return ret;
	}
	
	public String cle() {
		String ret ="";
		for(Integer key : tabArt.keySet())
			ret = ret + key.toString() + "\n";
		return ret;
	}
	
	public AbstractArticle retourner(Integer code) {
		return tabArt.get(code);
	}
	
	public void supprimer(Integer code) {
		tabArt.remove(code);
	}

	
	public void ajouter(AbstractArticle a) {
		tabArt.put(a.getCodearticle(),a);
	}
	
	public int taille() {
		return tabArt.size();
	}
	
}
