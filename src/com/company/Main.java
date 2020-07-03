package com.company;

import com.company.objects.Game;
import com.company.objects.people.Owner;


public class Main {

    public static void main(String[] args) {

        Owner owner = new Owner("Robert");

        Game game = new Game(owner);
        game.Play();
    }
}
