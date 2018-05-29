import java.util.*;
import java.io.*;
class Maze
{
	public static void main (String args[])
	{
		if(args.length < 2)
		{
			System.out.println("Usage: java Maze input.txt output.txt");
		}
		File file = new File(args[0]);
		try{

		int size;
		int arr[][];
		String result="";
		Scanner sc;
		sc = new Scanner(file);
		int row=0;
		String line = sc.nextLine();
		size = (line.length()+1)/2;
		arr = new int [size][size];
		do{	
			int i=0;
			int index=0;
			while((i)<line.length())
			{
				if(line.charAt(i)!='\t' && line.charAt(i)!='\n')
				{					
					arr[row][index] = line.charAt(i)=='0'?0:1;
					index++;
				}
				i++;
			}
			if(!sc.hasNextLine())
				break;
			line = sc.nextLine();
			row++;
		}while(true);


		Graph g = new Graph(size*size);

		for(int i=0; i<size; i++)
		{
			for(int j=0; j<size; j++)
			{
				int id = i * size + j;
				if(arr[i][j]!=1) continue;
				if(j+1 < size)	// get connected with the right node 
				{if(arr[i][j+1]==1){g.addEdge(id, id + 1);g.addEdge(id+1, id);}}
				if(i+1 < size)	// get connect with down node
				{if(arr[i+1][j]==1){g.addEdge(id, id + size);g.addEdge(id+size, id);}}
			}
		}

		g.printAllPaths(size*(size-1), size-1);

		}catch(Exception e){;}

			
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
			System.out.println(localPathList);
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
/*

	int size;
	Position pos;
	int arr[][];
	String result="";
	public Walker(File f)
	{
		Scanner sc;
		try{
		sc = new Scanner(f);
		int row=0;
		String line = sc.nextLine();
		size = (line.length()+1)/2;
		arr = new int [size][size];
		pos = new Position(size-1, 0, size*(size-1)+1, size);	// left down corner
		do{	
			int i=0;
			int index=0;
			while((i)<line.length())
			{
				if(line.charAt(i)!='\t' && line.charAt(i)!='\n')
				{					
					arr[row][index] = line.charAt(i)=='0'?0:1;
					index++;
				}
				i++;
			}
			if(!sc.hasNextLine())
				break;
			line = sc.nextLine();
			row++;
		}while(true);
		}catch(Exception e){System.out.println("Error");;}
	}*/


/*	public void Walk(Position p)
	{
		String s[] = new String[1];
		result += "-";
		result += (p.id);

		System.out.println(result);
		if(p.id == size)
			return ;
		while(p.left()!=-1)
		{

			System.out.println("left");
			Walk(p);

		}
		while(p.right()!=-1 )
		{

			System.out.println("right");
			Walk(p);
		}

		while(p.up()!=-1)
		{
			System.out.println("up");

			Walk(p);
		}

		while(p.down()!=-1)
		{
			System.out.println("down");

			Walk(p);
		}
	}*/
	/*
	public void printWalker()
	{
		for(int i=0; i<size; i++)
		{
			for(int j=0; j<size; j++)
			{
				System.out.print(arr[i][j]+"\t");
			}		
			System.out.println("");

		}
	}
}*/
/*
class Position{
	int x;	// row
	int y;
	int id;
	int visited;
	Position up;
	Position down;
	Position left;
	Position right;
	public Position(int x, int y, int id, int size)
	{
		this.x = x;
		this.y = y;
		this.id = id;
		visited = 0;
		up=down=left=right=null;
	}
	public int up()
	{
		if(pos.x==0)
			return -1;
		if(arr[pos.x-1][pos.y] == 0)
			return -1;
		pos.x--;
		pos.id = pos.id - size;
			visited[x][y] = 1;
	
		return 0;
	}
	public int down()
	{
		if(pos.x==size-1)
			return -1;
		if(arr[pos.x+1][pos.y] == 0)
			return -1;
		pos.x++;
		pos.id = pos.id + size;
		visited[x][y] = 1;

		return 0;

	}
	public int left()
	{
		if(pos.y==0)
			return -1;
		if(arr[pos.x][pos.y-1] == 0)
			return -1;
		pos.y--;
		pos.id--;
		visited[x][y] = 1;

		return 0;

	}
	public int right()
	{
		if(pos.y==size-1)
			return -1;
		if(arr[pos.x][pos.y+1] == 0)
			return -1;

		pos.y++;
		pos.id++;
		visited[x][y] = 1;

		return 0;
	}

}*/
