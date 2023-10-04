package com.iyke.app.database;

import java.util.UUID;

import com.iyke.app.beans.Customer;
import com.iyke.app.beans.CustomerSim;
import com.iyke.app.beans.VirtualSim;
import com.iyke.app.util.Gender;

public class Database {

    private Customer[] customers = new Customer[10];
    private VirtualSim[] sim = new VirtualSim[10];
    private CustomerSim[] customerAndSim = new CustomerSim[10];
    private static int trackCustomerIndex = 2;
    private static int trackCustomerSimIndex = 2;

    public Database(){
        populateCustomers();
        populateVirtualSim();
        populateSimAndCustomers();
    }

    /**
   * Populate customer array
   */
  public void populateCustomers() {
    customers[0] = new Customer("DL001", "dana", "moxham drive", "08032704143", Gender.FEMALE, "dana@gmail.com");
    customers[1] = new Customer("DL002", "chris", "ocean street", "09033367781", Gender.MALE, "chris@gmail.com");   
  }

  public void populateVirtualSim(){
    for (int i = 2; i < sim.length; i++) {
      sim[i] = new VirtualSim(VirtualSim.generatePhoneNumber(), 0);
      //System.out.println(sim[i].toString());
    }

    // for test sake
   //sim[0].setSimActiveState(1);
   sim[0] = new VirtualSim("08032704143", 1);
   sim[1] = new VirtualSim("09033367781", 0);
  }

  
  public void populateSimAndCustomers(){
    customerAndSim[0]= new CustomerSim(customers[0], sim [0]);
    customerAndSim[1]= new CustomerSim(customers[1], sim [1]);
  }

  //Save new Customer to DB
  public Customer saveCustomer(Customer customer){
    if ( trackCustomerIndex < customers.length){
      customers[trackCustomerIndex] = customer;
      trackCustomerIndex++;
      return customer;
    }
    System.out.println("We ran out of space to save Customer!");
    return null;
  }

  //validate sim number
  public CustomerSim validateSimNumber(String simNumber){
    System.out.println("Validating sim number " + simNumber + " ...");
    for(CustomerSim cSim : getCustomerSims()){
      if (cSim != null) {
        boolean isMatch = cSim.getSim().getSimNumber().trim().equals(simNumber.trim());
        boolean isActive = cSim.getSim().getSimActiveState() == 1;
        
        if( isMatch && isActive ){
          System.out.println("Fetched number: " + cSim.getSim().getSimNumber());
          return cSim;
        } else {
          System.out.println("IsMatch: " + isMatch + " isActive: " + isActive);
        }
      }
    }
    return null;
  } 

  //fetch customer using customer code
  public Customer fetchCustomer(String customerCode) {
    if( customerCode.isEmpty() || customerCode.isBlank() || customerCode == null){
      return null;
    }
    for (Customer cst : getCustomers()) {
      if (cst != null) {
        if (cst.getCustomerCode().toUpperCase().trim().equals(customerCode.toUpperCase().trim())) {
          return cst;
        }
      }
    }
    return null;
  }

  //using customer code to virtual number
  public CustomerSim fetchVirtualSimDetails(Customer c){
    if(c == null){
      return null;
    }
    for(CustomerSim simDetails : getCustomerSims()){
      if (simDetails != null){
        if(simDetails.getCustomer().getCustomerCode().toUpperCase().trim().equals(c.getCustomerCode())){
          return simDetails;
        }
      }    
    }
    return null;
  } 


  //fetch Sim by ID
  public VirtualSim fetchSimById(String id){
    for(VirtualSim sim : getVirtualSims()){
      if(sim != null){
        if (sim.getSimId().equals(UUID.fromString(id))){
          return sim;
        }
      }
    }
    return null;
  }

  //save to CustomerSim DB
  public CustomerSim saveCustomerSim(CustomerSim cs){
    if(trackCustomerSimIndex < customerAndSim.length){
      customerAndSim[trackCustomerSimIndex] = cs;
      trackCustomerSimIndex++;
      return cs;
    }
    System.out.println("We ran out of space to save Customer!");
    return null;
  }

  public Customer[] getCustomers(){
    return customers;
  }

  //get VirtualSIm
  public VirtualSim[] getVirtualSims(){
    return sim;
  }

  //get Customers and Sim
  public CustomerSim[] getCustomerSims(){
    return customerAndSim;
  }


}