import java.io.*;
import java.util.*;

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

        while ((input = Main.ReadLn (255)) != null)
        {
        	idata = new StringTokenizer (input);
			int low = Integer.parseInt(idata.nextToken());
			int high = Integer.parseInt(idata.nextToken());
			longestCycle(low, high);
        }
    }
	static void longestCycle(int low, int high){
		int l = 0;
		int h = 0;
		int max = 1;
		if(low > high){
			l = high;
			h = low;
		}
		else{
			h = high;
			l = low;
		}
		for(int i = l; i < h+1; i++){
			int k = counter(i, 1);
			if(k > max){
				max = k;
			}
		}
		System.out.println(low + " " + high + " " + max);
	}

	static int counter(long number, int count){
		if(number == 1){
			return count;
		}
		if(number%2 == 1){
			return counter(number*3 + 1, count+1);
		}
		return counter(number/2, count+1);
	}
}
