# CPSC 233 Valorant Tracker Part 3/3

### This project is the final project of the CPSC 233 course. This project is built by Saksham P and Aishan I
##### saksham.puri@ucalgary.ca 
##### aishanirfan.na@ucalgary.ca
##### Tutorial: T05
##### Data Started Development of Part 3: April 2nd 2022
This Software is a tracking program that tracks teams and players of a game called Valorant in a GUI Application. This Application is built with JavaFX and lets the user load and save the Data from a csv file and view it in a much more comfortable manner!

## How to Run Jar File:
    1. Open CMD at the location of the jar File
    2. Run the Jar file using (java --module-path "location of the JavaFX library" --add-modules javafx.controls,javafx.fxml -jar Name.jar)
    3. To pass arguments using command Line add your arguments at the end after typing in the jar file
    4. This will run the Jar File

## Navigating the Title Screen

    File Menu:
    1. "New" button opens a new screen with empty data where a user can use the GUI to add and edit data
    2. "Load" buton prompts the user to pick a .csv file that contains a read-able data set. Then the program loads in the data
    3. "Exit" button asks the user if they want to exit to make sure, and then quits the program.
    - Menu Options not in Title Screen
    4. "Save" button saves the current data to a recently saved or loaded file, if none exist then user selects a location to save the file
    5. "Save As" button prompts the user to choose a location to save their file

    Edit Menu:
    1. "Auto Save" button toggle the Auto Save feature ON and OFF. If ON, then the program saves data to a recent file every edit
    2. "Load Example Data" button loads default example data to the currently open file. It will erase all previous data.

    Help Menu:
    1. "About" button opens a new small window which gives info about the program and the developers

    Loading File from Command Line Arguments:
    1. If the user has entered an argument when running the file
    2. Then a button will be visible in the Title Screen that on click will load in the File if possible. 

## Navigating The Main Screen

#### Master Window:
    1. "Add a Team" button shows the "Team Name", "Team Wins", and "Team Losses" Text Field on the screen
        - "Team Name" Text Field requires user to input the Team Name they wish to add
        - "Team Wins" Text Field is an optional text field that a user can input the wins of the team they like to add
        - "Team Losses" Text Field is an optional text field that a user can input the losses of the team they like to add

    2. "Add a Player" button shows the "Player Username", "Player Real Name", and "Player Age" text fields, and "Player Team" option field
        - "Player Username" Text Field requires user to input the username of the player they wish to add
        - "Player Real Name" Text Field requires user to input the real name of the player they wish to add
        - "Player Age" Text Field requires user to input the age of the player they wish to add
        - "Player Team" ComboBox is an optional choice that a user can choose which team to add the player to
        
    3. "Update Team Data" button shows the "Teams" choice, and "New Team Name", "Update Team Wins", and "Update Team Losses" text fields
        - "Teams" ComboBox requires the user to select a Team which they would like to update the info of
        - "New Team Name" Text Field is an optional text field that a user can input the new name of the team in
        - "Update Team Wins" Text Field is an optional text field that a user can input the new wins of the team in
        - "Update Team Losses" Text Field is an optional text field that a user can input the new losses of the team in

    4. "Update Player Data" button shows the "New Player Username", "New Player Real Name", "New Player Age", "HS %", and "W/L Ratio" text fields
        It also shows the "Player", "Player Team", "Player Rank", and "Player Agent" choices to the user
        - "Players" ComboBox requires the user to select a Player which they would like to update the info of
        - "New Player Username" Text Field is an optional text field that a user can input the new player username in
        - "New Player Real Name" Text Field is an optional text field that a user can input the new player real name in
        - "New Player Age" Text Field is an optional text field that a user can input the new player age in
        - "Player Team" ComboBox is an optional choice that a user can make to place the player in a newer team
        - "Player Rank" ComboBox is an optional chocie that a user can choose from to update the player Rank
        - "Player Agent" ComboBox is an optional choice that a user can select from to update the players most used Agent
        - "HS %" Text Field is an optional text field that a user can input the new player's headshot percentage in
        - "W/L Ratio" Text Field is an optional text field that a user can input the new player's Win Loss Ration in

    5. "Add!" / "Update!" buttons
        - "Add!" button will add the Team or Player to the System with the given information
        - "Update!" button will update the Team or Player information in the System with the given information

#### View Window:
    1. "View Data" ComboBox gives user the choices "All Data", "All Players", and "All Teams"
        - "All Data" button displays all the data in the system about Teams and Players in the Text Area in the View Window
        - "All Players" button displays all the player data in the system in the Text Area
        - "All Teams" button displays all the team data in the system in the Text Area
    
    2. "All Players" button enables the "Options" and "Sort" ComboBoxes, and the "Top #" Text Field
        - "Options" ComboBox gives user the option to select "All Teams" which displays all players across all teams, or to display players from a specific team 
        - "Sort" ComboBox gives user the option to sort the data using Player's Rank, Headshot %, or their Win/Loss Ratio
        - "Top #" Text Field lets user type in the top # of players they would like to see in the list of all the players using a sort method

    3. "All Teams" button enables the "Sort" ComboBox and the "Top #" Text Field
        - "Sort" ComboBox gives user the option to sort the data using Team's Wins or Losses
        - "Top #" Text Field allows the user to type in a number, that controls the number of top teams that will be shown in the display

#### Details Window:
    1. "Teams/Players" ComboBox shows the list of teams/players there are currently stored in the System
        - "Selecting one of the Teams or Players shows the details of that team/player in the Text Area below

    2. "Teams/Players" button controls which list is currently being shown, the option changes every time the button is clicked
        - If the button currently has the name "Players" then the players list is currently being shown, same with Teams
        - To switch to Teams from Players / Players to Teams, click the button once to switch

