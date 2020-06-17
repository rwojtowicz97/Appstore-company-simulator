package com.company.objects.skills;

public abstract class Skill {
    public int skillLevel;

    public Skill(int level)
    {
        this.skillLevel = level;
    }

    public int getSkillLevel(){
        return skillLevel;
    }
}
