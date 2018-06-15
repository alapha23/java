#include <bitset>
#include <cstdlib>
#include <ctime>
#include <fstream>
#include <iostream>

int main(int argc, char *argv[])
{
	if(argc != 3)
	{
		std::cerr << "Usage: " << argv[0] << " <number of lines> <output file>" << std::endl;
		return 1;
	}

	srand(time(NULL));

	uint64_t numLines = std::atoll(argv[1]);
	std::ofstream outFile(argv[2]);

	for(uint64_t i = 0; i < numLines; i++)
	{
		uint64_t numRoute = rand() % 9000 + 1000;
		uint64_t numInterval = rand() % 30 + 2;
		outFile << numRoute << "," << numInterval << ",";
		std::bitset<52> stops;
		uint64_t numStops = rand() % 51 + 1;
		for(uint64_t j = 0; j < numStops; j++)
		{
			uint64_t numStop;
			do
			{
				numStop = rand() % numStops;
			} while(stops[numStop] != 0);
			stops[numStop] = 1;
			char stop;
			if(numStop < 26)
			{
				stop = 'A' + numStop;
			}
			else
			{
				stop = 'a' + numStop - 26;
			}
			outFile << stop;
			if(j != numStops - 1)
			{
				outFile << ",";
				uint64_t numDist = rand() % 10 + 1;
				outFile << numDist << ",";
			}
			else
			{
				outFile << std::endl;
			}
		}
		stops.reset();
	}
	outFile.close();

	return 0;
}
