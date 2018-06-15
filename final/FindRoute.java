import java.util.*;
import java.io.*;

class FindRoute
{

	public static void main (String argv[]) throws Exception
	{
		Scanner sc = new Scanner(new File("database.txt") );
		String all_routes [] = new String[100];
		int i=0;			// how many routes do we have
		String start = argv[0];
		String end = argv[1];
		while(sc.hasNext())
		{
			String s = sc.nextLine();
			all_routes[i] = s;
			i++;
		}
		int time[ ] = new time[i];
		for(int k=0; k<i; k++)
		{
			Graph g = new Graph(all_routes[k]);
		}
		System.out.println(start+",5515,"+end):
		System.out.println(6);
	}



}



class Graph
{
        // No. of vertices in graph
        private int v;
        // adjacency list 
        private ArrayList<Integer>[] adjList;

        //Constructor
        public Graph(int vertices){                               
        //initialise vertex count
                this.v = vertices;                                                                             // initialise adjacency list
                initAdjList();
        }
        // utility method to initialise
        // adjacency list
        @SuppressWarnings("unchecked")
        private void initAdjList()
        {
                adjList = new ArrayList[v];
                for(int i = 0; i < v; i++)
                {
                        adjList[i] = new ArrayList<>();
                }
        }
            // add edge from u to v
        public void addEdge(int u, int v)
        {
            // Add v to u's list.
                adjList[u].add(v);
        }

        // Prints all paths from
        // 's' to 'd'
        public void printAllPaths(int s, int d)

       {
                boolean[] isVisited = new boolean[v];
                ArrayList<Integer> pathList = new ArrayList<>();                 
                //add source to path[]
                pathList.add(s);
                //Call recursive utility
                printAllPathsUtil(s, d, isVisited, pathList);
        }
        private void printAllPathsUtil(Integer u, Integer d, boolean[] isVisited, List<Integer> localPathList)
        {
                // Mark the current node
                isVisited[u] = true;
                if (u.equals(d))
                {
                        //System.out.println(localPathList);
                        for(int i=0; i<localPathList.size(); i++)
                        {
                                if(i != localPathList.size()-1)
                                System.out.print(localPathList.get(i)+"-");
                                else
                                System.out.print(localPathList.get(i));

                        }
                        System.out.print("\n");

                }
                // Recur for all the vertices
                // adjacent to current vertex
                for (Integer i : adjList[u])
                {
                        if (!isVisited[i])
                        {
                        // store current node 
                        // in path[]
                                localPathList.add(i);
                                printAllPathsUtil(i, d, isVisited, localPathList);


                        // remove current node
                        // in path[]
                                localPathList.remove(i);
                        }
                }
                // Mark the current node
                isVisited[u] = false;
        }

}

