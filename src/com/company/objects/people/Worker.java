package com.company.objects.people;

import com.company.objects.skills.Skill;

import java.util.List;

public class Worker {
    public String workerName;
    public double price;
    public double salary;
    public List<Skill> skills;

    public Worker(String name, double price, double salary)
    {
        this.workerName = name;
        this.price = price;
        this.salary = salary;

    }
}
