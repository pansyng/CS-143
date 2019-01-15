// Pui Sze Pansy Ng
// CS 143
// Individual Assignment 4 - Adventure Program 

package tiles;

import game.Game;
import players.*;
//This is the child class extends BasicTile
public class MudTile extends BasicTile {
	//constructor
	public MudTile() {
		super(TileType.MUD);
		
	}
	/**
	 * This method call the player object from the game class. If player class is not squire, which is princess and 
	 * knight, current fatigue will add 2. 
	 * Call stepsOnMud statement. It will print out the statement. Depends on the player class, the player step on trap
	 * statement will be vary.
	 * Print out currentFatigue / maxFatigue
	 */
	@Override
	public void visitTile(Game game) {
		Player player = game.getPlayer();
		if(!player.getClass().equals(Squire.class))
			player.setCurrentFatigue(player.getCurrentFatigue() + 2);
		player.stepsInMud();
		System.out.println("Player fatigue: " + player.getCurrentFatigue() + "/" + player.getMaxFatigue());


	}

}
