package com.company;

import java.util.Comparator;

public class BurstComparator implements Comparator<Process> {
    @Override
    public int compare(Process a, Process b){
        return a.getBurstLength() - b.getBurstLength();
    }
}
