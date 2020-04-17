/*
    Program: Implementation of UNION-FIND Algorithm using path-compressed weighted trees
*/
import java.io.*;
import java.util.*;

public class union_find
{
    private int id[];
    private int sz[];

    union_find(int N)
    {
        N++;
        sz = new int[N];
        id = new int[N];
        for(int i=0; i<N; i++)
        {
            id[i] = i;
            sz[i] = 1;
        }
    }

    private int root(int i)
    {
        while(id[i] != i)
        {    
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    void union(int p, int q)
    {
        int i = root(p);
        int j = root(q);
        if(i == j)
            return;
        if(sz[i] < sz[j])
        {
            id[i] = j;
            sz[j] += sz[i];
        }
        else
        {
            id[j] = id[i];
            sz[i] += sz[j]; 
        }
    }

    boolean connected(int p, int q)
    {
        return root(p) == root(q);
    }

    public static void main(String args[])
    {
        try{
        System.setIn(new FileInputStream(new File("union_find_input.txt")));
        System.setOut(new PrintStream(new FileOutputStream("union_find_output.txt")));
        }catch(FileNotFoundException fe)
        { System.out.println("Error"); }
        Scanner s = new Scanner(System.in);
        System.out.println("Enter number of objects: ");
        int N = s.nextInt();
        union_find uf = new union_find(N);
        System.out.println("Enter number of operations: ");
        int nops = s.nextInt();
        for(int i=0; i<nops; i++)
        {
            int p = s.nextInt();
            int q = s.nextInt();
            if(!uf.connected(p, q))
            {
                uf.union(p, q);
                System.out.println("Connected " + p +" and " + q);
            } 
        }
        System.out.println("Checking for 8 and 9 " + uf.connected(8, 9));
        System.out.println("Checking for 1 and 0 " + uf.connected(1, 0));
        System.out.println("Checking for 6 and 7 " + uf.connected(6, 7));
        System.out.println("Checking for 9 and 6 " + uf.connected(9, 6));
    } 
}