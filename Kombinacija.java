package domaci;

import java.util.ArrayList;

public class Kombinacija {
	private Ispit ispit;
	private ArrayList<Sala> sale;
	private int termin;
	
	public Kombinacija(Ispit ispit, ArrayList<Sala> sale, int termin) {
		super();
		this.ispit = ispit;
		this.sale = sale;
		this.termin = termin;
	}

	public Ispit getIspit() {
		return ispit;
	}
	
	public void setIspit(Ispit ispit) {
		this.ispit = ispit;
	}
	
	public ArrayList<Sala> getSale() {
		return sale;
	}
	
	public void setSale(ArrayList<Sala> sale) {
		this.sale = sale;
	}
	
	public int getTermin() {
		return termin;
	}
	
	public void setTermin(int termin) {
		this.termin = termin;
	}

	@Override
	public String toString() {
		return "Kombinacija [ispit=" + ispit + ", sale=" + sale + ", termin=" + termin + "]";
	}
	
	
	
}
