package Main;

/**
 * Skills class to deal with all the skills a particular player possesses.
 */

public class Skills{
	// initializing the private fields for the rank,agent,headShot and ratio.
	private String rank = "N/A";
	private String agent = "N/A";
	private double headShot = 0.0;
	private double ratio = 0.0;

	/**
	 * This method is used to access the Rank of a player object.
	 * @return A String that holds the Rank of a player.
	 */
	public String getRank() {
		return rank;
	}

	/**
	 * This method is used to access the Agent of a player object.
	 * @return a String that holds the Agent of the player.
	 */
	public String getAgent() {
		return agent;
	}

	/**
	 *  This method is used to access the headShots of a player object.
	 * @return a double value that holds the players total number of headShots.
	 */
	public double getHeadShot() {
		return headShot;
	}

	/**
	 *  This method is used to access the win/loss ratio of a player object.
	 * @return a double value that holds the ratio of the players win/loss ratio.
	 */
	public double getRatio() {
		return ratio;
	}

	/**
	 * This method is used to set/change the rank of a Player object.
	 * @param rank is a String that holds the value to which the Rank of the player is to be changed.
	 */
	public void setRank(String rank) {
		this.rank = rank;
	}

	/**
	 * This method is used to set/change the agent of a Player object.
	 * @param agent is a String that holds the value of to which to the Agent of the player is to be changed.
	 */
	public void setAgent(String agent) {
		this.agent = agent;
	}

	/**
	 * This method is used to set/change the number of headShots of a Player object.
	 * @param headShot is a double that holds the value to which the total headShots of the player are to be changed.
	 */
	public void setHeadShot(double headShot) {
		this.headShot = headShot;
	}

	/**
	 * This method is used to set/change the win/loss ratio of a Player object.
	 * @param ratio is a double that hold the value to which the ratio is to be changed.
	 */
	public void setRatio(double ratio) {
		this.ratio = ratio;
	}

	/**
	 * This method is used to print a Skills object
	 * It prints out the rank,agent,headShots and win/loss ratio.
	 * @return a string that holds the skills of a Player object.
	 */
	public String toString() {
		return rank + "," + agent + "," + headShot + "," + ratio;
	}
}
