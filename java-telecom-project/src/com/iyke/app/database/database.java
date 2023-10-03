package com.iyke.app.database;

import com.iyke.app.beans.Customer;
import com.iyke.app.beans.CustomerSim;
import com.iyke.app.beans.VirtualSim;
import com.iyke.app.util.Gender;

public class Database{

    private Customer[] customers = new Customer[10];
    private VirtualSim[] sim = new VirtualSim[10];
    private CustomerSim[] customerAndSim = new CustomerSim[10];

    public Database(){
        populateCustomers();
        populateVirtualSim();
        populateSimAndCustomers();
    }

    /**
   * Populate customer array
   */
  public void populateCustomers() {
    customers[0] = new Customer("DL001", "dana", "moxham drive", "08033362261", Gender.FEMALE, "dana@gmail.com");
    customers[1] = new Customer("DL002", "chris", "ocean street", "09033367781", Gender.MALE, "chris@gmail.com");   
  }

  public void populateVirtualSim(){
    for (int i = 0; i < sim.length; i++) {
      sim[i] = new VirtualSim(VirtualSim.generatePhoneNumber(), 0);
    }

    // for test sake
    sim[0].setSimActiveState(1);
  }

  
  public void populateSimAndCustomers(){
    customerAndSim[0]= new CustomerSim(getCustomers()[0], getVirtualSims()[0]);
  }


  //validate sim number
  public CustomerSim validateSimNumber(String simNumber){
    for(CustomerSim cSim : getCustomerSims()){
      boolean isMatch = cSim.getSim().getSimNumber().trim().equals(simNumber.trim());
      boolean isActive = cSim.getSim().getSimActiveState() == 1;

      if( isMatch && isActive ){
        return cSim;
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