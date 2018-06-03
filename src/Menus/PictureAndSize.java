
package Menus;

import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionListener;

import GameTools.Game;
import Stock.Boards;
import Stock.PicturesStock;

import javax.swing.event.ListSelectionEvent;
import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import javax.swing.JScrollPane;

/*
 * This class represents the first window the user sees.
 * This class extends JFrame and implements ActionListener.
 * 
 */
public class PictureAndSize extends JFrame implements ActionListener{
	private PicturesStock pics;
	private Boards boardStack;
	private JLabel lblGamesSize;
	private JRadioButton b3;
	private JRadioButton b4;
	private JRadioButton b5;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblGamesPicture;
	private JScrollPane scrollPane;
	private JButton chosenImage;
	private JList list;
	private JButton btnReturn;
	private JButton btnStartGame;

	/* PictureAndSize constructor.
	 * 
	 * @param pics - a vector of all of the images
	 * @param boardStack - vectors by the given boards in CSV file.  
	 */
	public PictureAndSize(PicturesStock pics, Boards boardStack) {
		super("Start Game"); // initiating the frame
		this.pics = pics; // all of the pictures
		this.boardStack = boardStack;
		getContentPane().setBackground(SystemColor.inactiveCaptionBorder); //setting the background color 
		getContentPane().setLayout(null);

		lblGamesSize = new JLabel("Game's size:"); // "Game's size:" label
		lblGamesSize.setFont(new Font("Tahoma", Font.BOLD, 22)); // setting the font and the size
		lblGamesSize.setBounds(40, 40, 140, 40);
		getContentPane().add(lblGamesSize); // adding "Game's size:" to the frame

		b3 = new JRadioButton("3X3"); // "3X3" radio button
		buttonGroup.add(b3); // adding "3X3" to the button group
		b3.setSelected(true); // setting "3X3" as selected, so there will always a selected button
		b3.setFont(new Font("Tahoma", Font.BOLD, 22)); // setting the font, the background and the size
		b3.setContentAreaFilled(false);//.setBackground(SystemColor.inactiveCaptionBorder);
		b3.setBounds(200, 40, 100, 40);
		getContentPane().add(b3); // adding "3X3" to the frame

		b4 = new JRadioButton("4X4"); // same as "3X3"
		buttonGroup.add(b4);
		b4.setFont(new Font("Tahoma", Font.BOLD, 22));
		b4.setContentAreaFilled(false);
		b4.setBounds(300, 40, 100, 40);
		getContentPane().add(b4);

		b5 = new JRadioButton("5X5"); // same as "3X3"
		buttonGroup.add(b5);
		b5.setFont(new Font("Tahoma", Font.BOLD, 22));
		b5.setContentAreaFilled(false);
		b5.setBounds(400, 40, 100, 40);
		getContentPane().add(b5);

		lblGamesPicture = new JLabel("Game's image:"); // "Game's image:" label
		lblGamesPicture.setFont(new Font("Tahoma", Font.BOLD, 22)); // setting the font and the size
		lblGamesPicture.setBounds(40, 100, 165, 40);
		getContentPane().add(lblGamesPicture); // adding "Game's image:" to the frame

		scrollPane = new JScrollPane(); // a scroll pane for the list
		scrollPane.setBounds(220, 100, 165, 130);
		getContentPane().add(scrollPane); // adding the scroll pane to the frame

		chosenImage = new JButton("");
		chosenImage.setBounds(400, 100, 165, 165);
		chosenImage.setBorder(BorderFactory.createEmptyBorder());
		chosenImage.setContentAreaFilled(false);
		getContentPane().add(chosenImage);
		
		list = new JList(); // a list the will hold the pictures' options
		list.setFont(new Font("Tahoma", Font.BOLD, 22)); // setting the list's font
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Random", "Cat", "Cyber", "Sushi"}; // the list's values
			public int getSize() { // the size of the list
				return values.length;
			}
			public Object getElementAt(int index) { // returns the element in index
				return values[index];
			}
		});
		list.setSelectedIndex(0); // selecting random, so there will always be a selected image
		this.validate();

		scrollPane.setViewportView(list); // adding scrollPane to the list

		list.addListSelectionListener(new ListSelectionListener() { // adding an action listener in order to change the image shown in chosenImage label 
			public void valueChanged(ListSelectionEvent arg0) {
				ImageIcon im;
				Image resizedImage;
				if (list.getSelectedIndex()==0) { // if random was chosen
					chosenImage.setContentAreaFilled(false);
					getContentPane().add(chosenImage); 
					validate();
				}
				else if (list.getSelectedIndex()==1) { // if cat was chosen
					im = pics.getPics().get(0); // saving the cat's image
					resizedImage = im.getImage().getScaledInstance(165, 165, 0); // cutting the cat's image
					im.setImage(resizedImage);
					chosenImage.setIcon(im); // changing chosenImage's icon
					chosenImage.setContentAreaFilled(true);
					getContentPane().add(chosenImage); 
					validate();
				}
				else if (list.getSelectedIndex()==2) { // if cyber was chosen
					im = pics.getPics().get(1); // saving the cat's image
					resizedImage = im.getImage().getScaledInstance(165, 165, 0); // cutting the cat's image
					im.setImage(resizedImage);
					chosenImage.setIcon(im); // changing chosenImage's icon
					chosenImage.setContentAreaFilled(true);
					getContentPane().add(chosenImage); 
					validate();
				}
				else {// if sushi was chosen
					im = pics.getPics().get(2); // saving the cat's image
					resizedImage = im.getImage().getScaledInstance(165, 165, 0); // cutting the cat's image
					im.setImage(resizedImage);
					chosenImage.setIcon(im); // changing chosenImage's icon
					chosenImage.setContentAreaFilled(true);
					getContentPane().add(chosenImage); 
					validate();
				}
			}
		});

		btnReturn = new JButton("Return"); // the button that takes the user back to game menu
		btnReturn.setFont(new Font("Tahoma", Font.BOLD, 22)); // the font and size of "Return"
		btnReturn.setBounds(40, 292, 165, 40);
		btnReturn.setBackground(new Color(255, 255, 255));

		getContentPane().add(btnReturn);
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameMenu g= new GameMenu(pics, boardStack);
				dispose();
			}
		});
		
		btnStartGame = new JButton("Start Game"); // the button that starts the game
		btnStartGame.addActionListener(new ActionListener() { //if the button is pressed
			public void actionPerformed(ActionEvent e) {
				String selectedSizeString; // will hold the string inside the chosen radio button
				int selectedSizeInt;

				if (b3.isSelected()) // if 3X3 is selected
					selectedSizeString = b3.getText();
				else if (b4.isSelected()) // if 4X4 is selected
					selectedSizeString = b4.getText();
				else // if 5X5 is selected
					selectedSizeString = b5.getText();

				selectedSizeString = ""+selectedSizeString.charAt(0); // saving only the first number in the string
				selectedSizeInt = Integer.parseInt(selectedSizeString); // turning the first number in the string to an int

				int selectedImage; // will hold the index of the selected image
				if (list.getSelectedValue().equals("Cat")) // if cat was chosen
					selectedImage= 1;
				else if (list.getSelectedValue().equals("Cyber")) // if cyber was chosen
					selectedImage= 2;
				else if (list.getSelectedValue().equals("Sushi")) // if sushi was chosen
					selectedImage= 3;
				else { // if random was chosen
					Random rand = new Random(); 
					selectedImage = rand.nextInt(3) + 1; // getting a number from 1 to 3 (minimum 1, maximum 3)
				}

				if ( (boardStack.getBoards3x3Size()==0 && selectedSizeInt==3) || 
						(boardStack.getBoards4x4Size()==0 && selectedSizeInt==4) ||
						(boardStack.getBoards5x5Size()==0 && selectedSizeInt==5) ){ // if there isn't a board by the chosen size in the csv file 
					JOptionPane.showMessageDialog(getContentPane(),
							"The chosen size is unavailable, please choose a different size", 
							"Unavailable size",
							JOptionPane.ERROR_MESSAGE); // asking the user to choose a different size
				}
				else {
					Game game = new Game(selectedSizeInt, selectedSizeInt, selectedImage, pics, boardStack, false ); // initiating the game
					dispose(); // closing the window
				}
			}
		});
		btnStartGame.setFont(new Font("Tahoma", Font.BOLD, 22)); // the font and size of "Start game"
		btnStartGame.setBounds(400, 292, 165, 40);
		btnStartGame.setBackground(new Color(255, 255, 255));
		getContentPane().add(btnStartGame);
		
		JLabel lblBackground = new JLabel(""); // the label in which the background will be shown
		lblBackground.setBounds(0, 0, 634, 407); // setting the label's bounds
		ImageIcon imBackground = pics.getPics().elementAt(4);
		Image resizedImage = imBackground.getImage().getScaledInstance(634, 407, 0); // cutting the background's image
		imBackground.setImage(resizedImage); 
		lblBackground.setIcon(imBackground); // setting the label's icon to be the background
		getContentPane().add(lblBackground); // adding the label to the frame
		validate();
		
		setBounds(0, 0, 634, 407);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // in order to stop the program when the user closes the window
		setResizable(false);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}

