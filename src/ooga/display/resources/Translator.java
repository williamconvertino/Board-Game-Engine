package ooga.display.resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.ResourceBundle;
import ooga.display.Display;
import org.junit.Test;

public class Translator {

  public static final String TARGET_LANGUAGE = "French";
  public static final String FILE_MESSAGE = "Language configuration for: %s";


  public static void main(String[] args) throws IOException {

      String path = Display.class.getPackageName()+".resources." + "English";
      String pathh = String.format("%s%s",TARGET_LANGUAGE,".txt");
      ResourceBundle initialFile = ResourceBundle.getBundle(path);
      Properties newProperties = new Properties();
      BufferedReader br = new BufferedReader(new FileReader(String.format("%s%s",TARGET_LANGUAGE,".txt")));
      BufferedReader bm = new BufferedReader(
          new InputStreamReader(
              new FileInputStream(pathh), "UTF-8"));

      FileWriter fr = new FileWriter("fr" + ".properties");

      for (String key: initialFile.keySet()) {
        fr.write(key + "=" + bm.readLine() + "\n");
      }
      fr.close();
  }
}
