package model;

import java.util.Date;

public class Partita {

	private int id;
	private int id_squadracasa;
	private int id_squadrafuoricasa;
	private int id_campionato;
	private Date datapartita;
	private int gol_squadra_casa;
	private int gol_squadra_fuoricasa;
	
	
	public int getId_squadracasa() {
		return id_squadracasa;
	}
	public void setId_squadracasa(int id_squadracasa) {
		this.id_squadracasa = id_squadracasa;
	}
	public int getId_squadrafuoricasa() {
		return id_squadrafuoricasa;
	}
	public void setId_squadrafuoricasa(int id_squadrafuoricasa) {
		this.id_squadrafuoricasa = id_squadrafuoricasa;
	}
	public int getId_campionato() {
		return id_campionato;
	}
	public void setId_campionato(int id_campionato) {
		this.id_campionato = id_campionato;
	}
	public Date getDatapartita() {
		return datapartita;
	}
	public void setDatapartita(Date datapartita) {
		this.datapartita = datapartita;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGol_squadra_casa() {
		return gol_squadra_casa;
	}
	public void setGol_squadra_casa(int gol_squadra_casa) {
		this.gol_squadra_casa = gol_squadra_casa;
	}
	public int getGol_squadra_fuoricasa() {
		return gol_squadra_fuoricasa;
	}
	public void setGol_squadra_fuoricasa(int gol_squadra_fuoricasa) {
		this.gol_squadra_fuoricasa = gol_squadra_fuoricasa;
	}
	
	public Partita() {}
	public Partita(int id, int id_squadracasa, int id_squadrafuoricasa, int id_campionato, Date datapartita,
			int gol_squadra_casa, int gol_squadra_fuoricasa) {
		super();
		this.id = id;
		this.id_squadracasa = id_squadracasa;
		this.id_squadrafuoricasa = id_squadrafuoricasa;
		this.id_campionato = id_campionato;
		this.datapartita = datapartita;
		this.gol_squadra_casa = gol_squadra_casa;
		this.gol_squadra_fuoricasa = gol_squadra_fuoricasa;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((datapartita == null) ? 0 : datapartita.hashCode());
		result = prime * result + gol_squadra_casa;
		result = prime * result + gol_squadra_fuoricasa;
		result = prime * result + id;
		result = prime * result + id_campionato;
		result = prime * result + id_squadracasa;
		result = prime * result + id_squadrafuoricasa;
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
		Partita other = (Partita) obj;
		if (datapartita == null) {
			if (other.datapartita != null)
				return false;
		} else if (!datapartita.equals(other.datapartita))
			return false;
		if (gol_squadra_casa != other.gol_squadra_casa)
			return false;
		if (gol_squadra_fuoricasa != other.gol_squadra_fuoricasa)
			return false;
		if (id != other.id)
			return false;
		if (id_campionato != other.id_campionato)
			return false;
		if (id_squadracasa != other.id_squadracasa)
			return false;
		if (id_squadrafuoricasa != other.id_squadrafuoricasa)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Partita [id=" + id + ", id_squadracasa=" + id_squadracasa + ", id_squadrafuoricasa="
				+ id_squadrafuoricasa + ", id_campionato=" + id_campionato + ", datapartita=" + datapartita
				+ ", gol_squadra_casa=" + gol_squadra_casa + ", gol_squadra_fuoricasa=" + gol_squadra_fuoricasa + "]";
	} 
	
	
	
	
}
