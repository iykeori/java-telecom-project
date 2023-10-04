package com.iyke.app.demos;

import java.util.Scanner;

public class DebugDemo {
  
  Scanner scan;
  String phone;

  public DebugDemo() {

    scan  = new Scanner(System.in);

    System.out.println("Enter phone: ");

    if (scan.hasNext()) {
      phone = scan.nextLine();//throws an error
      System.out.println("phone: " + phone);
    } else {
      //here is alright at least for now!
      System.out.println("Invalid phone format!");
    }

  }

  public static void main(String[] args) {
    DebugDemo demo = new DebugDemo();
  }
}
