public class Grid {
	private boolean[][] bombGrid;
	private int[][] countGrid;
	private int numRows;
	private int numColumns;
	private int numBombs;

	public Grid() {
		this.numRows = 10;
		this.numColumns = 10;
		this.numBombs = 5;

		this.bombGrid = new boolean[numRows][numColumns];
		this.countGrid = new int[numRows][numColumns];
		
		createBombGrid();
		createCountGrid();
	}

	public Grid(int rows, int columns) {
		this.numRows = rows;
		this.numColumns = columns;
		this.numBombs = 25;
		
		this.bombGrid = new boolean[numRows][numColumns];
		this.countGrid = new int[numRows][numColumns];
		
		createBombGrid();
		createCountGrid();
	}

	public Grid(int rows, int columns, int numBombs) {
		this.numRows = rows;
		this.numColumns = columns;
		this.numBombs = numBombs;

		this.bombGrid = new boolean[numRows][numColumns];
		this.countGrid = new int[numRows][numColumns];
		
		createBombGrid();
		createCountGrid();
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

	public int getNumBombs() {
		return numBombs;
	}

	public boolean[][] getBombGrid() {
		boolean[][] bombCopy = new boolean[numRows][numColumns];
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numColumns; col++) {
				bombCopy[row][col] = bombGrid[row][col];
			}
		}
		return bombCopy;
	}

	public int[][] getCountGrid() {
		int[][] copy = new int[numRows][numColumns];
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numColumns; col++) {
				copy[row][col] = countGrid[row][col];
			}
		}
		return copy;
	}

	public boolean isBombAtLocation(int row, int column) {
		if ((row >= 0 && row < countGrid.length) && (column >= 0 && column < countGrid[row].length)){
				if(bombGrid[row][column]) {
					return true;
				}
			}
		 return false;
}
	
	public int getCountAtLocation(int row, int column) {
		if ((row >= 0 && row < countGrid.length) && (column >= 0 && column < countGrid[row].length)){
			return countGrid[row][column];
		}
		 return -1;
	}
	
	public void createBombGrid() {
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numColumns; col++) {
				bombGrid[row][col] = false;
			}
		}
		for (int i = 0; i < numBombs; i++) {
			placeBomb();
		}
	}
	
	public void createCountGrid() {
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numColumns; col++) {
				countGrid[row][col] = countBombsNearCell(row, col);
			}
		}
	}
	
	private void placeBomb() {
		int rowIndex = (int)(Math.random() * 10);
		int columnIndex = (int)(Math.random() * 10);
		
		if(!(bombGrid[rowIndex][columnIndex])) {
			bombGrid[rowIndex][columnIndex] = true;
		}else {
			placeBomb();
		}
	}

	private int countBombsNearCell(int row, int column) {
		int numBombs = 0;
		for(int i = row - 1; i <= row + 1; i++) {
			for (int j = column - 1; j <= column + 1; j++) {
				try {
					if (bombGrid[i][j]) {
						numBombs++;
					}
				}catch(Exception e) {
					
				}
			}
		} return numBombs;
	}
}