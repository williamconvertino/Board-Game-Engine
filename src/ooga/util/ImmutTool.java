package ooga.util;

import java.util.Collections;
import java.util.List;

/**
 * This class acts as an all-purpose tool for making safe an immutable data structures.
 *
 * @author William Convertino
 * @since 0.0.1
 */
public class ImmutTool {

  /**
   * Returns an immutable version of the specified list.
   *
   * @param myList the list to become immutable.
   * @param <T>    the class of the list.
   * @return an immutable version of the specified list.
   */
  public static <T> List<T> getImmutableList(List<T> myList) {
    return Collections.unmodifiableList(myList);
  }
}
