package Main;
// Importing all the necessary packages
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import util.Menu;
import util.Reader;

/**
 * Main class for a tracker software
 * A software that tracks a Valorant E-Sports Teams Statistics
 * This is the Second version of the software, and has multiple classes instead of One
 * This software will now take input from a file and the user
 * This software will also be able to store data in a file
 *
 * @author Saksham Puri, Aishan Irfan
 * @version 3.0
 *
 * @Date:     March 15, 2022.
 * @Tutorial:            T05.
 */
public class Main extends Application{
	
	public static double version = 3.0;
	
	// declaring all the class constants that will be used in the program to check various conditions.
	public static final int CHECK_PERCENTAGE = -2;
	public static final int CHECK_DOUBLE = -1;
	public static final int CHECK_MENU_INT = 0;
	public static final int CHECK_ADD_REMOVE = 1;
	public static final int CHECK_YES_NO = 2;
	public static final int CHECK_INT = 3;
	public static final int CHECK_TEAM_INT = 4;
	public static final int CHECK_PLAYER_INT = 5;

	// declaring all the class constants that will be used in the program to check various conditions.
	public static final int WINS = 8;
	public static final int LOSSES = 9;
	// declaring a string Array as a constant that holds all the ranks.
	public static final String[] ranks = {
			"Iron 1", "Iron 2", "Iron 3",
			"Bronze 1", "Bronze 2", "Bronze 3",
			"Silver 1", "Silver 2", "Silver 3",
			"Gold 1", "Gold 2", "Gold 3",
			"Platinum 1", "Platinum 2", "Platinum 3",
			"Diamond 1", "Diamond 2", "Diamond 3",
			"Immortal 1", "Immortal 2", "Immortal 3",
			"Radiant"
	};
	// More useful boolean class variables to store and load data in files
	public static boolean autoSaveOn = false;
	// declaring an arrayList of the class "Player" to hold all our player objects,
	public static ArrayList<Player> players = new ArrayList<Player>();
	// declaring an arrayList of the class "Team" to hold all our team objects.
	public static ArrayList<Team> teams = new ArrayList<Team>();
	
	public static String[] arguments;
	
	public static Scene scene;
	public static Stage stage;

	/**
	 * This method prints the welcome message to the user and prints out the menu
	 * @param args a sequence of characters (Strings) that are passed to the "Main" function.
	 */
	public static void main(String[] args) {
		arguments = args;
		if (Main.checkArgs(arguments)) {
			MainController.arg = arguments[0];
		}
		launch(args);
	}
	
	@Override
    public void start(Stage stag) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
        scene = new Scene(fxmlLoader.load(), 640, 460);
        //Students edit here to set up the scene
        stage = stag;
        //Setting the title of the Window with the Name and the current Version
        stage.setTitle("Valorant Tracker 3.0");
        Image icon = new Image("Main/Val-Icon3.png");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
	
	/**
	 * This method checks the arguments that are passed from the command Line
	 * @param args the arguments passed
	 * @return returns true, if the arguments exist, false if they don't
	 */
	public static boolean checkArgs(String[] args) {
		
		//If there are more than 1 arguments
		if (args.length > 1) {
			System.err.println("More than 1 arguments detected, only the first one will be used as file name");
		}
		
		//if there are no arguments passed
		else if (args.length < 1) {
			System.err.println("No Arguments detected, Program will continue without loading a File");
			return false;
		}
		
		return true;
	}

	/**
	 * This method takes a String, name as an input and then creates a Team object of the same name and adds that object to the Team ArrayList.
	 * @param name is the String name of the Team that is used to create a new Team object.
	 */
	public static void addTeam(String name) {
		// adding the name to the Team ArrayList by creating a new Team object of the given name.
		teams.add(new Team(name));
	}

	/**
	 * This method takes a players real name, username, age and teamIndex and uses them to create a new Player object and add that
	 * to the Player ArrayList.
	 * @param uName A string that holds a players gamerTag/userName
	 * @param rName A string that holds a players realName
	 * @param age an integer that is the players age.
	 * @param teamIndex an integer that is used to get the team object from the Teams ArrayList, and then add that team to the player object.
	 */
	public static void addPlayer(String uName, String rName, int age, int teamIndex) {

		// if the user does not want to add the player to a team.
		if (teamIndex == -1) {
			// we create a Player object without a team and it to the Player arrayList
			players.add(new Player(uName, rName, age));
		}
		else {
			// we create a Player object and add it tp the Player arrayList.
			players.add(new Player(uName, rName, age, teams.get(teamIndex)));
		}
	}

