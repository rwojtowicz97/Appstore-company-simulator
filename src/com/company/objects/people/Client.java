package com.company.objects.people;

public class Client {
    public String clientName;
    public int level;

    public Client(String clientName, int level) {
        this.clientName = clientName;
        this.level = level;
    }

    @Override
    public String toString()
    {
        return "Client {" + "Client name= " + clientName + '\'' +
                ", level= " + level + '\'';
    }
}
