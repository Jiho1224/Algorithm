package al_07_14;

import java.io.*;

public class Stair_2579 {
	
	public static int[] stair;
	public static int[] dp;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		
		stair = new int[n+1];
		dp = new int[n+1];
		
		//계단의 높이 순서대로 입력받고 저장
		for(int i = 1; i<=n;i++) {
			stair[i] = Integer.parseInt(br.readLine());
		}
		dp[0] = 0;
		dp[1] = stair[1];
		
		if(n>=2) dp[2] = stair[1]+stair[2];
		if(n>=3) {
			for(int i = 3; i<=n;i++) dp[i] = -1;
		}
		//맨 마지막 계단에서의 최대 합을 구한다. (top-down)
		int result = find(n);
		
		bw.write(result+"\n");
		bw.flush();
	}
	
	public static int find(int n) {
		
		//만약 dp가 갱신되지 않아있다면
		if(dp[n] == -1) {
			//전 칸 -> (한 칸 점프) -> 현재 칸
			//전전칸 -> (두 칸 점프) -> 현재 칸
			//중 큰 값을 dp에 저장
			dp[n] = Math.max(find(n-3)+stair[n-1],find(n-2)) + stair[n];
		}
		//이미 있는 dp[n] 혹은 갱신 후의 dp[n] 을 출력
		return dp[n];
	}
}
