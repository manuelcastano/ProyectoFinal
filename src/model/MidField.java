package model;

public class MidField extends Player {
	
	private int passes;

	public MidField(String name, String id, double salary, int yellowCards, int redCards, int goals, int assists,
			int passes) {
		super(name, id, salary, yellowCards, redCards, goals, assists);
		this.passes = passes;
	}

	public int getPasses() {
		return passes;
	}

	public void setPasses(int passes) {
		this.passes = passes;
	}
	
	
	
	

}
