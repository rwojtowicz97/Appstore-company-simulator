package com.company;

import com.company.objects.Game;
import com.company.objects.people.Owner;

public class Main {

    public static void main(String[] args) {

        Owner owner = new Owner("Robert");

        Game game = new Game(owner);
        game.Play();

        System.out.println(game.projects.get(0).daysOfWork);
        game.PrintAllProjects();

        owner.AssignProject(game.projects.get(0), game);

        System.out.println("After Asign");
        game.PrintAllProjects();

        System.out.println("Current project");
        owner.CurrentProject();


    }
}
