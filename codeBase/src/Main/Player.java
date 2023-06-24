package Main;

/**
 * Player class to deal with all the player statistics and information.
 */
public class Player {

	// initializing private fields for the player username, realName and player age.
	private String userName = "N/A";
	private String realName = "N/A";
	private int age = 0;
	// making a new Skill object from the Skills class.
	private Skills skills = new Skills();
	// creating a null team object from the Team class.
	private Team team = null;

	/**
	 * Creating the constructor for the Player class that also adds the player to a team.
	 * @param userName is a String that stores the username of the player.
	 * @param realName is a String that stores the real name of the player.
	 * @param age is an integer type that stores the age of the player.
	 * @param team is a Team object to which the player is to be added.
	 */
	public Player(String userName, String realName, int age, Team team) {
		this.userName = userName;
		this.realName = realName;
		this.age = age;
		this.team = team;
	}

	/**
	 * Creating the constructor for the Player class.
	 * @param userName is the String that holds the username of the player.
	 * @param realName is the String that holds the realName of the player.
	 * @param age is an integer type that stores the age of th ePlayer.
	 */
	public Player(String userName, String realName, int age) {
		this.userName = userName;
		this.realName = realName;
		this.age = age;
	}

	/**
	 * This method is used to get the userName of a Player object.
	 * @return returns the username of the Player object
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * This method is used to get the realName of a Player object.
	 * @return returns the realName of the player.
	 */
	public String getRealName() {
		return realName;
	}

	/**
	 * This method is used to get the age of the Player object.
	 * @return returns the age of the player
	 */
	public int getAge() {
		return age;
	}

	/**
	 * This method returns the team of the Player object if its is in a team.
	 * Otherwise, returns null
	 * @return returns the team of the player if the player has team, otherwise returns null.
	 */
	public Team getTeam() {
		// checks if the player is in a team.
		if (team != null) {
			// returns the team of the player.
			return team;
		}
		else {
			// prints out the message that the player is not in a team.
			System.out.println("Player is not in Any Team");
		}
		// default return null.
		return null;
		
	}

	/**
	 * This method uses the methods from the Skills class to get the requested skill for a particular player.
	 * @param skill is a String that stores the skill we want to get for our player.
	 * @return returns the requested skill for the player as a string.
	 * Return all the skills as a default if no skill is matched.
	 */
	public String getSkills(String skill) {
		// returning the rank of the player if it is requested
		if (skill.equalsIgnoreCase("Rank")) {
			return skills.getRank();
		// returning the agent of the player if it is requested
		} else if (skill.equalsIgnoreCase("Agent")) {
			return skills.getAgent();
		// returning the number of headShots of a player as a String if requested.
		} else if (skill.equalsIgnoreCase("HeadShot")) {
			return String.valueOf(skills.getHeadShot());
		// returning the win loss ratio of the player as a string if requested.
		} else if (skill.equalsIgnoreCase("Ratio")) {
			return String.valueOf(skills.getRatio());
		}
		// Returns all the skills of the player as a String.
		return skills.toString();
	}

	/**
	 * This method is used to set and change the username of a Player object.
	 * @param userName is a String to which the username is se/changed to.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * This method is used to set and change the realName of a PLayer object.
	 * @param realName is a String to which the realName is set/changed to
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
	 * This method is used to set and change the age of a Player object.
	 * @param age is an integer value to which the age is set/changed to.
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * This method is used to set and change the team of a Player object.
	 * @param team is a new Team object to which the team of the player is set/changed to.
	 */
	public void setTeam(Team team) {
		this.team = team;
	}

	/**
	 * This method is used to set/change a particular skill/skills of a Player object using the methods from the Skill class.
	 * @param skill is the String that stores the Skill we want to set/change.
	 * @param value is the String that stores the value we want to set/change the skill of the Player object to.
	 */
	public void setSkills(String skill, String value) {
		//checking if the skill requested to be set/changed is a Rank
		if (skill.equalsIgnoreCase("Rank")) {
			// setting the value to the given value.
			skills.setRank(value);
		// checking if the skill requested to be set/changed is an Agent
		} else if (skill.equalsIgnoreCase("Agent")) {
			// setting the value to the given value.
			skills.setAgent(value);
		// checking if the skill requested to be set/changed is the players Headshots.
		} else if (skill.equalsIgnoreCase("HeadShot")) {
			// setting the value ot the given value by converting it to a double.
			skills.setHeadShot(Double.parseDouble(value));
		// checking if the skill requested to be set/changed is a players win/loss ratio.
		} else if (skill.equalsIgnoreCase("Ratio")) {
			// setting the value to the given value by converting it to a double.
			skills.setRatio(Double.parseDouble(value));
		}
		// print an error message if skill is not found.
		else {
			System.out.println("Error");
		}
	}

	/**
	 * This method is used to print a Player object.
	 * It prints out the realName,userName,age and team of a player.
	 * @return returns a String that stores the  realName,userName,age and team of a player.
	 */
	public String toString() {
		return realName + "," + userName + "," + age + "," + team.getName();
	}

	
}
