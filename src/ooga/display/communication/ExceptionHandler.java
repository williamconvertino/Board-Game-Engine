package ooga.display.communication;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Handle logging exceptions
 *
 * @author William Convertino
 */
public class ExceptionHandler {

  private static final Logger LOG = LogManager.getLogger(ExceptionHandler.class);
  static {
    
  }

  public ExceptionHandler() {

  }

  public void showException(Exception e) {
    LOG.error(e.toString());
  }

}
