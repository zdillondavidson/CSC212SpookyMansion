package edu.smith.cs.csc212.spooky;

public class SecretExit extends Exit {
	
	/**
	 * Whether or not SecretExit is currently hidden, always
	 * starts off as true.
	 */
	private boolean hidden;

	public SecretExit(String target, String description) {
		super(target, description);
		// TODO Auto-generated constructor stub
		
		this.hidden = true;
	}
	
	/**
	 * Whether or not Secret Exit is currently still secret.
	 * @return - whether SecretExit is still hidden or not
	 */
	@Override
	public boolean isSecret() {
		return this.hidden;
	}
	
	/**
	 * If search() is called on a SecretExit, the boolean hidden
	 * is now changed to false.
	 */
	@Override
	public void search() {
		this.hidden = false;
	}
	

}
