package com.company.objects;

import com.company.objects.people.Client;
import com.company.objects.people.OldFriend;
import com.company.objects.people.Owner;
import com.company.objects.people.Worker;
import com.company.objects.skills.Skill;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Game {
    public int dayCounter;
    public int zusCounter;
    public int newClientCounter;
    public int bigProjectCounter;
    public Owner owner;
    public static List<Client> clients = new ArrayList<Client>();
    public static List<Project> projects = new ArrayList<Project>();
    public static List<Worker> workers = new ArrayList<Worker>();
    public static List<OldFriend> oldFriends = new ArrayList<OldFriend>();


    public Game(Owner owner)
    {
        this.owner = owner;
        dayCounter = 1;
        zusCounter = 0;
        newClientCounter = 0;
        bigProjectCounter = 0;
        InitializeData();
    }

    public void InitializeData()
    {
        InitializeClients();
        InitializeProjects();
        InitializeWorkers();
        InitializeOldFriends();
    }

    public void InitializeClients()
    {
        clients.add(new Client("Stefan Komora", 1));
        clients.add(new Client("Andrzej Kowalski", 2));
        clients.add(new Client("Michal Lis", 3));
        clients.add(new Client("Daniel Kubek", 2));
        clients.add(new Client("Marcin Nowak", 1));
    }

    public void InitializeProjects()
    {
        projects.add(new Project("Allegro", clients.get(0), 10, 0d,1500d, 20));
        projects.add(new Project("Instagram", clients.get(1), 10, 5000d,7000d, 20));
        projects.add(new Project("Facebook", clients.get(2), 10, 0d,2000d, 20));
        projects.add(new Project("OLX", clients.get(3), 10, 7000d,4500d, 20));
        projects.add(new Project("Spotify", clients.get(4), 10, 9000d,15000d, 20));
    }

    public void InitializeWorkers()
    {
        workers.add(new Worker("Milosz Gajowy", 400d, 200d));
        workers.add(new Worker("Bartosz Kownacki", 600d, 400d));
        workers.add(new Worker("Krystian Topór", 1000d, 1000d));
    }

    public void InitializeOldFriends()
    {
        oldFriends.add(new OldFriend("Rafał Kopacz", RandomNumberGenerator(2000, 4000), 1));
        oldFriends.add(new OldFriend("Mateusz Klosz", RandomNumberGenerator(1000, 3000), 2));
        oldFriends.add(new OldFriend("Zbigniew Korek", RandomNumberGenerator(400, 1000), 3));
    }

    public void PayWorkersAndZus() {
        for (Worker worker: owner.workers
             ) {
            owner.saldo -= (worker.salary + 0.23*worker.salary);
        }
        NextDay();
    }

    public void BuyWorker(Worker worker) {
        if (owner.saldo >= worker.price) {
            owner.workers.add(worker);
            this.workers.remove(worker);
            owner.saldo -= worker.price;
            NextDay();
        }
        else {
            System.out.println("Sorry, you don't have enough money to buy this Worker.");
        }
    }

    public void AssignProject(Project project)
    {
        owner.projects.add(project);
        this.projects.remove(project);
        NextDay();
    }

    public void ReturnProject()
    {
        if(owner.projects.get(0) != null){
        for (Skill skill: owner.projects.get(0).skillsNeeded
             ) {
            if(skill.daysOfWorkLeft != 0)
            {
                System.out.println("You can't return unfinished project!");
            }
            else
            {
                System.out.println("Congratulations, you returner the project!");
                owner.saldo += owner.projects.get(0).price;
                owner.projects.remove(0);
                NextDay();
            }
            return;
        }
        }
        else
        {
            System.out.println("You don't have any projects.");
        }
    }

    public void FindNewProject() {
        if (newClientCounter != 5) {
            this.newClientCounter++;
        }
        else {
            GenerateNewProject();
            this.newClientCounter = 0;
        }
        NextDay();
    }
    public void NextDay()
    {
        dayCounter++;
        DisplayMenu();
    }

    public void DisplayMenu()
    {
        System.out.println("Day: " + dayCounter + " | Saldo: " + owner.saldo);
        System.out.println(" ");
        System.out.println("1. Work on current project");
        System.out.println("2. List your workers");
        System.out.println("3. Find new worker");
        System.out.println("4. List Available Projects");
        System.out.println("5. List your old friends");
        System.out.println("6. Find a new Project (Stage: " + newClientCounter + "/5)");
        System.out.println("7. Pay your workers");


        System.out.println("9. Go back to menu");
        System.out.println("0. EXIT");
        System.out.println(" ");
    }

    public void PrintAllClients()
    {
        System.out.println("--------------Clients------------------");
        for (Client client: clients)
        {
            System.out.println(client);
        }
        System.out.println("---------------------------------------");
    }

    public void PrintAllProjects()
    {
        System.out.println("----------Available Projects-----------");
        for (Project project: projects)
        {
         System.out.println(project);
        }
        System.out.println("---------------------------------------");
    }

    public void PrintAllWorkers()
    {
        System.out.println("----------Available Workers-----------");
        for (Worker worker: workers
             ) {
            System.out.println(worker);
        }
        System.out.println("---------------------------------------");
    }

    public void PrintAllOldFriends()
    {
        System.out.println("----------Old Friends-----------");
        for (OldFriend oldFriend: oldFriends
        ) {
            System.out.println(oldFriend);
        }
        System.out.println("---------------------------------------");
    }

    public void GenerateNewProject()
    {

    }

    public void Work()
    {

    }

    public int RandomNumberGenerator(int min, int max)
    {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public void Play()
    {

    }
}
