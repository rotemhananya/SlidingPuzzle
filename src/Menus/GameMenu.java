package Menus;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import javax.swing.LayoutStyle.ComponentPlacement;

import Stock.Boards;
import Stock.PicturesStock;

public class GameMenu extends JFrame implements ActionListener {

	
	public GameMenu (PicturesStock pics, Boards boards) {
		Vector<ImageIcon> v = pics.getPics() ;
		ImageIcon im = v.elementAt(4); // image icon for the BackGround.
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // default operation for exiting the program.
		

		JButton btnExit = new JButton("Exit"); // // creating an Exit button
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnExit.setBackground(new Color(255, 255, 255));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // close the current window.
			}
		});
		
		JButton btnGameSize = new JButton("Start"); // creating Size and Picture button
		btnGameSize.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnGameSize.setBackground(new Color(255, 255, 255));
		btnGameSize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // if the user clicked the Size and Picture button
				PictureAndSize window = new PictureAndSize(pics, boards) ; // call for the Pics and Size window
				dispose(); // close the current window.
			}
		});
		
		JLabel lblWelcomeToSliding = new JLabel("Welcome To Sliding Puzzle");
		lblWelcomeToSliding.setFont(new Font("Stencil", Font.BOLD, 30));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(308)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnExit, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnGameSize, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(138)
							.addComponent(lblWelcomeToSliding)))
					.addContainerGap(150, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(79, Short.MAX_VALUE)
					.addComponent(lblWelcomeToSliding)
					.addGap(56)
					.addComponent(btnGameSize, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addGap(113))
		);
		getContentPane().setLayout(groupLayout);
		
		JLabel lblBackground = new JLabel(""); // the label in which the background will be shown
		lblBackground.setBounds(0, 0, 760, 500); // setting the label's bounds
		ImageIcon imBackground = pics.getPics().elementAt(4);
		Image resizedImage = imBackground.getImage().getScaledInstance(760, 500, 0); // cutting the background's image
		imBackground.setImage(resizedImage); 
		lblBackground.setIcon(imBackground); // setting the label's icon to be the background
		getContentPane().add(lblBackground); // adding the label to the frame
		validate();

		setResizable(false); 
	    pack();
	    setVisible(true);
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
	
	/*
	 * This is the main that starts the game. 
	 */
	public static void main(String[] args) {
		PicturesStock pics = new PicturesStock(); // load the pictures from file.
		Boards boards = new Boards() ; // load the boards from csv file.
		GameMenu m = new GameMenu(pics, boards) ; // call for menu window with the boards and pictures.
	
	}
}
