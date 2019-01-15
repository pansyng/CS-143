// Pui Sze Pansy Ng
// CS 143
// Individual Assignment 4 - Adventure Program 

package tiles;

import game.Game;
import players.Player;

//This is the child class extends BasicTile
public class GrassTile extends BasicTile {
	//constructor
	public GrassTile() {
		super(TileType.GRASS);
	}
	/**
	 * This method call the player object from the game class. Every player will add 1 fatigue.
	 * Call stepsOnGrass statement. It will print out the statement. Depends on the player class, the player step on trap
	 * statement will be vary.
	 * Print out currentFatigue / maxFatigue
	 */
	@Override
	public void visitTile(Game game) {
		Player player = game.getPlayer();
		
		player.setCurrentFatigue(player.getCurrentFatigue() + 1);
		player.stepsOnGrass();
		System.out.println("Player fatigue: " + player.getCurrentFatigue() + "/" + player.getMaxFatigue());

	}

}
