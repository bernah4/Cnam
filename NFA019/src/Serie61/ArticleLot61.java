package Serie61;

import Utils.DateUser;

public class ArticleLot61 extends AbstractArticle {
	
	private int qtelot;
	private float reduc;
	private DateUser begin;
	private DateUser end;
	
	public ArticleLot61(int codearticle, String designation, float prixunitaire, int qtelot, float reduc)
	{
		super(codearticle, designation, prixunitaire);
		this.qtelot = qtelot;
		this.reduc = reduc;
		this.begin = new DateUser();
		this.end = begin.ajouterNombreDeJour(7);
		this.setType("Lot");

	}

	public int getQtelot() {
		return qtelot;
	}

	public float getReduc() {
		return reduc;
	}

	public DateUser getBegin() {
		return begin;
	}

	public DateUser getEnd() {
		return end;
	}

	public void setQtelot(int qtelot) {
		this.qtelot = qtelot;
	}

	public void setReduc(int reduc) {
		this.reduc = reduc;
	}

	public void setBegin(DateUser begin) {
		this.begin = begin;
	}

	public void setEnd(DateUser end) {
		this.end = end;
	}

	public String toString() {
		return ("code article : "+this.getCodearticle()+"     designation : "+this.getDesignation()+
				"     prix unitaire : "+this.getPrixunitaire()+"    qte/lot "+qtelot+"     pourcentage reduction: "+reduc+"%"
				+"   date : "+begin+" - "+end);
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
		int nbrlot = qte / qtelot;
		int horslot = qte % qtelot;
		DateUser date = new DateUser();
		if (date.estSuperieur(this.end) || this.begin.estSuperieur(date))
			return (this.getPrixunitaire() * qte);
		else
			return (this.getPrixunitaire() * ((float)nbrlot * qtelot * (1 - this.getReduc()/100) + horslot));
	}
	
	public String infoArt()
	{
		return ("Cet article est en promo par lot ! Chaque lot de "+this.getQtelot() +" articles achetes, une reduction de "
				+this.getReduc()+"% sur chaque lot ! \n");			
	}
	
	public String[] listespec()
	{
		String[] ret = {""+getCodearticle(), getDesignation(), ""+getPrixunitaire(), getType(), ""+qtelot, ""+reduc+"%"};
		return ret;
	}
	
	public String[] listeFact(int qte)
	{
		String[] ret = {""+getCodearticle(), getDesignation(), ""+getPrixunitaire(), getType(), ""+qtelot, ""+reduc+"%", ""+qte, 
				""+(int)(prix(qte)*100)/100.0f, ""+(int)(prix(qte)*1.196*100)/100.0f};
		return ret;
	}
}
