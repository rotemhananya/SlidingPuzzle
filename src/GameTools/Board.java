package GameTools;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.ImageIcon;

import Point.Move;
import Point.MovesStack;
import Point.Point;
import Stock.Boards;
import Stock.PicturesStock;

public class Board  {


	private final int N;
	private final int M;
	private final int imageIndex ; // 1 for cat , 2 for cyber, 3 for sushi
	private String StringImage ;
	private MovesStack stack = new MovesStack() ; // stack of moves for "Undo".

	private Point [][] array ;


	/*
	 * Constructor of the game board.
	 * 
	 */

	public Board (int sizeM, int sizeN,PicturesStock pics ,int imageIndex, Boards boards, boolean isRandom) {

		M=sizeM;
		N=sizeN ;
		this.imageIndex=imageIndex ; // image index of picture, decided by the user.
		array = new Point [M][N];
		if (imageIndex==1) // convert the index to string.
			StringImage="cat";
		else if (imageIndex==2)
			StringImage="cyber";
		else if (imageIndex==3)
			StringImage="sushi";

		if (isRandom==false) { // in case of regular game
			initBoardMatrix(boards); // initial the board for starting the game.
			initArrayIcons(pics); // initial the array of icons.
		}
		else // in case of nxn game for the bonus
		{
			initRandomArrayIcons(sizeM); // initial random game board .
		}

	}


	/*
	 * Initial an array of random indexes for the start of the game.
	 * 
	 */


	private void initRandomArrayIcons (int n) {
		int k=0 ; // an index of random numbers Array.
		Point point ;
		int [][] randomArray = randomIndexes(n);
		Random random = new Random();
		for (int i=0 ; i < array.length ; i++) {// initial the Board with new points.
			for (int j=0 ; j < array[i].length ; j++) {
				point = new Point (i,j, randomArray[i][j]); // Create each point for the array board.
				point.setX(i);
				point.setY(j);
				k++;
				array[i][j]=point; // update the array board.
			}
		}
		k=0 ;
		int randomNumber = random.nextInt(4) ; // random between 0 to 3 for ways of the empty peace .
		boolean isMoved = false ; 
		while (isMoved==false && k<5*n*n) { // if there wasn't a movement.
			randomNumber = random.nextInt(4) ; 
			isMoved= searchPeace(randomNumber); 
			if (isMoved==true)
				k++ ;
		}

	}



	/*
	 * Initial an array of indexes for the start of the game.
	 * 
	 */

	private void initBoardMatrix(Boards boards){
		int [] board = boards.getBoard(M); //getting new board from the boards stock.
		int k=0 ;
		for (int i=0 ; i<M ; i++) {
			for (int j=0 ; j<N ; j++) {
				Point point =new Point(M, N, board[k]); // Creating new point with the current index in the given board.
				array[i][j]=point ; // update the points array .
				point.setX(i); // update the point with the new position .
				point.setY(j);
				k++ ;
			}

		}
	}



	/*
	 * This method sets new icon into each point in the board.
	 * 
	 */

	private void initArrayIcons(PicturesStock pics) { // Upload points from CSV file
		ImageIcon [] images = pics.getGamePictures(N, StringImage);
		for (int i=0 ; i < M ; i++) {// initial the Board
			for (int j=0 ; j < N ; j++) {
				if (array[i][j].getIndex() != 0)		
					array[i][j].setIcon(images[array[i][j].getIndex()-1]); 
			}
		}
	}


	/*
	 * This method creates an array of shuffled indexes .
	 */

	public int [][] randomIndexes (int n) {
		int [][] randomBoard = new int [n][n]; // initial an array of indexes.
		int index =0 ;
		for (int i=0 ; i<n ; i++)
			for (int j=0 ; j<n ; j++) {
				randomBoard[i][j]=index+1; // init the array with
				index++;
			}
		randomBoard[n-1][n-1]=0; // update the right-down corner with the 0 index that represents the empty peace.

		return randomBoard ;
	}



	/*
	 * This method checks if there is a point that can 
	 * move to a given way.
	 * 
	 */

	public boolean searchPeace ( int way) {
		Point point ; 
		Move move ;
		for ( int i = 0 ; i < N ; i++){ 
			for(int j = 0; j < N ; j++ ){ 
				point = checkNeighbors( i, j, way); 
				if (point !=null ){ //if the point was founded 
					switchPeaces(point, array[i][j] ); // switch between the empty point and its neighbor.
					move = new Move(point, array[i][j]);
					stack.push(move); // update the stack with the new move.
					return true;
				}
			}
		}
		return false ;
	}


