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
    //System.out.println("\n Select an Airtime to Purchase OR Press 0 to restart");
    double accountBal = customerSim.getSim().getAccountBal();
    double beforeAccountBal = accountBal;  
    Boolean isSimAirtimeBought = false;

    while(true){
      System.out.println("\nEnter your Airtime Selection OR Press 0 to restart: ");
      if (scan.hasNextInt()) {
        choice = scan.nextInt();
        
        if (choice >= 0 && choice <= 5) {
          if (choice == 0){
            System.out.println("Switching to main terminal");
            break;
          }else if (choice == 1) {

            isSimAirtimeBought = calculateAirtime(accountBal, AirtimeVoucher.getPrices()[0], customerSim);

          } else if (choice == 2) {

            isSimAirtimeBought = calculateAirtime(accountBal, AirtimeVoucher.getPrices()[1], customerSim);

          } else if (choice == 3) {

            isSimAirtimeBought = calculateAirtime(accountBal, AirtimeVoucher.getPrices()[2], customerSim);

          } else if (choice == 4) {

            isSimAirtimeBought = calculateAirtime(accountBal, AirtimeVoucher.getPrices()[3], customerSim);

          } else if (choice == 5) {

            isSimAirtimeBought = calculateAirtime(accountBal, AirtimeVoucher.getPrices()[4], customerSim);

          } 

          //Success message
          if(isSimAirtimeBought && beforeAccountBal < accountBal){
            System.out.println("Your recharge was successful!");
          } 
                  
        } else {
          System.out.println("***Invalid Selection! Enter a Valid Number***");
          continue;
        }
      } else {
        System.out.println("Invalid Selection");
        scan.nextLine();// accept the input
      }
      
    }
  }

  //Calculate Airtime
  public boolean calculateAirtime(double accountBal, double price, CustomerSim customerSim){
    //add new airtime to last airtime balance
    accountBal = accountBal + price;
    //set new account balance
    customerSim.getSim().setAcccountBal(accountBal);
    return true;
  }

  // Check Airtime
  public void checkAirtime(CustomerSim customerSim) {
    for (CustomerSim c : Database.getCustomerSims()) {
      if (c != null) {
        if (c.equals(customerSim)) {
          System.out.println("\n Dear " + customerSim.getCustomer().getName() + " Your airtime Balance is: "
              + customerSim.getSim().getAccountBal());
        }
      }
    }
  }
}
