package Stock;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;

public class Boards {

	private Vector<Integer> boards3x3 = new Vector<>();
	private Vector<Integer> boards4x4 = new Vector<>();
	private Vector<Integer> boards5x5 = new Vector<>();

	public Boards () {
		initCSVFile();
	}

	/*
	 * This method initials the vectors by the given boards in 
	 * CSV file .
	 */

	private void initCSVFile(){
		String csvFile = "boards.csv"; // the name of the csv file.
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(csvFile)); // reading from csv file
			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] csv = line.split(cvsSplitBy);
				for (int i=0 ; i< Integer.parseInt(csv[0]) ; i++) {
					line = br.readLine(); 
					if (csv[0].equals(""+3)) { // if the current board is in size 3x3
						for (int j=0;j<3;j++) {
							boards3x3.add( Integer.parseInt(line.split(cvsSplitBy)[j])); // add the next index of the board game.
						}
					}
					else if (csv[0].equals(""+4)) { // if the current board is in size 4x4
						for (int j=0;j<4;j++) {
							boards4x4.add( Integer.parseInt(line.split(cvsSplitBy)[j])); // add the next index of the board game.
						}
					}
					else{ // if the current board is in size 5x5
						for (int j=0;j<5;j++) {
							boards5x5.add(Integer.parseInt(line.split(cvsSplitBy)[j])); // add the next index of the board game.
						}
					}
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}


	/*
	 * This method random a board from the stock by a given size.
	 * @return board array
	 */
	public int [] getBoard(int size){ 	// random between the boards and return the chosen one.
		int numOfBoards;
		Random random = new Random();
		int [] board  ;
		if (size==3 && boards3x3.size()!=0) // in case of 3x3 board game
		{
			board = new int[9] ;
			numOfBoards=boards3x3.size()/9 ; // calculate the count of boards in size 3x3 .
			int randomNumber = random.nextInt(numOfBoards) ; // random between 0 to numOfBoards-1 .
			int j=0;
			for (int i=randomNumber*9 ; i<randomNumber*9+9 ; i++) {
				board[j]=boards3x3.elementAt(i);  // update the array with the chosen board
				j++;
			}
			return board;

		}
		else if (size ==4 && boards4x4.size()!=0) // in case of 4x4 board game
		{		
			board = new int[16] ;
			numOfBoards=boards4x4.size()/16 ;
			int randomNumber = random.nextInt(numOfBoards) ; // random between 0 to numOfBoards-1 .
			int j=0;
			for (int i=randomNumber*16 ; i<randomNumber*16+16 ; i++) {
				board[j]=boards4x4.elementAt(i);  // update the array with the chosen board
				j++;
			}
			return board;
		}
		else // if the board size is 5x5 
		{
			board = new int[25] ;
			if (boards5x5.size()!=0) {
				numOfBoards=boards5x5.size()/25 ;
				int randomNumber = random.nextInt(numOfBoards) ; // random between 0 to numOfBoards-1 .
				int j=0;
				for (int i=randomNumber*25 ; i<randomNumber*25+25 ; i++) {
					board[j]=boards5x5.elementAt(i); // update the array with the chosen board
					j++;
				}
			}
			return board;
		}
		

	}
	
	/*
	 * This functions return the size of the vector boards3x3/ boards4x4/ boards5x5.
	 * 
	 * @return the size of the vector boards3x3/ boards4x4/ boards5x5.
	 */
	public int getBoards3x3Size() {
		return boards3x3.size();
	}

	public int getBoards4x4Size() {
		return boards4x4.size();
	}
	
	public int getBoards5x5Size() {
		return boards5x5.size();
	}


}
