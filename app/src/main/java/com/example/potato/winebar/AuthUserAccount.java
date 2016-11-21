package com.example.potato.winebar;

import java.util.HashMap;

/**
 * Created by praneeth on 8/26/16.
 */
public class AuthUserAccount {
    public String name;
    public String email;
    public HashMap<String, String> campaigns;

    public AuthUserAccount() {
        // Default constructor required for calls to DataSnapshot.getValue(AuthUserAccount.class)
    }

    public AuthUserAccount(String name, String email, HashMap<String, String> campaigns) {
        this.name = name;
        this.email = email;
        this.campaigns = campaigns;
    }
}