	/*
	 * Check if there is a possibility to move with the current peace.
	 * returns a peace that can move in the given way to the Empty peace.
	 * */

	private Point checkNeighbors (int x, int y, int way) { 

		if (way ==0  && x!=0 && array[x-1][y].getIndex() ==0) { // if the empty peace is up & up way
			return array[x-1][y];
		}
		else if (way==1  && y!=array[x].length-1 && array[x][y+1].getIndex() == 0) { // if the peace is in the right & right way
			return array[x][y+1];
		}
		else if (way==2  && x!=array.length-1 && array[x+1][y].getIndex() == 0) { // if the peace is down & down way
			return array[x+1][y];
		}
		else if (way==3  && y!=0 && array[x][y-1].getIndex() == 0) { // if the peace is in the left & left way
			return array[x][y-1] ;
		}
		else // in case the peace is not match to the step.
			return null; 
	}


	/*
	 * Search for legal step with a given point and make the step.
	 * Returns true if there is a valid step.
	 * 
	 */

	public boolean makeStep( int x, int y) {
		Move move ;

		if (checkNeighbors(x, y, 0)!=null) // in case the valid step is above the peace
		{
			switchPeaces(array[x][y], array[x-1][y]); // making the valid step
			move = new Move(array[x][y], array[x-1][y]);
			stack.push(move); // update stack with the new move.
			return true;
		}
		else if (checkNeighbors(x, y, 1)!=null) // in case the valid step is in the right of the peace
		{
			switchPeaces(array[x][y], array[x][y+1]); // making the valid step
			move = new Move(array[x][y], array[x][y+1]);
			stack.push(move); // update stack with the new move.
			return true;
		}
		else if (checkNeighbors(x, y, 2)!=null)  // in case the valid step is below the peace
		{
			switchPeaces(array[x][y], array[x+1][y]); // making the valid step
			move = new Move(array[x][y], array[x+1][y]);
			stack.push(move); // update stack with the new move.
			return true;
		}
		else if (checkNeighbors(x, y, 3)!=null) // in case the valid step is in the left of the peace
		{
			switchPeaces(array[x][y], array[x][y-1]); // making the valid step
			move = new Move(array[x][y], array[x][y-1]);
			stack.push(move); // update stack with the new move.
			return true;
		}
		return false ;
	}


	/*
	 * Legal switch between two points in the board.
	 * 
	 */

	private void switchPeaces (Point one , Point two) {

		ImageIcon temp = one.getIcon();
		int indexTemp = one.getIndex();

		one.pointChanged(two.getIndex(), two.getIcon());
		two.pointChanged(indexTemp, temp);

		// call to MoveStep and update the stack .
	}
	/*
	 * This method is responsible for the "undo" step in order to return to the last state of game.
	 */
	public void undo () {
		Move move =stack.pop() ; // pop previous move from the stack
		if (move!=null) { // if there is a move in the stack
			ImageIcon tempIcon = move.getPoint1().getIcon(); //save the icon in temp value in order to switch points icons.
			int tempIndex = move.getPoint1().getIndex();
			array[move.getPoint1().getX()][move.getPoint1().getY()].setIcon(move.getPoint2().getIcon());
			array[move.getPoint1().getX()][move.getPoint1().getY()].setIndex(move.getPoint2().getIndex());
			array[move.getPoint2().getX()][move.getPoint2().getY()].setIcon(tempIcon);
			array[move.getPoint2().getX()][move.getPoint2().getY()].setIndex(tempIndex);
		}
	}


	/*
	 * Returns whether the game is done .
	 * 
	 */

	public boolean isGameOver() {
		if (array[array.length-1][array[0].length-1].getIndex()==0) { // if the right-down corner contains the 0 point.
			for (int i=0; i<array.length ; i++) { 
				for (int j=0; j<array[i].length; j++) {
					Point point = array[i][j];
					if (! point.isFinalPosition(i, j)) // if the current point doesn't stand in its final position
						return false ;
				}
			}
		}
		else return false ; // if the right-down corner doesn't contain the 0 point.
		return true; // if all the points are in the final position.
	}//end isGameOver


	/*
	 * Gets & Sets
	 */

	public Point getPoint(int i, int j) {
		return array[i][j];
	}

	public int getColLength () {
		return array.length ;
	}

	public int getRowLength () {
		return array[0].length ;
	}




}