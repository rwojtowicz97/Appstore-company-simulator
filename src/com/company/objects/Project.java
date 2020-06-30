package com.company.objects;

import com.company.objects.people.Client;
import com.company.objects.skills.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Project {
    public String projectName;
    public int daysOfWork;
    public Client client;
    public int daysTillPayment;
    public int daysTillDeadline;
    public double penalty;
    public double price;
    public List<Skill> skillsNeeded = new ArrayList<Skill>();

    public Project(String projectName, Client client, int daysTillPayment, double penalty, double price, int daysTillDeadline)
    {
        this.projectName = projectName;
        
        this.client = client;
        this.daysTillPayment = daysTillPayment;
        this.penalty = penalty;
        this.price = price;
        this.daysTillDeadline = daysTillDeadline;

        this.skillsNeeded.add(new Backend(ThreadLocalRandom.current().nextInt(1, 5 + 1)));
        this.skillsNeeded.add(new Frontend(ThreadLocalRandom.current().nextInt(1, 5 + 1)));
        this.skillsNeeded.add(new Pretashop(ThreadLocalRandom.current().nextInt(1, 5 + 1)));

        this.daysOfWork = SumDaysOfWork();
    }

    public int SumDaysOfWork()
    {
        int sum = 0;
        for (Skill skill: skillsNeeded)
        {
            sum += skill.daysOfWorkLeft;
        }
        return sum;
    }

    public void UpdateDaysOfWork()
    {
        int sum=0;
        for (Skill skill: skillsNeeded)
        {
            sum += skill.daysOfWorkLeft;
        }
        this.daysOfWork = sum;
    }

    @Override
    public String toString()
    {
        return "Project {" + "Project name= " + projectName + '\'' +
                ", days of work= " + daysOfWork + '\'' +
                ", days till payment= " + daysTillPayment + '\'' +
                ", penalty= " + penalty + '\'' +
                ", price= " + price + '\'' +
                ", days till deadline= " + daysTillDeadline + '}';
    }
}
