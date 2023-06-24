package util;

import java.util.ArrayList;
import java.util.Scanner;

import Main.Main;

/**
 * Simple Menu so that the user can enter different commands and interact with the program.
 */
public class Menu {

    // Creating a new Scanner object to take input from the user.
    private static Scanner input = new Scanner(System.in);

    //auto Save String to store "ON" or "OFF"
    public static String autoSave = "OFF";


    /**
     * This method adds the team with user inputed name and information to the teams arrayList in Main class
     */
    public static void addTeam() {
        //variable to store user choice when saying yes or no
        String option;

        //Asks the user to input the Team Name and stores it into a variable
        System.out.print("Please enter the name of the Team you would like to add: ");
        String teamName = input.nextLine();

        //as long as a team exists with that Name, keep looping
        while(Main.teamExists(teamName)) {

            //Asking the user if they would still like to add a team when a team with the same name exists
            System.out.print("The Team Name already exists, would you still like to add the team with a different Name? (y/n): ");
            option = input.nextLine();

            //Checks if the user input can be used or is one of "y" or "n"
            while(!Main.isValid(option, Main.CHECK_YES_NO)) {

                //As long as it isn't valid, keep asking the user
                System.out.print("The Team Name already exists, would you still like to add the team with a different Name? (y/n): ");
                option = input.nextLine();
            }

            //if user says no, then return the function and go back to menu options
            if (option.equalsIgnoreCase("n")) {
                return;
            }

            //else ask for a different team name
            //not checking if the input is "y" because the method isValid already did
            System.out.print("Please enter the name of the Team you would like to add: ");
            teamName = input.nextLine();
        }

        //Adding the stored variables into their dedicated ArrayLists
        Main.addTeam(teamName);

        //Let the user know that the team has been added.
        System.out.print("Team added into System!\nWould you like to add more information about the team right now? (y/n): ");
        option = input.nextLine();

        //Checks if the user input can be used or is one of "y" or "n"
        while(!Main.isValid(option, Main.CHECK_YES_NO)) {

            //As long as it isn't valid, keep asking the user
            System.out.print("Would you like to add more information about the team right now? (y/n): ");
            option = input.nextLine();
        }

        //if user says no, then return the function and go back to menu options
        if (option.equalsIgnoreCase("n")) {
            return;
        }

        //If the user wants to add more information about the team, call this method
        updateTeamInfo(teamName);
    }

    /**
     * This method creates a player from user input, then if the user wants, adds them to a team
     */
    public static void playerToTeam() {
        //option variable is for user inputs for what they would like to do
        String option;

        //playerName stores the user input for name of the player
        String playerName;

        //If the user wants to remove the player, this variable helps in confirming that player is removed
        boolean playerRemoved = false;

        //team stores the team Name, and teamIndex stores the location of the team in ArrayList
        String team;
        int teamIndex = -1;

        //Asking the player whether they would like to remove or add a player
        System.out.print("Would you like to add or remove a player (a/r): ");
        option = input.nextLine();

        //as long as the input is invalid or wrong, keep asking
        while(!Main.isValid(option, Main.CHECK_ADD_REMOVE)) {
            System.out.print("Would you like to add or remove a player (a/r): ");
            option = input.nextLine();
        }

        //If the user wants to remove a player, check if there are any players stored to remove
        if (option.equalsIgnoreCase("r")) {
            if (Main.players.size() == 0) {
                System.out.println("No Players in the System to Remove!");
                return;
            }
        }

        //Asks for the player's username/Name
        System.out.print("What is the username/gamerTag of the Player: ");
        playerName = input.nextLine();

        //If the user wants to add the player to the team
        if (option.equalsIgnoreCase("a")) {

            //Check if the player already exists in a team, if so, keep asking user for a new name
            while(Main.playerExists(playerName)) {

                //Asking the user if they would still like to add a player when a player with the same name exists
                System.out.print("Player with the name already exists!");
                System.out.print("Would you like to add another player with a different usermame/gamertag? (y/n): ");
                option = input.nextLine();

                //Checks if the user input can be used or is one of "y" or "n"
                while(!Main.isValid(option, Main.CHECK_YES_NO)) {

                    //As long as it isn't valid, keep asking the user
                    System.out.print("Would you like to add another player with a different usermame/gamertag? (y/n): ");
                    option = input.nextLine();
                }

                //if user says no, then return the function and go back to menu options
                if (option.equalsIgnoreCase("n")) {
                    return;
                }

                System.out.print("What is the username/gamertag of the Player: ");
                playerName = input.nextLine();
            }

            //Asks for the player's real Name
            System.out.print("What is the real Name of the Player: ");
            String realName = input.nextLine();

            //Asks for the player's age
            System.out.print("What is the age of the Player: ");
            String agee = input.nextLine();

            while (!Main.isValid(agee, Main.CHECK_INT)) {
                //Asks for the player's age
                System.out.print("What is the age of the Player: ");
                agee = input.nextLine();
            }
            int age = Integer.parseInt(agee);

            System.out.println("Would you like to add the player to a team right now as well? (y/n): ");
            option = input.nextLine();
            //Checks if the user input can be used or is one of "y" or "n"
            while(!Main.isValid(option, Main.CHECK_YES_NO)) {

                //As long as it isn't valid, keep asking the user
                System.out.print("Would you like to add the player to a team right now as well? (y/n): ");
                option = input.nextLine();
            }
            if (option.equalsIgnoreCase("y")) {
                //Ask user for the team name
                System.out.print("What is the name of the Team: ");
                team = input.nextLine();

                //As long as the team name cannot be found, keep asking the user
                while(!Main.teamExists(team)) {
                    System.out.print("Team Not Found! Please check your spelling and input a Team Name: ");
                    team = input.nextLine();
                }

                //find the index of the team Name in the teams ArrayList
                teamIndex = Main.getTeamIndex(team);
            }

            //Calls the method addPlayer in main class to add the player to the arrayList in Main
            Main.addPlayer(playerName, realName, age, teamIndex);

            if (option.equalsIgnoreCase("y")) {
                //Ask the user if they want to add more information about the user now
                System.out.print("Player added to the Team!"
                        + "\nWould you like to add more information about the player now? (y/n): ");
            }
            else {
                //Ask the user if they want to add more information about the user now
                System.out.print("Player Added!"
                        + "\nWould you like to add more information about the player now? (y/n): ");
            }
            option = input.nextLine();

            //As long as it isn't valid, keep asking the user
            while(!Main.isValid(option, Main.CHECK_YES_NO)) {
                System.out.print("Would you like to add more information about the player? (y/n): ");
                option = input.nextLine();
            }

            //If the user says yes to adding more information
            if (option.equalsIgnoreCase("y")) {

                //Call the update player Info method.
                updatePlayerInfo(playerName);
            }
            else {
                return;
            }
            return;
        }

        //If the user wants to remove a player
        //As long as player is not removed
        while(!playerRemoved) {

            //Check if the player exists
            if (Main.playerExists(playerName)) {

                Main.removePlayer(playerName);

                //Set playerRemove to true since player is removed
                playerRemoved = true;

                System.out.println("Player Removed");
            }

            //If the player doesn't exist, ask the user to input the player name again
            else {
                System.out.print("Invalid Player Name, Please try again: ");
                playerName = input.nextLine();
            }
        }
        System.out.println();
    }

