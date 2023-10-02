package src.com.iyke.app.beans;

import java.util.UUID;

public class CustomerSim {
    
    private Customer customer;
    private VirtualSim sim;
    private UUID refNo;

    //constructor
    public CustomerSim(Customer customer, VirtualSim sim) {
        this.customer = customer;
        this.sim = sim;
        this.refNo = UUID.randomUUID();
    }


    //Getters and Setters
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public VirtualSim getSim() {
        return sim;
    }

    public void setSim(VirtualSim sim) {
        this.sim = sim;
    }

    public UUID getRefNo() {
        return refNo;
    }


    @Override
    public String toString() {
        return "CustomerSim [customer=" + customer + ", sim=" + sim + ", refNo=" + refNo + "]";
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((customer == null) ? 0 : customer.hashCode());
        result = prime * result + ((sim == null) ? 0 : sim.hashCode());
        result = prime * result + ((refNo == null) ? 0 : refNo.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CustomerSim other = (CustomerSim) obj;
        if (customer == null) {
            if (other.customer != null)
                return false;
        } else if (!customer.equals(other.customer))
            return false;
        if (sim == null) {
            if (other.sim != null)
                return false;
        } else if (!sim.equals(other.sim))
            return false;
        if (refNo == null) {
            if (other.refNo != null)
                return false;
        } else if (!refNo.equals(other.refNo))
            return false;
        return true;
    }

    
 

}
