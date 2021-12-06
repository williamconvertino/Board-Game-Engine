package ooga.display.communication;


import ooga.exceptions.PlayerWarning;
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
    if (e instanceof PlayerWarning) {
      LOG.warn(String.format("[%s]", e.toString()));
    } else {
      e.printStackTrace();
      LOG.error(String.format("%s", e.toString()));
    }

  }

}
