//******************************************************************************
// Author      : Andy Vu
// Project     : Project 2
// Course      : CS 4310
// File        : Replacement.java
// Description : Interface for page replacement algorithms
//******************************************************************************

package cs4310hw2;

public interface Replacement 
{
    public void read();                     // reads reference string from .txt and stores info in appropriate data structures
    public void run();                      // runs page replacement algorithm
    public void printTable();               // prints page table
    public int getFaults();                 // returns page faults
    public void reformat();                 // reformats data for printing
    public boolean faultOccurs(int column); // checks columns to see if page fault occurs
}
