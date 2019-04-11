package Serie61;

public class ArticlePromotion61 extends AbstractArticle {
	
	private int qtemin;
	private float reduc;
	
	public ArticlePromotion61 (int codearticle, String designation, float prixunitaire, int qtemin, float reduc) {
		super(codearticle, designation, prixunitaire);
		this.qtemin = qtemin;
		this.reduc = reduc;
		this.setType("Promo");

	}
	
	public ArticlePromotion61 ()
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
	
	public String infoArt()
	{
		return ("Cet article est en promo ! A partir de "+this.getQtemin() +" articles achetes, une reduction de "
				+this.getReduc()+"%\n");			
	}
	
	public String[] listespec()
	{
		String[] ret = {""+getCodearticle(), getDesignation(), ""+getPrixunitaire(), getType(), ""+qtemin, ""+reduc+"%"};
		return ret;
	}
	
	public String[] listeFact(int qte)
	{
		String[] ret = {""+getCodearticle(), getDesignation(), ""+getPrixunitaire(), getType(), ""+qtemin, ""+reduc+"%", ""+qte, 
				""+(int)(prix(qte)*100)/100.0f, ""+(int)(prix(qte)*1.196*100)/100.0f};
		return ret;
	}
}
