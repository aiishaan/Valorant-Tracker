package Main;

import java.io.File;
import java.util.ArrayList;
import java.util.Optional;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import util.Reader;
import util.Writer;

public class MainController {
	// setting up booleans and null file that will be used later.
	private boolean initialized = false;
	private File recentFile = null;
	private boolean teamEnabled = true;
	private boolean playerEnabled = false;

	// Creating an ArrayLists that will hold player and team names.
	private ArrayList<String> playerNames = new ArrayList<String>();
	private ArrayList<String> teamNames = new ArrayList<String>();
	private ArrayList<String> playerTeamNames = new ArrayList<String>();
	
	//variables that are used later for teams and players
	private String currentTeam = "";
	private String oldPlayer = null;
	private String oldTeam = null;
	
	//variable to store the passed file argument
	public static String arg = null;


	// declaring a Ranks array that holds all the Ranks of a player.
	private String[] ranks = {
			"Iron 1", "Iron 2", "Iron 3",
			"Bronze 1", "Bronze 2", "Bronze 3",
			"Silver 1", "Silver 2", "Silver 3",
			"Gold 1", "Gold 2", "Gold 3",
			"Platinum 1", "Platinum 2", "Platinum 3",
			"Diamond 1", "Diamond 2", "Diamond 3",
			"Immortal 1", "Immortal 2", "Immortal 3",
			"Radiant"
	};

	// declaring an Agents Array that holds all the different types of the agents.
	private String[] agents = {
			"Brimstone", "Phoenix", "Sage", "Sova", "Viper", "Cypher",
			"Reyna", "Killjoy", "Breach", "Omen", "Jett", "Raze",
			"Sky", "Yoru", "Astra", "KAY/O", "Chamber", "Neon"
	};
	
	//All FXML object variables used by Main.fxml file
	@FXML
	private ImageView titleImage;
	@FXML
	private AnchorPane titlePane;
	@FXML
	private AnchorPane leftPane;
	@FXML
	private AnchorPane rightPane;
	@FXML
	private Label leftStatus;
	@FXML
	private Label rightStatus;
	@FXML
	private Label detailsLabel;
	@FXML
	private Label viewLabel;
	@FXML
	private Text titleText;
	@FXML
	private TextField firstField;
	@FXML
	private TextField secondField;
	@FXML
	private TextField thirdField;
	@FXML
	private TextField playerHS;
	@FXML
	private TextField playerRatio;
	@FXML
	private TextField topField;
	@FXML
	private TextArea details;
	@FXML
	private TextArea mainView;
	@FXML
	private Menu editMenu;
	@FXML
	private MenuButton viewOpt;
	@FXML
	private MenuButton viewSort;
	@FXML
	private MenuItem save;
	@FXML
	private MenuItem saveAs;
	@FXML
	private MenuItem autoSaveOption;
	@FXML
	private MenuItem allTeams;
	@FXML
	private MenuItem wins;
	@FXML
	private MenuItem losses;
	@FXML
	private MenuItem rank;
	@FXML
	private MenuItem headshot;
	@FXML
	private MenuItem pRatio;
	@FXML
	private MenuItem tRatio;
	@FXML
	private SplitPane splitPane;
	@FXML
	private VBox vBox;
	@FXML
	private HBox hBox;
	@FXML
	private Button teamButton;
	@FXML
	private Button playerButton;
	@FXML
	private Button argumentFile;
	@FXML
	private Button updateTeam;
	@FXML
	private Button updatePlayer;
	@FXML
	private Button addButton;
	@FXML
	private Button toggleView;
	@FXML
	private ComboBox<String> playerPicker;
	@FXML
	private ComboBox<String> teamPicker;
	@FXML
	private ComboBox<String> playerPicker1;
	@FXML
	private ComboBox<String> teamPicker1;
	@FXML
	private ComboBox<String> playerTeam;
	@FXML
	private ComboBox<String> playerRank;
	@FXML
	private ComboBox<String> playerAgent;
	@FXML
	private Group playerUpdate;
	@FXML
	private Group viewSelector;
	@FXML
	private Group viewOptions;
	@FXML
	private ScrollPane scrollPane;
	
