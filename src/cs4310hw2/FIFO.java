//******************************************************************************
// Author      : Andy Vu
// Project     : Project 2
// Course      : CS 4310
// File        : FIFO.java
// Description : First In First Out algorithm
//******************************************************************************

package cs4310hw2;

import java.util.*;
import java.io.*;

public class FIFO implements Replacement
{
    private ArrayList<ArrayList<Integer>> arr;
    private ArrayList<Integer> ref;
    private HashMap<Integer, Integer> map;
    private int faults;
    private int frames;
    
    
    public FIFO()
    {
        arr = new ArrayList();
        ref = new ArrayList();
        map = new HashMap();
        faults = 0;
        frames = 0;
    }
    
    //**************************************************************************
    // Function : read()
    // Purpose  : Reads data from reference string file
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
//                sc = new Scanner(new File("reference strings/ReferenceString.txt"));
                sc = new Scanner(new File("reference strings/test.txt"));
            }
            while(sc.hasNext())
            {
                String str = sc.nextLine();
                if(str.length() == 1)
                {
                    frames = Integer.parseInt(str);
                }
                else
                {
                    for(int i = 0; i < str.length(); ++i)
                    {
                        String num = Character.toString(str.charAt(i));
                        ref.add(Integer.parseInt(num));
                    }
                    for(int i = 0; i < frames; ++i)
                    {
                        ArrayList<Integer> temp = new ArrayList();
                        for(int j = 0; j < str.length(); ++j)
                        {
                            temp.add(-1);
                        }
                        arr.add(temp);
                    }
                }
            }
            sc.close();
        }
        catch(Exception e)
        {
            System.out.println("FIFO.read()");
            System.out.println(e.toString());
        }
    }
    
    //**************************************************************************
    // Function : run()
    // Purpose  : Peforms FIFO page replacement algorithm
    //**************************************************************************
    @Override
    public void run()
    {
        int curr = 0;
        for(int i = 0; i < ref.size(); ++i)
        {
            if(curr == frames)
            {
                curr = 0;
            }
            if(!map.containsKey(ref.get(i)))
            {
                if(map.size() == frames)
                {
                    Map.Entry<Integer, Integer> max = null;
                    for(Map.Entry<Integer, Integer> e : map.entrySet())
                    {
                        if(max == null || e.getValue().compareTo(max.getValue()) > 0)
                        {
                            max = e;
                        }
                    }
                    for(int j = 0; j < frames; ++j)
                    {
                        if(arr.get(j).get(i) == max.getKey())
                        {
                            curr = j;
                            break;
                        }
                    }
                    map.remove(max.getKey());
                }
                map.put(ref.get(i), 0);
                for(Map.Entry<Integer, Integer> e : map.entrySet())
                {
                    map.put(e.getKey(), e.getValue() + 1);
                }
                arr.get(curr).set(i, ref.get(i));
                ++faults;
                ++curr;
            }
            else
            {
                for(Map.Entry<Integer, Integer> e : map.entrySet())
                {
                    map.put(e.getKey(), e.getValue() + 1);
                }
            }
        }
    }
    
    //**************************************************************************
    // Function : printTable()
    // Purpose  : Prints formatted table that includes the reference string, 
    //            what pages are inside each page frame, when a page fault 
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
            System.out.print("  " + ref.get(i) + "  ");
        }
        System.out.println();
        reformat();
        for(int i = 0; i < ref.size(); ++i)
        {
            System.out.print("-----");
        }
        System.out.println();
        for(int i = 0; i < frames; ++i)
        {
            for(int j = 0; j < arr.get(i).size(); ++j)
            {
                if(arr.get(i).get(j) == -1)
                {
                    System.out.print("     ");
                }
                else
                {
                    System.out.print("  " + arr.get(i).get(j) + "  ");
                }
            }
            System.out.println();
        }
        for(int i = 0; i < ref.size(); ++i)
        {
            System.out.print("-----");
        }
        System.out.println();
        for(int i = 0; i < ref.size(); ++i)
        {
            if(!hits.isEmpty())
            {
                if(i == hits.get(0))
                {
                    System.out.print("  X  ");
                    hits.remove(0);
                }
                else
                {
                    System.out.print("     ");
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
    // Purpose  : Helper function to reformat numbers to in preparation for 
    //            printing
    //**************************************************************************
    private void reformat()
    {
        for(int i = 0; i < frames; ++i)
        {
            int num = arr.get(i).get(i);
            for(int j = i + 1; j < ref.size(); ++j)
            {
                if(arr.get(i).get(j) == -1)
                {
                    arr.get(i).set(j, num);
                }
                else
                {
                    num = arr.get(i).get(j);
                }
            }
        }
    }
    
    //**************************************************************************
    // Function : faultOccurs()
    // Purpose  : Helper function to check if page fault occurs. If entire 
    //            colummn is -1 return false, else return true
    //**************************************************************************
    private boolean faultOccurs(int column)
    {
        for(int i = 0; i < frames; ++i)
        {
            if(arr.get(i).get(column) != -1)
            {
                return true;
            }
        }
        return false;
    }
}
