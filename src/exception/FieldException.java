package exception;

public class FieldException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String field;

	public String getField() {
		return field;
	}

	public FieldException(String field) {
		this.field = field;
	}



	

}