	//method that runs first when a FXML File is being loaded
	@FXML
	public void initialize() {
		
		//sets the autosave text to its current state
		autoSaveOption.setText("Auto Save: " + util.Menu.autoSave);
		
		//sets other variables to their default state
		leftStatus.setText("");
		rightStatus.setText("");
		
		//makes the splitPane invisible as we are currently in Title Screen
		splitPane.setVisible(false);
		splitPane.setPrefHeight(0);
		playerTeam.setVisible(false);

		//If passed argument is null, don't show the button to load argument file
		if (arg != null) {
			argumentFile.setVisible(true);
		}
		
		//sets up a listener to listen for the Master view width to change
		leftPane.widthProperty().addListener((obs, oldVal, newVal)-> {
			
			//If it changes, re-position the contents of the left pane
			teamButton.setLayoutX((leftPane.getWidth()/2) - (teamButton.getWidth()/2));
			playerButton.setLayoutX((leftPane.getWidth()/2) - (playerButton.getWidth()/2));
			updateTeam.setLayoutX((leftPane.getWidth()/2) - (updateTeam.getWidth()/2));
			updatePlayer.setLayoutX((leftPane.getWidth()/2) - (updatePlayer.getWidth()/2));
			addButton.setLayoutX((leftPane.getWidth()/2) - (addButton.getWidth()/2));
			playerPicker1.setLayoutX((leftPane.getWidth()/2) - (playerPicker1.getWidth()/2));
			teamPicker1.setLayoutX((leftPane.getWidth()/2) - (teamPicker1.getWidth()/2));
			firstField.setLayoutX((leftPane.getWidth()/2) - (firstField.getWidth()/2));
			secondField.setLayoutX((leftPane.getWidth()/2) - (secondField.getWidth()/2));
			thirdField.setLayoutX((leftPane.getWidth()/2) - (thirdField.getWidth()/2));
			playerTeam.setLayoutX((leftPane.getWidth()/2) - (playerTeam.getWidth()/2));
			playerUpdate.setLayoutX((leftPane.getWidth()/2) - (playerUpdate.getBoundsInLocal().getWidth()/2));
			
			//If the left pane gets shorter than its content, set its minimum width
			if (leftPane.getWidth() <= playerUpdate.getBoundsInLocal().getWidth() + 10) {
				leftPane.setMinWidth(leftPane.getWidth() + 10);
			}
		});
		
		//Do the same with right Pane
		rightPane.widthProperty().addListener((obs, oldVal, newVal) -> {

			details.setLayoutX((rightPane.getWidth()/2) - (details.getWidth()/2));
			viewSelector.setLayoutX((rightPane.getWidth()/2) - (viewSelector.getBoundsInLocal().getWidth()/2));
			detailsLabel.setLayoutX(details.getLayoutX());

			if (rightPane.getWidth() <= details.getWidth() + 10) {
				rightPane.setMinWidth(rightPane.getWidth() + 10);
			}
		});
		
		//as well as with the middle Pane
		scrollPane.widthProperty().addListener((obs, oldVal, newVal) -> {
			mainView.setPrefWidth(((double)scrollPane.getWidth()) - (mainView.getLayoutX() * 2));
			viewOptions.setLayoutX(((double)scrollPane.getWidth()/2) - (viewOptions.getBoundsInLocal().getWidth()/2));

			if (scrollPane.getWidth() <= viewOptions.getBoundsInLocal().getWidth() + 10) {
				scrollPane.setMinWidth(scrollPane.getWidth() + 10);
			}
		});
		
		//set something invisible in Title Screen
		playerPicker.setVisible(false);
		playerTeamNames.add("Player Team (Optional)");
		playerPicker1.setVisible(false);
		teamPicker1.setVisible(false);
		
		//add a listener to comboBox value
		playerPicker1.valueProperty().addListener((obs, oldVal, newVal) -> {
			
			//If the value changes, set the details portion to show the current value
			toggleView.setText("Players");
			playerPicker.setVisible(true);
			teamPicker.setVisible(false);
			playerPicker.setValue(playerPicker1.getValue());
			
			//save the current value
			oldPlayer = newVal;

			toggleView.setDisable(true);
			playerPicker.setDisable(true);
			playerPicker1.setDisable(true);
		});
		
		//do the same as done to playerPicker1
		teamPicker1.valueProperty().addListener((obs, oldVal, newVal) -> {
			toggleView.setText("Teams");
			playerPicker.setVisible(false);
			teamPicker.setVisible(true);
			teamPicker.setValue(teamPicker1.getValue());

			oldTeam = newVal;

			toggleView.setDisable(true);
			teamPicker.setDisable(true);
			teamPicker1.setDisable(true);
		});
		
		playerUpdate.setVisible(false);

		//add values to the rank comboBox and agent comboBox
		playerRank.getItems().addAll(ranks);
		playerAgent.getItems().addAll(agents);
		
		//Set a method for the items of sort comboBox
		for (int i = 0; i < viewSort.getItems().size(); i++) {
			viewSort.getItems().get(i).setOnAction(sortChosen);
		}
	}

	/**
	 * This method loads a file from the command line by using the reader class.
	 */
	@FXML
	public void loadArgs() {
		// Create a new file from the command line argument input.
		File file = new File(arg);
		// using the reader to load that file in the program.
		argsFile(Reader.loadInfo(file));
	}

	/**
	 * This method checks if the file was loaded properly or not and sets the status of the program
	 *  to let the user know.
	 *  This method then sets the file up and loads the info from the file to launch the program.
	 * @param success is a boolean that is set to be true when the file is loaded properly.
	 */
	private void argsFile(boolean success) {
		if (success) {
			newFile();
			loadInfo();
			// setting the status to let the user know that the file has been loaded properly.
			rightStatus.setText("File Loaded!");
		}
		// setting the status to let the user know that there was an error.
		else {
			newFile();
			leftStatus.setText("Error loading File from Arguments!");
		}
		
	}
	
	/**
	 * This method sets the scene as if it was a newly opened window with no previous data
	 * If the program is going from the Title screen to the actual scene, certain more things are done
	 */
	@FXML
	public void newFile(){
		
		//If clicked new button from title screen
		if (!initialized) {
			
			//close the stage with title screen
			Main.stage.close();
			
			//Hide and show different objects to have a new looking scene
			titleImage.setVisible(false);
			titleText.setVisible(false);

			editMenu.setVisible(true);

			save.setVisible(true);
			saveAs.setVisible(true);

			vBox.getChildren().remove(titlePane);

			splitPane.setPrefHeight(800);
			splitPane.setVisible(true);

			hBox.setPrefHeight(100);
			hBox.setVisible(true);
			
			//setup a new stage and show it using the same scene
			Stage stage = new Stage();
			stage.setTitle("Valorant Tracker 3.0");
			stage.getIcons().add(new Image("Main/Val-Icon3.png"));
			stage.setScene(Main.scene);
			stage.setHeight(600);
			stage.setWidth(800);
			stage.show();
		}
		
		
		//If auto save is on, then save the file before resetting stuff
		if (Main.autoSaveOn) {
			save();
		}
		
		//reset the positions of the content in the scene
		teamButton.setLayoutX((leftPane.getWidth()/2) - (teamButton.getWidth()/2));
		playerButton.setLayoutX((leftPane.getWidth()/2) - (playerButton.getWidth()/2));
		updateTeam.setLayoutX((leftPane.getWidth()/2) - (updateTeam.getWidth()/2));
		updatePlayer.setLayoutX((leftPane.getWidth()/2) - (updatePlayer.getWidth()/2));
		addButton.setLayoutX((leftPane.getWidth()/2) - (addButton.getWidth()/2));
		playerPicker1.setLayoutX((leftPane.getWidth()/2) - (playerPicker1.getWidth()/2));
		teamPicker1.setLayoutX((leftPane.getWidth()/2) - (teamPicker1.getWidth()/2));
		firstField.setLayoutX((leftPane.getWidth()/2) - (firstField.getWidth()/2));
		secondField.setLayoutX((leftPane.getWidth()/2) - (secondField.getWidth()/2));
		thirdField.setLayoutX((leftPane.getWidth()/2) - (thirdField.getWidth()/2));
		playerTeam.setLayoutX((leftPane.getWidth()/2) - (playerTeam.getWidth()/2));
		playerUpdate.setLayoutX((leftPane.getWidth()/2) - (playerUpdate.getBoundsInLocal().getWidth()/2));

		details.setLayoutX((rightPane.getWidth()/2) - (details.getWidth()/2));
		viewSelector.setLayoutX((rightPane.getWidth()/2) - (viewSelector.getBoundsInLocal().getWidth()/2));

		mainView.setPrefWidth(((double) scrollPane.getWidth()) - (mainView.getLayoutX() * 2));
		viewOptions.setLayoutX(((double)scrollPane.getWidth()/2) - (viewOptions.getBoundsInLocal().getWidth()/2));
		
		//show all the current data that is stored
		viewAll();
		
		if (initialized) {
			//clear all the players and teams
			Main.players.clear();
			Main.teams.clear();
		}
		
		
		//update the lists
		updateLists();
		
		//set initialized to true as title screen won't be shown again until restarted
		initialized = true;
	}

