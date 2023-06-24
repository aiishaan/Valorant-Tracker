package Main;

/**
 * Team class that handles all the information and methods for teams.
 */
public class Team {
	// initializing private fields for the name, wins and losses of a team.
	private String name = "N/A";
	private int wins = 0;
	private int losses = 0;

	/**
	 * This is the constructor used to create an object of the class Team.
	 * @param name  is a String that holds the name of the Team object.
	 */
	public Team(String name) {
		this.name = name;
	}

	/**
	 * This method is used to access the name of a Team object.
	 * @return a String that holds the name of the Team.
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method is used to access the number of wins of a Team object
	 * @return an integer that holds the total number of wins a Team has.
	 */
	public int getWins() {
		return wins;
	}

	/**
	 * This method is used to access the number of losses of a Team object
	 * @return an integer that holds the total number of losses a Team has.
	 */
	public int getLosses() {
		return losses;
	}

	/**
	 * This method is used to set/change the name of a Team object.
	 * @param name is a String to which the name of the team is set/changed to.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method is used to set/change the number of wins of a Team object.
	 * @param wins is an integer to which to total number of wins of a team are set/changed to.
	 */
	public void setWins(int wins) {
		this.wins = wins;
	}

	/**
	 * This method is used to set/change the number of losses of a Team object.
	 * @param losses is an integer to which to total number of losses of a team are set/changed to.
	 */
	public void setLosses(int losses) {
		this.losses = losses;
	}

	/**
	 * This method is used to print a Team object.
	 * It prints out the name,wins and loses of a team.
	 * @return a string that holds the  name,wins and loses of a team.
	 */
	public String toString() {
		return name + "," + wins + "," + losses;
	}
}
