//******************************************************************************
// Author      : Andy Vu
// Project     : Project 2
// Course      : CS 4310
// File        : Manager.java
// Description : Interface for page replacement algorithms
//******************************************************************************

package cs4310hw2;

public interface Manager 
{
    public void read();         // reads n reference string from ReferenceString.txt and stores info in appropriate data structure
    public void run();          // runs page replacement algorithm and returns number of page faults
    public void printTable();   // prints page chart
    public int getFaults();     // returns page faults
}