    /**
     * This method adds or removes wins or losses from a user inputed team
     * @param type is an integer storing either the WIN or LOSS constant from Main Class
     * @param teamName is the name of the team user would like to change the info about, null if it hasn't been passed yet
     */
    public static void winsLossToTeam(int type, String teamName) {
        //String variables to store the user options, and the team name user inputs
        String option = "";
        String team;

        int teamIndex = -1;

        //integer variable to store the amount of wins user wishes to add, and the multiplier(win or loss)
        int amount;
        int multiplier = 0;

        //If the user wants to update wins
        if (type == Main.WINS) {

            //asking the user if they want to add or remove wins from a team
            System.out.print("Would you like to add or remove wins from a Team? (a/r): ");
            option = input.nextLine();

            //as long as the input is invalid or wrong, keep asking
            while(!Main.isValid(option, Main.CHECK_ADD_REMOVE)) {
                System.out.print("Would you like to add or remove wins from a Team? (a/r): ");
                option = input.nextLine();
            }
        }

        //If the user wants to update losses
        else if (type == Main.LOSSES) {

            //asking the user if they want to add or remove losses from a team
            System.out.print("Would you like to add or remove losses from a Team? (a/r): ");
            option = input.nextLine();

            //as long as the input is invalid or wrong, keep asking
            while(!Main.isValid(option, Main.CHECK_ADD_REMOVE)) {
                System.out.print("Would you like to add or remove losses from a Team? (a/r): ");
                option = input.nextLine();
            }
        }

        if (teamName == null) {
            //Ask user for the team name
            System.out.print("What is the name of the Team: ");
            team = input.nextLine();

            //As long as the team name cannot be found, keep asking the user
            while(!Main.teamExists(team)) {
                System.out.print("Team Not Found! Please check your spelling and input a Team Name: ");
                team = input.nextLine();
            }
        }
        else {
            team = teamName;
        }

        //find the index of the team Name in the teams ArrayList
        teamIndex = Main.getTeamIndex(team);

        //If the user wants to update wins
        if (type == Main.WINS) {

            //If the user wishes to add wins
            if (option.equalsIgnoreCase("a")) {

                //ask the user how many wins they wish to add
                System.out.print("How many wins would you like to add to Team " + team + ": ");
                option = input.nextLine();

                //As long as the user inputs a non-integer string value, keep looping
                while (!Main.isValid(option, Main.CHECK_INT)) {

                    //Asks for the user input and stores it in the option variable
                    System.out.print("How many wins would you like to add to Team " + team + ": ");
                    option = input.nextLine();
                }

                //multiplier of 1 because the user is wishing to add wins
                multiplier = 1;
            }

            //If the user wishes to remove wins from a team
            else {

                //ask the user how many wins they would like to remove
                System.out.print("How many wins would you like to remove from Team " + team + ": ");
                option = input.nextLine();

                //As long as the user inputs a non-integer string value, keep looping
                while (!Main.isValid(option, Main.CHECK_INT)) {

                    //Asks for the user input and stores it in the option variable
                    System.out.print("How many wins would you like to add to Team " + team + ": ");
                    option = input.nextLine();
                }

                //multiplier to -1 because the user is subtracting wins from the team
                multiplier = -1;
            }
        }

        //If the user wants to update losses
        else if (type == Main.LOSSES) {

            //If the user wishes to add losses
            if (option.equalsIgnoreCase("a")) {

                //ask the user how many losses they wish to add
                System.out.print("How many losses would you like to add to Team " + team + ": ");
                option = input.nextLine();

                //As long as the user inputs a non-integer string value, keep looping
                while (!Main.isValid(option, Main.CHECK_INT)) {

                    //Asks for the user input and stores it in the option variable
                    System.out.print("How many losses would you like to add to Team " + team + ": ");
                    option = input.nextLine();
                }

                //multiplier of 1 because the user is wishing to add losses
                multiplier = 1;
            }

            //If the user wishes to remove losses from a team
            else {

                //ask the user how many losses they would like to remove
                System.out.print("How many losses would you like to remove from Team " + team + ": ");
                option = input.nextLine();

                //As long as the user inputs a non-integer string value, keep looping
                while (!Main.isValid(option, Main.CHECK_INT)) {

                    //Asks for the user input and stores it in the option variable
                    System.out.print("How many losses would you like to add to Team " + team + ": ");
                    option = input.nextLine();
                }

                //multiplier to -1 because the user is subtracting losses from the team
                multiplier = -1;
            }
        }

        //get the amount of wins being added by multiplying the multiplier and the wins amount
        amount = (Integer.parseInt(option)) * multiplier;

        //If the user wants to update wins
        if (type == Main.WINS) {
            if ((Main.teams.get(teamIndex).getWins() + amount) < 0) {
                System.out.println("Team Wins below 0, setting the Team Wins to 0");
                //Update the team Wins
                Main.teams.get(teamIndex).setWins(0);
            }
            else {
                //Update the team Wins
                Main.teams.get(teamIndex).setWins(Main.teams.get(teamIndex).getWins() + amount);
            }
        }

        //If the user wants to update losses
        else if (type == Main.LOSSES) {

            if ((Main.teams.get(teamIndex).getLosses() + amount) < 0) {
                System.out.println("Team Losses below 0, setting the Team Losses to 0");
                //Update the team Wins
                Main.teams.get(teamIndex).setLosses(0);
            }
            else {
                //Update the team Wins
                Main.teams.get(teamIndex).setLosses(Main.teams.get(teamIndex).getLosses() + amount);
            }

        }

        //Let the user know Team Info has been updated
        System.out.println("Team Information has Updated successfully");
        System.out.println("Current " + team + " Wins: " + Main.teams.get(teamIndex).getWins());
        System.out.println("Current " + team + " Losses: " + Main.teams.get(teamIndex).getLosses());
        System.out.println();
    }

    /**
     * This method asks the user what information they would like to update, and update the team info accordingly
     * @param teamNam is the name of the team the user would like to update (this is passed on from another method directly)
     */
    public static void updateTeamInfo(String teamNam) {
        //Variables used to store data from user input
        String option;
        String team;
        String newTeam;
        String teamName = teamNam;

        while (true) {
            //Give the user options to choose from, and let the user type their option
            System.out.print("1) Update Team Name"
                    + "\n2) Update Team Wins"
                    + "\n3) Update Team Losses"
                    + "\nPress 'Enter' to Exit the options"
                    + "\n\n"
                    + "Please input your option: ");
            option = input.nextLine();

            if (option.equals("")) {
                System.out.println("Exiting Options!");
                break;
            }

            //As long as the user inputs a non-integer string value, keep looping
            while (!Main.isValid(option, Main.CHECK_TEAM_INT)) {
                //Asks for the user input and stores it in the option variable
                option = input.nextLine();
            }

            //If the user wants to update the Team Name
            if (option.equalsIgnoreCase("1")) {

                if (teamName == null) {
                    //Ask user for the team name
                    System.out.print("What is the name of the Team you would like to update the Name of?: ");
                    team = input.nextLine();

                    //As long as the team name cannot be found, keep asking the user
                    while(!Main.teamExists(team)) {
                        System.out.print("Team Not Found! Please check your spelling and input a Team Name: ");
                        team = input.nextLine();
                    }
                }
                else {
                    team = teamName;
                }

                //Asks the user for the new Team Name
                System.out.print("Please input the new name for Team " + team + ": ");
                newTeam = input.nextLine();

                //As long as the team name can be found, keep asking the user
                while(Main.teamExists(newTeam)) {
                    System.out.print("Team Already Exists! Please check your spelling and input a new Team Name: ");
                    newTeam = input.nextLine();
                }

                //find the index of the team Name in the teams ArrayList
                int teamIndex = Main.getTeamIndex(team);
                Main.teams.get(teamIndex).setName(newTeam);

                teamName = newTeam;

                System.out.println("Team Name Succesfully Changed!");
            }

            //If the user wants to change the number of wins, use the already created method winsLossToTeam()
            else if (option.equalsIgnoreCase("2")) {
                winsLossToTeam(Main.WINS, teamName);
            }

            //If the user wants to change the number of losses, use the already created method winsLossToTeam()
            else if (option.equalsIgnoreCase("3")) {
                winsLossToTeam(Main.LOSSES, teamName);
            }
        }
    }

    /**
     * This method updates the playerInfo (Skills,Name,Age...) depending on the user Input
     * @param playerNam is the name of the player passed directly from another method, null if called by user using menu options
     */
    public static void updatePlayerInfo(String playerNam) {
        //Variables used to store data from user input
        String option;

        //All the variables used to pass through the method updatePlayerStats
        String player;
        String userName;
        String realName;
        String userInput;

        String playerName = playerNam;

        while(true) {
            //Give the user options to choose from, and let the user type their option
            System.out.print("1) Update Player Username"
                    + "\n2) Update Player Real Name"
                    + "\n3) Update Player Age"
                    + "\n4) Update Player Rank"
                    + "\n5) Update Player Win/Loss Ratio"
                    + "\n6) Update Player's most used Agent"
                    + "\n7) Update Player Headshot %"
                    + "\n8) Update Player Team"
                    + "\nPress 'Enter' to Exit the options"
                    + "\n\n"
                    + "Please input your option: ");
            option = input.nextLine();

            if (option.equalsIgnoreCase("")) {
                System.out.println("Exiting Options!");
                break;
            }

            //As long as the user inputs a non-integer string value, keep looping
            while (!Main.isValid(option, Main.CHECK_PLAYER_INT)) {
                //Asks for the user input and stores it in the option variable
                option = input.nextLine();
            }

            //If the player name being passed through is null
            if (playerName == null) {

                //Ask the user which player they would like to update
                System.out.print("What Player would you like to make changes to: ");
                player = input.nextLine();

                //As long as the inputed player doesn't exist, keep asking the user
                while(!Main.playerExists(player)) {
                    System.out.println("The given Player does not Exist!");
                    System.out.print("Please check your spelling, and input a Player Username again: ");
                    player = input.nextLine();
                }
            }

            //If the player name being passed through is not null, use that as the player
            else {
                player = playerName;
            }

            //If the user wants to change the userName of a player
            if (option.equalsIgnoreCase("1")) {

                //Ask the user what name would they like to change it to
                System.out.print("What username would you like to change " + player + " to: ");
                userName = input.nextLine();

                //As long as a player with the new name given exists, ask the user to input another name.
                while(Main.playerExists(userName)) {
                    System.out.println("The given Player username already Exists!");
                    System.out.print("Please check your spelling, and input a username again: ");
                    userName = input.nextLine();
                }
                int playerIndex = Main.getPlayerIndex(player);
                Main.players.get(playerIndex).setUserName(userName);

                playerName = userName;

                System.out.println("Player Username changed to: " + userName);
            }

            //If the user wants to change the player's real name
            else if (option.equalsIgnoreCase("2")) {

                //Ask the user what name would they like to change it to
                System.out.print("What is the Real Name of " + player + ": ");
                realName = input.nextLine();

                int playerIndex = Main.getPlayerIndex(player);
                Main.players.get(playerIndex).setRealName(realName);

                System.out.println("Player Real Name changed to: " + realName);
            }

            //If the user wants to update the Age of the player
            else if (option.equalsIgnoreCase("3")) {

                //Ask the user what age would they like to change it to
                System.out.print("What is the Age of " + player + ": ");
                userInput = input.nextLine();

                while(!Main.isValid(userInput, Main.CHECK_INT)) {
                    //Ask the user what age would they like to change it to
                    System.out.print("What is the Age of " + player + ": ");
                    userInput = input.nextLine();
                }

                int age = Integer.parseInt(userInput);

                //Update the player Age
                int playerIndex = Main.getPlayerIndex(player);
                Main.players.get(playerIndex).setAge(age);

                System.out.println("Player Age changed to: " + age);
            }

            //If the user wants to update the player Rank
            else if (option.equalsIgnoreCase("4")) {

                //Print all the ranks for the user
                Main.printRanks();

                //Ask the user what rank would they like to change it to
                System.out.print("What is the Rank of " + player + ": ");
                userInput = input.nextLine();

                //As long as the ranks don't match, keep checking and asking user
                while (!Main.rankMatches(userInput)) {
                    System.out.print("Rank not Found, Please check your spelling and type again: ");
                    userInput = input.nextLine();
                }
                //Update the player Rank
                int playerIndex = Main.getPlayerIndex(player);
                Main.players.get(playerIndex).setSkills("Rank", userInput);

                System.out.println("Player Rank changed to: " + userInput);
            }

            //If the user wants to update the Win/Loss Ratio of the player
            else if (option.equalsIgnoreCase("5")) {

                //Ask the user what Ratio would they like to change it to
                System.out.print("What is the Win/Loss Ratio of " + player + ": ");
                userInput = input.nextLine();

                //check if the user input can be converted into a double value, if not keep asking
                while (!Main.isValid(userInput, Main.CHECK_DOUBLE)) {
                    System.out.print("What is the Win/Loss Ratio of " + player + ": ");
                    userInput = input.nextLine();
                }

                //Update the player Win/Loss Ratio
                int playerIndex = Main.getPlayerIndex(player);
                Main.players.get(playerIndex).setSkills("Ratio", userInput);

                System.out.println("Player Win/Loss Ratio changed to: " + userInput);
            }

            //If the user wants to update the Player's most used Agent
            else if (option.equalsIgnoreCase("6")) {

                //Ask the user what agent would they like to change it to
                System.out.print("What is the Most Used Agent of " + player + ": "); //Maybe have a array of Agents like we have array of ranks
                userInput = input.nextLine();

                //Update the player's Most used Agent
                int playerIndex = Main.getPlayerIndex(player);
                Main.players.get(playerIndex).setSkills("Agent", userInput);

                System.out.println("Player Most Used Agent changed to: " + userInput);
            }

            //If the user wants to update the players Head shot %
            else if (option.equalsIgnoreCase("7")) {

                //Ask the user what percentage would they like to change it to
                System.out.print("What is the Headshot % of " + player + ": ");
                userInput = input.nextLine();

                //check if the user input can be converted into a double value and is between 0-100
                while (!Main.isValid(userInput, Main.CHECK_PERCENTAGE)) {
                    System.out.print("What is the Headshot % of " + player + ": ");
                    userInput = input.nextLine();
                }

                //Update the player's Most used Agent
                int playerIndex = Main.getPlayerIndex(player);
                Main.players.get(playerIndex).setSkills("HeadShot", userInput);

                System.out.println("Player's HeadShot Percentage changed to: " + userInput + "%");
            }

            //If the user wants to update the player Team
            else if (option.equalsIgnoreCase("8")) {

                //Ask the user what Team they would like to change it to
                System.out.print("What Team is " + player + " in: ");
                userInput = input.nextLine();

                //check if the user input can be converted into a double value and is between 0-100
                while (!Main.teamExists(userInput)) {
                    System.out.println("Invalid Team Name! Please enter the Team Name Again");
                    System.out.print("What Team is " + player + " in: ");
                    userInput = input.nextLine();
                }

                //Update the player's Team
                int playerIndex = Main.getPlayerIndex(player);
                int teamIndex = Main.getTeamIndex(userInput);
                Main.players.get(playerIndex).setTeam(Main.teams.get(teamIndex));

                System.out.println("Player's Team Change to: " + userInput);
            }
        }
    }

