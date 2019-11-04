package model;

public class ElementExist extends Exception{
	
	public ElementExist() {
		super("The element to add already exist");
	}
}
