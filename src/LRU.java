
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Vinicios
 */
public class LRU {
    public static int[] nums = {3,6,2,7,5,5,3,5,6,8,9,8,7,6,8,3,0,0,3,2,5,5,6,4,0,6,4,4,6,2,7,7,8,7,3,4,7,9,6,1,6,8,0,8,7,3,6,3,8,1,6,6,6,0,2,7,5,2,2,5,4,2,2,2,3,1,6,2,0,7,9,5,4,0,4,9,3,9,9,6,9,5,9,1,9,7,7,3,8,2,1,5,2,1,1,4,7,3,7,5};

    public static void main(String[] args) throws IOException 
    {
        bla(5, 100);
        bla(6, 100);
        bla(7, 100);
        bla(8, 100);
    }

    public static void bla(int frame, int len) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int frames,pointer = 0, hit = 0, fault = 0,ref_len;
        Boolean isFull = false;
        int buffer[];
        ArrayList<Integer> stack = new ArrayList<Integer>();
        int reference[];
        int mem_layout[][];
        
        frames = frame;
        
        ref_len = len;
        
        reference = new int[ref_len];
        mem_layout = new int[ref_len][frames];
        buffer = new int[frames];
        for(int j = 0; j < frames; j++)
                buffer[j] = -1;
        
        System.out.println("Please enter the reference string: ");
        reference = nums;
        System.out.println();
        for(int i = 0; i < ref_len; i++)
        {
            if(stack.contains(reference[i]))
            {
             stack.remove(stack.indexOf(reference[i]));
            }
            stack.add(reference[i]);
            int search = -1;
            for(int j = 0; j < frames; j++)
            {
                if(buffer[j] == reference[i])
                {
                    search = j;
                    hit++;
                    break;
                }
            }
            if(search == -1)
            {
             if(isFull)
             {
              int min_loc = ref_len;
                    for(int j = 0; j < frames; j++)
                    {
                     if(stack.contains(buffer[j]))
                        {
                            int temp = stack.indexOf(buffer[j]);
                            if(temp < min_loc)
                            {
                                min_loc = temp;
                                pointer = j;
                            }
                        }
                    }
             }
                buffer[pointer] = reference[i];
                fault++;
                pointer++;
                if(pointer == frames)
                {
                 pointer = 0;
                 isFull = true;
                }
            }
            for(int j = 0; j < frames; j++)
                mem_layout[i][j] = buffer[j];
        }
        
        for(int i = 0; i < frames; i++)
        {
            for(int j = 0; j < ref_len; j++)
                System.out.printf("%3d ",mem_layout[j][i]);
            System.out.println();
        }
        
        System.out.println("The number of Hits: " + hit);
        System.out.println("Hit Ratio: " + (float)((float)hit/ref_len));
        System.out.println("The number of Faults: " + fault);
    }
}