    /**
     * This method prints all the information available in the system
     */
    public static void printAll() {
        //All arrayLists holding the individual statistics of a player
        ArrayList<String> playerUsername = new ArrayList<String>();
        ArrayList<String> playerTeam = new ArrayList<String>();
        ArrayList<String> playerName = new ArrayList<String>();
        ArrayList<String> playerAge = new ArrayList<String>();
        ArrayList<String> playerRank = new ArrayList<String>();
        ArrayList<String> playerRatio = new ArrayList<String>();
        ArrayList<String> playerAgent = new ArrayList<String>();
        ArrayList<String> playerHeadShot = new ArrayList<String>();

        //All the arrayLists holding individual statistics of a team
        ArrayList<String> teamName = new ArrayList<String>();
        ArrayList<String> teamWins = new ArrayList<String>();
        ArrayList<String> teamLoss = new ArrayList<String>();
        ArrayList<String> teamRatio = new ArrayList<String>();

        //The arrayLists holding all the information about a player and a team
        ArrayList<String> playerData = new ArrayList<String>();
        ArrayList<String> teamData = new ArrayList<String>();


        for (int i = 0; i < Main.players.size(); i++) {

            //Gets the statistics of all players and adds them to their specified arrayLists
            playerUsername.add(Main.players.get(i).getUserName());
            playerTeam.add(Main.players.get(i).getTeam().getName());
            playerName.add(Main.players.get(i).getRealName());
            playerAge.add(String.valueOf(Main.players.get(i).getAge()));
            playerRank.add(Main.players.get(i).getSkills("Rank"));
            playerRatio.add(Main.players.get(i).getSkills("Ratio"));
            playerAgent.add(Main.players.get(i).getSkills("Agent"));
            playerHeadShot.add(Main.players.get(i).getSkills("HeadShot"));
        }

        //For loop to go through all the teams and adding their statistics to their specified arrayLists
        for (int i = 0; i < Main.teams.size(); i++) {
            teamName.add(Main.teams.get(i).getName());
            teamWins.add(String.valueOf(Main.teams.get(i).getWins()));
            teamLoss.add(String.valueOf(Main.teams.get(i).getLosses()));
            double wins = (double)Main.teams.get(i).getWins();
            double losses = (double)Main.teams.get(i).getLosses();
            double ratio = wins/losses;
            teamRatio.add(String.valueOf(ratio));
        }

        //Adding the heading for the player data
        playerData.add("Username" + Main.printGap(playerUsername, -1, "Username")
                + "Team" + Main.printGap(playerTeam, -1, "Team")
                + "Real Name" + Main.printGap(playerName, -1, "Real Name")
                + "Age" + Main.printGap(playerAge, -1, "Age")
                + "Rank" + Main.printGap(playerRank, -1, "Rank")
                + "W/L Ratio" + Main.printGap(playerRatio, -1, "W/L Ratio")
                + "Most Used Agent" + Main.printGap(playerAgent, -1, "Most Used Agent")
                + "Headshot %" + Main.printGap(playerHeadShot, -1, "Headshot %"));

        //Adding all the information about the player into the playerData arrayList
        for (int i = 0; i < Main.players.size(); i++) {
            playerData.add(playerUsername.get(i) + Main.printGap(playerUsername, i, "Username")
                    + playerTeam.get(i) + Main.printGap(playerTeam, i, "Team")
                    + playerName.get(i) + Main.printGap(playerName, i, "Real Name")
                    + playerAge.get(i) + Main.printGap(playerAge, i, "Age")
                    + playerRank.get(i) + Main.printGap(playerRank, i, "Rank")
                    + playerRatio.get(i) + Main.printGap(playerRatio, i, "W/L Ratio")
                    + playerAgent.get(i) + Main.printGap(playerAgent, i, "Most Used Agent")
                    + playerHeadShot.get(i) + Main.printGap(playerHeadShot, i, "Headshot %"));
        }

        //Adding the heading for the team data
        teamData.add("Name" + Main.printGap(teamName, -1, "Name")
                + "Wins" + Main.printGap(teamWins, -1, "Wins")
                + "Losses" + Main.printGap(teamLoss, -1, "Losses")
                + "W/L Ratio" + Main.printGap(teamRatio, -1, "W/L Ratio"));

        //adding all the team info in the teamData ArrayList
        for (int i = 0; i < Main.teams.size(); i++) {
            teamData.add(teamName.get(i) + Main.printGap(teamName, i, "Name")
                    + teamWins.get(i) + Main.printGap(teamWins, i, "Wins")
                    + teamLoss.get(i) + Main.printGap(teamLoss, i, "Losses")
                    + teamRatio.get(i) + Main.printGap(teamRatio, i, "W/L Ratio"));
        }

        //printing all the information
        System.out.println("\nPlayers Information:");
        for (int i = 0; i < playerData.size(); i++) {
            System.out.println(playerData.get(i));
        }
        System.out.println("\nTeams Information:");
        for (int i = 0; i < teamData.size(); i++) {
            System.out.println(teamData.get(i));
        }
        System.out.println();

    }


