package Stock;

import java.awt.Image;
import java.util.Vector;

import javax.swing.ImageIcon;

public class PicturesStock {
	// pictures vector for background, game over gif and pictures for game.
	private Vector<ImageIcon> pictures = new Vector<ImageIcon>(); 

	// cat arrays
	private ImageIcon [] cat_5x5 = new ImageIcon [25] ;
	private ImageIcon [] cat_4x4 = new ImageIcon [16] ;
	private ImageIcon [] cat_3x3 = new ImageIcon [9] ;

	// cyber arrays
	private ImageIcon [] cyber_5x5 = new ImageIcon [25] ;
	private ImageIcon [] cyber_4x4 = new ImageIcon [16] ;
	private ImageIcon [] cyber_3x3 = new ImageIcon [9] ;

	// sushi arrays
	private ImageIcon [] sushi_5x5 = new ImageIcon [25] ;
	private ImageIcon [] sushi_4x4 = new ImageIcon [16] ;
	private ImageIcon [] sushi_3x3 = new ImageIcon [9] ;
/*
 * The PicturesStock constructor that initials the arrays and vector of image icons.
 */

	public PicturesStock() {
		initPictures();	//initial of the arrays and the vector of images .
	}
	/*
	 * This method is responsible for loading all the pictures before the game starts. 
	 */
	private void initPictures () {
		ImageIcon im ;
		Image resizedImage ;
		// initial of the game pictures.
		im= new ImageIcon("sample_pictures/cat/cat.jpeg");
		pictures.add(im); // add the cat image icon to the vector

		im= new ImageIcon("sample_pictures/cyber/cyber.jpeg");
		pictures.add(im); // add the cyber image icon to the vector

		im= new ImageIcon("sample_pictures/sushi/sushi.jpg");
		pictures.add(im); // add the sushi image icon to the vector
		
		im= new ImageIcon("sample_pictures/fireworks.gif") ;// loading the fireworks' image
		pictures.addElement(im);
		
		im= new ImageIcon("sample_pictures/background1.jpg") ;// loading the background's image
		pictures.addElement(im);

		for (int j=1; j<=9;j++) { // initial of 3x3 arrays .
			im= new ImageIcon("sample_pictures/cat/cat_3x3/"+j+".jpeg");
			resizedImage = im.getImage().getScaledInstance(150, 150, 0); // resizing the icon.
			im.setImage(resizedImage);
			cat_3x3[j-1]=im; // updating the array with the new icon
			im=  new ImageIcon("sample_pictures/cyber/cyber_3x3/"+j+".jpeg");
			resizedImage = im.getImage().getScaledInstance(150, 150, 0); // resizing the icon.
			im.setImage(resizedImage);
			cyber_3x3[j-1]=im; // updating the array with the new icon
			im=  new ImageIcon("sample_pictures/sushi/sushi_3x3/"+j+".jpeg");
			resizedImage = im.getImage().getScaledInstance(150, 150, 0); // resizing the icon.
			im.setImage(resizedImage);
			sushi_3x3[j-1]=im; // updating the array with the new icon
		}

		for (int j=1; j<=16;j++) { // initial of 4x4 arrays .
			im= new ImageIcon("sample_pictures/cat/cat_4x4/"+j+".jpeg");
			resizedImage = im.getImage().getScaledInstance(113, 113, 0);// resizing the icon.
			im.setImage(resizedImage);
			cat_4x4[j-1]=im; // updating the array with the new icon
			im=  new ImageIcon("sample_pictures/cyber/cyber_4x4/"+j+".jpeg");
			resizedImage = im.getImage().getScaledInstance(113, 113, 0);// resizing the icon.
			im.setImage(resizedImage);
			cyber_4x4[j-1]=im; // updating the array with the new icon
			im=  new ImageIcon("sample_pictures/sushi/sushi_4x4/"+j+".jpeg");
			resizedImage = im.getImage().getScaledInstance(113, 113, 0);// resizing the icon.
			im.setImage(resizedImage);
			sushi_4x4[j-1]=im; // updating the array with the new icon
		}


		for (int j=1; j<=25;j++) { // initial of 5x5 arrays .
			im= new ImageIcon("sample_pictures/cat/cat_5x5/"+j+".jpeg");
			resizedImage = im.getImage().getScaledInstance(90, 90, 0); // resizing the icon.
			im.setImage(resizedImage);
			cat_5x5[j-1]=im; // updating the array with the new icon
			im=  new ImageIcon("sample_pictures/cyber/cyber_5x5/"+j+".jpeg");
			resizedImage = im.getImage().getScaledInstance(90, 90, 0); // resizing the icon.
			im.setImage(resizedImage);
			cyber_5x5[j-1]=im; // updating the array with the new icon
			im=  new ImageIcon("sample_pictures/sushi/sushi_5x5/"+j+".jpeg");
			resizedImage = im.getImage().getScaledInstance(90, 90, 0); // resizing the icon.
			im.setImage(resizedImage);
			sushi_5x5[j-1]=im; // updating the array with the new icon
		}
	}
	
	/*
	 * This method returns pictures options for the game .
	 */
	public Vector<ImageIcon> getPics(){
		return pictures ;
	}
	
	/*
	 * This method returns an array with the required peaces of images
	 * for game.
	 */

	public ImageIcon [] getGamePictures(int size, String pictureName) {
		if (size==3)
			return getPics3(pictureName) ;
		else if (size==4)
			return getPics4(pictureName);
		else // in case of sushi picture
			return getPics5(pictureName);
	}
	
	/*
	 * This method returns an array with the required peaces of images
	 * for game in size 3x3
	 */
	
	private ImageIcon [] getPics3(String pictureName) {
		if (pictureName=="cat")
			return cat_3x3 ;
		else if (pictureName == "cyber")
			return cyber_3x3 ;
		else // in case of sushi picture
			return sushi_3x3 ;
	}
	
	/*
	 * This method returns an array with the required peaces of images
	 * for game in size 4x4
	 */
	
	private ImageIcon [] getPics4(String pictureName) {
		if (pictureName=="cat")
			return cat_4x4 ;
		else if (pictureName == "cyber")
			return cyber_4x4 ;
		else // in case of sushi picture
			return sushi_4x4 ;
	}
	
	/*
	 * This method returns an array with the required peaces of images
	 * for game in size 5x5
	 */
	
	private ImageIcon [] getPics5(String pictureName) {
		if (pictureName=="cat")
			return cat_5x5 ;
		else if (pictureName == "cyber")
			return cyber_5x5 ;
		else  // in case of sushi picture
			return sushi_5x5;
		
	}

}
