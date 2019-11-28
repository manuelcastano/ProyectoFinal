package model;

public class Coach extends Technical {
	
	private int wonGames;

	public Coach(String name, String id, double salary, int hoursWorked, int wonGames) {
		super(name, id, salary, hoursWorked);
		this.wonGames = wonGames;
	}

	public int getWonGames() {
		return wonGames;
	}

	public void setWonGames(int wonGames) {
		this.wonGames = wonGames;
	}
}
