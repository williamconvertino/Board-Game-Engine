package ooga.display.resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
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
      ResourceBundle initialFile = ResourceBundle.getBundle(path);
      Properties newProperties = new Properties();
      BufferedReader br = new BufferedReader(new FileReader(String.format("%s%s",TARGET_LANGUAGE,".txt")));



      for (String key: initialFile.keySet()) {
        newProperties.put(key, br.readLine());
      }

      FileOutputStream os = new FileOutputStream(TARGET_LANGUAGE + ".properties");
      newProperties.store(os, String.format("%s%s",FILE_MESSAGE, TARGET_LANGUAGE));
  }


//  public static void main(String[] args) throws FileNotFoundException {
//    String path = Display.class.getPackageName()+".resources." + "English";
//    ResourceBundle initialFile = ResourceBundle.getBundle(path);
//    File myFile = new File( TARGET_LANGUAGE +".txt");
//    PrintWriter out = new PrintWriter(myFile);
//
//    for (String key: initialFile.keySet()) {
//      out.println(initialFile.getString(key));
//    }
//    out.close();
//  }

}
