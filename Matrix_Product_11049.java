package al_07_14;

import java.io.*;


public class Matrix_Product_11049 {
	
	public static int INF = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine()); //����� ����
		
		if (N == 1) {
			bw.write("0");
			bw.flush();
			System.exit(0);
		}
		
		//������� ��� ũ�� ������ ��´�.
		int list[] = new int[N+1];
		
		for(int i = 0; i<N;i++) {
			String str = br.readLine();
			String[] sp = str.split(" ");

			int a = Integer.parseInt(sp[0]);
			int b = Integer.parseInt(sp[1]);
			
			list[i] = a;
			list[i+1] =b;
		}
		
		//dp ����
		int dp[][] = new int[N][N];
		
		//(x,y) ��ǥ ���� �ϳ��� �÷����� ��ǥ�� �ִ´�.		
		for(int i = 2; i < N+1;i++) {
			
			//N = 6�̶�� �������� ��
			//�� 2 N-i = 6-2 = 4
			//(0,2) (1,3) (2,4) (3,5) ���ʴ�� ä���ִ´�.
			
			//i = 2, j = 0
			//dp[0][1] = inf
			// k = 0 �� �� --> dp[0][0] = 0(������) + dp[1][1] = 0 (�Ʒ���) +
			// 				list[0] (ù��° row) * list[1] (ù��° col, �ι�° row) * list[2] (�ι�° col)
			for(int j = 0; j<N-i+1;j++) {
				
				dp[j][j+i-1] = INF;
				for(int k = j; k<j+i-1; k++) {
					int tmp = dp[j][k] + dp[k+1][j+i-1]+(list[j]*list[k+1]*list[j+i]);
					dp[j][j+i-1] = Math.min(tmp, dp[j][j+i-1]);
				}
			}
		}
		
		int result = dp[0][N-1];
		bw.write(result+"\n");
		bw.flush();

	}

}
