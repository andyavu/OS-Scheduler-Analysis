//******************************************************************************
// Author      : Andy Vu
// Project     : Project 1
// Course      : CS 4310
// File        : SJF.java
// Description : Shortest-Job-First (SJF)
//******************************************************************************

package cs4310hw1;

import java.util.*;
import java.io.*;

public class SJF implements Scheduler
{
    private int numJobs;
    private Hashtable<String, Integer> map;
    
    public SJF(int numJobs)
    {
        this.numJobs = numJobs;
        map = new Hashtable<>();
        
        read();
        System.out.println("Turnaround time: " + run() + "\n");
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
                int runTime = Integer.parseInt(sc.nextLine());
                map.put(job, runTime);
                ++count;
            }
        }
        catch(Exception e)
        {
            System.out.println("SJF.run()");
            System.out.println(e.toString());
        }
    }
    
    //**************************************************************************
    // Function : run()
    // Purpose  : Peforms SJF scheduling algorithm and prints out start/end time
    //            for each job
    //**************************************************************************
    public double run()
    {
        int time = 0;
        ArrayList<Integer> stops = new ArrayList<>();
        ArrayList<String> jobs = new ArrayList<>();
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
        double turnAround = total / numJobs;
        printTable(stops, jobs);
        return turnAround;
    }
    
    //**************************************************************************
    // Function : printTable()
    // Purpose  : Helper function to print out scheduling table
    //**************************************************************************
    private void printTable(ArrayList<Integer> stops, ArrayList<String> jobs)
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
}
