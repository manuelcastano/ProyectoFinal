import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import model.Ball;
import model.Club;
import model.ElementExistException;
import model.EliminateException;
import model.League;
import model.NotFindedException;
import model.Referee;
import model.Stadium;

class LeagueTest {

	private League l;
	
	private void setStage() {
		try {
			l = new League();
		} catch (ClassNotFoundException e) {
			fail();
		} catch (IOException e) {
			fail();
		} catch (ElementExistException e) {
			
		}
	}
	
	@Test
	public void testAddClub() {
		setStage();
		Club c = new Club("Barsa", 45);
		Club c1 = new Club("atleti", 45);
		Club c2 = new Club("real", 45);
		Club c3 = new Club("Barsa", 45);
		try {
			l.addClub(c);
			l.addClub(c1);
			l.addClub(c2);
		} catch (IOException e) {
			fail();
		} catch (ElementExistException e) {
			
		}
		try {
			l.addClub(c3);
			fail();
		} catch (FileNotFoundException e) {
			fail();
		} catch (IOException e) {
			fail();
		} catch (ElementExistException e) {
			
		}
	}
	
	@Test
	public void testAddReferee() {
		setStage();
		Referee r = new Referee("Manue", "1545", 46555, 45, 45, 12);
		Referee r1 = new Referee("Maria", "1545", 46555, 45, 45, 12);
		Referee r2 = new Referee("Manue", "1545", 46555, 45, 45, 12);
		try {
			l.addReferee(r);
			l.addReferee(r1);
		} catch (IOException e1) {
			fail();
		} catch (ElementExistException e1) {
			
		}
		try {
			l.addReferee(r2);
			fail();
		} catch (IOException e) {
			fail();
		} catch (ElementExistException e) {
		}
	}
	
	@Test
	public void testEliminateReferee() {
		setStage();
		Referee r = new Referee("Manue", "1545", 46555, 45, 45, 12);
		Referee r1 = new Referee("Maria", "1545", 46555, 45, 45, 12);
		try {
			l.addReferee(r);
			l.addReferee(r1);
		} catch (IOException e1) {
			fail();
		} catch (ElementExistException e1) {
			
		}
		try {
			l.eliminateReferee("Manue");
		} catch (IOException e) {
			fail();
		} catch (EliminateException e) {
			fail();
		}
		assertFalse(l.refereeExist("Manue"));
	}
	
	@Test
	public void testEliminateBall() {
		setStage();
		Ball b = new Ball("Green", "Soccre", "4641541");
		Ball b1 = new Ball("Green", "Soccre", "2555555");
		Ball b2 = new Ball("Green", "Soccre", "687685");
		Ball b3 = new Ball("Green", "Soccre", "2867");
		try {
			l.addBall(b);
			l.addBall(b1);
			l.addBall(b2);
			l.addBall(b3);
		} catch (ElementExistException e) {
			
		} catch (IOException e) {
			fail();
		}
		try {
			l.eliminateBall("4641541");
		} catch (IOException | EliminateException e) {
			fail();
		}
		try {
			l.searchBallById("4641541");
			fail();
		} catch (NotFindedException e) {
			
		}
	}
	
	@Test
	public void testOrderClubsByPoints() {
		setStage();
		for(int i = 0; i < 30; i++) {
			Club c = new Club(Math.random()+"", (int)Math.random());
			try {
				l.addClub(c);
			} catch (FileNotFoundException e) {
				fail();
			} catch (IOException e) {
				fail();
			} catch (ElementExistException e) {
				fail();
			}
		}
		l.orderClubsByPoints();
		for(int i = 0; i < l.getClubs().size()-1; i++) {
			assertTrue(l.getClubs().get(i).getPoints() >= l.getClubs().get(i+1).getPoints());
		}
	}
	
	@Test
	public void testOrderClubsByNames() {
		setStage();
		for(int i = 0; i < 30; i++) {
			Club c = new Club(Math.random()+"", (int)Math.random());
			try {
				l.addClub(c);
			} catch (FileNotFoundException e) {
				fail();
			} catch (IOException e) {
				fail();
			} catch (ElementExistException e) {
				fail();
			}
		}
		l.orderClubsByNames();
		for(int i = 0; i < l.getClubs().size()-1; i++) {
			assertTrue(l.getClubs().get(i).compareTo(l.getClubs().get(i)) <= 0);
		}
	}
	
	@Test
	public void testSearchClubByName() {
		setStage();
		Club c = new Club("Barsa", 45);
		Club c1 = new Club("atleti", 45);
		Club c2 = new Club("real", 45);
		try {
			l.addClub(c);
			l.addClub(c1);
			l.addClub(c2);
		} catch (IOException e) {
			fail();
		} catch (ElementExistException e) {
			
		}
		l.orderClubsByNames();
		try {
			l.searchClubByName("Barsa");
		} catch (NotFindedException e) {
			fail();
		}
		try {
			l.searchClubByName("fsdfdfsfds");
		} catch (NotFindedException e) {
		}
	}
	
	@Test
	public void testSearchStadiumByName() {
		setStage();
		Stadium s = new Stadium("Camp nou", 452, 4580);
		Stadium s1 = new Stadium("San siro", 452, 4580);
		Stadium s2 = new Stadium("Bernabeu", 452, 4580);
		try {
			l.addStadium(s);
			l.addStadium(s1);
			l.addStadium(s2);
		} catch (IOException e) {
			fail();
		} catch (ElementExistException e) {
			
		}
		l.orderStadiumsByName();
		try {
			l.searchStadiumByName("San siro");
		} catch (NotFindedException e) {
			fail();
		}
		try {
			l.searchStadiumByName("dsffdfds");
		} catch (NotFindedException e) {
			
		}
	}
	
	@Test
	public void testSearchBallByColor() {
		setStage();
		Ball b = new Ball("Green", "Soccre", "4641541");
		Ball b1 = new Ball("Black", "Soccre", "2555555");
		Ball b2 = new Ball("Violet", "Soccre", "687685");
		Ball b3 = new Ball("White", "Soccre", "2867");
		try {
			l.addBall(b);
			l.addBall(b1);
			l.addBall(b2);
			l.addBall(b3);
		} catch (ElementExistException e) {
			
		} catch (IOException e) {
			fail();
		}
		try {
			l.searchBallByColor("Green");
		} catch (NotFindedException e) {
			fail();
		}
		try {
			l.searchBallByColor("Violet");
		} catch (NotFindedException e) {
			fail();
		}
		try {
			l.searchBallByColor("fdsdfd");
		} catch (NotFindedException e) {
		}
	}
}
