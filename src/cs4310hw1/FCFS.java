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
            time += runTimes.get(i);
            stops.add(time);
        }
        
        double total = 0;
        for(int i = 0; i < stops.size(); ++i)
        {
            total += stops.get(i);
        }
        double turnAround = total / numJobs;
        printTable(stops);
        return turnAround;
    }
    
    //**************************************************************************
    // Function : printTable()
    // Purpose  : Helper function to print out scheduling table
    //**************************************************************************
    private void printTable(ArrayList<Integer> stops)
    {
        for(int i = 0; i < stops.size(); ++i)
        {
            if(i < stops.size() - 1)
            {
                System.out.print("+-------");
            }
            else
            {
                System.out.println("+-------+");
            }
        }
        for(int i = 0; i < jobs.size(); ++i)
        {
            if(i < jobs.size() - 1)
            {
                System.out.printf("|%6s%1s", jobs.get(i), "");
            }
            else
            {
                System.out.printf("|%6s%1s|\n", jobs.get(i), "");
            }
        }
        for(int i = 0; i < stops.size(); ++i)
        {
            if(i < stops.size() - 1)
            {
                System.out.print("+-------");
            }
            else
            {
                System.out.println("+-------+");
            }
        }
        System.out.print("0");
        for(int i = 0; i < stops.size(); ++i)
        {
            if(i < stops.size() - 1)
            {
                System.out.printf("%8s", stops.get(i));
            }
            else
            {
                System.out.printf("%8s\n", stops.get(i));
            }
        }
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
