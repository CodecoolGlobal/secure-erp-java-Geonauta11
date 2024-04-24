package com.codecool.secureerp.model;

public class CRMModel {
    private final String id;
    private final String name;
    private final String email;
    private final boolean isSubscribed;

    public CRMModel(String id, String name, String email, boolean isSubscribed) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.isSubscribed = isSubscribed;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean isSubscribed() {
        return isSubscribed;
    }
}