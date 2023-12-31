package com.iyke.app;

import java.util.Scanner;

import com.iyke.app.beans.Customer;
import com.iyke.app.beans.CustomerSim;
import com.iyke.app.beans.VirtualSim;
import com.iyke.app.database.Database;
import com.iyke.app.util.Gender;
import com.iyke.app.util.Validations;
import com.iyke.app.util.services.AirtimeVoucherService;
import com.iyke.app.util.services.InternetDataService;

public class App {
    private Database db;
    private AirtimeVoucherService avs;
    private InternetDataService ids;
    private int simStoreUserIndex = 0;
    private String[][] simCards;

    // Constructor
    public App() {
        db = new Database();// initialize the database object
        simCards = new String[db.getVirtualSims().length][2];
        avs = new AirtimeVoucherService();
        ids = new InternetDataService();
    }

    public void welcome() {
        Scanner scan = new Scanner(System.in);
        String simNumber;

        while (true) {
            try {
                // prompt user for virtual Number
                System.out.println("\nEnter your Virtual Sim Number: ");

                if (scan.hasNext()) {
                    simNumber = scan.nextLine();
                    // Run first Validation on sim number
                    boolean isValid = Validations.validateSimNumberEntry(simNumber);
                    if (isValid) {
                        CustomerSim customerAndSim = Validations.validateSimNumber(simNumber);

                        if (customerAndSim != null) {
                            System.out.println("Welcome: " + customerAndSim.getCustomer().getName());
                            // show services
                            displayServices();
                            processServices(customerAndSim.getCustomer());
                        } else{
                            System.out.println("\n Customer not Found!");
                            System.out.println("Enter your Customer Code: ");
                            String customerCode = scan.nextLine();
                            Customer customer = db.fetchCustomer(customerCode);
                            if (customer != null) { 
                                CustomerSim simDetails = db.fetchVirtualSimDetails(customer);
                                System.out.println("Welcome " + customer.getName());

                                if (simDetails != null) {
                                    System.out.println("Your Valid Virtual Number is  " + simDetails.getSim().getSimNumber().toString());
                                    // prompt customer to re-enter the correct number
                                    //System.out.println("\nEnter your Virtual Sim Number: ");
                                    continue;
                                } else {
                                    // if customer does not have a virtual number, let him purchase VN
                                    VirtualSim sim = getASim(customer);
                                    System.out.println("\nYour registration was successful");
                                    if(sim != null){
                                        displayServices();
                                        processServices(customer);
                                    }else{
                                        System.out.println("you did not succed in getting a sim");
                                    }
                                }
                            } else {
                                // if customer does not exit, register customer and get customer a sim
                                customer = registerCustomer(); 
                                if(customer != null){
                                    customer = db.saveCustomer(customer);
                                    if(customer != null){
                                        VirtualSim sim = getASim(customer);
                                        System.out.println("\nYour registration was successful");
                                        if(sim != null){
                                            displayServices();
                                            processServices(customer);
                                        }else{
                                            System.out.println("you did not succed in getting a sim");
                                        }
                                    }else{
                                        System.out.println(" Customer did not save");
                                    }
                                }else{
                                    System.out.println("Customer Registration was Successful");
                                }
                            }
                        }
                    } else {
                        System.out.println("Number does not exist!");
                        continue;
                    }
                } else {
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

    private void processServices(Customer customer) {
        String uCode = enterUssdCode();
        CustomerSim customerSim = db.fetchCustomerSim(customer);
        if (uCode != null) {
            if (customerSim  != null) {
                processUssdCode(uCode, customerSim);
            } else {
                System.out.println("Customer not registered");
            }
        } else {
            System.out.println("Your request cannot be processed at this time. Try again later");
        }
    }

    //enter ussd
    private String enterUssdCode() {
        Scanner scan = new Scanner(System.in);
        String ussdCode = "";   
        while(true){
            System.out.println("\n Enter the USSD code for the service you want");
            try {                
                if (scan.hasNext()) {
                    ussdCode = scan.next();
                    //System.out.println("ussd code assigned");
                    return ussdCode.trim();
                } else {
                    System.out.println("Invalid USSD Code");
                    continue;
                }
            } catch (Exception e) {
                System.out.println("Exception: " + e.getMessage());
            }
            return null;
        }       
    }

    private void processUssdCode(String code, CustomerSim customerSim) {
        String buyAirtime = "*310#";
        String checkAirtimeBal = "*310*1#";
        String buyData = "*320#";
        String checkDataBal = "*320*1#";

        if (code.equals(buyAirtime)) {
            //System.out.println("here-option-1");
            avs.displayAirtimeVouchersCat();
            avs.rechargeSim(customerSim);

        } else if (code.equals(checkAirtimeBal)) {
            avs.checkAirtime(customerSim);

        } else if (code.equals(buyData)) {
            ids.displayInternetDataService();
            ids.buyData(customerSim);

        } else if (code.equals(checkDataBal)) {
            ids.checkdata(customerSim);
        }
    }

    private void displayServices() {
        System.out.println("");
        System.out.println("SERVICE \t\t\t\t CODE");
        // System.out.println("1\t\t Acquire a virtual SIM \t\t\t\t *700#");
        System.out.println("Buy airtime voucher \t\t\t\t *310#");
        System.out.println("Check Airtime balance \t\t\t\t *310*1#");
        System.out.println("Buy data bundle \t\t\t\t *320#");
        System.out.println("Check data balance \t\t\t\t *320*1#");
    }

    private int displayInactiveSimNumbers() {
        System.out.println("\nChoose from the Available Sim");
        System.out.println("\nPlease select your preferred number OR Press 0 to go back to Main Options:");

        int count = 0;
        VirtualSim[] inactiveSim = db.getVirtualSims();
        simCards = new String[inactiveSim.length][2];
        simStoreUserIndex = 0;

        for (int i = 0; i < inactiveSim.length; i++) {
            if (inactiveSim[i] != null && inactiveSim[i].getSimActiveState() == 0) {
                storeUserSimChoice(i, inactiveSim[i]);
                System.out.println(++count + " - " + inactiveSim[i].getSimNumber());
            }
        }
        return count;
    }

    private void storeUserSimChoice(int index, VirtualSim sim) {
        simCards[simStoreUserIndex][0] = Integer.toString(index);
        simCards[simStoreUserIndex][1] = sim.getSimId().toString();
        simStoreUserIndex++;
    }

    private VirtualSim getASim(Customer customer) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            int simCount = displayInactiveSimNumbers();
            if (simCount == 0) {
                System.out.println("\n***No Virtual Sim is Available. Press 0 to go to Main Options***\n");
                break;
            }

            System.out.print("\nEnter option: ");
            int select;

            if (scan.hasNextInt()) {
                select = scan.nextInt();

                if (select == 0) {
                    welcome();
                    break;
                } else if (select >= 1 && select <= simCount) { 
                    // Get Sim ready for Booking
                    String[] detail = simCards[--select];
                    VirtualSim sim = db.fetchSimById(detail[1]); 
                    
                    if(sim != null){
                       // save to CustomerSim DB
                        CustomerSim cs = db.saveCustomerSim(new CustomerSim(customer, sim)); 
                        if (cs != null) {
                            // change virtual sim active status to 1
                            sim.setSimActiveState(1);
                            String customerPhoneno = sim.getSimNumber();
                            customer.setPhone(customerPhoneno);
                            System.out.println("\nCongratulations! Your Virtual Number is " + cs.getSim().getSimNumber());
                        } else {
                            System.out.println("\nSorry! You can't get this number now");
                            continue;
                        }

                        return sim;
                    }else{
                        System.out.println("Failed to Fetch sim details");
                    }  
                }
            } else {
                System.out.println("Invalid input. Please select a number!");
                scan.nextLine();// consume the input
                continue;
            }
        }
        return null;
    }

    private Customer registerCustomer() {
        Scanner scan = new Scanner(System.in);
        String customerCode, name, address, gender, email;
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

            System.out.print("Enter gender: ");
            gender = scan.nextLine();
            if (!Validations.validateField("gender", gender)) {
                System.out.println("Invalid input. Please try again.");
                continue;
            }
            if (gender.trim().equalsIgnoreCase("m")) {
                genderEnum = Gender.MALE;
            } else if (gender.trim().equalsIgnoreCase("f")) {
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

            if (!name.isEmpty() && !address.isEmpty() && !gender.isEmpty() && !email.isEmpty()) {
                return new Customer(customerCode, name, address, genderEnum, email);
            }
        }
    }

  public static void main(String[] args) {
    App app = new App();
    app.welcome();
  }
}
