package al_07_08;

import java.io.*;

public class first_Grade_5557 {
	public static int num;
	public static int[] list;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		num = Integer.parseInt(br.readLine()); //���� ���� �Է�
		String str= br.readLine();
		String[] sp = str.split(" ");
		
		// ���� ����
		list = new int[num]; 
		for(int i = 0;i<num;i++) {
			list[i] = Integer.parseInt(sp[i]);
		}
		
		//���� ������ dp ����
		long[][] dp = new long[100][21]; 
		
		dp[0][list[0]] = 1;
		
		for(int i = 1; i<num;i++) {
			for(int j = 0; j<21;j++) {
				//������ ���� 0�� �ƴ϶�� --> ���´ٸ�
				long tmp = dp[i-1][j];
				if(tmp != 0) {
					int tmp2 = list[i];
					if (j-tmp2 >= 0) dp[i][j-tmp2] += tmp;
					if (j+tmp2 <= 20) dp[i][j+tmp2] += tmp;
				}
			}
		}
		
		bw.write(dp[num-2][list[num-1]]+"");
		bw.flush();
	}
	
	
}
