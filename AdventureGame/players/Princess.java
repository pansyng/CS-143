// Pui Sze Pansy Ng
// CS 143
// Individual Assignment 4 - Adventure Program 

package players;

//This is the child class extends Player
public class Princess extends Player {
	
	private static int maxSteps = 3;
	private static int maxFatigue = 12;
	
	// constructor
	public Princess(String name) {
		super(name, maxFatigue, maxSteps);
	}
	
	//A statement will be print when princess steps in mud
	@Override
	public void stepsInMud() {
		System.out.println("This mud is going to stain my dress!");

	}

	//A statement will be print when princess steps on grass
	@Override
	public void stepsOnGrass() {
		System.out.println("This grass feels great between my toes.");

	}

	//A statement will be print when princess steps on road
	@Override
	public void stepsOnRoad() {
		System.out.println("This road is dusty.");

	}

	//A statement will be print when princess steps on trap
	@Override
	public void stepsOnTrap() {
		System.out.println("This trap won't affect me!");

	}

}
