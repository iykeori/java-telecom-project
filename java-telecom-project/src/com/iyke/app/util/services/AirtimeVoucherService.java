package com.iyke.app.util.services;

import java.util.Scanner;

import com.iyke.app.beans.AirtimeVoucher;
import com.iyke.app.beans.CustomerSim;
import com.iyke.app.database.Database;

public class AirtimeVoucherService {

  public void displayAirtimeVouchersCat() {
    System.out.println("\nSN\t\t VALUE \t\t\t\t PRICE");
    System.out.println("1\t\t\t" + AirtimeVoucher.getPriceCategories()[0] + "\t\t\t" + AirtimeVoucher.getPrices()[0]);
    System.out.println("2\t\t\t" + AirtimeVoucher.getPriceCategories()[1] + "\t\t\t" + AirtimeVoucher.getPrices()[1]);
    System.out.println("3\t\t\t" + AirtimeVoucher.getPriceCategories()[2] + "\t\t\t" + AirtimeVoucher.getPrices()[2]);
    System.out.println("4\t\t\t" + AirtimeVoucher.getPriceCategories()[3] + "\t\t\t" + AirtimeVoucher.getPrices()[3]);
    System.out.println("5\t\t\t" + AirtimeVoucher.getPriceCategories()[4] + "\t\t\t" + AirtimeVoucher.getPrices()[4]);
  }
  // Buy Airtime method
  public void rechargeSim(CustomerSim customerSim) {
    Scanner scan = new Scanner(System.in);
    int choice;
    System.out.println("\n Select an Airtime to Purchase OR Press 0 to restart");
    double accountBal = customerSim.getSim().getAccountBal();
    double beforeAccountBal = accountBal;
    System.out.println("\nEnter your Airtime Selection: ");
    if (scan.hasNextInt()) {
      choice = scan.nextInt();
      if (choice >= 1 && choice <= 5) {
        if (choice == 1) {
          accountBal = accountBal + AirtimeVoucher.getPrices()[0];
          customerSim.getSim().setAcccountBal(accountBal);
        } else if (choice == 2) {
          accountBal = accountBal + AirtimeVoucher.getPrices()[1];
          customerSim.getSim().setAcccountBal(accountBal);
        } else if (choice == 3) {
          accountBal = accountBal + AirtimeVoucher.getPrices()[2];
          customerSim.getSim().setAcccountBal(accountBal);
        } else if (choice == 4) {
          accountBal = accountBal + AirtimeVoucher.getPrices()[3];
          customerSim.getSim().setAcccountBal(accountBal);
        } else if (choice == 5) {
          accountBal = accountBal + AirtimeVoucher.getPrices()[4];
          customerSim.getSim().setAcccountBal(accountBal);
        }
        
      } else {
        System.out.println("Invalid Selection");
      }
    } else {
      System.out.println("Invalid Selection");
      scan.nextLine();// accept the input
    }
    if(beforeAccountBal < accountBal){
      System.out.println("You recharge was successful!");
    }

  }

  // Check Airtime
  public void checkAirtime(CustomerSim customerSim) {
    for (CustomerSim c : Database.getCustomerSims()) {
      if (c != null) {
        if (c.equals(customerSim)) {
          System.out.println("\n Dear " + customerSim.getCustomer().getName() + " your airtime Balance is: "
              + customerSim.getSim().getAccountBal());
        }
      }
    }
  }
}
