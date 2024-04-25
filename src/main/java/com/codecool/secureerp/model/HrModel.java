package com.codecool.secureerp.model;

public class HrModel implements Model {
    private String id;
    private String name;
    private String birthDate;
    private String department;
    private int clearance;

    public HrModel(String id, String name, String birthDate, String department, int clearance) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.department = department;
        this.clearance = clearance;
    }

    public String getId() {
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