    /**
     * This method prints all the information available in the system
     */
    public static String printAllFX() {

        String data = "";

        //All arrayLists holding the individual statistics of a player
        ArrayList<String> playerUsername = new ArrayList<String>();
        ArrayList<String> playerTeam = new ArrayList<String>();
        ArrayList<String> playerName = new ArrayList<String>();
        ArrayList<String> playerAge = new ArrayList<String>();
        ArrayList<String> playerRank = new ArrayList<String>();
        ArrayList<String> playerRatio = new ArrayList<String>();
        ArrayList<String> playerAgent = new ArrayList<String>();
        ArrayList<String> playerHeadShot = new ArrayList<String>();

        //All the arrayLists holding individual statistics of a team
        ArrayList<String> teamName = new ArrayList<String>();
        ArrayList<String> teamWins = new ArrayList<String>();
        ArrayList<String> teamLoss = new ArrayList<String>();
        ArrayList<String> teamRatio = new ArrayList<String>();

        //The arrayLists holding all the information about a player and a team
        ArrayList<String> playerData = new ArrayList<String>();
        ArrayList<String> teamData = new ArrayList<String>();


        for (int i = 0; i < Main.players.size(); i++) {

            //Gets the statistics of all players and adds them to their specified arrayLists
            playerUsername.add(Main.players.get(i).getUserName());
            if (Main.players.get(i).getTeam() != null) {
            	playerTeam.add(Main.players.get(i).getTeam().getName());
            }
            else {
            	playerTeam.add("N/A");
            }
            playerName.add(Main.players.get(i).getRealName());
            playerAge.add(String.valueOf(Main.players.get(i).getAge()));
            playerRank.add(Main.players.get(i).getSkills("Rank"));
            playerRatio.add(Main.players.get(i).getSkills("Ratio"));
            playerAgent.add(Main.players.get(i).getSkills("Agent"));
            playerHeadShot.add(Main.players.get(i).getSkills("HeadShot"));
        }

        //For loop to go through all the teams and adding their statistics to their specified arrayLists
        for (int i = 0; i < Main.teams.size(); i++) {
            teamName.add(Main.teams.get(i).getName());
            teamWins.add(String.valueOf(Main.teams.get(i).getWins()));
            teamLoss.add(String.valueOf(Main.teams.get(i).getLosses()));
            double wins = (double)Main.teams.get(i).getWins();
            double losses = (double)Main.teams.get(i).getLosses();
            double ratio = wins/losses;
            teamRatio.add(String.valueOf(ratio));
        }

        //Adding the heading for the player data
        playerData.add("Username" + Main.printGap(playerUsername, -1, "Username")
                + "Team" + Main.printGap(playerTeam, -1, "Team")
                + "Real Name" + Main.printGap(playerName, -1, "Real Name")
                + "Age" + Main.printGap(playerAge, -1, "Age")
                + "Rank" + Main.printGap(playerRank, -1, "Rank")
                + "W/L Ratio" + Main.printGap(playerRatio, -1, "W/L Ratio")
                + "Most Used Agent" + Main.printGap(playerAgent, -1, "Most Used Agent")
                + "Headshot %" + Main.printGap(playerHeadShot, -1, "Headshot %"));

        //Adding all the information about the player into the playerData arrayList
        for (int i = 0; i < Main.players.size(); i++) {
            playerData.add(playerUsername.get(i) + Main.printGap(playerUsername, i, "Username")
                    + playerTeam.get(i) + Main.printGap(playerTeam, i, "Team")
                    + playerName.get(i) + Main.printGap(playerName, i, "Real Name")
                    + playerAge.get(i) + Main.printGap(playerAge, i, "Age")
                    + playerRank.get(i) + Main.printGap(playerRank, i, "Rank")
                    + playerRatio.get(i) + Main.printGap(playerRatio, i, "W/L Ratio")
                    + playerAgent.get(i) + Main.printGap(playerAgent, i, "Most Used Agent")
                    + playerHeadShot.get(i) + Main.printGap(playerHeadShot, i, "Headshot %"));
        }

        //Adding the heading for the team data
        teamData.add("Name" + Main.printGap(teamName, -1, "Name")
                + "Wins" + Main.printGap(teamWins, -1, "Wins")
                + "Losses" + Main.printGap(teamLoss, -1, "Losses")
                + "W/L Ratio" + Main.printGap(teamRatio, -1, "W/L Ratio"));

        //adding all the team info in the teamData ArrayList
        for (int i = 0; i < Main.teams.size(); i++) {
            teamData.add(teamName.get(i) + Main.printGap(teamName, i, "Name")
                    + teamWins.get(i) + Main.printGap(teamWins, i, "Wins")
                    + teamLoss.get(i) + Main.printGap(teamLoss, i, "Losses")
                    + teamRatio.get(i) + Main.printGap(teamRatio, i, "W/L Ratio"));
        }

        //printing all the information
        data = data + "Players Information:\n";
        for (int i = 0; i < playerData.size(); i++) {
            data = data + playerData.get(i);
            data = data + "\n";
        }
        data = data + "\nTeams Information:\n";
        for (int i = 0; i < teamData.size(); i++) {
            data = data + teamData.get(i);
            data = data + "\n";
        }
        return data;
    }

    /**
     * This method prints all the teams, or top # of teams depending on user Input
     * @param all
     */
    public static void printTeams(boolean all) {
        //String variable to hold user input, and top integer to store the number of elements to print
        String option;
        int top = 0;

        //All the arrayLists holding individual statistics of a team
        ArrayList<String> teams = new ArrayList<String>();
        ArrayList<String> teamWins = new ArrayList<String>();
        ArrayList<String> teamLoss = new ArrayList<String>();
        ArrayList<String> teamRatio = new ArrayList<String>();

        //For loop to go through all the teams and adding their statistics to their specified arrayLists
        for (int i = 0; i < Main.teams.size(); i++) {
            teams.add(Main.teams.get(i).getName());
            teamWins.add(String.valueOf(Main.teams.get(i).getWins()));
            teamLoss.add(String.valueOf(Main.teams.get(i).getLosses()));
            double wins = (double)Main.teams.get(i).getWins();
            double losses = (double)Main.teams.get(i).getLosses();
            double ratio = wins/losses;
            teamRatio.add(String.valueOf(ratio));
        }

        //If the user wants to print all the team names
        if (all) {

            //Adding the heading for the team data
            System.out.println("Name" + Main.printGap(teams, -1, "Name")
                    + "Wins" + Main.printGap(teamWins, -1, "Wins")
                    + "Losses" + Main.printGap(teamLoss, -1, "Losses")
                    + "W/L Ratio" + Main.printGap(teamRatio, -1, "W/L Ratio"));

            //adding all the team info in the teamData ArrayList
            for (int i = 0; i < Main.teams.size(); i++) {
                System.out.println(teams.get(i) + Main.printGap(teams, i, "Name")
                        + teamWins.get(i) + Main.printGap(teamWins, i, "Wins")
                        + teamLoss.get(i) + Main.printGap(teamLoss, i, "Losses")
                        + teamRatio.get(i) + Main.printGap(teamRatio, i, "W/L Ratio"));
            }
        }

        //If the user doesn't want to print all the teams
        else {

            //Ask the user how many team they would like to print
            System.out.print("1) Top 5"
                    + "\n2) Top 10"
                    + "\n3) Custom # of Top List"
                    + "\n\n"
                    + "Please Input your option: ");
            option = input.nextLine();

            //As long as the user inputs a non-integer or unusable string value, keep looping
            while (!Main.isValid(option, Main.CHECK_TEAM_INT)) {
                //Asks for the user input and stores it in the option variable
                option = input.nextLine();
            }

            //If they want to print top 5 teams
            if (option.equalsIgnoreCase("1")) {

                //Check if the number of teams that exist are lower than 5
                if (5 > teams.size()) {

                    //If so, let the user know, there aren't enough teams, and the program will print all the teams
                    System.out.println("The number of teams that are in the system are less than the "
                            + "number of teams being asked for\n"
                            + "All " + teams.size() + " Teams will be printed out!\n");

                    //set the top variable to the number of teams that exist
                    top = teams.size();
                }

                //If the number of teams is greater than number asked to print, then make the integer "top" equal to 5
                else {
                    top = 5;
                }
            }

            //If the user wants to print Top 10 list of teams
            else if (option.equalsIgnoreCase("2")) {

                //Check if the number of teams that exist are lower then 10
                if (10 > teams.size()) {

                    //If so, let the user know that there aren't enough team, and the program will print all the teams
                    System.out.println("The number of teams that are in the system are less than the "
                            + "number of teams being asked for\n"
                            + "All " + teams.size() + " Teams will be printed out!\n");

                    //Set the top variable to number of teams
                    top = teams.size();
                }

                //If there are enough teams, set top to 10
                else {
                    top = 10;
                }
            }
            //If the user wants to choose a custom number
            else {

                //Ask the user how many teams they would like to make a list of
                System.out.print("How many Teams would you like to see in the Top List?: ");
                String temp = input.nextLine();

                //As long as the input isn't valid, keep asking the user
                while (!Main.isValid(temp, Main.CHECK_INT)) {
                    System.out.print("How many Teams would you like to see in the Top List?: ");
                    temp = input.nextLine();
                }

                //If the number of teams in the system is less than the number required in the list
                if (Integer.parseInt(temp) > teams.size()) {

                    //Let the user know, and set the top variable to number of teams available
                    System.out.println("The number of teams that are in the system are less than the "
                            + "number of teams being asked for\n"
                            + "All " + teams.size() + " Teams will be printed out!");
                    top = teams.size();
                }

                //If there are enough teams, set the top to the user input
                else {
                    top = Integer.parseInt(temp);
                }
            }

            //Ask the user about what statistics they would like to use to sort the Teams
            System.out.print("1) Sort Using Wins"
                    + "\n2) Sort Using Losses"
                    + "\n3) Sort Using Wins/Losses Ratio"
                    + "\n\n"
                    + "Please input your option: ");
            option = input.nextLine();

            //As long as the user inputs a non-integer or unusable string value, keep looping
            while (!Main.isValid(option, Main.CHECK_TEAM_INT)) {
                //Asks for the user input and stores it in the option variable
                option = input.nextLine();
            }

            //Make a new ArrayList to store the sorted Teams
            ArrayList<String> sorted = new ArrayList<String>();

            //Call a method that would sort the ArrayLists depending on what the user chose as a sorting statistic
            sorted = sortTeams(teams, Integer.parseInt(option));

            //Print out the ArrayList that was returned from sort() method
            for (int i = 0; i < top; i++) {
                System.out.println(sorted.get(i));
            }
        }
        System.out.println();
    }

