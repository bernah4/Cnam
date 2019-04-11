package Serie52;

public class ArticlePromotion52 extends AbstractArticle {
	
	private int qtemin;
	private float reduc;
	
	public ArticlePromotion52 (int codearticle, String designation, float prixunitaire, int qtemin, float reduc) {
		super(codearticle, designation, prixunitaire);
		this.qtemin = qtemin;
		this.reduc = reduc;
	}
	
	public ArticlePromotion52 ()
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
		return ("code article : "+this.getCodearticle()+"     designation : "+this.getDesignation()+
				"     prix unitaire : "+this.getPrixunitaire()+"    qte min: "+qtemin+"     pourcentage reduction: "+reduc+"%");
	}
	
	public String facturer(int qte) {
		return (this.getCodearticle()+"\t\t\t"
				+this.getDesignation()+"\t\t"
				+this.getPrixunitaire()+"\t\t\t"
				+qte+"\t\t"
				+(int)(prix(qte)*100)/100.0f+"\t\t"
				+(int)(prix(qte)*1.196*100)/100.0f+ "  avant reduction ("+(int)((1.196*this.getPrixunitaire()*qte*100))/100.0f+")");
	}
	
	public float prix(int qte)
	{
		if (qte < this.getQtemin())
			return (this.getPrixunitaire() *(float)qte);
		return (this.getPrixunitaire() *(float)qte * (1 - this.getReduc()/100));
	}
}
