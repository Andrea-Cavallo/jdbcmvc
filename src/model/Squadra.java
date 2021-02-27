package model;

public class Squadra {
	
	
	private int id;
	private String nome;
	private String citta;
	private String nomeStadio;
	private Integer capienzaStadio;

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	public String getNomeStadio() {
		return nomeStadio;
	}
	public void setNomeStadio(String nomeStadio) {
		this.nomeStadio = nomeStadio;
	}
	public Integer getCapienzaStadio() {
		return capienzaStadio;
	}
	public void setCapienzaStadio(Integer capienzaStadio) {
		this.capienzaStadio = capienzaStadio;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + capienzaStadio;
		result = prime * result + ((citta == null) ? 0 : citta.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((nomeStadio == null) ? 0 : nomeStadio.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Squadra other = (Squadra) obj;
		if (capienzaStadio != other.capienzaStadio)
			return false;
		if (citta == null) {
			if (other.citta != null)
				return false;
		} else if (!citta.equals(other.citta))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (nomeStadio == null) {
			if (other.nomeStadio != null)
				return false;
		} else if (!nomeStadio.equals(other.nomeStadio))
			return false;
		return true;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() { // attributo,attributo,
		return   nome + ", " + citta + ", " + nomeStadio + ", "
				+ capienzaStadio ;
	}
		public Squadra() {
		}

		public Squadra(String nome, String citta, String nomeStadio, int capienzaStadio) {
			this.nome = nome;
			this.citta = citta;
			this.nomeStadio = nomeStadio;
			this.capienzaStadio = capienzaStadio;
		}
	

}
