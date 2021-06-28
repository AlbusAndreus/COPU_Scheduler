package com.company;



import java.util.*;


public class Main {
    static ArrayList<Process> processes = new ArrayList<>();

    public static void main(String[] args) {
	    for(int i = 0; i <7; i++){
            Random random = new Random();
            Process currentProcess = new Process(random.nextInt(10), random.nextInt(100-20) + 20, random.nextInt(10-1)+1 );
            boolean processInArrayList = false;
	        for(int j = 0; j <processes.size(); j++){
	            if(currentProcess.getId() == processes.get(j).getId()){
                    processInArrayList = true;
                }
            }
	        if(!processInArrayList){
                processes.add(currentProcess);
            }else{
	            i--;
	            continue;
            }
        }

        Comparator<Process> comp = new ProcessIDComparator();
        Collections.sort(processes, comp);
        for(int a = 0; a <processes.size(); a++){
            System.out.println("Process ID: " + String.valueOf(processes.get(a).getId()) + " | Priority: " + String.valueOf(processes.get(a).getPriority()) + " | Burst Length: " + String.valueOf(processes.get(a).getBurstLength()));
        }
        System.out.println("Create a new process. Enter a value for Process ID between 0 and 10");
        Scanner inputID = new Scanner(System.in);
        int id = inputID.nextInt();
        int changedid = id;
        boolean idchanged = false;
        while(id > 10 || id < 0  && !idchanged){

            System.out.println("Invalid ID, please enter an ID between 0 and 10");
            Scanner inputID2 = new Scanner(System.in);
            id = inputID2.nextInt();

            if(id != changedid){
                idchanged = true;
            }
            changedid = id;
        }
        ArrayList<Integer> ids = new ArrayList<>();
        for(int z = 0; z <processes.size(); z++) {
            ids.add(processes.get(z).getId());
        }
        while(ids.contains(id)){
            System.out.println("ID is contained in a predefined process. Please enter another ID.");
            Scanner inputID3 = new Scanner(System.in);
            id = inputID3.nextInt();
        }

        System.out.println("Please enter a burst length between 20 and 100:");
        Scanner inputBurst = new Scanner(System.in);
        int burst = inputBurst.nextInt();
        while(burst <20 || burst > 100){
            System.out.println("Input a burst in the proper range. The burst is out of range.");
            burst = inputBurst.nextInt();
        }

        System.out.println("Please enter a priority value between 1 and 10");
        Scanner inputPriorityValue = new Scanner(System.in);
        int priorityValue = inputPriorityValue.nextInt();
        while(priorityValue < 1 || priorityValue > 10){
            System.out.println("Priority Value is in an invalid range. Please input a priority value between 1 and 10.");
            priorityValue = inputPriorityValue.nextInt();
        }
        Process process = new Process(id, burst, priorityValue);
        processes.add(process);
        comp = new ProcessIDComparator();
        Collections.sort(processes, comp);

        for(int x = 0; x < processes.size(); x++){
            System.out.println("Process ID: " + String.valueOf(processes.get(x).getId()) + " | Priority: " + String.valueOf(processes.get(x).getPriority()) + " | Burst Length: " + String.valueOf(processes.get(x).getBurstLength()));
        }

        BurstComparator comp2 = new BurstComparator();
        Collections.sort(processes, comp2);
        int processesBurstCount = 0;
        System.out.println("These are the non-preemptive SJF Scheduling times.");
        for(int z = 0; z < processes.size(); z++){
            System.out.println("Process " + processes.get(z).getId() + " has a wait time of:" + processesBurstCount);
            processesBurstCount += processes.get(z).getBurstLength();
        }

        System.out.println("These are the Priority Scheduling times");
        PriorityComparator comp3 = new PriorityComparator();
        Collections.sort(processes, comp3);
        int processesBurstCountPriority = 0;
        for(int c = 0; c < processes.size(); c++){
            System.out.println("Processes " + processes.get(c).getId() + " has a wait time of " + processesBurstCountPriority);
            processesBurstCountPriority+= processes.get(c).getBurstLength();
        }
        System.out.println("These are the Round Robin Scheduling Times");
        int processesBurstCount3 = 0;
        HashMap<Process, Integer> remainingBurstTime = new HashMap<Process, Integer>();
        int totalBurstLength = 0;
        for(int b = 0; b <processes.size(); b++){
            remainingBurstTime.put(processes.get(b), processes.get(b).getBurstLength());
            totalBurstLength += processes.get(b).getBurstLength();
        }
        final int totalBurstLengthMax = totalBurstLength;
        int processIndicator = 0;
        boolean allProcessesFinished = false;
        while(!allProcessesFinished){
            int processTimeAfterTimeQuantum = remainingBurstTime.get(processes.get(processIndicator)) - 20;
            boolean processFinishedEarly = false;
            int difference = 20;
            int timeTakenAway = 0;
            if(processTimeAfterTimeQuantum < 0){
                processTimeAfterTimeQuantum = 0;
                difference = remainingBurstTime.get(processes.get(processIndicator))-20;
                remainingBurstTime.put(processes.get(processIndicator), totalBurstLengthMax-totalBurstLength);
                processFinishedEarly = true;
            }
            totalBurstLength-=difference;
            //System.out.println("Now running process " + processes.get(processIndicator).getId() + " burst time left on completion is " + processTimeAfterTimeQuantum + " next process is up.");
            remainingBurstTime.put(processes.get(processIndicator), processTimeAfterTimeQuantum);
            processIndicator++;
            totalBurstLength += processTimeAfterTimeQuantum;
            if(processIndicator >= remainingBurstTime.size()){
                processIndicator = 0;
            }

            if(remainingBurstTime.get(processes.get(processIndicator)) <= 0){
                remainingBurstTime.remove(processes.get(processIndicator));
            }
            int processFinishedCounter = 0;
            for(int i = 0; i <remainingBurstTime.size(); i++){
                if(remainingBurstTime.get(processes.get(i)) <=0){
                    processFinishedCounter++;
                }
            }
            if(processFinishedCounter == processes.size()){
                allProcessesFinished = true;
            }
            Iterator<Process> iter = remainingBurstTime.keySet().iterator();
             while(iter.hasNext()){
                 Process currentProcess = iter.next();
                 System.out.println("Process " + currentProcess.getId() + " | Priority: " + currentProcess.getPriority() + " | Burst-Length: " + currentProcess.getBurstLength() + " | Scheduling Algorithm: Round Robin | " + "Waiting Time: " + remainingBurstTime.get(currentProcess) );
             }

        }


    }
}
