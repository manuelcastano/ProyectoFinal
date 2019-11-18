package model;

public class EliminateException extends Exception{
	
	public EliminateException() {
		super("The element to eliminate doesn't exists");
	}
}
