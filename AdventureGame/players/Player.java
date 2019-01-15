// Pui Sze Pansy Ng
// CS 143
// Individual Assignment 4 - Adventure Program 

package players;

public abstract class Player
{
	/*
	 * Player objects will need to manage:
	 * name: the players name
	 * fatigue: the players current and maximum fatigue (a player loses when current == maximum)
	 * steps: the maximum number of steps a player can take (based on sub class)
	 */
	private String name;
	private int currentFatigue;
	private int maxFatigue;
	private int steps;
	
	//constructor
	public Player(String name, int maxFatigue, int steps) {
		this.name = name;
		this.maxFatigue = maxFatigue;
		this.steps = steps;
	}
	
	// getter of Name
	public String getName() {
		return name;
	}

	//getter of Current Fatigue
	public int getCurrentFatigue() {
		return currentFatigue;
	}

	//setter of Current Fatigue
	public void setCurrentFatigue(int currentFatigue) {
		this.currentFatigue = currentFatigue;
	}

	//getter of MaxFatigue
	public int getMaxFatigue() {
		return maxFatigue;
	}

	// getter of steps
	public int getSteps() {
		return steps;
	}

	// abstract class calls three child class
	public abstract void stepsInMud();
	public abstract void stepsOnGrass();
	public abstract void stepsOnRoad();
	public abstract void stepsOnTrap();
}
