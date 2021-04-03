//******************************************************************************
// Author      : Andy Vu
// Project     : Project 1
// Course      : CS 4310
// File        : FCFS.java
// Description : First-Come-FIrst-Serve (FCFS)
//******************************************************************************

package cs4310hw1;

import java.util.*;
import java.io.*;

public class FCFS implements Scheduler
{
    private int numJobs;
    private ArrayList<String> jobs;
    private ArrayList<Integer> runTimes;
    
    public FCFS(int numJobs)
    {
        this.numJobs = numJobs;
        jobs = new ArrayList<>();
        runTimes = new ArrayList<>();
        
        read();
    }
    
    //**************************************************************************
    // Function : run()
    // Purpose  : Peforms FCFS scheduling algorithm and prints out start/end 
    //            time for each job
    //**************************************************************************
    public double run()
    {
        int time = 0;
        ArrayList<Integer> stops = new ArrayList<>();
        for(int i = 0; i < jobs.size(); ++i)
        {
            System.out.println(jobs.get(i) + "\n"
                    + " start: " + time + "\n"
                    + "   end: " + (time + runTimes.get(i)));
            time += runTimes.get(i);
            stops.add(time);
        }
        double total = 0;
        for(int i = 0; i < stops.size(); ++i)
        {
            total += stops.get(i);
        }
        double turnAround = total / numJobs;
        return turnAround;
    }
    
    //**************************************************************************
    // Function : read()
    // Purpose  : Reads data from jobs file based on the number of jobs the user 
    //            has selected
    //**************************************************************************
    public void read()
    {
        try
        {
            Scanner sc = new Scanner(new File("jobs/jobs.txt"));
            int count = 0;
            while(sc.hasNext() && count < numJobs)
            {
                String job = sc.nextLine();
                jobs.add(job);
                int runTime = Integer.parseInt(sc.nextLine());
                runTimes.add(runTime);
                ++count;
            }
        }
        catch(Exception e)
        {
            System.out.println("FCFS.run()");
            System.out.println(e.toString());
        }
    }
}
