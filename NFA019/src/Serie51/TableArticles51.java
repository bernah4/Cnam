package Serie51;


import java.io.Serializable;
import java.util.TreeMap;
import MesInterfaces.InterfaceStruct;

public class TableArticles51 implements InterfaceStruct<Integer,AbstractArticle>, Serializable{

	private TreeMap<Integer,AbstractArticle> tabArt = new TreeMap<Integer,AbstractArticle>();
	
	public TableArticles51() {
		AbstractArticle hdd = new Article51 (1, "disaue dur 500Go", 50);
		AbstractArticle cartemere = new Article51 (4, "carte mere tx38", 150);
		AbstractArticle cartereseaux = new Article51 (5, "carte reseau 10Gbs", 60);
		AbstractArticle ram = new Article51 (18, "8Go DDR4 cl11 2300Mhz", 90);
		AbstractArticle ecran_oled = new ArticlePromotion51(12, "Ecran oled top moumoute", 300, 10, (float) 35);
		AbstractArticle cle_usb = new ArticlePromotion51(15, "Cle USB 3.0 50Go", 40, 20, (float) 60);

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
			if(art instanceof ArticlePromotion51)
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
