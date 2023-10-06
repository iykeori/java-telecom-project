package com.iyke.app.beans;

import java.util.UUID;

public class InternetData {
    private static double[] prices = {250, 500, 1000, 3000, 5000};
    private static int[] dataCategories = {25, 50, 300, 500, 900};
    private UUID internetDataId;
    private int internetDataStatus = 0;
    private CustomerSim simDataBundle;

    //Constructor
    public InternetData(int internetDataStatus, CustomerSim simDataBundle) {
        this.internetDataId = UUID.randomUUID();
        this.internetDataStatus = internetDataStatus;
        this.simDataBundle = simDataBundle;
    }

    //Getters and Setters
    public static double[] getPrices() {
        return prices;
    }

    public static void setPrices(double[] prices) {
        InternetData.prices = prices;
    }

    public static int[] getDataCategories() {
        return dataCategories;
    }

    public static void setDataCategories(int[] dataCategories) {
        InternetData.dataCategories = dataCategories;
    }

    public UUID getInternetDataId() {
        return internetDataId;
    }

    public int getInternetDataStatus() {
        return internetDataStatus;
    }

    public void setInternetDataStatus(int internetDataStatus) {
        this.internetDataStatus = internetDataStatus;
    }

    public CustomerSim getSimDataBundle() {
        return simDataBundle;
    }

    public void setSimDataBundle(CustomerSim simDataBundle) {
        this.simDataBundle = simDataBundle;
    }

    @Override
    public String toString() {
        return "InternetData [internetDataId=" + internetDataId + ", internetDataStatus=" + internetDataStatus
                + ", simDataBundle=" + simDataBundle + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((internetDataId == null) ? 0 : internetDataId.hashCode());
        result = prime * result + internetDataStatus;
        result = prime * result + ((simDataBundle == null) ? 0 : simDataBundle.hashCode());
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
        InternetData other = (InternetData) obj;
        if (internetDataId == null) {
            if (other.internetDataId != null)
                return false;
        } else if (!internetDataId.equals(other.internetDataId))
            return false;
        if (internetDataStatus != other.internetDataStatus)
            return false;
        if (simDataBundle == null) {
            if (other.simDataBundle != null)
                return false;
        } else if (!simDataBundle.equals(other.simDataBundle))
            return false;
        return true;
    }
    
}
