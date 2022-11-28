package no.ntnu.idata2001.wargame;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import no.ntnu.idata2001.wargame.data.CommanderUnit;
import no.ntnu.idata2001.wargame.data.Terrain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



/**
 * Unit-tests of the CommanderUnit class.
 * The following tests are performed:
 * <ul>
 *   <li> Successful creation of instance with valid parameters (positive)</li>
 *   <li> Creation of instance with invalid parameters (negative)</li>
 *   <li> </li>
 * </ul>
 */

public class CommanderUnitTest {
  private CommanderUnit commanderUnit;
  private StringBuilder stringBuilder;
  private int errors;

  /**
   * Sets up environment for testing
   */
  @BeforeEach
  public void setUpCommanderUnit() {
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
  @DisplayName("Test that creation of instance with valid parameter is successful")
  public void testCreationOfInstanceWithValidNameParameters() {
    String prefix = "Expected an IllegalArgumentException since ";
    try {
      CommanderUnit commanderUnit = new CommanderUnit("Orcs", 100, Terrain.FOREST);
    } catch (IllegalArgumentException exception) {
      addError(prefix, "the input is valid. ");
    }

    checkIfTestsFailedAndDisplayResult();
  }

  /**
   * Test that creation of instance with invalid parameter is unsuccessful
   */
  @Test
  @DisplayName("Test that creation of instance with invalid parameter is unsuccessful")
  public void testCreationOfInstanceWithInvalidNameParameters() {
    String prefix = "Expected an IllegalArgumentException since ";
    try {
      CommanderUnit commanderUnit= new CommanderUnit("", 100, Terrain.FOREST);
      addError(prefix, "the input name is empty");
    } catch (IllegalArgumentException exception) {}
    try {
      CommanderUnit commanderUnit= new CommanderUnit(null, 100, Terrain.FOREST);
      addError(prefix, "the input name is null");
    } catch (IllegalArgumentException exception) {}
    try {
      CommanderUnit commanderUnit= new CommanderUnit("Orcs", 0, Terrain.FOREST);
      addError(prefix, "the input health is zero");
    } catch (IllegalArgumentException exception) {}
    try {
      CommanderUnit commanderUnit= new CommanderUnit("Orcs", -100, Terrain.FOREST);
      addError(prefix, "the input health is a negative number");
    } catch (IllegalArgumentException exception) {}
    try {
      CommanderUnit commanderUnit= new CommanderUnit("Orcs", -100, null);
      addError(prefix, "the input terrain cannot be null");
    } catch (IllegalArgumentException exception) {}


    checkIfTestsFailedAndDisplayResult();
  }



}








