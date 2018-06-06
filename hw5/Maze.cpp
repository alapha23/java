#include <iostream>
#include "Maze.hpp"
#include "Graph.hpp"
using namespace std;

int main(int argc, char **argv)
{
	if(argc < 3)
	{
		cout << "Usage: ./Maze input.txt output.txt" << endl;
		exit(0);
	}
	open_file(argv);
	parse_file();
	Graph g(size*size);
	for(int i=0; i<size; i++)
	{
		for(int j=0; j<size; j++)
		{
			if(Maze[i][j]==1)
			{
				if(j<size-1 && Maze[i][j+1]==1)
				{
					g.addEdge(i*size+j, i*size+j+1);
					g.addEdge(i*size+j+1, i*size+j);
				}
				if(i<size-1 && Maze[i+1][j]==1)
				{
					g.addEdge(i*size+j, i*size+j+size);
					g.addEdge(i*size+j+size, i*size+j);
				}
			}
		}
	}

	g.printAllPaths(size*(size-1), size-1);

	free(Maze);
	fclose(input);
	return 0;
}


void parse_file()
{
	char *line;
	size_t len=0;
	int row_num=0;
	int i, j;

	size = get_rownumber();
	Maze = (int **)malloc(size*sizeof(int*));
	for(i=0; i<size; i++)
	{Maze[i] = (int *)malloc(size*sizeof(int));}
	line = (char *)malloc((size)*2);
	while(getline(&line, &len, input)!=-1)
	{		
		j=0;
		for(i=0; i<2*size; i=i+2)
		{
			Maze[row_num][j] = (int)((line[i]) - 0x30);
			j++;
		}
		row_num++;
	}
	free(line);
}

int get_rownumber(void)
{
	int lines=0;
	char ch;
	while(!feof(input))
	{
	  ch = fgetc(input);
	  if(ch == '\n')
	  {
	    lines++;
	  }
	}
        rewind(input);
	return lines;
}

