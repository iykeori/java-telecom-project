package com.iyke.app.util;

import com.iyke.app.beans.CustomerSim;
import com.iyke.app.database.Database;

public class Validations {
   
    public static boolean validateSimNumberEntry(String simNumber){
        simNumber = simNumber.trim();
        if(simNumber.isEmpty() || simNumber == null){
            return false;
        }

        if(simNumber.length() < 11 ||  simNumber.length() > 11){
            return false;
        }

        return true;
    }
    //Method to Validate Input Registration Field
    public static boolean validateField(String field, String value) {
        if (value.trim().isEmpty() || value == null) {
          return false;
        } 
        // trim user input
        value = value.trim();
  
        if (field.equals("gender")) {
          if (value.length() > 1 || (!value.equalsIgnoreCase("M") && !value.equalsIgnoreCase("F"))) {
            return false;
          }
        } else if (field.equals("email")) {
          // example@gmail.com
          String[] emailParts = value.split("@");
          if (emailParts.length == 2) {
            //System.out.println("Splitted!");
            if (emailParts[0].length() == 0 || emailParts[1].length() == 0) {
              // E.g. @gmail.com
              return false;
            }
  
            if (!emailParts[1].contains(".")) {
              return false;
            }
  
          } else {
            return false;
          }
        }
  
        return true;
    }

    //validate sim number
    public static CustomerSim validateSimNumber(String simNumber){
        System.out.println("Validating sim number " + simNumber + " ...");
        for(CustomerSim cSim : Database.getCustomerSims() ){
            if (cSim != null) {
                boolean isMatch = cSim.getSim().getSimNumber().trim().equals(simNumber.trim());
                boolean isActive = cSim.getSim().getSimActiveState() == 1;
                
                if( isMatch && isActive ){
                System.out.println("Fetched number: " + cSim.getSim().getSimNumber());
                return cSim;
                } 
            }
        }
        return null;
    }

    
    
}
