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
		int length;

		while ((input = Main.ReadLn (10000000)) != null)
		{
			idata = new StringTokenizer (input);
			if(!idata.hasMoreTokens()){
				return;
			}
			length = Integer.parseInt(idata.nextToken());
			int[] table = new int[length];
			for(int i = 0; i<length; i++){
				table[i] = Integer.parseInt(idata.nextToken());
			}
			jumperCheck(table);

		}
		return;
	}
	void jumperCheck(int[] table){
		boolean[] btable = new boolean[table.length];
		for(int i = 1; i<table.length; i++){
			if(Math.abs(table[i-1]-table[i]) > table.length-1 ){
				System.out.println("Not jolly");
				return;
			}
			if(btable[Math.abs(table[i-1]-table[i])]){
				System.out.println("Not jolly");
				return;
			}
			else{
				btable[Math.abs(table[i-1]-table[i])] = true;
			}


		}
		System.out.println("Jolly");
	}
}


