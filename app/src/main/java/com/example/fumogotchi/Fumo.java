package com.example.fumogotchi;

public class Fumo
{

    private int hunger;
    private int mood;

    public Fumo() {
        this.hunger = 100;
        this.mood = 100;
    }

    public int getHunger() { return hunger; }

    public void setHunger(int hunger) { this.hunger = hunger; }

    public int getMood() { return mood; }

    public void setMood(int mood) { this.mood = mood; }

    public int improveHunger()
    {
        if(this.hunger < 100){
            this.hunger += 10;
            return this.hunger;
        }
        return this.hunger;
    }

    public int improveMood()
    {
        if(this.mood < 100){
            this.mood += 10;
            return this.mood;
        }
        return this.mood;
    }
}
