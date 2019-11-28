import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Player;

class PlayerTest {

	private Player p;
	
	private void setStage() {
		p = new Player("fddfs", "fdsfds", 654645, 64546, 65446, 421, 12);
	}
	
	@Test
	public void testTaxes() {
		setStage();
		assertTrue(p.taxes() == 0);
	}
	
	@Test
	public void testDay() {
		setStage();
		assertTrue(p.day() == 7);
	}
	
	@Test
	public void testFine() {
		setStage();
		assertTrue(p.fine() == p.getRedCards() *50000);
	}
}
