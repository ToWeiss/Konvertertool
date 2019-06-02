package weiss;

import weiss.tokenizer.Token;
import weiss.tokenizer.Tokenizer;

public class Main {
	public static void main (String[] args) {
		String s = "Name;Klasse;Note\nTEXT(2,50);TEXT(2,7);GANZZAHL(1,5)\n\"Tobias Weiss\";;2";
		Tokenizer tn = new Tokenizer(s);
		
		while(tn.hasToken()) {
			Token curr = tn.getToken();
			System.out.println(curr.getValue() + " : " + curr.getTokentype());
		}
	}
}
