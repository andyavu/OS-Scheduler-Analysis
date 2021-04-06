//******************************************************************************
// Author      : Andy Vu
// Project     : Project 1
// Course      : CS 4310
// File        : Scheduler.java
// Description : Interface for scheduling algorithms
//******************************************************************************

package cs4310hw1;

public interface Scheduler 
{
    public void read();             // reads n jobs from jobs.txt and stores info in appropriate data structure
    public void run();              // runs scheduling algorithm and calculates turnaround time
    public void printTable();       // prints scheudling table
    public double getTurnaround();  // returns turnaround time
}