	/**
	 * This method will run when the load File button is clicked in the menu bar options
	 * This method loads the file of user's choice.
	 */
	@FXML
	public void loadFile() {

		//create a new stage for the file choosing window
		Stage stage = new Stage();

		//create a FileChooser object
		FileChooser fileChooser = new FileChooser();

		//set the Title, initial directory, and the type of files that can be opened from the file chooser
		fileChooser.setTitle("Open Data File");
		fileChooser.setInitialDirectory(new File("."));
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Comma Separate Value", "*.csv"));

		//show the file chooser window, and get the file from it
		File file = fileChooser.showOpenDialog(stage);

		if (file != null) {

			//If the file isn't null (the user chose a file), then load the file using reader
			if (util.Reader.loadInfo(file)) {
				//set the right status to Loaded New File
				rightStatus.setText("Loaded New File");
				recentFile = file;
				loadInfo();
				newFile();
			}
			else {

				//Let the user know if unable to load file
				leftStatus.setText("Unable to Load file, please make sure you have selected the correct File");
			}
		}
		else {

			//If no file is chosen, then let the user know that no file is chosen
			leftStatus.setText("No File Chosen!");
		}
	}

	/**
	 * This method calls the updateLists method to load the info from the file.
	 */
	private void loadInfo() {
		//calling the updateLists method.
		updateLists();
		
		viewAll();
	}

	/**
	 * This method clears the list of players and teams and then fills them up with the data from the main class arraylists that is given by the user
	 * These lists are then used in the other parts of the program.
	 */
	private void updateLists() {
		// clearing the lists.
		playerNames.clear();
		teamNames.clear();
		playerTeamNames.clear();
		playerTeamNames.add("Player Team (Optional)");

		playerPicker.getItems().clear();
		teamPicker.getItems().clear();
		playerPicker1.getItems().clear();
		teamPicker1.getItems().clear();
		playerTeam.getItems().clear();

		viewOpt.getItems().clear();
		viewOpt.getItems().add(allTeams);
		// using for loops to add data to the lists.
		for (int i = 0; i < Main.players.size(); i++) {
			playerNames.add(Main.players.get(i).getUserName());
		}
		for (int i = 0; i < Main.teams.size(); i++) {
			teamNames.add(Main.teams.get(i).getName());
			playerTeamNames.add(Main.teams.get(i).getName());

			viewOpt.getItems().add(new MenuItem(Main.teams.get(i).getName()));
			viewOpt.getItems().get(i+1).setOnAction(teamChosen);

		}
		
		//Storing new values into the comboBoxes
		playerPicker.getItems().addAll(playerNames);
		teamPicker.getItems().addAll(teamNames);
		playerPicker1.getItems().addAll(playerNames);
		teamPicker1.getItems().addAll(teamNames);
		playerTeam.getItems().addAll(playerTeamNames);
	}

	/**
	 * This EventHandler gets triggered whenever a menu Item is chosen
	 * specifically the menu items in the Options' menu in the scrollPane
	 */
	EventHandler<ActionEvent> teamChosen = new EventHandler<ActionEvent>() {

		//set up the method for
		public void handle(ActionEvent event) {
			
			//go through all the items in the viewOpt menu
			for (int i = 0; i < viewOpt.getItems().size(); i++) {
				
				//if found the one that clicked was clicked
				if (viewOpt.getItems().get(i).equals(event.getSource())) {
					
					//get the text of the menuItem, and use that as a method to show all players from that team
					viewPlayers(viewOpt.getItems().get(i).getText());
					currentTeam = viewOpt.getItems().get(i).getText();
				}
			}
		}
	};

	/**
	 * This EventHandler gets triggered whenever a menu Item is chosen
	 * specifically the menu items in the sort menu in the scrollPane
	 */
	EventHandler<ActionEvent> sortChosen = new EventHandler<ActionEvent>() {

		//set up the method for
		public void handle(ActionEvent event) {
			
			//go through all the items in the menu
			for (int i = 0; i < viewSort.getItems().size(); i++) {
				
				//depending on which menuItem was clicked, run a sorting method accordingly
				if (event.getSource().equals(wins)) {
					sortTeams("Wins");
				}
				else if (event.getSource().equals(losses)) {
					sortTeams("Losses");
				}
				else if (event.getSource().equals(tRatio)) {
					sortTeams("Ratio");
				}
				else if (event.getSource().equals(rank)) {
					sortPlayers("Rank");
				}
				else if (event.getSource().equals(headshot)) {
					sortPlayers("Headshot");
				}
				else if (event.getSource().equals(pRatio)) {
					sortPlayers("Ratio");
				}
			}
		}
	};


	/**
	 * This method runs when the save file button is clicked in the menu bar File options
	 * This method saves to the file which was loaded
	 */
	@FXML
	public void save() {
		if (recentFile != null) {

			//If the writer is not able to save the world properly, let the user know
			if (!Writer.writeFile(recentFile)) {
				leftStatus.setText("Internal System Error Occurred while Saving File");
			}
			else {
				//if successful let the user know
				rightStatus.setText("File Saved successfully to: " + recentFile.getAbsolutePath());
			}
		}
		else {
			
			//if there is no recent file loaded, make the player save the file in a location of their choice
			saveAs();
		}
	}

