package com.company;

import com.company.objects.Game;
import com.company.objects.people.Owner;

import java.awt.*;

public class Main {

    public static void main(String[] args) {

        Owner owner = new Owner("Robert");

        Game game = new Game(owner);
        game.Play();

        System.out.println(game.projects.get(0).daysOfWork);
        game.PrintAllProjects();

        game.AssignProject(game.projects.get(0));

        game.PrintAllProjects();

        owner.CurrentProject();

        game.PrintAllWorkers();

        System.out.println(owner.saldo);
        game.BuyWorker(game.workers.get(0));
        System.out.println(owner.saldo);

        game.PrintAllWorkers();
        owner.PrintAllWorkers();

        game.FindNewProject();
        game.DisplayMenu();


    }
}
