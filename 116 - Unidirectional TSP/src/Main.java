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
			int m = Integer.parseInt(idata.nextToken());
			int n = Integer.parseInt(idata.nextToken());
			int[][] cyl = new int[m][n];
			idata = new StringTokenizer (Main.ReadLn(2550));
			for(int i = 0; i < m; i++){
				for(int j = 0; j < n; j++){
					if(!idata.hasMoreTokens()){
						idata = new StringTokenizer (Main.ReadLn(2550));
					}
					cyl[i][j] = Integer.parseInt(idata.nextToken());
				}
			}
			findPath(cyl, m, n);
		}
		return;

	}

	void findPath(int[][] cyl, int m, int n) {
		int[][] pathValue = new int[m][n];
		int[][] parent = new int[m][n];
		for(int i = 0 ; i < m; i++){
			pathValue[i][n-1] = cyl[i][n-1];
		}
		for(int j = n-2; j >= 0; j--){
			for(int i = 0; i < m; i++){
				pathValue[i][j] = pathValue[(i-1+m)%m][j+1] + cyl[i][j];
				parent[i][j] = (i-1+m)%m;
				if(pathValue[i][j+1] + cyl[i][j] < pathValue[i][j]){
					pathValue[i][j] = pathValue[i][j+1] + cyl[i][j];
					parent[i][j] = i;
				}
				if(pathValue[(i+1)%m][j+1] + cyl[i][j] < pathValue[i][j]){
					pathValue[i][j] = pathValue[(i+1)%m][j+1] + cyl[i][j];
					parent[i][j] = (i+1)%m;
				}
				if(pathValue[i][j+1] + cyl[i][j] == pathValue[i][j] && i < parent[i][j]){
					parent[i][j] = i;
				}
				if(pathValue[(i+1)%m][j+1] + cyl[i][j] == pathValue[i][j] && (i+1)%m < parent[i][j]){
					parent[i][j] = (i+1)%m;
				}

			}
		}
		int min = pathValue[0][0];
		int index = 0;
		for(int i = 1; i<m; i++){
			if(pathValue[i][0] < min){
				min = pathValue[i][0];
				index = i;
			}
		}
		System.out.print((index+1));
		for(int i = 0; i < n-1; i++){
			System.out.print(" ");
			index = parent[index][i];
			System.out.print((index+1));
		}
		System.out.println();
		System.out.println(min);
		return;
	}
}
