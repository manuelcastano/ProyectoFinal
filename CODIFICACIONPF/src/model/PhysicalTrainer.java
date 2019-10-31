package model;

public class PhysicalTrainer extends Technical {

	
	private int injuredPlayers;

	public PhysicalTrainer(String name, String id, double salary, int hoursWorked, int injuredPlayers) {
		super(name, id, salary, hoursWorked);
		this.injuredPlayers = injuredPlayers;
	}

	public int getInjuredPlayers() {
		return injuredPlayers;
	}

	public void setInjuredPlayers(int injuredPlayers) {
		this.injuredPlayers = injuredPlayers;
	}
	
	
	
}
