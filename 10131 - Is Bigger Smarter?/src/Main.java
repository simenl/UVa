import java.io.*;
import java.util.*;

class Main
{
    static String ReadLn (int maxLg)  // utility function to read from stdin
    {
        byte lin[] = new byte [maxLg];
        int lg = 0, car = -1;
        String line = "";

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
        String input;
        StringTokenizer idata;
        ArrayList<pair> elephants = new ArrayList<pair>();
        int count = 1;
        while ((input = Main.ReadLn (255)) != null)
        {
          idata = new StringTokenizer (input);
          if(!idata.hasMoreTokens()){
                  findSeq(elephants);
                  return;
          }
          int weight = Integer.parseInt(idata.nextToken());
          int iq = Integer.parseInt(idata.nextToken());
          elephants.add(new pair(weight, iq, count));
          count++;
        }
        findSeq(elephants);
        return;

    }

    void findSeq(ArrayList<pair> elephants) {
                Collections.sort(elephants);
                int[] values = new int[elephants.size()];
                int[] parent = new int[elephants.size()];
                for(int i = 0; i<elephants.size(); i++){
                        values[i] = 1;
                        parent[i] = i;
                        for(int j = 0; j < i; j++){
                                if(elephants.get(i).getIq() > elephants.get(j).getIq() && elephants.get(i).getWeight() < elephants.get(j).getWeight()){
                                        if(values[j] + 1 > values[i]){
                                                values[i] = values[j] + 1;
                                                parent[i] = j;
                                        }
                                }
                        }
                }
                int max = 0;
                int index = 0;
                for(int i = 0; i<values.length; i++){
                        if(values[i] > max){
                                index = i;
                                max = values[i];
                        }
                }
                System.out.println(max);
                while(parent[index] != index){
                        System.out.println(elephants.get(index).getIndex());
                        index = parent[index];
                }
                System.out.println(elephants.get(index).getIndex());
        }

        static class pair implements Comparable{
            int weight;
            int iq;
            int index;

                pair(int weight, int iq, int index){
                    this.weight = weight;
                    this.iq = iq;
                    this.index = index;
            }

                int getWeight(){
                        return weight;
                }
                int getIq(){
                        return iq;
                }
                int getIndex(){
                        return index;
                }

                @Override
                public int compareTo(Object o) {
                        if(this.weight > ((pair) o).getWeight()){
                                return -1;
                        }
                        return 1;
                }
    }
}