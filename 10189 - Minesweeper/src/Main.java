import java.util.ArrayList;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int n = 0;
		int m = 1;
		while(n < args.length){
			int height = Integer.parseInt(args[n]);
			int length = Integer.parseInt(args[n + 1]);
			n += 2;
			ArrayList<ArrayList<String>> y = new ArrayList<ArrayList<String>>();
			for(int i = 0 ; i < height; i++){
				ArrayList<String> x = new ArrayList<String>();
				for(int j = 0; j < length; j++){
					x.add(Character.toString(args[n + i].charAt(j)));
				}
				y.add(x);
			}
			if(!y.isEmpty()){
				minesweeper(length, height, y, m);
			}
			m++;
			n += height;
		}

	}

	public static void minesweeper(int length, int height, ArrayList<ArrayList<String>> l, int m){
		for(int i = 0; i < height; i++){
			for(int j = 0; j < length; j++){
				if(!l.get(i).get(j).equals("*")){
					int n = 0;
					if(j > 0){
						if(l.get(i).get(j-1).equals("*")){
							n++;
						}
					}
					if(j < length -1 ){
						if(l.get(i).get(j+1).equals("*")){
							n++;
						}
					}
					if(i > 0){
						if(l.get(i-1).get(j).equals("*")){
							n++;
						}
						if(j>0){
							if(l.get(i-1).get(j-1).equals("*")){
								n++;
							}
						}
						if(j< length - 1){
							if(l.get(i-1).get(j+1).equals("*")){
								n++;
							}
						}
					}
					if(i < height - 1){
						if(l.get(i+1).get(j).equals("*")){
							n++;
						}
						if(j>0){
							if(l.get(i+1).get(j-1).equals("*")){
								n++;
							}
						}
						if(j< length - 1){
							if(l.get(i+1).get(j+1).equals("*")){
								n++;
							}
						}
					}
					l.get(i).set(j, Integer.toString(n));
				}
			}
		}
		System.out.println("Field #" + m + ":");
		for(int i = 0; i < height; i++){
			for(int j = 0; j < length; j++){
				System.out.print(l.get(i).get(j));
			}
			System.out.println();
		}

	}
}
