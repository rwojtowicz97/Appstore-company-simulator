package com.company.objects.people;

import com.company.objects.Project;
import com.company.objects.skills.*;

import java.util.ArrayList;
import java.util.List;

public class Owner {
    public String ownerName;
    public double saldo;
    public List<Skill> skills;
    public List<Worker> workers;
    public List<Project> projects;
    public List<OldFriend> oldFriends;

    public Owner(String name)
    {
        this.ownerName = name;
        this.saldo = 2500d;
        skills = new ArrayList<Skill>();
        this.skills.add(new Backend());
        this.skills.add(new Databases());
        this.skills.add(new Frontend());
        this.skills.add(new Wordpress());
        this.skills.add(new Pretashop());
        this.workers = new ArrayList<Worker>();
        this.projects = new ArrayList<Project>();
        this.oldFriends = new ArrayList<OldFriend>();
    }


    public void CurrentProject()
    {
        if (!this.projects.isEmpty()) {
            System.out.println("Current project:");
            System.out.println(this.projects.get(0));
        }else {
            System.out.println("You don't have any project");
        }
    }
    public void PrintAllWorkers()
    {
        int index = 1;
        System.out.println("----------My Workers-----------");
        for (Worker worker: workers
        ) {
            System.out.println((index++) + ". " +worker);
        }
        System.out.println("-------------------------------");
    }

}
