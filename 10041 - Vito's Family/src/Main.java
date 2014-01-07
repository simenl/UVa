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
			int cases = Integer.parseInt(idata.nextToken());
			for(int i = 0; i<cases; i++){
				idata = new StringTokenizer (Main.ReadLn(10000));
				int relatives = Integer.parseInt(idata.nextToken());
				int[] loc = new int[relatives];
				for(int j = 0; j<relatives; j++){
					loc[j] = Integer.parseInt(idata.nextToken());
				}
				vitonize(loc);
			}
			return;
		}
		return;

	}

	void vitonize(int[] loc) {
		Arrays.sort(loc);
		int distance = 0;
		for(int i = 0; i<loc.length/2; i++){
			distance += loc[loc.length-1-i] - loc[i];
		}
		System.out.println(distance);
	}
}
