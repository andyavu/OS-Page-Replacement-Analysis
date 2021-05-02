//******************************************************************************
// Author      : Andy Vu
// Project     : Project 2
// Course      : CS 4310
// File        : CS4310Hw2.java
// Description : Simulates page replacement manager and does performance analysis
//******************************************************************************

package cs4310hw2;

public class CS4310Hw2 
{
    static boolean analysis = false;
    
    public static void main(String args[]) 
    {
        FIFO fifo = new FIFO();
        fifo.read();
        fifo.run();
        fifo.printTable();
        System.out.println("Page Faults: " + fifo.getFaults());
    }
}