    /**
     * This method prints all the teams, or top # of teams depending on user Input
     * @param all
     */
    public static String printTeamsFX(boolean all, String sort, int top) {
        //String variable to hold user input, and top integer to store the number of elements to print
        String option = "";
        String data = "";

        //All the arrayLists holding individual statistics of a team
        ArrayList<String> teams = new ArrayList<String>();
        ArrayList<String> teamWins = new ArrayList<String>();
        ArrayList<String> teamLoss = new ArrayList<String>();
        ArrayList<String> teamRatio = new ArrayList<String>();

        //For loop to go through all the teams and adding their statistics to their specified arrayLists
        for (int i = 0; i < Main.teams.size(); i++) {
            teams.add(Main.teams.get(i).getName());
            teamWins.add(String.valueOf(Main.teams.get(i).getWins()));
            teamLoss.add(String.valueOf(Main.teams.get(i).getLosses()));
            double wins = (double)Main.teams.get(i).getWins();
            double losses = (double)Main.teams.get(i).getLosses();
            double ratio = wins/losses;
            teamRatio.add(String.valueOf(ratio));
        }

        //If the user wants to print all the team names
        if (all) {

            //Adding the heading for the team data
            data = data + "Name" + Main.printGap(teams, -1, "Name")
                    + "Wins" + Main.printGap(teamWins, -1, "Wins")
                    + "Losses" + Main.printGap(teamLoss, -1, "Losses")
                    + "W/L Ratio" + Main.printGap(teamRatio, -1, "W/L Ratio") + "\n";

            //adding all the team info in the teamData ArrayList
            for (int i = 0; i < Main.teams.size(); i++) {
                data = data + teams.get(i) + Main.printGap(teams, i, "Name")
                        + teamWins.get(i) + Main.printGap(teamWins, i, "Wins")
                        + teamLoss.get(i) + Main.printGap(teamLoss, i, "Losses")
                        + teamRatio.get(i) + Main.printGap(teamRatio, i, "W/L Ratio") + "\n";
            }
        }

        //If the user doesn't want to print all the teams
        else {

            if (sort.equals("Wins")) {
                option = "1";
            }
            else if (sort.equals("Losses")) {
                option = "2";
            }
            else if (sort.equals("Ratio")) {
                option = "3";
            }

            if (top == -1) {
                top = teams.size();
            }

            //Make a new ArrayList to store the sorted Teams
            ArrayList<String> sorted = new ArrayList<String>();

            //Call a method that would sort the ArrayLists depending on what the user chose as a sorting statistic
            sorted = sortTeams(teams, Integer.parseInt(option));

            //Print out the ArrayList that was returned from sort() method
            for (int i = 0; i < top + 1; i++) {
                data = data + sorted.get(i) + "\n";
            }
        }
        return data;
    }

    /**
     * This method prints all the players that exist across all teams, if the boolean all is true, else
     * this method will print all the players from a specific team if the boolean all is false
     *
     * @param players is a 2D arrayList that contains all the players names
     * @param teams is an arrayList that contains all the names of the teams
     * @param all is a boolean that if true will make sure all the players are printed, if not then only specific ones
     */
    public static void printPlayers(boolean all) {
        String option;

        //All arrayLists holding the individual statistics of a player
        ArrayList<String> playerUsername = new ArrayList<String>();
        ArrayList<String> playerTeam = new ArrayList<String>();
        ArrayList<String> playerName = new ArrayList<String>();
        ArrayList<String> playerAge = new ArrayList<String>();
        ArrayList<String> playerRank = new ArrayList<String>();
        ArrayList<String> playerRatio = new ArrayList<String>();
        ArrayList<String> playerAgent = new ArrayList<String>();
        ArrayList<String> playerHeadShot = new ArrayList<String>();

        //For loop with a count variable to see what iteration it is on in total
        for (int i = 0; i < Main.players.size(); i++) {

            //Gets the statistics of all players and adds them to their specified arrayLists
            playerUsername.add(Main.players.get(i).getUserName());
            playerTeam.add(Main.players.get(i).getTeam().getName());
            playerName.add(Main.players.get(i).getRealName());
            playerAge.add(String.valueOf(Main.players.get(i).getAge()));
            playerRank.add(Main.players.get(i).getSkills("Rank"));
            playerRatio.add(Main.players.get(i).getSkills("Ratio"));
            playerAgent.add(Main.players.get(i).getSkills("Agent"));
            playerHeadShot.add(Main.players.get(i).getSkills("HeadShot"));
        }

        //If all boolean is true
        if (all) {

            //Adding the heading for the player data
            System.out.println("Username" + Main.printGap(playerUsername, -1, "Username")
                    + "Team" + Main.printGap(playerTeam, -1, "Team")
                    + "Real Name" + Main.printGap(playerName, -1, "Real Name")
                    + "Age" + Main.printGap(playerAge, -1, "Age")
                    + "Rank" + Main.printGap(playerRank, -1, "Rank")
                    + "W/L Ratio" + Main.printGap(playerRatio, -1, "W/L Ratio")
                    + "Most Used Agent" + Main.printGap(playerAgent, -1, "Most Used Agent")
                    + "Headshot %" + Main.printGap(playerHeadShot, -1, "Headshot %"));

            //Adding all the information about the player into the playerData arrayList
            for (int i = 0; i < Main.players.size(); i++) {
                System.out.println(playerUsername.get(i) + Main.printGap(playerUsername, i, "Username")
                        + playerTeam.get(i) + Main.printGap(playerTeam, i, "Team")
                        + playerName.get(i) + Main.printGap(playerName, i, "Real Name")
                        + playerAge.get(i) + Main.printGap(playerAge, i, "Age")
                        + playerRank.get(i) + Main.printGap(playerRank, i, "Rank")
                        + playerRatio.get(i) + Main.printGap(playerRatio, i, "W/L Ratio")
                        + playerAgent.get(i) + Main.printGap(playerAgent, i, "Most Used Agent")
                        + playerHeadShot.get(i) + Main.printGap(playerHeadShot, i, "Headshot %"));
            }
        }

        //if all is false
        else {

            //Ask the user which team they would like to see the players of
            System.out.print("Which team would you like to print the players of: ");
            option = input.nextLine();

            //as long as the team isn't found keep asking the user
            while(!Main.teamExists(option)) {
                System.out.print("Team not Found in System, Please try Again: ");
                option = input.nextLine();
            }

            //Since the index of the team would be the same index containing all the players of that team, print the heading
            System.out.println("Name" + Main.printGap(playerUsername, -1, "Name") + "Team");

            //print all the players from that same index from players 2D arrayList
            for (int i = 0; i < playerUsername.size(); i++) {
                if (option.equalsIgnoreCase(playerTeam.get(i))) {
                    System.out.println(playerUsername.get(i) + Main.printGap(playerUsername, i, "Username")
                            + playerTeam.get(i) + Main.printGap(playerTeam, i, "Team")
                            + playerName.get(i) + Main.printGap(playerName, i, "Real Name")
                            + playerAge.get(i) + Main.printGap(playerAge, i, "Age")
                            + playerRank.get(i) + Main.printGap(playerRank, i, "Rank")
                            + playerRatio.get(i) + Main.printGap(playerRatio, i, "W/L Ratio")
                            + playerAgent.get(i) + Main.printGap(playerAgent, i, "Most Used Agent")
                            + playerHeadShot.get(i) + Main.printGap(playerHeadShot, i, "Headshot %"));
                }

            }

        }
    }

    /**
     * This method prints all the players that exist across all teams, if the boolean all is true, else
     * this method will print all the players from a specific team if the boolean all is false
     *
     * @param players is a 2D arrayList that contains all the players names
     * @param teams is an arrayList that contains all the names of the teams
     * @param all is a boolean that if true will make sure all the players are printed, if not then only specific ones
     */
    public static String printPlayersFX(boolean all, String team) {
        String data = "";

        //All arrayLists holding the individual statistics of a player
        ArrayList<String> playerUsername = new ArrayList<String>();
        ArrayList<String> playerTeam = new ArrayList<String>();
        ArrayList<String> playerName = new ArrayList<String>();
        ArrayList<String> playerAge = new ArrayList<String>();
        ArrayList<String> playerRank = new ArrayList<String>();
        ArrayList<String> playerRatio = new ArrayList<String>();
        ArrayList<String> playerAgent = new ArrayList<String>();
        ArrayList<String> playerHeadShot = new ArrayList<String>();

        //For loop with a count variable to see what iteration it is on in total
        for (int i = 0; i < Main.players.size(); i++) {

            //Gets the statistics of all players and adds them to their specified arrayLists
            playerUsername.add(Main.players.get(i).getUserName());
            playerTeam.add(Main.players.get(i).getTeam().getName());
            playerName.add(Main.players.get(i).getRealName());
            playerAge.add(String.valueOf(Main.players.get(i).getAge()));
            playerRank.add(Main.players.get(i).getSkills("Rank"));
            playerRatio.add(Main.players.get(i).getSkills("Ratio"));
            playerAgent.add(Main.players.get(i).getSkills("Agent"));
            playerHeadShot.add(Main.players.get(i).getSkills("HeadShot"));
        }

        //Adding the heading for the player data
        data = data + "Username" + Main.printGap(playerUsername, -1, "Username")
                + "Team" + Main.printGap(playerTeam, -1, "Team")
                + "Real Name" + Main.printGap(playerName, -1, "Real Name")
                + "Age" + Main.printGap(playerAge, -1, "Age")
                + "Rank" + Main.printGap(playerRank, -1, "Rank")
                + "W/L Ratio" + Main.printGap(playerRatio, -1, "W/L Ratio")
                + "Most Used Agent" + Main.printGap(playerAgent, -1, "Most Used Agent")
                + "Headshot %" + Main.printGap(playerHeadShot, -1, "Headshot %") + "\n";


        //If all boolean is true
        if (all) {

            //Adding all the information about the player into the playerData arrayList
            for (int i = 0; i < Main.players.size(); i++) {
                data = data + playerUsername.get(i) + Main.printGap(playerUsername, i, "Username")
                        + playerTeam.get(i) + Main.printGap(playerTeam, i, "Team")
                        + playerName.get(i) + Main.printGap(playerName, i, "Real Name")
                        + playerAge.get(i) + Main.printGap(playerAge, i, "Age")
                        + playerRank.get(i) + Main.printGap(playerRank, i, "Rank")
                        + playerRatio.get(i) + Main.printGap(playerRatio, i, "W/L Ratio")
                        + playerAgent.get(i) + Main.printGap(playerAgent, i, "Most Used Agent")
                        + playerHeadShot.get(i) + Main.printGap(playerHeadShot, i, "Headshot %") + "\n";
            }
        }

        //if all is false
        else {

            //print all the players from that same index from players 2D arrayList
            for (int i = 0; i < playerUsername.size(); i++) {
                if (team.equalsIgnoreCase(playerTeam.get(i))) {
                    data = data + playerUsername.get(i) + Main.printGap(playerUsername, i, "Username")
                            + playerTeam.get(i) + Main.printGap(playerTeam, i, "Team")
                            + playerName.get(i) + Main.printGap(playerName, i, "Real Name")
                            + playerAge.get(i) + Main.printGap(playerAge, i, "Age")
                            + playerRank.get(i) + Main.printGap(playerRank, i, "Rank")
                            + playerRatio.get(i) + Main.printGap(playerRatio, i, "W/L Ratio")
                            + playerAgent.get(i) + Main.printGap(playerAgent, i, "Most Used Agent")
                            + playerHeadShot.get(i) + Main.printGap(playerHeadShot, i, "Headshot %") + "\n";
                }

            }

        }
        return data;
    }

