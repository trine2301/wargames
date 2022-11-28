package no.ntnu.idata2001.wargame.data.exception;

/**
 *
 * @author Trine Staverløkk
 * @version 0.1
 */
public class CouldNotRemoveUnitException extends Exception {

  /**
   * Makes an instance of the CouldNotRemoveUnitException.
   * @param message The error message.
   */
  public CouldNotRemoveUnitException(String message) {
    super(message);
  }
}
