package com.iyke.app;

import java.util.Scanner;

import com.iyke.app.beans.Customer;
import com.iyke.app.beans.CustomerSim;
import com.iyke.app.database.Database;
import com.iyke.app.util.Gender;
import com.iyke.app.util.Validations;

public class App {
    private Database db;

    // Constructor
    public App() {
        db = new Database();// initialize the database object
    }

    public void welcome(){
        Scanner scan = new Scanner(System.in);
        String simNumber;

        while(true){
            try{
                //prompt user for virtual Number
                System.out.println("Enter your Virtual Sim Number: ");

                if (scan.hasNext()){
                    simNumber = scan.nextLine();
                    // System.out.println("Entered number: " + simNumber);

                    //Run first Validation on sim number
                    boolean isValid = Validations.validateSimNumberEntry(simNumber);
                    //boolean isValid = true;
                    if(isValid){
                        CustomerSim customerAndSim = db.validateSimNumber(simNumber);
                        if (customerAndSim != null){
                            System.out.println("Welcome: "+ customerAndSim.getCustomer().getName());
                        }else{
                            System.out.println("\n Customer not Found!");
                            String customerCode = scan.nextLine();
                            Customer customer = db.fetchCustomer(customerCode);
                            CustomerSim simDetails = db.fetchVirtualSimDetails(customer);
                            if(customer != null){
                                System.out.println("Welcome "+ customer.getName());
                                if (simDetails != null){
                                    System.out.println("Your Valid Virtual Number is  "+ simDetails.getSim().getSimNumber().toString());
                                    //prompt customer to re-enter the correct number
                                }else{
                                    // if customer does not have a virtual number, let him purchase VN
                                }
                            }else{
                                // if customer does not exit, register customer
                            }
                        }
                    } else {
                      System.out.println("Number does not exist!");
                      continue;
                    }

                } else{
                    System.out.println("Invalid input. Please enter a number");
                    scan.nextLine();// consume the input
                    continue; 
                }

            } catch (Exception e) {
                System.out.println("Exception: " + e.getMessage());
                continue;
            }
        }
    
    }

    private Customer registerCustomer() {
        Scanner scan = new Scanner(System.in);
        String customerCode, name, address, phone, gender, email;
        Gender genderEnum = null;
    
        while (true) {
    
          System.out.println("\nCustomer registration terminal.\nPlease provide your customer registration information");
    
          System.out.print("Enter name: ");
          name = scan.nextLine();
          if (!Validations.validateField("name", name)) {
            System.out.println("Invalid input. Please try again.");
            continue;
          }
    
          System.out.print("Enter address: ");
          address = scan.nextLine();
          if (!Validations.validateField("address", address)) {
            System.out.println("Invalid input. Please try again.");
            continue;
          }
    
          System.out.print("Enter phone: ");
          phone = scan.nextLine();
          if (!Validations.validateField("phone", phone)) {
            System.out.println("Invalid input. Please try again.");
            continue;
          }
    
          System.out.print("Enter gender: ");
          gender = scan.nextLine();
          if (!Validations.validateField("gender", gender)) {
            System.out.println("Invalid input. Please try again.");
            continue;
          }
          if(gender.trim().equalsIgnoreCase("m")){
            genderEnum = Gender.MALE;
          }else if(gender.trim().equalsIgnoreCase("f")){
            genderEnum = Gender.FEMALE;
          }
    
          System.out.print("Enter email: ");
          email = scan.nextLine();
          if (!Validations.validateField("email", email)) {
            System.out.println("Invalid input. Please try again.");
            continue;
          }
    
          // Generate the customer code in the following format
          customerCode = Customer.generateCustomerCode();
    
          if (!name.isEmpty() && !address.isEmpty() && !phone.isEmpty() && !gender.isEmpty() && !email.isEmpty()) {
            return new Customer(customerCode, name, address, phone, genderEnum, email);
          }
        }
      }

    public static void main(String[] args) {
        App app = new App();
        app.welcome();
    }
    
    
}
 