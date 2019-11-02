package model;

public class Technical extends Person {
	
	//ABB
	private Technical left;
	private Technical right;
	
	
	private int hoursWorked;


	public Technical(String name, String id, double salary, int hoursWorked) {
		super(name, id, salary);
		this.hoursWorked = hoursWorked;
	}


	public Technical getLeft() {
		return left;
	}


	public void setLeft(Technical left) {
		this.left = left;
	}


	public Technical getRight() {
		return right;
	}


	public void setRight(Technical right) {
		this.right = right;
	}


	public int getHoursWorked() {
		return hoursWorked;
	}


	public void setHoursWorked(int hoursWorked) {
		this.hoursWorked = hoursWorked;
	}
	
	

}
