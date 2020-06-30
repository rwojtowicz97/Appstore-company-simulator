package com.company.objects;

import com.company.Randomizer;
import com.company.objects.people.Client;
import com.company.objects.people.OldFriend;
import com.company.objects.people.Owner;
import com.company.objects.people.Worker;
import com.company.objects.skills.Skill;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Game implements Randomizer {
    public int dayCounter;
    public int zusCounter;
    public int newClientCounter;
    public int bigProjectCounter;
    public Owner owner;
    public static List<Client> clients = new ArrayList<Client>();
    public static List<Project> projects = new ArrayList<Project>();
    public static List<Worker> workers = new ArrayList<Worker>();
    public static List<OldFriend> oldFriends = new ArrayList<OldFriend>();
    public Scanner scan = new Scanner(System.in);

    Faker faker = new Faker();

    public Game(Owner owner)
    {
        this.owner = owner;
        this.dayCounter = 1;
        this.zusCounter = 0;
        this.newClientCounter = 0;
        this.bigProjectCounter = 0;
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
        for (int i = 0;i<=3;i++)
        {
            GenerateNewProject();
        }
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

    public void FireWorker(int x)
    {
        //TODO
        owner.PrintAllWorkers();
        owner.workers.remove(x);
        owner.saldo -= 200;
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

    public void AssignProject()
    {
        PrintAllProjects();
        int option = scan.nextInt();

        Project project = projects.get(option-1);

        owner.projects.add(project);
        this.projects.remove(project);
        NextDay();
    }

    public void ReturnProject()
    {
        if(!owner.projects.isEmpty()){
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
        System.out.println();
        System.out.println("Day: " + dayCounter + " | Saldo: " + owner.saldo);
        System.out.println();
        System.out.println("1. Work on current project");
        System.out.println("2. List your workers");
        System.out.println("3. Find new worker");
        System.out.println("4. List available projects");
        System.out.println("5. List your old friends");
        System.out.println("6. Find a new project (Stage: " + newClientCounter + "/5)");
        System.out.println("7. Pay your workers");
        System.out.println("8. Choose new project");
        System.out.println("9. Go back to menu");
        System.out.println("10. Return your project");
        System.out.println("11. Kick-out your worker");
        System.out.println("0. EXIT");
        System.out.println();
        MenuScanner();
    }

    public void MenuScanner()
    {
        int option = scan.nextInt();

        switch (option){
            case 1:
                Work();
                break;
            case 2:
                owner.PrintAllWorkers();
                break;
            case 3:
                PrintAllWorkers();
                break;
            case 4:
                PrintAllProjects();
                break;
            case 5:
                PrintAllOldFriends();
                break;
            case 6:
                FindNewProject();
                break;
            case 7:
                PayWorkersAndZus();
                break;
            case 8:
                AssignProject();
                break;
            case 9:
                DisplayMenu();
                break;
            case 10:
                ReturnProject();
                break;
            case 11:
                //TODO
                owner.PrintAllWorkers();
                break;
            case 0:
                System.exit(0);
        }
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
        int index = 1;
        System.out.println("----------Available Projects-----------");
        for (Project project: projects)
        {
         System.out.println((index++) + ". " +project);
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
        System.out.println("---------------------------------");
    }

    public void GenerateNewProject()
    {
        String randomName = faker.rickAndMorty().location();
        int randomNumber = RandomNumberGenerator(0,14);
        int clientNumber = RandomNumberGenerator(0, clients.size()-1);
        int daysTillDeadLine = RandomNumberGenerator(5,15);
        int daysTillPayment = randomNumber + daysTillDeadLine;
        double randomPenalty = RandomNumberGenerator(0,500);
        double randomPrice = RandomNumberGenerator(500, 10000);
        projects.add(new Project(randomName, clients.get(clientNumber), daysTillPayment, randomPenalty, randomPrice, daysTillDeadLine));
    }

    public void Work()
    {
        if(!owner.projects.isEmpty())
        {
            for (Skill projectSkill: owner.projects.get(0).skillsNeeded
                 ) {
                for (Skill ownerSkill: owner.skills
                     ) {
                    if(projectSkill.getClass().getSimpleName() == ownerSkill.getClass().getSimpleName())
                    {
                        if (projectSkill.daysOfWorkLeft > 0)
                        {
                            projectSkill.daysOfWorkLeft--;
                            owner.projects.get(0).UpdateDaysOfWork();
                            NextDay();
                            return;
                        }
                    }

                }
            }
            System.out.println("Sorry, you can't work on this project.");
        }
        else {
            System.out.println("You don't have any project.");
        }
    }

    public int RandomNumberGenerator(int min, int max)
    {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public void Play()
    {
        while (true){
            DisplayMenu();
        }
    }
}