	/**
	 * This method is triggered when the user selects the option SaveAs in the File Menu bar
	 * User chooses a location to save the file at
	 */
	@FXML
	public void saveAs() {
		//create a new stage
		Stage stage = new Stage();

		//set up the FileChooser
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Data");
		fileChooser.setInitialDirectory(new File("."));
		fileChooser.setInitialFileName("Save.csv");

		//Show the FileChooser window
		File file = fileChooser.showSaveDialog(stage);

		if (file != null) {
			//If the writer is not able to save the world properly, let the user know
			if (!Writer.writeFile(file)) {
				leftStatus.setText("Internal System Error Occurred while Saving File");
			}
			else {
				//if successful let the user know
				rightStatus.setText("File Saved successfully to: " + file.getAbsolutePath());
				recentFile = file;
			}
		}
		// Display the message of no directory chosen.
		else {
			leftStatus.setText("No Directory Chosen");
		}
	}

	/**
	 * This method is used to turn the Auto save feature on and off in the program.\
	 * The auto save option saves all the changes made by the user without the user manually having to save them
	 * every single time.
	 */
	@FXML
	public void autoSaveToggle() {
		// calling the autoSaveToggle from the main class.
		Main.autoSaveToggle();
		// setting the auto save button to show its current status.
		autoSaveOption.setText("Auto Save: " + util.Menu.autoSave);
		// setting the current status of the autoSaveButton as the program status.
		rightStatus.setText("Auto Save: " + util.Menu.autoSave);
	}

	/**
	 * This method is used to load the default data of the program and set the program status to show that
	 * the default data has been loaded.
	 */
	@FXML
	public void loadDefault() {
		// calling the loadDefaultData method from the main class.
		Main.loadDefaultData();
		// setting the status of their program to show that default data has been loaded.
		rightStatus.setText("Default Data Loaded!");
		
		loadInfo();
	}

	/**
	 * This method is triggered when the user selects About option in Help Menu bar and it displays
	 * all the information about the program as an information alert.
	 */
	@FXML
	public void about() {

		//create a new Alert object of information type
		Alert alert = new Alert(Alert.AlertType.INFORMATION);

		//Setup up the alert with its initial settings
		alert.setTitle("About");
		alert.setHeaderText("Valorant Tracker");

		//Set the content of the Alert
		alert.setContentText("Made By: Saksham Puri & Aishan Irfan\nContact: saksham.puri@ucalgary.ca & aishanirfan.na@ucalgary.ca\nTutorial: T05\nDate Started Development: April 2nd 2022\nVersion: "
				+ Main.version + "\n\nThis Software is a tracking program that tracks teams and players of a game "
				+ "called Valorant in a GUI Application. This Application is built with JavaFX"
				+ "\nUser gets to load and save the Data and view it in a much more comfortable manner!");
		alert.setHeight(400);

		//Show the alert
		alert.show();
	}

	/**
	 * This method triggers when the user wants to quit the program
	 */
	@FXML
	public void exitProgram() {

		//Create a new alert of type confirmation
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

		//Set the title, and the content of the Alert
		alert.setTitle("Quit Program!");
		alert.setHeaderText("Do you want to Exit the Program?");
		alert.setContentText("Make sure to save the program before Exiting!");
		alert.setHeight(400);

		//Set up Optional Object type of ButtonTypes which is returned from showAndWait() function
		Optional<ButtonType> button = alert.showAndWait();

		//if the button OK is pressed, then quit the program, else don't do anything
		if (button.get() == ButtonType.OK) {
			Platform.exit();
		}
	}

	/**
	 * This method is triggered when the user clicks the "add a team" button.
	 * This method sets the text of the text fields with default values.
	 * This method also updates the prompt texts for the various text fields.
	 * This method also updates the values of the boolean variables.
	 */
	@FXML
	public void addTeam() {
		// setting default values to the text fields texts.
		firstField.setText("");
		secondField.setText("");
		thirdField.setText("");
		// updating the prompt texts for these text fields.
		firstField.setPromptText("Team Name");
		secondField.setPromptText("Team Wins (Optional)");
		thirdField.setPromptText("Team Losses (Optional)");
		playerTeam.setVisible(false);
		// updating the boolean variables.
		teamEnabled = true;
		playerEnabled = false;

		toggleView.setDisable(false);
		teamPicker.setDisable(false);
		playerPicker.setDisable(false);
		
		playerPicker1.setVisible(false);
		teamPicker1.setVisible(false);
		
		addButton.setText("Add!");

		playerUpdate.setVisible(false);
	}

	/**
	 * This method is triggered when the user clicks the " add a player" button.
	 * This method sets the text of the text fields with default values.
	 * This method also updates the prompt texts for the various text fields.
	 * This method also updates the values of the boolean variables.
	 */
	@FXML
	public void addPlayer() {
		// setting default values to the text fields.
		playerPicker1.setVisible(false);
		teamPicker1.setVisible(false);

		addButton.setText("Add!");

		firstField.setText("");
		secondField.setText("");
		thirdField.setText("");
		playerTeam.setValue("Player Team (Optional)");

		// updating the prompt texts for these text fields.
		firstField.setPromptText("Player Username");
		secondField.setPromptText("Player Real Name");
		thirdField.setPromptText("Player Age");
		playerTeam.setVisible(true);
		// updating the boolean variables.
		teamEnabled = false;
		playerEnabled = true;

		toggleView.setDisable(false);
		teamPicker.setDisable(false);
		playerPicker.setDisable(false);

		playerUpdate.setVisible(false);
	}

	/**
	 * This method is triggered when the user clicks the " Update team data" button.
	 * This method updates the prompt texts for the various text fields.
	 *  This method also updates the values of the boolean variables.
	 */
	@FXML
	public void updateTeam() {

		playerPicker1.setVisible(false);
		teamPicker1.setVisible(true);

		addButton.setText("Update!");

		// updating the prompt texts for these text fields.
		firstField.setPromptText("New Team Name");
		secondField.setPromptText("Update Team Wins");
		thirdField.setPromptText("Update Team Losses");

		playerTeam.setVisible(false);
		// updating the boolean variables.
		teamEnabled = true;
		playerEnabled = false;

		playerUpdate.setVisible(false);
	}

	/**
	 * This method is triggered when the user clicks the "Update Player data" method.
	 * This method makes the players drop down button visible.
	 * This method changes the text on the add button to "Update".
	 * This method also updates the values of the boolean variables.
	 */
	@FXML
	public void updatePlayer() {
		// making the players drop down button visible.
		playerPicker1.setVisible(true);
		// hiding the teams drop down button.
		teamPicker1.setVisible(false);
		// updating the add button text.
		addButton.setText("Update!");

		// updating the prompt texts for these text fields.
		firstField.setPromptText("New Player Username");
		secondField.setPromptText("New Player Real Name");
		thirdField.setPromptText("New Player Age");

		
		// updating the boolean variables.
		teamEnabled = false;
		playerEnabled = true;

		// setting the player team and player update buttons visible.
		playerTeam.setVisible(true);
		playerUpdate.setVisible(true);
	}

