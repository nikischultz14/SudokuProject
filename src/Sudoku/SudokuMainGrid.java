package Sudoku;

import java.io.Serializable;
import java.util.Random;

/**
 * 
 * @author nikitaschultz
 *
 */

public class SudokuMainGrid implements Serializable {
	private SudokuGridValueCell[][] _grid;
	private SudokuGridValueCell[][] _gridStartCondition;
	private SudokuGridValueCell[][] _solution;
	private final int _xSize = 9;
	private final int _ySize = 9;
	private int _difficultyLevel;
	Random r;
	
	//create a new grid object for gameplay
	//difficulty = easiest, default 
	
	public SudokuMainGrid() {
		_difficultyLevel = 0; 
		r = new Random();
		ResetGame();
	}
	
	//Create a new grid object for gameplay
	public SudokuMainGrid(int difficultyLevel) {
		_difficultyLevel = difficultyLevel;
		r = new Random();
		ResetGame();
	}
	
	//Reset the game
	private void ResetGame() {
		_grid = new SudokuGridValueCell[_xSize][_ySize];
		_gridStartCondition = new SudokuGridValueCell[_xSize][_ySize];
		for (int i = 0; i < _xSize; i++) {
			for (int j = 0; j < _ySize; j++) {
				_grid[i][j] = new SudokuGridValueCell(0, false);
				_gridStartCondition[i][j] = new SudokuGridValueCell(0, false);
			}
		}
		//build the solution;
		generateNewSolution();
	}
	
	//Generate new solution, then put it in the solution grid
	private void generateNewSolution() {
		_solution = new SudokuGridValueCell[_xSize][_ySize];
		SudokuMainGenerator sg = new SudokuMainGenerator();
		int[][] tempSol = sg.generateSolution(new int[_xSize][_ySize], 0);
		for (int i = 0; i< _xSize; i++) {
			for (int j = 0; j < _ySize; j++) {
				SudokuGridValueCell sgc = new SudokuGridValueCell();
				sgc.setValue(tempSol[i][j]);;
				sgc.setIsLocked(true);;
				_solution[i][j] = sgc;
			}
		}
		
		int[][] tempGame = sg.generateGame(tempSol);
		for (int i = 0; i < _xSize; i++) {
			for (int j = 0; j < _ySize; j++) {
				SudokuGridValueCell sgc = new SudokuGridValueCell();
				sgc.setValue(tempGame[i][j]);;
				sgc.setIsLocked(sgc.getValue() != 0);;
				_grid[i][j] = sgc;
				SudokuGridValueCell sgc2 = new SudokuGridValueCell();
				sgc2.setValue(tempGame[i][j]);;
				sgc2.setIsLocked(sgc.getValue() != 0);;
				_gridStartCondition[i][j] = sgc2;
			}
		}
		
		int x;
		int y;
		boolean altered = false;
		
		if (_difficultyLevel == 0) {
			//easiest - add in one more number per sector
			x = GetNextRandom(0, 2);
			y = GetNextRandom(0, 2);
			PartialSolveBlock(x, y, 0, 2, 0, 2);
			x = GetNextRandom(3, 5);
			y = GetNextRandom(0, 2);
			PartialSolveBlock(x, y, 3, 5, 0, 2);
			x = GetNextRandom(6, 8);
			y = GetNextRandom(0, 2);
			PartialSolveBlock(x, y, 6, 8, 0, 2);
			x = GetNextRandom(0, 2);
			y = GetNextRandom(3, 5);
			PartialSolveBlock(x, y, 0, 2, 3, 5);
			x = GetNextRandom(3, 5);
			y = GetNextRandom(3, 5);
			PartialSolveBlock(x, y, 3, 5, 3, 5);
			x = GetNextRandom(6, 8);
			y = GetNextRandom(3, 5);
			PartialSolveBlock(x, y, 6, 8, 3, 5);
			x = GetNextRandom(0, 2);
			y = GetNextRandom(6, 8);
			PartialSolveBlock(x, y, 0, 2, 6, 8);
			x = GetNextRandom(3, 5);
			y = GetNextRandom(6, 8);
			PartialSolveBlock(x, y, 3, 5, 6, 8);
			x = GetNextRandom(6, 8);
			y = GetNextRandom(6, 8);
			PartialSolveBlock(x, y, 6, 8, 6, 8);
		}
		else if (_difficultyLevel == 1) {
			int alteredCount = 0;
			//easy - add in 6 extra numbers at random cells
			while (alteredCount < 5) {
				x = GetNextRandom(0, 8);
				y = GetNextRandom(0, 8);
				if (!_grid[x][y].IsLocked()) {
					SudokuGridValueCell sgc = new SudokuGridValueCell();
					sgc.setValue(_solution[x][y].getValue());
					sgc.setIsLocked(sgc.getValue() != 0);
					_grid[x][y] = sgc;
					SudokuGridValueCell sgc2 = new SudokuGridValueCell();
					sgc2.setValue(_solution[x][y].getValue());
					sgc2.setIsLocked(sgc.getValue() != 0);
					_gridStartCondition[x][y] = sgc2;
					alteredCount++;
				}
			}
		}
		else if (_difficultyLevel == 2) {
			//medium - add in 3 extra numbers at random cells
			int alteredCount = 0;
			while (alteredCount < 3) {
				x = GetNextRandom(0, 8);
				y = GetNextRandom(0, 8);
				if (!_grid[x][y].IsLocked()) {
					SudokuGridValueCell sgc = new SudokuGridValueCell();
					sgc.setValue(_solution[x][y].getValue());
					sgc.setIsLocked(sgc.getValue() != 0);
					_grid[x][y] = sgc;
					SudokuGridValueCell sgc2 = new SudokuGridValueCell();
					sgc2.setValue(_solution[x][y].getValue());
					sgc2.setIsLocked(sgc.getValue() != 0);
					_gridStartCondition[x][y] = sgc2;
					alteredCount++;
				}
			}
		}
		//otherwise leave as is
	}
	
