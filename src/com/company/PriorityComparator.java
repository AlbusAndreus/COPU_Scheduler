package com.company;

import java.util.Comparator;

public class PriorityComparator implements Comparator<Process> {
    @Override
    public int compare(Process a, Process b){
        return a.getPriority() - b.getPriority();
    }
}
