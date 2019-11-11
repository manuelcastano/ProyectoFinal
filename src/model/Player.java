package model;

import java.util.Comparator;

public class Player extends Person implements Comparator<Player>, Comparable<Player>{
	
	
	private int yellowCards;
	private int redCards;
	private int goals;
	private int assists;
	
	//List
	private Player next;
	private Player prev;
	
	//Constructor
	public Player(String name, String id, double salary, int yellowCards, int redCards, int goals, int assists) {
		super(name, id, salary);
		this.yellowCards = yellowCards;
		this.redCards = redCards;
		this.goals = goals;
		this.assists = assists;
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

	public int getGoals() {
		return goals;
	}

	public void setGoals(int goals) {
		this.goals = goals;
	}

	public int getAssists() {
		return assists;
	}

	public void setAssists(int assists) {
		this.assists = assists;
	}

	public Player getNext() {
		return next;
	}

	public void setNext(Player next) {
		this.next = next;
	}

	public Player getPrev() {
		return prev;
	}

	public void setPrev(Player prev) {
		this.prev = prev;
	}

	@Override
	public int compare(Player p1, Player p2) {
		return p1.goals-p2.goals;
	}

	@Override
	public int compareTo(Player p) {
		return assists-p.assists;
	}
}
