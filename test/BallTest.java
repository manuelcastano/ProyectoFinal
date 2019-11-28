import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Ball;
import model.ElementExistException;
import model.EliminateException;
import model.NotFindedException;
import model.Technical;

class BallTest {

	private Ball b;
	
	private void setStage() {
		b = new Ball("Green", "Soccer", "6465464");
	}
	
	@Test
	public void testAddBall() {
		setStage();
		Ball b1 = new Ball("Black", "Basquet", "464556");
		Ball b2 = new Ball("Black", "Basquet", "1516561");
		Ball b3 = new Ball("Black", "Basquet", "784156");
		Ball b4 = new Ball("Black", "Basquet", "6465464");
		try {
			b.addBall(b1);
		} catch (ElementExistException e) {
			fail();
		}
		try {
			b.addBall(b2);
			assertTrue(b1.getLeft().equals(b2));
		} catch (ElementExistException e) {
			fail();
		}
		try {
			b.addBall(b3);
			assertTrue(b.getRight().equals(b3));
		} catch (ElementExistException e) {
			fail();
		}
		try {
			b.addBall(b4);
			fail();
		} catch (ElementExistException e) {
			
		}
	}
	
	@Test
	public void testEliminateTechnical() {
		setStage();
		Ball b1 = new Ball("Black", "Basquet", "464556");
		Ball b2 = new Ball("Black", "Basquet", "1516561");
		Ball b3 = new Ball("Black", "Basquet", "784156");
		Ball b4 = new Ball("Black", "Basquet", "254524");
		try {
			b.addBall(b1);
			b.addBall(b2);
			b.addBall(b3);
			b.addBall(b4);
		} catch (ElementExistException e) {
			fail();
		}
		try {
			b = b.eliminateBall("1516561");
		} catch (EliminateException e) {
			fail();
		}
		assertTrue(!b1.getLeft().equals(b2));
	}
	
	@Test
	public void testSearchBallById() {
		setStage();
		Ball b1 = new Ball("Black", "Basquet", "464556");
		Ball b2 = new Ball("Black", "Basquet", "1516561");
		Ball b3 = new Ball("Black", "Basquet", "784156");
		Ball b4 = new Ball("fdfds", "fdfdsfds", "254524");
		try {
			b.addBall(b1);
			b.addBall(b2);
			b.addBall(b3);
			b.addBall(b4);
		} catch (ElementExistException e) {
			fail();
		}
		Ball b8 = null;
		try {
			b8 = b.searchBallById("254524");
		} catch (NotFindedException e) {
			fail();
		}
		assertTrue(b8.getType().equals(b4.getType()));
	}
	
	@Test
	public void searchBallByColor() {
		setStage();
		Ball b1 = new Ball("Black", "Basquet", "464556");
		Ball b2 = new Ball("Violet", "Basquet", "1516561");
		Ball b3 = new Ball("Black", "Basquet", "784156");
		Ball b4 = new Ball("fdfds", "fdfdsfds", "254524");
		try {
			b.addBall(b1);
			b.addBall(b2);
			b.addBall(b3);
			b.addBall(b4);
		} catch (ElementExistException e) {
			fail();
		}
		Ball b8 = b.searchBallByColor("Violet");
		if(b8 == null) {
			fail();
		}
		assertTrue(b8.getType().equals(b2.getType()));
	}
}
