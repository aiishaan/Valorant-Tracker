package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import Main.Main;

/**
 * Writer class gets all the information from the system and stores it in a file
 * 
 * @author Saksham Puri, Aishan Irfan
 */
public class Writer {
	
	//FileWriter and Buffered writers to be used in the class
	private static FileWriter fileW;
	private static BufferedWriter writer;
	
	/**
	 * This method gets the information from the Main class, and stores it in the file
	 * @param file the file that everything will be saved in
	 */
	public static boolean writeFile(File file) {
		
		//try
		try {
			
			//if the file isn't ready
			if (!checkFile(file)) {
				
				//let the user know the system is creating a file
				System.out.println("Creating a File...");
				
				//create a file, and if not successful, let the user know writing to the file
				if (file.createNewFile()) {
					
					//let the user know file is created
					System.out.println("File Created: " + file.getName());
				}
				else {
					file.delete();
					file.createNewFile();
				}
			}
			else {
				file.delete();
				file.createNewFile();
			}
			
			//setup the file writers
			fileW = new FileWriter(file);
			writer = new BufferedWriter(fileW);
			
			//Go through all the teams in the system, and add their information to the file
			for (int i = 0; i < Main.teams.size(); i++) {
				writer.write(Main.teams.get(i).toString());
				writer.newLine();
			}
			
			//Put this symbol after the teams info has been put
			writer.write("~");
			writer.newLine();
			
			//Go through the players and get their info, and add their info to the file
			for (int i = 0; i < Main.players.size(); i++) {
				writer.write(Main.players.get(i).toString() + "," + Main.players.get(i).getSkills(""));
				writer.newLine();
			}
			
			//Write all the info stored in the file
			writer.flush();
			
			//If an exception occurs, let the user know
		} catch(Exception e) {
			System.err.println("Internal System Error Occurred" + e.getStackTrace() + "\n" + e.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * This method checks if the file is ready to be written to
	 * @param file is the file being checked
	 * @return returns true if the file is ready, false if not
	 */
	private static boolean checkFile(File file) {
		
		//if the file isn't null
		if (file != null) {
			
			//if the file exists and can be written to, and the file is a file
			if (file.exists() && file.canWrite() && file.isFile()) {
				
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
