package com.iyke.app.beans;

import java.util.Random;
import java.util.UUID;

public class VirtualSim{
    private String simNumber;
    private UUID simId;
    private int simActiveState;
    private static int code = 0;

    //constructor
    public VirtualSim(String simNumber, int simActiveState) {
        this.simNumber = simNumber;
        this.simActiveState = simActiveState;
        this.simId = UUID.randomUUID();
    }
    
    //getters and setters
    public String getSimNumber() {
        return simNumber;
    }

    public void setSimNumber(String simNumber) {
        this.simNumber = simNumber;
    }

    public UUID getSimId() {
        return simId;
    }
    
    public int getSimActiveState() {
        return simActiveState;
    }

    public void setSimActiveState(int simActiveState) {
        this.simActiveState = simActiveState;
    }

    //Generate Phone Number
    public static String generatePhoneNumber(){
        // Create an instance of the Random class
        Random random = new Random();
         // Generate a 6-digit random number
        int min = 100000;
        int max = 999999; 
        
        int randomNumber = random.nextInt(max - min + 1) + min;
        System.out.println("***Random number Generated***");
        return "0803" + randomNumber + code++;
    }

    @Override
    public String toString() {
        return "VirtualSim [simNumber=" + simNumber + ", simId=" + simId + ", simActiveState=" + simActiveState + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((simNumber == null) ? 0 : simNumber.hashCode());
        result = prime * result + ((simId == null) ? 0 : simId.hashCode());
        result = prime * result + simActiveState;
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
        VirtualSim other = (VirtualSim) obj;
        if (simNumber == null) {
            if (other.simNumber != null)
                return false;
        } else if (!simNumber.equals(other.simNumber))
            return false;
        if (simId == null) {
            if (other.simId != null)
                return false;
        } else if (!simId.equals(other.simId))
            return false;
        if (simActiveState != other.simActiveState)
            return false;
        return true;
    }




   
}
