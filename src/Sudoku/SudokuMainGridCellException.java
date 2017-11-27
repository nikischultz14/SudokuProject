package Sudoku;

import java.io.Serializable;

/**
 * 
 * @author nikitaschultz
 *
 */

public class SudokuMainGridCellException extends RuntimeException
	implements Serializable {
	
	public SudokuMainGridCellException() {
		super ("Grid cells must contain numbers 1-9 only.");
	}
	
	public SudokuMainGridCellException(String message) {
		super(String.format("%s|n%s"
				,"Grid cells must contain numbers 1-9 only."
				, message));
	}
}
