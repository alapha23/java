#ifndef MAZE
#define MAZE

FILE *input;
FILE *output;
int **Maze;
static int size=0;

void open_file(char **argv)
{
        if((input = fopen(argv[1], "r")) == NULL)
        {
                fprintf(stderr, "filedir: %s\n", argv[1]);
                fprintf(stderr, "Usage: ./Maze <input.txt> <out.txt>\n");
                fflush(stderr);
                exit(0);
        }
        if((output = fopen(argv[2], "w")) == NULL)
        {
                fprintf(stderr, "filedir: %s\n", argv[2]);
                fprintf(stderr, "Usage: ./Maze <input.txt> <out.txt>\n");
                fflush(stderr);
                exit(0);
        }
}
void parse_file(void);
int get_rownumber(void);
#endif
