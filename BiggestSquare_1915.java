package al_07_14;

import java.io.*;

public class BiggestSquare_1915 {

	public static void main(String[] args)  throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		

		String str = br.readLine();
		String[] sp = str.split(" ");
		
		//�Է� �� �迭 ����
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
		
		//(x,y)���� �����ؼ� ���� �� �ִ� ���� ū ���簢���� ��� �迭
		int dp[][] = new int[n][m];
		int max = 0;
		
		for(int i = 0; i<n;i++) {
			for(int j = 0; j<m;j++) {
				dp[i][j] = board[i][j];
				
				if(max == 0 && dp[i][j] == 1) max = 1;
				
				//�� ��° ��,������
				//������(i,j-1) ���� (i-1,j) �밢�� ������� (i-1,j-1)
				//�� ��� 0���� ũ�ٸ� �� �� ���� ū �� +1
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
