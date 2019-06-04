package weiss.parser;

import java.util.ArrayList;

import weiss.tokenizer.Token;
import weiss.tokenizer.TokenTypeEnum;

public class Grammarchecker {
	public static int errorLine;
	public static String errorMsg;
	
	public static void checkGrammer(ArrayList<Token> tokens) {
		int sp_anz = 0;
		
		if(tokens.get(0).getTokentype() != TokenTypeEnum.DECLARATION) {
			errorLine = 0;
			errorMsg = "Keine Spaltendeklaration in der ersten Zeile";
			return;
		}
		
		int firstnewline = tokens.indexOf(new Token(TokenTypeEnum.NEWLINE, " "));
		if(firstnewline == -1) {
			errorLine = 0;
			errorMsg = "Keine zweite Zeile: fehlende Spaltendatentypdeklaration";
			return;
		}
		
		for(int i = 1; i < tokens.size(); i++) {
			Token curr = tokens.get(i);
			Token prev = tokens.get(i-1);
			
			if(curr.getTokentype() == TokenTypeEnum.DECLARATION) {
				
			}
		}
	}
}
