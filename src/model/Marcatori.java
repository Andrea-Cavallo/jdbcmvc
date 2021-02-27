package model;

public class Marcatori {

    private int id;
	private int gol_fatti;
	private int cartellini_gialli;
	private int cartellini_rossi;
	private int id_giocatore;
	private int id_campionato;

	public int getGol_fatti() {
		return gol_fatti;
	}
	public void setGol_fatti(int gol_fatti) {
		this.gol_fatti = gol_fatti;
	}
	public int getCartellini_gialli() {
		return cartellini_gialli;
	}
	public void setCartellini_gialli(int cartellini_gialli) {
		this.cartellini_gialli = cartellini_gialli;
	}
	public int getCartellini_rossi() {
		return cartellini_rossi;
	}
	public void setCartellini_rossi(int cartellini_rossi) {
		this.cartellini_rossi = cartellini_rossi;
	}
	public int getId_giocatore() {
		return id_giocatore;
	}
	public void setId_giocatore(int id_giocatore) {
		this.id_giocatore = id_giocatore;
	}
	public int getId_campionato() {
		return id_campionato;
	}
	public void setId_campionato(int id_campionato) {
		this.id_campionato = id_campionato;
	}
	public Marcatori() {
	}
	public Marcatori(int gol_fatti, int cartellini_gialli, int cartellini_rossi, int id_giocatore, int id_campionato) {
		super();
		this.gol_fatti = gol_fatti;
		this.cartellini_gialli = cartellini_gialli;
		this.cartellini_rossi = cartellini_rossi;
		this.id_giocatore = id_giocatore;
		this.id_campionato = id_campionato;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cartellini_gialli;
		result = prime * result + cartellini_rossi;
		result = prime * result + gol_fatti;
		result = prime * result + id_campionato;
		result = prime * result + id_giocatore;
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
		Marcatori other = (Marcatori) obj;
		if (cartellini_gialli != other.cartellini_gialli)
			return false;
		if (cartellini_rossi != other.cartellini_rossi)
			return false;
		if (gol_fatti != other.gol_fatti)
			return false;
		if (id_campionato != other.id_campionato)
			return false;
		if (id_giocatore != other.id_giocatore)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Marcatori [gol_fatti=" + gol_fatti + ", cartellini_gialli=" + cartellini_gialli + ", cartellini_rossi="
				+ cartellini_rossi + ", id_campionato=" + id_campionato + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
