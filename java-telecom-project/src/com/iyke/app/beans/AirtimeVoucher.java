package com.iyke.app.beans;

import java.util.UUID;

public class AirtimeVoucher {
    private static double[] prices = {110, 210, 420, 520, 1550};
    private static int[] priceCategories = {100, 200, 400, 500, 1500};
    private double airtimePrice;
    private int airtimeCategory;
    private UUID airtimeVoucherId;
    private int airtimeVoucherStatus = 0;
    private CustomerSim simAirtime;
    
    //Default Constructor
    public AirtimeVoucher(){
      this(0, 0, 0, null);
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
    public static double[] getPrices() {
        return prices;
    }

    public static void setPrices(double[] prices) {
        AirtimeVoucher.prices = prices;
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

    public static int[] getPriceCategories() {
        return priceCategories;
    }

    public static void setPriceCategories(int[] priceCategories) {
        AirtimeVoucher.priceCategories = priceCategories;
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