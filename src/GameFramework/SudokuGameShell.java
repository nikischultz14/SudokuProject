package GameFramework;
import java.io.Serializable;

/**
 * 
 * @author nikitaschultz
 *
 */

public abstract class SudokuGameShell implements SudokuGame, Serializable {
	private int numPlayers;
	private SudokuPlayer[] players;
	private String instructions;
	private final int minPlayers = 1;
	private final int maxPlayers = 25;
	
	public SudokuGameShell() {
		numPlayers = 0;
	}
	
	//Get the number of players
	public int getNumberOfPlayers() {
		return this.numPlayers;
	}
	
	//Set the number of players
	public void setNumberOfPlayers(int value) {
		if (value < minPlayers || value > maxPlayers) {
			throw new IllegalArgumentException(String.format("Please "
					+ "enter between %d and %d players"
					, minPlayers
					, maxPlayers));
		}
		this.numPlayers = value;
	}
	
	//Get the player details
	public SudokuPlayer[] getPlayerDetails() {
		return this.players;
	}
	
	//Set all player details
	public void setPlayerDetails(SudokuPlayer[] value) {
		if (value == null || value.length < minPlayers
				|| value.length > maxPlayers) {
			throw new IllegalArgumentException(
					String.format("Make sure you have "
							+ "only between %d and %d players"
							+ "and that the values are set correctly."
							, minPlayers
							, maxPlayers));
		}
		this.players = value;
	}
	
	//Get the instructions for the game
	public String getInstructions() {
		return this.instructions;
	}
	
	//Set the instructions for the game
	public void setInstructions(String value) {
		if (value == null || value.equals("")) {
			throw new IllegalArgumentException("Instructions must be set.");
		}
		this.instructions = value;
	}
	
	//play algorithm differs each game so need to defer that code to the implementing class
	public abstract void Play();
	
	//add a player
	protected boolean AddPlayer(SudokuPlayer p) {
		SudokuPlayer[] newPlayers = new SudokuPlayer[this.numPlayers + 1];
		for (int i = 0; i < this.numPlayers; i++) {
			newPlayers[i] = players[i];
		}
		newPlayers[this.numPlayers] = p;
		players = newPlayers;
		this.numPlayers++;
		return true;
	}
	
	//method to remove players******
	//methods to get/adjust scores etc******
	
	//List each player by details in a String
	public String listPlayers() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < this.numPlayers; i++) {
			sb.append(String.format("%s\n",  players[i]));
		}
		return sb.toString();
	}
}
