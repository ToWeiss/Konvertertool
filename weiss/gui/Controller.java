package weiss.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import weiss.parser.Parser;

public class Controller implements ActionListener{
	private Panel panel;
	private Frame frame;
	private Parser parser;
	
	public Controller() {
		this.panel = new Panel();
		this.frame = new Frame();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main (String[] args) {
		
	}
}
