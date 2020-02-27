package edu.smith.cs.csc212.spooky;

public class LockedExit extends Exit {

	/**
	 * The item that has the power to unlock a particular locked exit.
	 */
	String roomKey;

	public LockedExit(String target, String description, String roomKey) {
		super(target, description);
		// TODO Auto-generated constructor stub

		this.roomKey = roomKey;
	}

	/**
	 * Can the player open this door?
	 * @param player - the player object (and all other state)
	 * @return true if that is OK, false if they need something special.
	 */
	@Override
	public boolean canOpen(Player player) {
		if (player.itemsPlayerHas.contains(this.roomKey)) {
			return true;
		}
		System.out.println("You cannot open that yet. Maybe you need a key.");
		return false;
	}

}
