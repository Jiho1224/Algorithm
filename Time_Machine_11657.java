package al_07_13;

import java.io.*;


public class Time_Machine_11657 {
	public static int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine();
		String[] sp = str.split(" ");
		
		int N = Integer.parseInt(sp[0]); // 도시의 개수
		int M = Integer.parseInt(sp[1]); // 버스 노선의 개수
		
		//간선 리스트 생성
		int edge[][] = new int[M+1][3]; //[0] 출발 [1] 도착 [2] cost
		
		//간선 정보 받기
		for(int i = 0; i<M; i++) {
			str = br.readLine();
			sp = str.split(" ");
			
			// a b c <=> a->b (cost: c)
			int a = Integer.parseInt(sp[0]);
			int b = Integer.parseInt(sp[1]);
			int c = Integer.parseInt(sp[2]);
			
			edge[i][0] = a; //출발
			edge[i][1] = b; //도착
			edge[i][2] = c; //비용
		}
		//결과표 초기화
		long visit[] = new long[N+1];
		//initializing
		for(int i = 1; i<=N;i++) visit[i] = INF;
		
		visit[1] = 0; //1번도시부터 출발이므로 초기화
		//벨만포드 알고리즘
		//N번 반복
		for(int i = 1; i<= N; i++) {
			for(int j = 0; j<M; j++) { //간선 리스트를 한 번씩 둘러본다
				//만약 해당 간선의 출발지에 갈 수 있고
				//현재 해당 간선의 도착지에 가는 비용이
				//현재 해당 간선의 출발지에 가는 비용 + 현재 해당 간선 TO 도착지 비용
				//보다 크다면 작은 값으로 갱신해준다.
				if(visit[edge[j][0]] <INF && visit[edge[j][1]] > visit[edge[j][0]] + edge[j][2]) {
					visit[edge[j][1]] = visit[edge[j][0]] + edge[j][2];
				}
			}
		}
		
		//음의 사이클이 있는지 확인한다.
		boolean isNegativeCycle = false; //없는 것으로 초기화
		
		//한 번 더 둘러보고
		//만약 값이 갱신된다면 음의 사이클이 있는 것!
		for(int j = 0; j<M; j++) { //간선 리스트를 한 번씩 둘러본다

			if(visit[edge[j][0]] <INF && visit[edge[j][1]] > visit[edge[j][0]] + edge[j][2]) {
				isNegativeCycle = true;
				break;
			}
		}
		
		//출력
		
		if(isNegativeCycle) bw.write("-1");
		else {
			for(int i = 2; i<=N ;i ++) {
				//가는 경로가 없다면 -1 출력
				if(visit[i] == INF) bw.write("-1\n");
				//가는 경로가 있다면 최소비용 출력
				else bw.write(visit[i]+"\n");
			}
		}
		bw.flush();
	}

}
