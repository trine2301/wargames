package no.ntnu.idata2001.wargame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;
import no.ntnu.idata2001.wargame.data.Army;
import no.ntnu.idata2001.wargame.data.InfantryUnit;
import no.ntnu.idata2001.wargame.data.Terrain;
import no.ntnu.idata2001.wargame.data.Unit;
import no.ntnu.idata2001.wargame.data.exception.CouldNotRemoveUnitException;
import no.ntnu.idata2001.wargame.logic.Battle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests of the Battle class.
 * The following tests are performed:
 * <ul>
 *   <li> Successful creation of instance with valid parameters (positive)</li>
 *   <li> Creation of instance with invalid parameters (negative)</li>
 *   <li> Test that the winner is the right winner (positive)</li>
 *   <li> </li>
 * </ul>
 */



public class BattleTest {


  private Army armyOne;
  private Army armyTwo;
  private Battle battle;
  private StringBuilder stringBuilder;
  private int errors;

  /**
   * Sets up the environment for testing.
   */
  @BeforeEach
  public void setUpTestBattle() {
    //Adding some test data to battleTest
    try {
      armyOne = new Army("ArmyOne");
      armyTwo = new Army("ArmyTwo");
      this.battle = new Battle(armyOne, armyTwo);
    } catch (IllegalArgumentException e) {
      fail("Test data could not be added");
    }
    stringBuilder = new StringBuilder();
    errors = 0;
  }

  /**
   * Adds a new error to the stringBuilder.
   * @param errorPrefix what it should say before the error.
   * @param error the error to append.
   */
  private void addError(String errorPrefix, String error){
    stringBuilder.append("\n").append(errorPrefix).append(error);
    errors++;
  }

  /**
   * Checks if the tests failed and displays the results.
   */
  private void checkIfTestsFailedAndDisplayResult(){
    if(stringBuilder.length() == 0){
      assertTrue(true);
    }else {
      fail("\nAmount of errors " + errors + " listed errors: " + stringBuilder);
    }
  }


  /**
   * Test that creation of instance with valid parameter is successful
   */
  @Test
  @DisplayName("Checks if creation of instance works with valid input")
  public void testCreationOfInstanceWithValidNameParameters() {
    String prefix = "Expected an IllegalArgumentException since ";
    try {
      this.battle = new Battle(armyOne, armyTwo);
    } catch (IllegalArgumentException exception) {
      addError(prefix, "the input is valid. ");
    }
    checkIfTestsFailedAndDisplayResult();
  }


  /**
   * Test that creation of instance with invalid parameter is unsuccessful.
   */
  @Test
  @DisplayName("Checks that creation of instance with invalid input is unsuccessful")
  public void testCreationOfInstanceWithInvalidParameters() {
    String prefix = "Expected an IllegalArgumentException since ";
    try {
      Battle battle= new Battle(null, armyTwo);
      addError(prefix, "the input armyOne is null");
    } catch (IllegalArgumentException exception) {}
    try {
      Battle battle= new Battle(armyOne, null);
      addError(prefix, "the input armyTwo is null");
    } catch (IllegalArgumentException exception) {}

    checkIfTestsFailedAndDisplayResult();
  }

  /**
   * Test that expected army wins.
   */
  @Test
  @DisplayName("Test that expected army wins.")
  void hasRightWinnerWon() throws CouldNotRemoveUnitException {
    List<Unit> badUnits = new ArrayList<>();
    List<Unit> goodUnits = new ArrayList<>();
    badUnits.add(new InfantryUnit("Losers", 1, Terrain.FOREST));
    goodUnits.add(new InfantryUnit("Winners", 10000, Terrain.FOREST));


    Army armyLoser = new Army("Losing army", badUnits);
    Army armyWinner = new Army("Winning army", goodUnits);
    Battle battle= new Battle(armyLoser, armyWinner);

    assertEquals(armyWinner, battle.simulate());

  }

}
