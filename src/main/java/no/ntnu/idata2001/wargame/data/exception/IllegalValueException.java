package no.ntnu.idata2001.wargame.data.exception;

/**
 *
 * @author Trine Staverløkk
 * @version 0.1
 */
public class IllegalValueException extends Exception {
  /**
   * Makes an instance of the IllegalValueException
   * @param message The error message.
   */
  public IllegalValueException(String message) {
    super(message);
  }
}

