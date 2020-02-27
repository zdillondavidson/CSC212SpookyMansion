package edu.smith.cs.csc212.spooky;

import java.util.List;

/**
 * This is our main class for SpookyMansion. It interacts with a GameWorld and
 * handles user-input. It can play any game, really.
 *
 * @author jfoley
 *
 */
public class InteractiveFiction {

	/**
	 * This method actually plays the game.
	 * 
	 * @param input - a helper object to ask the user questions.
	 * @param game  - the places and exits that make up the game we're playing.
	 * @return where - the place the player finished.
	 */
	
	static String runGame(TextInput input, GameWorld game) {
		// This is the current location of the player (initialize as start).
		Player player = new Player(game.getStart());
		// To keep track of game current time and total hours.
		GameTime gameTime = new GameTime();
		

		// Instructs player how to get help
		System.out.println("Type 'help' at any time for instructions.");

		// Play the game until quitting.
		// This is too hard to express here, so we just use an infinite loop with
		// breaks.
		while (true) {
			// Print the description of where you are.
			Place here = game.getPlace(player.getPlace());

			System.out.println();
			System.out.println("... --- ...");
			System.out.println(here.printDescription());
			// Prints game time.
			System.out.println("The time is "+gameTime.getHour()+":00.");

			if (player.hasBeenHereBefore()) {
				System.out.println("This place feels familiar.");
			}

			// Game over after print!
			if (here.isTerminalState()) {
				// Prints total time spent in game.
				System.out.println("\nYou spent " +gameTime.getTotalHours()+" hours in the game.");
				break;
				
			}

			// Show a user the ways out of this place.
			List<Exit> exits = here.getVisibleExits();

			for (int i = 0; i < exits.size(); i++) {
				Exit e = exits.get(i);
				System.out.println(" " + i + ". " + e.getDescription());
			}

			// Figure out what the user wants to do, for now, only "quit" is special.
			List<String> words = input.getUserWords("?");
			if (words.size() > 1) {
				System.out.println("Only give the system 1 word at a time!");
				continue;
			}

			// Get the word they typed as lowercase, and no spaces.
			// Do not uppercase action -- I have lowercased it.
			String action = words.get(0).toLowerCase().trim();

			if (action.equals("q") || action.equals("escape") || action.equals("quit")){
				if (input.confirm("Are you sure you want to quit?")) {
					// Prints total time spent in game.
					System.out.println("\nYou spent " +gameTime.getTotalHours()+" hours in the game.");
					// quit!
					break;
				} else {
					// go to the top of the game loop!
					continue;
				}
			}
			// The help command
			if (action.equals("help")) {
				System.out.println("To play the game, enter the number of the "
						+ "option you want to choose, followed by the enter key."
						+ " If you would like to quit the game at any time, type 'q' or " + "'escape'. Good luck!");

				// go to the top of the game loop!
				continue;
			}
			// The search command
			if (action.equals("search")) {
				here.search();
				continue;
			}

			// The stuff command
			if (action.equals("stuff")) {
				player.printStuff();
				continue;
			}
			// The take command
			if (action.equals("take")) {
				player.take(here.getPlacesItems());
				here.emptyPlacesItems();
				continue;
			}

			// From here on out, what they typed better be a number!
			Integer exitNum = null;
			try {
				exitNum = Integer.parseInt(action);
			} catch (NumberFormatException nfe) {
				System.out.println("That's not something I understand! Try a number!");
				continue;
			}

			if (exitNum < 0 || exitNum >= exits.size()) {
				System.out.println("I don't know what to do with that number!");
				continue;
			}

			// Move to the room they indicated.
			Exit destination = exits.get(exitNum);
			if (destination.canOpen(player)) {
				player.moveTo(destination.getTarget());
				// Increase time by 1 hour.
				gameTime.increaseHour();
				
			} else {
				// TODO: some kind of message about it being locked?
			}
		}

		return player.getPlace();
	}

	/**
	 * This is where we play the game.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// This is a text input source (provides getUserWords() and confirm()).
		TextInput input = TextInput.fromArgs(args);

		// This is the game we're playing.
		GameWorld game = new SpookyMansion();

		// Actually play the game.
		runGame(input, game);

		// You get here by typing "quit" or by reaching a Terminal Place.
		System.out.println("\n\n>>> GAME OVER <<<");
		
	}

}
