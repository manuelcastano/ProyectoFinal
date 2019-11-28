import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Coach;
import model.ElementExistException;
import model.EliminateException;
import model.PhysicalTrainer;
import model.Technical;
import model.TechnicalAssistant;

class TechnicalTest {

	private Technical t;
	
	private void setStage() {
		t = new Technical("manuel", "464565", 8946464, 54);
	}
	
	@Test
	public void testIsSheet() {
		setStage();
		assertTrue(t.isSheet());
		Technical t1 = new Technical("Fercho", "46645156458", 645645, 461465);
		try {
			t.addTechnical(t1);
		} catch (ElementExistException e) {
			fail();
		}
		assertFalse(t.isSheet());
	}
	
	@Test
	public void testGetMinor() {
		setStage();
		Technical t1 = new Technical("Fercho", "46645156458", 645645, 461465);
		Technical t2 = new Technical("Maria", "201565", 645645, 461465);
		Technical t3 = new Technical("Jose", "3555645156458", 645645, 461465);
		Technical t4 = new Technical("Carlos", "048046645156458", 645645, 461465);
		try {
			t.addTechnical(t1);
			t.addTechnical(t2);
			t.addTechnical(t3);
			t.addTechnical(t4);
		} catch (ElementExistException e) {
			fail();
		}
		assertTrue(t.getMinor().equals(t4));
	}
	
	@Test
	public void testEliminateTechnical() {
		setStage();
		Technical t1 = new Technical("Fercho", "46645156458", 645645, 461465);
		Technical t2 = new Technical("Maria", "201565", 645645, 461465);
		Technical t3 = new Technical("Jose", "3555645156458", 645645, 461465);
		Technical t4 = new Technical("Carlos", "048046645156458", 645645, 461465);
		try {
			t.addTechnical(t1);
			t.addTechnical(t2);
			t.addTechnical(t3);
			t.addTechnical(t4);
		} catch (ElementExistException e) {
			fail();
		}
		try {
			t = t.eliminateTechnical("Maria");
		} catch (EliminateException e) {
			fail();
		}
		assertTrue(!t.getLeft().equals(t2));
	}
	
	@Test
	public void testSearchTechnicalByName() {
		setStage();
		Technical t1 = new Technical("Fercho", "46645156458", 645645, 461465);
		Technical t2 = new Technical("Maria", "201565", 645645, 461465);
		Technical t3 = new Technical("Jose", "3555645156458", 645645, 461465);
		Technical t4 = new Technical("Carlos", "048046645156458", 645645, 461465);
		try {
			t.addTechnical(t1);
			t.addTechnical(t2);
			t.addTechnical(t3);
			t.addTechnical(t4);
		} catch (ElementExistException e) {
			fail();
		}
		Technical s = t.searchTechnicalByName("Jose");
		assertTrue(t3.getId().equals(s.getId()));
	}
	
	@Test
	public void testSearchTechnicalsByPosition() {
		setStage();
		Technical t1 = new Coach("Fercho", "46645156458", 645645, 461465, 46465456);
		Technical t2 = new TechnicalAssistant("Maria", "201565", 645645, 461465, 5456456);
		Technical t3 = new Coach("Jose", "3555645156458", 645645, 461465, 46464646);
		Technical t4 = new PhysicalTrainer("Carlos", "048046645156458", 645645, 461465, 486468);
		try {
			t.addTechnical(t1);
			t.addTechnical(t2);
			t.addTechnical(t3);
			t.addTechnical(t4);
		} catch (ElementExistException e) {
			fail();
		}
		String s = t.searchTechnicalsByPosition("Coach");
		assertTrue(s.equals(t1.search() + "\n" + t3.search() + "\n"));
	}
	
	@Test
	public void testAddTechnical() {
		setStage();
		Technical t1 = new Coach("Fercho", "46645156458", 645645, 461465, 46465456);
		Technical t2 = new TechnicalAssistant("Fercho", "201565", 645645, 461465, 5456456);
		Technical t3 = new Coach("pose", "3555645156458", 645645, 461465, 46464646);
		try {
			t.addTechnical(t1);
			assertTrue(t.getLeft().equals(t1));
		} catch (ElementExistException e) {
			fail();
		}
		try {
			t.addTechnical(t2);
			fail();
		} catch (ElementExistException e) {
			
		}
		try {
			t.addTechnical(t3);
			assertTrue(t.getRight().equals(t3));
		} catch (ElementExistException e) {
			fail();
		}
	}
	
	@Test
	public void updateWonGames() {
		setStage();
		Technical t1 = new Coach("Fercho", "46645156458", 645645, 461465, 46465456);
		Technical t2 = new TechnicalAssistant("Maria", "201565", 645645, 461465, 5456456);
		Technical t3 = new Coach("Jose", "3555645156458", 645645, 461465, 46464646);
		Technical t4 = new PhysicalTrainer("Carlos", "048046645156458", 645645, 461465, 486468);
		try {
			t.addTechnical(t1);
			t.addTechnical(t2);
			t.addTechnical(t3);
			t.addTechnical(t4);
		} catch (ElementExistException e) {
			fail();
		}
		t.updateWonGames("Jose", 745);
		Coach s = (Coach) t3;
		assertTrue(s.getWonGames() == 745);
	}
	
	@Test
	public void testTaxes() {
		setStage();
		assertTrue(t.taxes() == (t.getSalary() * 0.19));
	}
	
	@Test
	public void testDay() {
		setStage();
		assertTrue(t.day() == 0);
	}
}
