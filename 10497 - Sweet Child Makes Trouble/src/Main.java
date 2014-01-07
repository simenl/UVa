import java.io.*;
import java.math.BigInteger;
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
			int n = Integer.parseInt(idata.nextToken());
			if(n == -1){
				return;
			}
			if(n == 0){
				System.out.println(0);
			}
			else{
				BigInteger answer = findDerangements(n);
				System.out.println(answer);
			}
		}
		return;

	}
	static BigInteger[] list = new BigInteger[1000];

	 static BigInteger findDerangements(int n) {
		if(list[n] != null){
			return list[n];
		}
		if(n == 1){
			return list[n] = BigInteger.valueOf(0);
		}
		else if(n%2 == 0){
			return list[n] = BigInteger.valueOf(n).multiply(findDerangements(n-1)).add(BigInteger.valueOf(1));
		}
		else{
			return list[n] = BigInteger.valueOf(n).multiply(findDerangements(n-1)).add(BigInteger.valueOf(-1));
		}
	}

}
