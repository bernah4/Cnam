package Serie41;

import Serie21.Article;

public class LigneDeCommande41 {

	private int codearticle;
	private int quantitecommande;
	
	public LigneDeCommande41(int codearticle, int quantitecommande) {
		this.codearticle = codearticle;
		this.quantitecommande = quantitecommande;
	}
	
	public LigneDeCommande41() {}
	
	public int getCodearticle() {
		return codearticle;
	}
	public int getQuantitecommande() {
		return quantitecommande;
	}
	public void setCodearticle(int codearticle) {
		this.codearticle = codearticle;
	}
	public void setQuantitecommande(int quantitecommande) {
		this.quantitecommande = quantitecommande;
	}
	
	public String toString()
	{
		return ("code article : "+ codearticle+" quantite commande : "+quantitecommande);
	}
	
	public String facturer(TableArticles41 tabArt) {
		Article art = tabArt.retourner(this.getCodearticle());
		return (art.facturer(this.quantitecommande));
	}
	
	public float prix(TableArticles41 tabArt)
	{
		return (tabArt.retourner(this.getCodearticle()).prix(this.getQuantitecommande()));
	}
}
