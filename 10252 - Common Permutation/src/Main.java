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
		String first;
		String second;

		while ((input = Main.ReadLn (10000)) != null){
			idata = new StringTokenizer (input);
			if(!idata.hasMoreTokens()){
				first = "";
			}
			else{
				first = idata.nextToken();
			}

			idata = new StringTokenizer (Main.ReadLn(10000));

			if(!idata.hasMoreTokens()){
				second = "";
			}
			else{
				second = idata.nextToken();
			}
			permutatify(first, second);
		}
		return;

	}
	void permutatify(String first, String second){
		String fst = chars(first);
		String snd = chars(second);
		String permutated = "";
		for(int i = 0; i<fst.length(); i++){
			for(int j = 0; j<snd.length(); j++){
				if(fst.charAt(i) == snd.charAt(j)){
					permutated += fst.charAt(i);
					snd = snd.replaceFirst(Character.toString(fst.charAt(i)), "!");
					j=snd.length();
				}
			}
		}
		System.out.println(permutated);
	}

	String chars(String word){
		char[] l = word.toCharArray();
		char min;
		int mindex;
		for(int i = 0; i<l.length; i++){
			min = l[i];
			mindex = i;
			for(int j = i+1; j<l.length; j++){
				if(min>l[j]){
					min = l[j];
					mindex = j;
				}
			}
			l[mindex] = l[i];
			l[i] = min;
		}
		String c = "";
		for(int i = 0; i<l.length; i++){
			c += l[i];
		}
		return c;
	}
}
