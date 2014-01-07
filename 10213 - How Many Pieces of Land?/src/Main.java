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
		BigInteger n;
		int cases;

		while ((input = Main.ReadLn (255)) != null){
			idata = new StringTokenizer (input);
			if(!idata.hasMoreTokens()){
				return;
			}
			cases = Integer.parseInt(idata.nextToken());
			for(int i = 0; i<cases; i++){
				n = new BigInteger(Main.ReadLn (255));
				piecesOfLand(n);
			}
			return;
		}
		return;

	}

	void piecesOfLand(BigInteger n) {
		BigInteger one = new BigInteger("1");
		BigInteger two = new BigInteger("2");
		BigInteger three = new BigInteger("3");
		BigInteger twfour = new BigInteger("24");
		n = one.add(n.multiply(n.subtract(one)).divide(two)).add(n.multiply(n.subtract(one)).multiply(n.subtract(two)).multiply(n.subtract(three)).divide(twfour));
		//1 + (n*(n-1))/2 + (n*(n-1)*(n-2)*(n-3))/24;
		System.out.println(n);
	}
}
