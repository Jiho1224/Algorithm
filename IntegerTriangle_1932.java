package al_07_14;

import java.io.*;

public class IntegerTriangle_1932 {

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
		int n = Integer.parseInt(br.readLine()); // 삼각형의 크기
		
		//그래프 정보 입력
		int graph[][] = new int[n][n];
		for(int i = 0; i<n;i++) {
			String str = br.readLine();
			String[]sp = str.split(" ");
			for(int j = 0; j<sp.length;j++)
				graph[i][j] = Integer.parseInt(sp[j]);
		}
		
		//dp 정보 입력
		int dp[][] = new int[n][n];
		
		dp[0][0] = graph[0][0]; //초기값 저장
		int max = dp[0][0]; //가장 큰 합을 저장
		
		for(int i = 1; i<n;i++) {
			for(int j = 0; j<=i;j++) {
				
				//삼각형의 가장 앞에서는 오른쪽 위에서만 숫자를 받아올 수 있다.
				if(j == 0) {
					dp[i][j] = graph[i][j] + dp[i-1][j];
				}
				//삼각형의 가장 뒤에서는 왼쪽 위에서만 숫자를 받아올 수 있다.
				else if(j == i) {
					dp[i][j] = graph[i][j] + dp[i-1][j-1];
				}
				//삼각형의 양끝이 아닌 그 중간의 숫자들을 구하는 경우
				else {
					//오른쪽 위와 왼쪽 위 중 더 큰 숫자를 선택
					int tmp_max = dp[i-1][j-1] > dp[i-1][j]?dp[i-1][j-1]:dp[i-1][j];
					dp[i][j] = graph[i][j] + tmp_max;
				}
				max = max < dp[i][j]? dp[i][j]:max;
			}
		}
		
		bw.write(max+"\n");
		bw.flush();
	}

}
