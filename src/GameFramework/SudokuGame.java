package GameFramework;
/**
 * 
 * @author nikitaschultz
 *
 */


public interface SudokuGame {
	//Get the number of players
	int getNumberOfPlayers();
	
	//Set the number of players
	void setNumberOfPlayers(int value);
	
	//Get the player details
	SudokuPlayer[] getPlayerDetails();
	
	//Set all player details
	void setPlayerDetails(SudokuPlayer[] value);
	
	//Get the instructions for the game
	String getInstructions();
	
	//Set the instructions for the game
	void setInstructions(String value);
	
	//Run the algorithm to play the game
	void Play();
	
	//List each player by details in a String
	String listPlayers();
	
}
