/* COP3503 - CS II
 * Lab 01 - Prim’s algorithm
 * Submitted by:
 * 		Matthew Weinert
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Lab01 
{   
    public static void main(String[] args)
    {
        File file = new File(args[0]);
        int vert = 0;
        int current = 0;
        
        try {
           
            BufferedReader br = new BufferedReader(new FileReader(file));
                      
            Scanner scanner = new Scanner(file);

			String line1 = scanner.nextLine();
			
			vert = Integer.parseInt(line1);

	        Graph graph = new Graph(vert);

	        int a = 0, b = 0, c = 0;
	        float d = 0;

            while(scanner.hasNext())
            {
            	current++;
            	
				String data = scanner.nextLine();
			
				String[] line = data.split(" "); 

				if(current >= 2)
				{
					a = Integer.parseInt(line[0]);
					b = Integer.parseInt(line[1]);
					d = Float.parseFloat(line[2]);
					c = (int) (d * 10000000);
					graph.e(a, b, c);
				}
            }
            
            graph.prim();
            
            br.close();
            
            scanner.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Graph
{
    int vert, adj[][];

    public Graph(int v)
    {
        this.vert = v;
        adj = new int[v][v];
    }
    
    class R
    {
        int parent, weight;
    }
    
    public void print(R[] re)
    {
        double total = 0;
        
        for (int i = 1; i < vert ; i++)
        {
            System.out.print(re[i].parent + "-" + i + " ");
            
            System.out.printf("%.5f\n", total/10000000);
            
            total += re[i].weight;
        }
        
        System.out.printf("%.5f", total/10000000);
	System.out.printf("\n");
    }
    
    public void e(int x, int y, int w)
    {
    	adj[x][y]= w; adj[y][x] = w;
    }

    int min(int[] key, boolean[] tree)
    {
        int min = Integer.MAX_VALUE, vertex = -1;
                
        for (int i = 0; i < vert ; i++)
        {
            if(tree[i] == false && min > key[i])
            {
                min = key[i];
                vertex = i;
            }
        }
        
        return vertex;
    }

    public void prim()
    {
        boolean[] mst = new boolean[vert];
        
        R[] r = new R[vert];
        
        int [] key = new int[vert];

        for (int i = 0; i < vert ; i++)
        {
            key[i] = Integer.MAX_VALUE;
            
            r[i] = new R();
        }

        key[0] = 0;
        r[0] = new R(); r[0].parent = -1;

        for (int i = 0; i < vert ; i++)
        {
            int vertex = min(key, mst);

            mst[vertex] = true;

            for (int j = 0; j < vert ; j++)
            {
                if(adj[vertex][j]>0){
                	
                    if(mst[j] == false && adj[vertex][j]<key[j])
                    {
                    	
                        key[j] = adj[vertex][j];

                        r[j].parent = vertex;
                        r[j].weight = key[j];
                    }
                }
            }
        }
        
        print(r);
    }
}
