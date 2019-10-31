package model;

public class Defense extends Player {

	
	private int recoveries;

	public Defense(String name, String id, double salary, int yellowCards, int redCards, int goals, int assists,
			int recoveries) {
		super(name, id, salary, yellowCards, redCards, goals, assists);
		this.recoveries = recoveries;
	}

	
	public int getRecoveries() {
		return recoveries;
	}

	public void setRecoveries(int recoveries) {
		this.recoveries = recoveries;
	}
	
	
	
	
}
