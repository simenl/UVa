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
			double l = Double.parseDouble(idata.nextToken());
			double w = Double.parseDouble(idata.nextToken());
			findMaxMin(l, w);

		}
		return;

	}

	void findMaxMin(double l, double w) {
		double max =  (4*(l+w) - Math.sqrt(16*(l*l + w*w + 2*l*w) - 4*12*w*l))/24;
		double min = 0.5*Math.min(l, w);
		System.out.printf("%.3f ", max);
		System.out.print("0.000 ");
		System.out.printf("%.3f\n", min);

	}
}