    /**
     * This method prints the top # of players depending on the user input to whether print top 5, 10, 15
     * or a number of their choice. The user will also choose what type of statistic will be used to sort the players
     *
     * @param all This is a boolean if true, then prints all the player, if false then prints top # of players
     */
    public static void printTopPlayers(boolean all) {
        //String variable to hold user input, and top integer to store the number of elements to print
        String option;
        String team = "";
        int size = 0;
        int top = 0;

        //Create a new arrayList
        ArrayList<String> allPlayers = new ArrayList<String>();

        //fill the new arrayList with all the players
        for (int i = 0; i < Main.players.size(); i++) {
            allPlayers.add(Main.players.get(i).getUserName());
        }

        //if not all players need to be printed
        if (!all) {

            //Ask the user which team they would like to see the players of
            System.out.print("Which team would you like to print the players of: ");
            team = input.nextLine();

            //as long as the team isn't found keep asking the user
            while(!Main.teamExists(team)) {
                System.out.print("Team not Found in System, Please try Again: ");
                team = input.nextLine();
            }

            for (int i = 0; i < Main.players.size(); i++) {
                if (Main.players.get(i).getTeam().getName().equalsIgnoreCase(team)) {
                    size++;
                }
            }
        }
        else {
            size = allPlayers.size();
        }


        //Ask the user how many players they would like to print
        System.out.print("1) Top 5"
                + "\n2) Top 10"
                + "\n3) Custom # of Top List"
                + "\n\n"
                + "Please Input your option: ");
        option = input.nextLine();

        //As long as the user inputs a non-integer or unusable string value, keep looping
        while (!Main.isValid(option, Main.CHECK_TEAM_INT)) {
            //Asks for the user input and stores it in the option variable
            option = input.nextLine();
        }

        //If they want to print top 5 players
        if (option.equalsIgnoreCase("1")) {

            //Check if the number of players that exist are lower than 5
            if (5 > allPlayers.size()) {

                //If so, let the user know, there aren't enough players, and the program will print all the players
                System.out.println("The number of players that are in the system are less than the "
                        + "number of players being asked for\n"
                        + "All " + size + " Players will be printed out!\n");

                //set the top variable to the number of players that exist
                if (!all) {
                    top = size;
                } else {
                    top = allPlayers.size();
                }
            }

            //If the number of players is greater than number asked to print, then make the integer "top" equal to 5
            else {
                if (!all) {
                    top = size;
                } else {
                    top = 5;
                }
            }
        }

        //If the user wants to print Top 10 list of players
        else if (option.equalsIgnoreCase("2")) {

            //Check if the number of players that exist are lower then 10
            if (10 > allPlayers.size()) {

                //If so, let the user know that there aren't enough players, and the program will print all the players
                System.out.println("The number of players that are in the system are less than the "
                        + "number of players being asked for\n"
                        + "All " + size + " Players will be printed out!\n");

                //Set the top variable to number of players
                if (!all) {
                    top = size;
                } else {
                    top = allPlayers.size();
                }
            }

            //If there are enough players, set top to 10
            else {
                if (!all) {
                    top = size;
                } else {
                    top = 5;
                }
            }
        }

        //If the user wants to choose a custom number
        else {

            //Ask the user how many players they would like to make a list of
            System.out.print("How many Players would you like to see in the Top List?: ");
            String temp = input.nextLine();

            //As long as the input isn't valid, keep asking the user
            while (!Main.isValid(temp, Main.CHECK_INT)) {
                System.out.print("How many Players would you like to see in the Top List?: ");
                temp = input.nextLine();
            }

            //If the number of players in the system is less than the number required in the list
            if (Integer.parseInt(temp) > allPlayers.size()) {

                //Let the user know, and set the top variable to number of players available
                System.out.println("The number of players that are in the system are less than the "
                        + "number of players being asked for\n"
                        + "All " + size + " Players will be printed out!");

                if (!all) {
                    top = size;
                } else {
                    top = allPlayers.size();
                }
            }

            //If there are enough players, set the top to the user input
            else {
                if (!all) {
                    top = size;
                } else {
                    top = Integer.parseInt(temp);
                }
            }
        }

        //Ask the user about what statistics they would like to use to sort the Players
        System.out.print("1) Sort Using Player Rank"
                + "\n2) Sort Using Headhot %"
                + "\n3) Sort Using Wins/Losses Ratio"
                + "\n\n"
                + "Please input your option: ");
        option = input.nextLine();

        //As long as the user inputs a non-integer or unusable string value, keep looping
        while (!Main.isValid(option, Main.CHECK_TEAM_INT)) {
            //Asks for the user input and stores it in the option variable
            option = input.nextLine();
        }

        //Make a new ArrayList to store the sorted Players
        ArrayList<String> sorted = new ArrayList<String>();

        //if all players need to be printed, then copy the list of all players, and use that to sort
        if (all) {
            ArrayList<String> list = new ArrayList<String>(allPlayers);

            //Call a method that would sort the ArrayLists depending on what the user chose as a sorting statistic
            sorted = sortPlayers(list, Integer.parseInt(option));
        }

        //if not all players need to printed
        else {

            //make a new arrayList, and fill it with all the players that are needed to be sorted
            ArrayList<String> list = new ArrayList<String>();

            //add all the players from that same index from players 2D arrayList to the list arrayList
            for (int i = 0; i < Main.players.size(); i++) {
                if (Main.players.get(i).getTeam().getName().equalsIgnoreCase(team)) {
                    list.add(Main.players.get(i).getUserName());
                }
            }

            //Call a method that would sort the ArrayLists depending on what the user chose as a sorting statistic
            sorted = sortPlayers(list, Integer.parseInt(option));
        }

        //Print out the ArrayList that was returned from sort() method
        for (int i = 0; i < top; i++) {
            System.out.println(sorted.get(i));
        }
    }

    /**
     * This method prints the top # of players depending on the user input to whether print top 5, 10, 15
     * or a number of their choice. The user will also choose what type of statistic will be used to sort the players
     *
     * @param all This is a boolean if true, then prints all the player, if false then prints top # of players
     */
    public static String printTopPlayersFX(boolean all, String team, String sort, int top) {
        //String variable to hold user input, and top integer to store the number of elements to print
        String option = "";
        String data = "";

        //Create a new arrayList
        ArrayList<String> allPlayers = new ArrayList<String>();

        //fill the new arrayList with all the players
        for (int i = 0; i < Main.players.size(); i++) {
            allPlayers.add(Main.players.get(i).getUserName());
        }

        if (sort.equals("Rank")) {
            option = "1";
        }
        else if (sort.equals("Headshot")) {
            option = "2";
        }
        else if (sort.equals("Ratio")) {
            option = "3";
        }

        //Make a new ArrayList to store the sorted Players
        ArrayList<String> sorted = new ArrayList<String>();

        //if all players need to be printed, then copy the list of all players, and use that to sort
        if (all) {

            if (top == -1) {
                top = allPlayers.size();
            }

            ArrayList<String> list = new ArrayList<String>(allPlayers);

            //Call a method that would sort the ArrayLists depending on what the user chose as a sorting statistic
            sorted = sortPlayers(list, Integer.parseInt(option));
        }

        //if not all players need to printed
        else {

            //make a new arrayList, and fill it with all the players that are needed to be sorted
            ArrayList<String> list = new ArrayList<String>();

            //add all the players from that same index from players 2D arrayList to the list arrayList
            for (int i = 0; i < Main.players.size(); i++) {
                if (Main.players.get(i).getTeam().getName().equalsIgnoreCase(team)) {
                    list.add(Main.players.get(i).getUserName());
                    System.out.println(team + "  " + Main.players.get(i).getUserName());
                }
            }

            if (top == -1) {
                top = list.size();
            }

            //Call a method that would sort the ArrayLists depending on what the user chose as a sorting statistic
            sorted = sortPlayers(list, Integer.parseInt(option));
        }

        //Print out the ArrayList that was returned from sort() method
        for (int i = 0; i < top + 1; i++) {
            data = data + sorted.get(i) + "\n";
        }
        return data;
    }

