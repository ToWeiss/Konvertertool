package weiss.csvp;

public class CSVPObject {
	private String value;
	private CSVPColumn column;
	private int rownumber;
	
	public CSVPObject(String value, int rownumber) {
		this.value = value;
		this.rownumber = rownumber;
	}

	public int compareTo(String arg0) {
		return value.compareTo(arg0);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((column == null) ? 0 : column.hashCode());
		result = prime * result + rownumber;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		CSVPObject other = (CSVPObject) obj;
		if (column == null) {
			if (other.column != null)
				return false;
		} else if (!column.equals(other.column))
			return false;
		if (rownumber != other.rownumber)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public CSVPColumn getColumn() {
		return column;
	}

	public void setColumn(CSVPColumn column) {
		this.column = column;
	}

	public int getRownumber() {
		return rownumber;
	}

	public void setRownumber(int rownumber) {
		this.rownumber = rownumber;
	}
}
