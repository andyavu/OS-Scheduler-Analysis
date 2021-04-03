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
    private HashMap<String, Integer> map;
    
    public SJF(int numJobs)
    {
        this.numJobs = numJobs;
        map = new HashMap<>();
        
        read();
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
            System.out.println(job + "\n"
                    + " start: " + time + "\n"
                    + "   end: " + (time + min));
            time += min;
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
            while(sc.hasNext())
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
}
