package it.unibs.ing.fp.aziendasanitaria;
import UniBSFpLib.bin.it.unibs.fp.mylib.InputDati;

public class Paziente extends Persona {

	public Paziente(String nome, String cognome) {
		super(nome, cognome);
	}
	
	
	public int generateID() {
		int id = InputDati.leggiIntero("Inserisci il codice della tessera sanitaria: ");
		return id;
	}
	
	public String toString() {
		return "Il paziente "+ getNome() + " " + getCognome() + " con codice " + getId()+"\n";
	}
	
	
}
