package model;

import java.io.Serializable;
import java.util.Comparator;

public class Club implements Comparator<Club>, Comparable<Club>, Serializable{

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
	
	public Technical searchTechnicalByName(String nameTechnical) throws NotFindedException {
		if(firstTechnical != null) {
			Technical t = firstTechnical.searchTechnicalByName(nameTechnical);
			if(t == null) {
				throw new NotFindedException();
			}
			else {
				return t;
			}
		}
		else {
			return null;
		}
	}
	
	public String searchTechnicalsByPosition(String position) throws NotFindedException {
		if(firstTechnical != null) {
			String msg = firstTechnical.searchTechnicalsByPosition(position);
			if(msg.equals("")) {
				throw new NotFindedException();
			}
			else {
				return msg;
			}
		}
		else {
			return null;
		}
	}
	
	public void addPlayer(Player newPlayer) throws ElementExistException {
		if (firstPlayer == null) {
			firstPlayer = newPlayer;
		}
		else {
			if(!playerExist(newPlayer.getId())) {
				newPlayer.setNext(firstPlayer);
				firstPlayer.setPrev(newPlayer);
				firstPlayer = newPlayer;
			}
			else {
				throw new ElementExistException();
			}
		}
	}
	
	public boolean playerExist(String idPlayer) {
		Player actual = firstPlayer;
		boolean finded = false;
		while(actual != null && !finded) {
			if(actual.getId().equals(idPlayer)) {
				finded = true;
			}
			actual = actual.getNext();
		}
		return finded;
	}
	
	public void updateGoalsPlayer(String namePlayer ,int numberGoals) throws NotFindedException {
		Player actual = firstPlayer;
		boolean finded = false;
		while (actual !=null && !finded) {
			if (actual.getName().equals(namePlayer)) {
				actual.setGoals(numberGoals);
				finded = true;
			}
			actual = actual.getNext();
		}
		if(!finded) {
			throw new NotFindedException();
		}
	}
	
	public boolean eliminatePlayer(String namePlayer) throws EliminateException {
		boolean deleted = false;
		Player actual = firstPlayer;
		if(firstPlayer != null && firstPlayer.getName().equals(namePlayer)) {
			firstPlayer = firstPlayer.getNext();
			if(firstPlayer != null) {
				firstPlayer.setPrev(null);
			}
			deleted = true;
		}
		else {
			while(actual != null && !deleted) {
				if(actual.getName().equals(namePlayer)) {
					actual = actual.getPrev();
					actual.setNext(actual.getNext().getNext());
					if(actual.getNext() != null) {
						actual.getNext().setPrev(actual);
					}
					deleted = true;
				}
				actual = actual.getNext();
			}
		}
		if(!deleted) {
			throw new EliminateException();
		}
		return deleted;
	}
	
	public void addTechnical(Technical newTechnical) throws ElementExistException {
		if (firstTechnical == null) {
			firstTechnical = newTechnical;
		}
		else {
			firstTechnical.addTechnical(newTechnical);
		}
	}
	
	public void updateWonGames(String idTechnical, int numberWonGames) {
		if(firstTechnical != null) {
			firstTechnical.updateWonGames(idTechnical, numberWonGames);
		}
	}	
}