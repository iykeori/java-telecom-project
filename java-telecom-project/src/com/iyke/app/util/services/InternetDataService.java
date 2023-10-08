package com.iyke.app.util.services;

import java.util.Scanner;

import com.iyke.app.beans.CustomerSim;
import com.iyke.app.beans.InternetData;
import com.iyke.app.database.Database;

public class InternetDataService {

  public void displayInternetDataService() {
    System.out.println("\nSN\t\t VALUE \t\t\t\t PRICE");
    System.out.println("1\t\t\t" + InternetData.getDataCategories()[0] + "\t\t\t" + InternetData.getPrices()[0]);
    System.out.println("2\t\t\t" + InternetData.getDataCategories()[1] + "\t\t\t" + InternetData.getPrices()[1]);
    System.out.println("3\t\t\t" + InternetData.getDataCategories()[2] + "\t\t\t" + InternetData.getPrices()[2]);
    System.out.println("4\t\t\t" + InternetData.getDataCategories()[3] + "\t\t\t" + InternetData.getPrices()[3]);
    System.out.println("5\t\t\t" + InternetData.getDataCategories()[4] + "\t\t\t" + InternetData.getPrices()[4]);
  }

  // Buy Data method
  public void buyData(CustomerSim customerSim) {
    Scanner scan = new Scanner(System.in);
    int choice;
    // System.out.println("\n Select a Data to Purchase ");
    // double dataBal = customerSim.getSim().getDataBal();
    double airtimeBal = customerSim.getSim().getAccountBal();
    double dataValue = customerSim.getSim().getDataValue();
    double beforeDataValueBal = dataValue;
    boolean isSimDataBought = false;

    while (true) {
      System.out.println("\nEnter your Data Selection OR Press 0 to restart: ");
      if (scan.hasNextInt()) {
        choice = scan.nextInt();
        if (choice >= 0 && choice <= 5) {
          if (choice == 0) {
            System.out.println("Switching to main terminal");
            break;
          } else if (choice == 1) {

            isSimDataBought = calculateAirtimeAndData(airtimeBal, dataValue, --choice, --choice, customerSim);

          } else if (choice == 2) {

            isSimDataBought = calculateAirtimeAndData(airtimeBal, dataValue, --choice, --choice, customerSim);

          } else if (choice == 3) {

            isSimDataBought = calculateAirtimeAndData(airtimeBal, dataValue, --choice, --choice, customerSim);

          } else if (choice == 4) {

            isSimDataBought = calculateAirtimeAndData(airtimeBal, dataValue, --choice, --choice, customerSim);

          } else if (choice == 5) {

            isSimDataBought = calculateAirtimeAndData(airtimeBal, dataValue, --choice, --choice, customerSim);

          }

          // success message
          if (isSimDataBought && beforeDataValueBal < dataValue) {
            System.out.println("Your Data recharge was successful!");
          } else {
            System.out.println("Your Data recharge was not  successful!");
            continue;
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

  // Calculate Airtime and Data
  public boolean calculateAirtimeAndData(double airtimeBal, double dataValue, int priceIndex, int catIndex, CustomerSim customerSim) {

    double price = InternetData.getPrices()[priceIndex];
    int dataCategory = InternetData.getDataCategories()[catIndex];

    if (airtimeBal > price) {
      // subtracting the data price from airtime
      airtimeBal = airtimeBal - price;
      // set new airtime balance
      customerSim.getSim().setAcccountBal(airtimeBal);
      // add to add value
      dataValue = dataValue + dataCategory;
      // set new data value
      customerSim.getSim().setDataValue(dataValue);

      return true;
    }

    return false;
  }

  // Check Airtime
  public void checkdata(CustomerSim customerSim) {
    double dataVal, newVal;
    String sIunit;
    for (CustomerSim c : Database.getCustomerSims()) {
      if (c != null) {
        if (c.equals(customerSim)) {
          dataVal = customerSim.getSim().getDataValue();
          // converting mb to gb
          if (dataVal > 1000) {
            newVal = dataVal / 1000;
            sIunit = "GB";
          } else {
            newVal = dataVal;
            sIunit = "MB";
          }
          // show data balance
          System.out.println("\n Dear " + customerSim.getCustomer().getName() + " Your Data Balance is: "
              + newVal + sIunit);
        }
      }
    }
  }
}
