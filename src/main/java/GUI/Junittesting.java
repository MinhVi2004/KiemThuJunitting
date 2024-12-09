package GUI;
public class Junittesting {

      public boolean isNumeric(String str) {
          try {
              Double.valueOf(str);
              return true;
          } catch (NumberFormatException e) {
              return false;
          }
      }
  }