package model;

public class Campionato {

	private int id;
	private int anno;
	private int id_serie;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAnno() {
		return anno;
	}
	public void setAnno(int anno) {
		this.anno = anno;
	}
	public int getId_serie() {
		return id_serie;
	}
	public void setId_serie(int id_serie) {
		this.id_serie = id_serie;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + anno;
		result = prime * result + id;
		result = prime * result + id_serie;
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
		Campionato other = (Campionato) obj;
		if (anno != other.anno)
			return false;
		if (id != other.id)
			return false;
		if (id_serie != other.id_serie)
			return false;
		return true;
	}
	public Campionato() {}
	public Campionato(int id, int anno, int id_serie) {
		super();
		this.id = id;
		this.anno = anno;
		this.id_serie = id_serie;
	}
	@Override
	public String toString() {
		return "Campionato [id=" + id + ", anno=" + anno + ", id_serie=" + id_serie + "]";
	}
	
	
}