    /**
     * This method sorts the given team ArrayList using a specific statistic chosen by the user
     *
     * @param teams is the ArrayList that holds the team names, and needs to be sorted
     * @param type is an integer that is used to recognize what kind of statistic user wants to use
     * @return returns the sorted ArrayList
     */
    private static ArrayList<String> sortTeams(ArrayList<String> teams, int type) {

        //dupli ArrayList is the duplicate of the original teams ArrayList, this is so we don't change the actual ArrayList
        ArrayList<String> dupli = new ArrayList<String>(teams);
        ArrayList<String> dupli2 = new ArrayList<String>(teams);
        ArrayList<String> teamWins = new ArrayList<String>();
        ArrayList<String> teamLoss = new ArrayList<String>();
        ArrayList<String> teamRatio = new ArrayList<String>();

        //value ArrayList to hold the number values of the statistics
        ArrayList<Double> value = new ArrayList<Double>();

        //go through the duplicate teams ArrayList
        for (int i = 0; i < dupli.size(); i++) {

            //If the user wants to use wins, copy all wins into the value ArrayList
            if (type == 1) {
                value.add((double)Main.teams.get(Main.getTeamIndex(dupli.get(i))).getWins());
                teamLoss.add(String.valueOf(Main.teams.get(Main.getTeamIndex(dupli.get(i))).getLosses()));
                double wins = (double)Main.teams.get(Main.getTeamIndex(dupli.get(i))).getWins();
                double losses = (double)Main.teams.get(Main.getTeamIndex(dupli.get(i))).getLosses();
                double ratio = wins/losses;
                teamRatio.add(String.valueOf(ratio));
            }

            //If the user wants to use losses, copy all the losses into the value ArrayList
            else if (type == 2) {
                value.add((double)Main.teams.get(Main.getTeamIndex(dupli.get(i))).getLosses());
                teamWins.add(String.valueOf(Main.teams.get(Main.getTeamIndex(dupli.get(i))).getWins()));
                double wins = (double)Main.teams.get(Main.getTeamIndex(dupli.get(i))).getWins();
                double losses = (double)Main.teams.get(Main.getTeamIndex(dupli.get(i))).getLosses();
                double ratio = wins/losses;
                teamRatio.add(String.valueOf(ratio));
            }

            //If the user wants to use the win/loss ratio, copy all the values into the value ArrayList
            else if (type == 3) {
                value.add((double)Main.teams.get(Main.getTeamIndex(dupli.get(i))).getWins()/(double)Main.teams.get(Main.getTeamIndex(dupli.get(i))).getLosses());
                teamWins.add(String.valueOf(Main.teams.get(Main.getTeamIndex(dupli.get(i))).getWins()));
                teamLoss.add(String.valueOf(Main.teams.get(Main.getTeamIndex(dupli.get(i))).getLosses()));
            }
        }

        //going through the value arrayList to sort the arrayList value and dupli
        for (int j = 0; j < value.size(); j++) {

            //set the current value as the max value
            double max = value.get(j);

            //set the current index as the index where max was found
            int maxIndex = j;

            //get the team name from duplicate array using the index and setting that as max
            String max1 = dupli.get(j);
            String max2 = "";
            String max3 = "";
            String max4 = "";

            //getting the information from arrayLists, following the steps of the values being sorted
            if (teamWins.size() > 0 ) {
                max2 = teamWins.get(j);
            }
            if (teamLoss.size() > 0 ) {
                max3 = teamLoss.get(j);
            }
            if (teamRatio.size() > 0 ) {
                max4 = teamRatio.get(j);
            }

            //Nested for loop to go through all the elements to the right of elements we have already encountered
            for (int k = j + 1; k < value.size(); k++) {

                //If the current value is more than max value
                if (value.get(k) > max) {

                    //change the max and maxIndex variables to current location
                    max = value.get(k);
                    maxIndex = k;

                    max1 = dupli.get(k);
                    if (teamWins.size() > 0 ) {
                        max2 = teamWins.get(k);
                    }
                    if (teamLoss.size() > 0 ) {
                        max3 = teamLoss.get(k);
                    }
                    if (teamRatio.size() > 0 ) {
                        max4 = teamRatio.get(k);
                    }
                }
            }

            //After the nested for loop is over, we get our max value in the ArrayList
            //Using that we can swap the values of the max value to the index at the first for loop
            double temp = value.get(j);
            value.set(j, max);
            value.set(maxIndex, temp);

            //Copy the actions of the value ArrayList to the dupli team Names ArrayList
            //to keep consistency between values and teams
            String temp1 = dupli.get(j);
            dupli.set(j, max1);
            dupli.set(maxIndex, temp1);

            if (teamWins.size() > 0 ) {
                String temp2 = teamWins.get(j);
                teamWins.set(j, max2);
                teamWins.set(maxIndex, temp2);
            }
            if (teamLoss.size() > 0 ) {
                String temp3 = teamLoss.get(j);
                teamLoss.set(j, max3);
                teamLoss.set(maxIndex, temp3);
            }
            if (teamRatio.size() > 0 ) {
                String temp4 = teamRatio.get(j);
                teamRatio.set(j, max4);
                teamRatio.set(maxIndex, temp4);
            }
        }

        ArrayList<String> valueDup = new ArrayList<String>();

        ArrayList<String> print = new ArrayList<String>();
        //If the user chose to use win/loss ratio, set the value of the dupli list using the team name and the ratio in decimals
        if (type == 3) {

            for (int i = 0; i < value.size(); i++) {
                valueDup.add(String.valueOf(value.get(i)));
            }

            //Print the heading for printing the information
            print.add("Name" + Main.printGap(teams, -1, "Name")
                    + "Wins" + Main.printGap(teamWins, -1, "Wins")
                    + "Losses" + Main.printGap(teamLoss, -1, "Losses")
                    + "W/L Ratio" + Main.printGap(valueDup, -1, "W/L Ratio"));

            //adds all the information that needs to be printed in the dupli arrayList
            for (int l = 0; l < dupli2.size(); l++) {
                dupli2.set(l, dupli.get(l) + Main.printGap(dupli, l, "Name")
                        + teamWins.get(l) + Main.printGap(teamWins, l, "Wins")
                        + teamLoss.get(l) + Main.printGap(teamLoss, l, "Losses")
                        + value.get(l) + Main.printGap(valueDup, l, "W/L Ratio"));
            }
        }

        //If the user chose to use
        else if (type == 2){

            for (int i = 0; i < value.size(); i++) {
                valueDup.add(String.valueOf(value.get(i).intValue()));
            }

            //Print the heading for printing the information
            print.add("Name" + Main.printGap(teams, -1, "Name")
                    + "Wins" + Main.printGap(teamWins, -1, "Wins")
                    + "Losses" + Main.printGap(valueDup, -1, "Losses")
                    + "W/L Ratio" + Main.printGap(valueDup, -1, "W/L Ratio"));

            //adds all the information that needs to be printed in the dupli arrayList
            for (int l = 0; l < dupli2.size(); l++) {
                dupli2.set(l, dupli.get(l) + Main.printGap(dupli, l, "Name")
                        + teamWins.get(l) + Main.printGap(teamWins, l, "Wins")
                        + value.get(l).intValue() + Main.printGap(valueDup, l, "Losses")
                        + teamRatio.get(l) + Main.printGap(teamRatio, l, "W/L Ratio"));
            }
        }

        else if (type == 1) {

            for (int i = 0; i < value.size(); i++) {
                valueDup.add(String.valueOf(value.get(i).intValue()));
            }

            //Print the heading for printing the information
            print.add("Name" + Main.printGap(teams, -1, "Name")
                    + "Wins" + Main.printGap(valueDup, -1, "Wins")
                    + "Losses" + Main.printGap(teamLoss, -1, "Losses")
                    + "W/L Ratio" + Main.printGap(valueDup, -1, "W/L Ratio"));

            //adds all the information that needs to be printed in the dupli arrayList
            for (int l = 0; l < dupli2.size(); l++) {
                dupli2.set(l, dupli.get(l) + Main.printGap(dupli, l, "Name")
                        + value.get(l).intValue() + Main.printGap(valueDup, l, "Wins")
                        + teamLoss.get(l) + Main.printGap(teamLoss, l, "Losses")
                        + teamRatio.get(l) + Main.printGap(teamRatio, l, "W/L Ratio"));
            }
        }

        for (int i = 0; i < dupli2.size(); i++) {
            print.add(dupli2.get(i));
        }

        return print;
    }

