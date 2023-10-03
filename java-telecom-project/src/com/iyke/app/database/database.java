package com.iyke.app.database;

import com.iyke.app.beans.Customer;
import com.iyke.app.beans.CustomerSim;
import com.iyke.app.beans.VirtualSim;
import com.iyke.app.util.Gender;

public class Database{

    private Customer[] customers = new Customer[5];
    private VirtualSim[] sim = new VirtualSim[5];
    private CustomerSim[] customerAndSim = new CustomerSim[5];

    public Database(){
        populateCustomers();
        populateVirtualSim();
    }

    /**
   * Populate customer array
   */
  public void populateCustomers() {
    customers[0] = new Customer("DL001", "dana", "moxham drive", "08033362261", Gender.FEMALE, "dana@gmail.com");
    customers[1] = new Customer("DL002", "chris", "ocean street", "09033367781", Gender.MALE, "chris@gmail.com");   
  }

  public void populateVirtualSim(){
    sim[0] = new VirtualSim("08033362261", 0);
    sim[0] = new VirtualSim("09033367781", 0);
  }
}