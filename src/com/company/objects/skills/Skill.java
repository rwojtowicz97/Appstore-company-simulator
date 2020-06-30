package com.company.objects.skills;

public abstract class Skill {
    public int daysOfWorkLeft;

    public Skill()
    {

    }

    public Skill( int daysOfWorkLeft)
    {
        this.daysOfWorkLeft = daysOfWorkLeft;
    }


    @Override
    public String toString()
    {
        return "Skill { skill name: " + this.getClass().getSimpleName() + '\'';
    }
}
