package Serie53;


import java.io.Serializable;
import java.util.TreeMap;
import MesInterfaces.InterfaceStruct;
import Utils.DateUser;

public class TableArticles53 implements InterfaceStruct<Integer,AbstractArticle>, Serializable{

	private TreeMap<Integer,AbstractArticle> tabArt = new TreeMap<Integer,AbstractArticle>();
	
	public TableArticles53() {
		AbstractArticle hdd = new Article53 (1, "disaue dur 500Go", 50);
		AbstractArticle cartemere = new Article53 (4, "carte mere tx38", 150);
		AbstractArticle cartereseaux = new Article53 (5, "carte reseau 10Gbs", 60);
		AbstractArticle ram = new Article53 (18, "8Go DDR4 cl11 2300Mhz", 90);
		AbstractArticle ecran_oled = new ArticlePromotion53(12, "Ecran oled top moumoute", 300, 10, (float) 35);
		AbstractArticle cle_usb = new ArticlePromotion53(15, "Cle USB 3.0 50Go", 40, 20, (float) 60);

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
			if(art.infoArt() != "")
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
