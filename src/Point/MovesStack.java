package Point;

/*
 * This class represents a stack, that will hold the moves that were made during the game.
 * 
 */

public class MovesStack { 
	private Move head; // the last move that was made
	private Move tail; // the first move that was made

	/* 
	 * MovesStack's first constructor.
	 */

	public MovesStack () {
		this.head = null; 
		this.tail = null; 
	}

	 /* MovesStack's second constructor.
	 * 
	 * @param m - a move that was made.
	 */
	
	public MovesStack (Move m) {
		this.head = m; // m is the only move so it's both the head and the tail 
		this.tail = m; 
		this.head.setNext(null); // their is only one move, so it doesn't have a prev and a next
		this.head.setPrev(null);
		this.tail.setNext(null);
		this.tail.setPrev(null);
	}
	
	/* This function pushes a new move into the stack
	 * 
	 * @param m - a move that was made.
	 */
	
	public void push(Move m) {
		if(this.head==null) { // if m is the first move
			this.head=m;
			this.tail=m;
		}
		else { // if there's more than one move
			Move prevHead = this.head; // saving the previous head
			prevHead.setNext(m); // saving the previous head's next to be the new move that was made
			this.head=m; // setting the new head to be the new move that was made
			this.head.setPrev(prevHead); // setting the new head's prev to be the previous head
			this.head.setNext(null); // setting the new head's next to be null
		}
	}
	
	/* 
	 * This function pops/deletes the last move that was made, from the stack.
	 */
	
	public Move pop() {
		Move prevHead=this.head;  // saving the previous head in order to return it 
		if (this.head==null) //if there aren't any moves
			return prevHead;
		else if(this.head.equals(this.tail)) { // if tail==head, there's only one move
			this.head=null;	 // both the head and the tail are null, because the only move was popped
			this.tail=null;
		}
		else { //if there's more than one move
			this.head=this.head.getPrev(); // setting the new head to be the previous head's next
			this.head.setNext(null); // setting the new head's next to be null
		}
		return prevHead; // returning the move that was popped
	}

}
