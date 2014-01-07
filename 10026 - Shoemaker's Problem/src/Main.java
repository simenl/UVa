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
			for(int i = 0; i<cases; i++){
				if(i != 0){
					System.out.println();
				}
				Main.ReadLn(255);
				idata = new StringTokenizer(Main.ReadLn(255));
				int orders = Integer.parseInt(idata.nextToken());
				ArrayList<pairs> list = new ArrayList<pairs>();
				int index = 1;
				for(int j = 0; j<orders; j++){
					idata = new StringTokenizer(Main.ReadLn(255));
					int x = Integer.parseInt(idata.nextToken());
					int y = Integer.parseInt(idata.nextToken());
					list.add(new pairs(x,y,index++));
				}
				cheapest(list);
			}
			return;

		}
		return;

	}

	void cheapest(ArrayList<pairs> list) {
		int index;
		while(list.size()>1){
			index = 0;
			for(int i = 1; i<list.size(); i++){
				if(list.get(i).getFst()*list.get(index).getSnd() < list.get(i).getSnd()*list.get(index).getFst()){
					index = i;
				}
			}
			System.out.print(list.get(index).getIndex() + " ");
			list.remove(index);
		}
		System.out.println(list.get(0).getIndex());
		list.clear();

	}

	static class pairs{
		int x;
		int y;
		int index;
		pairs(int x, int y, int index){
			this.x = x;
			this.y = y;
			this.index = index;
		}
		int getFst(){
			return x;
		}
		int getSnd(){
			return y;
		}
		int getIndex(){
			return index;
		}
	}
}
