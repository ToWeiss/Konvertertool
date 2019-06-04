package weiss.functions;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import weiss.csvp.CSVPColumn;
import weiss.csvp.CSVPMeta;
import weiss.csvp.CSVPObject;

/**
 * Implementiert die Funktion "Splitten nach Spalte"
 * Nimmt eine CSVP-Datei und spaltet diese, nach einer bestimmten Spalte. Unterschiedliche Werte in 
 * dieser Spalte werden in eine eigene Datei gespeichert
 * @author tweiss
 */
public class F_Splitten extends FunktionAbstract{
	/**
	 * Konstruktor
	 * @param parameters:
	 * 	expected:
	 * 		Spaltenname [0]
	 * 		Dateiname [1]
	 */
	public F_Splitten(ArrayList<String> parameters) {
		super(parameters);
	}

	@Override
	public void start(CSVPMeta meta) {
		if(this.getParameters().size() != 2) {
			this.setError(true);
			return;
		}
		
		String filename = this.getParameters().get(1);
		String searchedName = this.getParameters().get(0);
		int searchedColumnPos = -1;
		CSVPColumn currColumn;
		
		for(int i = 0; i < meta.getZeilenanzahl(); i++) {
			currColumn = meta.getSpalten().get(i);
			if(currColumn.getName().equals(searchedName)) {
				searchedColumnPos = i;
			}
		}
		
		if(searchedColumnPos == -1) {
			this.setError(true);
			return;
		}
		
		HashMap<String, ArrayList<Integer>> map = new HashMap<>();
		
		for(CSVPObject currObj : meta.getSpalten().get(searchedColumnPos).getData()) {
			if(map.containsKey(currObj.getValue())) {
				map.get(currObj.getValue()).add(currObj.getRownumber());
			}else {
				map.put(currObj.getValue(), new ArrayList<Integer>());
				map.get(currObj.getValue()).add(currObj.getRownumber());
			}
		}
		
		Set<Map.Entry<String, ArrayList<Integer>>> entryset = map.entrySet();
		Iterator<Map.Entry<String, ArrayList<Integer>>> it = entryset.iterator();
		
		Map.Entry<String, ArrayList<Integer>> currList;
		Path file;
		BufferedWriter bwriter;
		
		while(it.hasNext()) {
			currList = it.next();
			
			try {
				file = Files.createFile(Paths.get(filename + "_" + currList.getKey().replaceAll("\"", "") + ".csvp"));
				
				bwriter = Files.newBufferedWriter(file);
				
				//spaltennamen
				for(int i = 0; i < meta.getSpaltenanzahl(); i++) {
					currColumn = meta.getSpalten().get(i);
					if(!currColumn.getName().equals(searchedName)) {
						for(int j = 0; j < currColumn.getName().length(); j++) {
							bwriter.append(currColumn.getName().charAt(j));
						}
						if(i != meta.getSpaltenanzahl() - 1) {
							bwriter.append(';');
						}
					}

				}
				bwriter.append('\n');
				
				for(int i = 0; i < meta.getSpaltenanzahl(); i++) {
					currColumn = meta.getSpalten().get(i);
					if(!currColumn.getName().equals(searchedName)) {
						for(int j = 0; j < currColumn.getDatatype().name().length(); j++) {
							bwriter.append(currColumn.getDatatype().name().charAt(j));
						}
						bwriter.append('(');
						bwriter.append(currColumn.getDatatype().getMin()+"");
						bwriter.append(',');
						bwriter.append(currColumn.getDatatype().getMax()+"");
						bwriter.append(')');
						
						if(i != meta.getSpaltenanzahl() - 1) {
							bwriter.append(';');
						}
					}
				}
				bwriter.append('\n');
				
				for(int i = 0; i < currList.getValue().size(); i++) {
					for(int j = 0; j < meta.getSpaltenanzahl(); j++) {
						currColumn = meta.getSpalten().get(j);
						
						if(!currColumn.getName().equals(searchedName)) {
							for(CSVPObject currObj : currColumn.getData()) {
								if(currObj.getRownumber() == currList.getValue().get(i)) {
									for(int u = 0; u < currObj.getValue().length(); u++) {
										bwriter.append(currObj.getValue().charAt(u));
									}
									if(j != meta.getSpaltenanzahl()-1) {
										bwriter.append(';');
									}
								}
							}
						}
					}
					bwriter.append('\n');
				}
				
				bwriter.close();
			} catch (IOException e) {
				this.setError(true);
				return;
			}
		}
	}
}