	/**
	 * This method removes a Player object using a players' username, from the PLayer ArrayList.
	 * @param uName is a string that stores a players' username. It is used to identify the Player object that is to be removed.
	 */
	public static void removePlayer(String uName) {
		int index = getPlayerIndex(uName);
		players.remove(index);
	}

	/**
	 * This method checks whether the user input is a valid input or not
	 * depending on what form of validity check we are performing
	 *
	 * @param option is a String containing user input
	 * @param check is an integer that we use to check if the correct menu option has been entered.
	 * @return returns either true if the user input can be used without error, false otherwise
	 */
	public static boolean isValid(String option, int check) {

		//If the user is inputting a value for Menu options
		if (check == CHECK_INT || check == CHECK_MENU_INT || check == CHECK_TEAM_INT || check == CHECK_PLAYER_INT) {
			//empty integer value that would later hold the user input converted to an integer
			int value;

			//Trying to convert user input string into an integer
			try {
				value = Integer.parseInt(option);

				//If there is an exception, display it to the user, and return false as user input is not a valid Integer
			} catch(Exception e) {

				//Display errors
				System.out.println("Error: " + e.getLocalizedMessage());
				System.out.println("Please input a valid number (1, 2, 3...)\n");
				return false;
			}

			//If user input was successfully converted to an integer, then check if its in range
			if (check == CHECK_MENU_INT && (value > 19 || value < 1)) {
				//If the integer is out of range, then output the error to user, and return false
				System.out.println("Invalid input, please type a number between (1-19) inclusively\n");
				return false;
			}

			//If user input was successfully converted to an integer, then check if its in range
			else if (check == CHECK_TEAM_INT && (value > 3 || value < 1)) {
				//If the integer is out of range, then output the error to user, and return false
				System.out.println("Invalid input, please type a number between (1-3) inclusively\n");
				return false;
			}

			//If the user input was successfully converted to an integer, then check if its in range
			else if (check == CHECK_PLAYER_INT && (value > 8 || value < 1)) {
				//If the integer is out of range, then output the error to user, and return false
				System.out.println("Invalid input, please type a number between (1-8) inclusively\n");
				return false;
			}
		}

		//If the user is inputting a double value
		if (check == CHECK_DOUBLE || check == CHECK_PERCENTAGE) {

			double value;

			//Trying to convert user input string into a double value
			try {
				value = Double.parseDouble(option);

				//If there is an exception, display it to the user, and return false as user input is not a valid double
			} catch(Exception e) {

				//Display errors
				System.out.println("Error: " + e.getLocalizedMessage());
				System.out.println("Please input a valid number (1.1, 2.2, 3.3 ...)\n");
				return false;
			}

			//If checking whether the user typed in a valid percentage value, check if it is in bounds
			if (check == CHECK_PERCENTAGE && (value > 100 || value < 0)) {

				//If not let the user know
				System.out.println("Invalid input, please type a number between (0-100) inclusively\n");
				return false;
			}
		}

		//If the user is inputting a value for adding or removing
		if (check == CHECK_ADD_REMOVE) {

			//If the user input is either "a" or "b" return true
			if (option.equalsIgnoreCase("a") || option.equalsIgnoreCase("r")) {
				return true;
			}

			//otherwise, let the user know their input is invalid value, and return false
			else {
				System.out.println("Invalid Input, please either type 'a' for adding or 'r' for removing\n");
				return false;
			}
		}

		//If the user is inputting a value for yes or no
		if (check == CHECK_YES_NO) {

			//if the user input is either "y" or "n" return true
			if (option.equalsIgnoreCase("y") || option.equalsIgnoreCase("n")) {
				return true;
			}

			//otherwise, let the user know their input is invalid, and return false
			else {
				System.out.println("Invalid Input, please either type 'y' for yes or 'n' for no\n");
				return false;
			}
		}

		//if not returned false by now, return true as the input is valid.
		return true;
	}

