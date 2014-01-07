
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
			Main.ReadLn(255);
			for(int i = 0; i<cases; i++){
				if(i != 0){
					System.out.println();
				}
				int[] price = new int[24];
				ArrayList<photoRecords> pr = new ArrayList<photoRecords>();
				idata = new StringTokenizer(Main.ReadLn(2555));
				for(int j = 0; j<24; j++){
					price[j] = Integer.parseInt(idata.nextToken());
				}
				while(((input = Main.ReadLn(255)) != null) && (idata = new StringTokenizer(input)).hasMoreTokens()){
					if(idata.hasMoreTokens()){
						String lnum = idata.nextToken();
						int date = Integer.parseInt(idata.nextToken().replaceAll("[^0-9]", ""));
						boolean enter = false;
						if(idata.nextToken().equals("enter")){
							enter = true;
						}
						int km = Integer.parseInt(idata.nextToken());
						pr.add(new photoRecords(lnum, date, enter, km));
					}
				}
				quicksort(pr, 0, pr.size()-1);
				toll(pr, price);
			}
			return;

		}
		return;
	}

	void toll(ArrayList<photoRecords> pr, int[] price) {
		String lnum = pr.get(0).getlnum();
		boolean enter = pr.get(0).enter();
		int km = pr.get(0).getkm();
		int hour = (pr.get(0).getdate()/100)%100;
		int fee = 200;
		for(int i = 1; i< pr.size(); i++){
			if(lnum.equals(pr.get(i).getlnum())){
				if(enter){
					if(!pr.get(i).enter()){
						fee += price[hour]*Math.abs(km-pr.get(i).getkm()) + 100;
						enter = false;
					}
					else{
						km = pr.get(i).getkm();
						hour = (pr.get(i).getdate()/100)%100;
					}
				}
				else{
					enter = pr.get(i).enter();
					km = pr.get(i).getkm();
					hour = (pr.get(i).getdate()/100)%100;
				}
			}
			else{
				if(fee > 200){
					printFee(lnum, fee);
				}
				lnum = pr.get(i).getlnum();
				hour = (pr.get(i).getdate()/100)%100;
				km = pr.get(i).getkm();
				fee = 200;
				enter = pr.get(i).enter();
			}
		}
		if(fee > 200){
			printFee(lnum, fee);
		}


		/*while(i < pr.size()){
			boolean enter;
			String lnum = pr.get(i).getlnum();
			enter = pr.get(i).enter();
			int km = pr.get(i).getkm();
			int date = (pr.get(i).getdate()/100)%100;
			int fee = 0;
			while(++i < pr.size() && lnum.equals(pr.get(i).getlnum())){
				if(!enter){
					while(++i < pr.size() && !enter && lnum.equals(pr.get(i).getlnum())){
						enter = pr.get(i).enter();
						km = pr.get(i).getkm();
						date = (pr.get(i).getdate()/100)%100;
					}
				}
				if(enter){
					if(i < pr.size() && pr.get(i).enter() && lnum.equals(pr.get(i).getlnum())){
						enter = pr.get(i).enter();
						km = pr.get(i).getkm();
						date = (pr.get(i).getdate()/100)%100;
					}
					else if(i < pr.size() && lnum.equals(pr.get(i).getlnum())){
						km = Math.abs(km-pr.get(i).getkm());
						fee += price[date]*km;
					}
				}
			}
			double total = fee/100;
			System.out.printf("%s $%.2f\n",lnum, total);
		}
		 */

	}

	void printFee(String lnum, int fee) {
		double total = (double)fee/100;
		System.out.printf("%s $%.2f\n",lnum, total);
	}

	/**
	 * Compares two photorecords, and returns true if a "first".
	 * @param a
	 * @param b
	 * @return
	 */
	boolean compare(photoRecords a, photoRecords b){
		if(a.getlnum().compareTo(b.getlnum()) < 0){
			return false;
		}
		if(a.getlnum().compareTo(b.getlnum()) > 0){
			return true;
		}
		if(a.getdate() - b.getdate() < 0){
			return false;
		}
		if(a.getdate() - b.getdate() > 0){
			return true;
		}
		return a.enter();
	}

	void quicksort(ArrayList<photoRecords> list, int a, int b) {
		if(a >= b){
			return;
		}
		photoRecords p = list.get(b);
		int l = a;
		int r = b - 1;
		while (l <= r) {
			while ((l <= r) && !compare(list.get(l), p)) {
				l++;
			}
			while ((r >= l) && compare(list.get(r), p)) {
				r--;
			}
			if (l < r) {
				photoRecords k = list.get(l);
				list.set(l, list.get(r));
				list.set(r, k);
			}
		}
		photoRecords k = list.get(l);
		list.set(l, list.get(b));
		list.set(b, k);
		quicksort(list, a, l - 1);
		quicksort(list, l + 1, b);

	}

	static class photoRecords{
		String lnum;
		int date;
		boolean enter;
		int km;

		photoRecords(String lnum, int date, boolean enter, int km){
			this.lnum = lnum;
			this.date = date;
			this.enter = enter;
			this.km = km;
		}

		String getlnum(){
			return lnum;
		}

		int getdate(){
			return date;
		}

		boolean enter(){
			return enter;
		}

		int getkm(){
			return km;
		}
	}

}

