package weiss;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import weiss.csvp.*;
import weiss.parser.*;
import weiss.tokenizer.*;
import weiss.functions.*;

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
		
		ArrayList<String> params = new ArrayList<>();
		params.add("Klasse");
		params.add("test");
		F_Splitten f = new F_Splitten(params);
		f.start(csvpmeta);
		System.out.println(f.successfull());
	}
}
