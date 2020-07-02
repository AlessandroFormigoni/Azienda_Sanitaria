package it.unibs.ing.fp.aziendasanitaria;

import java.io.FileOutputStream;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

public class ScriviFile {
	static String version = "1.0";
	static String encoding = "UTF-8";
	static String filename = "/home/alessandro/git/Azienda_Sanitaria/AziendaSanitaria/src/Medici.xml";
	static XMLOutputFactory xmlof = null;
	static XMLStreamWriter xmlw = null;
	
	public static void initializeWriter() {
		try {
			xmlof = XMLOutputFactory.newInstance();
			xmlw = xmlof.createXMLStreamWriter(new FileOutputStream(filename),encoding);
			xmlw.writeStartDocument(encoding, version);
		} catch (Exception e) {e.printStackTrace();}
	}
	
	public static void writeMedics(Azienda asl) {
		try {
			xmlw.writeStartElement("Medici");
			for (Medico medico : asl.getMedici()) {
			xmlw.writeStartElement("medico");
			xmlw.writeAttribute("nome", medico.getNome());
			xmlw.writeAttribute("cognome", medico.getCognome());
			xmlw.writeAttribute("id", Integer.toString(medico.getId()));
			xmlw.writeAttribute("giorno", medico.getGiornoDiRiposo().toString());
				for (Paziente paziente : medico.getPazienti()) {
					xmlw.writeStartElement("paziente");
					xmlw.writeAttribute("nome", paziente.getNome());
					xmlw.writeAttribute("cognome", paziente.getCognome());
					xmlw.writeAttribute("id", Integer.toString(paziente.getId()));
					xmlw.writeEndElement();
					}
			xmlw.writeEndElement();
			}
			xmlw.writeEndElement();
			xmlw.flush();
			xmlw.close();
		} catch (Exception e) {e.printStackTrace();}
	}
	
	public static void write(Azienda asl) {
		initializeWriter();
		writeMedics(asl);
	}
}
