package Point;

/*
 * This class represents one move in the game.
 */


public class Move {
	private Point point1; // the point in which the moves starts
	private Point point2; // the point in which the moves ends
	private Move next; // the move after this move
	private Move prev; // the move before this move

	/* Move's constructor. 
	 * 
	 * @param p1 - the point in which the moves starts.
	 * @param p2 - the point in which the moves ends.
	 */

	public Move(Point p1, Point p2) {
		this.point1=p1;
		this.point2=p2;
	}

	/*
	 * point1, point2, next & prev 's getters and setters. 
	 */

	public void setNext (Move m) {
		this.next=m;
	}

	public void setPrev (Move m) {
		this.prev=m;
	}

	public Move getNext () {
		return this.next;
	}

	public Move getPrev () {
		return this.prev;
	}

	public Point getPoint1 () {
		return this.point1;
	}

	public Point getPoint2 () {
		return this.point2;
	}

	public void setPoint1 (Point p) {
		this.point1 = p;
	}

	public void setPoint2 (Point p) {
		this.point2 = p;
	}

}
