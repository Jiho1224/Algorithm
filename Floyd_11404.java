package al_07_13;

import java.io.*;


public class Floyd_11404 {
	public static int INF = Integer.MAX_VALUE;
	public static long[][] bus;
	public static int n;
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine();
		String []sp;
		
		n = Integer.parseInt(str); //도시의 개수
		int m = Integer.parseInt(br.readLine()); //버스의 개수
		
		//bus 그래프 : 인접행렬
		//초기화 bus[a][b] = c는 a->b 최단 비용 c이다.
		//처음 비용은 모두 INF로 잡는다.
		
		bus = new long[n+1][n+1];
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if (i == j) bus[i][j] = 0; //자기 자신에게 가는 cost는 0
				else bus[i][j] = INF;
			}
		}
		
		//버스의 개수만큼 간선 정보를 받는다.
		for(int i = 0; i<m;i++) {
			str = br.readLine();
			sp = str.split(" ");
			//입력 : a b c
			//의미 : a->b 버스 (cost: c)
			int a = Integer.parseInt(sp[0]);
			int b = Integer.parseInt(sp[1]);
			int c = Integer.parseInt(sp[2]);
			
			//입력받은 정보로 인접행렬 생산
			if(bus[a][b] == INF) bus[a][b] = c; //만약 새로 추가된 노선이면 그냥 넣는다.
			else bus[a][b] = c > bus[a][b]? bus[a][b]:c; //이미 있던 노선이면 더 작은 값으로 갱신
		}
		
		//플로이드 함수 -> 플로이드 워셜 알고리즘을 이용하여
		//모든 버스간의 최소 비용을 계산할 수 있도록 bus graph 갱신
		Floyd();
		
		//결과 출력
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				//갈 수 없는 경우
				if(bus[i][j] == INF) bw.write("0 ");
				else bw.write(bus[i][j]+" ");		
			}
			bw.write("\n");
		}
		
		bw.flush();
	}
	
	//도시 i에서 j로 가는데 필요한 최소비용을 계산하는 함수
	//플로이드 워셜 알고리즘 이용
	public static void Floyd() {
		for(int k = 1; k<=n; k++) { //경유지
			for(int i = 1; i<=n ; i++) { //출발지
				for(int j = 1; j<=n; j++) { //도착지
					if(bus[i][j] > bus[i][k]+bus[k][j])
						bus[i][j] = bus[i][k]+bus[k][j];
				}
			}
		}
		
	}

}
