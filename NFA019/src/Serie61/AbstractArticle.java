package Serie61;

import java.io.Serializable;

public abstract class AbstractArticle implements Serializable{
	
	private int codearticle;
	private String designation;
	private float prixunitaire;
	private String type;
	
	public AbstractArticle (int codearticle, String designation, float prixunitaire) {
		this.codearticle = codearticle;
		this.designation = designation;
		this.prixunitaire = prixunitaire;
	}
	
	public AbstractArticle ()
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

	public String getType(){
		return type;
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
	
	public void setType(String type) {
		this.type = type;
	}
	
	public abstract String[] listespec();
	
	public abstract String[] listeFact(int qte);
	
	public abstract String toString();
	
	public abstract float prix(int qte);
	
	public abstract String facturer(int qte);
	
	public abstract String infoArt();
	
}
