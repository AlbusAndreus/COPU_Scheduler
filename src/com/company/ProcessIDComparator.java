package com.company;

import java.util.Comparator;

public class ProcessIDComparator implements Comparator<Process> {

    public int compare(Process process1, Process process2){
        return process1.getId() - process2.getId();
    }
}
