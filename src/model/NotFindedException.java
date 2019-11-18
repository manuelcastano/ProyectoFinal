package model;

public class NotFindedException extends Exception{

	public NotFindedException() {
		super("The searched element was not found");
	}
}
