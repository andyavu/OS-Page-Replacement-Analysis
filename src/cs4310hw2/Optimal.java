//******************************************************************************
// Author      : Andy Vu
// Project     : Project 2
// Course      : CS 4310
// File        : Optimal.java
// Description : Optimal page replacement algorithm
//******************************************************************************

package cs4310hw2;

import java.util.*;
import java.io.*;

public class Optimal implements Replacement
{
    private ArrayList<ArrayList<Integer>> table;
    private ArrayList<Integer> ref;
    private ArrayList<Integer> refCopy;
    private HashMap<Integer, Integer> map;
    private int faults;
    private int frames;
    
    public Optimal(int frames)
    {
        table = new ArrayList();
        ref = new ArrayList();
        refCopy = new ArrayList();
        map = new HashMap();
        faults = 0;
        this.frames = frames;
    }
    
    //**************************************************************************
    // Function : read()
    // Purpose  : Reads data from reference string file. The first line contains 
    //            number of page frames and the second line contains the 
    //            reference string
    //**************************************************************************
    @Override
    public void read()
    {
        try
        {
            Scanner sc;
            if(CS4310Hw2.analysis)
            {
                sc = new Scanner(new File("reference strings/ReferenceStringAnalysis.txt"));
            }
            else
            {
                sc = new Scanner(new File("reference strings/ReferenceStringImport.txt"));
                frames = Integer.parseInt(sc.nextLine());
            }
            while(sc.hasNext())
            {
                String str = sc.nextLine();
                for(int i = 0; i < str.length(); ++i)
                {
                    String num = Character.toString(str.charAt(i));
                    ref.add(Integer.parseInt(num));
                    refCopy.add(Integer.parseInt(num));
                }
            }
            for(int i = 0; i < frames; ++i)
            {
                ArrayList<Integer> temp = new ArrayList();
                for(int j = 0; j < ref.size(); ++j)
                {
                    temp.add(-1);
                }
                table.add(temp);
            }
            sc.close();
        }
        catch(Exception e)
        {
            System.out.println("Optimal.read()");
            System.out.println(e.toString());
        }
    }
    
    //**************************************************************************
    // Function : run()
    // Purpose  : Peforms Optimal page replacement algorithm
    //**************************************************************************
    @Override
    public void run()
    {
        int curr = 0;
        int last = 0;
        for(int i = 0; i < ref.size(); ++i)
        {
            refCopy.remove(ref.get(i));
            if(curr == frames)
            {
                curr = 0;
            }
            if(!map.containsKey(ref.get(i)))
            {
                if(map.size() == frames)
                {
                    for(Map.Entry<Integer, Integer> e : map.entrySet())
                    {
                        map.put(e.getKey(), ref.size());
                    }
                    for(Map.Entry<Integer, Integer> e : map.entrySet())
                    {
                        for(int j = refCopy.size() - 1; j >= 0; --j)
                        {
                            if(e.getKey() == refCopy.get(j))
                            {
                                map.put(e.getKey(), j);
                            }
                        }
                    }
                    Map.Entry<Integer, Integer> max = null;
                    for(Map.Entry<Integer, Integer> e : map.entrySet())
                    {
                        if(max == null)
                        {
                            max = e;
                        }
                        else if(e.getValue() >= max.getValue())
                        {
                            if(e.getValue() > max.getValue())
                            {
                                max = e;
                            }
                            else
                            {
                                if(e.getKey() == last)
                                {
                                    max = e;
                                    break;
                                }
                            }
                        }
                    }
                    for(int j = 0; j < ref.size(); ++j)
                    {
                        for(int k = 0; k < frames; ++k)
                        {
                            if(table.get(k).get(j) == max.getKey())
                            {
                                curr = k;
                            }
                        }
                    }
                    map.remove(max.getKey());
                }
                map.put(ref.get(i), ref.size());
                table.get(curr).set(i, ref.get(i));
                ++faults;
                ++curr;
            }
            last = ref.get(i);
        }
    }
    
    //**************************************************************************
    // Function : printTable()
    // Purpose  : Prints formatted table that includes the reference string, 
    //            what pages are inside each page frame, and when a page fault 
    //            occurs (marked with an X)
    //**************************************************************************
    @Override
    public void printTable()
    {
        ArrayList<Integer> hits = new ArrayList();
        for(int i = 0; i < ref.size(); ++i)
        {
            if(faultOccurs(i))
            {
                hits.add(i);
            }
            System.out.print(" " + ref.get(i) + " ");
        }
        System.out.println();
        reformat();
        for(int i = 0; i < ref.size(); ++i)
        {
            System.out.print("---");
        }
        System.out.println();
        for(int i = 0; i < frames; ++i)
        {
            for(int j = 0; j < table.get(i).size(); ++j)
            {
                if(table.get(i).get(j) == -1)
                {
                    System.out.print("   ");
                }
                else
                {
                    System.out.print(" " + table.get(i).get(j) + " ");
                }
            }
            System.out.println();
        }
        for(int i = 0; i < ref.size(); ++i)
        {
            System.out.print("---");
        }
        System.out.println();
        for(int i = 0; i < ref.size(); ++i)
        {
            if(!hits.isEmpty())
            {
                if(i == hits.get(0))
                {
                    System.out.print(" X ");
                    hits.remove(0);
                }
                else
                {
                    System.out.print("   ");
                }
            }
        }
        System.out.println();
    }
    
    //**************************************************************************
    // Function : getFaults()
    // Purpose  : Returns total number of page faults
    //**************************************************************************
    @Override
    public int getFaults()
    {
        return faults;
    }

    //**************************************************************************
    // Function : reformat()
    // Purpose  : Helper function to reformat numbers for printing
    //**************************************************************************
    @Override
    public void reformat()
    {
        for(int i = 0; i < frames; ++i)
        {
            int num = table.get(i).get(i);
            for(int j = i + 1; j < ref.size(); ++j)
            {
                if(table.get(i).get(j) == -1)
                {
                    table.get(i).set(j, num);
                }
                else
                {
                    num = table.get(i).get(j);
                }
            }
        }
    }
    
    //**************************************************************************
    // Function : faultOccurs()
    // Purpose  : Helper function to check if page fault occurs. If entire 
    //            colummn is -1 return false, else return true
    //**************************************************************************
    @Override
    public boolean faultOccurs(int column)
    {
        for(int i = 0; i < frames; ++i)
        {
            if(table.get(i).get(column) != -1)
            {
                return true;
            }
        }
        return false;
    }
}