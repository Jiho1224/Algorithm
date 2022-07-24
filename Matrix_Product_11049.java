package al_07_14;

import java.io.*;


public class Matrix_Product_11049 {
	
	public static int INF = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine()); //행렬의 개수
		
		if (N == 1) {
			bw.write("0");
			bw.flush();
			System.exit(0);
		}
		
		//순서대로 행렬 크기 정보를 담는다.
		int list[] = new int[N+1];
		
		for(int i = 0; i<N;i++) {
			String str = br.readLine();
			String[] sp = str.split(" ");

			int a = Integer.parseInt(sp[0]);
			int b = Integer.parseInt(sp[1]);
			
			list[i] = a;
			list[i+1] =b;
		}
		
		//dp 생성
		int dp[][] = new int[N][N];
		
		//(x,y) 좌표 차를 하나씩 늘려가며 좌표에 넣는다.		
		for(int i = 2; i < N+1;i++) {
			
			//N = 6이라고 가정했을 때
			//차 2 N-i = 6-2 = 4
			//(0,2) (1,3) (2,4) (3,5) 차례대로 채워넣는다.
			
			//i = 2, j = 0
			//dp[0][1] = inf
			// k = 0 일 때 --> dp[0][0] = 0(오른쪽) + dp[1][1] = 0 (아래쪽) +
			// 				list[0] (첫번째 row) * list[1] (첫번째 col, 두번째 row) * list[2] (두번째 col)
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
