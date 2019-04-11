package Serie61;

public class Article61 extends AbstractArticle{
		
	public Article61 (int codearticle, String designation, float prixunitaire) {
		super(codearticle, designation, prixunitaire);
		this.setType("Normal");
	}
	
	public Article61 ()
	{
		this.setType("Normal");
	}

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
	
	public String[] listespec()
	{
		String[] ret = {""+getCodearticle(), getDesignation(), ""+getPrixunitaire(), getType(), "_", "_"};
		return ret;
	}
	
	public String[] listeFact(int qte)
	{
		String[] ret = {""+getCodearticle(), getDesignation(), ""+getPrixunitaire(), getType(), "_", "_", ""+qte, 
				""+(int)(prix(qte)*100)/100.0f, ""+(int)(prix(qte)*1.196*100)/100.0f};
		return ret;
	}
}
