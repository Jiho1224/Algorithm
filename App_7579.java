package al_07_15;

import java.io.*;

public class App_7579 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// Input  /////////////////////////////////////////////////
		
		String str = br.readLine();
		String[] sp = str.split(" ");
		
		int N = Integer.parseInt(sp[0]); //���� ����
		long M = Long.parseLong(sp[1]); //�߰��� �ʿ��� �޸�
		
		long memory[] = new long[N]; //���� ���� ����ϴ� �޸� ����Ʈ
		int cost[] = new int[N]; //����� �� �߰����� �ڽ�Ʈ
		
		str = br.readLine();
		sp = str.split(" ");
		
		for(int i = 0; i<N;i++) {
			memory[i] = Long.parseLong(sp[i]);
		}
		
		str = br.readLine();
		sp = str.split(" ");
		
		int total = 0;
		for(int i = 0; i<N;i++) {
			cost[i] = Integer.parseInt(sp[i]);
			total += cost[i];
		}
		
		
		// dp  ////////////////////////////////////////////////////
		
		long dp[][] = new long[N][total+1]; //[i][j] : i���� ���ý� j cost�� �Ἥ Ȯ���� �� �ִ� �ִ� memory
		
		
		// loop ///////////////////////////////////////////////////
		
		//�޸� ������ŭ ������.
		dp[0][cost[0]] = memory[0]; //ù��° ���� �ʱ�ȭ
		
		for(int i = 1; i<N;i++) {
				
			for(int j = 0; j<=total;j++) {
				//i��° ���� cost���� j cost�� ���� ���� 
				//i��° ���� �ƿ� �������� ���ϴ� ��Ȳ�̹Ƿ� �׳� ������ dp table�� �״�� �־��ش�.
				if(j<cost[i]) dp[i][j] = dp[i-1][j];
				//i��° ���� ������ �� ���� ��
				else {
					//i-1��° ���� j cost �������� �� �޸� �� vs. i��° ���� ���� + j-cost ��ŭ ���� ������
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-cost[i]]+memory[i]);
				}
			}
		}
	   
		
	    // �ּ� M ����Ʈ �ּ� cost //////////////////////////////////////
		
		int result = Integer.MAX_VALUE;
	    for(int i = 0; i<N;i++) {
	    	
		    for(int j = 0; j <= total; j++) {
			  if (dp[i][j] >= M) result = Math.min(j, result);
		    }
	    }
	    
	    if (N == 0) result = cost[0];
	    
	    
	    // ���  ///////////////////////////////////////////////////
	    
	    bw.write(result+"");
	    bw.flush();
			
	
	}

}
