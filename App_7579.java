package al_07_15;

import java.io.*;

public class App_7579 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// Input  /////////////////////////////////////////////////
		
		String str = br.readLine();
		String[] sp = str.split(" ");
		
		int N = Integer.parseInt(sp[0]); //앱의 개수
		long M = Long.parseLong(sp[1]); //추가로 필요한 메모리
		
		long memory[] = new long[N]; //각각 앱이 사용하는 메모리 바이트
		int cost[] = new int[N]; //재실행 시 추가적인 코스트
		
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
		
		long dp[][] = new long[N][total+1]; //[i][j] : i까지 선택시 j cost를 써서 확보할 수 있는 최대 memory
		
		
		// loop ///////////////////////////////////////////////////
		
		//메모리 개수만큼 돌린다.
		dp[0][cost[0]] = memory[0]; //첫번째 줄을 초기화
		
		for(int i = 1; i<N;i++) {
				
			for(int j = 0; j<=total;j++) {
				//i번째 앱의 cost보다 j cost가 작을 때는 
				//i번째 앱을 아예 선택하지 못하는 상황이므로 그냥 이전의 dp table을 그대로 넣어준다.
				if(j<cost[i]) dp[i][j] = dp[i-1][j];
				//i번째 앱을 선택할 수 있을 때
				else {
					//i-1번째 앱의 j cost 선택했을 때 메모리 값 vs. i번째 앱을 선택 + j-cost 만큼 전의 데이터
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-cost[i]]+memory[i]);
				}
			}
		}
	   
		
	    // 최소 M 바이트 최소 cost //////////////////////////////////////
		
		int result = Integer.MAX_VALUE;
	    for(int i = 0; i<N;i++) {
	    	
		    for(int j = 0; j <= total; j++) {
			  if (dp[i][j] >= M) result = Math.min(j, result);
		    }
	    }
	    
	    if (N == 0) result = cost[0];
	    
	    
	    // 출력  ///////////////////////////////////////////////////
	    
	    bw.write(result+"");
	    bw.flush();
			
	
	}

}
