package noobanidus.mods.grindr.util;

public class StringUtil {
  public static String capitalize(String name) {
    if (name != null && !name.isEmpty()) {
      char[] chars = name.toCharArray();
      chars[0] = Character.toUpperCase(chars[0]);
      return new String(chars);
    } else {
      return name;
    }
  }
}
