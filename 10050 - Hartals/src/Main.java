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
		idata = new StringTokenizer(Main.ReadLn(255));
		int sets = Integer.parseInt(idata.nextToken());
		for(int i = 0; i<sets; i++){
			int count = 0;
			idata = new StringTokenizer(Main.ReadLn(255));
			int days = Integer.parseInt(idata.nextToken());
			idata = new StringTokenizer(Main.ReadLn(255));
			int parties = Integer.parseInt(idata.nextToken());
			int[] strike = new int[parties];
			for(int j = 0; j<parties; j++){
				idata = new StringTokenizer(Main.ReadLn(255));
				strike[j] = Integer.parseInt(idata.nextToken());
			}
			for(int j = 1; j<days+1; j++){
				boolean counted = false;
				if(!(j%7 == 6) &&  !(j%7 == 0)){
					for(int k = 0; k<parties; k++){
						if(!counted && j%strike[k] == 0){
							count++;
							counted = true;
						}
					}
				}
			}
			System.out.println(count);
		}
		return;

	}
}

