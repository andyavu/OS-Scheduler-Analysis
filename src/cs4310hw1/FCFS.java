//******************************************************************************
// Author      : Andy Vu
// Project     : Project 1
// Course      : CS 4310
// File        : FCFS.java
// Description : First-Come-FIrst-Serve
//******************************************************************************

package cs4310hw1;

import java.util.*;
import java.io.*;

public class FCFS implements Scheduler
{
    private int numJobs;
    private ArrayList<String> jobs;
    private ArrayList<Integer> runtimes;
    private ArrayList<Integer> stops;
    private double turnaround;
    
    public FCFS(int numJobs)
    {
        this.numJobs = numJobs;
        jobs = new ArrayList<>();
        runtimes = new ArrayList<>();
        stops = new ArrayList<>();
        turnaround = 0;
    }
    
    //**************************************************************************
    // Function : read()
    // Purpose  : Reads data from jobs file based on the number of jobs the user 
    //            has selected
    //**************************************************************************
    @Override
    public void read()
    {
        try
        {
            Scanner sc;
            if(CS4310Hw1.analysis)
            {
                sc = new Scanner(new File("jobs/jobs_analysis.txt"));
            }
            else
            {
                sc = new Scanner(new File("jobs/jobs.txt"));
            }
            int count = 0;
            while(sc.hasNext() && count < numJobs)
            {
                String job = sc.nextLine();
                jobs.add(job);
                int runtime = Integer.parseInt(sc.nextLine());
                runtimes.add(runtime);
                ++count;
            }
            sc.close();
        }
        catch(Exception e)
        {
            System.out.println("FCFS.run()");
            System.out.println(e.toString());
        }
    }
    
    //**************************************************************************
    // Function : run()
    // Purpose  : Peforms FCFS scheduling algorithm and calculates turnaround time
    //**************************************************************************
    @Override
    public void run()
    {
        int time = 0;
        for(int i = 0; i < runtimes.size(); ++i)
        {
            time += runtimes.get(i);
            stops.add(time);
        }
        
        double total = 0;
        for(int i = 0; i < stops.size(); ++i)
        {
            total += stops.get(i);
        }
        turnaround = total / numJobs;
    }
    
    //**************************************************************************
    // Function : printTable()
    // Purpose  : Prints out formatted scheduling table with jobs and times
    //**************************************************************************
    @Override
    public void printTable()
    {
        System.out.println();
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
    // Function : getTurnaround()
    // Purpose  : Returns turnaround time
    //**************************************************************************
    @Override
    public double getTurnaround()
    {
        return turnaround;
    }
}
