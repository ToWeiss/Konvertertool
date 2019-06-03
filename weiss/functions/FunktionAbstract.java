package weiss.functions;

import java.util.ArrayList;

import weiss.csvp.CSVPMeta;

abstract class FunktionAbstract {
	private ArrayList<String> parameters;
	private boolean error;
	
	public FunktionAbstract(ArrayList<String> parameters) {
		this.parameters = parameters;
	}
	
	abstract void start(CSVPMeta meta);
	abstract boolean successfull();
	
	public ArrayList<String> getParameters() {
		return parameters;
	}

	public void setParameters(ArrayList<String> parameters) {
		this.parameters = parameters;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}
}
