package com.company.objects;

import com.company.objects.people.Client;
import com.company.objects.skills.*;

import java.util.ArrayList;
import java.util.List;

public class Project {
    public String projectName;
    public int daysOfWork;
    public Client client;
    private int daysTillPayment;
    public int daysTillDeadline;
    public double penalty;
    public double price;
    public List<Skill> skillsNeeded = new ArrayList<Skill>();

    public Project(String projectName, int daysOfWork, Client client, int daysTillPayment, double penalty, double price, int daysTillDeadline)
    {
        this.projectName = projectName;
        this.daysOfWork = daysOfWork;
        this.client = client;
        this.daysTillPayment = daysTillPayment;
        this.penalty = penalty;
        this.price = price;
        this.daysTillDeadline = daysTillDeadline;

        this.skillsNeeded.add(new Backend(3));
        this.skillsNeeded.add(new Databases(3));
        this.skillsNeeded.add(new Frontend(5));
        this.skillsNeeded.add(new Wordpress(0));
        this.skillsNeeded.add(new Pretashop(3));

    }
}
