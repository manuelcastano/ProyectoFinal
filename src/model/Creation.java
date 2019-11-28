package model;

public class Creation extends MidField {
	
	private int createdOccassions;

	public Creation(String name, String id, double salary, int yellowCards, int redCards, int goals, int assists,
			int passes, int createdOccassions) {
		super(name, id, salary, yellowCards, redCards, goals, assists, passes);
		this.createdOccassions = createdOccassions;
	}

	public int getCreatedOccassions() {
		return createdOccassions;
	}

	public void setCreatedOccassions(int createdOccassions) {
		this.createdOccassions = createdOccassions;
	}
}
