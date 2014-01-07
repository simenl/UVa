import java.io.*;
import java.util.*;

// NOTE: I've used the skeleton from the sample Java code: http://online-judge.uva.es/problemset/data/p100.java.html
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
        String line;
        char letter;
        int wk = -1;
        int bk = -1;
        int k = 0;
        int d = 0;
        int[][] board = new int[8][8];
        while(k < 64){
        	d++;
			k = 0;
			for(int i = 0; i<8; i++){
				idata = new StringTokenizer (ReadLn(255));
				line = idata.nextToken();
				for(int j = 0; j<8; j++){
					letter = line.charAt(j);
					switch(letter){
						case '.': board[i][j] = 0; k++; break;
						case 'P': board[i][j] = 1; break;
						case 'p': board[i][j] = -1; break;
						case 'R': board[i][j] = 2; break;
						case 'r': board[i][j] = -2; break;
						case 'B': board[i][j] = 3; break;
						case 'b': board[i][j] = -3; break;
						case 'Q': board[i][j] = 6; break;
						case 'q': board[i][j] = -6; break;
						case 'K': board[i][j] = 5; wk = i*10 + j; break;
						case 'k': board[i][j] = -5; bk = i*10 + j; break;
						case 'N': board[i][j] = 7; break;
						case 'n': board[i][j] = -7; break;
					}
				}
			}
			if(k < 64){
				boardCheck(board, wk, bk, d);
				ReadLn(255);
			}
		}
    }
    void boardCheck(int[][] board, int wk, int bk, int d){
    	int i;
    	int j;
    	int k = 1;
    	boolean open = true;
    	if(bk != -1){
    		open = true;
    		k = 1;
    		i = bk/10;
    		j = bk%10;
    		while(open && (i-k) > -1){
    			int m = board[i][j]*board[i-k][j];
    			if(m > 0){
    				open = false;
    			}
    			if(m < 0){
    				if(k == 1){
    					if(Math.abs(board[i-k][j]) == 5){
        					System.out.println("Game #" + d + ": black king is in check.");
        					return;
    					}
    				}
    				if(Math.abs(board[i-k][j])%2 == 0){
    					System.out.println("Game #" + d + ": black king is in check.");
    					return;
    				}
    				open = false;
    			}
    			k++;
    		}
    		open = true;
    		k = 1;
    		while(open && (i+k) < 8){
    			int m = board[i][j]*board[i+k][j];
    			if(m > 0){
    				open = false;
    			}
    			if(m < 0){
    				if(k == 1){
    					if(Math.abs(board[i+k][j]) == 5){
        					System.out.println("Game #" + d + ": black king is in check.");
        					return;
    					}
    				}
    				if(Math.abs(board[i+k][j])%2 == 0){
    					System.out.println("Game #" + d + ": black king is in check.");
    					return;
    				}
    				open = false;
    			}
    			k++;
    		}
    		open = true;
    		k = 1;
    		while(open && (j-k) > -1){
    			int m = board[i][j]*board[i][j-k];
    			if(m > 0){
    				open = false;
    			}
    			if(m < 0){
    				if(k == 1){
    					if(Math.abs(board[i][j-k]) == 5){
        					System.out.println("Game #" + d + ": black king is in check.");
        					return;
    					}
    				}
    				if(Math.abs(board[i][j-k])%2 == 0){
    					System.out.println("Game #" + d + ": black king is in check.");
    					return;
    				}
    				open = false;
    			}
    			k++;
    		}
    		open = true;
    		k = 1;
    		while(open && (j+k) < 8){
    			int m = board[i][j]*board[i][j+k];
    			if(m > 0){
    				open = false;
    			}
    			if(m < 0){
    				if(k == 1){
    					if(Math.abs(board[i][j+k]) == 5){
        					System.out.println("Game #" + d + ": black king is in check.");
        					return;
    					}
    				}
    				if(Math.abs(board[i][j+k])%2 == 0){
    					System.out.println("Game #" + d + ": black king is in check.");
    					return;
    				}
    				open = false;
    			}
    			k++;
    		}
    		open = true;
    		k = 1;
    		while(open && ((i+k) < 8) && ((j+k) < 8)){
    			int m = board[i][j]*board[i+k][j+k];
    			if(m > 0){
    				open = false;
    			}
    			if(m < 0){
    				if(k == 1){
    					if(Math.abs(board[i+k][j+k]) == 1 || Math.abs(board[i+k][j+k]) == 5){
        					System.out.println("Game #" + d + ": black king is in check.");
        					return;
    					}
    				}
    				if(Math.abs(board[i+k][j+k])%3 == 0){
    					System.out.println("Game #" + d + ": black king is in check.");
    					return;
    				}
    				open = false;
    			}
    			k++;
    		}
    		open = true;
    		k = 1;
    		while(open && (i+k) < 8 && (j-k) > -1){
    			int m = board[i][j]*board[i+k][j-k];
    			if(m > 0){
    				open = false;
    			}
    			if(m < 0){
    				if(k == 1){
    					if(Math.abs(board[i+k][j-k]) == 1 || Math.abs(board[i+k][j-k]) == 5){
        					System.out.println("Game #" + d + ": black king is in check.");
        					return;
    					}
    				}
    				if(Math.abs(board[i+k][j-k])%3 == 0){
    					System.out.println("Game #" + d + ": black king is in check.");
    					return;
    				}
    				open = false;
    			}
    			k++;
    		}
    		open = true;
    		k = 1;
    		while(open && (i-k) > -1 && (j+k) < 8){
    			int m = board[i][j]*board[i-k][j+k];
    			if(m > 0){
    				open = false;
    			}
    			if(m < 0){
    				if(k == 1){
    					if(Math.abs(board[i-k][j+k]) == 5){
        					System.out.println("Game #" + d + ": black king is in check.");
        					return;
    					}
    				}
    				if(Math.abs(board[i-k][j+k])%3 == 0){
    					System.out.println("Game #" + d + ": black king is in check.");
    					return;
    				}
    				open = false;
    			}
    			k++;
    		}
    		open = true;
    		k = 1;
    		while(open && (i-k) > -1 && (j-k) > -1){
    			int m = board[i][j]*board[i-k][j-k];
    			if(m > 0){
    				open = false;
    			}
    			if(m < 0){
    				if(k == 1){
    					if(Math.abs(board[i-k][j-k]) == 5){
        					System.out.println("Game #" + d + ": black king is in check.");
        					return;
    					}
    				}
    				if(Math.abs(board[i-k][j-k])%3 == 0){
    					System.out.println("Game #" + d + ": black king is in check.");
    					return;
    				}
    				open = false;
    			}
    			k++;
    		}
    		if(i-2 > -1){
    			if(j-1 > -1){
    				if(board[i][j]*board[i-2][j-1] < 0 && Math.abs(board[i-2][j-1]) == 7){
    					System.out.println("Game #" + d + ": black king is in check.");
    					return;
    				}
    			}
    			if(j+1 < 8){
    				if(board[i][j]*board[i-2][j+1] < 0 && Math.abs(board[i-2][j+1]) == 7){
    					System.out.println("Game #" + d + ": black king is in check.");
    					return;
    				}
    			}
    		}
    		if(i-1 > -1){
    			if(j-2 > -1){
    				if(board[i][j]*board[i-1][j-2] < 0 && Math.abs(board[i-1][j-2]) == 7){
    					System.out.println("Game #" + d + ": black king is in check.");
    					return;
    				}
    			}
    			if(j+2 < 8){
    				if(board[i][j]*board[i-1][j+2] < 0 && Math.abs(board[i-1][j+2]) == 7){
    					System.out.println("Game #" + d + ": black king is in check.");
    					return;
    				}
    			}
    		}
    		if(i+2 < 8){
    			if(j-1 > -1){
    				if(board[i][j]*board[i+2][j-1] < 0 && Math.abs(board[i+2][j-1]) == 7){
    					System.out.println("Game #" + d + ": black king is in check.");
    					return;
    				}
    			}
    			if(j+1 < 8){
    				if(board[i][j]*board[i+2][j+1] < 0 && Math.abs(board[i+2][j+1]) == 7){
    					System.out.println("Game #" + d + ": black king is in check.");
    					return;
    				}
    			}
    		}
    		if(i+1 < 8){
    			if(j-2 > -1){
    				if(board[i][j]*board[i+1][j-2] < 0 && Math.abs(board[i+1][j-2]) == 7){
    					System.out.println("Game #" + d + ": black king is in check.");
    					return;
    				}
    			}
    			if(j+2 < 8){
    				if(board[i][j]*board[i+1][j+2] < 0 && Math.abs(board[i+1][j+2]) == 7){
    					System.out.println("Game #" + d + ": black king is in check.");
    					return;
    				}
    			}
    		}
    	}
    	if(wk != -1){
    		open = true;
    		k = 1;
    		i = wk/10;
    		j = wk%10;
    		while(open && (i-k) > -1){
    			int m = board[i][j]*board[i-k][j];
    			if(m > 0){
    				open = false;
    			}
    			if(m < 0){
    				if(k == 1){
    					if(Math.abs(board[i-k][j]) ==  5){
        					System.out.println("Game #" + d + ": white king is in check.");
        					return;
    					}
    				}
    				if(Math.abs(board[i-k][j])%2 == 0){
    					System.out.println("Game #" + d + ": white king is in check.");
    					return;
    				}
    				open = false;
    			}
    			k++;
    		}
    		open = true;
    		k = 1;
    		while(open && (i+k) < 8){
    			int m = board[i][j]*board[i+k][j];
    			if(m > 0){
    				open = false;
    			}
    			if(m < 0){
    				if(k == 1){
    					if(Math.abs(board[i+k][j]) == 5){
        					System.out.println("Game #" + d + ": white king is in check.");
        					return;
    					}
    				}
    				if(Math.abs(board[i+k][j])%2 == 0){
    					System.out.println("Game #" + d + ": white king is in check.");
    					return;
    				}
    				open = false;
    			}
    			k++;
    		}
    		open = true;
    		k = 1;
    		while(open && (j-k) > -1){
    			int m = board[i][j]*board[i][j-k];
    			if(m > 0){
    				open = false;
    			}
    			if(m < 0){
    				if(k == 1){
    					if(Math.abs(board[i][j-k]) ==  5){
        					System.out.println("Game #" + d + ": white king is in check.");
        					return;
    					}
    				}
    				if(Math.abs(board[i][j-k])%2 == 0){
    					System.out.println("Game #" + d + ": white king is in check.");
    					return;
    				}
    				open = false;
    			}
    			k++;
    		}
    		open = true;
    		k = 1;
    		while(open && (j+k) < 8){
    			int m = board[i][j]*board[i][j+k];
    			if(m > 0){
    				open = false;
    			}
    			if(m < 0){
    				if(k == 1){
    					if(Math.abs(board[i][j+k]) == 5){
        					System.out.println("Game #" + d + ": white king is in check.");
        					return;
    					}
    				}
    				if(Math.abs(board[i][j+k])%2 == 0){
    					System.out.println("Game #" + d + ": white king is in check.");
    					return;
    				}
    				open = false;
    			}
    			k++;
    		}
    		open = true;
    		k = 1;
    		while(open && (i+k) < 8 && (j+k) < 8){
    			int m = board[i][j]*board[i+k][j+k];
    			if(m > 0){
    				open = false;
    			}
    			if(m < 0){
    				if(k == 1){
    					if(Math.abs(board[i+k][j+k]) ==  5){
        					System.out.println("Game #" + d + ": white king is in check.");
        					return;
    					}
    				}
    				if(Math.abs(board[i+k][j+k])%3 == 0){
    					System.out.println("Game #" + d + ": white king is in check.");
    					return;
    				}
    				open = false;
    			}
    			k++;
    		}
    		open = true;
    		k = 1;
    		while(open && (i+k) < 8 && (j-k) > -1){
    			int m = board[i][j]*board[i+k][j-k];
    			if(m > 0){
    				open = false;
    			}
    			if(m < 0){
    				if(k == 1){
    					if(Math.abs(board[i+k][j-k]) == 5){
        					System.out.println("Game #" + d + ": white king is in check.");
        					return;
    					}
    				}
    				if(Math.abs(board[i+k][j-k])%3 == 0){
    					System.out.println("Game #" + d + ": white king is in check.");
    					return;
    				}
    				open = false;
    			}
    			k++;
    		}
    		open = true;
    		k = 1;
    		while(open && (i-k) > -1 && (j+k) < 8){
    			int m = board[i][j]*board[i-k][j+k];
    			if(m > 0){
    				open = false;
    			}
    			if(m < 0){
    				if(k == 1){
    					if(Math.abs(board[i-k][j+k]) == 1  || Math.abs(board[i-k][j+k]) == 5){
        					System.out.println("Game #" + d + ": white king is in check.");
        					return;
    					}
    				}
    				if(Math.abs(board[i-k][j+k])%3 == 0){
    					System.out.println("Game #" + d + ": white king is in check.");
    					return;
    				}
    				open = false;
    			}
    			k++;
    		}
    		open = true;
    		k = 1;
    		while(open && (i-k) > -1 && (j-k) > -1){
    			int m = board[i][j]*board[i-k][j-k];
    			if(m > 0){
    				open = false;
    			}
    			if(m < 0){
    				if(k == 1){
    					if(Math.abs(board[i-k][j-k]) == 1 || Math.abs(board[i-k][j-k]) == 5){
        					System.out.println("Game #" + d + ": white king is in check.");
        					return;
    					}
    				}
    				if(Math.abs(board[i-k][j-k])%3 == 0){
    					System.out.println("Game #" + d + ": white king is in check.");
    					return;
    				}
    				open = false;
    			}
    			k++;
    		}
    		if(i-2 > -1){
    			if(j-1 > -1){
    				if(board[i][j]*board[i-2][j-1] < 0 && Math.abs(board[i-2][j-1]) == 7){
    					System.out.println("Game #" + d + ": white king is in check.");
    					return;
    				}
    			}
    			if(j+1 < 8){
    				if(board[i][j]*board[i-2][j+1] < 0 && Math.abs(board[i-2][j+1]) == 7){
    					System.out.println("Game #" + d + ": white king is in check.");
    					return;
    				}
    			}
    		}
    		if(i-1 > -1){
    			if(j-2 > -1){
    				if(board[i][j]*board[i-1][j-2] < 0 && Math.abs(board[i-1][j-2]) == 7){
    					System.out.println("Game #" + d + ": white king is in check.");
    					return;
    				}
    			}
    			if(j+2 < 8){
    				if(board[i][j]*board[i-1][j+2] < 0 && Math.abs(board[i-1][j+2]) == 7){
    					System.out.println("Game #" + d + ": white king is in check.");
    					return;
    				}
    			}
    		}
    		if(i+2 < 8){
    			if(j-1 > -1){
    				if(board[i][j]*board[i+2][j-1] < 0 && Math.abs(board[i+2][j-1]) == 7){
    					System.out.println("Game #" + d + ": white king is in check.");
    					return;
    				}
    			}
    			if(j+1 < 8){
    				if(board[i][j]*board[i+2][j+1] < 0 && Math.abs(board[i+2][j+1]) == 7){
    					System.out.println("Game #" + d + ": white king is in check.");
    					return;
    				}
    			}
    		}
    		if(i+1 < 8){
    			if(j-2 > -1){
    				if(board[i][j]*board[i+1][j-2] < 0 && Math.abs(board[i+1][j-2]) == 7){
    					System.out.println("Game #" + d + ": white king is in check.");
    					return;
    				}
    			}
    			if(j+2 < 8){
    				if(board[i][j]*board[i+1][j+2] < 0 && Math.abs(board[i+1][j+2]) == 7){
    					System.out.println("Game #" + d + ": white king is in check.");
    					return;
    				}
    			}
    		}
    	}
		System.out.println("Game #" + d + ": no king is in check.");
		return;


    }
}

