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
		boolean start = true;
		StringTokenizer idata;
		String input;
		int counter = 1;
		while ((input = Main.ReadLn (255)) != null){
			idata = new StringTokenizer (input);
			if(!idata.hasMoreTokens()){
				return;
			}
			int cities = Integer.parseInt(idata.nextToken());
			if(cities == 0){
				return;
			}
			if(!start){
				System.out.println();
			}
			else{
				start = false;
			}
			HashMap<String, Integer> city = new HashMap<String, Integer>();
			String[] citynames = new String[cities];
			for(int i = 0; i < cities; i++){
				idata = new StringTokenizer (Main.ReadLn(255));
				String cityname = idata.nextToken();
				city.put(cityname, i);
				citynames[i] = cityname;
			}
			idata = new StringTokenizer (Main.ReadLn(255));
			int roads = Integer.parseInt(idata.nextToken());
			boolean[][] connect = new boolean[cities][cities];
			for(int i = 0; i < roads; i++){
				idata = new StringTokenizer (Main.ReadLn(255));
				String a = idata.nextToken();
				String b = idata.nextToken();
				connect[city.get(a)][city.get(b)] = true;
				connect[city.get(b)][city.get(a)] = true;
			}
			System.out.print("City map #" + counter + ": ");
			counter++;
			findCutPoints(connect, citynames);
		}
		return;
	}

	void findCutPoints(boolean[][] connect, String[] citynames) {
		int[] partitions = findPartitions(connect);
		ArrayList<String> cutpoints = new ArrayList<String>();
		for(int i = 0; i < connect.length; i++){
			if(!con(connect, i, partitions)){
				cutpoints.add(citynames[i]);
			}
		}
		Collections.sort(cutpoints);
		System.out.println(cutpoints.size() + " camera(s) found");
		for(int i = 0; i < cutpoints.size(); i++){
			System.out.println(cutpoints.get(i));
		}
	}

	private boolean con(boolean[][] connect, int i, int[] partitions) {
		int nip = 0;
		int start = 0;
		for(int j = 0; j < partitions.length; j++){
			if(partitions[j] == partitions[i]){
				nip++;
				if(i != j){
					start = j;
				}
			}
		}
		if(nip<3){
			return true;
		}
		return connected(connect, i, partitions, start, nip);
	}

	int[] findPartitions(boolean[][] connect) {
		int[] partition = new int[connect.length];
		for(int i = 0 ; i<connect.length; i++){
			partition[i] = i;
		}
		for(int i = 0; i < connect.length; i++){
			boolean[] visited = new boolean[connect.length];
			if(partition[i] == i){
				depthfst(connect, visited ,partition, i, i);
			}
			/*for(int j = 0 ; j < connect.length; j++){
				if(connect[i][j]){
					if(partition[j] < partition[i]){
						partition[i] = partition[j];
					}
					else{
						partition[j] = partition[i];
					}
				}
			}*/
		}
		return partition;

	}


	boolean connected(boolean[][] connect, int i, int[] partitions, int start, int nip) {
		boolean[] visited = new boolean[connect.length];
		visited[i] = true;
		count = 0;
		dfs(connect, visited, start);
		if(count == nip-1){
			return true;
		}
		return false;
	}
	static int count;

	void dfs(boolean[][] connect, boolean[] visited, int start) {
		if(visited[start]){
			return;
		}
		visited[start] = true;
		count++;
		for(int i = 0; i< connect.length; i++){
			if(connect[start][i]){
				dfs(connect, visited, i);
			}
		}

	}

	void depthfst(boolean[][] connect, boolean[] visited, int[] partition, int start, int parent) {
		if(visited[start]){
			return;
		}
		visited[start] = true;
		partition[start] = parent;
		for(int i = 0; i< connect.length; i++){
			if(connect[start][i]){
				depthfst(connect, visited, partition, i, parent);
			}
		}

	}
}
