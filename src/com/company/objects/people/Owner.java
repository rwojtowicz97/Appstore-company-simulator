package com.company.objects.people;

import com.company.objects.skills.*;

import java.util.ArrayList;
import java.util.List;

public class Owner {
    public String ownerName;
    public double saldo;
    public List<Skill> skills;
    public List<Worker> workers;

    public Owner(String name)
    {
        this.ownerName = name;
        this.saldo = 2500d;
        List<Skill> skills = new ArrayList<Skill>();
        skills.add(new Backend(10));
        skills.add(new Databases(5));
        skills.add(new Frontend(5));
        skills.add(new Wordpress(8));
        skills.add(new Pretashop(5));
    }

}
