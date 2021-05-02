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
    public void read();         // reads n reference string from ReferenceString.txt and stores info in appropriate data structures
    public void run();          // runs page replacement algorithm
    public void printTable();   // prints page chart
    public int getFaults();     // returns page faults
}
