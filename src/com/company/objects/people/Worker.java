package com.company.objects.people;

import com.company.objects.skills.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Worker {
    public String workerName;
    public double price;
    public double salary;
    public List<Skill> skills = new ArrayList<Skill>();

    public Worker(String name, double price, double salary)
    {
        this.workerName = name;
        this.price = price;
        this.salary = salary;

        this.skills.add(new Backend());
        this.skills.add(new Databases());
        this.skills.add(new Frontend());
        this.skills.add(new Wordpress());
        this.skills.add(new Pretashop());
    }


    @Override
    public String toString()
    {
        return "Worker {" + "Worker name= " + workerName + '\'' +
                ", price= " + price + '\'' +
                ", salary= " + salary + '\'' +
                ", skills= " + skills + '\'';

    }
}
