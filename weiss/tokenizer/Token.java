package weiss.tokenizer;

/**
 * Stellt einen Token dar. Token-Typen siehe TokenTypeEnum
 * @author tweiss
 * @version 2019-06-04s
 */
public class Token {
	private TokenTypeEnum tokentype;
	private String value;
	
	public Token(TokenTypeEnum tokentype, String value) {
		this.tokentype = tokentype;
		this.setValue(value);;
	}
	
	public boolean isValidToken() {
		return !(this.tokentype == TokenTypeEnum.UNKOWN);
	}
	
	public TokenTypeEnum getTokentype() {
		return tokentype;
	}

	public void setTokentype(TokenTypeEnum tokentype) {
		this.tokentype = tokentype;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		if(value.equals("\n")) {
			this.value = "";
		}else {
			this.value = value;
		}
	}
	
	public static boolean isNumber(String exp) {
		try {
			Integer.parseInt(exp);
			return true;
		}catch(NumberFormatException ex) {
			return false;
		}
	}
	
	public static boolean isDeclaration(String exp) {
		if(exp.equals("")) {
			return false;
		}
		if(Token.isNumber(exp)) {
			return false;
		}
		if(exp.contains("\"")) {
			return false;
		}
		if(exp.contains(";")) {
			return false;
		}
		
		return true;
	}
	
	public static boolean isOperator(String exp) {
		if(!exp.equals(";")) {
			return false;
		}
		return true;
	}
	
	public static boolean isText(String exp) {
		if(!exp.contains("\"")) {
			return false;
		}
		return true;
	}
	
	public static boolean isNewline(String exp) {
		if(exp.equals("\n")) {
			return true;
		}
		return false;
	}
}
