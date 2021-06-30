package domaci;

import java.io.*;

import java.util.*;
import org.json.*;

public class Main  {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader reader = new BufferedReader(new FileReader("sale1.json"));
		String json = "";
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = reader.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append("\n");
		        line = reader.readLine();
		    }
		    json = sb.toString();
		} finally {
		    reader.close();
		}
		
		//System.out.println(json);
		
		JSONArray array = new JSONArray(json);

		ArrayList<Sala> SaleList = new ArrayList<Sala>(array.length());
		for(int i=0; i<array.length(); i++){
		    JSONObject temp = array.getJSONObject(i);
		    Sala sala = new Sala();
		    
		    sala.setNaziv(temp.getString("naziv"));
		    sala.setKapacitet(temp.getInt("kapacitet"));
		    sala.setRacunari(temp.getInt("racunari"));
		    sala.setDezurni(temp.getInt("dezurni"));
		    sala.setEtf(temp.getInt("etf"));
		    
		    SaleList.add(i, sala);
		    
		}
		

		
		reader = new BufferedReader(new FileReader("rok1.json"));
		json = "";
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = reader.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append("\n");
		        line = reader.readLine();
		    }
		    json = sb.toString();
		} finally {
		    reader.close();
		}
		
		JSONObject object = new JSONObject(json);
		
		array = object.getJSONArray("ispiti");
		
		ArrayList<Ispit> listaIspita = new ArrayList<Ispit>(array.length());
		int trajanjeDani = object.getInt("trajanje_u_danima");
		
		for(int i=0; i<array.length(); i++){
		    JSONObject temp = array.getJSONObject(i);
		    Ispit ispit = new Ispit();
		    ispit.setSifra(temp.getString("sifra"));
		    ispit.setPrijavljeni(temp.getInt("prijavljeni"));
		    ispit.setRacunari(temp.getInt("racunari"));
		    
		    
		    //reading odseci
		    
		    JSONArray odseci = temp.getJSONArray("odseci");
		    String [] nizOdseka = new String[odseci.length()];
		    for(int j =0; j<odseci.length();j++) {
		    	nizOdseka[j] = String.valueOf(odseci.get(j));
		    }
		    ispit.setOdseci(nizOdseka);
		    
		    
		    
		    
			    listaIspita.add(i , ispit);
		}
		
		for(int i=0; i<SaleList.size() ; i++) {
			//System.out.println(SaleList.get(i).getNaziv());
		}
		
		for(int i=0; i<listaIspita.size() ; i++) {
			//System.out.println(listaIspita.get(i).getSifra());
		}
		
		ArrayList<ArrayList<Kombinacija>> sveKombinacije = new ArrayList<ArrayList<Kombinacija>>();
		
		
		
		Rok rok = new Rok(listaIspita, SaleList, trajanjeDani);
		for(int i=0; i<listaIspita.size(); i++) {
			sveKombinacije.add(rok.generisiKombinacije(listaIspita.get(i), SaleList, trajanjeDani));
		}
		
		System.out.println(sveKombinacije.size()+"sve kom");
		
		Kombinacija[] konacneKombinacije = rok.generisiRok(sveKombinacije);
		
		for(int i=0; i<konacneKombinacije.length; i++) {
			System.out.println(konacneKombinacije[i].toString());
		}
		
		System.out.println(konacneKombinacije.length+"konacne kom");
		
	}
	

}
