//******************************************************************************
// Author      : Andy Vu
// Project     : Project 1
// Course      : CS 4310
// File        : CS4310Hw1.java
// Description : Simulating Job Scheduler and Performance Analysis
//******************************************************************************

package cs4310hw1;

import java.util.*;

public class CS4310Hw1 
{
    public static void main(String args[]) 
    {
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        while(loop)
        {
            System.out.print("1.) First-Come-First-Serve\n"
                           + "2.) Shortest-Job-First\n"
                           + "3.) Round-Robin\n"
                           + "4.) Quit program\n"
                           + "Enter command: ");
            int scheduler = sc.nextInt();
            
            if(scheduler == 4)
            {
                break;
            }
            
            int slice = 0;
            if(scheduler == 3)
            {
                System.out.print("Enter time slice (2 or 5): ");
                slice = sc.nextInt();
            }
            
            System.out.print("Enter number of jobs (5, 10, or 15): ");
            int jobs = sc.nextInt();
            
            switch(scheduler)
            {
                case 1:
                    FCFS fcfs = new FCFS(jobs);
                    System.out.println();
                    System.out.println("Turnaround time: " + fcfs.run());
                    System.out.println();
                    break;
                case 2:
                    SJF sjf = new SJF(jobs);
                    System.out.println();
                    System.out.println("Turnaround time: " + sjf.run());
                    System.out.println();
                    break;
                case 3:
                    RR rr = new RR(jobs, slice);
                    System.out.println();
                    System.out.println("Turnaround time: " + rr.run());
                    System.out.println();
                    break;
                default:
                    System.out.println("\nInvalid input.\n");
            }
        }
    }
}
