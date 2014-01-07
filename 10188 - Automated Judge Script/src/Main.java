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
		int count = 1;
		while ((input = Main.ReadLn (10000)) != null){
			idata = new StringTokenizer (input);
			boolean pe = false;
			boolean wa = false;
			String fNumbers = "";
			String aNumbers = "";
			ArrayList<String> fasit = new ArrayList<String>();
			int fLength = Integer.parseInt(idata.nextToken());
			if(fLength == 0){
				return;
			}
			for(int i = 0; i<fLength; i++){
				fasit.add(Main.ReadLn(10000));
				fNumbers += fasit.get(i).replaceAll("[^0-9]", "");
			}



			idata = new StringTokenizer (Main.ReadLn(10000));
			int aLength = Integer.parseInt(idata.nextToken());
			if(aLength != fLength){
				pe = true;
				for(int i = 0; i<aLength; i++){
					aNumbers += Main.ReadLn(10000).replaceAll("[^0-9]", "");
				}
			}
			else{
				for(int i = 0; i<aLength; i++){
					String temp = Main.ReadLn(10000);
					aNumbers += temp.replaceAll("[^0-9]", "");
					if(!fasit.get(i).equals(temp)){
						pe = true;
					}
				}
			}
			if(!aNumbers.equals(fNumbers)){
				wa = true;
			}
			if(wa){
				System.out.println("Run #" + count + ": Wrong Answer");
			}
			else if(pe){
				System.out.println("Run #" + count + ": Presentation Error");
			}
			else{
				System.out.println("Run #" + count + ": Accepted");
			}
			count++;
		}
		return;

	}
}
