package com.example.employeelist;

import java.util.List;

public class Company {
    private String name;
    private int age;
    private List<String> competences;
    private List<Employee> employees;

    public Company(String name, int age, List<String> competences, List<Employee> employees) {
        this.name = name;
        this.age = age;
        this.competences = competences;
        this.employees = employees;
    }

    public Company() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getCompetences() {
        return competences;
    }

    public void setCompetences(List<String> competences) {
        this.competences = competences;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

}
