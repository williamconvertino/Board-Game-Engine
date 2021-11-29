package ooga.exceptions;

public class ImproperlyFormattedFile extends Exception {

  private String filename;

  public ImproperlyFormattedFile(String filename) {
    this.filename = filename;
  }

  public String getFilename() {
    return filename;
  }

}
