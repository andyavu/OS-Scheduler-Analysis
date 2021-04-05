//******************************************************************************
// Author      : Andy Vu
// Project     : Project 1
// Course      : CS 4310
// File        : RR.java
// Description : Round-Robin
//******************************************************************************

package cs4310hw1;

import java.util.*;
import java.io.*;

public class RR implements Scheduler
{
    private int numJobs;
    private int slice;
    private ArrayList<String> jobs;
    private ArrayList<Integer> runtimes;
    
    public RR(int numJobs, int slice)
    {
        this.numJobs = numJobs;
        this.slice = slice;
        jobs = new ArrayList<>();
        runtimes = new ArrayList<>();
        
        read();
    }
    
    //**************************************************************************
    // Function : run()
    // Purpose  : Peforms RR scheduling algorithm and prints out start/end time
    //            for each job
    //**************************************************************************
    public double run()
    {
        int time = 0;
        ArrayList<Integer> stops = new ArrayList<>();
        while(!jobs.isEmpty())
        {
            for(int i = 0; i < jobs.size(); ++i)
            {
                if(runtimes.get(i) - slice > 0)
                {
                    System.out.println(jobs.get(i) + "\n"
                    + " start: " + time + "\n"
                    + "   end: " + (time + slice));
                    time += slice;
                    runtimes.set(i, runtimes.get(i) - slice);
                }
                else
                {
                    System.out.println(jobs.get(i) + "\n"
                    + " start: " + time + "\n"
                    + "   end: " + (time + runtimes.get(i)));
                    time += runtimes.get(i);
                    stops.add(time);
                    runtimes.set(i, 0);
                }
            }
            while(containsCompletedJob(runtimes))
            {
                if(getCompletedJob(runtimes) != -1)
                {
                    int index = getCompletedJob(runtimes);
                    jobs.remove(index);
                    runtimes.remove(index);
                }
            }
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
    // Function : containsCompletedJob()
    // Purpose  : Helper function to check if list contains any jobs that are 
    //            completed
    //**************************************************************************
    private boolean containsCompletedJob(ArrayList<Integer> runtimes)
    {
        for(int i = 0; i < runtimes.size(); ++i)
        {
            if(runtimes.get(i) == 0)
            {
                return true;
            }
        }
        return false;
    }
    
    //**************************************************************************
    // Function : getCompletedJob()
    // Purpose  : Helper function to get index of completed job
    //**************************************************************************
    private int getCompletedJob(ArrayList<Integer> runtimes)
    {
        for(int i = 0; i < runtimes.size(); ++i)
        {
            if(runtimes.get(i) == 0)
            {
                return i;
            }
        }
        return -1;
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
                int runtime = Integer.parseInt(sc.nextLine());
                runtimes.add(runtime);
                ++count;
            }
        }
        catch(Exception e)
        {
            System.out.println("SJF.run()");
            System.out.println(e.toString());
        }
    }
}
