package model;

public class Recovery extends MidField{

	private int recoveries;

	public Recovery(String name, String id, double salary, int yellowCards, int redCards, int goals, int assists,
			int passes, int recoveries) {
		super(name, id, salary, yellowCards, redCards, goals, assists, passes);
		this.recoveries = recoveries;
	}

	public int getRecoveries() {
		return recoveries;
	}

	public void setRecoveries(int recoveries) {
		this.recoveries = recoveries;
	}
	
	
	
	
}