	/**
	 * This method is triggered when the user clicks the "Add!" button.
	 * This method first checks if the user wants to update information or add a player or a team.
	 * This method then adds the team or the player to the program.
	 */
	@FXML
	public void add() {
		// checks if the user wants to update the data.
		if (addButton.getText().equals("Update!")) {
			// updates the data by calling the update method.
			update();
		}
        // if the user wants to add a team or a player
		else {
            // if the user wants to add a team.
			if (teamEnabled) {
				String teamName = null;
				int teamWins = -1;
				int teamLosses = -1;
                // checks if the name entered by the user is valid.
				if (validTeamName(firstField.getText(), false)) {
                    // gets the teamName from the user input.
					teamName = firstField.getText();
				}
				else {
					return;
				}
                // adding the number of wins for a team.
				if (!secondField.getText().replaceAll(" ", "").equals("")) {
					if (Main.isValid(secondField.getText(), Main.CHECK_INT)) {
                        // checks if the user input is a non-negative integer value.
						if (Integer.parseInt(secondField.getText()) < 0) {
                            // letting the user now that an incorrect value has been input by updating the status.
							leftStatus.setText("Please Input a non-negative value for Team Wins");
							return;
						}
                        // setting the teamWins to the user input value.
						else {
							teamWins = Integer.parseInt(secondField.getText());
						}
					}
                    // letting the user know that the team wins doesn't have a valid integer value.
					else {
						leftStatus.setText("Team Wins does not contain a Valid Integer");
						return;
					}
				}
				else {
                    // setting a default value for the teamWins
					teamWins = -1;
				}
                // adding the number of losses to a team.
				if (!thirdField.getText().replaceAll(" ", "").equals("")) {
					if (Main.isValid(thirdField.getText(), Main.CHECK_INT)) {
                        // checks if the user input is a non-negative value.
						if (Integer.parseInt(thirdField.getText()) < 0) {
                            // letting the user now that an incorrect value has been input by updating the status.
							leftStatus.setText("Please Input a non-negative value for Team Losses");
							return;
						}
						else {
                            // setting the teamLosses to the user input value.
							teamLosses = Integer.parseInt(thirdField.getText());
						}
					}
					// letting the user know that the team losses doesn't have a valid integer value.
					else {
						leftStatus.setText("Team Losses does not contain a Valid Integer");
						return;
					}
				}
				else {
                    // setting the default value for the team losses.
					teamLosses = -1;
				}


				// Adding the team name and the team wins/losses.
				if (teamName != null) {
                    // adding the team name
					Main.addTeam(teamName);
					if (teamWins != -1) {
                        // setting the team Wins.
						int index = Main.getTeamIndex(teamName);
						Main.teams.get(index).setWins(teamWins);
					}
					if (teamLosses != -1) {
                        // setting the team losses.
						int index = Main.getTeamIndex(teamName);
						Main.teams.get(index).setLosses(teamLosses);
					}
					// Letting the user know which team has been added by updating the status.
					rightStatus.setText("Team " + teamName +" Added!");
					leftStatus.setText("");
					firstField.setText("");
					secondField.setText("");
					thirdField.setText("");
					updateLists();

					toggleView.setText("Teams");
					playerPicker.setVisible(false);
					teamPicker.setVisible(true);
					teamPicker.setValue(teamName);
				}
			}
			// if the user wants to add a player.
			else if (playerEnabled) {
				String uName = null;
				String rName = null;
				int age = -1;
				int index = -1;
				// checks if the username entered by the user is valid.
				if (validName(firstField.getText(), true)) {
					// gets the username from the user input.
					uName = firstField.getText();
					// checks if the real name entered by the user is a valid input.
					if (validName(secondField.getText(), false)) {
						// gets the real name from the user input.
						rName = secondField.getText();
						// checks if the player age entered by the user is valid.
						if (Main.isValid(thirdField.getText(), Main.CHECK_INT)) {
							if (Integer.parseInt(thirdField.getText()) < 0) {
								// lets the user know that the age entered is not valid by updating the status.
								leftStatus.setText("Player isn't born yet!");
								return;
							}
							// gets the age of the player from the user input.
							else {
								age = Integer.parseInt(thirdField.getText());
								// adding the player object.
								if (!playerTeam.getValue().equals("Player Team (Optional)")) {
									if (!validTeamName(playerTeam.getValue(), true)) {
										return;
									}
									else {
										index = Main.getTeamIndex(playerTeam.getValue());
										Main.addPlayer(uName, rName, age, index);
									}
								}
								else {
									Main.addPlayer(uName, rName, age, index);
								}
							}
						}
						// letting the user know that the age value is not a valid input
						else {
							leftStatus.setText("Age Value of the Player is not a valid Integer");
							return;
						}
					}
					else {
						return;
					}
				}
				else {
					return;
				}

				// updating the status to let the user know that the player od the given name has been added.
				rightStatus.setText("Player " + uName + " Added!");
				leftStatus.setText("");
				firstField.setText("");
				secondField.setText("");
				thirdField.setText("");
				updateLists();
				playerTeam.setValue("Player Team (Optional)");

				playerPicker.setValue(uName);
				toggleView.setText("Players");
				playerPicker.setVisible(true);
				teamPicker.setVisible(false);
			}
		}
		// displays all the data.
		viewAll();
		// if the autoSave toggle is on, save all the data.
		if (Main.autoSaveOn) {
			save();
		}
		
	}