	/**
	 * This method checks if the given @param value  is a team name that exists in the team ArrayList and if so returns true, false otherwise.
	 * @param value is a String that is the name of the Team to be checked.
	 * @return returns true if the Team is found in the Team ArrayList. False otherwise.
	 */
	public static boolean teamExists(String value) {
		// using a for loop to go through the team Arraylist.
		for (int i = 0; i < teams.size(); i++) {
			// if the team name is found, return true
			if (value.equals(teams.get(i).getName())) {
				return true;
			}
		}
		// default return false.
		return false;
	}

	/**
	 * This method returns the index of the team that is entered as the value in the team ArrayList.
	 * @param value is a string that holds the team name.
	 * @return returns the index of the team when found in the team ArrayList.
	 */
	public static int getTeamIndex(String value) {
		// default index value.
		int index = -1;
		// using a for loop to go through the teams ArrayList
		for (int i = 0; i < teams.size(); i++) {
			// if the team name is found, make that iteration the index.
			if (value.equals(teams.get(i).getName())) {
				index = i;
			}
		}
		// return the index.
		return index;
	}

	/**
	 * This method checks if the given @param value is a player username that exists in the player ArrayList and if so returns true, false otherwise.
	 * @param value is a String that is the username of a player that is to be checked.
	 * @return returns true if the username is found in the player ArrayList. False otherwise.
	 */
	public static boolean playerExists(String value) {
		// using a for loop to go through the player ArrayList.
		for (int i = 0; i < players.size(); i++) {
			// if the username is found, return true
			if (value.equals(players.get(i).getUserName())) {
				return true;
			}
		}
		// default return false.
		return false;
	}

	/**
	 * This method returns the index of the player whose username is entered as the value in the player ArrayList.
	 * @param value is a String that holds the playerUsername.
	 * @return returns the index of the player when found in the player ArrayList.
	 */
	public static int getPlayerIndex(String value) {
		// default index value.
		int index = -1;
		// using a for loop to go through the team ArrayList.
		for (int i = 0; i < players.size(); i++) {
			// if the player is found, make that iteration the index
			if (value.equals(players.get(i).getUserName())) {
				index = i;
			}
		}
		// return the index.
		return index;
	}

	/**
	 * This method uses the ranks Array that is created to print out all the ranks with proper spaces.
	 */
	public static void printRanks() {
		System.out.println();
		// setting up a counter
		int count = 0;
		// using a for loop to go through the ranks Array.
		for (int i = 0; i < ranks.length; i++) {
			// using conditionals to print out the ranks in with proper spaces.
			if (i == ranks.length-1) {
				System.out.print(ranks[i]);
				System.out.println("\n");
			}
			else if (count < 2) {
				System.out.print(ranks[i] + ", ");
			}
			else {
				System.out.print(ranks[i]);
				System.out.println();
				count = -1;
			}
			count++;
		}
	}

	/**
	 *This method checks if the rank that is passed in as the @param value, exists in the ranks Array.
	 *If yes, it returns True, False otherwise.
	 * @param rank is a String that is checked to exist in the ranks Array
	 * @return returns true if the rank is found. False otherwise.
	 */
	public static boolean rankMatches(String rank) {
		// using a for loop to go through the ranks Array.
		for (int i = 0; i < ranks.length; i++) {
			if (ranks[i].equalsIgnoreCase(rank)) {
				// if rank is found in the Array, return True.
				return true;
			}
		}
		// default return false.
		return false;
	}

