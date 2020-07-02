package it.unibs.ing.fp.aziendasanitaria;

public class Main {

	public static void main(String[] args) {
		Azienda asl = LeggiFile.getAzienda();
		ScriviFile.initializeWriter();
		Util.menu(asl);
	}

}
