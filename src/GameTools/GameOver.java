package GameTools;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/*
 * This class represents the window the user see in case of a victory.
 * This class extends JFrame and implements ActionListener.
 * 
 */

public class GameOver extends JFrame implements ActionListener {
	private JLabel lblWinner;
	private JLabel lblSteps;
	private JLabel lblTime;
	private JLabel lblFireworks;
	private JButton btnExit;

	/* GameOver constructor.
	 * 
	 * @param steps - the amount of steps the user had done before winning.
	 * @param time  - the time it took the user to fix the picture.
	 */

	public GameOver(int steps, String time, ImageIcon im) {
		super("Game over"); // initiating the frame
		getContentPane().setBackground(new Color(0, 0, 0)); // setting the frame's background and bounds
		setBounds(0, 0, 800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setResizable(false); 

		lblWinner = new JLabel("YOU WON!"); // "YOU WON!" label
		lblWinner.setBounds(206, 85, 384, 72); // setting the font, the background, the foreground and the size
		lblWinner.setBackground(new Color(255, 255, 255));
		lblWinner.setFont(new Font("Stencil", Font.PLAIN, 80));
		lblWinner.setForeground(SystemColor.text);
		getContentPane().add(lblWinner); // adding "YOU WON!" to the frame

		lblSteps = new JLabel("Steps: " + steps); // "Steps: " label, steps is the amount of step the user had done
		lblSteps.setBounds(30, 250, 220, 40); // setting the font, the background, the foreground and the size
		lblSteps.setForeground(new Color(255, 255, 255));
		lblSteps.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblSteps.setBackground(Color.BLACK);
		getContentPane().add(lblSteps); // adding "Steps:" to the frame

		lblTime = new JLabel("Time: "+time); // "Time: " label, time is the time it took the user to win
		lblTime.setBounds(30, 340, 313, 40); // setting the font, the background, the foreground and the size
		lblTime.setForeground(Color.WHITE);
		lblTime.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTime.setBackground(Color.BLACK);
		getContentPane().add(lblTime); // adding "Time:" to the frame

		btnExit = new JButton("Exit"); // "Exit" button
		btnExit.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				dispose(); // closing the window when the button is pressed
			}
		});
		btnExit.setBackground(Color.BLACK); // setting the font, the background, the foreground and the size
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnExit.setForeground(Color.WHITE);
		btnExit.setBounds(570, 480, 190, 50);
		btnExit.setFocusable(false);
		getContentPane().add(btnExit); // adding btnExit to the frame
		validate();

		lblFireworks = new JLabel(""); // the label in which the fireworks will be shown
		lblFireworks.setBounds(0, 0, 800, 600); // setting the label's bounds
		Image resizedImage = im.getImage().getScaledInstance(800, 600, 0); // cutting the fireworks' image
		im.setImage(resizedImage); 
		lblFireworks.setIcon(im); // setting the label's icon to be the fireworks
		getContentPane().add(lblFireworks); // adding the label to the frame
		validate();

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
	
}
