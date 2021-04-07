//******************************************************************************
// Author      : Andy Vu
// Project     : Project 1
// Course      : CS 4310
// File        : SJF.java
// Description : Shortest-Job-First
//******************************************************************************

package cs4310hw1;

import java.util.*;
import java.io.*;

public class SJF implements Scheduler
{
    private int numJobs;
    private HashMap<String, Integer> map;
    private ArrayList<Integer> stops;
    private ArrayList<String> jobs;
    private double turnaround;
    
    public SJF(int numJobs)
    {
        this.numJobs = numJobs;
        map = new HashMap<>();
        stops = new ArrayList<>();
        jobs = new ArrayList<>();
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
                sc = new Scanner(new File("jobs/job_analysis.txt"));
            }
            else
            {
                sc = new Scanner(new File("jobs/job.txt"));
            }
            int count = 0;
            while(sc.hasNext() && count < numJobs)
            {
                String job = sc.nextLine();
                int runTime = Integer.parseInt(sc.nextLine());
                map.put(job, runTime);
                ++count;
            }
            sc.close();
        }
        catch(Exception e)
        {
            System.out.println("SJF.run()");
            System.out.println(e.toString());
        }
    }
    
    //**************************************************************************
    // Function : run()
    // Purpose  : Peforms SJF scheduling algorithm and calculates turnaround time
    //**************************************************************************
    @Override
    public void run()
    {
        int time = 0;
        while(!map.isEmpty())
        {
            int min = map.entrySet().stream().min(Map.Entry.comparingByValue()).get().getValue();
            String job = "";
            for(String k : map.keySet())
            {
                if(map.get(k) == min)
                {
                    job = k;
                    break;
                }
            }
            map.remove(job);
            jobs.add(job);
            time += min;
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
    // Function : GanttChart()
    // Purpose  : Prints out Gantt chart with jobs and times
    //**************************************************************************
    @Override
    public void GanttChart()
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
