package weiss.parser;

public class SyntaxErrorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5092019998204102194L;
	
	public SyntaxErrorException() {
		super("Syntax-Error: Datei beeinhaltet nicht korrekte Strukturen");
	}
	
	public SyntaxErrorException(String s) {
		super(s);
	}
}
