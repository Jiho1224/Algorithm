package al_07_04;

import java.io.*;
import java.util.*;

public class Marbel_Escape2_13460 {
	
	public static char board[][];
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine();
		String[] sp = str.split(" ");
		
		int row = Integer.parseInt(sp[0]);
		int col = Integer.parseInt(sp[1]);
		
		//board 모양 저장
		board = new char[row][col];
		for(int i = 0; i<row;i++) {
			str = br.readLine();
			for(int j = 0; j<col;j++) {
				board[i][j] = str.charAt(j);
			}
		}
		
		

	}

}
