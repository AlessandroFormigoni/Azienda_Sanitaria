package it.unibs.ing.fp.aziendasanitaria;
import java.util.ArrayList;

public class Azienda {
	private ArrayList<Medico> medici = new ArrayList<>();
	private ArrayList<Paziente> totalePazienti = new ArrayList<>();
	
	public void addMedico(Medico m) {
		medici.add(m);
		for(Paziente paziente : m.getPazienti()) {
			if(!totalePazienti.contains(paziente))
				totalePazienti.add(paziente);
		}
	}
	
	public Medico medicoFromID(int id) {
		for (Medico medico : medici) {
			if (medico.getId()==id)
				return medico;
		}
		System.out.println("Medico non trovato");
		return null;
	}
	
	public ArrayList<Medico> medicoFromGiorno(Settimana giorno) {
		ArrayList<Medico> nonARiposo = this.medici;
		for (Medico medico : medici) {
			if(medico.getGiornoDiRiposo().equals(giorno))
				nonARiposo.remove(medico);
		}
		return nonARiposo;
	}
	
	public Paziente pazienteFromID(int id) {
		for (Paziente p : totalePazienti) {
			if(p.getId()==id)
				return p;
		}
		System.out.println("Paziente non trovato");
		return null;
	}
	
	public Medico medicoFromPaziente(Paziente p) {
		for (Medico medico : medici) {
			if (medico.getPazienti().contains(p))
				return medico;
		}
		return null;
	}
	
	public void substitute(Paziente p, Medico m) {
		medicoFromPaziente(p).getPazienti().remove(p);
		m.addPaziente(p);
	}

	public ArrayList<Medico> getMedici() {
		return medici;
	}

	public void setMedici(ArrayList<Medico> medici) {
		this.medici = medici;
	}

	public ArrayList<Paziente> getTotalePazienti() {
		return totalePazienti;
	}

	public void setTotalePazienti(ArrayList<Paziente> totalePazienti) {
		this.totalePazienti = totalePazienti;
	}
	
	

}
