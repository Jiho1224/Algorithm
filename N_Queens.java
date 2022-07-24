package al_07_04;

import java.io.*;
import java.util.*;

public class N_Queens {
	public static int N;
	public static int[] board;
	public static int ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		ans = 0;
		N = Integer.parseInt(str);
		
		board = new int[N];
		
		N_Queen(0);
		System.out.println(ans);
		
	}
	
	public static void N_Queen(int row) {
		if(row == N) {
			ans++;
			return;
		}
		
		for(int i = 0 ; i<N;i++) {
			board[row] = i;
			if(is_attack(row) == false) {
				N_Queen(row+1);
			}
		}
	}
	
	public static boolean is_attack(int row) {
		for(int i =0; i<row;i++) {
			if(board[row] == board[i])
				return true;
			
			if(Math.abs(row-i) == Math.abs(board[i]-board[row])) {
				return true;
			}
		}
		return false;
	}
}
