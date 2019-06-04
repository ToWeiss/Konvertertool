package weiss.functions;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import weiss.csvp.CSVPColumn;
import weiss.csvp.CSVPMeta;
import weiss.csvp.CSVPObject;

/**
 * Implementiert die Funktion "Filtern nach Spalte und einem Suchkriterium"
 * @author tweiss
 * @version 2019-06-04
 */
public class F_Filtern extends FunktionAbstract{
	/**
	 * Konstruktor
	 * @param parameters
	 * 	expected parameters:
	 * 	- spaltenname
	 *  - dateiname
	 *  - suchkriterium
	 */
	public F_Filtern(ArrayList<String> parameters) {
		super(parameters);
	}

	@Override
	public void start(CSVPMeta meta) {
		if(this.getParameters().size() != 3) {
			this.setError(true);
			return;
		}
		
		boolean found = false;
		
		String searchTerm = this.getParameters().get(2);
		String searchedName = this.getParameters().get(0);
		int searchedPos = -1;
		CSVPColumn searchedColumn;
		
		for(int i = 0; i < meta.getSpaltenanzahl(); i++) {
			//wenn ein Spaltennamen mit dem übergebenen ubereinstimmt
			if(meta.getSpalten().get(i).getName().equals(searchedName)) {
				found = true;
				searchedPos = i;
			}
		}
		
		if(!found) {
			this.setError(true);
			return;
		}
		
		searchedColumn = meta.getSpalten().get(searchedPos);
		
		ArrayList<Integer> foundPos = new ArrayList<>();
		ArrayList<CSVPObject> data = searchedColumn.getData();
		
		for(int i = 0; i < data.size(); i++) {
			if(data.get(i).getValue().contains(searchTerm)) {
				foundPos.add(i);
			}
		}
		
		BufferedWriter bw;
		
		try {
			Path file = Files.createFile(Paths.get(this.getParameters().get(1) + "_SORTIERT_" + this.getParameters().get(0)));
			
			bw = Files.newBufferedWriter(file);
			for(int i = 0; i < meta.getSpaltenanzahl(); i++) {
				CSVPColumn curr = meta.getSpalten().get(i);
				for(int u = 0; u < curr.getName().length(); u++) {
					bw.append(curr.getName().charAt(u));
				}
				bw.append(';');
			}
			bw.append('\n');
		}catch(IOException ex) {
			this.setError(true);
			return;
		}
	}
	
	
}
