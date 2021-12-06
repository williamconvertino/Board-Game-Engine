package ooga.exceptions;

public class InvalidFileFormatException extends GameError {

  String fileName;

  public InvalidFileFormatException(String fileName) {
    this.fileName = fileName;
  }

  public InvalidFileFormatException() {

  }
}
