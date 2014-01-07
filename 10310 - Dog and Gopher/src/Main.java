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
		boolean first = true;
		while ((input = Main.ReadLn (255)) != null){
			if(first){
				first = false;
			}
			else{
				input = Main.ReadLn (255);
			}
			idata = new StringTokenizer (input);
			if(!idata.hasMoreTokens()){
				return;
			}
			int numHoles = Integer.parseInt(idata.nextToken());
			double gx = Double.parseDouble(idata.nextToken());
			double gy = Double.parseDouble(idata.nextToken());
			Point gopher = new Point(gx, gy);
			double dx = Double.parseDouble(idata.nextToken());
			double dy = Double.parseDouble(idata.nextToken());
			Point dog = new Point(dx, dy);
			Point[] holes = new Point[numHoles];
			for(int i = 0; i < numHoles; i++){
				idata = new StringTokenizer(Main.ReadLn(255));
				double x = Double.parseDouble(idata.nextToken());
				double y = Double.parseDouble(idata.nextToken());
				holes[i] = new Point(x,y);
			}
			escape(gopher, dog, holes);
		}
		return;

	}

	void escape(Point gopher, Point dog, Point[] holes) {
		for(int i = 0; i < holes.length; i++){
			if(0.5*distance(dog, holes[i]) >= distance(gopher, holes[i])){
				System.out.print("The gopher can escape through the hole at ");
				System.out.printf("(%.3f,", holes[i].getX());
				System.out.printf("%.3f).\n", holes[i].getY());
				return;
			}
		}
		System.out.println("The gopher cannot escape.");

	}



	double distance(Point a, Point b) {
		double x = a.getX() - b.getX();
		double y = a.getY() - b.getY();
		return Math.sqrt(x*x + y*y);
	}



	static class Point{
		double x;
		double y;
		Point(double x, double y){
			this.x = x;
			this.y = y;
		}

		double getX(){
			return x;
		}

		double getY(){
			return y;
		}
	}
}