    /**
     * This method sorts the given player ArrayList using a specific statistic chosen by the user
     *
     * @param teams is the ArrayList that holds the team names, and needs to be sorted
     * @param players is the ArrayList that helps in sorting with a specific statistic
     * @param playerInfo is the ArrayList that holds all the other statistics to sort the ArrayList teams
     * @param type is an integer that is used to recognize what kind of statistic user wants to use
     * @return returns the sorted ArrayList
     */
    private static ArrayList<String> sortPlayers(ArrayList<String> players, int type) {

        //dupli ArrayList is the duplicate of the original players ArrayList, this is so we don't change the actual ArrayList
        ArrayList<String> dupli = new ArrayList<String>(players);

        //value ArrayList to hold the number values of the statistics
        ArrayList<Double> value = new ArrayList<Double>();

        ArrayList<String> playerTeam = new ArrayList<String>();
        ArrayList<String> playerName = new ArrayList<String>();
        ArrayList<String> playerAge = new ArrayList<String>();
        ArrayList<String> playerRank = new ArrayList<String>();
        ArrayList<String> playerRatio = new ArrayList<String>();
        ArrayList<String> playerAgent = new ArrayList<String>();
        ArrayList<String> playerHeadShot = new ArrayList<String>();

        for (int i = 0; i < dupli.size(); i++) {

            //Gets the statistics of all players and adds them to their specified arrayLists
            playerTeam.add(Main.players.get(Main.getPlayerIndex(dupli.get(i))).getTeam().getName());
            playerName.add(Main.players.get(Main.getPlayerIndex(dupli.get(i))).getRealName());
            playerAge.add(String.valueOf(Main.players.get(Main.getPlayerIndex(dupli.get(i))).getAge()));
            playerAgent.add(Main.players.get(Main.getPlayerIndex(dupli.get(i))).getSkills("Agent"));
        }

        //go through the duplicate teams ArrayList
        for (int i = 0; i < dupli.size(); i++) {

            int index = Main.getPlayerIndex(dupli.get(i));
            //If the user wants to use rank, copy all ranks into the rankValue ArrayList
            if (type == 1) {
                //get the rank value
                String rank = Main.players.get(index).getSkills("Rank");

                //go through all the ranks
                for (int k = 0; k < Main.ranks.length; k++) {
                    //if the rank matches to what the player rank is
                    if (Main.ranks[k].equals(rank)) {

                        //add the index to the value arrayList
                        value.add((double)k);
                        break;
                    }
                    else if (rank.equalsIgnoreCase("N/A")) {
                        value.add((double)-1);
                        break;
                    }
                }

                playerRatio.add(Main.players.get(i).getSkills("Ratio"));
                playerHeadShot.add(Main.players.get(i).getSkills("HeadShot"));
            }
            //If the user wants to use head shot %, copy all the values into the value ArrayList
            else if (type == 2) {
                value.add(Double.parseDouble(Main.players.get(index).getSkills("HeadShot")));
                playerRank.add(Main.players.get(i).getSkills("Rank"));
                playerRatio.add(Main.players.get(i).getSkills("Ratio"));
            }

            //If the user wants to use the win/loss ratio, copy all the values into the value ArrayList
            else if (type == 3) {
                value.add(Double.parseDouble(Main.players.get(index).getSkills("Ratio")));
                playerRank.add(Main.players.get(i).getSkills("Rank"));
                playerHeadShot.add(Main.players.get(i).getSkills("HeadShot"));
            }
        }

        //going through the value arrayList to sort the arrayList value and dupli
        for (int j = 0; j < value.size(); j++) {

            //set the current value as the max value
            double max = value.get(j);

            //set the current index as the index where max was found
            int maxIndex = j;

            //get the team name from duplicate array using the index and setting that as max
            String max1 = dupli.get(j);
            String max2 = playerTeam.get(j);
            String max3 = playerName.get(j);
            String max4 = playerAge.get(j);
            String max5 = playerAgent.get(j);
            String max6 = "";
            String max7 = "";
            String max8 = "";
            if (playerRank.size() > 0) {
                max6 = playerRank.get(j);
            }
            if (playerHeadShot.size() > 0) {
                max7 = playerHeadShot.get(j);
            }
            if (playerRatio.size() > 0) {
                max8 = playerRatio.get(j);
            }
            //Nested for loop to go through all the elements to the right of elements we have already encountered
            for (int k = j + 1; k < value.size(); k++) {

                //If the current value is more than max value
                if (value.get(k) > max) {

                    //change the max and maxIndex variables to current location
                    max = value.get(k);
                    maxIndex = k;

                    max1 = dupli.get(k);
                    max2 = playerTeam.get(k);
                    max3 = playerName.get(k);
                    max4 = playerAge.get(k);
                    max5 = playerAgent.get(k);
                    if (playerRank.size() > 0) {
                        max6 = playerRank.get(k);
                    }
                    if (playerHeadShot.size() > 0) {
                        max7 = playerHeadShot.get(k);
                    }
                    if (playerRatio.size() > 0) {
                        max8 = playerRatio.get(k);
                    }
                }
            }

            //After the nested for loop is over, we get our max value in the ArrayList
            //Using that we can swap the values of the max value to the index at the first for loop
            double temp = value.get(j);
            value.set(j, max);
            value.set(maxIndex, temp);

            //Copy the actions of the value ArrayList to the dupli team Names ArrayList
            //to keep consistency between values and teams
            String temp1 = dupli.get(j);
            dupli.set(j, max1);
            dupli.set(maxIndex, temp1);

            String temp2 = playerTeam.get(j);
            playerTeam.set(j,max2);
            playerTeam.set(maxIndex, temp2);

            String temp3 = playerName.get(j);
            playerName.set(j,max3);
            playerName.set(maxIndex, temp3);

            String temp4 = playerAge.get(j);
            playerAge.set(j,max4);
            playerAge.set(maxIndex, temp4);

            String temp5 = playerAgent.get(j);
            playerAgent.set(j,max5);
            playerAgent.set(maxIndex, temp5);

            if (playerRank.size() > 0) {
                String temp6 = playerRank.get(j);
                playerRank.set(j,max6);
                playerRank.set(maxIndex, temp6);
            }

            if (playerHeadShot.size() > 0) {
                String temp7 = playerHeadShot.get(j);
                playerHeadShot.set(j,max7);
                playerHeadShot.set(maxIndex, temp7);
            }

            if (playerRatio.size() > 0) {
                String temp8 = playerRatio.get(j);
                playerRatio.set(j,max8);
                playerRatio.set(maxIndex, temp8);
            }
        }



        //new arrayList to add the sorted information into
        ArrayList<String> sorted = new ArrayList<String>();

        ArrayList<String> valueDup = new ArrayList<String>();


        if (type == 1) {

            //go through all of the value arrayList
            for (int i = 0; i < value.size(); i++) {
                for (int k = 0; k < Main.ranks.length; k++) {
                    //if the rank matches to what the player rank is
                    if (value.get(i) == k) {

                        //add the rank String in the value arrayList
                        valueDup.add(Main.ranks[k]);
                        break;
                    }

                    //If the value is -1, then adding the string N/A as the rank hasn't been added for that player
                    else if (value.get(i) == -1) {
                        valueDup.add("N/A");
                        break;
                    }
                }
            }

            //Print out the Title
            sorted.add("Username" + Main.printGap(dupli, -1, "Username")
                    + "Team" + Main.printGap(playerTeam, -1, "Team")
                    + "Real Name" + Main.printGap(playerName, -1, "Real Name")
                    + "Age" + Main.printGap(playerAge, -1, "Age")
                    + "Rank" + Main.printGap(valueDup, -1, "Rank")
                    + "W/L Ratio" + Main.printGap(playerRatio, -1, "W/L Ratio")
                    + "Most Used Agent" + Main.printGap(playerAgent, -1, "Most Used Agent")
                    + "Headshot %" + Main.printGap(playerHeadShot, -1, "Headshot %"));

            //Add info to the sorted arrayList
            for (int l = 0; l < dupli.size(); l++) {
                sorted.add(dupli.get(l) + Main.printGap(dupli, l, "Username")
                        + playerTeam.get(l) + Main.printGap(playerTeam, l, "Team")
                        + playerName.get(l) + Main.printGap(playerName, l, "Real Name")
                        + playerAge.get(l) + Main.printGap(playerAge, l, "Age")
                        + valueDup.get(l) + Main.printGap(valueDup, l, "Rank")
                        + playerRatio.get(l) + Main.printGap(playerRatio, l, "W/L Ratio")
                        + playerAgent.get(l) + Main.printGap(playerAgent, l, "Most Used Agent")
                        + playerHeadShot.get(l) + Main.printGap(playerHeadShot, l, "Headshot %"));
            }
        }
        else if (type == 2) {

            for (int i = 0; i < value.size(); i++) {
                valueDup.add(String.valueOf(value.get(i)));
            }

            //print the title
            sorted.add("Username" + Main.printGap(dupli, -1, "Username")
                    + "Team" + Main.printGap(playerTeam, -1, "Team")
                    + "Real Name" + Main.printGap(playerName, -1, "Real Name")
                    + "Age" + Main.printGap(playerAge, -1, "Age")
                    + "Rank" + Main.printGap(playerRank, -1, "Rank")
                    + "W/L Ratio" + Main.printGap(playerRatio, -1, "W/L Ratio")
                    + "Most Used Agent" + Main.printGap(playerAgent, -1, "Most Used Agent")
                    + "Headshot %" + Main.printGap(valueDup, -1, "Headshot %"));

            //Add info to the sorted arrayList
            for (int l = 0; l < dupli.size(); l++) {
                sorted.add(dupli.get(l) + Main.printGap(dupli, l, "Username")
                        + playerTeam.get(l) + Main.printGap(playerTeam, l, "Team")
                        + playerName.get(l) + Main.printGap(playerName, l, "Real Name")
                        + playerAge.get(l) + Main.printGap(playerAge, l, "Age")
                        + playerRank.get(l) + Main.printGap(playerRank, l, "Rank")
                        + playerRatio.get(l) + Main.printGap(playerRatio, l, "W/L Ratio")
                        + playerAgent.get(l) + Main.printGap(playerAgent, l, "Most Used Agent")
                        + valueDup.get(l) + Main.printGap(valueDup, l, "Headshot %"));
            }
        }
        else if (type == 3){

            for (int i = 0; i < value.size(); i++) {
                valueDup.add(String.valueOf(value.get(i)));
            }

            //print the title
            sorted.add("Username" + Main.printGap(dupli, -1, "Username")
                    + "Team" + Main.printGap(playerTeam, -1, "Team")
                    + "Real Name" + Main.printGap(playerName, -1, "Real Name")
                    + "Age" + Main.printGap(playerAge, -1, "Age")
                    + "Rank" + Main.printGap(playerRank, -1, "Rank")
                    + "W/L Ratio" + Main.printGap(valueDup, -1, "W/L Ratio")
                    + "Most Used Agent" + Main.printGap(playerAgent, -1, "Most Used Agent")
                    + "Headshot %" + Main.printGap(playerHeadShot, -1, "Headshot %"));

            //Add info to the sorted arrayList
            for (int l = 0; l < dupli.size(); l++) {
                sorted.add(dupli.get(l) + Main.printGap(dupli, l, "Username")
                        + playerTeam.get(l) + Main.printGap(playerTeam, l, "Team")
                        + playerName.get(l) + Main.printGap(playerName, l, "Real Name")
                        + playerAge.get(l) + Main.printGap(playerAge, l, "Age")
                        + playerRank.get(l) + Main.printGap(playerRank, l, "Rank")
                        + valueDup.get(l) + Main.printGap(valueDup, l, "W/L Ratio")
                        + playerAgent.get(l) + Main.printGap(playerAgent, l, "Most Used Agent")
                        + playerHeadShot.get(l) + Main.printGap(playerHeadShot, l, "Headshot %"));
            }
        }

        //return the sorted ArrayList
        return sorted;
    }
}