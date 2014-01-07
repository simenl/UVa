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
                        int n = Integer.parseInt(idata.nextToken());
                        if(n == 0){
                                return;
                        }
                        idata = new StringTokenizer(Main.ReadLn(255));
                        int l = Integer.parseInt(idata.nextToken());
                        char[] coloring = new char[n];
                        boolean[][] neighbour = new boolean[n][n];
                        for(int i = 0; i < l; i++){
                                idata = new StringTokenizer(Main.ReadLn(255));
                                int a = Integer.parseInt(idata.nextToken());
                                int b = Integer.parseInt(idata.nextToken());
                                neighbour[a][b] = true;
                                neighbour[b][a] = true;
                        }
                        coloring[0] = 'R';
                        if(bipartit(neighbour, coloring, 0)){
                                System.out.println("BICOLORABLE.");
                        }
                        else{
                                System.out.println("NOT BICOLORABLE.");
                        }
                }
                return;
        }

        boolean bipartit(boolean[][] neighbour, char[] coloring, int x) {
                for(int j = 0; j<neighbour.length; j++){
                        if(neighbour[x][j]){
                                neighbour[x][j] = false;
                                neighbour[j][x] = false;
                                if(coloring[x] == coloring[j]){
                                        return false;
                                }
                                if(coloring[x] == 'R'){
                                        coloring[j] = 'B';
                                        if(!bipartit(neighbour, coloring, j)){
                                                return false;
                                        }
                                }
                                else{
                                        coloring[j] = 'R';
                                        if(!bipartit(neighbour, coloring, j)){
                                                return false;
                                        }
                                }
                        }
                }
                return true;

        }
}
