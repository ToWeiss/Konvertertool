package weiss.functions;

import java.util.ArrayList;

import weiss.csvp.CSVPMeta;

/**
 * Abstrakte Klasse zur Definition von Methoden für Funktionen des Programms (Splitten, Filtern...)
 * @author tweiss
 * @version 2019-06-04
 */
abstract class FunktionAbstract {
	private ArrayList<String> parameters;
	private boolean error;
	
	public FunktionAbstract(ArrayList<String> parameters) {
		this.parameters = parameters;
	}
	
	abstract void start(CSVPMeta meta);
	
	public boolean successfull() {
		return !this.isError();
	}
	
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
