package com.company;

public class Ship {
    public int type;                    //length of the ship
    public boolean vertical = true;     //true -> vertical, false -> horizontal
    private int health;

    public Ship(int type, boolean vertical) {
        this.type = type;
        this.vertical = vertical;
        this.health = type;             //health is equal to the type, eg: 5hp = 5-masted-ship
    }

    public void hit() {
        health--;
    }

    public boolean isAlive() {
        return health > 0;
    }
}