	/**
	 * This method updates the team information or the player information depending upon the users choice.
	 */
	private void update() {
		// if the user wants to update team data.
		if (teamEnabled) {
			// setting the default values.
			String teamName = null;
			int teamWins = -1;
			int teamLosses = -1;

			// checks if the team name entered by the user is valid.
			if (!firstField.getText().replaceAll(" ", "").equals("")) {
				if (validTeamName(firstField.getText(), false)) {
					// updates the team name to the new name entered by the user.
					teamName = firstField.getText();
				}
				else {
					return;
				}
			}

			// checks if the team wins entered by the user are valid input.
			if (!secondField.getText().replaceAll(" ", "").equals("")) {
				if (Main.isValid(secondField.getText(), Main.CHECK_INT)) {
					// lets the user know that the value entered for team wins is a negative integer by updating the status.
					if (Integer.parseInt(secondField.getText()) < 0) {
						leftStatus.setText("Please Input a non-negative value for Team Wins");
						return;
					}
					else {
						// updates the team wins to the value entered by the user.
						teamWins = Integer.parseInt(secondField.getText());
					}
				}
				else {
					// lets the user know that the value entered is not a valid integer value by updating the status.
					leftStatus.setText("Team Wins does not contain a Valid Integer");
					return;
				}
			}
			// checks if the team losses entered by the user are valid input.
			if (!thirdField.getText().replaceAll(" ", "").equals("")) {
				if (Main.isValid(thirdField.getText(), Main.CHECK_INT)) {
					if (Integer.parseInt(thirdField.getText()) < 0) {
						// lets the user know that the value entered for team losses is a negative integer by updating the status.
						leftStatus.setText("Please Input a non-negative value for Team Losses");
						return;
					}
					else {
						// updates the team losses to the value entered by the user.
						teamLosses = Integer.parseInt(thirdField.getText());
					}
				}
				else {
					// lets the user know that the value entered is not a valid integer value by updating the status.
					leftStatus.setText("Team Losses does not contain a Valid Integer");
					return;
				}
			}
			
			int index = -1;
			if (oldTeam != null && Main.teamExists(oldTeam)) {
				index = Main.getTeamIndex(oldTeam);
			}
			else {
				leftStatus.setText("No Team selected to Update!");
				return;
			}
			
			// updating the team name.
			if (teamName != null) {
				Main.teams.get(index).setName(teamName);
			}
			if (teamWins != -1) {
				// updating the team wins.
				Main.teams.get(index).setWins(teamWins);
			}
			if (teamLosses != -1) {
				// updating the team losses.
				Main.teams.get(index).setLosses(teamLosses);
			}
			// letting the user know that the team data has been updated by updating the status.
			rightStatus.setText("Team " + oldTeam +" Updated!");
			leftStatus.setText("");
			firstField.setText("");
			secondField.setText("");
			thirdField.setText("");
			updateLists();

			teamPicker.setValue(teamName);
			toggleView.setText("Teams");
			playerPicker.setVisible(false);
			teamPicker.setVisible(true);

			teamPicker1.setDisable(false);
			teamPicker.setDisable(false);
			playerPicker.setDisable(false);

		}
		// if the user wants to update a players'  data.
		else if (playerEnabled) {
			// setting the default values.
			String uName = null;
			String rName = null;
			int age = -1;
			String team = null;
			String rank = null;
			String agent = null;
			double HS = -1;
			double ratio = -1;
			int index = -1;

			// checks if the username the user wants to update to is valid input.
			if (!firstField.getText().replaceAll(" ", "").equals("")) {
				if (validName(firstField.getText(), true)) {
					// updating the player username to the given input value.
					uName = firstField.getText();
				}
			}
			// checks if the player real name entered by the user is valid input
			if (!secondField.getText().replaceAll(" ", "").equals("")) {
				if (validName(secondField.getText(), false)) {
					// updating the player real name to the given input value.
					rName = secondField.getText();
				}
			}
			// checks if the player age entered by the user is a valid input.
			if (!thirdField.getText().replaceAll(" ", "").equals("")) {
				if (Main.isValid(thirdField.getText(), Main.CHECK_INT)) {
					if (Integer.parseInt(thirdField.getText()) < 0) {
						// lets the user know that the age entered is not a valid input by updating the status
						leftStatus.setText("Player isn't born yet!");
						return;
					}
					else {
						// updates the player age.
						age = Integer.parseInt(thirdField.getText());
					}
				}
			}

			if (!playerTeam.getValue().equals("Player Team (Optional)") || !playerTeam.getValue().equals("")) {
				if (!validTeamName(playerTeam.getValue(), true)) {
					// letting the user know an error occurred if a valid player team is not entered.
					leftStatus.setText("Unkown Error Occured!");
					return;
				}
				else {
					// updating the player team.
					team = playerTeam.getValue();
				}
			}

			// updating the player rank.
			if (!playerRank.getValue().equals("Player Rank (Optional)") || !playerRank.getValue().equals("")) {
				rank = playerRank.getValue();
			}

			// updating the players' agent.
			if (!playerAgent.getValue().equals("Player Agent (Optional)") || !playerAgent.getValue().equals("")) {
				agent = playerAgent.getValue();
			}

			// updating the players' headshot percentage.
			if (!playerHS.getText().replaceAll(" ", "").equals("")) {
				if (Main.isValid(playerHS.getText(), Main.CHECK_PERCENTAGE)) {
					HS = Double.valueOf(playerHS.getText());
				}
				else {
					// letting the user know that the percentage entered is not valid by updating the status.
					leftStatus.setText("Make sure Headshot percentage is a number between 0-100");
					return;
				}
			}

			// updating a players' win/loss ratio.
			if (!playerRatio.getText().replaceAll(" ", "").equals("")) {
				if (Main.isValid(playerRatio.getText(), Main.CHECK_DOUBLE)) {
					ratio = Double.valueOf(playerRatio.getText());
				}
				else {
					// letting the user know that the value entered is not a valid win/loss ratio by updating the status.
					leftStatus.setText("Make sure the Ratio is a number between 0-100 (1.1, 2,2, 3,3)");
					return;
				}
			}

			index = Main.getTeamIndex(oldPlayer);

			// changing the player username
			if (uName != null) {
				Main.players.get(index).setUserName(uName);
			}
			// changing the player real name
			if (rName != null) {
				Main.players.get(index).setRealName(rName);
			}
			// changing the player age
			if (age != -1) {
				Main.players.get(index).setAge(age);
			}
			// changing the player team
			if (team != null) {
				Main.players.get(index).setTeam(Main.teams.get(Main.getTeamIndex(team)));
			}
			// changing the players rank
			if (rank != null) {
				Main.players.get(index).setSkills("Rank", rank);
			}
			// changing the players agent
			if (agent != null) {
				Main.players.get(index).setSkills("Agent", agent);
			}
			// changing the players' headshot percentage
			if (HS != -1) {
				Main.players.get(index).setSkills("Headshot", String.valueOf(HS));
			}
			// changing the players win/loss ratio
			if (ratio != -1) {
				Main.players.get(index).setSkills("Ratio", String.valueOf(ratio));
			}

			// setting the status to let the user know that the information has been updated.
			rightStatus.setText("Player " + oldPlayer + " Updated!");
			leftStatus.setText("");
			firstField.setText("");
			secondField.setText("");
			thirdField.setText("");
			playerTeam.setValue("Player Team (Optional)");

			playerPicker.setValue(uName);
			toggleView.setText("Players");
			playerPicker.setVisible(true);
			teamPicker.setVisible(false);
		}
		// displaying all the data
		viewAll();
		// if auto save is on, saving all the changes
		if (Main.autoSaveOn) {
			save();
		}
	}