	//solve part of the puzzle to lower the difficulty
	private void PartialSolveBlock(int x, int y, int minx, int maxx
									, int miny, int maxy) {
		boolean altered = false;
		
		while ((_grid[x][y]).IsLocked() && altered == false) {
			x++;
			if (x > maxx) x=minx;
			
			if ((_grid[x][y]).IsLocked() && altered == false) {
				y++;
				if (y > maxy) y=miny;
				if ((_grid[x][y]).IsLocked() && altered == false) {
					y--;
					if (y < miny) y = maxy;
				}
				else {
					SudokuGridValueCell sgc = new SudokuGridValueCell();
					sgc.setValue(_solution[x][y].getValue());
					sgc.setIsLocked(sgc.getValue() != 0);
					_grid[x][y] = sgc;
					SudokuGridValueCell sgc2 = new SudokuGridValueCell();
					sgc.setValue(_solution[x][y].getValue());
					sgc2.setIsLocked(sgc.getValue() != 0);
					_gridStartCondition[x][y] = sgc2;
					altered = true;
				}
			}
			else {
				SudokuGridValueCell sgc = new SudokuGridValueCell();
                sgc.setValue(_solution[x][y].getValue());
                sgc.setIsLocked(sgc.getValue() != 0);
                _grid[x][y] = sgc;
                SudokuGridValueCell sgc2 = new SudokuGridValueCell();
                sgc2.setValue(_solution[x][y].getValue());
                sgc2.setIsLocked(sgc.getValue() != 0);
                _gridStartCondition[x][y] = sgc2;
                altered = true;
			}
		}
		if (!altered) {
			SudokuGridValueCell sgc = new SudokuGridValueCell();
            sgc.setValue(_solution[x][y].getValue());
            sgc.setIsLocked(sgc.getValue() != 0);
            _grid[x][y] = sgc;
            SudokuGridValueCell sgc2 = new SudokuGridValueCell();
            sgc2.setValue(_solution[x][y].getValue());
            sgc2.setIsLocked(sgc.getValue() != 0);
            _gridStartCondition[x][y] = sgc2;
            altered = true;
		}
	}
		
