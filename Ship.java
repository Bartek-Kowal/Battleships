package com.company;

public class Ship {
    public int type;
    private int hp;

    public Ship(int type)
    {
        this.type = type;
        this.hp = type;
    }

    public void gettingHit()
    {
        hp--;
    }

    public boolean isAlive()
    {
        return hp > 0;
    }
}
