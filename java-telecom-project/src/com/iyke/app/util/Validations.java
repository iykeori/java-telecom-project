package com.iyke.app.util;


public class Validations {
   
    public static boolean validateSimNumberEntry(int simNumber){
        // if(simNumber <= 0 ){
        //     return false;
        // }
        String phoneNumber = Integer.toString(simNumber);

        phoneNumber = phoneNumber.trim();
        if(phoneNumber.isEmpty() || phoneNumber == null){
            return false;
        }

        if(phoneNumber.length() < 11 || phoneNumber.length() > 11){
            return false;
        }

        return true;
    }

    
    
}
