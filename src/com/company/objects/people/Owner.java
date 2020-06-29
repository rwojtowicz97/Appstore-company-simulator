package com.company.objects.people;

import com.company.objects.Game;
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
        List<Skill> skills = new ArrayList<Skill>();
        skills.add(new Backend());
        skills.add(new Databases());
        skills.add(new Frontend());
        skills.add(new Wordpress());
        skills.add(new Pretashop());
        this.workers = new ArrayList<Worker>();
        this.projects = new ArrayList<Project>();
        this.oldFriends = new ArrayList<OldFriend>();
    }

    public void AssignProject(Project project, Game game)
    {
        this.projects.add(project);
        game.projects.remove(project);
    }

    public void BuyWorker(Worker worker, Game game) {
        if (saldo >= worker.price) {
            this.workers.add(worker);
            game.workers.remove(worker);
        }
        else {
            System.out.println("Sorry, you don't have enough money to buy this Worker.");
        }
    }
    public void CurrentProject()
    {
        System.out.println(this.projects.get(0));
    }

}
