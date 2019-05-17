package com.example.laxmikant.databaseexample;

public class EmployeeInfoModel {
    private String name;
    private String age;
    private String company;
    private String role;

    public EmployeeInfoModel() {

    }

    public EmployeeInfoModel(String name, String age, String company, String role) {
        this.name = name;
        this.age = age;
        this.company = company;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
