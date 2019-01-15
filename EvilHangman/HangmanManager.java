// Pui Sze Pansy Ng
// CS 143
// Homework 3: Evil Hangman 
import java.util.*;
/**
 * This class constructs an integer for counting the how many guesses left, a set of words which contains all words from the
 * dictionary, a set of letters which record your guesses, and a String for the pattern which will changed during the game. 
 * @author Pansy
 *
 */
public class HangmanManager {
	private int guessesLeft;
	private Set<String> words;
	private Set<Character> letters;
	private String pattern;
	
	/**
	 * It will throw an IllegalArgumentException() when the length less than 1 and max is less than 0
	 * 
	 * At first, the users is required to type in the length of words that they would like to play. Then, use the length of words
	 * to pick the equal-length words from the dictionary.txt and store in the set of words.
	 * 
	 * Set the guessesLeft as max 
	 * 
	 * Create the initial pattern. If length of words is four, it will show four dashes "----". 
	 * @param dictionary - list contains all words 
	 * @param length - desired words length type in by the user
	 * @param max - the number of max guesses
	 */
	public HangmanManager(List<String> dictionary, int length, int max) {
		if(length < 1 || max < 0)
			throw new IllegalArgumentException();
		words = new TreeSet<String>();
		
		for(String s: dictionary) {
			if(s.length() == length)
				words.add(s);
		}
		guessesLeft = max;
		letters = new TreeSet<Character>();
		
		pattern = "";
		for(int i = 0; i < length; i++) {
			pattern += "- ";
		}
	}
	
	/**
	 * set of words pull out from the dictionary which has the same length of words as the user typed in.
	 * @return Set<String> words
	 */
	public Set<String> words(){
		return words;
	}
	
	/**
	 * integer of how many guesses left 
	 * @return maximum guesses minus the size of the set<Character> letters
	 */
	public int guessesLeft() {
		return guessesLeft - letters.size();
	}
	
	/**
	 * set of characters that the user has been guessed 
	 * @return all letters that user has been typed in to guess
	 */
	public Set<Character> guesses(){
		return letters;
	}
	
	/**
	 * Throws an IllegalStateException() if the set of words is empty   
	 * @return pattern without spaces
	 */
	public String pattern() {
		if(words.isEmpty())
			throw new IllegalStateException();
		return pattern.trim();
	}
	
	/**
	 * This method count how letter the user guessed
	 * Throws IllegalStateException() if guessesLeft less than 1 or words set is empty
	 * Throws IllegalArgumentException() is words set is not empty and letters set contains guess. 
	 * @param guess - letter that user type in to guess
	 * @return count - how many count that the user guess it right 
	 */
	public int record(char guess) {
		if(guessesLeft < 1 || words.isEmpty())
			throw new IllegalStateException();
		if(!words.isEmpty() && letters.contains(guess))
			throw new IllegalArgumentException();
		Map<String, Set<String>> map = new TreeMap<String, Set<String>> ();
		
		changePattern(map, guess);
		pick(map);
		
		int count = 0; 
		for(int i = 0; i < pattern.length(); i++) {
			if(pattern.charAt(i) == guess)
				count++;
		}
		if(count > 0)
			guessesLeft++;
		letters.add(guess);
		return count;
	}

	/**
	 * This method picks the greatest number of size 
	 * @param map - changed from changePattern
	 */
	private void pick(Map<String, Set<String>> map) {
		int pickSize = 0;
		for(String pattern: map.keySet()) {
			Set<String> family = map.get(pattern);
			if(family.size() > pickSize) {
				pickSize = family.size();
				words = family;
				this.pattern = pattern;
			}
		}
	}
	
	/**
	 * Using Iterator to go through each element inside the list of words. Then, go through each letter inside the words using 
	 * for loop. If that letter is equals to the letter which user guessed, we will change the pattern to dashes and that letter.
	 * Set this new pattern as a key and the corresponding words as a value. If the pattern is already exists, it will add the word
	 * to the set.
	 * @param map - passed in a empty set of map. Since map is change by reference, so no need to return. 
	 * @param guess - letter that the user guess passed in from the record method.
	 */
	private void changePattern(Map<String, Set<String>> map, char guess) {
		Iterator<String> itr = words.iterator();
		
		while(itr.hasNext()) {
			Set<String> remainWords = new TreeSet<String>();
			String word = itr.next();
			String pattern = this.pattern;
			for(int i = 0; i < word.length(); i++) {
				if(word.charAt(i) == guess)
					pattern = pattern.substring(0, 2*i) + guess + pattern.substring(2*i +1);
			}
			if(!map.containsKey(pattern))
				map.put(pattern, remainWords);
			remainWords = map.get(pattern);
			remainWords.add(word);
		}
	}
}
