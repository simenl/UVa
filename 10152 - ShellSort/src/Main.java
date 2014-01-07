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
		int lines;

		while ((input = Main.ReadLn (255)) != null){
			idata = new StringTokenizer (input);
			String[] original;
			String[] desired;
			int cases = Integer.parseInt(idata.nextToken());
			for(int i = 0; i<cases; i++){
				if(i != 0){
					System.out.println();
				}
				idata = new StringTokenizer (Main.ReadLn(255));
				lines = Integer.parseInt(idata.nextToken());
				original = new String[lines];
				desired = new String[lines];
				for(int j = 0; j<lines; j++){
					original[j] = Main.ReadLn(255);
				}
				for(int j = 0; j<lines; j++){
					desired[j] = Main.ReadLn(255);
				}
				shellSort(original, desired);
			}
			System.out.println();
			return;

		}
		return;

	}
	//O(n^2)
	void shellSort(String[] original, String[] desired){
		//ArrayList<Integer> under = new ArrayList<Integer>();
		int[] numList = new int[original.length];
		/*
		for(int i = 0; i<original.length; i++){
			for(int j = 0; j<original.length; j++){
				if(original[i].equals(desired[j])){
					numList[i] = j;
					j = original.length;
				}
			}
		}
		*/
		Map<String, Integer> order = new HashMap<String, Integer>();
		for(int i = 0; i<original.length; i++){
			order.put(desired[i], i);
		}
		for(int i = 0; i<original.length; i++){
			numList[i] = order.get(original[i]);
		}

		int high = numList[0];
		int max = -1;
		for(int i = 1; i < numList.length; i++){
			if(numList[i]> high){
				high = numList[i];
			}
			else if(numList[i] > max){
				max = numList[i];
				//under.add(i);
			}
		}
		//Collections.sort(under);
		for(int i = max; i>-1; i--){
			System.out.println(desired[i]);
		}

		/*while(max != -1){
			max = -1;
			high = -1;
			index = -1;
			for(int i = 1; i<numList.length; i++){
				if(numList[i-1]> high){
					high = numList[i-1];
				}
				if(high > numList[i] && numList[i] > max){
					max = numList[i];
					index = i;
				}
			}
			if(index > -1){
			System.out.println(desired[numList[index]]);
			original = toTheTop(original, index);
			numList = toTheTop(numList, index);
			}
		}*/
	}

	/*String[] toTheTop(String[] original, int i) {
		String top = original[i];
		for(int j = i-1; j>-1; j--){
			original[j+1] = original[j];
		}
		original[0] = top;
		return original;
	}
	int[] toTheTop(int[] original, int i) {
		int top = original[i];
		for(int j = i-1; j>-1; j--){
			original[j+1] = original[j];
		}
		original[0] = top;
		return original;
	} */
}
