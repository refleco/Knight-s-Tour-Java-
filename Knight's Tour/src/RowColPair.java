public class RowColPair {
	private int row;
	private int column;
	
	public RowColPair(int r, int c) {
		row = r;
		column = c;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	@Override
	public String toString() {
		return "RowColPair [row=" + row + ", column=" + column + "]";
	}
	
	public boolean equals(Object x) {
		RowColPair other = (RowColPair)x;
		return this.row == other.row && this.column == other.column;
	}
}
