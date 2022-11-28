package no.ntnu.idata2001.wargame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import no.ntnu.idata2001.wargame.data.RangedUnit;
import no.ntnu.idata2001.wargame.data.Terrain;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

/**
 * Unit-tests of the CavalryUnit class.
 * The following tests are performed:
 * <ul>
 *   <li> Successful creation of instance with valid parameters (positive)</li>
 *   <li> Creation of instance with invalid parameters (negative)</li>
 * </ul>
 */

public class RangedUnitTest {
  private RangedUnit rangedUnit;
  private StringBuilder stringBuilder;
  private int errors;

  /**
   * Sets up environment for testing
   */
  @BeforeEach
  public void setUpRangedUnit() {
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
      RangedUnit rangedUnit = new RangedUnit("Orcs", 100, Terrain.FOREST);
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
      RangedUnit rangedUnit = new RangedUnit("", 100, Terrain.FOREST);
      addError(prefix, "the input name is empty");
    } catch (IllegalArgumentException exception) {}
    try {
      RangedUnit rangedUnit = new RangedUnit(null, 100, Terrain.FOREST);
      addError(prefix, "the input name is null");
    } catch (IllegalArgumentException exception) {}
    try {
      RangedUnit rangedUnit = new RangedUnit("Orcs", 0, Terrain.FOREST);
      addError(prefix, "the input health is zero");
    } catch (IllegalArgumentException exception) {}
    try {
      RangedUnit rangedUnit = new RangedUnit("Orcs", -100, Terrain.FOREST);
      addError(prefix, "the input health is a negative number");
    } catch (IllegalArgumentException exception) {}
    try {
      RangedUnit rangedUnit = new RangedUnit("Orcs", -100, null);
      addError(prefix, "the input terrain cannot be null");
    } catch (IllegalArgumentException exception) {}


    checkIfTestsFailedAndDisplayResult();
  }



}







