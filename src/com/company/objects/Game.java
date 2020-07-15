package com.company.objects;

import com.company.Randomizer;
import com.company.objects.people.Client;
import com.company.objects.people.OldFriend;
import com.company.objects.people.Owner;
import com.company.objects.people.Worker;
import com.company.objects.skills.Skill;
import com.github.javafaker.Faker;
import org.joda.time.DateTime;

import java.util.*;
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
    public DateTime dateTime;


    Faker faker = new Faker();

    public Game(Owner owner)
    {
        this.dateTime = new DateTime(2020,01,01,0,0);
        this.owner = owner;
        this.dayCounter = 1;
        this.zusCounter = 0;
        this.newClientCounter = 0;
        this.bigProjectCounter = 0;
        InitializeData();
    }

    public void DisplayMenu()
    {
        if(owner.saldo < 0)
        {
            System.out.println("YOU LOST!");
            System.exit(0);
        }else {
            System.out.println();
            if (owner.projects.isEmpty()) {
                System.out.println("Date: " + dateTime.getYear() + "-" + dateTime.getMonthOfYear() + "-" + dateTime.getDayOfMonth() + " | Day: " + dayCounter + " | Saldo: " + owner.saldo);
            } else {
                System.out.println("Date: " + dateTime.getYear() + "-" + dateTime.getMonthOfYear() + "-" + dateTime.getDayOfMonth() + " | Day: " + dayCounter + " | Saldo: " + owner.saldo + " | Project name: " + owner.projects.get(0).projectName + " WorkDays left: " + owner.projects.get(0).daysOfWork);
            }
            System.out.println();
            System.out.println("1. Work on current project");
            System.out.println("2. List your workers");
            System.out.println("3. Find new worker");
            System.out.println("4. Check your current project");
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
                BuyWorker();
                break;
            case 4:
                owner.CurrentProject();
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
                FireWorker();
                break;
            case 0:
                System.exit(0);
        }
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
        oldFriends.add(new OldFriend("Rafał Kopacz", RandomNumberGenerator(400, 1000), 1));
        oldFriends.add(new OldFriend("Mateusz Klosz", RandomNumberGenerator(1000, 3000), 2));
        oldFriends.add(new OldFriend("Zbigniew Korek", RandomNumberGenerator(2500, 4000), 3));
    }

    public void PayWorkersAndZus() {
        if (!owner.workers.isEmpty()) {
            for (Worker worker : owner.workers
            ) {
                owner.saldo -= (worker.salary + 0.23 * worker.salary);
            }
            NextDay();
        }else {
            System.out.println("You don't have any worker");
            DisplayMenu();
        }
    }

    public void FireWorker()
    {
        owner.PrintAllWorkers();
        int option = scan.nextInt();
        owner.workers.remove(option-1);
        owner.saldo -= 200;
        NextDay();
    }

    public void BuyWorker() {
        PrintAllWorkers();
        int option = scan.nextInt();

        if (option > workers.size()){
            DisplayMenu();
        }
        else {
            if (owner.saldo >= workers.get(option - 1).price) {
                owner.saldo -= workers.get(option - 1).price;
                owner.workers.add(workers.get(option - 1));
                workers.remove(option - 1);
                GenerateNewWorker();
                NextDay();
            } else {
                System.out.println("Sorry, you don't have enough money to buy this Worker.");
            }
        }
    }

    public void AssignProject()
    {
        PrintAllProjects();
        int option = scan.nextInt();

        if(option > projects.size()){
            DisplayMenu();
        }
        else {
            Project project = projects.get(option - 1);
            owner.projects.add(project);
            this.projects.remove(project);
            NextDay();
        }
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
                System.out.println("Congratulations, you returned the project!");
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
        dateTime = dateTime.plusDays(1);
        DateTime.Property pDoW = dateTime.dayOfWeek();
        String strT = pDoW.getAsText(Locale.ENGLISH);

        System.out.println(strT);
        dayCounter++;
        if(!(strT == "Saturday" | strT == "Sunday"))
        {
            WorkersWork();
        }
        DisplayMenu();
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
        int index = 1;
        System.out.println("----------Available Workers-----------");
        for (Worker worker: workers
             ) {
            System.out.println((index++) + ". " + worker);
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

    public void GenerateNewWorker()
    {
        String randomName = faker.funnyName().name();
        double randomPrice = RandomNumberGenerator(200,6000);
        double randomSalary = RandomNumberGenerator(2500, 10000);
        workers.add(new Worker(randomName, randomPrice, randomSalary));
    }

    public void Work()
    {
        if(!owner.projects.isEmpty())
        {
            for (Skill projectSkill: owner.projects.get(0).skillsNeeded)
            {
                if (projectSkill.daysOfWorkLeft > 0) {
                    for (Skill ownerSkill : owner.skills)
                    {
                        if (projectSkill.getClass().getSimpleName() == ownerSkill.getClass().getSimpleName())
                        {
                            projectSkill.daysOfWorkLeft -= 1;
                            owner.projects.get(0).daysOfWork -= 1;
                            NextDay();
                        }
                    }
                }
            }
            System.out.println("Sorry, you can't work on this project.");
            DisplayMenu();
        }
        else {
            System.out.println("You don't have any project.");
            DisplayMenu();
        }
    }

    public void WorkersWork() {
        if (!owner.projects.isEmpty() & !owner.workers.isEmpty()) {
                for (Worker worker : owner.workers) {
                    int isSick = RandomNumberGenerator(1,100);
                    if (isSick < 95)
                    {
                        label:
                        for (Skill workerSkill: worker.skills
                             ) {
                            if (owner.projects.get(0).daysOfWork > 0) {
                                for (Skill projectSkill : owner.projects.get(0).skillsNeeded
                                ) {
                                    if ((projectSkill.getClass().getSimpleName() == workerSkill.getClass().getSimpleName()) & (projectSkill.daysOfWorkLeft > 0))
                                    {
                                        projectSkill.daysOfWorkLeft -= 1;
                                        owner.projects.get(0).daysOfWork -= 1;
                                        break label;
                                    }
                                }
                                continue;
                            }
                            break;
                        }
                    } else {
                        System.out.println("Worker " + worker.workerName + " is sick today.");
                    }
                }
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
