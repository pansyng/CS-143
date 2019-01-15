// Pui Sze Pansy Ng
// CS 143
// Assignment 6: 20 Questions

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This class uses the class named QuestionNode to construct overallRoot and Scanner console. 
 * This class allows the QuestionMain.java to call the methods. readTree method is to read each line in the 
 * document and construct the tree. writeTree method is to write tree into the txt file. play method  
 * @author Pansy
 */
public class QuestionsGame {
	//fields 
	private QuestionNode overallRoot;
	private static Scanner console;
	
	//constructor 
	public QuestionsGame(String object) {
		//instantiate the console 
		console = new Scanner(System.in);
		//instantiate the overallRoot
		overallRoot = new QuestionNode(object);
	}
	
	//constructor
	public QuestionsGame(Scanner fileIn) {
		 //instantiate the console 
		console = new Scanner(System.in);
		//call recursive readTree method
		overallRoot = readTree(fileIn);
	}
	
	// QuestionsGame(Scanner fileIn) recursive helper method
	private QuestionNode readTree(Scanner input) {
		//Declare a QuestionNode named root
		QuestionNode root = null;
		//if there are lines in the input file
		if(input.hasNextLine()) {
			//read in a line 
			String typeLine = input.nextLine();
			//if the line begins with "A"
			if(typeLine.startsWith("A")) {
				//read in the next line from the file and construct a QuestionNode with the answer and assign to root
				root = new QuestionNode(input.nextLine());
			}
			//else the line begins with "Q:"
			else if(typeLine.startsWith("Q:")) {
				//read in the next line from the file and construct a QuestionNode with the question and assign to root
				root = new QuestionNode(input.nextLine());
				//call recursive readTree for root.left
				root.left = readTree(input);
				//call recursive readTree for root.right
				root.right = readTree(input);
			}
		}
		//return the root
		return root;
	}
	
	/**
	 * This method call on the writeTree recursive method. 
	 * throw IllegalArgumentException if the PrintWriter is null
	 * @param fileOut
	 */
	public void saveQuestions(PrintWriter fileOut) {
		if(fileOut == null)
			throw new IllegalArgumentException();
		//call the writeTree recursive method
		writeTree(fileOut, overallRoot);
	}
	
	// saveQuestions recursive helper method
	private void writeTree(PrintWriter output, QuestionNode root) {
		//if root is not null
		if(root != null) {
			//if root is a question 
			if(root.isQuestion()) {
				//write Q: to file 
				output.println("Q:");
			}
			//else root is an answer
			else{	
				//write A: to file 
				output.println("A:");
			}
			//write root.data to file 
			output.println(root.data);
			//call writeTree for root.left
			writeTree(output, root.left);
			//call writeTree for root.right
			writeTree(output, root.right);
		}
	}
	
	
	// This is play method to call on recursive play helper method.   
	public void play() {
		//call recursive play method in x = change(x)
		overallRoot = play(overallRoot);
	}
	
	// play recursive helper method
    private QuestionNode play(QuestionNode root) {
    	//if root is not null
    	if(root != null) {
    		//if the node is a question
    		if(root.isQuestion()) {
    			//display question in console
    			System.out.print(root.data + " (y/n)? ");
    			
    			//if users replies yes 
    			if(console.nextLine().trim().toLowerCase().startsWith("y"))
    				//call recursive play method for root.left(yes is always the left subtree)
    				root.left = play(root.left);
    			//else user replies no
    			else
    				//call recursive play method for root.right(no is always the right subtree)
    				root.right = play(root.right);
    		}
    		
    		//else node is an answer - may need to modify tree
    		else if(!root.isQuestion()){
    			//display "I guess that your object is ____! Am I right? (y/n)?"
    			System.out.print("I guess that your object is "+ root.data + "! \nAm I right? (y/n)? ");
    			
    			//if user replies yes 
    			if(console.nextLine().trim().toLowerCase().startsWith("y"))
    				//display "Awesome! I win!"
    				System.out.println("Awesome! I win!");
    			//else user replies no - need to modify tree
    			else {
    				//display question "Boo! I Lose. Please help me get better! What is your object?"
    				System.out.print("Boo! I Lose. Please help me get better! \nWhat is your object? ");
    				//read in newAnswer and construct a new QuestionNode 
    				QuestionNode newAnswerNode = new QuestionNode(console.nextLine());
    				//display "Please give me a yes/no question that distinguishes between ____ and ____"
    				System.out.println("Please give me a yes/no question that distinguishes between " + root.data + " and " + newAnswerNode.data);
    				System.out.print("Q: ");
    				//read in newQuestion and construct a new QuestionNode 
    				QuestionNode newQuestionNode = new QuestionNode(console.nextLine());
    				//display question "Is the answer "yes" for ______? (y/n)?"
    				System.out.print("Is the answer \"yes\" for " + newAnswerNode.data + "? (y/n)?");
    				//if user replies yes, 
    				if(console.nextLine().trim().toLowerCase().startsWith("y")) {
    					//assign the newAnswer QuestionNode to the newQuestion QuestionNode's LEFT and 
    					newQuestionNode.left = newAnswerNode;
    					//assign the original answer node (root) to the newQuestion QuestionNode's RIGHT
    					newQuestionNode.right = root;
    				}
    				//else user replies no, 
    				else {
    					//assign the newAnswer QuestionNode to the newQuestion QuestionNode's RIGHT and 
    					newQuestionNode.right = newAnswerNode;
    					//assign the original answer node(root) to the newQuestion QuestionNode's LEFT
    					newQuestionNode.left = root;
    				}
    				//assign newQuestion QuestionNode to the root passed into this recursive call
    				root = newQuestionNode;
    			}
    		}
    	}
    	//return the root 
        return root;
    }	
}
