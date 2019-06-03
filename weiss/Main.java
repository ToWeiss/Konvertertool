package weiss;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import weiss.csvp.*;
import weiss.parser.Parser;
import weiss.parser.SyntaxErrorException;
import weiss.tokenizer.Token;
import weiss.tokenizer.Tokenizer;

public class Main {
	public static void main (String[] args) throws IOException {
		BufferedReader br = Files.newBufferedReader(Paths.get("/home/tobias/java-school/Konvertertool/test.csvp"));
		
		StringBuilder s = new StringBuilder();
		
		while(br.ready()) {
			s.append((char) br.read());
		}
		
		Tokenizer tokenizer = new Tokenizer(s.toString());
		Parser parser;
		
		ArrayList<Token> tokens = new ArrayList<>();
		
		while(tokenizer.hasToken()) {
			Token curr = tokenizer.getToken();
			tokens.add(curr);
		}
		
		parser = new Parser(tokens);
		try {
			parser.checkSyntax();
		}catch(SyntaxErrorException see) {
			System.out.println("Syntax-Error");
		}
		
		CSVPMeta csvpmeta = parser.buildMeta();
		parser.buildData();
		
		System.out.println(csvpmeta);
	}
}
