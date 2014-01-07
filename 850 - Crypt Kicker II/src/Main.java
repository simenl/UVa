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
		String plainTxt = "the quick brown fox jumps over the lazy dog";
		boolean done;
		String temp;
		ArrayList<String> crypts;

		while ((input = Main.ReadLn (1000)) != null){
			idata = new StringTokenizer (input);
			int cases = Integer.parseInt(idata.nextToken());
			Main.ReadLn(1000);
			for(int i = 0; i<cases; i++){
				crypts = new ArrayList<String>();
				if(i != 0){
					System.out.println();
				}
				done = false;
				while(!done){
					if((temp = Main.ReadLn(1000)) == null|| !(idata = new StringTokenizer(temp)).hasMoreElements()){
						if(i == cases-1){
							findCrypt(plainTxt, crypts);
							System.exit(0);
						}
						done = true;
					}
					else{
						crypts.add(temp);
					}
				}
				findCrypt(plainTxt, crypts);
			}
			System.exit(0);

		}
		return;

	}
	void findCrypt(String plainTxt, ArrayList<String> crypts){
		char[] temp;
		char[] code = new char[26];
		boolean found = false;
		for(int i = 0; i<crypts.size(); i++){
			if(crypts.get(i).length() == plainTxt.length()){
				if(!((temp = charCodes(plainTxt, crypts.get(i)))[0] == 'Z')){
					code = temp;
					found = true;
					i = crypts.size();
				}
			}
		}
		if(!found){
			System.out.println("No solution.");
			return;
		}
		decrypter(crypts, code);
		return;
	}

	void decrypter(ArrayList<String> crypts, char[] code) {
		String sentence;
		StringTokenizer words;
		String word;
		boolean first;
		for(int i = 0; i<crypts.size(); i++){
			sentence = crypts.get(i);
			words = new StringTokenizer(sentence);
			/*if(i != 0){
				System.out.println();
			} */
			first = true;
			while(words.hasMoreTokens()){
				if(!first){
					System.out.print(" ");
				}
				else{
					first = false;
				}
				word = words.nextToken();
				for(int j = 0; j<word.length(); j++){
					System.out.print(code[word.charAt(j)-'a']);
				}
			}
			System.out.println();
		}
		return;

	}

	char[] charCodes(String plainTxt, String cryptTxt){
		char[] codes = new char[26];
		for(int i = 0; i<codes.length; i++){
			codes[i] = 'Z';
		}
		String p;
		String c;
		StringTokenizer plain = new StringTokenizer(plainTxt);
		StringTokenizer crypt = new StringTokenizer(cryptTxt);
		while(plain.hasMoreTokens() && crypt.hasMoreTokens()){
			p = plain.nextToken();
			c = crypt.nextToken();
			if(p.length() == c.length()){
				for(int i = 0; i < p.length(); i++){
					if(codes[c.charAt(i)-'a'] == 'Z'){
						codes[c.charAt(i)-'a'] = p.charAt(i);
					}
					else if(codes[c.charAt(i)-'a'] != p.charAt(i)){
						codes[0] = 'Z';
						return codes;
					}
				}
			}
			else{
				codes[0] = 'Z';
				return codes;
			}
		}
		if(plain.hasMoreTokens() || crypt.hasMoreTokens()){
			codes[0] = 'Z';
			return codes;
		}
		return codes;
	}
}
