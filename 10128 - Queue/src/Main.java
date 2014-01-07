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
			for(int i = 0; i < cases; i++){
				idata = new StringTokenizer (Main.ReadLn(255));
				int n = Integer.parseInt(idata.nextToken());
				int p = Integer.parseInt(idata.nextToken());
				int r = Integer.parseInt(idata.nextToken());
				queuePerm(n,p,r);
			}
			return;
		}
		return;

	}

	void queuePerm(int n, int p, int r) {
		int permutations = 0;
		int low = Math.min(p-1, r-1);
		int high = Math.max(p-1, r-1);
		for(int i = low; i<n-high; i++){
			permutations += choose(n-1,i)*perm(i,low)*perm(n-1-i, high);
			//System.out.println("Low: " + i + " " + low + " High: "  + (n-1-i) + " " + high);
		}
		System.out.println(permutations);

	}
	static long[][] perm = new long[13][13];

	long perm(int n, int r) {
		if(perm[n][r] != 0){
			return perm[n][r];
		}
		if(r == 1){
			return perm[n][r] = faculty(n-1);
		}
		if(n == r){
			return perm[n][r] = 1;
		}
		if(r == 0){
			return 0;
		}
		long sum = 0;
		int a = n-1;
		int b = r-1;
		for(int i = b; i<n; i++){
			sum += choose(a,i)*faculty(a-i)*perm(i,b);
		}
		return perm[n][r] = sum;
	}

	static long[] fac = new long[12];

	long faculty(int i) {
		if(i == 0){
			return 1;
		}
		if(fac[i] != 0){
			return fac[i];
		}
		return fac[i] = i*faculty(i-1);
	}
	static long[][] nCr = new long[13][7];

	//WORKS
	long choose(int n, int r) {
		if(r > n/2){
			r = n-r;
		}
		if(nCr[n][r] != 0){
			return nCr[n][r];
		}
		if(r == 1){
			return nCr[n][r] = n;
		}
		if(r == 0){
			return nCr[n][r] = 1;
		}
		if(n==1){
			return nCr[n][r] = 1;
		}
		return nCr[n][r] = choose(n-1, r-1) + choose(n-1,r);
	}
}
