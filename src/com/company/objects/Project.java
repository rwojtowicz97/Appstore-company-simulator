package com.company.objects;

import com.company.Randomizer;
import com.company.objects.people.Client;
import com.company.objects.skills.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Project implements Randomizer {
    public String projectName;
    public int daysOfWork;
    public Client client;
    public int daysTillPayment;
    public int daysTillDeadline;
    public double penalty;
    public double price;
    public int level;
    public List<Skill> skillsNeeded = new ArrayList<Skill>();
    public static List<Skill> allSkills = new ArrayList<Skill>();


    public Project(String projectName, Client client, int daysTillPayment, double penalty, double price, int daysTillDeadline)
    {
        this.projectName = projectName;
        this.client = client;
        this.daysTillPayment = daysTillPayment;
        this.penalty = penalty;
        this.price = price;
        this.daysTillDeadline = daysTillDeadline;
        this.level = RandomNumberGenerator(1,3);

        if (skillsNeeded.isEmpty())
        {
            InitializeAllSkills();
        }

        switch (level){
            case 1:
                this.skillsNeeded.add(allSkills.get(RandomNumberGenerator(0,allSkills.size()-1)));
                skillsNeeded.get(0).daysOfWorkLeft = RandomNumberGenerator(1,5);
                break;
            case 2:
                this.skillsNeeded.add(allSkills.get(RandomNumberGenerator(0,2)));
                skillsNeeded.get(0).daysOfWorkLeft = RandomNumberGenerator(1,5);
                this.skillsNeeded.add(allSkills.get(RandomNumberGenerator(3,5)));
                skillsNeeded.get(1).daysOfWorkLeft = RandomNumberGenerator(1,5);
                break;
            case 3:
                this.skillsNeeded.add(allSkills.get(RandomNumberGenerator(0,1)));
                skillsNeeded.get(0).daysOfWorkLeft = RandomNumberGenerator(1,5);
                this.skillsNeeded.add(allSkills.get(RandomNumberGenerator(2,3)));
                skillsNeeded.get(1).daysOfWorkLeft = RandomNumberGenerator(1,5);
                this.skillsNeeded.add(allSkills.get(RandomNumberGenerator(4,5)));
                skillsNeeded.get(2).daysOfWorkLeft = RandomNumberGenerator(1,5);
                break;
        }


//        this.skillsNeeded.add(new Backend(RandomNumberGenerator(1,5)));
//        this.skillsNeeded.add(new Frontend(ThreadLocalRandom.current().nextInt(1, 5 + 1)));
//        this.skillsNeeded.add(new Pretashop(ThreadLocalRandom.current().nextInt(1, 5 + 1)));

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

    public void InitializeAllSkills()
    {
        allSkills.add(new Backend());
        allSkills.add(new Databases());
        allSkills.add(new Frontend());
        allSkills.add(new Wordpress());
        allSkills.add(new Pretashop());
        allSkills.add(new Mobile());
    }

    public int RandomNumberGenerator(int min, int max)
    {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
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
