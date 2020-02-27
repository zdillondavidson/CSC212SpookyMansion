package edu.smith.cs.csc212.spooky;

import java.util.HashSet;
import java.awt.List;
import java.util.ArrayList;
import java.util.Set;

/**
 * This class represents all of the game state needed to understand the player.
 * At the beginning of this project, it is just the ID or name of a place.
 * 
 * @author jfoley
 *
 */
public class Player {
	/**
	 * The ID of the Place object where the player is currently.
	 */
	private String place;
	/**
	 * The ID of the Place object where the player is currently.
	 */
	private Set<String> visited;

	/**
	 * Stuff the player has.
	 */
	public ArrayList<String> itemsPlayerHas = new ArrayList<>();

	/**
	 * A player is created at the start of a game with just an initial place.
	 * 
	 * @param initialPlace - where do we start?
	 */
	public Player(String initialPlace) {
		this.place = initialPlace;
		this.visited = new HashSet<>();	}
	
	/**
	 *  Creates the 
	 * @return - printed String (without brackets) of stuff player has
	 */
	public String printedStuff() {
		String printedStuff = "";
		for (String i : this.itemsPlayerHas) {
			printedStuff += i;
		}
		return printedStuff;
	}
	/**
	 * Prints the stuff the player has.
	 */
	public void printStuff() {
		if (this.itemsPlayerHas.size() != 0) {
			System.out.println("You have: "+printedStuff()+".");

		} else {
			System.out.println("You have nothing.");
		}
	}

	/**
	 * Get access to the place instance variable from outside this class.
	 * 
	 * @return the id of the current location.
	 */
	public String getPlace() {
		return place;
	}

	/**
	 * Call this method when the user types "take" to take
	 * items from a place.
	 * @param list - the list of items from any place the player is taking.
	 */
	public void take(ArrayList<String> list) {
		if (list.size() == 0) {
			System.out.println("There is nothing left to take here.");
		} else {
			for (String i : list) {
				itemsPlayerHas.add(i);
				System.out.println("You take the " + i + ".");
			}
		}
	}

	/**
	 * Call this method when the player moves to a new place.
	 * 
	 * @param place - the place we are now located at.
	 */
	public void moveTo(String place) {
		this.rememberThisPlace();
		this.place = place;

	}

	/**
	 * This adds the current place to the player's memory.
	 */
	public void rememberThisPlace() {
		this.visited.add(place);

	}

	/**
	 * This checks the player's memory.
	 * 
	 * @return true if we have been here before.
	 */
	public boolean hasBeenHereBefore() {
		return this.visited.contains(this.getPlace());
	}
}
