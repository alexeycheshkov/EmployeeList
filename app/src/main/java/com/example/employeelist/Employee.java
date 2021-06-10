package com.example.employeelist;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Employee implements Comparable<Employee>{
    private String name;
    @SerializedName("phone_number")
    private Long phoneNumber;
    private List<String> skills;

    public Employee(String name, Long phoneNumber, List<String> skills) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.skills = skills;
    }

    public Employee(Long phoneNumber, List<String> skills) {
        this.name = "Unknown";
        this.phoneNumber = phoneNumber;
        this.skills = skills;
    }

    public Employee(String name, Long phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Employee(String name, List<String> skills) {
        this.name = name;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }


    @Override
    public int compareTo(Employee o) {

        if (o.getName()==null && this.getName()==null){
            return 0;
        } else if (o.getName()==null && this.getName()!=null) {
            return 1;
        } else if (o.getName()!=null && this.getName()==null){
            return -1;
        }
        return this.getName().compareTo(o.getName());
    }
}
