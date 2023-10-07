package com.iyke.app.util.services;

import java.util.Scanner;

import com.iyke.app.beans.CustomerSim;
import com.iyke.app.beans.InternetData;
import com.iyke.app.database.Database;

public class InternetDataService {

    public void displayInternetDataService(){
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
        //System.out.println("\n Select a Data to Purchase ");
        //double dataBal = customerSim.getSim().getDataBal();
        double airtimeBal = customerSim.getSim().getAccountBal();
        double dataValue = customerSim.getSim().getDataValue();
        double beforeDataValueBal = dataValue;
        
        while(true){
            System.out.println("\nEnter your Data Selection OR Press 0 to restart: ");
            if (scan.hasNextInt()) {
                choice = scan.nextInt();
                if (choice >= 0 && choice <= 5) {
                    if (choice == 0){
                        System.out.println("Switching to main terminal");
                        break;
                    }else if (choice == 1 && airtimeBal > InternetData.getPrices()[0]) {   
                        airtimeBal = airtimeBal - InternetData.getPrices()[0];
                        customerSim.getSim().setAcccountBal(airtimeBal);
                        dataValue = dataValue + InternetData.getDataCategories()[0];
                        customerSim.getSim().setDataValue(dataValue);
                    } else if (choice == 2 && airtimeBal > InternetData.getPrices()[1]) {
                        airtimeBal = airtimeBal - InternetData.getPrices()[1];
                        customerSim.getSim().setAcccountBal(airtimeBal);
                        dataValue = dataValue + InternetData.getDataCategories()[1];
                        customerSim.getSim().setDataValue(dataValue);
                    } else if (choice == 3 && airtimeBal > InternetData.getPrices()[2]) {
                        airtimeBal = airtimeBal - InternetData.getPrices()[2];
                        customerSim.getSim().setAcccountBal(airtimeBal);
                        dataValue = dataValue + InternetData.getDataCategories()[2];
                        customerSim.getSim().setDataValue(dataValue);
                    } else if (choice == 4 && airtimeBal > InternetData.getPrices()[3]) {
                        airtimeBal = airtimeBal - InternetData.getPrices()[3];
                        customerSim.getSim().setAcccountBal(airtimeBal);
                        dataValue = dataValue + InternetData.getDataCategories()[3];
                        customerSim.getSim().setDataValue(dataValue);
                    } else if (choice == 5 && airtimeBal > InternetData.getPrices()[4]) {
                        airtimeBal = airtimeBal - InternetData.getPrices()[4];
                        customerSim.getSim().setAcccountBal(airtimeBal);
                        dataValue = dataValue + InternetData.getDataCategories()[4];
                        customerSim.getSim().setDataValue(dataValue);
                    }else{
                        System.out.println("Insufficient Amount Please Recharge your account");
                    }
                    if(beforeDataValueBal < dataValue){
                        System.out.println("Your Data recharge was successful!");
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

    // Check Airtime
    public void checkdata(CustomerSim customerSim) {
        double dataVal, newVal;
        String sIunit;
        for (CustomerSim c : Database.getCustomerSims()) {
            if (c != null) {
                if (c.equals(customerSim)) {
                    dataVal = customerSim.getSim().getDataValue();
                    if(dataVal > 1000){
                        newVal = dataVal / 1000;
                        sIunit = "GB";
                    }else{
                        newVal = dataVal;
                        sIunit = "MB";
                    }
                System.out.println("\n Dear " + customerSim.getCustomer().getName() + " Your Data Balance is: "
                    + newVal+sIunit);
                }
            }
        }
    }
}
