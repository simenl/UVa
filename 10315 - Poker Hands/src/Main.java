import java.io.*;
import java.util.*;

//NOTE: I've used the skeleton from the sample Java code: http://online-judge.uva.es/problemset/data/p100.java.html
class Main
{
	static String ReadLn (int maxLg)  // utility function to read from stdin
	{
		byte lin[] = new byte [maxLg];
		int lg = 0, car = -1;

		try
		{
			while (lg < maxLg)
			{
				car = System.in.read();
				if ((car < 0) || (car == '\n')) break;
				lin [lg++] += car;
			}
		}
		catch (IOException e)
		{
			return (null);
		}

		if ((car < 0) && (lg == 0)) return (null);  // eof
		return (new String (lin, 0, lg));
	}

	public static void main (String args[])  // entry point from OS
	{
		Main myWork = new Main();  // create a dinamic instance
		myWork.Begin();            // the true entry point
	}

	void Begin()
	{
		StringTokenizer idata;
		String input;
		String[] black = new String[5];
		String[] white = new String[5];
		while ((input = Main.ReadLn (255)) != null){
			idata = new StringTokenizer (input);
			if(idata.hasMoreTokens()){
				for(int i = 0; i < 5; i++){
					black[i] = idata.nextToken();
				}
				for(int i = 0; i < 5; i++){
					white[i] = idata.nextToken();
				}
				pokerize(black, white);
			}
			else{
				return;
			}
		}
		return;

	}

	void pokerize(String[] black, String[] white){
		int[] bsort = sort(black);
		int[] wsort = sort(white);
		if(straightFlush(bsort, black) > straightFlush(wsort, white)){
			System.out.println("Black wins.");
			return;
		}
		if(straightFlush(bsort, black) < straightFlush(wsort, white)){
			System.out.println("White wins.");
			return;
		}
		if(fourOfAKind(bsort) > fourOfAKind(wsort)){
			System.out.println("Black wins.");
			return;
		}
		if(fourOfAKind(bsort) < fourOfAKind(wsort)){
			System.out.println("White wins.");
			return;
		}

		if(house(bsort) > house(wsort)){
			System.out.println("Black wins.");
			return;
		}

		if(house(bsort) < house(wsort)){
			System.out.println("White wins.");
			return;
		}
		if(flush(black) && flush(white)){
			if(high(bsort) > high(wsort)){
				System.out.println("Black wins.");
				return;
			}
			if(high(bsort) < high(wsort)){
				System.out.println("White wins.");
				return;
			}
			System.out.println("Tie.");
			return;
		}
		if(flush(black)){
			System.out.println("Black wins.");
			return;
		}
		if(flush(white)){
			System.out.println("White wins.");
			return;
		}
		if(straight(bsort) > straight(wsort)){
			System.out.println("Black wins.");
			return;
		}
		if(straight(bsort) < straight(wsort)){
			System.out.println("White wins.");
			return;
		}

		if(threeOfAKind(bsort) > threeOfAKind(wsort)){
			System.out.println("Black wins.");
			return;
		}
		if(threeOfAKind(bsort) < threeOfAKind(wsort)){
			System.out.println("White wins.");
			return;
		}
		if(pairs(bsort) > pairs(wsort)){
			System.out.println("Black wins.");
			return;
		}
		if(pairs(bsort) < pairs(wsort)){
			System.out.println("White wins.");
			return;
		}
		if(high(bsort) > high(wsort)){
			System.out.println("Black wins.");
			return;
		}
		if(high(bsort) < high(wsort)){
			System.out.println("White wins.");
			return;
		}
		System.out.println("Tie.");
		return;
	}

	int[] sort(String[] hand){
		int temp;
		int[] sorted = new int[hand.length];
		for(int i = 0; i < hand.length; i++){
			sorted[i] = cardValue(hand[i].charAt(0));
		}


		for(int i = 0; i<hand.length-1; i++){
			temp = sorted[i];
			for(int j = i+1; j<hand.length; j++){
				if(sorted[j] > temp){
					sorted[i] = sorted[j];
					sorted[j] = temp;
					temp = sorted[i];
				}
			}
		}
		return sorted;
	}

	int cardValue(char a){
		switch(a){
		case '2': return 2;
		case '3': return 3;
		case '4': return 4;
		case '5': return 5;
		case '6': return 6;
		case '7': return 7;
		case '8': return 8;
		case '9': return 9;
		case 'T': return 10;
		case 'J': return 11;
		case 'Q': return 12;
		case 'K': return 13;
		case 'A': return 14;
		}
		return 0;
	}

	int high(int[] hand){
		int value = 0;
		for(int i = 0; i<hand.length; i++){
			value = 16*value + hand[i];
		}
		return value;
	}

	int pairs(int[] hand){
		int[] pairs = new int[2];
		pairs[0] = 0;
		pairs[1] = 0;
		int count = 0;
		for(int i = 3; i>-1; i--){
			if(hand[i]-hand[i+1] == 0){
				pairs[count]= hand[i];
				i--;
				count++;
			}
		}
		return pairs[1]*16 + pairs[0];
	}

	int threeOfAKind(int[] hand){
		for(int i = 0; i < 3; i++){
			if((hand[i] == hand[i+1]) && (hand[i] == hand[i+2])){
				return hand[i];
			}
		}
		return 0;
	}

	int straight(int[] hand){
		for(int i = 0; i < hand.length-1; i++){
			if(!(hand[i]-hand[i+1] == 1)){
				return 0;
			}
		}
		return hand[0];
	}

	boolean flush(String[] hand){
		for(int i = 0; i < 4; i++){
			if(hand[i].charAt(1) != hand[i+1].charAt(1)){
				return false;
			}
		}
		return true;
	}

	int house(int[] hand){
		if(pairs(hand) > 14){
			return threeOfAKind(hand);
		}
		return 0;
	}

	int fourOfAKind(int[] hand){
		for (int i = 0; i<2; i++){
			if(hand[i] == hand[i+1] && hand[i] == hand[i+2] && hand[i] == hand[i+3]){
				return hand[i];
			}
		}
		return 0;
	}

	int straightFlush(int[] hand, String[] colours){
		if(flush(colours)){
			return straight(hand);
		}
		return 0;
	}

}
