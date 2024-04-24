package com.codecool.secureerp.model;

public class HRModel {
    private int id;
    private String name;
    private String birthDate;
    private String department;
    private int clearance;

    public HRModel(int id, String name, String birthDate, String department, int clearance) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.department = department;
        this.clearance = clearance;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getDepartment() {
        return department;
    }

    public int getClearance() {
        return clearance;
    }
}
