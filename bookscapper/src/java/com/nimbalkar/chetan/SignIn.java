
package com.nimbalkar.chetan;

public class SignIn {
    private static boolean isSignedIn;
    public static void setSignIn(boolean value) {
        isSignedIn = value;
    }
    
    public static boolean getSignIn() {
        return isSignedIn;
    }
}
