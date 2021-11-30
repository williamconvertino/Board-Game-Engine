package ooga.display.communication;

/**
 * The display communication which shows exceptions
 *
 * @author William Convertino
 */
public class DisplayComm {

  private ExceptionHandler myExceptionHandler;

  /**
   * The constructor to make a exception handler in DisplayComm
   */
  public DisplayComm() {
    this.myExceptionHandler = new ExceptionHandler();
  }

  /**
   * Handles showing the exception passed in
   * @param e
   */
  public void showException(Exception e) {
    myExceptionHandler.showException(e);
  }


}
