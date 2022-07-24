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
		
		//����� ���� ������� �Է¹ް� ����
		for(int i = 1; i<=n;i++) {
			stair[i] = Integer.parseInt(br.readLine());
		}
		dp[0] = 0;
		dp[1] = stair[1];
		
		if(n>=2) dp[2] = stair[1]+stair[2];
		if(n>=3) {
			for(int i = 3; i<=n;i++) dp[i] = -1;
		}
		//�� ������ ��ܿ����� �ִ� ���� ���Ѵ�. (top-down)
		int result = find(n);
		
		bw.write(result+"\n");
		bw.flush();
	}
	
	public static int find(int n) {
		
		//���� dp�� ���ŵ��� �ʾ��ִٸ�
		if(dp[n] == -1) {
			//�� ĭ -> (�� ĭ ����) -> ���� ĭ
			//����ĭ -> (�� ĭ ����) -> ���� ĭ
			//�� ū ���� dp�� ����
			dp[n] = Math.max(find(n-3)+stair[n-1],find(n-2)) + stair[n];
		}
		//�̹� �ִ� dp[n] Ȥ�� ���� ���� dp[n] �� ���
		return dp[n];
	}
}
