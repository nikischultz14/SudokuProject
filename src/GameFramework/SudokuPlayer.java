package GameFramework;

import java.io.Serializable;

/**
 * 
 * @author nikitaschultz
 *
 */

public class SudokuPlayer implements Serializable {
	private String playerName;
	private int playerScore;
	private int playerID;
	private final int minPlayers = 1;
	private final int maxPlayers = 25;
	private final int minScore = 0;
	private final int maxScore = 25000000;
	
	//Default constructor
	public SudokuPlayer() {
		//do nothing...
	}
	
	//Construct with name and score = 0
	public SudokuPlayer(String name, int PlayerID) {
		playerName = name;
		playerID = playerID;
		playerScore = 0;
	}
	
	//Construct with name, id, and score
	public SudokuPlayer(String name, int playerID, int score) {
		this.setPlayerName(name);
		this.setPlayerID(playerID);
		this.setPlayerScore(score);
	}
	
	//Get the player name
	public String getPlayerName() {
		return this.playerName;
	}
	
	//Set the player name
	public void setPlayerName(String value) {
		if (value == null || value.equals("")) {
			throw new IllegalArgumentException("Please enter a valid"
					+ " player name.");
		}
		this.playerName = value;
	}
	
	//Get the player ID
	public int getPlayerID() {
		return this.playerID;
	}
	
	//Set the player ID
	public void setPlayerID(int value) {
		if (value < minPlayers || value > maxPlayers) {
			throw new IllegalArgumentException("ID should match player number");
		}
		this.playerID = value;
	}
	
	//Get the player score
	public int getPlayerScore() {
		return this.playerScore;
	}
	
	//Set the player score
	public void setPlayerScore(int value) {
		if (value < minScore || value > maxScore) {
			throw new IllegalArgumentException("Score needs to be valid.");
		}
		this.playerScore = value;
	}
	
	//The player details
	public String toString() {
		return String.format("%d]%s | %d\n"
				, this.getPlayerID()
				, this.getPlayerName()
				, this.getPlayerScore());
	}
}
