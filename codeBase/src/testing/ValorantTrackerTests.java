package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ValorantTrackerTests {
	
	//Constants used in validity checks.
	static final int CHECK_PERCENTAGE = -2;
	static final int CHECK_DOUBLE = -1;
	static final int CHECK_MENU_INT = 0;
	static final int CHECK_ADD_REMOVE = 1;
	static final int CHECK_YES_NO = 2;
	static final int CHECK_INT = 3;
	static final int CHECK_TEAM_INT = 4;
	static final int CHECK_PLAYER_INT = 5;

	//Updating Team Name
	static final int TEAM_NAME = 6;
	static final int ABBREVIATION = 7;

	//Updating Team Statistics
	static final int WINS = 8;
	static final int LOSSES = 9;
	
	//Tests if the arguments given are correctly recognized or not
	@Test
	void mainCheckArgsTest() {
		
		String[] args = {};
		
		boolean expResult = false;
		boolean result = Main.Main.checkArgs(args);
		
		assertEquals(result, expResult);
	}
	
	//checks if there is an argument given by the user
	@Test
	void mainCheckArgsTest2() {
		
		String[] args = {"File"};
		
		boolean expResult = true;
		boolean result = Main.Main.checkArgs(args);
		
		assertEquals(result, expResult);
	}
	
	//check if the rank matches the rank in the list of ranks
	@Test
	void mainRankMatches() {
		boolean expResult = true;
		boolean result = Main.Main.rankMatches("Immortal 1");
		
		assertEquals(result, expResult);
	}
	@Test
	void mainRankMatches2() {
		boolean expResult = false;
		boolean result = Main.Main.rankMatches("Player1");
		
		assertEquals(result, expResult);
	}
	

	// This Test checks whether the given option value falls between a correct percentage value
	// i.e. between 0-100 using the isValid method.

	//Expected result is true to check if the function returns correct value when the option is between 0-100
	@Test
	void testIsValidPercentage() {
		String option = "58";

		boolean expResult = true;
		boolean result = Main.Main.isValid(option, CHECK_PERCENTAGE);

		assertEquals(expResult, result, "Value of 58 is an Integer (Double also works) and falls between 0 and 100, therefore should be true");
	}

	// Expected result is false to check if the function returns the correct value when the option entered by the user is not between 0-100.
	@Test
	void testIsValidPercentage1() {

		String option = "101";

		boolean expResult = false;
		boolean result = Main.Main.isValid(option, CHECK_PERCENTAGE);

		assertEquals(expResult, result, "Value of 101 is an Integer (Double also works) but does not fall between 0 and 100, therefore should be false");
	}

	//This Test checks whether the given option value is a valid double with decimals
	// using the isValid method.

	// Expected result is true to check if the function returns the correct value when a proper decimal is entered by the user.
	@Test
	void testIsValidDouble() {

		String option = "1.990";

		boolean expResult = true;
		boolean result = Main.Main.isValid(option, CHECK_DOUBLE);

		assertEquals(expResult, result, "Value 1.990 is a Double number with decimals, therefore should be true");
	}

	// Expected result is false to check if the function returns the correct value when a proper decimal is not entered by the user.
	@Test
	void testIsValidDouble1() {

		String option = "a";

		boolean expResult = false;
		boolean result = Main.Main.isValid(option, CHECK_DOUBLE);

		assertEquals(expResult, result, "Value a is not a Double number with decimals, therefore should be false");
	}

	// Tests to check if the user enters the correct menu option( an integer that is between 1-15) using the isValid method.

	// Expected result is true to check if the function returns the correct value when the user enters
	// the correct menu option(an integer between 1-15).
	@Test
	void testIsValidMenu() {

		String option = "3";

		boolean expResult = true;
		boolean result = Main.Main.isValid(option, CHECK_MENU_INT);

		assertEquals(expResult, result, "Value 3 is an Integer and falls between 1 and 15, therefore should be true");
	}

	// Expected result is false to check if the function returns the correct value when the user enters
	// the wrong menu option(an integer not between 1-15)
	@Test
	void testIsValidMenu1() {

		String option = "20";

		boolean expResult = false;
		boolean result = Main.Main.isValid(option, CHECK_MENU_INT);

		assertEquals(expResult, result, "Value 20 is an Integer but does not fall between 1 and 15, therefore should be false");
	}

	// Expected result is false to check if the function returns the correct value when the user enters
	// the wrong menu option(not an integer)
	@Test
	void testIsValidMenu2() {

		String option = "a";

		boolean expResult = false;
		boolean result = Main.Main.isValid(option, CHECK_MENU_INT);

		assertEquals(expResult, result, "Value a is not an Integer, therefore should be false");
	}

	// Tests to check if the user enters a correct option( a String that is either a or r) using the isValid method

	// Expected result is true to check if the function returns the correct value when the user enters
	// a correct option(a String that is either a or r).
	@Test
	void testIsValidAdd() {

		String option = "a";

		boolean expResult = true;
		boolean result = Main.Main.isValid(option, CHECK_ADD_REMOVE);

		assertEquals(expResult, result, "Value a is a String that is either a or r, therefore should be true");
	}

	// Expected result is false to check if the function returns the correct value when the user enters
	// an incorrect option(a String that is neither a nor r).
	@Test
	void testIsValidAdd1() {

		String option = "c";

		boolean expResult = false;
		boolean result = Main.Main.isValid(option, CHECK_ADD_REMOVE);

		assertEquals(expResult, result, "Value c is a String but is neither a nor r, therefore should be false");
	}

	// Expected result is false to check if the function returns the correct value when the user enters
	// an incorrect option(not a String).
	@Test
	void testIsValidAdd2() {

		String option = "2";

		boolean expResult = false;
		boolean result = Main.Main.isValid(option, CHECK_ADD_REMOVE);

		assertEquals(expResult, result, "Value 2 is not a String, therefore should be true");
	}


	// Tests to check if the user enters a correct option( a String that is either a or r) using the isValid method


	// Expected result is true to check if the function returns the correct value when the user enters
	// a correct option(a String that is either a or r).
	@Test
	void testIsValidRemove() {

		String option = "r";

		boolean expResult = true;
		boolean result = Main.Main.isValid(option, CHECK_ADD_REMOVE);

		assertEquals(expResult, result, "Value r is a String that is either a or r, therefore should be true");
	}

	// Expected result is false to check if the function returns the correct value when the user enters
	// an incorrect option(a String that is neither a nor r).
	@Test
	void testIsValidRemove1() {

		String option = "c";

		boolean expResult = false;
		boolean result = Main.Main.isValid(option, CHECK_ADD_REMOVE);

		assertEquals(expResult, result, "Value c is a String that is neither a nor r, therefore should be false");
	}

	// Expected result is false to check if the function returns the correct value when the user enters
	// an incorrect option(not a String).
	@Test
	void testIsValidRemove2() {

		String option = "2";

		boolean expResult = false;
		boolean result = Main.Main.isValid(option, CHECK_ADD_REMOVE);

		assertEquals(expResult, result, "Value 2 is not a String, therefore should be false");
	}

	// Tests to check if the user enters a correct option( a String that is either y or n) using the isValid method

	// Expected result is true to check if the function returns the correct value when the user enters
	// a correct option(a String that is y).
	@Test
	void testIsValidYes() {

		String option = "y";

		boolean expResult = true;
		boolean result = Main.Main.isValid(option, CHECK_YES_NO);

		assertEquals(expResult, result, "Value y is a String that is either y or n, therefore should be true");
	}

	// Expected result is false to check if the function returns the correct value when the user enters
	// an incorrect option(a String that is neither not y).
	@Test
	void testIsValidYes1() {

		String option = "noo";

		boolean expResult = false;
		boolean result = Main.Main.isValid(option, CHECK_YES_NO);

		assertEquals(expResult, result, "Value noo is a String that is neither y nor n, therefore should be false");
	}

	// Expected result is true to check if the function returns the correct value when the user enters
	// a correct option(a String that is n).
	@Test
	void testIsValidNo() {

		String option = "n";

		boolean expResult = true;
		boolean result = Main.Main.isValid(option, CHECK_YES_NO);

		assertEquals(expResult, result, "Value n is a String that is either y or n, therefore should be true");
	}

	// Expected result is false to check if the function returns the correct value when the user enters
	// an incorrect option(a String that is not n).
	@Test
	void testIsValidNo1() {

		String option = "abc";

		boolean expResult = false;
		boolean result = Main.Main.isValid(option, CHECK_YES_NO);

		assertEquals(expResult, result, "Value abc is a String that is neither y nor n, therefore should be false");
	}

	// Tests to check if the user enters a correct option(a proper integer) using the isValid method.

	// Expected result is true to check if the function returns the correct value when the user enters
	// a correct option(a proper integer).
	@Test
	void testIsValidInteger() {

		String option = "1";

		boolean expResult = true;
		boolean result = Main.Main.isValid(option, CHECK_INT);

		assertEquals(expResult, result, "Value 1 is an Integer, therefore should be true");
	}

	// Expected result is false to check if the function returns the correct value when the user enters
	// an incorrect option(not an integer value).
	@Test
	void testIsValidInteger1() {

		String option = "a";

		boolean expResult = false;
		boolean result = Main.Main.isValid(option, CHECK_INT);

		assertEquals(expResult, result, "Value a is not an Integer, therefore should be false");
	}

	// Tests to check if the user enters a correct option(a proper integer value between 1 and 3) using the isValid method.

	// Expected result is true to check if the function returns the correct value when the user enters
	// a correct option(a proper integer value between 1 and 3).
	@Test
	void testIsValidTeam() {

		String option = "2";

		boolean expResult = true;
		boolean result = Main.Main.isValid(option, CHECK_TEAM_INT);

		assertEquals(expResult, result, "Value 2 is an Integer and is between 1 and 3, therefore should be true");
	}

	// Expected result is false to check if the function returns the correct value when the user enters
	// an incorrect option(a proper integer value not between 1 and 3).
	@Test
	void testIsValidTeam1() {

		String option = "5";

		boolean expResult = false;
		boolean result = Main.Main.isValid(option, CHECK_TEAM_INT);

		assertEquals(expResult, result, "Value 5 is an Integer but is not between 1 and 3, therefore should be false");
	}

	// Expected result is false to check if the function returns the correct value when the user enters
	// an incorrect option(not an integer value).
	@Test
	void testIsValidTeam2() {

		String option = "a";

		boolean expResult = false;
		boolean result = Main.Main.isValid(option, CHECK_TEAM_INT);

		assertEquals(expResult, result, "Value a is not an Integer, therefore should be false");
	}

	// Tests to check if the user enters a correct option(a proper integer value between 1 and 7) using the isValid method.

	// Expected result is true to check if the function returns the correct value when the user enters
	// a correct option(a proper integer value between 1 and 7).
	@Test
	void testIsValidPlayer() {

		String option = "6";

		boolean expResult = true;
		boolean result = Main.Main.isValid(option, CHECK_PLAYER_INT);

		assertEquals(expResult, result, "Value 6 is an Integer and falls between 1 and 8, therefore should be true");
	}

	// Expected result is false to check if the function returns the correct value when the user enters
	// an incorrect option(a proper integer value not between 1 and 7).
	@Test
	void testIsValidPlayer1() {

		String option = "9";

		boolean expResult = false;
		boolean result = Main.Main.isValid(option, CHECK_PLAYER_INT);

		assertEquals(expResult, result, "Value 9 is an Integer but does not fall between 1 and 8, therefore should be false");
	}

	// Expected result is false to check if the function returns the correct value when the user enters
	// an incorrect option(not an integer value).
	@Test
	void testIsValidPlayer2() {

		String option = "a";

		boolean expResult = false;
		boolean result = Main.Main.isValid(option, CHECK_PLAYER_INT);

		assertEquals(expResult, result, "Value a is not an Integer, therefore should be false");
	}
}
