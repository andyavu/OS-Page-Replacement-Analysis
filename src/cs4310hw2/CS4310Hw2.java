//******************************************************************************
// Author      : Andy Vu
// Project     : Project 2
// Course      : CS 4310
// File        : CS4310Hw2.java
// Description : Simulates page replacement algorithms and does performance 
//               analysis
//******************************************************************************

package cs4310hw2;

import java.util.*;

public class CS4310Hw2 
{
    static boolean analysis = false;
    
    public static void main(String args[]) 
    {
        int frames = 0;
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        while(loop)
        {
            System.out.print("1.) First In First Out\n"
                           + "2.) Least Recently Used\n"
                           + "3.) Optimal Algorithm\n"
                           + "4.) Perform analysis\n"
                           + "5.) Quit program\n"
                           + "Enter command: ");
            int command = sc.nextInt();
            switch(command)
            {
                case 1:
                    System.out.print("\nEnter number of page frames: ");
                    frames = sc.nextInt();
                    FIFO fifo = new FIFO(frames);
                    fifo.read();
                    fifo.run();
                    fifo.printTable();
                    System.out.println("Page Faults: " + fifo.getFaults() + "\n");
                    break;
                case 2:
                    System.out.print("\nEnter number of page frames: ");
                    frames = sc.nextInt();
                    LRU lru = new LRU(frames);
                    lru.read();
                    lru.run();
                    lru.printTable();
                    System.out.println("Page Faults: " + lru.getFaults() + "\n");
                    break;
                case 3:
                    System.out.print("\nEnter number of page frames: ");
                    frames = sc.nextInt();
                    Optimal opt = new Optimal(frames);
                    opt.read();
                    opt.run();
                    opt.printTable();
                    System.out.println("Page Faults: " + opt.getFaults() + "\n");
                    break;
                case 4:
                    analysis = true;
                    System.out.print("\nEnter number of trials: ");
                    int trials = sc.nextInt();
                    System.out.print("Enter reference string length: ");
                    int length = sc.nextInt();
                    System.out.print("Enter number of pages: ");
                    int pages = sc.nextInt();
                    System.out.print("Enter number of page frames: ");
                    frames = sc.nextInt();
                    Analysis a = new Analysis(trials, length, pages, frames);
                    a.run();
                    a.analyze();
                    break;
                case 5:
                    loop = false;
                    break;
                default:
                    System.out.println("\nInvalid input.\n");
            }
        }
    }
}
