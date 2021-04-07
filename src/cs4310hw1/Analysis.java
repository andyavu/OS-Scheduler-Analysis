//******************************************************************************
// Author      : Andy Vu
// Project     : Project 1
// Course      : CS 4310
// File        : Analysis.java
// Description : Gernerates n random numbers and performs analysis for each 
//               scheduling algorithm
//******************************************************************************

package cs4310hw1;

import java.util.*;
import java.io.*;

public class Analysis 
{
    private ArrayList<Double> fcfsTimes;
    private ArrayList<Double> sjfTimes;
    private ArrayList<Double> rr2Times;
    private ArrayList<Double> rr5Times;
    private int numJobs;
    private int trials;
    
    public Analysis(int numJobs, int trials)
    {
        fcfsTimes = new ArrayList<>();
        sjfTimes = new ArrayList<>();
        rr2Times = new ArrayList<>();
        rr5Times = new ArrayList<>();
        this.numJobs = numJobs;
        this.trials = trials;
    }
    
    //**************************************************************************
    // Function : generate()
    // Purpose  : Generates n random runtimes and writes it into text file
    //**************************************************************************
    private void generate()
    {
        try
        {
            FileWriter fw = new FileWriter("jobs/job_analysis.txt");
            Random r = new Random();
            for(int i = 1; i <= numJobs; ++i)
            {
                fw.write("Job" + i + "\n");
                fw.write(Integer.toString(r.nextInt(20) + 1) + "\n");
            }
            fw.close();
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
            System.out.println("Analysis.generate()");
        }
    }
    
    //**************************************************************************
    // Function : run()
    // Purpose  : Performs X trials on X number of different datasets of n runtimes
    //**************************************************************************
    public void run()
    {
        for(int i = 0; i < trials; ++i)
        {
            generate();
            
            FCFS fcfs = new FCFS(numJobs);
            fcfs.read();
            fcfs.run();
            fcfsTimes.add(fcfs.getTurnaround());
            
            SJF sjf = new SJF(numJobs);
            sjf.read();
            sjf.run();
            sjfTimes.add(sjf.getTurnaround());
            
            RR rr2 = new RR(numJobs, 2);
            rr2.read();
            rr2.run();
            rr2Times.add(rr2.getTurnaround());
            
            RR rr5 = new RR(numJobs, 5);
            rr5.read();
            rr5.run();
            rr5Times.add(rr5.getTurnaround());
        }
    }
    
    //**************************************************************************
    // Function : analyze()
    // Purpose  : Calculates average turnaround time for each scheduling 
    //            algorithm and displays the results
    //**************************************************************************
    public void analyze()
    {
        double fcfsTA = 0;
        double sjfTA = 0;
        double rr2TA = 0;
        double rr5TA = 0;
        for(int i = 0; i < trials; ++i)
        {
            fcfsTA += fcfsTimes.get(i);
            sjfTA += sjfTimes.get(i);
            rr2TA += rr2Times.get(i);
            rr5TA += rr5Times.get(i);
        }
        fcfsTA /= trials;
        sjfTA /= trials;
        rr2TA /= trials;
        rr5TA /= trials;
        System.out.println("\nAverage turnaround times for " + numJobs + " jobs (" + trials + " trials):\n"
                         + "  FCFS: " + fcfsTA + "\n"
                         + "   SJF: " + sjfTA + "\n"
                         + "   RR2: " + rr2TA + "\n"
                         + "   RR5: " + rr5TA + "\n");
    }
}
