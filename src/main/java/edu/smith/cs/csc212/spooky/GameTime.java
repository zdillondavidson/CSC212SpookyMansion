package edu.smith.cs.csc212.spooky;

public class GameTime {
	/**
	 * The hour of the game the player is on.
	 */
	int hour;
	/**
	 * The total hours the player has spent in the game.
	 */
	int totalHours;

	public GameTime() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * To be called every time the player moves, increases hour by 1.
	 */
	public void increaseHour() {
		if (this.hour == 23) {
			this.hour = 0;
		} else {
			this.hour += 1;
		}
		this.totalHours += 1;
	}

	/**
	 * To be called and shown to the player every time the player reaches a new
	 * location after the room description.
	 * 
	 * @return - the current hour
	 */
	public int getHour() {
		int currentHour = this.hour;
		return currentHour;
	}

	/**
	 * Returns total hours player spent in game.
	 * 
	 * @return - the total hours
	 */
	public String getTotalHours() {
		String printedTotalHours = Integer.toString(this.totalHours);
		return printedTotalHours;
	}

	/**
	 * Whether or not it is night time in the game.
	 * 
	 * @return - returns true if hour is between 20 and 6 (8pm to 6am)
	 */
	public boolean isNightTime() {
		if (this.hour <= 6 || this.hour >= 20) {
			return true;
		}
		return false;
	}

	/**
	 * Advances game two hours in order to test descriptions
	 */
	public void rest() {
		this.hour += 2;
	}

}
