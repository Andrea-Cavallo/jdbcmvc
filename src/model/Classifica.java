package model;

public class Classifica {


	private int id ;
	private int id_campionato;
	private int id_squadra;
	private int posizione;
	private int punti;
	private int gol_fatti;
	private int gol_subiti;
	
	

	public int getId_squadra() {
		return id_squadra;
	}
	public void setId_squadra(int id_squadra) {
		this.id_squadra = id_squadra;
	}
	public int getPosizione() {
		return posizione;
	}
	public void setPosizione(int posizione) {
		this.posizione = posizione;
	}
	public int getPunti() {
		return punti;
	}
	public void setPunti(int punti) {
		this.punti = punti;
	}
	public int getGol_fatti() {
		return gol_fatti;
	}
	public void setGol_fatti(int gol_fatti) {
		this.gol_fatti = gol_fatti;
	}
	public int getGol_subiti() {
		return gol_subiti;
	}
	public void setGol_subiti(int gol_subiti) {
		this.gol_subiti = gol_subiti;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_campionato() {
		return id_campionato;
	}
	public void setId_campionato(int id_campionato) {

		this.id_campionato = id_campionato;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + gol_fatti;
		result = prime * result + gol_subiti;
		result = prime * result + id;
		result = prime * result + id_campionato;
		result = prime * result + id_squadra;
		result = prime * result + posizione;
		result = prime * result + punti;
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
		Classifica other = (Classifica) obj;
		if (gol_fatti != other.gol_fatti)
			return false;
		if (gol_subiti != other.gol_subiti)
			return false;
		if (id != other.id)
			return false;
		if (id_campionato != other.id_campionato)
			return false;
		if (id_squadra != other.id_squadra)
			return false;
		if (posizione != other.posizione)
			return false;
		if (punti != other.punti)
			return false;
		return true;
	}

	public Classifica(){}
	public Classifica(int id, int id_campionato, int id_squadra, int posizione, int punti, int gol_fatti,
			int gol_subiti) {
		super();
		this.id = id;
		this.id_campionato = id_campionato;
		this.id_squadra = id_squadra;
		this.posizione = posizione;
		this.punti = punti;
		this.gol_fatti = gol_fatti;
		this.gol_subiti = gol_subiti;
	}
	@Override
	public String toString() {
		return "Classifica [id=" + id + ", id_campionato=" + id_campionato + ", id_squadra=" + id_squadra
				+ ", posizione=" + posizione + ", punti=" + punti + ", gol_fatti=" + gol_fatti + ", gol_subiti="
				+ gol_subiti + "]";
	} 
	
	
	

	

	
	
	
}
