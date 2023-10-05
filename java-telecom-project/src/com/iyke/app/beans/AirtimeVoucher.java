package com.iyke.app.beans;

import java.util.Scanner;
import java.util.UUID;

import com.iyke.app.database.Database;

public class AirtimeVoucher{
    private static double[] price = {110, 210, 420, 520, 1550};
    private static int[] priceCategory = {100, 200, 400, 500, 1500};
    private double airtimePrice;
    private int airtimeCategory;
    private UUID airtimeVoucherId;
    private int airtimeVoucherStatus = 0;
    private CustomerSim simAirtime;


    public void displayAirtimeVouchersCat(){
        System.out.println("\nSN\t\t VALUE \t\t\t\t PRICE");
        System.out.println("1\t\t\t"+getPriceCategory()[0]+"\t\t\t" + getPrice()[0]);
        System.out.println("2\t\t\t"+getPriceCategory()[1]+"\t\t\t" + getPrice()[1]);
        System.out.println("3\t\t\t"+getPriceCategory()[2]+"\t\t\t" + getPrice()[2]);
        System.out.println("4\t\t\t"+getPriceCategory()[3]+"\t\t\t" + getPrice()[3]);
        System.out.println("5\t\t\t"+getPriceCategory()[4]+"\t\t\t"+ getPrice()[4]);
    }

    //Buy Airtime method
    public void rechargeSim(CustomerSim customerSim){
        Scanner scan = new Scanner(System.in);
        int choice;
        System.out.println("\n Select an Airtime to Purchase OR Press 0 to restart");
        double accountBal = customerSim.getSim().getAccountBal();
        System.out.println("\nEnter your Airtim Selection: ");
        if (scan.hasNextInt()){
            choice = scan.nextInt();
            if(choice >=1 && choice <=5){
                if(choice == getPriceCategory()[0]){
                    accountBal = accountBal + getPrice()[0];
                    customerSim.getSim().setAcccountBal(accountBal);
                } else if(choice == getPriceCategory()[1]){
                    accountBal = accountBal + getPrice()[1];
                    customerSim.getSim().setAcccountBal(accountBal);
                }else if(choice == getPriceCategory()[2]){
                    accountBal = accountBal + getPrice()[2];
                    customerSim.getSim().setAcccountBal(accountBal);
                }else if(choice == getPriceCategory()[3]){
                    accountBal = accountBal + getPrice()[3];
                    customerSim.getSim().setAcccountBal(accountBal);
                }else if(choice == getPriceCategory()[4]){
                    accountBal = accountBal + getPrice()[4];
                    customerSim.getSim().setAcccountBal(accountBal);
                }
                System.out.println("You recharge was successful!");
            }else{
                System.out.println("Invalid Selection");
            }
        }else{
            System.out.println("Invalid Selection");
        }
        
    }
    //Check Airtime
    public void checkAirtime(CustomerSim customerSim){
         for(CustomerSim c : Database.getCustomerSims()){
             if(c != null){
                 if(c.equals(customerSim)){
                    System.out.println("\n Dear "+customerSim.getCustomer().getName()+" your airtime Balance is: "+ customerSim.getSim().getAccountBal());
                 }
             }
         }        
    }
    
    //Default Constructor
    public AirtimeVoucher(){

    }
    //Constructor
    public AirtimeVoucher(double airtimePrice, int airtimeCategory, int airtimeVoucherStatus, CustomerSim simAirtime) {
        this.airtimeVoucherId = UUID.randomUUID();
        this.airtimeVoucherStatus = airtimeVoucherStatus;
        this.simAirtime = simAirtime;
        this.airtimePrice = airtimePrice;
        this.airtimeCategory = airtimeCategory;
    }
    //Getter and Setters
    public static double[] getPrice() {
        return price;
    }

    public static void setPrice(double[] price) {
        AirtimeVoucher.price = price;
    }

    public UUID getAirtimeVoucherId() {
        return airtimeVoucherId;
    }

  
    public int getAirtimeVoucherStatus() {
        return airtimeVoucherStatus;
    }

    public void setAirtimeVoucherStatus(int airtimeVoucherStatus) {
        this.airtimeVoucherStatus = airtimeVoucherStatus;
    }

    public CustomerSim getSimAirtime() {
        return simAirtime;
    }

    public void setSimAirtime(CustomerSim simAirtime) {
        this.simAirtime = simAirtime;
    }

    public static int[] getPriceCategory() {
        return priceCategory;
    }

    public static void setPriceCategory(int[] priceCategory) {
        AirtimeVoucher.priceCategory = priceCategory;
    }

    @Override
    public String toString() {
        return "AirtimeVoucher [airtimeVoucherId=" + airtimeVoucherId + ", airtimeVoucherStatus=" + airtimeVoucherStatus
                + ", simAirtime=" + simAirtime + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((airtimeVoucherId == null) ? 0 : airtimeVoucherId.hashCode());
        result = prime * result + airtimeVoucherStatus;
        result = prime * result + ((simAirtime == null) ? 0 : simAirtime.hashCode());
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
        AirtimeVoucher other = (AirtimeVoucher) obj;
        if (airtimeVoucherId == null) {
            if (other.airtimeVoucherId != null)
                return false;
        } else if (!airtimeVoucherId.equals(other.airtimeVoucherId))
            return false;
        if (airtimeVoucherStatus != other.airtimeVoucherStatus)
            return false;
        if (simAirtime == null) {
            if (other.simAirtime != null)
                return false;
        } else if (!simAirtime.equals(other.simAirtime))
            return false;
        return true;
    }
 
    
}