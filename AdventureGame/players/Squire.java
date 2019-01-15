// Pui Sze Pansy Ng
// CS 143
// Individual Assignment 4 - Adventure Program 

package players;
/**
 * This class is the child class extends Player 
 * @author Pansy
 *
 */
public class Squire extends Player {
	private static int maxSteps = 3;
	private static int maxFatigue = 15;
	//This is the constructor 
	public Squire(String name){
		super(name, maxFatigue, maxSteps);
	}
	
	//A statement will be print when squire steps in mud
	@Override
	public void stepsInMud() {
		System.out.println("I grew up in the mud, this won't affect me!");

	}

	//A statement will be print when squire steps on grass
	@Override
	public void stepsOnGrass() {
		System.out.println("I love the open grassy fields.");

	}

	//A statement will be print when squire steps on road
	@Override
	public void stepsOnRoad() {
		System.out.println("I wonder how long this road will go on.");

	}

	//A statement will be print when squire steps on trap
	@Override
	public void stepsOnTrap() {
		System.out.println("Ugh a trap!");

	}

}
