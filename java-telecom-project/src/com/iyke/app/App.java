package com.iyke.app;

import java.util.Scanner;

import com.iyke.app.beans.Customer;
import com.iyke.app.beans.CustomerSim;
import com.iyke.app.beans.VirtualSim;
import com.iyke.app.database.Database;
import com.iyke.app.util.Gender;
import com.iyke.app.util.Validations;

public class App {
    private Database db;
    private int simStoreUserIndex = 0;
    private String[][] simCards;

    // Constructor
    public App() {
        db = new Database();// initialize the database object
        simCards = new String[db.getVirtualSims().length][2];
        
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
                            //show services
                            displayServices();
                        }else if (customerAndSim == null){
                            System.out.println("\n Customer not Found!");
                            String customerCode = scan.nextLine();
                            Customer customer = db.fetchCustomer(customerCode);
                            CustomerSim simDetails = db.fetchVirtualSimDetails(customer);
                            if(customer != null){
                                System.out.println("Welcome "+ customer.getName());
                                
                                if (simDetails != null){
                                    System.out.println("Your Valid Virtual Number is  "+ simDetails.getSim().getSimNumber().toString());
                                    //prompt customer to re-enter the correct number
                                    System.out.println("\nEnter your Virtual Sim Number: ");
                                    continue; 
                                }else{
                                    // if customer does not have a virtual number, let him purchase VN
                                    getASim(customer);

                                    //show services
                                    displayServices();
                                }
                            }else{
                                // if customer does not exit, register customer
                                customer = registerCustomer();
                                db.saveCustomer(customer);
                                System.out.println("\nYour registration was successful ");
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

    private void displayServices(){
        System.out.println("SN\t\t SERVICE \t\t\t\t CODE");
        System.out.println("1\t\t Acquire a virtual SIM \t\t\t\t *700#");
        System.out.println("2\t\t Buy airtime voucher \t\t\t\t *310#");
        System.out.println("3\t\t Check data balance \t\t\t\t *310*1#");
        System.out.println("4\t\t Buy data bundle \t\t\t\t *320#");
        System.out.println("5\t\t Check data balance \t\t\t\t *320*1#");
    }

    private int displayInactiveSimNumbers(){
        System.out.println("\nChoose from the Available Sim");
        System.out.println("\nPlease select your preferred number OR Press 0 to go back to Main Options:");

        int count = 0;
        VirtualSim[] inactiveSim = db.getVirtualSims();
        simCards = new String[inactiveSim.length][2];
        
        for(int i = 0; i < inactiveSim.length; i++){
            if(inactiveSim[i] != null && inactiveSim[i].getSimActiveState() == 0){
                storeUserSimChoice(i, inactiveSim[i]);
                System.out.println(++count + " - " + inactiveSim[i].getSimNumber());
            }
        }
        return count;
    }

    private void storeUserSimChoice(int index, VirtualSim sim){
        simCards[simStoreUserIndex][0] = Integer.toString(index);
        simCards[simStoreUserIndex][1] = sim.getSimId().toString();
        simStoreUserIndex++;
    }

    private void getASim(Customer customer){
        Scanner scan = new Scanner(System.in);
        while(true){
            int simCount = displayInactiveSimNumbers();
            if(simCount == 0){
                System.out.println("\n***No Virtual Sim is Available. Press 0 to go to Main Options***\n");
            }
            System.out.print("Enter option: ");
            int select;

            if (scan.hasNextInt()){
                select = scan.nextInt();

                if (select == 0){
                    welcome();
                    break;
                }else{
                    //Get Sim ready for Booking
                    String[] detail = simCards[--select];
                    VirtualSim sim = db.fetchSimById(detail[1]);

                    //create an instance of a customerSim to save
                    //CustomerSim cs = new CustomerSim(customer, sim);
                    //save to CustomerSim DB
                   CustomerSim cs = db.saveCustomerSim(new CustomerSim(customer, sim));

                   if (cs != null){
                    //change virtual sim active status to 1
                    cs.getSim().setSimActiveState(1);
                    System.out.println("Congratulations! Your Virtual Number is "+ cs.getSim().getSimNumber());
                   }else {
                    System.out.println("Sorry! You can't get this number now");
                    continue;
                  }
                }
            }else {
                System.out.println("Invalid input. Please select a number!");
                scan.nextLine();//consume the input
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
 