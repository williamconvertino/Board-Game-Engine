package ooga.display.communication;

import ooga.display.communication.DisplayStateSignaler.State;

public class DisplayComm {

  DisplayStateSignaler displayStateSignaler;
  ExceptionHandler myExceptionHandler;

  public void showException(Exception e) {
    myExceptionHandler.showException(e);
  }

  public void setDisplayStateSignaler(State state) {
    displayStateSignaler.signalDisplay(state);
  }

}
