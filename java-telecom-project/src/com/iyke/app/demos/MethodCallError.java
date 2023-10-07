package com.iyke.app.demos;

import java.util.Date;

public class MethodCallError {

  static String getMessage() {
    System.out.println("This happened at first");
    System.out.println("Secondly another thing happened");
    return "Afterwards i had to return some String value";
  }

  static boolean isDataEmpty() {
    //check if data is empty here
    return true;
  }

  static void doSomethingWithString() {
    String txt = getMessage();
    boolean isTrue = isDataEmpty();
    Date now = getLocalTime();

    if (isDataEmpty()) {
      System.out.println("This is the text i got:\n" + getMessage());
    } else {
      System.out.println("Data not empty!");
    }
  }

  static Date getLocalTime() {
    return new Date(System.currentTimeMillis());
  }
  
  public static void main(String[] args) {
    doSomethingWithString();
  }
}
