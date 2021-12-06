package ooga.exceptions;

public class ImproperlyFormattedFile extends GameError {

  private String filename;

  public ImproperlyFormattedFile(String filename) {
    this.filename = filename;
  }

  public String getFilename() {
    return filename;
  }

}
