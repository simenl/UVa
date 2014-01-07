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
				if(i != 0){
					System.out.println();
				}
				Main.ReadLn(255);
				idata = new StringTokenizer (Main.ReadLn(255));
				int freckles = Integer.parseInt(idata.nextToken());
				double[][] frckl = new double[freckles][2];
				for(int j = 0; j < freckles; j++){
					idata = new StringTokenizer (Main.ReadLn(255));
					frckl[j][0] = Double.parseDouble(idata.nextToken());
					frckl[j][1] = Double.parseDouble(idata.nextToken());
				}
				findMST(frckl);
			}
			return;
		}
		return;

	}

	void findMST(double[][] frckl) {
		int[] partition = new int[frckl.length];
		for(int i = 0 ; i<frckl.length; i++){
			partition[i] = i;
		}
		int counter = 0;
		int sum = 0;
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		for(int i = 0; i < frckl.length-1; i++){
			for(int j = i+1; j < frckl.length; j++){
				double x = frckl[i][0] - frckl[j][0];
				double y = frckl[i][1] - frckl[j][1];
				double dist = Math.sqrt(x*x + y*y);
				int distance = (int) Math.round(100*dist);
				pq.add(new Edge(i,j, distance));
			}
		}
		while(counter < frckl.length-1){
			Edge e = pq.poll();
			if(partition[e.getA()] != partition[e.getB()]){
				counter++;
				ref(e.getA(), e.getB(), partition);
				sum += e.length;
			}
		}

		System.out.printf("%.2f\n", (double)sum/100);


	}

	void p(int a, int b, int[] partition) {
		for(int i = 0; i < partition.length; i++){
			if(partition[i] == a){
				partition[i] = b;
			}
		}


	}

	void ref(int a, int b, int[] partition) {
		if(partition[a] > partition[b]){
			p(partition[a], partition[b], partition);
		}
		else{
			p(partition[b], partition[a], partition);
		}

	}
	class Edge implements Comparable{
		int a = -1;
		int b = -1;
		double length = 0;


		Edge(int a, int b, double length){
			this.a = a;
			this.b = b;
			this.length = length;
		}

		int getA(){
			return a;
		}

		int getB(){
			return b;
		}

		double getLength(){
			return length;
		}

		@Override
		public int compareTo(Object o) {
			if(this.length < ((Edge) o).length){
				return -1;
			}
			return 1;
		}
	}
}