	/**
	 * This method prints the gap between two things, so they are equally spaced each time no matter the length of a word
	 *
	 * @param teams is an ArrayList that is checked to find the largest sized element in
	 * @param index is the current index a word that is iterating through an ArrayList
	 * @param previous A string value that is within the Heading of something being printed, this string value is used to evenly space the next thing that is going to be printed, so it doesn't print values without any space
	 * @return returns String gap which is the gap that needs to be printed for equal spacing
	 */
	public static String printGap(ArrayList<String> teams, int index, String previous) {

		//String to hold the amount of spaces that needs to be returned
		//integer max value to hold the max length of String value that is encountered in the ArrayList
		String gap = "";
		int max = 0;

		//for loop to go through and get the max length element
		for (int i = 0; i < teams.size(); i++) {
			if (teams.get(i).length() > max) {
				max = teams.get(i).length();
			}
		}

		//If the index is -1 (Title Spacing), then print out as many spaces as the max length of element
		if (index == -1) {
			for (int j = 0; j < max; j++) {
				gap = gap + " ";
			}
		}

		//If index is not -1
		else {

			//calculate the difference between the max length and the current length
			int temp = max - teams.get(index).length();

			//print as many gaps as the difference + 4 (Added 4 more spaces to account for the Title "Name")
			for (int i = 0; i < temp + previous.length(); i++) {
				gap = gap + " ";
			}
		}

		//Return the String holding the spaces
		return gap;
	}

	/**
	 * This method acts as a switch to turn the autoSave boolean true if it is false and vice versa.
	 * This is used for saving the data in the files.
	 * It also prints out a message showing the user the state of the autoSave.
	 */
	public static void autoSaveToggle() {
		if (autoSaveOn) {
			// if the autoSaveOn boolean is true, it turns it to false.
			autoSaveOn = false;
			Menu.autoSave = "OFF";
			System.out.println("AutoSave turned Off");
		}
		else {
			// if autoSaveBoolean is false, it turns it to true.
			autoSaveOn = true;
			Menu.autoSave = "ON";
			System.out.println("AutoSave turned On (Data will be saved each time a change is made)");
		}
	}

	/**
	 * This method creates a new file object using the fileName that is passed in as a parameter.
	 * Then it loads all the information from that file using the Reader class and stores it in the player and team arrayLists and prints all the info
	 * using the printAll method.
	 * @param fileName is the name of the file that has to be loaded.
	 */
	public static void loadInfo(String fileName) {
		// creating a new file object.
		File file = new File(fileName);
		
 		boolean success = Reader.loadInfo(file);
		 // if file loads successfully, print the message to the user and print all the info about the teams and players.
 		if (success) {
			System.out.println("File Loaded!");
			Menu.printAll();
 		}
	}

	/**
	 * This method deletes all the previously saved data from the teams and players ArrayList.
	 * After that it adds all the default values and statistics to the teams and players and prints them all out.
	 */
	public static void loadDefaultData() {
		// Deleting all the previous items in the players and teams Arraylists.
		teams.clear();
		players.clear();
		// adding default teams and players to the ArrayLists.
		addTeam("Team 1");
		addTeam("Team 2");
		addPlayer("Player_1 T1", "realPlayer1", 24, 0);
		addPlayer("Player_2 T1", "realPlayer2", 21, 0);
		addPlayer("Player_3 T2", "realPlayer3", 23, 1);
		addPlayer("Player_4 T2", "realPlayer4", 18, 1);

		// setting wins and losses for the default teams.
		teams.get(0).setWins(10);
		teams.get(0).setLosses(1);
		teams.get(1).setWins(5);
		teams.get(1).setLosses(2);

		// adding default skills to the players.
		players.get(0).setSkills("Ratio", "3.0");
		players.get(0).setSkills("HeadShot", "78.7");
		players.get(0).setSkills("Agent", "Neon");
		players.get(0).setSkills("Rank", "Immortal 1");
		
		players.get(1).setSkills("Ratio", "1.5");
		players.get(1).setSkills("HeadShot", "81.7");
		players.get(1).setSkills("Agent", "Raze");
		players.get(1).setSkills("Rank", "Diamond 3");
		
		players.get(2).setSkills("Ratio", "0.7");
		players.get(2).setSkills("HeadShot", "42.3");
		players.get(2).setSkills("Agent", "Killjoy");
		players.get(2).setSkills("Rank", "Iron 1");
		
		players.get(3).setSkills("Ratio", "5.5");
		players.get(3).setSkills("HeadShot", "95.6");
		players.get(3).setSkills("Agent", "Yoru");
		players.get(3).setSkills("Rank", "Silver 2");
		
		System.out.println("Example Loaded!");
		// printing all the information.
		Menu.printAll();
	}

}