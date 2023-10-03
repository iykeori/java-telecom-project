package com.iyke.app.util;


public class Validations {
   
    public static boolean validateSimNumberEntry(int simNumber){
        if(simNumber <= 0 ){
            return false;
        }
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

    public static boolean validateField(String field, String value) {
        if (value.trim().isEmpty() || value == null) {
          return false;
        } 
        // trim user input
        value = value.trim();
  
        if (field.equals("phone")) {
          if (value.length() < 10 || value.length() > 10) {
            return false;
          }
        } else if (field.equals("gender")) {
          if (value.length() > 1 || (!value.equalsIgnoreCase("M") && !value.equalsIgnoreCase("F"))) {
            return false;
          }
        } else if (field.equals("email")) {
          // example@gmail.com
          String[] emailParts = value.split("@");
          if (emailParts.length == 2) {
            //System.out.println("Splitted!");
            if (emailParts[0].length() == 0 || emailParts[1].length() == 0) {
              //System.out.println("Bad!");
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

    
    
}
