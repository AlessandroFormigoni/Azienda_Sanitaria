package it.unibs.ing.fp.aziendasanitaria;

public abstract class Persona {
	private String nome;
	private String cognome;
	private int id;
	
	public Persona(String nome, String cognome) {
		this.nome = nome;
		this.cognome = cognome;
		this.id = generateID();
	}
	
	public Persona(String nome, String cognome, int id) {
		this.nome = nome;
		this.cognome = cognome;
		this.id = id;
	}
	
	abstract int generateID ();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
	

}
