package com.nimbalkar.chetan;

public class User {
   private static int userID; 
   public static void setUserID(int id) {
       userID = id;
   }
   public static int getUserID() {
        return userID;
   }
}