package weiss.csvp;

import java.util.ArrayList;

/**
 * Stellt quasi die CSVP-Datei selber dar. Speichert Daten wie Spaltennamen, die Spalten selber ....
 * @author tweiss
 * @version 2019-06-04
 *
 */
public class CSVPMeta {
	private int spaltenanzahl;
	private int zeilenanzahl;
	private ArrayList<CSVPColumn> spalten;
	
	public CSVPMeta(int spaltenanzahl) {
		this.spaltenanzahl = spaltenanzahl;
		this.zeilenanzahl = 0;
		this.spalten = new ArrayList<>();
	}
	

	public CSVPMeta() {
		this.spaltenanzahl = 0;
		this.zeilenanzahl = 0;
		this.spalten = new ArrayList<>();
	}
	
	public boolean addColumn(String columnname, CSVPTypeEnum datatype) {
		CSVPColumn column = new CSVPColumn(datatype, columnname);
		for(CSVPColumn cur : spalten) {
			if(cur.getName() == columnname){
				return false;
			}
		}
		this.spalten.add(column);
		return true;
	}
	
	@Override 
	public String toString() {
		int width = 60;
		int widthColumn = width / spaltenanzahl;
		int limit = 0;
		
		for(int i = 0; i < this.spaltenanzahl; i++) {
			CSVPColumn column = this.spalten.get(i);
			limit = (widthColumn - column.getName().length()) / 2;
			for(int u = 0; u < limit; u++) {
				System.out.print(" ");
			}
			System.out.print(column.getName());
			for(int u = 0; u < limit; u++) {
				System.out.print(" ");
			}
			System.out.print("|");
		}
		
		System.out.println("");
		
		for(int i = 0; i < this.zeilenanzahl; i++) {
			for(int u = 0; u < this.spaltenanzahl; u++) {
				CSVPObject currObj = this.spalten.get(u).getData().get(i);
				limit = (widthColumn - currObj.getValue().length()) / 2;
				for(int j = 0; j < limit; j++) {
					System.out.print(" ");
				}
				System.out.print(currObj.getValue());
				for(int j = 0; j < limit; j++) {
					System.out.print(" ");
				}
				System.out.print("|");
			}
			System.out.println("");
		}
		return "";
	}
	
	public void addRow(ArrayList<String> data) {
		
	}

	public int getSpaltenanzahl() {
		return spaltenanzahl;
	}

	public void setSpaltenanzahl(int spaltenanzahl) {
		this.spaltenanzahl = spaltenanzahl;
	}

	public int getZeilenanzahl() {
		return zeilenanzahl;
	}

	public void setZeilenanzahl(int zeilenanzahl) {
		this.zeilenanzahl = zeilenanzahl;
	}

	public ArrayList<CSVPColumn> getSpalten() {
		return spalten;
	}

	public void setSpalten(ArrayList<CSVPColumn> spalten) {
		this.spalten = spalten;
	}
	
}
