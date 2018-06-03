package Point;

import javax.swing.ImageIcon;

public class Point {
	
	private int imageIndex ;
	private ImageIcon icon ;
	private int M,N ;
	private int x,y ;

	/*
	 * Constructor of point that represents a peace in the game
	 * @param M - the board game count of Rows.
	 * @param N - the board game count of Cols.
	 * @param index - image index of the current point  
	 */
	public Point(int M, int N, int index) {
		ImageIcon im=null;
		this.icon=im;
		this.imageIndex=index;
		this.M=M ;
		this.N= N ;
	}
	
	/*
	 * This method sets new location for the current point.
	 * @param - imageindex - new image index 
	 * @param icon - new image icon 
	 */
	public void pointChanged (int imageIndex, ImageIcon icon) {
		
		this.imageIndex=imageIndex;
		this.icon=icon;
	}
	
	/*
	 * This method returns if the current point's location is the final location.
	 * @param i - the x value of the location
	 * @param j - the y value of the location
	 * @return if the point location equals to the given location.
	 */
	public boolean isFinalPosition ( int i, int j) {
		if (imageIndex==0) // in case of the empty point
			return (i==M-1 &&  j==N-1) ;
		else  // in case of other points.
			return (i*N + j+1 == imageIndex) ;
	}
	
	/*
	 * Gets and Sets
	 */
	public int getIndex() {return imageIndex;}
	public void setIndex(int i) {this.imageIndex=i;}
	public ImageIcon getIcon() {return icon;}
	public void setIcon(ImageIcon icon) { this.icon=icon;}
	public void setX(int x) {this.x=x;}
	public int getX() {return x;}
	public void setY(int y) {this.y=y;}
	public int getY() {return y;}

}
