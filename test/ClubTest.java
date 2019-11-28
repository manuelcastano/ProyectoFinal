import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Club;
import model.Coach;
import model.ElementExistException;
import model.EliminateException;
import model.NotFindedException;
import model.PhysicalTrainer;
import model.Player;
import model.Technical;
import model.TechnicalAssistant;

class ClubTest {

	private Club c;
	
	private void setStage() {
		c = new Club("Barcelona", 1545);
	}
	
	@Test
	public void testEliminateTechnical() {
		setStage();
		Technical t = new Technical("Manuel", "156443", 416466, 45);
		try {
			c.addTechnical(t);
		} catch (ElementExistException e) {
			fail();
		}
		assertTrue(c.getFirstTechnicall() != null);
		try {
			c.eliminateTechnical("Manuel");
		} catch (EliminateException e) {
			fail();
		}
		assertTrue(c.getFirstTechnicall() == null);
	}
	
	@Test
	public void testOrderPlayersByGoals() {
		setStage();
		for(int i = 0; i < 100; i++) {
			Player p = new Player("Manuel", Math.random() + "", 464646456, 45645, 4646, (int)Math.random() * 100, 44646);
			try {
				c.addPlayer(p);
			} catch (ElementExistException e) {
				fail();
			}
		}
		c.orderPlayersByGoals();
		Player actual = c.getFirstPlayer();
		while(actual != null) {
			if(actual.getNext() != null) {
				assertTrue(actual.getGoals() - actual.getNext().getGoals() >= 0);
			}
			actual = actual.getNext();
		}
	}
	
	@Test
	public void testOrderPlayersByAssists() {
		setStage();
		for(int i = 0; i < 100; i++) {
			Player p = new Player("Manuel", Math.random() + "", 464646456, 45645, 4646, 4654564, (int)Math.random() * 100);
			try {
				c.addPlayer(p);
			} catch (ElementExistException e) {
				fail();
			}
		}
		c.orderPlayersByAssists();
		Player actual = c.getFirstPlayer();
		while(actual != null) {
			if(actual.getNext() != null) {
				assertTrue(actual.getAssists() - actual.getNext().getAssists() >= 0);
			}
			actual = actual.getNext();
		}
	}
	
	@Test
	public void testSearchTechnicalByName() {
		setStage();
		Technical t = new Technical("Manuel", "156443", 416466, 7575);
		Technical t1 = new Technical("Carlos", "156443", 416466, 57757);
		Technical t2 = new Technical("Pablo", "156443", 416466, 687798);
		Technical t3 = new Technical("Jairo", "156443", 416466, 5683);
		try {
			c.addTechnical(t);
			c.addTechnical(t1);
			c.addTechnical(t2);
			c.addTechnical(t3);
		} catch (ElementExistException e) {
			fail();
		}
		try {
			Technical t8 = c.searchTechnicalByName("Jairo");
			assertTrue(t8.getHoursWorked() == t3.getHoursWorked());
		} catch (NotFindedException e) {
			fail();
		}
	}
	
	@Test
	public void testSearchTechnicalsByPosition() {
		setStage();
		Technical t = new Coach("Manuel", "156443", 416466, 7575, 4646);
		Technical t1 = new Coach("Carlos", "156443", 416466, 57757, 466465);
		Technical t2 = new TechnicalAssistant("Pablo", "156443", 416466, 687798, 46464);
		Technical t3 = new PhysicalTrainer("Jairo", "156443", 416466, 5683, 4645456);
		try {
			c.addTechnical(t);
			c.addTechnical(t1);
			c.addTechnical(t2);
			c.addTechnical(t3);
		} catch (ElementExistException e) {
			fail();
		}
		String msg = "";
		try {
			msg = c.searchTechnicalsByPosition("Coach");
		} catch (NotFindedException e) {
			fail();
		}
		assertTrue(msg.equals(t1.search() + "\n" + t.search() + "\n"));
	}
	
	@Test
	public void testAddPlayer() {
		setStage();
		for(int i = 0; i < 100; i++) {
			Player p = new Player("Manuel", Math.random() + "", 464646456, 45645, 4646, 52788, 44646);
			try {
				c.addPlayer(p);
			} catch (ElementExistException e) {
				fail();
			}
			assertTrue(c.getFirstPlayer().equals(p));
		}
	}
	
	@Test
	public void testPlayerExist() {
		setStage();
		Player p = new Player("Manuel", "46556465", 464646456, 45645, 4646, 52788, 44646);
		Player p1 = new Player("Manuel", "574", 464646456, 45645, 4646, 52788, 44646);
		Player p2 = new Player("Manuel", "741", 464646456, 45645, 4646, 52788, 44646);
		Player p3 = new Player("Manuel", "36879643", 464646456, 45645, 4646, 52788, 44646);
		try {
			c.addPlayer(p);
			c.addPlayer(p1);
			c.addPlayer(p2);
			c.addPlayer(p3);
		} catch (ElementExistException e) {
			fail();
		}
		assertFalse(c.playerExist("46515641"));
		assertTrue(c.playerExist("46556465"));
		assertTrue(c.playerExist("574"));
		assertTrue(c.playerExist("741"));
		assertFalse(c.playerExist("051"));
	}
	
	@Test
	public void testUpdateGoalsPlayer() {
		setStage();
		Player p = new Player("Manuel", "46556465", 464646456, 45645, 4646, 52788, 44646);
		Player p1 = new Player("Carlos", "574", 464646456, 45645, 4646, 52788, 44646);
		Player p2 = new Player("Camilo", "741", 464646456, 45645, 4646, 52788, 44646);
		Player p3 = new Player("Manuela", "36879643", 464646456, 45645, 4646, 52788, 44646);
		try {
			c.addPlayer(p);
			c.addPlayer(p1);
			c.addPlayer(p2);
			c.addPlayer(p3);
		} catch (ElementExistException e) {
			fail();
		}
		try {
			c.updateGoalsPlayer("Camilo", 75);
		} catch (NotFindedException e) {
			fail();
		}
		assertTrue(p2.getGoals() == 75);
	}
	
	@Test
	public void testEliminatePlayer() {
		setStage();
		Player p = new Player("Manuel", "46556465", 464646456, 45645, 4646, 52788, 44646);
		Player p1 = new Player("Maria", "574", 464646456, 45645, 4646, 52788, 44646);
		Player p2 = new Player("Alberto", "741", 464646456, 45645, 4646, 52788, 44646);
		Player p3 = new Player("Carlos", "36879643", 464646456, 45645, 4646, 52788, 44646);
		try {
			c.addPlayer(p);
			c.addPlayer(p1);
			c.addPlayer(p2);
			c.addPlayer(p3);
		} catch (ElementExistException e) {
			fail();
		}
		assertTrue(c.playerExist("46556465"));
		assertTrue(c.playerExist("574"));
		try {
			c.eliminatePlayer("Maria");
		} catch (EliminateException e) {
			fail();
		}
		assertFalse(c.playerExist("574"));
		assertTrue(c.playerExist("46556465"));
		assertTrue(c.playerExist("36879643"));
	}
}
