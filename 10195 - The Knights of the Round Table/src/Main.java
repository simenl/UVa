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
			double a = Double.parseDouble(idata.nextToken());
			double b = Double.parseDouble(idata.nextToken());
			double c = Double.parseDouble(idata.nextToken());
			findRadius(a,b,c);
		}
		return;

	}

	void findRadius(double a, double b, double c) {
		if(a == 0 || b == 0 || c == 0){
			System.out.println("The radius of the round table is: 0.000");
			return;
		}
		double theta = Math.acos((a*a+c*c-b*b)/(2*a*c))/2;
		double phi = Math.acos((a*a+b*b-c*c)/(2*a*b))/2;
		double alpha = Math.PI - theta - phi;
		double r = (a*Math.sin(phi)*Math.sin(theta))/Math.sin(alpha);
		System.out.print("The radius of the round table is: ");
		System.out.printf("%.3f\n", r);
	}
}
