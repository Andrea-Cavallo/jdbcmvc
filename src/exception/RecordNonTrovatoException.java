package exception;

public class RecordNonTrovatoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private String descrizione;


	public String getDescrizione() {
		return descrizione;
	}


	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


	public RecordNonTrovatoException(String descrizione) {
		super();
		this.descrizione = descrizione;
	}
	

	
	
}
