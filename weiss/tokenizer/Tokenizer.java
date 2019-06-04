package weiss.tokenizer;

/**
 * Nimmt einen String als Attribut und teilt diesen in Token ein
 * @author tweiss
 * @version 2019-06-04
 */
public class Tokenizer {
	private String text;
	private int pos;
	
	public Tokenizer(String text) {
		this.text = text;
	}
	
	public Token getToken() {
		TokenTypeEnum type = TokenTypeEnum.UNKOWN;
		Token current = new Token(type, "");
		
		boolean isString = false;
		int startPos = pos;
		
		if(this.text.charAt(pos) == '\"') {
			isString = true;
		}
		
		String value = "";
		while(this.hasToken()) {
			char curChar = this.text.charAt(pos);
			
			if(curChar == ';' && !isString) {
				if(value.equals("")) {					
					value = ";";
					pos++;
				}
				break;
			}
			
			if(startPos != pos && this.text.charAt(pos) == '\"') {
				isString = false;
			}
			
			if(curChar == '\n') {
				if(value.equals("")) {
					value = "\n";
					pos++;
				}
				break;
			}
			
			value += curChar;
			pos++;
		}
		
		if(Token.isNumber(value)) {
			type = TokenTypeEnum.NUMBER;
		}else if(Token.isNewline(value)) {
			type = TokenTypeEnum.NEWLINE;
		}else if(Token.isDeclaration(value)) {
			type = TokenTypeEnum.DECLARATION;
		}else if(Token.isOperator(value)) {
			type = TokenTypeEnum.OPERATOR;
		}else if(Token.isText(value)){
			type = TokenTypeEnum.TEXT;
		}
		
		current.setTokentype(type);
		current.setValue(value);
		
		return current;
	}
	
	public boolean hasToken() {
		return (this.pos < text.length());
	}
}
