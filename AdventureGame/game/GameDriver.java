// Pui Sze Pansy Ng
// CS 143
// Individual Assignment 4 - Adventure Program 

package game;


import java.util.Scanner;

import players.*;
/**
 * This program is the client program. This program will use the Game class, all classes from the players, all classes 
 * from the tiles, to run. 
 * @author Pansy
 *
 */
public class GameDriver {
	private static Scanner console = new Scanner(System.in);


	public static void main(String[] args) {
		
		System.out.println("Welcome to the Longest Adventure.");
		System.out.println("*********************************");

		
		System.out.print("Please choose a number of tiles: ");
		int tiles = console.nextInt();
		console.nextLine(); // clear enter key from integer input

		System.out.print("Enter your player name: ");
		String name = console.nextLine();

		System.out.println("Please choose a player type (knight, princess, squire):");
		System.out.println("Knight - Tough travelers that can weather any adversity!");
		System.out.println("Princess - Smart and savvy, a princess is never caught off guard!");
		System.out.println("Squire - Squires have a knack for getting there quicker!");
		System.out.print("Enter your player type: ");
		String type = console.nextLine();

		
		// send in the number of tiles and a new player object here
		// use the type entered to determine which object to construct 
		// Princess, Knight, or Squire ex. 
		// Game game = new Game(tiles, new Princess(name));
		// you will need an if or switch statement
		
		/*
		 * when an user enter a string of player type, it will become lower case. If the player type equals to princess, 
		 * it will construct a princess object. Same when enter knight and squire.
		 */
		Game game = new Game(0, null);
		if(type.toLowerCase().equals("princess")) { 
			game = new Game(tiles, new Princess(name));
		}
		else if(type.toLowerCase().equals("knight")) {
			game = new Game(tiles, new Knight(name));
		}
		else if(type.toLowerCase().equals("squire")) {
			game = new Game(tiles, new Squire(name));
		}

		//call startGame method from the Game class
		game.startGame();
		
		console.close();
	}
}
