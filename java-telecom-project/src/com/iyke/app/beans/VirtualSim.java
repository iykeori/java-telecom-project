package src.com.iyke.app.beans;

import java.util.UUID;

public class VirtualSim{
    private int simNumber;
    private UUID simId;
    private int simActiveState;
    private static int code = 0;

    //constructor
    public VirtualSim(int simNumber, int simActiveState) {
        this.simNumber = simNumber;
        this.simActiveState = simActiveState;
        this.simId = UUID.randomUUID();
    }
    
    //getters and setters
    public int getSimNumber() {
        return simNumber;
    }

    public void setSimNumber(int simNumber) {
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
    public String generatePhoneNumber(){
        // Create an instance of the Random class
        Random random = new Random();
         // Generate a 6-digit random number
        int min = 100000;
        int max = 999999; 

        int randomNumber = random.nextInt(max - min + 1) + min;

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
        result = prime * result + simNumber;
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
        if (simNumber != other.simNumber)
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
