//Pui Sze Pansy Ng
// CS 143
// Homework 2: HTMLValidator 
import java.util.*;
/**
 * This class construct a queue, which passed from other class, that can add, remove, and 
 * fix HTML tags inside the queue.
 * @author Pansy
 *
 */
public class HTMLManager {
  
	private Queue<HTMLTag> queue;
	
	/**
	 * This is the constructor with parameter of queue which create the empty set of queue.
	 * @param page 
	 * Throws IllegalArgumentException if queue passed is null
	 * 
	 */
	public HTMLManager(Queue<HTMLTag> page) {
		if(page == null) 
			throw new IllegalArgumentException();
		
		else 
			queue = new LinkedList<HTMLTag>(page);
	}
	
	/**
	 * This method adds HTML tag into the queue
	 * Throws IlleagalArgumentException if tag is null.
	 * @param tag to be added into the queue
	 */
	public void add(HTMLTag tag) {
		if(tag == null) 
			throw new IllegalArgumentException("null");
		
		else
			queue.add(tag);
	}
	
	/**
	 * This method remove all the specific HTML tags from the queue 
	 * Throws IllegalArgumentException if tag is null
	 * @param tag(s) to be removed from the queue
	 */
	public void removeAll(HTMLTag tag) {	
		if(tag == null) 
			throw new IllegalArgumentException("null");
		
		else {
			Iterator<HTMLTag> itr = queue.iterator();
			
			while(itr.hasNext()) {
				HTMLTag tag1 = itr.next();
				
				if(tag.equals(tag1)) 
					itr.remove();
			}
		}
	}
	
	/**
	 * This is the getter that allows to other class to print the queue
	 * @return queue
	 */
	public Queue<HTMLTag> getTags(){
		return queue;
	}
	
	/*
	 * This method fix the queue that each opening tag is corresponding to a closing tag. 
	 * It will go over each elements in the queue. If the tag is a Self-Closing tag, it will 
	 * remove the tag and add it to the back of the queue. If the tag is a opening tag, it 
	 * will remove from the queue, and add it to the stack and queue. If the tag is a closing tag, 
	 * an element of the stack will be removed and check is it match with the element of the queue. 
	 * If true, that element will be removed and add it to the back of the queue. If not, it will 
	 * add a new tag to the queue which matches to the opening tag. After go through each elements 
	 * inside the queue, it will check is the stack is empty or not. If the stack is not empty, it
	 * will generate a corresponding closing tag to the back of the queue.   
	 */
	public void fixHTML() {
		Stack<HTMLTag> stack = new Stack<HTMLTag> ();
		int size = queue.size();
		
		while(size != 0) {
			if(queue.peek().isSelfClosing()) 
				queue.add(queue.remove());
			
			else if(queue.peek().isOpening()) {
				stack.push(queue.peek());
				queue.add(queue.remove());	
			}
			
			else if(queue.peek().isClosing()) {
				if(stack.isEmpty()) 
					queue.remove();
				
				else {
					HTMLTag tag1 = stack.pop();
					
					if(tag1.matches(queue.peek()))
						queue.add(queue.remove());
					
					else if(!tag1.matches(queue.peek())) {
						queue.remove();
						queue.add(tag1.getMatching());
					}
				}
			} 
			size--;
		}
		
		while(!stack.isEmpty()) {
			queue.add(stack.pop().getMatching());
		}
		
	}
}
