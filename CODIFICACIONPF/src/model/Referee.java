package model;

public class Referee extends Person{
	
	
	private int yellowCards;
	private int redCards;
	private int fouls;
	
	//List
	private Referee next;
	private Referee prev;

	
	public Referee(String name, String id, double salary, int yellowCards, int redCards, int fouls) {
		super(name, id, salary);
		this.yellowCards = yellowCards;
		this.redCards = redCards;
		this.fouls = fouls;
		
	}




	public int getYellowCards() {
		return yellowCards;
	}




	public void setYellowCards(int yellowCards) {
		this.yellowCards = yellowCards;
	}




	public int getRedCards() {
		return redCards;
	}




	public void setRedCards(int redCards) {
		this.redCards = redCards;
	}




	public int getFouls() {
		return fouls;
	}




	public void setFouls(int fouls) {
		this.fouls = fouls;
	}




	public Referee getNext() {
		return next;
	}




	public void setNext(Referee next) {
		this.next = next;
	}




	public Referee getPrev() {
		return prev;
	}




	public void setPrev(Referee prev) {
		this.prev = prev;
	}
	
	
	
	
	

}
