package exception;

public class FormatErrorException extends FieldException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FormatErrorException(String field ) {
		super(field);
	}

}
