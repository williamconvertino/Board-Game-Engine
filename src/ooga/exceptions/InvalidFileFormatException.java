package ooga.exceptions;

public class InvalidFileFormatException extends Exception {

  String fileName;

  public InvalidFileFormatException(String fileName) {
    this.fileName = fileName;
  }

  public InvalidFileFormatException() {

  }
}
