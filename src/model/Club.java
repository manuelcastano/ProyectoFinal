package model;

public class Club {
	
	private String name;
	//Relations
    private Player firstPlayer;
    private Technical firstTechnical;
    
	public Club(String name) {
		this.name = name;
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
	
	
	
	
	
	

}
