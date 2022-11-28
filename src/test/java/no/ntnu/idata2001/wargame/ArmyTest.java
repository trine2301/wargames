package no.ntnu.idata2001.wargame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;
import no.ntnu.idata2001.wargame.data.exception.CouldNotRemoveUnitException;
import no.ntnu.idata2001.wargame.data.Army;
import no.ntnu.idata2001.wargame.data.InfantryUnit;
import no.ntnu.idata2001.wargame.data.Terrain;
import no.ntnu.idata2001.wargame.data.Unit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


/**
 * Unit-tests of the Army class.
 * The following tests are performed:
 * <ul>
 *   <li> Test that creation of instance with valid parameter is successful (positive).</li>
 *   <li> Test that creation of instance with invalid parameter is unsuccessful (negative).</li>
 *   <li> Test that adding of a unit with valid parameters is successful in addUnit()-method (positive).</li>
 *   <li> Test that adding a unit with invalid parameters is unsuccessful in addUnit()-method (negative).</li>
 *   <li> Test that all units with valid input are successfully added in addAll() (positive).</li>
 *   <li> Test that addAll with invalid parameters is unsuccessful (negative).</li>
 *   <li> Test that unit with valid input are successfully removed in remove(unit) (positive).</li>
 *   <li> Test that a unit is not removed with invalid parameters (negative).</li>
 *   <li> Try to remove a unit that is not in list in remove(unit) (negative).</li>
 * </ul>
 */

public class ArmyTest {
  private Army army;
  private Unit unit;
  private StringBuilder stringBuilder;
  private int errors;

  /**
   * Sets up the environment for testing
   */
  @BeforeEach
  public void setUpTestArmy() {
    try {
      this.army = new Army("ArmyOne");
      this.unit = new InfantryUnit("Orcs", 10, Terrain.FOREST);
      army.addUnit(unit);
    } catch (IllegalArgumentException e) {
      fail("The test data could not be added");
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
   * Test that creation of instance with valid parameter is successful (positive).
   */
  @Test
  @DisplayName("Checks if creation of instance works with valid input")
  public void testCreationOfInstanceWithValidNameParameters() {
    String prefix = "Expected an IllegalArgumentException since ";
    try {
      this.army = new Army("ArmyOne");
    } catch (IllegalArgumentException exception) {
      addError(prefix, "the input is valid. ");
    }

    checkIfTestsFailedAndDisplayResult();
  }


  /**
   * Test that creation of instance with invalid parameter is unsuccessful (negative).
   */
  @Test
  @DisplayName("Checks that creation of instance with invalid input is unsuccessful")
  public void testCreationOfInstanceWithInvalidParameters() {
    String prefix = "Expected an IllegalArgumentException since ";
    try {
      army= new Army("");
      addError(prefix, "the input name is empty");
    } catch (IllegalArgumentException exception) {}
      //I do nothing in here since I except to get an exception. Only registers errors above the catch.
    try {
      army= new Army(null);
      addError(prefix, "the input name is null");
    } catch (IllegalArgumentException exception) { }

    checkIfTestsFailedAndDisplayResult();

  }


  /**
   * Test that adding of a unit with valid parameters is successful in addUnit()-method (positive).
   */
  @Test
  @DisplayName("Test that adding of a unit with valid parameters is successful in addUnit()-method")
  public void testAddUnit() {
    String prefix = "Expected an IllegalArgumentException since ";
    try {
      army.addUnit(unit);
    } catch (IllegalArgumentException exception) {
      addError(prefix, "the input is valid. ");
    }

    checkIfTestsFailedAndDisplayResult();
  }


  /**
   * Test that adding a unit with invalid parameters is unsuccessful in addUnit()-method (negative).
   */
  @Test
  @DisplayName("Test that adding a unit with invalid parameters is unsuccessful in addUnit()-method.")
  public void testAddUnitWithInvalidParameters() {
    try {
      army.addUnit(null);
      fail("Expected to get an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
    checkIfTestsFailedAndDisplayResult();
  }


  /**
   * Test that all units with valid input are successfully added in addAll() (positive).
   */
  @Test
  @DisplayName("Test that all units with valid input are successfully added in addAll()")
  public void testAddAll() {
    String prefix = "Expected an IllegalArgumentException since ";
    List<Unit> units = new ArrayList<>();
    try {
      army.addAll(units);
    } catch (IllegalArgumentException exception) {
      addError(prefix, "the input is valid. ");
    }
    checkIfTestsFailedAndDisplayResult();
  }

  /**
   * Test that addAll with invalid parameters is unsuccessful (negative).
   */
  @Test
  @DisplayName("Test that addAll with invalid parameters is unsuccessful (negative)")
  public void testAddAllWithInvalidParameters() {
    try {
      army.addAll(null);
      fail("Expected to get an illegal argument exception");
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
    checkIfTestsFailedAndDisplayResult();
  }


  /**
   * Test that unit with valid input are successfully removed in remove(unit) (positive).
   */
  @Test
  @DisplayName("Test that unit with valid input are successfully removed in remove(unit)")
  public void testRemoveUnitWithValidParameters() {
    String prefix = "Expected an CouldNotRemoveUnitException since ";
    try {
      army.remove(unit);
    } catch (CouldNotRemoveUnitException | IllegalArgumentException e) {
      addError(prefix, "the input is valid");
    }
    checkIfTestsFailedAndDisplayResult();
  }

  /**
   *
   * Test that a unit is not removed with invalid parameters (negative).
   */
  @Test
  @DisplayName("Test removeUnit with invalid parameters")
  public void testRemoveUnitWithInvalidParameters() {
    try {
      army.remove(null);
      fail("Expected to get an IllegalArgumentException");
    } catch (IllegalArgumentException exception) {
      assertTrue(true);
    } catch (CouldNotRemoveUnitException e) {
      fail("Expected to get an IllegalArgumentException");
    }
  }


  /**
   * Try to remove a unit that is not in list in remove(unit) (negative).
   */
  @Test
  @DisplayName("Try to remove a unit that is not in list in remove(unit)")
  public void testRemoveUnitThatDoesNotExistInList() {
    Unit unitToRemove = new InfantryUnit("Elves", 10, Terrain.FOREST);
    try {
      army.remove(unitToRemove);
      fail("expected to get an CouldNotRemoveUnitException");
    } catch (CouldNotRemoveUnitException e) {
      assertTrue(true);
    }
  }

}
