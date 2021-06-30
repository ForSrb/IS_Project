package domaci;

public class Sala {
	public String naziv;
	public int kapacitet;
	public int racunari;
	public int dezurni;
	public int etf;
	
	
	
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public int getKapacitet() {
		return kapacitet;
	}
	public void setKapacitet(int kapacitet) {
		this.kapacitet = kapacitet;
	}
	public int getRacunari() {
		return racunari;
	}
	public void setRacunari(int racunari) {
		this.racunari = racunari;
	}
	public int getDezurni() {
		return dezurni;
	}
	public void setDezurni(int dezurni) {
		this.dezurni = dezurni;
	}
	public int getEtf() {
		return etf;
	}
	public void setEtf(int etf) {
		this.etf = etf;
	}
	@Override
	public String toString() {
		return "Sala [naziv=" + naziv + "]";
	}
	
	
}
