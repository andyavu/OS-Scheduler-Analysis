//******************************************************************************
// Author      : Andy Vu
// Project     : Project 1
// Course      : CS 4310
// File        : CS4310Hw1.java
// Description : Simulates job scheduling algorithm and does performance analysis
//******************************************************************************

package cs4310hw1;

import java.util.*;

public class CS4310Hw1 
{
    public static boolean analysis = false;
    
    public static void main(String args[]) 
    {
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        while(loop)
        {
            System.out.print("1.) First-Come-First-Serve\n"
                           + "2.) Shortest-Job-First\n"
                           + "3.) Round-Robin\n"
                           + "4.) Performance analysis\n"
                           + "5.) Quit program\n"
                           + "Enter command: ");
            int scheduler = sc.nextInt();
            
            if(scheduler == 5)
            {
                break;
            }
            
            int slice = 0;
            if(scheduler == 3)
            {
                System.out.print("Enter time slice (2 or 5): ");
                slice = sc.nextInt();
            }
            
            System.out.print("Enter number of jobs (1 to 30): ");
            int jobs = sc.nextInt();
            
            switch(scheduler)
            {
                case 1:
                    FCFS fcfs = new FCFS(jobs);
                    fcfs.read();
                    fcfs.run();
                    fcfs.GanttChart();
                    System.out.println("Turnaround time: " + fcfs.getTurnaround() + "\n");
                    break;
                case 2:
                    SJF sjf = new SJF(jobs);
                    sjf.read();
                    sjf.run();
                    sjf.GanttChart();
                    System.out.println("Turnaround time: " + sjf.getTurnaround() + "\n");
                    break;
                case 3:
                    RR rr = new RR(jobs, slice);
                    rr.read();
                    rr.run();
                    rr.GanttChart();
                    System.out.println("Turnaround time: " + rr.getTurnaround() + "\n");
                    break;
                case 4:
                    analysis = true;
                    System.out.print("Enter number of trials: ");
                    int trials = sc.nextInt();
                    Analysis a = new Analysis(jobs, trials);
                    a.run();
                    a.analyze();
                    break;
                default:
                    System.out.println("\nInvalid input.\n");
            }
        }
    }
}
