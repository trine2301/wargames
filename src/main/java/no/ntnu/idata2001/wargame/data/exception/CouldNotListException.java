package no.ntnu.idata2001.wargame.data.exception;

/**
 *
 * @author Trine Staverløkk
 * @version 0.1
 */
public class CouldNotListException extends Exception {
  /**
   * Makes an instance of the CouldNotListException.
   * @param message The error message.
   */
  public CouldNotListException(String message) {
    super(message);
  }
}
