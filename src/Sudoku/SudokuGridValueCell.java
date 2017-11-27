package Sudoku;
import java.io.Serializable;

/**
 * 
 * @author nikitaschultz
 *
 */

public class SudokuGridValueCell implements Serializable {
	private int _value;
	private boolean _isLocked;
	
	//Construct an unlocked grid cell with no value
	public SudokuGridValueCell() {
		//leave blank
	}

	//construct an unlocked grid cell with a value
	public SudokuGridValueCell(int value) {
		ValidateValue(value);
		_value = value;
		_isLocked = false;
	}
	
	//Construct grid cell with value and lock
	public SudokuGridValueCell(int value, boolean isLocked) {
		_value = value;
		_isLocked = isLocked;
	}
	
	//Get the value of the cell
	public int getValue() {
		return _value;
	}
	
	//Set value of cell
	public void setValue(int value) {
		ValidateValue(value);
		_value = value;
	}
	
	//Get the lock on the cell
	public boolean getIsLocked() {
		return _isLocked;
	}
	
	//Public friendly method for use in code
	public boolean IsLocked() {
		return getIsLocked();
	}
	
	//Set the lock on the cell
	public void setIsLocked(boolean value) {
		_isLocked = value;
	}
	
	//Return only the value of the cell in toString
	public String toString() {
		return String.format("%d",  _value);
	}
	
	//Validate that the value is valid for a Sudoku cell
	private void ValidateValue(int value) {
		if ((value < 0 || value > 9)) {
			throw new SudokuMainGridCellException();
		}
	}	
}