	/**
	 * This method is triggered when the user clicks the Teams/Players button in the details window.
	 * This method switches the values from player to team and vice versa for the toggleView button
	 */
	@FXML
	public void toggleDetails() {
		// if the user wants to view details about a team
		if (toggleView.getText().equals("Teams")) {
			// sets the toggle view value to players.
			toggleView.setText("Players");
			playerPicker.setVisible(true);
			teamPicker.setVisible(false);
		}
		// if the user wants to view details about a player
		else if (toggleView.getText().equals("Players")) {
			// sets the toggle view value to Teams.
			toggleView.setText("Teams");
			playerPicker.setVisible(false);
			teamPicker.setVisible(true);
		}
	}

	/**
	 * This method is triggered when the user clicks the teamPicker button.
	 * This method displays all the  details for the selected team.
	 */
	@FXML
	public void teamSelected() {
		// gets the team whose details have to be displayed, chosen from the user.
		String team = teamPicker.getValue();
		if (team != null) {
			int index = Main.getTeamIndex(team);
			// displays the details of the selected team.
			setDetails(team, index, false);
		}
	}

	/**
	 * This method is triggered when the user clicks the playerPicker button.
	 * This method displays all the details for the selected player.
	 * @param event is an ActionEvent object.
	 */
	@FXML
	public void playerSelected() {
		if (playerPicker.getValue() != null) {
			// gets the player whose details have to be displayed, chosen from the user.
			String player = playerPicker.getValue();
			int index = Main.getPlayerIndex(player);
			// displays the details of the selected player.
			setDetails(player, index, true);
		}
	}
	
	/**
	 * This method sets the details of the player or the team
	 * @param name is the name of the player or the team
	 * @param index is the index of the player or the team
	 * @param player is true if its a player, false if it isn't
	 */
	private void setDetails(String name, int index, boolean player) {
		
		//sets the title of the details
		detailsLabel.setText(name);
		
		//if its a player, get the player detail and set it in the details textArea
		if (player) {
			if (Main.players.get(index).getTeam()!= null) {
				details.setText("Real Name: " + Main.players.get(index).getRealName()
						+ "\nAge: " + Main.players.get(index).getAge()
						+ "\nTeam: " + Main.players.get(index).getTeam().getName()
						+ "\nRank: " + Main.players.get(index).getSkills("Rank")
						+ "\nW/L Ratio: " + Main.players.get(index).getSkills("Ratio")
						+ "\nMost Used Agent: " + Main.players.get(index).getSkills("Agent")
						+ "\nHeadshot %: " + Main.players.get(index).getSkills("Headshot") + "%");
			}
			else {
				details.setText("Real Name: " + Main.players.get(index).getRealName()
						+ "\nAge: " + Main.players.get(index).getAge()
						+ "\nTeam: " + "N/A"
						+ "\nRank: " + Main.players.get(index).getSkills("Rank")
						+ "\nW/L Ratio: " + Main.players.get(index).getSkills("Ratio")
						+ "\nMost Used Agent: " + Main.players.get(index).getSkills("Agent")
						+ "\nHeadshot %: " + Main.players.get(index).getSkills("Headshot") + "%");
			}
		}
		
		//else do the same with a team instead
		else {
			double wins = Main.teams.get(index).getWins();
			double loss = Main.teams.get(index).getLosses();
			double ratio = wins/loss;

			details.setText("Wins: " + Main.teams.get(index).getWins()
					+ "\nLosses: " + Main.teams.get(index).getLosses()
					+ "\nW/L Ratio: " + ratio);
		}
	}
	
	/**
	 * This method checks if the given team name is valid or not
	 * @param teamName is the teamName being checked
	 * @param shouldExist is a boolean that tells us that is the name should exist or not
	 * @return
	 */
	private boolean validTeamName(String teamName, boolean shouldExist) {
		
		//if the name isn't null
		if (teamName != null) {
			
			//if the field isn't empty
			if (!teamName.replaceAll(" ", "").equals("")) {
				
				//if it shouldn't exist
				if (!shouldExist) {
					
					//check if it does, if it does let the user know
					if (Main.teamExists(teamName)) {
						leftStatus.setText("Team with that Name Already Exists");
						return false;
					}
				}
				
				//if the teams exists when it should, return true
				else {
					if (Main.teamExists(teamName)) {
						return true;
					}
					
					//otherwise let the user know it doesn't exist
					else {
						leftStatus.setText("Team with that Name doesn't Exists");
						return false;
					}
				}
			}
			
			//if the field is empty let the user know
			else {
				leftStatus.setText("Team Name is Empty!");
				return false;
			}
		}
		
		//is the name is null, let the user know
		else {
			leftStatus.setText("No Team Name Detected in Text Field!");
			return false;
		}
		return true;
	}

	/**
	 * This method checks if the name given is valid
	 * @param name is the String that contains the name
	 * @param userName is true is its a userName we are checking, otherwise we are checking for the real name
	 * @return returns true is the name is valid, false otherwise
	 */
	private boolean validName(String name, boolean userName) {
		
		//if the name isn't empty
		if (!name.replaceAll(" ", "").equals("")) {
			if (userName) {
				
				//if we are checking for username, make sure it doesn't exist already
				if (Main.playerExists(name)) {
					
					//let the user know if it does
					leftStatus.setText("Player UserName already Exists");
					return false;
				}
			}
		}
		
		//if the name is empty let the user know
		else {
			if (userName) {
				leftStatus.setText("Player User Name is empty");
			}
			else {
				leftStatus.setText("Player Real Name is empty");
			}
			return false;
		}
		
		//return true if haven't already 
		return true;
	}
	
