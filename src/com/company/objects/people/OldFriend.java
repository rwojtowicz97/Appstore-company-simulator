package com.company.objects.people;

import com.company.Randomizer;
import com.company.objects.skills.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class OldFriend implements Randomizer {
    public String name;
    public int level;
    public List<Skill> skills = new ArrayList<Skill>();
    public double price;
    public int randomSkill;


    public OldFriend(String name, double price, int level)
    {
        this.name = name;
        this.price = price;
        this.level = level;

        switch (this.level){
            case 1:
                this.randomSkill = RandomNumberGenerator(1,6);
                break;
            case 2:
                this.randomSkill = RandomNumberGenerator(7,9);
                break;
            case 3:
                this.randomSkill = RandomNumberGenerator(10,12);
                break;
        }

        switch (randomSkill){
            case 1:
                this.skills.add(new Backend());
                break;
            case 2:
                this.skills.add(new Databases());
                break;
            case 3:
                this.skills.add(new Frontend());
                break;
            case 4:
                this.skills.add(new Wordpress());
                break;
            case 5:
                this.skills.add(new Pretashop());
                break;
            case 6:
                this.skills.add(new Mobile());
                break;
            case 7:
                this.skills.add(new Backend());
                this.skills.add(new Databases());
                break;
            case 8:
                this.skills.add(new Wordpress());
                this.skills.add(new Pretashop());
                break;
            case 9:
                this.skills.add(new Mobile());
                this.skills.add(new Frontend());
                break;
            case 10:
                this.skills.add(new Backend());
                this.skills.add(new Mobile());
                this.skills.add(new Frontend());
                break;
            case 11:
                this.skills.add(new Mobile());
                this.skills.add(new Databases());
                this.skills.add(new Pretashop());
                break;
            case 12:
                this.skills.add(new Mobile());
                this.skills.add(new Backend());
                this.skills.add(new Databases());
                this.skills.add(new Pretashop());
                break;
        }
    }

    @Override
    public String toString()
    {
        return "OldFriend {" + "Old friend name= " + name + '\'' +
                ", level= " + level + '\'' +
                ", skills= " + skills + '\'' +
                ", price= " + price + '\'';
    }

    @Override
    public int RandomNumberGenerator(int min, int max)
    {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
