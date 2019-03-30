package com.nimbalkar.chetan;

import java.security.MessageDigest;

public class HashClass  {
    public static String digest(String passwd) {
        String pass = "";
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");        
            System.out.println(pass);            
            pass = new String(md.digest(passwd.getBytes()));            
        } catch(Exception e) {
            e.printStackTrace();
        }
        return pass;
    }
}
