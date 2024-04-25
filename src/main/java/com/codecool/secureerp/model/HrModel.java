package com.codecool.secureerp.model;

public record HrModel(String id, String name, String birthDate, String department, int clearance) implements Model {
}
