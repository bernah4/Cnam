package Serie21;

public class Article {
	
	private int codearticle;
	private String designation;
	private float prixunitaire;
	
	public Article (int codearticle, String designation, float prixunitaire) {
		this.codearticle = codearticle;
		this.designation = designation;
		this.prixunitaire = prixunitaire;
	}
	
	public Article ()
	{}

	public int getCodearticle() {
		return codearticle;
	}

	public String getDesignation() {
		return designation;
	}

	public float getPrixunitaire() {
		return prixunitaire;
	}

	public void setCodearticle(int codearticle) {
		this.codearticle = codearticle;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public void setPrixunitaire(float prixunitaire) {
		this.prixunitaire = prixunitaire;
	}
	
	public String toString() {
		return ("code article : "+codearticle+"     designation : "+designation+"     prix unitaire : "+prixunitaire);
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
	
}
