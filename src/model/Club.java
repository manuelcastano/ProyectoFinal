package model;

import java.util.Comparator;

public class Club implements Comparator<Club>{
	
	private String name;
	private int points;
    private Player firstPlayer;
    private Technical firstTechnical;
    
	public Club(String name, int points) {
		this.name = name;
		this.points = points;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Player getFirstPlayer() {
		return firstPlayer;
	}

	public void setFirstPlayer(Player firstPlayer) {
		this.firstPlayer = firstPlayer;
	}

	public Technical getFirstTechnical() {
		return firstTechnical;
	}

	public void setFirstTechnical(Technical firstTechnical) {
		this.firstTechnical = firstTechnical;
	}
	
	public void eliminateTechnical(String nameTechnical) {
		if(firstTechnical != null) {
			firstTechnical = firstTechnical.eliminateTechnical(nameTechnical);
		}
	}

	@Override
	public int compare(Club c, Club c1) {
		return c.points-c1.points;
	}
}
