package model;

public class TechnicalAssistant extends Technical {
	
	private int plannedPlays;

	public TechnicalAssistant(String name, String id, double salary, int hoursWorked, int plannedPlays) {
		super(name, id, salary, hoursWorked);
		this.plannedPlays = plannedPlays;
	}

	public int getPlannedPlays() {
		return plannedPlays;
	}

	public void setPlannedPlays(int plannedPlays) {
		this.plannedPlays = plannedPlays;
	}
	
	
	
	

}
