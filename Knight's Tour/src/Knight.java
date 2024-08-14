import java.util.Random;

public class Knight {
	private RowColPair currentCell;
	private RowColPair startingCell;
	private boolean[][] grid;
	static Random randy = new Random();
	
	/**
	 * Creates a Knight with grid of size n by n.
	 * Sets the value of grid to true in the RowColPair represented by start. 
	 * Sets all other grid values to false.
	 * Sets currentCell and startingCell to start.
	 * @param start the starting RowColPair for this Knight
	 * @param n the number of rows and the number of columns in this Knight's board
	 */
	public Knight(RowColPair start, int n) {
		
		grid = new boolean[n][n];
		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[r].length;c++) {
				grid[r][c] = false;
			}
		}
		
		startingCell = new RowColPair(start.getRow(),start.getColumn());
		currentCell = new RowColPair(start.getRow(),start.getColumn());
		grid[start.getRow()][start.getColumn()] = true;
		
	}
	
	/**
	 * Returns this Knight's current RowColPair.
	 * @return this Knight's current RowColPair.
	 */
	public RowColPair getCurrentCell() {
		return currentCell;
	}

	/**
	 * Returns this Knight's starting RowColPair.
	 * @return this Knight's starting RowColPair.
	 */
	public RowColPair getStartingCell() {
		return startingCell;
	}

	/**
	 * Returns this Knight's board.
	 * @return this Knight's board.
	 */
	public boolean[][] getBoard() {
		return grid;
	}

	/**
	 * Returns an array of RowColPairs representing a Knights Tour for this Knight.
	 * @return an array of RowColPairs representing a Knights Tour for this Knight.
	 */
	public RowColPair[] solve() {
		RowColPair[] solution = new RowColPair[grid.length * grid[0].length];
		solution[0] = startingCell;
		for (int k = 1; k < solution.length; k++) {
			RowColPair cc = this.getCurrentCell();
			RowColPair[] possible = this.getPossibleMoves(cc.getRow(), cc.getColumn());
			if (possible.length == 0) {
				this.clearBoard();
				int newStartingRow = randy.nextInt(grid.length);
				int newStartingCol = randy.nextInt(grid[0].length);
				startingCell = new RowColPair(newStartingRow,newStartingCol);
				currentCell = startingCell;
				grid[newStartingRow][newStartingCol] = true;
				solution[0] = startingCell;
				k = 0;
			}
			else {
				RowColPair best = this.getBestSquare(possible);
				currentCell = best;
				solution[k] = best;
				grid[best.getRow()][best.getColumn()] = true;
			}
		}
		return solution;
	}

	/**
	 * Sets this Knight's current cell.
	 * @param current the desired current cell
	 */
	public void setCurrentCell(RowColPair current) {
		currentCell = current;
	}

	/**
	 * Resets this Knight's starting cell.
	 * @param starting the desired starting cell
	 */
	public void setStartingCell(RowColPair starting) {
		startingCell=starting;
	}

	/**
	 * Returns a RowColPair with the smallest score in possible.
	 * If several RowColPairs in possible have the same lowest score,
	 * one of these RowColPairs is selected at random and returned.
	 * @param possible the list of RowColPairs
	 * @return a RowColPair with the smallest score in possible
	 */
	public RowColPair getBestSquare(RowColPair[] possible) {
		int min = getScoreOfSquare(possible[0].getRow(),possible[0].getColumn());
		for(int k = 0; k < possible.length; k++) {
			if (getScoreOfSquare(possible[k].getRow(),possible[k].getColumn())< min) {
				min = getScoreOfSquare(possible[k].getRow(),possible[k].getColumn());
			}
		}
		
		int count = 0;
		for(int k = 0; k < possible.length; k++) {
			if (getScoreOfSquare(possible[k].getRow(),possible[k].getColumn())== min) {
				count++;
			}
		}
		
		RowColPair[] lowest = new RowColPair[count];
		int v= 0;
		for(int k = 0; k < possible.length; k++) {
			if (getScoreOfSquare(possible[k].getRow(),possible[k].getColumn())== min) {
				lowest[v] = possible[k];
				v++;
			}
		}
		return lowest[randy.nextInt(count)];
	}

	/**
	 * Sets all values in this Knight's board to false.
	 */
	public void clearBoard() {
		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[r].length;c++) {
				grid[r][c] = false;
			}
		}
	}

	/**
	 * Returns an array of all unvisited RowColPairs that are within one knight move of
	 * this Knight's current RowColPair.	 
	 * @return an array of all unvisited RowColPairs that are within one knight move of
	 * this Knight's current RowColPair.
	 */
	public RowColPair[] getPossibleMoves(int r, int c) {
		RowColPair[] nums = new RowColPair[getScoreOfSquare(currentCell.getRow(),currentCell.getColumn())];
		int v = 0;
		if (isValid(r-1,c+2) && grid[r-1][c+2]== false) {
			nums[v] = new RowColPair(r-1,c+2);
			v++;
		}
		if (isValid(r+1,c+2) && grid[r+1][c+2]== false) {
			nums[v] = new RowColPair(r+1,c+2);
			v++;
		}
		if (isValid(r-1,c-2) && grid[r-1][c-2]== false) {
			nums[v] = new RowColPair(r-1,c-2);
			v++;
		}
		if (isValid(r+1,c-2) && grid[r+1][c-2]== false) {
			nums[v] = new RowColPair(r+1,c-2);
			v++;
		}
		if (isValid(r+2,c+1) && grid[r+2][c+1]== false) {
			nums[v] = new RowColPair(r+2,c+1);
			v++;
		}
		if (isValid(r+2,c-1) && grid[r+2][c-1]== false) {
			nums[v] = new RowColPair(r+2,c-1);
			v++;
		}
		if (isValid(r-2,c+1) && grid[r-2][c+1]== false) {
			nums[v] = new RowColPair(r-2,c+1);
			v++;
		}
		if (isValid(r-2,c-1) && grid[r-2][c-1]== false) {
			nums[v] = new RowColPair(r-2,c-1);
			v++;
		}
		return nums;
	}

	/**
	 * Returns the number of unvisited RowColPairs that can be reached (with a knight move) from the RowColPair at row, col.
	 * @param row the row
	 * @param col the column
	 * @return the number of unvisited RowColPairs that can be reached (with a knight move) from the RowColPair at row, col
	 */
	public int getScoreOfSquare(int row, int col) {
		int count = 0;
		if (isValid(row-1,col+2)&& grid[row-1][col+2]==false) {
			count++;
		}
		if (isValid(row+1,col+2)&& grid[row+1][col+2]==false) {
			count++;
		}
		if (isValid(row+2,col+1)&& grid[row+2][col+1]==false) {
			count++;
		}
		if (isValid(row+2,col-1)&& grid[row+2][col-1]==false) {
			count++;
		}
		if (isValid(row+1,col-2)&& grid[row+1][col-2]==false) {
			count++;
		}
		if (isValid(row-1,col-2)&& grid[row-1][col-2]==false) {
			count++;
		}
		if (isValid(row-2,col+1)&& grid[row-2][col+1]==false) {
			count++;
		}
		if (isValid(row-2,col-1)&& grid[row-2][col-1]==false) {
			count++;
		}
		return count;
	}

	/**
	 * Returns true if the cell at row r, column c is in this Knight's board; returns false otherwise.
	 * @param r the row
	 * @param c the column
	 * @return true if the cell at row r, column c is in this Knight's board; false otherwise
	 */
	public boolean isValid(int r, int c) {
		if (r>grid.length -1 || r <0||c<0|| c >grid.length-1) {
			return false;
		}
		return true;
	}	
}
