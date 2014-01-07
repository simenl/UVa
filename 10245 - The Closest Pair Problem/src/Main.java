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
		double x,y;
		while ((input = Main.ReadLn (255)) != null){
			idata = new StringTokenizer (input);
			if(!idata.hasMoreTokens()){
				return;
			}
			int numPoints = Integer.parseInt(idata.nextToken());
			if(numPoints == 0){
				return;
			}
			Point[] leftRight = new Point[numPoints];
			Point[] downTop = new Point[numPoints];

			for(int i = 0; i < numPoints; i++){
				idata = new StringTokenizer(Main.ReadLn(255));
				x = Double.parseDouble(idata.nextToken());
				y = Double.parseDouble(idata.nextToken());
				leftRight[i] = new Point(x,y);
				downTop[i] = new Point(x,y);
			}
			Arrays.sort(leftRight, new LeftRight());
			/*
			for(int i = 0; i < leftRight.length; i++){
				System.out.println(leftRight[i].x);
			} */
			/*
			Arrays.sort(downTop, new DownTop());
			double d = closestPair(leftRight, downTop, 0, leftRight.length-1);
			*/
			double d = dunno(leftRight, 10000);
			if(d < 10000){
				System.out.printf("%.4f\n", d);
			}
			else{
				System.out.println("INFINITY");
			}
			/*
			System.out.print("FASIT: ");
			double dd = squared(leftRight);
			if(d < 10000){
				System.out.printf("%.4f\n", dd);
			}
			else{
				System.out.println("INFINITY");
			} */
		}
		return;
	}





	double dunno(Point[] leftRight, double d) {
		for(int i = 0; i<leftRight.length-1; i++){
			for(int j = i+1; j < leftRight.length; j++){
				if(leftRight[j].x-leftRight[i].x >= d){
					break;
				}
				if(Math.abs(leftRight[j].y-leftRight[i].y) < d){
					d = Math.min(d, distance(leftRight[j], leftRight[i]));
				}
			}
		}
		return d;
	}






	double squared(Point[] p){
		double d = 200000;
		for(int i = 0; i< p.length-1; i++){
			for(int j = i+1; j < p.length; j++){
				if(distance(p[i], p[j]) < d){
					d = distance(p[i], p[j]);
				}
			}
		}
		return d;
	}

	double closestPair(Point[] leftRight, Point[] downTop, int s, int e) {
		if(e-s <= 0){
			return 2000000;
		}
		if(e - s == 2){
			return Math.sqrt(Math.min(distance(leftRight[e], leftRight[s]), Math.min(distance(leftRight[e-1], leftRight[s]), distance(leftRight[e], leftRight[e-1]))));
		}
		if(e - s == 1){
			return Math.sqrt(distance(leftRight[e], leftRight[s]));
		}
		int middle = ((s+e)/2);
		double m = leftRight[middle].x;
		/*while(middle < e && leftRight[middle+1].x == m){
			middle++;
		} */
		double d = Math.min(closestPair(leftRight, downTop, s, middle), closestPair(leftRight, downTop, middle+1, e));
		ArrayList<Point> ml = new ArrayList<Point>();
		ArrayList<Point> mr = new ArrayList<Point>();
		for(int i = 0; i < downTop.length; i++){
			if(downTop[i].x < m+d && downTop[i].x > m-d){
				if(downTop[i].x <= m){
					ml.add(downTop[i]);
				}
				else{
					mr.add(downTop[i]);
				}
			}
		}
		double dd = 200000000;
		int start = 0;
		for(int i = 0; i < ml.size(); i++){
			for(int j = start; j < mr.size(); j++){
				if(ml.get(i).y - mr.get(j).y > d){
					start = j;
				}
				if(mr.get(j).y - ml.get(i).y > d ){
					break;
				}
				dd = Math.min(dd, distance(mr.get(j), ml.get(i)));
			}

		}

		dd = Math.sqrt(dd);
		return Math.min(d, dd);
	}

	double distance(Point a, Point b){
		double dx = a.x - b.x;
		double dy = a.y - b.y;
		double d = dx*dx + dy*dy;
		return Math.sqrt(d);
	}


	class Point{
		double x;
		double y;

		Point(double x, double y){
			this.x = x;
			this.y = y;
		}
	}

	class LeftRight implements Comparator<Point>{

		@Override
		public int compare(Point o1, Point o2) {
			if(o1.x > o2.x){
				return 1;
			}
			return -1;
		}

	}
	class DownTop implements Comparator<Point>{

		@Override
		public int compare(Point o1, Point o2) {
			/*if(o1.y == o2.y){
				if(o1.x <= o2.x){
					return 1;
				}
				return -1;
			}*/
			if(o1.y >= o2.y){
				return 1;
			}
			return -1;
		}

	}

}
