package GameTools;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.Timer;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;

import Menus.GameMenu;
import Menus.PictureAndSize;
import Stock.Boards;
import Stock.PicturesStock;
/*
 * This Class is responsible for the game frame.
 * this class implements ActionListener and extends JFrame. 
 */
public class Game extends JFrame implements ActionListener {


	public final static int ONE_SECOND = 1000;
	private JFrame frame = this;
	private Timer timer; // game timer
	private PicturesStock Pics ; //  pictures stock
	private Boards BoardStock ; //  boards stock
	private int imageIndex ; // game picture
	private boolean isGameOver = false ; 
	private int counter; // counter for the timer
	private int Steps = 0 ; // steps counter
	private JLabel steps = new JLabel("0");
	private JButton btnUndo ; // Undo button
	private GroupLayout groupLayout;
	private Board board ; // points board
	private JButton [][] btnBoard ; // Buttons array.
	private  final int ROWS ; // rows size
	private  final int COLS ; // cols size

	 /*
	  * Constructor of a new Game by given size, pictures and boards. 
	  */
	public Game(int ROWS,int  COLS, int imageIndex, PicturesStock pics, Boards boardStock, boolean isRandom) {
		this.Pics=pics ;
		this.BoardStock = boardStock ;
		this.imageIndex=imageIndex ;
		this.ROWS=ROWS;
		this.COLS = COLS ;
		board = new Board(ROWS, COLS, pics,imageIndex,boardStock, isRandom);
		btnBoard = new JButton[ROWS][COLS];
		InitializeBoard() ;
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		JLabel lblSlidingPuzzle = new JLabel("Sliding Puzzle");
		lblSlidingPuzzle.setFont(new Font("Snap ITC", Font.BOLD, 45));

		JLabel lblTimer = new JLabel("00:00:00"); // The timer begins from 00:00:00
		lblTimer.setFont(new Font("Tahoma", Font.BOLD, 23));

		JLabel lblSteps = new JLabel("Steps:");
		lblSteps.setFont(new Font("Tahoma", Font.BOLD, 23));

		JLabel labelTime = new JLabel("Timer:");
		labelTime.setFont(new Font("Tahoma", Font.BOLD, 23));

		JButton btnExit = new JButton("Exit"); // Exit button for exit from the program .
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnExit.setBackground(new Color(255, 255, 255));
		btnExit.addActionListener(new ActionListener() { // if the Exit button was clicked
			public void actionPerformed(ActionEvent arg0) {
				dispose(); // close the current window
			}
		});

		JButton btnReturn = new JButton("Return"); // Returm button for moving to the prev window.
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PictureAndSize window = new PictureAndSize(pics, boardStock) ; // call for the Pics and Size window
				dispose(); // close the current window
			}
		});
		btnReturn.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnReturn.setBackground(new Color(255, 255, 255));

		btnUndo = new JButton("Undo"); // Undo button for returning to the prev status in the game.
		btnUndo.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnUndo.setBackground(new Color(255, 255, 255));
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				board.undo(); // updating the board with the prev game status . 
				for (int i=0 ; i<ROWS; i++)
					for (int j=0 ; j<COLS ; j++) {
						btnBoard[i][j].setIcon(board.getPoint(i, j).getIcon()); // update the buttons icons.
					}
				frame.requestFocusInWindow();
			}
		});

		groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(labelTime)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblTimer))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblSteps)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(steps)))
					.addGap(19)
					.addComponent(lblSlidingPuzzle)
					.addPreferredGap(ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnUndo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnExit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnReturn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(35))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(34)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnExit)
								.addComponent(labelTime)
								.addComponent(lblTimer))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblSteps)
									.addComponent(steps))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnReturn)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnUndo))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(88)
							.addComponent(lblSlidingPuzzle)))
					.addContainerGap(529, Short.MAX_VALUE))
		);
		steps.setFont(new Font("Tahoma", Font.BOLD, 23));
		getContentPane().setLayout(groupLayout);



		ActionListener actListner = new ActionListener() { // Action listener for updating the timer label.

			@Override

			public void actionPerformed(ActionEvent event) { // Update the timer.
				counter += 1;
				lblTimer.setText(getTime(counter));
			}

		};

		timer = new Timer(1000, actListner);

		timer.start(); // start the timer game.
		
        this.addKeyListener(new KeyListener() { // Listener for the keyboard.

            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) { // key listener for playing with the keyboard.
            	boolean ismoved=false ;

        		if (e.getKeyCode() == KeyEvent.VK_UP) { //Up arrow key code
        			ismoved = board.searchPeace(0); //moving the point that is under the empty point
        		} 	
        		else if (e.getKeyCode() == KeyEvent.VK_RIGHT) { //Right arrow key code
        			ismoved = board.searchPeace(1); //moving the point that is left to the empty point, sending that point to bored.makestep

        		} 

        		else if (e.getKeyCode() == KeyEvent.VK_DOWN) { //Down arrow key code
        			ismoved = board.searchPeace(2); //moving the point that is above the empty point	
        		}
        		else if (e.getKeyCode() == KeyEvent.VK_LEFT) { //Left arrow key code
        			ismoved = board.searchPeace(3); //moving the point that is right to the empty point
        		} 
        		
        		else if ((e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Z) ) { // In case of ctrl+Z press
        			board.undo(); // updating the board with the prev game status . 
    				for (int i=0 ; i<ROWS; i++)
    					for (int j=0 ; j<COLS ; j++) {
    						btnBoard[i][j].setIcon(board.getPoint(i, j).getIcon()); // update the buttons icons.
    					}
    				frame.requestFocusInWindow(); 
        		} 

        		if (ismoved==true) { // if there was a movement
        			if (board.isGameOver()) { // if the game is over .
        				isGameOver=true ;
        				paintGameOver(); // final paint .
        			}
        			else { // if the game is not done yet .
        				paintBoard();
        				Steps++; // update the steps count .
        				steps.setText(Steps+"");

        			}
        		}
            }
        });

        this.setFocusable(true); // set focus for the keyboard.
        this.requestFocusInWindow(); 
        
        JLabel lblBackground = new JLabel(""); // the label in which the background will be shown
		lblBackground.setBounds(0, 0, 900, 800); // setting the label's bounds
		ImageIcon imBackground = pics.getPics().elementAt(4);
		Image resizedImage = imBackground.getImage().getScaledInstance(900, 800, 0); // cutting the background's image
		imBackground.setImage(resizedImage); 
		lblBackground.setIcon(imBackground); // setting the label's icon to be the background
		getContentPane().add(lblBackground); // adding the label to the frame
		validate();
		
		pack();
		setVisible(true);
		
		

	}

