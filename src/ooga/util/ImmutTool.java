package ooga.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class acts
 */
public class ImmutTool {

  public static <T> List<T> getImmutableList(List<T> myList) {
    return Collections.unmodifiableList(myList);
  }

}
