package com.codecool.secureerp.model;

public record CrmModel(String id, String name, String email, boolean isSubscribed) implements Model{
}