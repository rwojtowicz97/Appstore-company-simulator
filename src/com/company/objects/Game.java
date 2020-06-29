package com.company.objects;

import com.company.objects.people.Client;
import com.company.objects.people.OldFriend;
import com.company.objects.people.Owner;
import com.company.objects.people.Worker;

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

    public void NextDay()
    {
        dayCounter++;
    }

    public void PrintAllClients()
    {
        for (Client client: clients)
        {
            System.out.println(client);
        }
    }

    public void PrintAllProjects()
    {
        for (Project project: projects)
        {
         System.out.println(project);
        }
    }

    public void PrintAllWorkers()
    {
        for (Worker worker: workers
             ) {
            System.out.println(worker);
        }
    }

    public void PrintAllOldFriends()
    {
        for (OldFriend oldFriend: oldFriends
        ) {
            System.out.println(oldFriend);
        }
    }

    public int RandomNumberGenerator(int min, int max)
    {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public void Play()
    {

    }
}
