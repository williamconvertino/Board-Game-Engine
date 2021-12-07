package ooga.exceptions;

public class ImproperlyFormattedFile extends GameError {

  private final String filename;

  public ImproperlyFormattedFile(String filename) {
    this.filename = filename;
  }

  public String getFilename() {
    return filename;
  }

}
