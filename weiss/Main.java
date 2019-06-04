package weiss;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import weiss.csvp.CSVPColumn;
import weiss.csvp.CSVPMeta;
import weiss.functions.F_Filtern;
import weiss.functions.F_Splitten;
import weiss.parser.Parser;
import weiss.tokenizer.Token;
import weiss.tokenizer.Tokenizer;

/**
 * Main-Klasse. Kümmert sich um die Darstellung (CLI), um die Ausführung der richtigen Funktionen
 * Einlesen der Dateien und ausgeben von Informationen
 * @author tweiss
 * @version 2019-06-04
 */
public class Main {
	public static CSVPMeta meta;
	public static Parser parser;
	public static String filename;
	
	public static void main (String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		String input, errorMsg = "", successMsg = "";
		boolean repeat = true;
		
		System.out.println("--------------------------------------------------------");
		System.out.println("----------------- CSVP - Konvertertool -----------------");
		System.out.println("--------------------------------------------------------");

		Main.printMenu();
		
		while(repeat) {
			System.out.print("- Wählen Sie die jewaehlige Zahl aus(h for help): ");
			input = sc.nextLine();
			
			errorMsg = "";
			successMsg = "";
			
			switch(input) {
				case "1":
					System.out.println("- Dateiname: ");
					input = sc.nextLine();
					
					errorMsg = Main.openFile(input);
					filename = input.substring(0,input.indexOf('.'));
					successMsg = "Datei erfolgreich eingelesen.";
					break;
				case "2":
					if(Main.meta != null) {
						System.out.println(Main.meta.toString());
						successMsg = filename + " erfolgreich angezeigt.";
					}else {
						errorMsg = "Öffnen Sie zuerst einen Datei.";
					}
					break;
				case "3":
					if(Main.meta != null) {
						ArrayList<String> temp = new ArrayList<>();
						
						System.out.println("");
						System.out.print("| ");
						for(CSVPColumn curr : Main.meta.getSpalten()) {
							System.out.print(curr.getName() + " | ");
						}
						System.out.println("\n");
						System.out.println("Wählen Sie einen Spaltennamen aus: ");
						input = sc.nextLine();
						
						temp.add(input);
						System.out.println("Geben Sie ein Suchwort ein: ");
						input = sc.nextLine();
						temp.add(input);
						
						errorMsg = Main.dateiFiltern(temp.get(0), Main.filename, temp.get(1));
						successMsg = filename + " erfolgreich nach Spalte " + temp.get(0) + " und dem Suchwort \"" + temp.get(1) + "\" gefiltert";
					}else {
						errorMsg = "Öffnen Sie zuerst eine Datei.";
					}
					break;
				case "4":
					if(Main.meta != null) {
						System.out.println("");
						System.out.print("| ");
						for(CSVPColumn curr : Main.meta.getSpalten()) {
							System.out.print(curr.getName() + " | ");
						}
						System.out.println("\n");
						System.out.println("Wählen Sie einen Spaltennamen aus: ");
						input = sc.nextLine();
						
						errorMsg = Main.dateiSplitten(input);
						successMsg = filename + " erfolgreich nach Spalte " + input + " getrennt.\n";
					}else {
						errorMsg = "Öffnen Sie zuerst einen Datei.";
					}
					break;
				case "5":
				
					break;
				case "6":
					System.out.println("");
					System.out.println("---------------- Konvertertool beendet -----------------");
					return;
				case "h":
					System.out.println("");
					Main.printMenu();
					break;
				default:
					errorMsg = "Ungueltige Eingabe";
			}
			
			if(!errorMsg.equals("")) {
				System.out.println("! " + errorMsg + "\n");
			}else {
				System.out.println("+ " + successMsg + "\n");
			}
		}
		
	
		sc.close();
	}
	
	public static String openFile(String filename) {
		StringBuilder s;
		
		try {
			BufferedReader br = Files.newBufferedReader(Paths.get(filename));
			
			s = new StringBuilder();
			
			while(br.ready()) {
				s.append((char) br.read());
			}
		}catch(IOException ex) {
			return "Leider gab es ein Problem beim Einlesen der Datei";
		}
		
		Tokenizer tokenizer = new Tokenizer(s.toString());
		Parser parser;
		
		ArrayList<Token> tokens = new ArrayList<>();
		
		while(tokenizer.hasToken()) {
			Token curr = tokenizer.getToken();
			tokens.add(curr);
		}
		
		Main.parser = new Parser(tokens);
		
		int syntaxcheck = Main.parser.checkSyntax();
		
		if(syntaxcheck > -1) {
			return "Syntax-Fehler in Zeile " + syntaxcheck;
		}
		
		Main.meta = Main.parser.buildMeta();
		Main.parser.buildData();
		
		return "";
	}
	
	public static String dateiSplitten(String spaltenname) {
		ArrayList<String> params = new ArrayList<>();
		params.add(spaltenname);
		params.add(Main.filename);
 		F_Splitten split = new F_Splitten(params);
		split.start(Main.meta);
		
		if(split.successfull()) {
			return "";
		}else {
			return "Leider gab es ein Problem.";
		}
	}
	
	public static String dateiFiltern(String spaltenname, String filename, String suchkriterium) {
		ArrayList<String> params = new ArrayList<>();
		params.add(spaltenname);
		params.add(filename);
		params.add(suchkriterium);
		
		F_Filtern filter = new F_Filtern(params);
		filter.start(Main.meta);
		
		if(filter.successfull()) {
			return "";
		}else {
			return "Leider gab es ein Problem.";
		}
	}
	
	public static void printMenu() {
		System.out.println("- Datei oeffnen(1)");
		System.out.println("- Datei anzeigen(2)");
		System.out.println("- Datei filtern(3)");
		System.out.println("- Datei splitten(4)");
		System.out.println("- Datei validieren(5)");
		System.out.println("- Programm schließen(6)\n");
	}
}
