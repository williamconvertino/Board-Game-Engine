package ooga.display.communication;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExceptionHandler {

  private static final Logger LOG = LogManager.getLogger(ExceptionHandler.class);

  public ExceptionHandler() {

  }

  public void showException(Exception e) {
    LOG.error(e.toString());
  }

}
