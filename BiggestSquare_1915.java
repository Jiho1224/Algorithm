package al_07_14;

import java.io.*;

public class BiggestSquare_1915 {

	public static void main(String[] args)  throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		

		String str = br.readLine();
		String[] sp = str.split(" ");
		
		//입력 및 배열 저장
		int n =  Integer.parseInt(sp[0]); 
		int m = Integer.parseInt(sp[1]);
		
		int board[][] = new int[n][m];
		for(int i = 0; i<n;i++) {
			str = br.readLine();
			sp = str.split("");
			for(int j = 0; j<m;j++) {
				board[i][j] = Integer.parseInt(sp[j]);
			}
		}
		
		//(x,y)부터 시작해서 만들 수 있는 가장 큰 정사각형을 담는 배열
		int dp[][] = new int[n][m];
		int max = 0;
		
		for(int i = 0; i<n;i++) {
			for(int j = 0; j<m;j++) {
				dp[i][j] = board[i][j];
				
				if(max == 0 && dp[i][j] == 1) max = 1;
				
				//두 번째 행,열부터
				//오른쪽(i,j-1) 위쪽 (i-1,j) 대각선 좌측상단 (i-1,j-1)
				//이 모두 0보다 크다면 그 중 가장 큰 값 +1
				if(j>0 && i>0) {
					if(dp[i][j-1] > 0 && dp[i-1][j] >0 && dp[i-1][j-1] >0 && dp[i][j] == 1) {
						int tmp_max = min(dp[i][j-1],dp[i-1][j],dp[i-1][j-1]);
						dp[i][j] = tmp_max + 1;
						max = max > dp[i][j] ? max : dp[i][j];
					}
				}
			}
		}
		
		bw.write(max*max+"\n");
		bw.flush();
	}
	
	public static int min(int a, int b, int c) {
		if(b >= a && c >= a) return a;
		else if (a > b && c >= b) return b;
		else return c;
	}

}
