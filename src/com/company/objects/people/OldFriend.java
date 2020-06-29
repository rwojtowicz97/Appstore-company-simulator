package com.company.objects.people;

import com.company.objects.skills.*;

import java.util.ArrayList;
import java.util.List;

public class OldFriend {
    public String name;
    public int level;
    public List<Skill> skills = new ArrayList<Skill>();
    public double price;

    public OldFriend(String name, double price, int level)
    {
        this.name = name;
        this.price = price;
        this.level = level;


        this.skills.add(new Backend());
        this.skills.add(new Databases());
        this.skills.add(new Frontend());
        this.skills.add(new Wordpress());
        this.skills.add(new Pretashop());
    }

}
