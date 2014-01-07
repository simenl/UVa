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
			int cases = Integer.parseInt(idata.nextToken());
			for(int i = 0; i<cases; i++){
				if(i != 0){
					System.out.println();
				}
				Main.ReadLn(255);
				idata = new StringTokenizer(Main.ReadLn(255));
				int y = Integer.parseInt(idata.nextToken());
				int x = Integer.parseInt(idata.nextToken());
				char[][] table = new char[y][x];
				for(int j = 0; j<y; j++){
					String temp = Main.ReadLn(255).toLowerCase();
					for(int k = 0; k<x; k++){
						table[j][k] = temp.charAt(k);
					}
				}
				idata = new StringTokenizer(Main.ReadLn(255));
				int numWords = Integer.parseInt(idata.nextToken());
				String[] words = new String[numWords];
				for(int j = 0; j<numWords; j++){
					words[j] = Main.ReadLn(255).toLowerCase();
				}
				findWords(words, table);
			}
			return;


		}
		return;

	}

	void findWords(String[] words, char[][] table){
		for(int i = 0; i<words.length; i++){
			wordFinder(words[i], table);
		}
	}

	void wordFinder(String word, char[][] table){
		for(int i = 0; i<table.length; i++){
			for(int j = 0; j<table[0].length; j++){
				if(table[i][j] == word.charAt(0)){
					if(i-word.length() + 2 >0){
						if(j-word.length() + 2 >0){
							if(upleft(word, table, i, j)){
								return;
							}
						}
						if(upwards(word, table, i, j)){
							return;
						}
						if(j+word.length() - 1 < table[0].length){
							if(upright(word, table, i, j)){
								return;
							}
						}
					}
					if(j-word.length() + 2 >0){
						if(leftwards(word, table, i, j)){
							return;
						}
					}
					if(j+word.length() - 1  < table[0].length){
						if(rightwards(word, table, i, j)){
							return;
						}
					}
					if(i+word.length() -1 <table.length){
						if(j-word.length() + 2 >0){
							if(downleft(word, table, i, j)){
								return;
							}
						}
						if(downwards(word, table, i, j)){
							return;
						}
						if(j+word.length()-1 < table[0].length){
							if(downright(word, table, i, j)){
								return;
							}
						}
					}
				}
			}
		}
	}
	boolean upleft(String word, char[][] table, int y, int x){
		for(int k = 1; k<word.length(); k++){
			if(table[y-k][x-k] != word.charAt(k)){
				return false;
			}
		}
		System.out.println(y+1 + " " + (x+1));
		return true;
	}
	boolean upwards(String word, char[][] table ,int y, int x){
		for(int k = 1; k<word.length(); k++){
			if(table[y-k][x] != word.charAt(k)){
				return false;
			}
		}
		System.out.println(y+1 + " " + (x+1));
		return true;
	}
	boolean upright(String word, char[][] table, int y, int x){
		for(int k = 1; k<word.length(); k++){
			if(table[y-k][x+k] != word.charAt(k)){
				return false;
			}
		}
		System.out.println(y+1 + " " + (x+1));
		return true;
	}
	boolean leftwards(String word, char[][] table, int y, int x){
		for(int k = 1; k<word.length(); k++){
			if(table[y][x-k] != word.charAt(k)){
				return false;
			}
		}
		System.out.println(y+1 + " " + (x+1));
		return true;
	}
	boolean rightwards(String word, char[][] table, int y, int x){
		for(int k = 1; k<word.length(); k++){
			if(table[y][x+k] != word.charAt(k)){
				return false;
			}
		}
		System.out.println(y+1 + " " + (x+1));
		return true;
	}
	boolean downleft(String word, char[][] table, int y, int x){
		for(int k = 1; k<word.length(); k++){
			if(table[y+k][x-k] != word.charAt(k)){
				return false;
			}
		}
		System.out.println(y+1 + " " + (x+1));
		return true;
	}
	boolean downwards(String word, char[][] table, int y, int x){
		for(int k = 1; k<word.length(); k++){
			if(table[y+k][x] != word.charAt(k)){
				return false;
			}
		}
		System.out.println(y+1 + " " + (x+1));
		return true;
	}
	boolean downright(String word, char[][] table, int y, int x){
		for(int k = 1; k<word.length(); k++){
			if(table[y+k][x+k] != word.charAt(k)){
				return false;
			}
		}
		System.out.println(y+1 + " " + (x+1));
		return true;
	}
}
