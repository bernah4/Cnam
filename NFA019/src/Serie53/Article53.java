package Serie53;

public class Article53 extends AbstractArticle{
		
	public Article53 (int codearticle, String designation, float prixunitaire) {
		super(codearticle, designation, prixunitaire);
	}
	
	public Article53 ()
	{}

	public String toString() {
		return ("code article : "+this.getCodearticle()+"     designation : "+this.getDesignation()+
				"     prix unitaire : "+this.getPrixunitaire());
	}
	
	public float prix(int qte)
	{
		return (this.getPrixunitaire() *(float)qte);
	}
	
	public String facturer(int qte) {
		return (this.getCodearticle()+"\t\t\t"
		+this.getDesignation()+"\t\t"
		+this.getPrixunitaire()+"\t\t\t"
		+qte+"\t\t"
		+(int)(prix(qte)*100)/100.0f+"\t\t"
		+(int)(prix(qte)*1.196*100)/100.0f);
	}
	
	public String infoArt()
	{
		return ("");			
	}
}
