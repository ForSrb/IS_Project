package domaci;

public class Ispit {
	private String sifra;
	private int prijavljeni;
	private int racunari;
	private String[] odseci;
	private Sala[] sale;
	private int termin;
	private int dan;
	
	public String getSifra() {
		return sifra;
	}
	
	public void setSifra(String sifra) {
		this.sifra = sifra;
	}
	
	public int getPrijavljeni() {
		return prijavljeni;
	}
	
	public void setPrijavljeni(int prijavljeni) {
		this.prijavljeni = prijavljeni;
	}
	
	public int getRacunari() {
		return racunari;
	}
	
	public void setRacunari(int racunari) {
		this.racunari = racunari;
	}
	
	public String[] getOdseci() {
		return odseci;
	}
	
	public void setOdseci(String[] odseci) {
		this.odseci = odseci;
	}

	public Sala[] getSale() {
		return sale;
	}

	public void setSale(Sala[] sale) {
		this.sale = sale;
	}

	public int getTermin() {
		return termin;
	}

	public void setTermin(int termin) {
		this.termin = termin;
	}

	public int getDan() {
		return dan;
	}

	public void setDan(int dan) {
		this.dan = dan;
	}

	@Override
	public String toString() {
		return "Ispit [sifra=" + sifra + "]";
	}
	
	
	
	
}
