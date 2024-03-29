package model;

import java.util.Comparator;

public class Player extends Person implements Comparator<Player>, Comparable<Player>, Tax, Holiday, FineRedCards {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

	@Override
	public double taxes() {
		double salaryMin = 828116;
		double tax = 0;
		if (this.getSalary() > salaryMin && this.getSalary() < salaryMin * 2) {
			tax = this.getSalary() * 0.10;
		}
		else if (this.getSalary() >= salaryMin*2) {
			tax = this.getSalary() * 0.20;
		}
		return tax;
	}
	
	//vaciones por mes 
	@Override
	public int day() {
		int dayBymont = 3;
		if (goals == 1) {
			dayBymont = 4;
		}else if (goals == 2) {
			dayBymont = 5;
		}else {
			dayBymont = 7;
		}
		return dayBymont;
	}
	
	public String holidays() {
		return getName() + "\t" + day();
	}
	
	public String tableGoals() {
		return getName() + "\t" + goals;
	}
	
	public String tableAssists() {
		return getName() + "\t" + assists;
	}
	
	public String theTaxes() {
		return getName() + "\t" + taxes();
	}

	@Override
	public int fine() {
		int fine = 0;
		if(redCards <= 10) {
			fine = redCards * 10000;
		}
		else {
			fine = redCards * 50000;
		}
		return fine;
	}
	
	public String theFines() {
		return getName() + "\t" + fine();
	}
}
