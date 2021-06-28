package com.company;

public class Process {
    int id;
    int burstLength;
    int priority;

    public Process(int id, int burstLength, int priority) {
        this.id = id;
        this.burstLength = burstLength;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBurstLength() {
        return burstLength;
    }

    public void setBurstLength(int burstLength) {
        this.burstLength = burstLength;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
