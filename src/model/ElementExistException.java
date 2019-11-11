package model;

public class ElementExistException extends Exception{
	
	public ElementExistException() {
		super("The element to add already exist");
	}
}