	/**
	 * This method shows all the data in the system
	 */
	@FXML
	public void viewAll() {
		
		//set the label
		viewLabel.setText("All Data");
		viewOpt.setDisable(true);
		viewSort.setDisable(true);
		topField.setDisable(true);

		currentTeam = "";
		
		//show the data
		mainView.setText(util.Menu.printAllFX());

	}
	
	/**
	 * This method shows all players data to the mainView
	 */
	@FXML
	public void viewPlayers() {
		//disable certain things, and set the label
		viewLabel.setText("Player Data - All Teams");
		viewOpt.setDisable(false);
		viewSort.setDisable(false);
		topField.setDisable(false);

		//make certain things invisible and visible
		wins.setVisible(false);
		losses.setVisible(false);
		tRatio.setVisible(false);
		rank.setVisible(true);
		headshot.setVisible(true);
		pRatio.setVisible(true);

		//current team to nothing, since a team isn't selected
		currentTeam = "";
		
		//show the data
		mainView.setText(util.Menu.printPlayersFX(true, ""));
	}
	
	/**
	 * This method shows the team data to the mainView
	 */
	@FXML
	public void viewTeams() {
		//disable certain things, and set the label
		viewLabel.setText("Team Data");
		viewOpt.setDisable(true);
		viewSort.setDisable(false);
		topField.setDisable(false);
		
		//make certain things invisible and visible
		wins.setVisible(true);
		losses.setVisible(true);
		tRatio.setVisible(true);
		rank.setVisible(false);
		headshot.setVisible(false);
		pRatio.setVisible(false);
		
		//current team to nothing, since a team isn't selected
		currentTeam = "";

		//show the team data
		mainView.setText(util.Menu.printTeamsFX(true, "", -1));
	}
	
	/**
	 * This method shows the players in the mainView and sets the other objects visibility
	 * @param team is the team from which players are going to be shown from
	 */
	private void viewPlayers(String team) {
		
		//disable certain things, and set the label
		viewLabel.setText("Player Data - " + team);
		viewOpt.setDisable(false);
		viewSort.setDisable(false);
		topField.setDisable(false);
		
		//make certain things invisible and visible
		wins.setVisible(false);
		losses.setVisible(false);
		tRatio.setVisible(false);
		rank.setVisible(true);
		headshot.setVisible(true);
		pRatio.setVisible(true);
		
		//show the info
		mainView.setText(util.Menu.printPlayersFX(false, team));
	}
	
	/**
	 * This method sorts and shows the teams depending on the sort parameter
	 * @param sort is a String variable that controls which value is going to be used to sort the teams
	 */
	private void sortTeams(String sort) {
		
		//If the topField isn't empty, check if the value is valid
		if (!topField.getText().replaceAll(" ", "").equals("")) {
			if (Main.isValid(topField.getText(), Main.CHECK_INT)) {
				int top = Integer.parseInt(topField.getText());
				if (top > Main.teams.size()) {
					top = Main.teams.size();
				}
				
				//if the value is below 1, let the user know thats invalid
				if (top < 1) {
					leftStatus.setText("Top # Field value cannot be below 1");
					return;
				}
				
				//Show the team data
				viewLabel.setText("Team Data - " + sort + " - Top " + top);

				mainView.setText(util.Menu.printTeamsFX(false, sort, top));
			}
			
			//if the top value is not an integer, let the user know
			else {
				leftStatus.setText("Top # Field does not contain a valid Integer!");
				return;
			}
		}
		//show the team data without any top #
		else {
			viewLabel.setText("Team Data - " + sort);

			mainView.setText(util.Menu.printTeamsFX(false, sort, -1));
		}
	}
	
	/**
	 * This method sorts the players and then sets the sorted players to the mainView textArea
	 * @param sort is a String variable that controls which value is going to be used to sort the players
	 */
	private void sortPlayers(String sort) {
		
		//if there is currently a team selected by the user
		if (!currentTeam.equals("")) {
			
			//count how many players there are in the selected team
			int count = 0;
			for (int i = 0; i < Main.players.size(); i++) {
				if (Main.players.get(i).getTeam().getName().equals(currentTeam)) {
					count++;
				}
			}
			
			//If the top field isnt empty
			if (!topField.getText().replaceAll(" ", "").equals("")) {
				
				//check if its a valid number
				if (Main.isValid(topField.getText(), Main.CHECK_INT)) {
					int top = Integer.parseInt(topField.getText());
					
					//if its over the number of players, set it to number of players
					if (top > count) {
						top = count;
					}
					
					//if the number is 0 or below, let the user know its invalid
					if (top < 1) {
						leftStatus.setText("Top # Field value cannot be below 1");
						return;
					}
					
					//set the label saying what data is being shown
					viewLabel.setText("Player Data - " + currentTeam + " - " + sort + " - Top " + top);
					
					//set the data by getting the value from Menu class
					mainView.setText(util.Menu.printTopPlayersFX(false, currentTeam, sort, top));
				}
				
				//if the top number isn't valid, let the user know
				else {
					leftStatus.setText("Top # Field does not contain a valid Integer!");
					return;
				}
			}
			
			//if the top field is empty
			else {
				
				//Display the data without any top #
				viewLabel.setText("Player Data - " + currentTeam + " - " + sort);

				mainView.setText(util.Menu.printTopPlayersFX(false, currentTeam, sort, -1));
			}
		}
		
		//if no current team is selected, do the same as above without a selected team
		else {
			if (!topField.getText().replaceAll(" ", "").equals("")) {
				if (Main.isValid(topField.getText(), Main.CHECK_INT)) {
					int top = Integer.parseInt(topField.getText());
					if (top > Main.players.size()) {
						top = Main.players.size();
					}
					if (top < 1) {
						leftStatus.setText("Top # Field value cannot be below 1");
						return;
					}
					viewLabel.setText("Player Data - All Teams - " + sort + " - Top " + top);

					mainView.setText(util.Menu.printTopPlayersFX(true, "", sort, top));
				}
				else {
					leftStatus.setText("Top # Field does not contain a valid Integer!");
					return;
				}
			}
			else {
				viewLabel.setText("Player Data - All Teams - " + sort);

				mainView.setText(util.Menu.printTopPlayersFX(true, "", sort, -1));
			}

		}
	}

}