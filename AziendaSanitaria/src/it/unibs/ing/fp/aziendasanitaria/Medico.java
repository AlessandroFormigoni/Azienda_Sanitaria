package it.unibs.ing.fp.aziendasanitaria;
import java.util.*;

public class Medico extends Persona {
	private Settimana giornoDiRiposo;
	private static int counter = 0;
	private ArrayList<Paziente> pazienti = new ArrayList<>();

	public Medico(String nome, String cognome, Settimana giornoDiRiposo) {
		super(nome, cognome);
		this.giornoDiRiposo = giornoDiRiposo;
	}
	
	
	public Settimana getGiornoDiRiposo() {
		return giornoDiRiposo;
	}


	public void setGiornoDiRiposo(Settimana giornoDiRiposo) {
		this.giornoDiRiposo = giornoDiRiposo;
	}


	public static int getCounter() {
		return counter;
	}


	public static void setCounter(int counter) {
		Medico.counter = counter;
	}


	public ArrayList<Paziente> getPazienti() {
		return pazienti;
	}

	public void addPaziente(Paziente p) {
		this.pazienti.add(p);
	}


	public void setPazienti(ArrayList<Paziente> pazienti) {
		this.pazienti = pazienti;
	}

	public Paziente pazienteFromID(int id) {
		for (Paziente paziente : pazienti) {
			if(paziente.getId()==id)
				return paziente;
		}
		System.out.println("Paziente non trovato");
		return null;
	} 
	
	public int generateID() {
		int id = counter;
		counter++;
		return id;
	}
	
	public String toString() {
		return "Il medico " + getNome() + " " + getCognome() + " no " +getId() + " giorno di riposo " + giornoDiRiposo+ " ha come pazienti\n" + getPazienti();
	}

}
