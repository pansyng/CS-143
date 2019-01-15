import java.util.*;
/**
 * This program will ask the user to enter a starting word and an ending word. Then, construct a WordLadder object and 
 * pass these two words to findLadder method. If findLadder method return false, it will print out "no word ladder was found".
 * It will ask the user to continue or not. It will continue loop the game until the user answer "n". 
 * @author Pansy
 *
 */
public class WordLadderDriver {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		char answer = 'y';
		while(answer == 'y') {
			
			System.out.print("Enter a starting word: ");
			String startingWord = console.next().toLowerCase();
			
			System.out.print("Enter an ending word: ");
			String endingWord = console.next().toLowerCase();
			
			WordLadder wl = new WordLadder();
			if(!wl.findLadder(startingWord, endingWord))
				System.out.println("no word ladder was found");
			
			System.out.print("Do you want to continue? (Y/N)");
			answer = console.next().toLowerCase().charAt(0);
		}
	}

}
