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
                int cases = 0;
                while ((input = Main.ReadLn (255)) != null){
                        cases++;
                        idata = new StringTokenizer (input);
                        if(!idata.hasMoreTokens()){
                                return;
                        }
                        int n = Integer.parseInt(idata.nextToken());
                        int r = Integer.parseInt(idata.nextToken());
                        if(n == r && r == 0){
                                return;
                        }
                        Node[] nodeList = new Node[n+1];
                        for(int i = 1; i<n+1; i++){
                        	nodeList[i] = new Node(i);
                        }
                        for(int i = 1; i<r+1; i++){
                                idata = new StringTokenizer(Main.ReadLn(255));
                                int a = Integer.parseInt(idata.nextToken());
                                int b = Integer.parseInt(idata.nextToken());
                                int c = Integer.parseInt(idata.nextToken());
                                Edge e = new Edge(a, b, c);
                                nodeList[a].addEdge(e);
                                nodeList[b].addEdge(e);
                        }
                        idata = new StringTokenizer(Main.ReadLn(255));
                        int a = Integer.parseInt(idata.nextToken());
                        int b = Integer.parseInt(idata.nextToken());
                        int tourists = Integer.parseInt(idata.nextToken());
                        if(a == b){
                                System.out.println("Scenario #" + cases);
                                System.out.println("Minimum Number of Trips = " + 0);
                                System.out.println();
                        }
                        else{
                                int max = maxCapacity(nodeList, a, b);
                                trips(cases, tourists, max);
                        }
                }
                return;
        }

        int maxCapacity(Node[] nodeList, int a, int b) {
			PriorityQueue<Edge> edgeQ = new PriorityQueue<Edge>();
			ArrayList<Edge> temp  = nodeList[a].getEdges();
			nodeList[a].visit();
			for(int i = 0; i < temp.size(); i++){
				Edge e = temp.get(i);
				e.setUsed();
				edgeQ.add(e);
			}
			while(!edgeQ.isEmpty()){
				Edge e = edgeQ.poll();
				int n = e.getA();
				if(nodeList[n].visited()){
					n = e.getB();
				}
				if(nodeList[n].visited()){
				}
				else{
					nodeList[n].setValue(e.getValue());
					if(n == b){
						return nodeList[n].getValue();
					}
					nodeList[n].visit();
					temp  = nodeList[n].getEdges();
					for(int i = 0; i<temp.size(); i++){
						if(temp.get(i).used()){
						}
						else{
							temp.get(i).setUsed();
							temp.get(i).updateValue(nodeList[n].getValue());
							edgeQ.add(temp.get(i));
						}
					}
				}
			}

			return 0;
		}

		void trips(int cases, int tourists, int max) {
                int trip = 0;
                if(max < 2 && tourists > 0){ //There are no solution...
                        return;
                }
                while(tourists > 0){
                        tourists = tourists - max + 1;
                        trip++;
                }
                /*
                max--;
                trip = tourists / max + ((tourists % max == 0) ? 0 : 1);
                */
                System.out.println("Scenario #" + cases);
                System.out.println("Minimum Number of Trips = " + trip);
                System.out.println();

        }

        static class Node{
        	int index;
        	int value = -1;
        	boolean visited = false;
        	ArrayList<Edge> edgeList = new ArrayList<Edge>();
        	Node(int index){
        		this.index = index;
        	}
        	void setValue(int value){
        		this.value = value;
        	}
        	int getValue(){
        		return value;
        	}
        	void addEdge(Edge e){
        		edgeList.add(e);
        	}
        	void visit(){
        		visited = true;
        	}
        	boolean visited(){
        		return visited;
        	}

        	ArrayList<Edge> getEdges(){
        		return edgeList;
        	}
        }
        static class Edge implements Comparable{
        	int value;
        	int a;
        	int b;
        	boolean used = false;
        	Edge(int a, int b, int value){
        		this.value = value;
        		this.a = a;
        		this.b = b;
        	}

        	void updateValue(int v){
        		this.value = Math.min(value, v);
        	}
        	int getValue(){
        		return value;
        	}

        	boolean used(){
        		return used;
        	}
        	void setUsed(){
        		used = true;
        	}
        	int getA(){
        		return a;
        	}
        	int getB(){
        		return b;
        	}

			@Override
			public int compareTo(Object o) {
				if(this.getValue() > ((Edge) o).getValue()){
					return -1;
				}
				return 1;
			}

        }
}