package edu.smith.cs.csc212.spooky;

import java.util.HashMap;
import java.util.Map;

/**
 * FordHall, the game
 * @author zoedillon-davidson
 * (structure and methods taken liberally from jfoley's 
 * SpookyMansion
 *
 */ 

public class FordHall implements GameWorld{
	private Map<String, Place> places = new HashMap<>();
	
	/**
	 * Where the player should start
	 */
	@Override
	public String getStart() {
		// TODO Auto-generated method stub
		return "atrium";
	}

	public FordHall() {
		// TODO Auto-generated constructor stub
		Place atrium = insert(
				Place.create("atrium", "You are in the grand atrium of a large building.\n"
						+ "The front door was locked and you forgot your onecard. Nobody is around. How did you get inside? You don't know."
						+ " What will you do next?"));
		atrium.addExit(new Exit("basement", "There are stairs leading down."));
		atrium.addExit(new Exit("firstFloor", "There are stairs leading up."));
		atrium.addExit(new Exit("elevator", "There is an elevator."));
		
		Place firstFloor = insert(
				Place.create("firstFloor", "You're on the first floor. Everything looks deserted. Where is everybody?"));
		firstFloor.addExit(new Exit("atrium", "Too creepy. Go back down the stairs."));
		firstFloor.addExit(new Exit("secondFloor", "Go up more stairs."));
		
		Place secondFloor = insert(
				Place.create("secondFloor", "Eerie silence on the second floor. Where is everybody?"));
		secondFloor.addExit(new Exit("firstFloor", "Go back down the stairs."));
		secondFloor.addExit(new Exit("thirdFloor", "Going up stairs seems like a bad idea, but you could."));
		
		Place thirdFloor = insert(
				Place.create("thirdFloor", "There's a quiet clicking. It sounds like it's coming from everywhere and nowhere."));
		thirdFloor.addExit(new Exit("secondFloor", "You're no fool. Go back down the stairs."));
		thirdFloor.addExit(new Exit("elevator", "Heck no. You need to get down faster. Take the elevator."));
		thirdFloor.addExit(new Exit("classroom", "You're feeling brave. There's a nearby classroom.  Maybe someone in there can help you out of here?"));
		
		Place elevator = insert(
				Place.create("elevator", "Which level? Choose carefully."));
		elevator.addExit(new Exit("firstFloor", "First floor."));
		elevator.addExit(new Exit ("secondFloor", "Second floor."));
		elevator.addExit(new Exit("thirdFloor", "Third floor."));
		elevator.addExit(new SecretExit("secretLab", "A new level button pops up. It seems "
				+ "to be glowing with some kind of promising academic aura."));
		
		Place basement = insert(
				Place.create("basement", "It's dark and there are a lot of engineering students around. Wait, no --"
						+ " those are ZOMBIES!"));
		basement.addExit(new Exit("atrium", "Run back up those stairs!"));
		
		Place secretLab = insert(
				Place.terminal("secretLab", "It's a research lab! There are pathways to your future everywhere. "
						+ "There's even a door to the sunny outside. \nCongratulations, "
						+"you've made it."));
		
		Place classroom = insert(
				Place.create("classroom", "Notes on the whiteboard are appearing mysteriously,"
						+" as if written by an invisible ghost hand..."));
		classroom.addExit(new Exit("thirdFloor", "No way. You have enough on your plate without adding ghosts."
				+" Back out to the third floor hallway."));
	}
	
	/**
	 * This helper method saves us a lot of typing. We always want to map from p.id
	 * to p.
	 * 
	 * @param p - the place.
	 * @return the place you gave us, so that you can store it in a variable.
	 */
	private Place insert(Place p) {
		places.put(p.getId(), p);
		return p;
	}
	/**
	 * I like this method for checking to make sure that my graph makes sense!
	 */
	private void checkAllExitsGoSomewhere() {
		boolean missing = false;
		// For every place:
		for (Place p : places.values()) {
			// For every exit from that place:
			for (Exit x : p.getVisibleExits()) {
				// That exit goes to somewhere that exists!
				if (!places.containsKey(x.getTarget())) {
					// Don't leave immediately, but check everything all at once.
					missing = true;
					// Print every exit with a missing place:
					System.err.println("Found exit pointing at " + x.getTarget() + " which does not exist as a place.");
				}
			}
		}

		// Now that we've checked every exit for every place, crash if we printed any
		// errors.
		if (missing) {
			throw new RuntimeException("You have some exits to nowhere!");
		}
	}
	/**
	 * Get a Place object by name.
	 */
	public Place getPlace(String id) {
		// TODO Auto-generated method stub
		return this.places.get(id);
	}
	

}
