package Serie21;

import Serie22.TableArticles22;

public class LigneDeCommande {

	private int codearticle;
	private int quantitecommande;
	
	public LigneDeCommande(int codearticle, int quantitecommande) {
		this.codearticle = codearticle;
		this.quantitecommande = quantitecommande;
	}
	
	public LigneDeCommande() {}
	
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
	
	public String facturer(TableArticles21 tabArt) {
		float a = prixTotal(tabArt);
		return (tabArt.retourner(this.getCodearticle()).getCodearticle()+"\t\t\t"
		+tabArt.retourner(this.getCodearticle()).getDesignation()+"\t\t"
		+tabArt.retourner(this.getCodearticle()).getPrixunitaire()+"\t\t\t"
		+this.getQuantitecommande()+"\t\t"
		+a+"\t\t"
		+(int)(a*1.196*100)/100.0f);
	}
	
	public String newfacturer(TableArticles22 tabArt) {
		float a = newprixTotal(tabArt);
		return (tabArt.retourner(this.getCodearticle()).getCodearticle()+"\t\t\t"
		+tabArt.retourner(this.getCodearticle()).getDesignation()+"\t\t"
		+tabArt.retourner(this.getCodearticle()).getPrixunitaire()+"\t\t\t"
		+this.getQuantitecommande()+"\t\t"
		+a+"\t\t"
		+(int)(a*1.196*100)/100.0f);
	}
	
	public float prixTotal(TableArticles21 tabArt) {
		float a = this.getQuantitecommande()*tabArt.retourner(this.getCodearticle()).getPrixunitaire();
		return (int)(a*100)/100.0f;
	}
	
	public float newprixTotal(TableArticles22 tabArt) {
		float a = this.getQuantitecommande()*tabArt.retourner(this.getCodearticle()).getPrixunitaire();
		return (int)(a*100)/100.0f;
	}
	
	
}
