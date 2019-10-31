package model;

public class GoalKeeper extends Player{
	
	
	private int saved;

	public GoalKeeper(String name, String id, double salary, int yellowCards, int redCards, int goals, int assists,
			int saved) {
		super(name, id, salary, yellowCards, redCards, goals, assists);
		this.saved = saved;
	}

	public int getSaved() {
		return saved;
	}

	public void setSaved(int saved) {
		this.saved = saved;
	}
	
	
	
	

}
