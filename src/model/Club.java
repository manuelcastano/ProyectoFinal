package model;

import java.util.Comparator;

public class Club implements Comparator<Club>, Comparable<Club>{

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

	public Technical getFirstTechnicall() {
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

	@Override
	public String toString() {
		return "Club [name=" + name + ", points=" + points + "]";
	}

	public int compareTo(Club e) {
		return name.compareTo(e.getName());
	}

	//Bubble
	public void orderPlayersByGoals() {
		int players = 0;
		Player actual = firstPlayer;
		while(actual != null) {
			Player next = actual.getNext();
			while(next != null) {
				if(actual.compare(actual, next) > 0) {
					if(actual.getPrev() != null) {
						actual.getPrev().setNext(next);
					}
					if(next.getNext() != null) {
						next.getNext().setPrev(actual);
					}
					actual.setNext(next.getNext());
					next.setPrev(actual.getPrev());
					actual.setPrev(next);
					next.setNext(actual);
					next = actual.getNext();
				}
				else {
					actual = actual.getNext();
					next = next.getNext();
				}
			} 
			players++;
			if(players < PlayersNumber()) {
				findThefirstPlayer();
				actual = firstPlayer;
				if(actual.getNext() != null) {
					next = actual.getNext();
				}
			}
			else {
				actual = null;
			}
		}
		findThefirstPlayer();
	}

	//Bubble
	public void orderPlayersByAssists() {
		int players = 0;
		Player actual = firstPlayer;
		while(actual != null) {
			Player next = actual.getNext();
			while(next != null) {
				if(actual.compareTo(next) > 0) {
					if(actual.getPrev() != null) {
						actual.getPrev().setNext(next);
					}
					if(next.getNext() != null) {
						next.getNext().setPrev(actual);
					}
					actual.setNext(next.getNext());
					next.setPrev(actual.getPrev());
					actual.setPrev(next);
					next.setNext(actual);
					next = actual.getNext();
				}
				else {
					actual = actual.getNext();
					next = next.getNext();
				}
			} 
			players++;
			if(players < PlayersNumber()) {
				findThefirstPlayer();
				actual = firstPlayer;
				if(actual.getNext() != null) {
					next = actual.getNext();
				}
			}
			else {
				actual = null;
			}
		}
		findThefirstPlayer();
	}

	public void findThefirstPlayer() {
		Player actual = firstPlayer;
		Player thefirstPlayer = null;
		while(actual != null) {
			thefirstPlayer = actual;
			actual = actual.getPrev();
		}
		firstPlayer = thefirstPlayer;
	}

	public int PlayersNumber() {
		int Players = 0; 
		Player actual = firstPlayer;
		while(actual != null) {
			Players++;
			actual = actual.getNext();
		}
		return Players;
	}
	
	public Technical searchTechnicalByName(String nameTechnical) {
		if(firstTechnical != null) {
			return firstTechnical.searchTechnicalByName(nameTechnical);
		}
		else {
			return null;
		}
	}
	
	public String searchTechnicalsByPosition(String position) {
		if(firstTechnical != null) {
			return firstTechnical.searchTechnicalsByPosition(position);
		}
		else {
			return null;
		}
	}
}
