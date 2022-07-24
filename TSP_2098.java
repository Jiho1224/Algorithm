package al_07_15;

import java.io.*;

public class TSP_2098 {
	
	public static int SIZE = (1<<16);
	public static int INF = 20000000;
	
	public static int dp[][] = new int[16][SIZE];
	public static int [][] matrix;
	public static int N;
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// INPUT ///////////////////////////////////////////////////////
		
		N = Integer.parseInt(br.readLine()); // 도시의 수
		
		matrix = new int[N][N];
		
		
		for(int i = 0; i<N;i++) {
			String str = br.readLine();
			String[] sp = str.split(" ");
			for(int j = 0; j<N;j++) {
				matrix[i][j] = Integer.parseInt(sp[j]);
				
			}		
			
		}
		
		for(int i = 0; i<N;i++) {
			for(int j = 0; j < (1<<N)-1;j++) dp[i][j] = -1;
		}
		
		// Travel Start! ///////////////////////////////////////////////
		
		//0번 도시부터 탐색 시작 (어디서부터 시작해도 상관없다)
		//check는 0번만 이미 탐색한 것이므로 0001 즉, 2^(0) = 1로 설정
		int result = travel(0,1);
		
		// 출력 ////////////////////////////////////////////////////////
		
		bw.write(result+"\n");
		
		br.close();
		bw.flush();

	}
	public static int travel(int x, int check) {
		//모든 도시를 방문하였다면 --> (1111..)일 것이다 == 2^(N)-1
		if(check == (1<<N)-1) {
			if(matrix[x][0] == 0) return INF; //경로가 없다는 뜻
			else return matrix[x][0]; //경로가 있을 경우 최단 거리 return
		}
		
		//만약 이미 방문한 도시라면
		if(dp[x][check] != -1) return dp[x][check]; //계산해놓은 값을 반환
		
		dp[x][check] = INF; //방문 체크
		
		//나머지 도시들로 이루어진 subset 탐색
		for(int i = 0; i<N;i++) {
			int nexti = check | (1<<i); //한 칸 씩 미뤄가면서 다른 도시 판단
			
			// 만약 경로가 없어서 INF가 찍혀있거나
			// 이미 i 도시를 방문한 경우 (현재 도시 check와 i도시를 & 연산자 취해서 0이 아닌 1이 나온다면
			// i도시는 이미 check의 subset과 마찬가지이므로
			
			if(matrix[x][i] == 0 || (check &(1<<i)) != 0) continue;
			
			dp[x][check] = Math.min(dp[x][check], travel(i,nexti) + matrix[x][i]);
		}
		return dp[x][check];
	}
	
	

}
