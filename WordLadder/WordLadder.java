import java.util.*;
import java.io.*;
/**
 * This class construct a words list which contain all words from the words.txt. There is a ladder list which will have the word
 * that is one difference between the currentWord. Steps is counting how many words needed to achieve the endWords. 
 * @author Pansy
 *
 */
public class WordLadder{
	//Fields
	private List<String> words;
	private List<String> ladder;
	private int steps;
	
	//This is a constructor. It reads each word in the file and adding it into words list.
	public WordLadder() {
		words = new ArrayList<String>();
		
		try(Scanner fileIn = new Scanner(new File("files/words.txt"))){
			while(fileIn.hasNext()) {
				String word = fileIn.next();
				words.add(word);
			}
		}catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		ladder = new ArrayList<String>();
		steps = 0;
	}
	
	/*
	 * It takes two String, a startWord and an endWord and clears the ladder. Besides, it will remove the word if it longer than
	 * length of the endWord.
	 */
	public boolean findLadder(String startWord, String endWord) {
		Iterator<String> itr = words.iterator();
		while(itr.hasNext()) {
			String str = itr.next();
			if(str.length() != endWord.length())
				itr.remove();
		}
		
		ladder.clear();
		ladder.add(startWord);
		
		return findLadder(startWord, endWord, steps);
	}
	
	/*
	 *It takes two Strings currentWord and an endWord. The currentWord changes with each call to the recursive private findLadder
	 *method. There is two base case: 1. If the currentWord and endWord are the same, then return true. 2. If it reached the end 
	 *of the word list, then return false. The recursive case: changing one letter at a time until currentWord is equal to 
	 *endWord. 
	 */
	private boolean findLadder(String currentWord, String endWord, int steps) {
		if(currentWord.equals(endWord)) {
			System.out.println("Success");
			this.printList(ladder);
			return true;
		}
		
		else if(ladder.contains(words.get(words.size() - 1))) {
			System.out.println("no word ladder was found");
			return false;
		}
		
		else {
			for(String word: words) {
				if(this.oneDiff(currentWord, word) && !ladder.contains(word)) {
						ladder.add(word);
						steps++;
						if(findLadder(word, endWord, steps))
							return true;
				} 
			}
		}
		ladder.remove(ladder.size() - 1);
		return false;
	}
	
	/*
	 * It takes a list of Strings and prints it.
	 */
	public void printList(List<String> list) {
		for(String str: list) {
			System.out.println(str);
		}
	}
	
	/*
	 * It takes a word and a list of Strings. If the list contains the word, it returns true. Otherwise, it returns false.
	 */
	public boolean inList(String word, List<String> list) {
		if(list.contains(word))
			return true;
		return false;
	}
	
	/*
	 * It takes two strings. If both strings are the same length, then it compares every letter in both strings. The method 
	 * returns true if there is exactly one difference between the strings.
	 */
	public boolean oneDiff(String word1, String word2) {
		int countDiff = 0;
		
		if(word1.length() == word2.length()) {
			for(int i = 0; i < word1.length(); i++) {
				if(word1.charAt(i) != word2.charAt(i))
					countDiff++;
			}
			return countDiff == 1;
		}
		
		return false;
	}
}
