package com.company.objects;

import com.company.objects.people.Client;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public static List<Client> clients = new ArrayList<Client>();
    public static List<Project> projects = new ArrayList<Project>();


    public void InitiateClients()
    {
        clients.add(new Client("Stefan Komora", 1));
        clients.add(new Client("Andrzej Kowalski", 2));
        clients.add(new Client("Michal Lis", 3));
        clients.add(new Client("Daniel Kubek", 2));
        clients.add(new Client("Marcin Nowak", 1));
    }

    public void InitiateProjects()
    {
        projects.add(new Project("Allegro", 10, clients.get(0), 10, 500d,1500, 20));
    }

    public void Play()
    {

    }
}
