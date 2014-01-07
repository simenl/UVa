import java.io.*;
import java.util.*;
import java.math.BigInteger;

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
		;

		while ((input = Main.ReadLn (255)) != null){
			idata = new StringTokenizer (input);
			if(!idata.hasMoreTokens()){
				return;
			}
			BigInteger id = new BigInteger("0");
			BigInteger a = new BigInteger(idata.nextToken());
			BigInteger b = new BigInteger(idata.nextToken());
			if(a.equals(id) && b.equals(id)){
				return;
			}
			nFibs(a,b);
		}
		return;

	}

	void nFibs(BigInteger a, BigInteger b) {
		ArrayList<BigInteger> fibL = new ArrayList<BigInteger>();
		boolean found = false;
		int num = 0;
		int start = 0;
		fibL.add(new BigInteger("1"));
		fibL.add(new BigInteger("1"));
		if(a.compareTo(fibL.get(0)) <= 0){
			num++;
		}
		int k = 1;
		while(fibL.get(k).compareTo(b) <= 0){
			k++;
			fibL.add(fibL.get(k-1).add(fibL.get(k-2)));
		}
		k = 1;
		while(!found){
			k++;
			if(fibL.get(k).compareTo(a) >= 0){
				found = true;
				start = k;
			}
		}
		num += (fibL.size()-start - 1);
		System.out.println(num);
	}
}