/*
 * This method is responsible for making new step in the board game by clicking on a button.
 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
 */

	@Override
	public void actionPerformed(ActionEvent arg0) { 
		int i=0 ;
		String temp, pointX="", pointY ;
		temp = ((JComponent) arg0.getSource()).getName()+"";
		while (temp.charAt(i)!=' ') { // searching the point that was clicked by its string name.
			pointX=pointX+temp.charAt(i);
			i++;
		}
		i++;
		pointY=temp.substring(i); 
		int x=Integer.parseInt(pointX), y=Integer.parseInt(pointY); // convert x and y to integers.
		if (board.makeStep(x, y)) { // if there is valid step
			if (board.isGameOver()) { // if the game is over.
				isGameOver=true ;
				paintGameOver(); // painting the end of game.
			}
			else { // if the game is not done yet
				paintBoard(); // updating the icons with thw new game status
				Steps++; // updating the steps count.

				steps.setText(Steps+"");

			}
		}
		this.setFocusable(true); // set focus for the keyboard.
        this.requestFocusInWindow();

	}
	/*
	 * This method paints the board images.
	 */

	private void paintBoard() {
		for (int i=0;i<ROWS;i++)
			for (int j=0;j<COLS;j++){
				btnBoard[i][j].setIcon(board.getPoint(i, j).getIcon()); // set each icon in the match place.
			}
	}

