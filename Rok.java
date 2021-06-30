package domaci;

import java.util.*;

public class Rok {
	private ArrayList<Ispit> ispiti;
	private ArrayList<Sala> sale;
	private int trajanjeDani;
	private String[][] termini;
	
	public Rok(ArrayList<Ispit> ispiti, ArrayList<Sala> sale, int trajanjeDani) {
		super();
		this.ispiti = ispiti;
		this.sale = sale;
		this.trajanjeDani = trajanjeDani;
	}


	public ArrayList<Ispit> getIspiti() {
		return ispiti;
	}


	public void setIspiti(ArrayList<Ispit> ispiti) {
		this.ispiti = ispiti;
	}


	public ArrayList<Sala> getSale() {
		return sale;
	}


	public void setSale(ArrayList<Sala> sale) {
		this.sale = sale;
	}


	public int getTrajanjeDani() {
		return trajanjeDani;
	}


	public void setTrajanjeDani(int trajanjeDani) {
		this.trajanjeDani = trajanjeDani;
	}
	
	
	public String[][] getTermini() {
		return termini;
	}


	public void setTermini(String[][] termini) {
		this.termini = termini;
	}

/*
	public void raspodeli() {
		ArrayList<Ispit> slobodniIspiti = this.ispiti;
		ArrayList<Ispit> uklonjeniIspiti = new ArrayList<Ispit>();
		ArrayList<Sala> slobodneSale = this.sale;
		ArrayList<Sala> uklonjeneSale = new ArrayList<Sala>();
		int termin = 0;
		
		while(slobodniIspiti.size() > 0) {
			Ispit ispit = slobodniIspiti.get(0);
			slobodniIspiti.remove(0);
			
			while(ispit.getPrijavljeni() > 0) {
				int brojSale = 0;
				Sala sala = slobodneSale.get(brojSale);
				if(ispit.getRacunari() != sala.getRacunari()) {
					brojSale++;
					continue;
				}
				
			}
			
		}
		
	}*/
	
	private ArrayList<ArrayList<Sala>> getAllCombinations(ArrayList<Sala> elements, Ispit ispit) {
		ArrayList<ArrayList<Sala>> combinationList = new ArrayList<ArrayList<Sala>>();
	    for ( long i = 1; i < Math.pow(2, elements.size()); i++ ) {
	        ArrayList<Sala> list = new ArrayList<Sala>();
	        for ( int j = 0; j < elements.size(); j++ ) {
	            if ( (i & (long) Math.pow(2, j)) > 0 ) {
	                list.add(elements.get(j));
	            }
	        }
	        
	        int kapacitetSala = 0;
	        //boolean uslov = false;
	        
	        for(int k = 0; k < list.size(); k++) {
	        	Sala sala = list.get(k);
	        	kapacitetSala += sala.getKapacitet();
	        	if(kapacitetSala >= ispit.getPrijavljeni()) {
	        		if(k == (list.size() - 1)) {
	        			combinationList.add(list);
	        		}
		        	//uslov = true;
		        	break;
		        }
	        }
	        //combinationList.add(list);
	  
	    }
	    
	    return combinationList;
	}
	
	public ArrayList<Kombinacija> generisiKombinacije(Ispit ispit, ArrayList<Sala> sale, int trajanjeDana) {
		ArrayList<Sala> moguceSale = new ArrayList<Sala>();
		for(int i=0; i<sale.size(); i++) {
			Sala sala = sale.get(i);
			if(ispit.getRacunari() == 1 && sala.getRacunari() == 1) {
				moguceSale.add(sala);
			}
			else if(ispit.getRacunari() == 0) {
				moguceSale.add(sala);
			}
		}
		
		ArrayList<ArrayList<Sala>> kombinacijaMogucihSala = this.getAllCombinations(moguceSale, ispit);
		ArrayList<Kombinacija> listaKombinacija = new ArrayList<Kombinacija>();
		for(int i=0; i<kombinacijaMogucihSala.size(); i++) {
			for(int j=0; j<4*this.trajanjeDani; j++) {
				Kombinacija kombinacija = new Kombinacija(ispit, kombinacijaMogucihSala.get(i), j);
				listaKombinacija.add(kombinacija);
			}
		}
		
		return listaKombinacija;
		
	}
	
	public Kombinacija[] generisiRok(ArrayList<ArrayList<Kombinacija>> sveKombinacije) {
		int[] indeksi = new int[sveKombinacije.size()];
		int trenutniIspit = 0;
		//ArrayList<Kombinacija> trenutneKombinacije = new ArrayList<Kombinacija>(sveKombinacije.size());
		Kombinacija[] trenutneKombinacije = new Kombinacija[sveKombinacije.size()];
		boolean uspesno;
		
		while(trenutniIspit < sveKombinacije.size()) {
			uspesno = true;
			Kombinacija komb = sveKombinacije.get(trenutniIspit).get(indeksi[trenutniIspit]);
			trenutneKombinacije[trenutniIspit]=komb;
			if(trenutniIspit == 0) trenutniIspit++;
			else {
				for(int i=0; i<trenutniIspit; i++) {
					if(!uspesno) break;
					Kombinacija kombZaProveru = trenutneKombinacije[i];
					
					if(komb.getTermin() == kombZaProveru.getTermin()) {
						for(int j=0; j<komb.getSale().size(); j++) {
							if(!uspesno) break;
							Sala sala1 = komb.getSale().get(j);
							for(int k=0; k<kombZaProveru.getSale().size(); k++) {
								Sala sala2 = kombZaProveru.getSale().get(k);
								if(sala1.equals(sala2)) {
									uspesno = false;
									break;
								}
							}
						}
					}
				}
				
				if(uspesno) {
					for(int i=0; i<trenutniIspit; i++) {
						if(!uspesno) break;
						Kombinacija kombZaProveru = trenutneKombinacije[i];
						
						if(komb.getIspit().getSifra().charAt(5) == kombZaProveru.getIspit().getSifra().charAt(5) && 
								(komb.getTermin() / 4) == (kombZaProveru.getTermin() / 4)) {
							
							for(int j=0; j<komb.getIspit().getOdseci().length; j++) {
								if(!uspesno) break;
								String odsek1 = komb.getIspit().getOdseci()[j];
								for(int k=0; k<kombZaProveru.getIspit().getOdseci().length; k++) {
									String odsek2 = kombZaProveru.getIspit().getOdseci()[k];
									if(odsek1.equals(odsek2)) {
										uspesno = false;
										break;
									}
								}
							}
						}
					}
				}
				
				if(uspesno) {
					trenutniIspit++;
				}
				else {
					trenutneKombinacije[trenutniIspit]=null;
					indeksi[trenutniIspit]++;
					while(trenutniIspit >= 0 && indeksi[trenutniIspit] == sveKombinacije.get(trenutniIspit).size()) {
						indeksi[trenutniIspit] = 0;
						trenutniIspit--;
						indeksi[trenutniIspit]++;
					}
					//if(indeksi[0] == 0) nema resenja
					
				}
			}
			for(int i =0; i<indeksi.length; i++)
				System.out.print(indeksi[i]+" ");
			System.out.println();
		}
		
		return trenutneKombinacije;
	}
	
}
