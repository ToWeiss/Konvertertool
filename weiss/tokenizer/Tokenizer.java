package weiss.tokenizer;

public class Tokenizer {
	private String text;
	private int pos;
	
	private boolean prevWasEmpty = false;
	
	public Tokenizer(String text) {
		this.text = text;
	}
	
	public Token getToken() {
		TokenTypeEnum type = TokenTypeEnum.UNKOWN;
		Token current = new Token(type, "");
		
		String value = "";
		while(this.hasToken()) {
			char curChar = this.text.charAt(pos);
			
			if(curChar == ';') {
				if(value.equals("")) {					
					value = ";";
					pos++;
				}
				break;
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
