package ooga.display.communication;

import ooga.display.communication.DisplayStateSignaler.State;
import ooga.model.data.tilemodels.TileModel;

public class DisplayComm {

  private DisplayStateSignaler myDisplayStateSignaler;
  private ExceptionHandler myExceptionHandler;

  public DisplayComm(DisplayStateSignaler stateSignaler, ExceptionHandler exceptionHandler) {
    this.myDisplayStateSignaler = stateSignaler;
    this.myExceptionHandler = exceptionHandler;
  }

  public DisplayComm() {

  }

  public void showException(Exception e) {
    //myExceptionHandler.showException(e);
  }

  public void signalDisplay(State state) {
    //myDisplayStateSignaler.signalDisplay(state);
  }

  public void signalTile(TileModel tile) {

  }

}