/*
 * This method paints the final icon that represent the end of the game.
 */
	private void paintGameOver () {
		Vector<ImageIcon> v = Pics.getPics() ;
		btnUndo.setVisible(false); // deleting the "undo" button from the window.
		for (int i=0 ; i<ROWS;i++)
			for (int j=0;j<COLS ; j++) {
				btnBoard[i][j].setVisible(false); // deleting the game buttons. 
			}
		JButton btnMenu = new JButton("Menu"); // new button for getting to the menu window .
		btnMenu.setFont(new Font("Tahoma", Font.PLAIN, 23));
		
		btnMenu.addActionListener(new ActionListener() { // if the Menu button was clicked.
			public void actionPerformed(ActionEvent e) {
				GameMenu menu = new GameMenu(Pics, BoardStock) ; // calling for the menu window
				dispose(); // closing the current window.
			}
		});
		btnMenu.setBounds(720, 125, 102, 40);
		getContentPane().add(btnMenu);
		
		JButton gameOver = new JButton(v.elementAt(imageIndex-1)); // creating new button for the final status.
		gameOver.setBounds(112, 160, 480, 480);
		getContentPane().add(gameOver);
		setVisible(true);
		String gameTime= getTime(counter); // getting the final time of the game.
		timer.stop(); // stop the timer
		
		GameOver end = new GameOver(Steps, gameTime, v.elementAt(3)); // calling for the GameOver window with the results.

	}
	


	/*
	 * This method initials the buttons array of the game.
	 */

	private void InitializeBoard() {
		int range=0 ; // range for each button in the game. 
		if(this.ROWS==3)
			range=150; // range of 3x3 game
		else if (this.ROWS==4)
			range=113 ; // range of 4x4 game
		else if (this.ROWS==5)
			range=90; // range of 5x5 game
		int xPrev,yPrev=170;
		JButton btnB; // Creating new button for initial the board with buttons.
		for(int i=0; i<this.ROWS; i++) // updating the buttons bounds and icon.
		{
			xPrev=160;

			for(int j=0;j<this.COLS; j++)
			{
				btnB = new JButton("");
				btnB.setName(i+" "+j); //set name on each button with the current button place in the array.
				btnB.setIcon(board.getPoint(i, j).getIcon()); // set to the button new icon 
				btnB.setBounds(xPrev, yPrev, range, range);
				xPrev+=range;	//update the place on window for the next button
				btnB.addActionListener(this); // add action listener for each button 
				getContentPane().add(btnB); // add the button in the content pane.
				btnBoard[i][j] = btnB; // updating the matrix with the new button in the right place.
			}

			yPrev+=range ; //update the place on window for the next button
		}
	}


	/*
	 * Calculate the timer and return a string that represents timer
	 * by the formula: HH:MM:SS
	 * 
	 * @param numSec - the seconds count
	 * @return string that represents the time.
	 */

	static String getTime(int numSec) {
		//Separate timer to hours, minutes and seconds . 
		int hours = 0;
		int checkMinSec = 0;
		int minutes = 0;
		int seconds = 0;

		if (numSec >= 3600) // in case of more then hour .   
		{
			hours = numSec / 3600;        // update the hours value.
			checkMinSec = numSec % 3600;  // calculate the time that rest

			if (checkMinSec >= 60)   //in case of more then a minute.
			{
				minutes = checkMinSec / 60; // update the minutes value.
				seconds = checkMinSec % 60;  // update the seconds value. 
			}
			else // in case of seconds (less then a minute)
			{                       
				seconds = checkMinSec; // update the seconds value.
			}
		}
		// in case of more then a minute.
		else if (numSec >= 60)                
		{
			hours = 0;              
			minutes = numSec / 60;
			seconds = numSec % 60;
		}
		//in case of seconds only.
		else if (numSec < 60)
		{
			hours = 0;
			minutes = 0;
			seconds = numSec;
		}
		//convert the time to string

		String strHours;
		String strMins; 
		String strSecs; 

		if(seconds < 10) // in case of one digit of seconds
			strSecs = "0" + Integer.toString(seconds);
		else // in case of more then one digit of seconds
			strSecs = Integer.toString(seconds);

		if(minutes < 10)// in case of one digit of minutes
			strMins = "0" + Integer.toString(minutes);
		else // in case of more then one digit of minutes
			strMins = Integer.toString(minutes);

		if(hours < 10) // in case of one digit of hours
			strHours = "0" + Integer.toString(hours);
		else // in case of more then one digit of hours
			strHours = Integer.toString(hours);


		String time = strHours + ":" + strMins + ":" + strSecs; // string to return
		return time;
	}
	
}
