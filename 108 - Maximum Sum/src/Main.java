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

		while ((input = Main.ReadLn (255)) != null){
			idata = new StringTokenizer (input);
			if(!idata.hasMoreTokens()){
				return;
			}
			int size = Integer.parseInt(idata.nextToken()) + 1;
			int[][] table = new int[size][size];
			int i = 1;
			int j = 1;
			while(i < size){
				while(j < size){
					if(!idata.hasMoreTokens()){
						idata = new StringTokenizer(Main.ReadLn(1000));
					}
					else{
						table[i][j] = Integer.parseInt(idata.nextToken());
						j++;
					}
				}
				j = 1;
				i++;
			}


			/*for(int i = 1; i<size; i++){
				idata = new StringTokenizer(Main.ReadLn(1000));
				for(int j = 1; j<size; j++){
					table[i][j] = Integer.parseInt(idata.nextToken());
				}
			}*/
			findMaxSum(table);
		}
		return;

	}

	void findMaxSum(int[][] table) {
		int[][] subSums = new int[table.length][table.length];
		for(int i = 1; i<table.length; i++){
			for(int j = 1; j<table.length; j++){
				subSums[i][j] = table[i][j] + subSums[i-1][j] + subSums[i][j-1] - subSums[i-1][j-1];
			}
		}
		int max = subSums[1][1];
		int temp;
		for(int i = 1; i<table.length; i++){
			for(int j = 1; j<table.length; j++){
				for(int k = i; k<table.length; k++){
					for(int l = j; l<table.length; l++){
						temp = subSums[k][l] - subSums[k][j-1] - subSums[i-1][l] + subSums[i-1][j-1];
						if(temp > max){
							max = temp;
						}
					}
				}
			}
		}
		System.out.println(max);
	}
}
