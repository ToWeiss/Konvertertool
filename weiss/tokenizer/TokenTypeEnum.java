package weiss.tokenizer;

public enum TokenTypeEnum {
	DECLARATION(0),
	OPERATOR(1),
	NUMBER(2),
	TEXT(3),
	NEWLINE(5),
	UNKOWN(6);
	
	private int value;

	private TokenTypeEnum(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
}
