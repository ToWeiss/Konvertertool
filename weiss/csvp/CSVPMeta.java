package weiss.csvp;

import java.util.ArrayList;

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
