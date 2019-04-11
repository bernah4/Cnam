package Serie51;

import java.io.Serializable;

public class LigneDeCommande51 implements Serializable{

	private int codearticle;
	private int quantitecommande;
	
	public LigneDeCommande51(int codearticle, int quantitecommande) {
		this.codearticle = codearticle;
		this.quantitecommande = quantitecommande;
	}
	
	public LigneDeCommande51() {}
	
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
	
	public String facturer(TableArticles51 tabArt) {
		AbstractArticle art = tabArt.retourner(this.getCodearticle());
		return (art.facturer(this.quantitecommande));
	}
	
	public float prix(TableArticles51 tabArt)
	{
		return (tabArt.retourner(this.getCodearticle()).prix(this.getQuantitecommande()));
	}
}
