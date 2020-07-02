package it.unibs.ing.fp.aziendasanitaria;
import java.io.FileInputStream;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

public class LeggiFile {
	static String filename = "/home/alessandro/git/Azienda_Sanitaria/AziendaSanitaria/src/Medici.xml";
	static XMLStreamReader xmlr = null;
	private static Azienda asl = new Azienda();
	
	public static void initializeReader() {
		try {
		xmlr = XMLInputFactory.newInstance().createXMLStreamReader(new FileInputStream(filename));
		} catch (Exception e) {System.out.println("Errore nell'inizializzazione del file");}
	}
	
	public static void extractMedics() {
		try {
			int id = 0;
			while (xmlr.hasNext()) {
				switch (xmlr.getEventType()) {
				case XMLStreamConstants.START_DOCUMENT: 
					 break;
				case XMLStreamConstants.START_ELEMENT:
					switch(xmlr.getLocalName()) {
						case "medico":
							String nomeMed = xmlr.getAttributeValue(0);
							String cognomeMed = xmlr.getAttributeValue(1);
							int idMed = Integer.parseInt(xmlr.getAttributeValue(2));
							Settimana giorno = Settimana.valueOf(xmlr.getAttributeValue(3));
							asl.addMedico(new Medico(nomeMed,cognomeMed,giorno));
							id = idMed;
							break;
						case "paziente":
							String nomePaz = xmlr.getAttributeValue(0);
							String cognomePaz = xmlr.getAttributeValue(1);
							int idPaz = Integer.parseInt(xmlr.getAttributeValue(2));
							Paziente p = new Paziente(nomePaz,cognomePaz,idPaz);
							asl.medicoFromID(id).addPaziente(p);
							asl.addTotalePazienti(p);
							break;
					}
					break;
				 case XMLStreamConstants.END_ELEMENT:
					 break;
				 case XMLStreamConstants.COMMENT:
					 break; 
				 case XMLStreamConstants.CHARACTERS:
					 break;
				}
				xmlr.next();
			}
		} catch (Exception e) {System.out.println("Lettura terminata inaspettatamente");;}
	}
	
	public static Azienda getAzienda() {
		read();
		return asl;
	}
	
	public static void read() {
		initializeReader();
		extractMedics();
	}
}
