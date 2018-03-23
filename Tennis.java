import java.util.*;

class Tennis
{
	static Scanner sc = new Scanner(System.in);
	static String current="";
	static char match;	/* deciding set: tie break in US, advantage set in australia */
	static char gender;	/* men: best of 5, women: best of 3 */
	static int setL=0;	/* how many games left won in latest set */
	static int setR=0;
	static int scoreL=0;
	static int scoreR=0;
	static int round=1;

	static int ifDeuce=0;	/* 1 for 40A-xx, 2 for xx-40A*/

	/* deuce happens when 40-40 */

	/* tie break */

	static void initCurrent( )
	{
		current = "Current: ";
	}
	static void emitCurrent( )
	{
		if(ifDeuce == 0)
			System.out.printf("%s %d-%d(%d-%d)\n", current, setL, setR, scoreL, scoreR);
		else if(ifDeuce == 1)
			System.out.printf("%s %d-%d(%dA-%d)\n", current, setL, setR, scoreL, scoreR);
		else
			System.out.printf("%s %d-%d(%d-%dA)\n", current, setL, setR, scoreL, scoreR);

	}
	static void wins(char winner)
	{
		int winStatus;

		winStatus = increaseScore(winner);

		if(winStatus != 0)
			archiveOneGame(winStatus);
	}
	static int increaseScore(char winner)
	{
		/* this function might result in end of one game in a set */
		/* left wins: flag=1, right wins: flag=2 */
		/* return 1 if left wins game, 2 for right, 0 for no change*/
		if(winner == 'L')
		{
			if(ifDeuce == 1)
			{
				/* left wins game */
				return 1;
			}
			else if(ifDeuce == 2)
			{
				ifDeuce = 0;
				return 0;
			}
			if(scoreL != 40)
				scoreL = scoreL==0?15:scoreL==15?30:40;
			else if(scoreR == 40)
				/* 40A-40, deuce */
				ifDeuce = 1;
			else
				/* 40-xx, Left wins the game */
				return 1;
		}
		else
		{
			if(ifDeuce == 2)
				/* right wins game */
				return 2;
			else if(ifDeuce == 1)
			{
				ifDeuce--;
				return 0;
			}
			if(scoreR != 40)
				scoreR = scoreR==0?15:scoreR==15?30:40;
			else if(scoreL == 40)
				/* 40-40A, deuce */
				ifDeuce = 2;
			else
				/* xx-40, right wins the game */
				return 2;
		}
		return 0;
	}
	static void archiveOneGame(int winStatus)
	{
		if(winStatus==1)
			setL++;
		else
			setR++;
		scoreL = scoreR = 0;
		ifDeuce = 0;
	}
	static int checkSetStatus()
	{
		int winner = 0;
		/* win */
		if(setL >= 6|| setR >= 6)
		{
			if(setL-setR>=2 || setR-setL>=2)
			{
				/* no tie--> someone wins the set by 6-x or 7-5*/
				winner = setL>setR?1:2;
				/* archive set */
				archiveSet(winner);
				/* win match */
				return winner;
			}
			/* no tie --> 6-5 */
			if(setL == 6|| setR == 6)
				return winner;
			/* tie */
			if(setL == setR && setR == 6)
			{
				if((gender =='M' && round == 5)||(gender=='F'&&round==3))
				{
				/* if deciding set: */
				/* invoke tie-breaker under US open */
				/* advantage set under Australia open */
				}else
				{
				/* if not deciding set*/
				/* tie-breaker */
				}
			}
			System.out.println("We should never get here");
			System.exit(0);
		}
		return winner;
	}

	static void archiveSet(int winner)
	{
		/* winner = 1 for left wins */

		/* update current string */
		current = String.format("%s %d-%d", current, setL, setR);
		/* reset set & score */
		setL = setR = 0;
		/* reset deuce bit */
		ifDeuce = 0;
		/* next round */
		round++;
	}

	static void gameEnd(int winner)
	{
		archiveSet(0);
		System.out.printf("%s\n", current);
		System.out.println("Game finished!");
	}

	public static void main(String args[])
	{
		/* read input and decide game type */
		System.out.print("Type the match (A: Australian Open/U: US Open): ");
		match = sc.next().charAt(0);
		System.out.print("Type the gender (F: Female/M: Male): ");
		gender = sc.next().charAt(0);
	
		if(match == 'A')
			System.out.printf("Australian Open/");
		else
			System.out.printf("US Open/");
		if(gender == 'M')
			System.out.printf("Male chosen.\n");
		else
			System.out.printf("Female chosen.\n");

		initCurrent();
		while(true)
		{
			char winner;
			int setStatus;

			emitCurrent();
			System.out.print("Type the winner (L: Left/R: Right): ");
			winner = sc.next().charAt(0);

			wins(winner);
			setStatus = checkSetStatus();
			if(setStatus!=0)
			{
				gameEnd(setStatus);
				break;
			}
		}

		sc.close();
	}
}
