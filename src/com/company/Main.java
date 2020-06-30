package com.company;

import com.company.objects.Game;
import com.company.objects.people.Owner;

import java.awt.*;

public class Main {

    public static void main(String[] args) {

        Owner owner = new Owner("Robert");

        Game game = new Game(owner);
        game.Play();

        game.PrintAllProjects();

        game.AssignProject(game.projects.get(0));

        game.Work();

        owner.CurrentProject();

        game.Work();

        owner.CurrentProject();

        game.Work();
        game.Work();
        game.Work();
        game.Work();
        game.Work();

        owner.CurrentProject();

        game.ReturnProject();

        game.BuyWorker(game.workers.get(0));

    }
}
