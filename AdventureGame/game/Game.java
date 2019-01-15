// Pui Sze Pansy Ng
// CS 143
// Individual Assignment 4 - Adventure Program 

package game;

import java.lang.reflect.Array;
import java.util.Random;
import java.util.Scanner;

import players.Player;
import tiles.BasicTile;
import tiles.*;
/**
 * This class construct getPlayer(), startGame(), movePlayer(), buildTiles(), toString(). getPlayer() allows the classes
 * in tiles to get the player and change the current fatigue. startGame() constructs the game. movePlayer() change the 
 * poistion of the player. buildTiles() constructs the tiles array randomly. toStirng prints out the current location of
 * player. 
 * @author Pansy
 *
 */

public class Game {
	private static Scanner kb = new Scanner(System.in);
	
	// stores tile objects
	private BasicTile[] tiles;
	

	/*
	 * stores the index of player (which tile the player is located within inside
	 * the tiles array
	 */
	private int position = -1;

	// create the player object
	private Player player;

	public Game(int numTiles, Player player) {
		tiles = new BasicTile[numTiles];
		this.player = player;
	}

	/*
	 * This method call the buildTile() method and play the game while the gameover is false. 
	 */
	// public methods
	public void startGame() {
		// this creates our array of tile objects
		buildTiles();
		// this print out the array/tiles
		System.out.println(this.toString());
		/*
		 * The main game loop should be declared here. Each turn the player should move
		 * a random distance forward from 1 tmaxSteps.
		 * 
		 * NOTE: maxSteps should be stored in the Player class and should be 2 for
		 * Knights and 3 for squires or princesses.
		 */
		System.out.println();


		boolean gameOver = false;
		Random gen = new Random();
		
		while (!gameOver) {
			System.out.println();
			System.out.print("Do you want to quit playing?(true/false) ");
			gameOver = kb.nextBoolean();
			// If this if statement is not added, after type in "true", it will run one more time then end. 
			if (gameOver) {
				break;
			}
			// generate a random number from 1 to steps
			int randomSteps = gen.nextInt(player.getSteps()) + 1;

			if(movePlayer(randomSteps)) {
				System.out.println("Player moves " + randomSteps + " step(s) to a " + tiles[position].getType() + " tile");
				System.out.println(toString());
				tiles[position].visitTile(this);
			}

			
			if(position >= tiles.length - 1) {
				gameOver = true;
				System.out.println("Congratulation, you win!!!");
			}
			if(player.getCurrentFatigue() == player.getMaxFatigue()) {
				gameOver = true;
				System.out.println("So close, yet so far, you lose!!!");
			}
		}

		System.out.println("Thank you for playing");

	}

	// getter of player
	public Player getPlayer() {
		return player;
	}

	/*
	 * This method move player based on the random number which the computer generated. If the position is less than 0 
	 * or greater that tile.length - 1, it will return false. Otherwise, it will return true. 
	 */
	public boolean movePlayer(int distance) {
		/*
		 * This method moves the player the given distance. A positive value will move
		 * the player to the later indices in the tiles[] array, while a negative value
		 * will move the player back to lower indices.
		 * 
		 * Return false if the distance passed falls outside the range 0 -->
		 * tiles.length - 1
		 */
		position += distance;
		
		if(position < 0 || position > tiles.length - 1)
			return false;
		
		return true;
		
	}

	/*
	 * This method will based on the probability random generate the array of tile. 
	 */
	// private methods
	private void buildTiles() {
		/*
		 * This method should instantiate tile objects to fill the tiles[] array above.
		 * 10% of the tiles should be trap tiles, 20% mud, 40% grass and 30% road.
		 */

		
		Random rand = new Random();
		for(int i = 0; i < tiles.length; i++) {
			int r = rand.nextInt(10) + 1;
			if(r <= 4)
				tiles[i] = new GrassTile();
			else if(r >= 5 && r <= 7)
				tiles[i] = new RoadTile();
			else if(r == 8 || r == 9)
				tiles[i] = new MudTile();
			else if(r == 10)
				tiles[i] = new TrapTile();
		}
		
	}

	// prints out the tiles array along with the current player position
	@Override
	public String toString() {
		String result = "[";

		for (int i = 0; i < tiles.length; i++) {
			if (i != 0) {
				result += ", ";
			}

			// player is in this tile
			if (position == i) {
				result += tiles[i].toString() + " - (player)";
			} else {
				result += tiles[i].toString();
			}
		}
		result += "]";

		if (position >= tiles.length) {
			result += " (player)";
		}

		return result;
	}
}
