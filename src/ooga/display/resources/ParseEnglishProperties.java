package ooga.display.resources;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ResourceBundle;
import ooga.display.Display;

public class ParseEnglishProperties {

  public static final String TARGET_LANGUAGE = "French";
  public static final String FILE_MESSAGE = "Language configuration for: %s";

  public static void main(String[] args) throws FileNotFoundException {
    String path = Display.class.getPackageName()+".resources." + "English";
    ResourceBundle initialFile = ResourceBundle.getBundle(path);
    File myFile = new File( TARGET_LANGUAGE +".txt");
    PrintWriter out = new PrintWriter(myFile);

    for (String key: initialFile.keySet()) {
      out.println(initialFile.getString(key));
    }
    out.close();
  }
}
