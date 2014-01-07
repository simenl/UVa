import java.io.*;

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
		String fstln;
		String sndln;
		String input;

		while ((input = Main.ReadLn (3000)) != null){
			fstln = input;
			sndln = Main.ReadLn(3000);
			if(fstln == null  || sndln == null){
				System.out.println("0");
			}
			else{
				findLongest(fstln, sndln);
			}

		}
		return;

	}

	void findLongest(String fstln, String sndln) {
		int[][] table = new int[fstln.length()+1][sndln.length()+1];
		for(int i = 1; i < fstln.length() + 1; i++){
			for(int j = 1; j < sndln.length() + 1; j++){
				if(fstln.charAt(i-1) == sndln.charAt(j-1)){
					table[i][j] = table[i-1][j-1] + 1;
				}
				else{
					table[i][j] = Math.max(table[i-1][j], table[i][j-1]);
				}
			}
		}
		System.out.println(table[fstln.length()][sndln.length()]);
	}
}

