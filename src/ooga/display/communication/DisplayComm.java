package ooga.display.communication;


public class DisplayComm {

  private ExceptionHandler myExceptionHandler;

  public DisplayComm() {
    this.myExceptionHandler = new ExceptionHandler();
  }

  public void showException(Exception e) {
    myExceptionHandler.showException(e);
  }


}