	//Get a true random for a range
	private int GetNextRandom(int min, int max) {
		return min + (int)(java.lang.Math.random() * ((max - min) + 1));
	}	
	
	//Determine if the grid is solved
	public boolean IsSolved() {
		//iterate entire gameboard grid & determine if any numbers are missing
		for (int i = 0; i < _xSize; i++) {
			for (int j = 0; j < _ySize; j++) {
				SudokuGridValueCell sgc = _grid[i][j];
				SudokuGridValueCell sgcSol = _solution[i][j];
				int gridVal = sgc.getValue();
				int solVal = sgcSol.getValue();
				if (gridVal == 0 || (gridVal != solVal)) {
					return false;
				}
			}
		}
		return true;
	}
	
	//Instantly set the grid to solved
	public void InstantSolveGrid() {
		_grid = _solution.clone();
	}
	
	//instantly reset the grid back to start condition
	public void InstantResetGrid() {
		_grid = _gridStartCondition.clone();
	}
	
	//Have to allow the user to enter a bad number but not set the grid
	public boolean CheckCellValue(int row, int col, int val) {
		if (val < 1 || val > 9) {
			return false;
		}
		return ValidateInput(row, col, val);
	}
	
	
	/** Attempt to set the grid cell to a given value
	 * if input is invalid nothing will happen, if valid grid will update
	 * either way the grid gets reprinted at end
	 * 
	 */
	
	public boolean SetCellValue(int row, int col, int val) {
		if (val < 1 || val > 9) {
			return false;
		}
		
		boolean isOK = ValidateInput(row, col, val);
		if (isOK) {
			SudokuGridValueCell sgc = _grid[row][col];
			sgc.setValue(val);
			return true;
		}
		return false;
		
	}
	
	//Validate input against game grid not solution grid
	private boolean ValidateInput(int row, int col, int val) {
		SudokuGridValueCell sgc = _grid[row][col];
		if (sgc.IsLocked()) {
			return false;
		}
		
		if (!isValidRow(row, val)) {
			return false;
		}
		if (!isValidCol(col, val)) {
			return false;
		}
		if (!isValidSector(row, col, val)) {
			return false;
		}
		return true;
	}
	
	//Check for number already in row
	private boolean isValidRow(int row, int val) {
		for (int i = 0; i < _ySize; i++) {
			SudokuGridValueCell sgc = _grid[row][i];
			if (sgc.getValue() == val) {
				return false;
			}
		}
		return true;
	}
	
	//Check if number already is in column
	private boolean isValidCol(int col, int val) {
		for (int i = 0; i < _xSize; i++) {
			SudokuGridValueCell sgc = _grid[i][col];
			if (sgc.getValue() == val) {
				return false;
			}
		}
		return true;
	}
	
	//Check if valid in sector
	private boolean isValidSector(int row, int col, int val) {
		int maxRow = 0;
		int minRow = 0;
		int maxCol = 0;
		int minCol = 0;
		
		if (row < 3) {
			maxRow = 3;
			minRow = 0;
		}
		else if (row < 6) {
			maxRow = 6; 
			minRow = 3;
		}
		else {
			maxRow = 9;
			minRow = 6;
		}
		if (col < 3) {
			maxCol = 3;
			minCol = 0;
		}
		else if (col < 6) {
			maxCol = 6;
			minCol = 3;
		}
		else {
			maxCol = 9;
			minCol = 6;
		}
		
		for (int i = minRow; i < maxRow; i++) {
			for (int j = minCol; j < maxCol; j++) {
				SudokuGridValueCell sgc = _grid[i][j];
				if (sgc.getValue() == val) {
					return false;
				}
			}
		}
		return true;
	}
	
	//get the grids as value that can be used to generate the results
	//return the game grid
	public SudokuGridValueCell[][] getGameGrid() {
		return _grid;
	}
	
	//return the solution
	public SudokuGridValueCell[][] getSolution() {
		return _solution;
	}
	
	//return the start condition/reset grid
	public SudokuGridValueCell[][] getStartCondition() {
		return _gridStartCondition;
	}
}
