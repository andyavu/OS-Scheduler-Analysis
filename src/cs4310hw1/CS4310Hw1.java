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
            System.out.print("1.) First-Come-First-Serve (FCFS)\n"
                           + "2.) Shortest-Job-First (SJF)\n"
                           + "3.) Round-Robin with Time Slice = 2 (RR-2)\n"
                           + "4.) Round-Robin with Time Slice = 5 (PR-5)\n"
                           + "5.) Quit program\n"
                           + "Enter command: ");
            int scheduler = sc.nextInt();
            System.out.println();
            if(scheduler == 5)
            {
                break;
            }
            System.out.print("Enter number of jobs (5, 10, 15): ");
            int jobs = sc.nextInt();
            System.out.println();

            switch(scheduler)
            {
                case 1:
                    FCFS fcfs = new FCFS(jobs);
                    System.out.println("Turnaround time: " + fcfs.run());
                    System.out.println();
                    break;
                case 2:
                    SJF sjf = new SJF(jobs);
                    System.out.println("Turnaround time: " + sjf.run());
                    System.out.println();
                    break;
                case 3:
                    RR rr2 = new RR(jobs, 2);
                    System.out.println("Turnaround time: " + rr2.run());
                    System.out.println();
                    break;
                case 4:
                    RR rr5 = new RR(jobs, 5);
                    System.out.println("Turnaround time: " + rr5.run());
                    System.out.println();
                    break;
                default:
                    System.out.println("Invalid input.\n");
            }
        }
    }
}
