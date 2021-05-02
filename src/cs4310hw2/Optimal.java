//******************************************************************************
// Author      : Andy Vu
// Project     : Project 2
// Course      : CS 4310
// File        : Optimal.java
// Description : Optimal algorithm
//******************************************************************************

package cs4310hw2;

public class Optimal implements Replacement
{
    public Optimal()
    {}
    
    //**************************************************************************
    // Function : read()
    // Purpose  : Reads data from reference string file. The first line contains 
    //            the total page frames and the second line contains the 
    //            reference string
    //**************************************************************************
    @Override
    public void read()
    {}
    
    //**************************************************************************
    // Function : run()
    // Purpose  : Peforms LRU page replacement algorithm
    //**************************************************************************
    @Override
    public void run()
    {}
    
    //**************************************************************************
    // Function : printTable()
    // Purpose  : Prints formatted table that includes the reference string, 
    //            what pages are inside each page frame, when a page fault 
    //            occurs (marked with an X)
    //**************************************************************************
    @Override
    public void printTable()
    {}
    
    //**************************************************************************
    // Function : getFaults()
    // Purpose  : Returns total number of page faults
    //**************************************************************************
    @Override
    public int getFaults()
    {
        return 0;
    }

    //**************************************************************************
    // Function : reformat()
    // Purpose  : Helper function to reformat numbers to in preparation for 
    //            printing
    //**************************************************************************
    public void reformat()
    {}
    
    //**************************************************************************
    // Function : faultOccurs()
    // Purpose  : Helper function to check if page fault occurs. If entire 
    //            colummn is -1 return false, else return true
    //**************************************************************************
    public boolean faultOccurs(int column)
    {
        return false;
    }
}
