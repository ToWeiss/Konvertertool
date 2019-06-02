package weiss;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import weiss.tokenizer.Token;
import weiss.tokenizer.Tokenizer;

public class Main {
	public static void main (String[] args) throws IOException {
		BufferedReader br = Files.newBufferedReader(Paths.get("/home/tobias/java-school/Konvertertool/test.csvp"));
		
		StringBuilder s = new StringBuilder();
		
		while(br.ready()) {
			s.append((char) br.read());
		}
		
		Tokenizer tn = new Tokenizer(s.toString());
		
		while(tn.hasToken()) {
			Token curr = tn.getToken();
			System.out.println(curr.getValue() + " : " + curr.getTokentype());
		}
	}
}
