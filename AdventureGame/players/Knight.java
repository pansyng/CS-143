// Pui Sze Pansy Ng
// CS 143
// Individual Assignment 4 - Adventure Program 

package players;
//This is the child class extends Player
public class Knight extends Player {
	private static int maxSteps = 2;
	private static int maxFatigue = 25;
	//constructor
	public Knight(String name) {
		super(name, maxFatigue, maxSteps);
	}

	//A statement will be print when knight steps in mud
	@Override
	public void stepsInMud() {
		System.out.println("Mud, yuck, mud everywhere!");

	}

	//A statement will be print when knight steps on grass
	@Override
	public void stepsOnGrass() {
		System.out.println("This grass is slowing me down.");

	}

	//A statement will be print when knight steps on road
	@Override
	public void stepsOnRoad() {
		System.out.println("This road is more to my liking.");

	}

	//A statement will be print when knight steps on trap
	@Override
	public void stepsOnTrap() {
		System.out.println("Lucky my armor is on, ouch!");

	}

}
