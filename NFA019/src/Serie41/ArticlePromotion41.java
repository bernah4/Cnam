package Serie41;

import Serie21.Article;

public class ArticlePromotion41 extends Article {
	
	private int qtemin;
	private float reduc;
	
	public ArticlePromotion41 (int codearticle, String designation, float prixunitaire, int qtemin, float reduc) {
		super(codearticle, designation, prixunitaire);
		this.qtemin = qtemin;
		this.reduc = reduc;
	}
	
	public ArticlePromotion41 ()
	{}

	public int getQtemin() {
		return qtemin;
	}

	public float getReduc() {
		return reduc;
	}

	public void setQtemin(int qtemin) {
		this.qtemin = qtemin;
	}

	public void setReduc(float reduc) {
		this.reduc = reduc;
	}

	public String toString() {
		return (super.toString()+"    qte min: "+qtemin+"     pourcentage reduction: "+reduc+"%");
	}
	
	public String facturer(int qte) {
		return (super.facturer(qte) + "  avant reduction ("+(int)((1.196*this.getPrixunitaire()*qte*100))/100.0f+")");
	}
	
	public float prix(int qte)
	{
		if (qte < this.getQtemin())
			return super.prix(qte);
		return (this.getPrixunitaire() *(float)qte * (1 - this.getReduc()/100));
	}
}
