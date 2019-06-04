package weiss.csvp;

import java.util.ArrayList;

/**
 * Stellt eine Spalte in der CSVP-Datei dar. Jede Spalte ist einem CSVPMeta Objekt zugewiesen
 * @author tweiss
 * @version 2019-06-04
 */
public class CSVPColumn {
	private CSVPTypeEnum datatype;
	private ArrayList<CSVPObject> data;
	private String name;
	
	public CSVPColumn(CSVPTypeEnum datatype, String name) {
		this.datatype = datatype;
		this.name = name;
		this.data = new ArrayList<>();
	}
	
	public CSVPColumn(){
		this.datatype = null;
		this.data = new ArrayList<>();
		this.name = "";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((datatype == null) ? 0 : datatype.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CSVPColumn other = (CSVPColumn) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (datatype != other.datatype)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public void addData(CSVPObject object) {
		data.add(object);
	}

	public CSVPTypeEnum getDatatype() {
		return datatype;
	}

	public void setDatatype(CSVPTypeEnum datatype) {
		this.datatype = datatype;
	}

	public ArrayList<CSVPObject> getData() {
		return data;
	}

	public void setData(ArrayList<CSVPObject> data) {
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
