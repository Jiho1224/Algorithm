package al_07_14;

import java.io.*;

public class IntegerTriangle_1932 {

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
		int n = Integer.parseInt(br.readLine()); // �ﰢ���� ũ��
		
		//�׷��� ���� �Է�
		int graph[][] = new int[n][n];
		for(int i = 0; i<n;i++) {
			String str = br.readLine();
			String[]sp = str.split(" ");
			for(int j = 0; j<sp.length;j++)
				graph[i][j] = Integer.parseInt(sp[j]);
		}
		
		//dp ���� �Է�
		int dp[][] = new int[n][n];
		
		dp[0][0] = graph[0][0]; //�ʱⰪ ����
		int max = dp[0][0]; //���� ū ���� ����
		
		for(int i = 1; i<n;i++) {
			for(int j = 0; j<=i;j++) {
				
				//�ﰢ���� ���� �տ����� ������ �������� ���ڸ� �޾ƿ� �� �ִ�.
				if(j == 0) {
					dp[i][j] = graph[i][j] + dp[i-1][j];
				}
				//�ﰢ���� ���� �ڿ����� ���� �������� ���ڸ� �޾ƿ� �� �ִ�.
				else if(j == i) {
					dp[i][j] = graph[i][j] + dp[i-1][j-1];
				}
				//�ﰢ���� �糡�� �ƴ� �� �߰��� ���ڵ��� ���ϴ� ���
				else {
					//������ ���� ���� �� �� �� ū ���ڸ� ����
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
