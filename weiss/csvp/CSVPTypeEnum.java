package weiss.csvp;

public enum CSVPTypeEnum {
	TEXT(0,0),
	GANZZAHL(0,0);

	private int min;
	private int max;
	
	private CSVPTypeEnum(int min, int max) {
		this.min = min;
		this.max = max;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
