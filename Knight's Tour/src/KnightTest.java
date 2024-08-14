import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class KnightTest {
	

	@Before
	public void setUp() throws Exception {
		
	}
	
	@Test
	public void testKnight() {
		Knight rider = new Knight(new RowColPair(2,3),8);
		assertEquals(8,rider.getBoard().length);
		assertEquals(8,rider.getBoard()[0].length);
		assertEquals(3,rider.getStartingCell().getColumn());
		assertEquals(2,rider.getStartingCell().getRow());
		assertEquals(3,rider.getCurrentCell().getColumn());
		assertEquals(2,rider.getCurrentCell().getRow());
		
		for (int r = 0; r < rider.getBoard().length; r++) {
			for (int c = 0; c < rider.getBoard()[r].length; c++) {
				if (r != rider.getCurrentCell().getRow() || c != rider.getCurrentCell().getColumn()) {
					assertFalse(rider.getBoard()[r][c]);
				}
			}
		}
		
		assertTrue(rider.getBoard()[rider.getCurrentCell().getRow()][rider.getCurrentCell().getColumn()]);
		
	}

	@Test
	public void testSetCurrentCell() {
		Knight tazmond = new Knight(new RowColPair(2,2),5);
		tazmond.setCurrentCell(new RowColPair(0,1));
		assertEquals(new RowColPair(0,1),tazmond.getCurrentCell());
	}

	@Test
	public void testSetStartingCell() {
		Knight tazmond = new Knight(new RowColPair(2,2),5);
		tazmond.clearBoard();
		tazmond.setStartingCell(new RowColPair(0,1));
		assertEquals(new RowColPair(0,1),tazmond.getStartingCell());
	}

	@Test
	public void testGetBestSquare() {
		Knight tazmond = new Knight(new RowColPair(2,2),5);
		RowColPair[] possible = tazmond.getPossibleMoves(2,2);
		RowColPair drBest = tazmond.getBestSquare(possible);
		assertTrue(drBest.equals(new RowColPair(0,1)) || 
				   drBest.equals(new RowColPair(0,3)) ||
				   drBest.equals(new RowColPair(1,0)) ||
				   drBest.equals(new RowColPair(1,4)) ||
				   drBest.equals(new RowColPair(3,0)) ||
				   drBest.equals(new RowColPair(3,4)) ||
				   drBest.equals(new RowColPair(4,1)) ||
				   drBest.equals(new RowColPair(4,3)));
		
		tazmond.setCurrentCell(new RowColPair(3,0));
		tazmond.getBoard()[3][0] = true;
		possible = tazmond.getPossibleMoves(3,0);
		assertEquals(2,possible.length);
		ArrayList<RowColPair> possibleAL = new ArrayList<RowColPair>();
		for (RowColPair v: possible) {
			possibleAL.add(v);
		}
		assertTrue(possibleAL.contains(new RowColPair(1,1)));
		assertTrue(possibleAL.contains(new RowColPair(4,2)));
		for (RowColPair sq: possible) {
			assertEquals(3,tazmond.getScoreOfSquare(sq.getRow(), sq.getColumn()));
		}
		tazmond.setCurrentCell(new RowColPair(4,2));
		tazmond.getBoard()[4][2] = true;
		
		possible = tazmond.getPossibleMoves(4,2);
		assertEquals(3,possible.length);
		possibleAL = new ArrayList<RowColPair>();
		for (RowColPair v: possible) {
			possibleAL.add(v);
		}
		assertTrue(possibleAL.contains(new RowColPair(2,1)));
		assertTrue(possibleAL.contains(new RowColPair(2,3)));
		assertTrue(possibleAL.contains(new RowColPair(3,4)));
		drBest = tazmond.getBestSquare(possible);
		assertEquals(new RowColPair(3,4),tazmond.getBestSquare(possible));
		
	}

	@Test
	public void testClearBoard() {
		Knight rider = new Knight(new RowColPair(0,0),8);
		rider.clearBoard();
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				assertFalse(rider.getBoard()[r][c]);
			}
		}
	}

	@Test
	public void testGetPossibleMoves() {
		Knight rider = new Knight(new RowColPair(0,0),8);
		RowColPair[] possible = rider.getPossibleMoves(0,0);
		assertEquals(2,possible.length);
		assertTrue(possible[0].getRow() == 1 && possible[0].getColumn() == 2 ||
				   possible[0].getRow() == 2 && possible[0].getColumn() == 1);		
		assertTrue(possible[1].getRow() == 1 && possible[1].getColumn() == 2 ||
				   possible[1].getRow() == 2 && possible[1].getColumn() == 1);
		
		
		Knight mildred = new Knight(new RowColPair(4,4),8);
		possible = mildred.getPossibleMoves(4,4);
		assertEquals(8,possible.length);
		
		Knight zuzu = new Knight(new RowColPair(0,0),8);
		boolean[][] g = zuzu.getBoard();
		for (int r = 0; r < g.length; r++) {
			for (int c = 0; c < g[r].length; c++) {
				g[r][c] = true;
			}
		}
		possible = zuzu.getPossibleMoves(0,0);
		assertEquals(0,possible.length);
			
	}

	@Test
	public void testGetScoreOfSquare() {
		Knight lancelot = new Knight(new RowColPair(0,0),8);
		assertEquals(5,lancelot.getScoreOfSquare(1,2));
		assertEquals(5,lancelot.getScoreOfSquare(2,1));
		assertEquals(3,lancelot.getScoreOfSquare(0,1));
		assertEquals(4,lancelot.getScoreOfSquare(0,2));
		assertEquals(4,lancelot.getScoreOfSquare(0,3));
		assertEquals(4,lancelot.getScoreOfSquare(0,4));
		assertEquals(4,lancelot.getScoreOfSquare(0,5));
		assertEquals(3,lancelot.getScoreOfSquare(0,6));
		assertEquals(2,lancelot.getScoreOfSquare(0,7));
		assertEquals(8,lancelot.getScoreOfSquare(3,4));
	}

	@Test
	public void testIsValid() {
		Knight rider = new Knight(new RowColPair(0,0),8);
		assertFalse(rider.isValid(-1, 1));
		assertFalse(rider.isValid(1, -1));
		assertFalse(rider.isValid(1, 8));
		assertFalse(rider.isValid(8, 1));
		
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				assertTrue(rider.isValid(r, c));
			}
		}
	}
	
	
}