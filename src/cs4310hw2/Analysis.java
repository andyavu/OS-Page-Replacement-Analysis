//******************************************************************************
// Author      : Andy Vu
// Project     : Project 2
// Course      : CS 4310
// File        : Analysis.java
// Description : Gernerates a number of reference strings and performs analysis 
//               for each page replacement algorithm
//******************************************************************************

package cs4310hw2;

import java.util.*;
import java.io.*;

public class Analysis 
{
    private ArrayList<Integer> fifoFaults;
    private ArrayList<Integer> lruFaults;
    private ArrayList<Integer> optFaults;
    private int trials;
    private int pages;
    private int frames;
    private int length;
    
    public Analysis(int trials, int length, int frames, int pages)
    {
        fifoFaults = new ArrayList();
        lruFaults = new ArrayList();
        optFaults = new ArrayList();
        this.trials = trials;
        this.length = length;
        this.frames = frames;
        this.pages = pages;
    }
    
    //**************************************************************************
    // Function : generate()
    // Purpose  : Generates random reference string given a number of pages and 
    //            length. First line contains the number of page frames and 
    //            second line contains the reference string
    //**************************************************************************
    private void generate()
    {
        try
        {
            FileWriter fw = new FileWriter("reference strings/ReferenceStringAnalysis.txt");
            fw.write(frames + "\n");
            Random r = new Random();
            for(int i = 0; i < length; ++i)
            {
                fw.write(Integer.toString(r.nextInt(pages)));
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
    // Purpose  : Perform X trials and calculate the average page faults for 
    //            each algorithm. A trial consists of generating reference 
    //            string and running it through each algorithm
    //**************************************************************************
    public void run()
    {    
        for(int i = 0; i < trials; ++i)
        {
            generate();
            
            FIFO fifo = new FIFO();
            fifo.read();
            fifo.run();
            fifoFaults.add(fifo.getFaults());
            
            LRU lru = new LRU();
            lru.read();
            lru.run();
            lruFaults.add(lru.getFaults());
            
            Optimal opt = new Optimal();
            opt.read();
            opt.run();
            optFaults.add(opt.getFaults());
        }
    }
    
    //**************************************************************************
    // Function : analyze()
    // Purpose  : Calculates average page faults for each algorithm and displays 
    //            the results
    //**************************************************************************
    public void analyze()
    {
        double fifoAvg = 0;
        double lruAvg = 0;
        double optAvg = 0;
        for(int i = 0; i < trials; ++i)
        {
            fifoAvg += fifoFaults.get(i);
            lruAvg += lruFaults.get(i);
            optAvg += optFaults.get(i);
        }
        fifoAvg /= trials;
        lruAvg /= trials;
        optAvg /= trials;
        System.out.println("\nAverage page faults for " + frames + " page frames and " + pages + " pages:\n"
                         + "   FIFO: " + fifoAvg + "\n"
                         + "    LRU: " + lruAvg + "\n"
                         + "Optimal: " + optAvg + "\n");
    }
}
