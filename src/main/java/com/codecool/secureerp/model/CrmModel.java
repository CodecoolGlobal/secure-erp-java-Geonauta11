package com.codecool.secureerp.model;

import java.util.Objects;

public record CrmModel(String id, String name, String email, boolean isSubscribed) implements Model {
}