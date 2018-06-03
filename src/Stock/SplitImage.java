package Stock;
import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.*;

public class SplitImage {
	
	  public SplitImage(PicturesStock pics ,BufferedImage image, int n) throws IOException {
	/*	    
        File picture = new File(""+ stringPic +".jpg"); 
        FileInputStream picture1 = new FileInputStream(picture);
        BufferedImage image = ImageIO.read(picture1); //reading the image file
*/
        int amountOfPieces= n*n;
        int pieceWidth = image.getWidth() /n; // determines the piece width
        int pieceHeight = image.getHeight() /n; // determines the piece height 
        int count = 0;
        BufferedImage imgs[] = new BufferedImage[amountOfPieces]; //Image array to hold image pieces
        for (int x=0; x<n; x++) {
            for (int y=0; y<n; y++) {
                //Initialize the image array with image pieces
                imgs[count]=new BufferedImage(pieceWidth, pieceHeight, image.getType());

                // draws the image piece
                Graphics2D imPiece = imgs[count++].createGraphics();
                imPiece.drawImage(image, 0, 0, pieceWidth, pieceHeight, pieceWidth*y, pieceHeight*x, pieceWidth*y+pieceWidth, pieceHeight*x+pieceHeight, null);
                imPiece.dispose();
            }
        }

        //writing mini images into image files
        for (int i = 0; i < imgs.length; i++) {
            ImageIO.write(imgs[i], "jpg", new File("img"+i+".jpg"));
        }
        
      /*  pics.addNewBoard(n,image);
        for (int i=0 ; i<imgs.length ; i++)
        	pics.addIcons();*/
}}