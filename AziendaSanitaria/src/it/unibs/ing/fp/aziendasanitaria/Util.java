package it.unibs.ing.fp.aziendasanitaria;
import UniBSFpLib.bin.it.unibs.fp.mylib.*;

public class Util {
	
	
	public static Paziente generatePaziente() {
		String nome;
		String cognome;
		try {
			nome = InputDati.leggiStringaNonVuota("Inserisci nome paziente: ");
			cognome = InputDati.leggiStringaNonVuota("Inserisci cognome paziente: ");
			return new Paziente(nome,cognome);
		} catch (Exception e) {System.out.println("Errore nella creazione");}
		
		return null;
	}
	
	public static Medico generateMedico() {
		Medico med;
		String nome;
		String cognome;
		Settimana giorno;
		boolean loop = false;
		try {
			nome = InputDati.leggiStringaNonVuota("Inserisci nome medico: ");
			cognome = InputDati.leggiStringaNonVuota("Inserisci cognome medico: ");
			giorno = Settimana.valueOf(InputDati.leggiStringaNonVuota("Inserisci giorno di riposo del medico: ").toUpperCase());
			med = new Medico(nome,cognome,giorno);
			
			do {
				System.out.println("Inserisci nuovo paziente:");
				med.addPaziente(generatePaziente());
				loop = InputDati.yesOrNo("Inserire altri pazienti?");
			} while (loop);
			
			return med;
		} catch (Exception e) {System.out.println("Errore nella creazione");}	
		
		return null;
	
	}
	
	public static void menu(Azienda asl) {
		boolean loop = true;
		System.out.println("Benvenuto nel programma di gestione dell'ASL");
		System.out.println("Inserire cosa vuoi fare: ");
		do {
			System.out.println("--------------------------------------------");
			System.out.println("1. Aggiungi medico");
			System.out.println("2. Aggiungi un paziente");
			System.out.println("3. Cerca medico");
			System.out.println("4. Cerca paziente");
			System.out.println("5. Riassegna paziente");
			System.out.println("6. Salva i dati inseriti");
			System.out.println("7. Termina programma");
			int query = InputDati.leggiIntero(">");
			switch (query) {
				case 1: {
					asl.addMedico(generateMedico());
					break;
				}
				case 2: {
					asl.medicoFromID(InputDati.leggiIntero("Inserisci id del medico")).addPaziente(generatePaziente());
					break;
				}
				case 3: {
					System.out.println("Quale metodo di ricerca preferisci?");
					System.out.println("1. Ricerca per id");
					System.out.println("2. Ricerca per giorno di riposo");
					int s =InputDati.leggiIntero(">");
					switch (s) {
						case 1: {
							try {
							System.out.println(asl.medicoFromID(InputDati.leggiIntero("Inserisci id: ")).toString());
							} catch (NullPointerException e) {asl.getMedici().remove(null); }
							break;
						}
						case 2: {
							try {
								Settimana day = Settimana.valueOf(InputDati.leggiStringaNonVuota("Inserisci giorno di riposo interessato: ").toUpperCase());
								for (Medico med : asl.medicoFromGiorno(day)) {
									System.out.println(med.toString());
								}
							} catch (Exception e) {System.out.println("Errore nella ricerca");}
							break;
						}
						default: {
							System.out.println("Comando non trovato");
							break;
						}
					}
					break;
				}
				case 4: {
					try {
						System.out.println(asl.pazienteFromID(InputDati.leggiIntero("Inserisci codice tessera sanitaria paziente: ")).toString());
					} catch (NullPointerException e) {asl.getTotalePazienti().remove(null);}
					break;
				}
				case 5: {
					try {
						Paziente pazScambiato = asl.pazienteFromID(InputDati.leggiIntero("Inserisci codice tessera sanitaria del paziente da scambiare: "));
						Medico target = asl.medicoFromID(InputDati.leggiIntero("Inserisci id del nuovo medico: "));
						asl.substitute(pazScambiato, target);
					} catch (Exception e) {System.out.println("Qualcosa e' andato storto nel processo"); asl.getMedici().remove(null); asl.getTotalePazienti().remove(null);}
					break;
				}
				case 6: {
					try {
						ScriviFile.writeMedics(asl);
					} catch (Exception e) {System.out.println("Salvataggio non riuscito");}
					System.out.println("Salvataggio riuscito!");
					break;
				}
				case 7: {
					loop = !InputDati.yesOrNo("Vuoi davvero terminare il programma(i dati non salvati andranno persi)?");
					break;
				}
				default: {
					System.out.println("Comando non trovato");
					break;
				}
			}
		} while(loop);
	}

}
