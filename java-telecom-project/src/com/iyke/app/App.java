package com.iyke.app;

import java.util.Scanner;

import com.iyke.app.beans.Customer;
import com.iyke.app.beans.CustomerSim;
import com.iyke.app.database.Database;
import com.iyke.app.util.Validations;

public class App {
    private Database db;

    // Constructor
    public App() {
        db = new Database();// initialize the database object
    }

    public void welcome(){
        Scanner scan = new Scanner(System.in);
        int simNumber;

        while(true){
            try{
                //prompt user for virtual Number
                System.out.println("Enter your Virtual Sim Number: ");

                if (scan.hasNextInt()){
                    simNumber = scan.nextInt();

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

    public static void main(String[] args) {
        App app = new App();
        app.welcome();
    }
    
    
}
 