package model;

public class Forward extends Player{

	private int kicksToGoal;

	public Forward(String name, String id, double salary, int yellowCards, int redCards, int goals, int assists,
			int kicksToGoal) {
		super(name, id, salary, yellowCards, redCards, goals, assists);
		this.kicksToGoal = kicksToGoal;
	}

	public int getKicksToGoal() {
		return kicksToGoal;
	}

	public void setKicksToGoal(int kicksToGoal) {
		this.kicksToGoal = kicksToGoal;
	}
}
