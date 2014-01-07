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
		int length;
		int[] stack;

		while ((input = Main.ReadLn (255)) != null){
			idata = new StringTokenizer (input);
			if(!idata.hasMoreTokens()){
				return;
			}
			length = idata.countTokens();
			System.out.println(input);
			stack = new int[length];
			for(int i = 0; i<length; i++){
				stack[i] = Integer.parseInt(idata.nextToken());
			}
			flapjacker(stack, length);

		}
		return;

	}
	void flapjacker(int[] stack, int length){
		int index;
		for(int i = 1; i<stack.length; i++){
			if((index = findLargest(stack, i)) != stack.length - i){
				if(index != 0){
					stack = flip(stack, stack.length-index);
					System.out.print(stack.length-index + " ");
				}
				stack = flip(stack, i);
				System.out.print(i + " ");
			}
		}
		System.out.println(0);
	}

	int[] flip(int[] stack, int spatula){
		int temp;
		for(int i = 0; i<(stack.length-spatula+1)/2; i++){
			temp = stack[stack.length-spatula-i];
			stack[stack.length-spatula-i] = stack[i];
			stack[i] = temp;
		}
		return stack;
	}

	int findLargest(int[] stack, int i){
		int max = 0;
		int index = 0;
		for(int j = 0; j<stack.length-i+1; j++){
			if(stack[j] > max){
				max = stack[j];
				index = j;
			}
		}
		return index;
	}

}
