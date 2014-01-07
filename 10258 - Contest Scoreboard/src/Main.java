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
		int user;
		int problem;
		int time;
		char l;
		idata = new StringTokenizer (Main.ReadLn (255));
		int cases = Integer.parseInt(idata.nextToken());
		Main.ReadLn(255);
		for(int i = 0; i<cases; i++){
			int[][] table = new int[101][11];
			boolean[][] users = new boolean[101][10];
			boolean done = false;
			while(!done){
				input = Main.ReadLn (255);
				if(input != null){
					idata = new StringTokenizer (input);
					if(idata.hasMoreTokens()){
						user = Integer.parseInt(idata.nextToken());
						problem = Integer.parseInt(idata.nextToken());
						time = Integer.parseInt(idata.nextToken());
						l = idata.nextToken().charAt(0);
						users[user][0] = true;
						if(l == 'I'){
							table[user][problem] += 20;
						}
						if(l == 'C'){
							if(!users[user][problem]){
								table[user][problem] += time;
								table[user][0] += table[user][problem];
								table[user][10] += 1;
								users[user][problem] = true;
							}
						}
					}
					else{
						done = true;
					}
				}
				else{
					done = true;
				}

			}
			ArrayList<Integer> userList = new ArrayList<Integer>();
			for(int j = 1; j < 101; j++){
				if(users[j][0]){
					userList.add(j);
				}
			}
			int max;
			while(!userList.isEmpty()){
				int index = 0;
				max = userList.get(0);
				for(int j = 1; j < userList.size(); j++){
					if(table[max][10] < table[userList.get(j)][10]){
						max = userList.get(j);
						index = j;
					}
					if(table[max][10] == table[userList.get(j)][10]){
						if(table[max][0] > table[userList.get(j)][0]){
							max = userList.get(j);
							index = j;
						}
						if(table[max][0] == table[userList.get(j)][0]){
							if(max > userList.get(j)){
								max = userList.get(j);
								index = j;
							}
						}
					}
				}
				userList.remove(index);
				System.out.println(max + " " + table[max][10] + " " + table[max][0]);
			}
			if(i != cases-1){
				System.out.println();
			}
		}
		return;

	}
}
