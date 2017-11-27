package Sudoku;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

/**
 * 
 * @author nikitaschultz
 *
 */

public class SudokuGridCell extends JTextField {
	private boolean isLocked;
	
	//constructor
	public SudokuGridCell() {
		super();
		setupCell();
	}
	
	//constructor
	public SudokuGridCell(int cols) {
		super(cols);
		setupCell();
	}
	
	public boolean getIsLocked() {
		return isLocked;
	}
	
	public void setIsLocked(boolean value) {
		isLocked = value;
	}
	
	//set the default properties
	private void setupCell() {
		this.setText("");;
		this.setFont(new Font("Verdana", java.awt.Font.PLAIN, 22));;
		this.setAlignmentX(CENTER_ALIGNMENT);
		this.setHorizontalAlignment(JTextField.CENTER);
		this.setBorder(new javax.swing.border.LineBorder(Color.BLACK, 1));
	}
}
