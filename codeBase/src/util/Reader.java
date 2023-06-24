package util;

import Main.Main;
import Main.Player;
import Main.Team;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Reader class to read all the information from the File given, and store it in the System
 * 
 * @author Saksham Puri, Aishan Irfan
 */
public class Reader{
	
	/**
	 * This method opens the file, reads the lines, and depending on the information read, stores it in the system using objects
	 * @param file is the file being read
	 * @return returns true or false depending on if loading file went successfully or not
	 */
	public static boolean loadInfo(File file) {
		
		//Check file, if file is ok to read or not
		if (!checkFile(file)) {
			System.err.println("Unable to Load File!");
			return false;
		}
		
		//if file can be read
		try {
			
			//setup the file reader
			Scanner input = new Scanner(file);
			
			//Store the first line in the String
			String line = input.nextLine();
			
			//Setup the counter, which will count the number of line we are at
			int counter = 0;
			
			//as long as the input has a next line to read, and doesn't reach the point where the line reads '~'
			while(input.hasNext() && !line.equals("~")) {
				
				//Get the current line, and split it using a comma
				String[] split = line.split(",");
				
				//get the information using the comma separate values
				String teamName = split[0];
				
				//If the teamName already exists in the system, skip this line, and move on to the next line
				if (Main.teamExists(teamName)) {
					System.out.println("Team (" + teamName + ") not Loaded! Team Already Exists in the System");
					line = input.nextLine();
					continue;
				}
				int wins = Integer.parseInt(split[1]);
				int losses = Integer.parseInt(split[2]);
				
				//Create a team object using the information gathered and add it to the arrayList in main class
				Main.teams.add(new Team(teamName));
				Main.teams.get(counter).setWins(wins);
				Main.teams.get(counter).setLosses(losses);
				
				//increase the counter and read the next Line
				counter++;
				line = input.nextLine();
			}
			
			//reset the counter to 0
			counter = 0;
			
			//as long as there is a line to read
			while(input.hasNext()) {
				
				//get the line, split the line using a comma
				line = input.nextLine();
				String[] split = line.split(",");
				String rName = split[0];
				String uName = split[1];
				
				//is there already exists a player with the same userName don't add the player, and move on to the next Line
				if (Main.playerExists(uName)) {
					System.out.println("Player " + uName + " not Loaded! Player Already Exists in the System");
					continue;
				}
				
				int age = Integer.parseInt(split[2]);
				Team team = Main.teams.get(Main.getTeamIndex(split[3]));
				String rank = split[4];
				String agent = split[5];
				String head = split[6];
				String ratio = split[7];
				
				//create a player and add it to the player arrayList in Main class using the information gathered
				Main.players.add(new Player(uName, rName, age, team));
				Main.players.get(counter).setSkills("Rank", rank);
				Main.players.get(counter).setSkills("Agent", agent);
				Main.players.get(counter).setSkills("HeadShot", head);
				Main.players.get(counter).setSkills("Ratio", ratio);
				counter++;
			}
			
			//Close the reader
			input.close();
			
			//If file not found, print the error
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
			//If any other error, let the user know system couldn't read the file
		} catch (Exception e) {
			System.err.println("Unable to Read File!" + e.getLocalizedMessage());
		}
		return true;
	}
	
	/**
	 * This method checks if the file is ready to be read
	 * @param file is the file being checked
	 * @return returns true if the file is ready, false if not
	 */
	private static boolean checkFile(File file) {
		
		//if the file isn't null
		if (file != null) {
			
			//if the file exists and can be read, and the file is a file
			if (file.exists() && file.canRead() && file.isFile()) {
				
				if (file.length() == 0) {
					System.err.println("File is Empty!");
					return false;
				}
				
				//return true
				return true;
			}
			
			//else let the user know the file doesn't exist with the file path
			System.err.printf("The file %s does not exist%n", file.getAbsoluteFile());
			return false;
		}
		//let the user know and return false
		System.err.println("The file does not exist");
		return false;
	}
	
}
