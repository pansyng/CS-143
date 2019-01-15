// Pui Sze Pansy Ng
// CS 143
// Individual Assignment 4 - Adventure Program 

package tiles;

import game.Game;
import players.Player;
import players.Princess;


//This is the child class extends BasicTile
public class TrapTile extends BasicTile {
	//constructor
	public TrapTile() {
		super(TileType.TRAP);
	}
	/**
	 * This method call the player object from the game class. If player class is not princess, which is squire and 
	 * knight, current fatigue will add 2. 
	 * Call stepsOnTrap statement. It will print out the statement. Depends on the player class, the player step on trap
	 * statement will be vary.
	 * Print out currentFatigue / maxFatigue
	 */
	@Override
	public void visitTile(Game game) {
		Player player = game.getPlayer();
		if(!player.getClass().equals(Princess.class)) {
			player.setCurrentFatigue(player.getCurrentFatigue() + 2);
			game.movePlayer(-1);
		}
		
		player.stepsOnTrap();
		System.out.println("Player fatigue: " + player.getCurrentFatigue() + "/" + player.getMaxFatigue());

	}

}
