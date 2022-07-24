package al_07_13;

import java.io.*;
import java.util.*;

class data{
	int node;
	int cost;
	
	public Integer getNode() {
		return node;
	}
	public Integer getCost() {
		return cost;
	}
	public data(int node,int cost) {
		this.node = node;
		this.cost = cost;
	}
}

public class Halloween_3860 {
	
	public static int INF = Integer.MAX_VALUE;
	public static int []dx = {-1,0,1,0};
	public static int []dy = {0,-1,0,1};
	public static int W,H;
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str;
		String[] sp;
		
		while(true) {
			str = br.readLine();
			sp = str.split(" ");
			
			W = Integer.parseInt(sp[0]); //묘지의 너비
			H = Integer.parseInt(sp[1]); //묘지의 높이
			
			if(W == 0 && H == 0) break; // 0 0이 입력되면 종료
			
			//최단거리 정보 초기화
			long visit[] = new long[W*H+1];
			for(int i = 0; i<=W*H;i++) {
				visit[i] = INF;
			}
			
			visit[0] = 0;
			visit[1] = 0;
			
			//묘지의 상태 저장
			
			//Idea
			
			//묘지의 노드는 (x,y)를 알 수 있는 상태로 저장한다.
			//초기화 시 (x,y)의 동서남북인 (x-1,y)(x+1,y)(x,y-1)(x,y+1)은 
			//간선 리스트에 간선을 추가해준다. 각 가중치는 1로 잡는다.
			//단 이때, 묘비의 위치 pair는 어떤 그래프와도 잇지 않는다.
			
			//묘비의 위치를 받으면 
			//해당 묘비와 관련된 간선을 끊는다.
			
			//귀신구멍의 위치를 받으면
			//그 귀신구멍 위치와 도착 위치를 간선 연결 해준다.
			
			
			
			
			//묘지 상태를 만들어 준다.
			ArrayList<ArrayList<data>> cemetry = new ArrayList<>();
			
			for(int i = 0; i<= W*H;i++) {
				cemetry.add(new ArrayList<data>());
			}
			
			//각 노드의 동서남북 노드와 간선을 이어준다.
			for(int i = 0; i<W;i++) {
				for(int j = 0; j<H;j++) {
					int tmp  = cToN(i,j); //현재 노드의 압축 번호
					//동서남북 탐색
					for(int k = 0; k<4;k++) {
						
						//동서남북의 x좌표 nx, y좌표 ny
						int nx = dx[k] + i;
						int ny = dy[k] + j;
						
						if(nx<0||ny<0||nx>=W||ny>=H) continue;
						
						//기본적으로 cost는 1이다.
						cemetry.get(tmp).add(new data(cToN(nx,ny),1)); //간선 추가
					}
				}
			}
			cemetry.get(W*H).clear(); //도착지에 나가는 선은없다.
			int G = Integer.parseInt(br.readLine()); //묘비의 개수
			
			//묘비의 위치

			for(int i = 0; i<G;i++) {
				str = br.readLine();
				sp = str.split(" ");
				
				int a = Integer.parseInt(sp[0]);
				int b = Integer.parseInt(sp[1]);
				
				//묘비의 위치 동서남북 노드와 간선을 끊는다.
				int tmp = cToN(a,b);
				cemetry.get(tmp).clear();
				
				//동서남북 탐색
				for(int k = 0; k<4;k++) {
					
					//동서남북의 x좌표 nx, y좌표 ny
					int nx = dx[k] + a;
					int ny = dy[k] + b;
					
					if(nx<0||ny<0||nx>=W||ny>=H) continue;
					
					//tmp의 동서남북의 노드를 가지고 있는 간선은 모두 제거
					cemetry.get(cToN(nx,ny)).removeIf((item)->item.getNode() == tmp);
				}
			}
			
			
			
			int E = Integer.parseInt(br.readLine()); // 귀신 구멍의 수
			
			//귀신 구멍의 정보
			
			// X1 Y1 X2 Y2 T
			//귀신 구멍의 위치 X1,Y1
			//귀신 구멍 빠져나오는 위치 X2,Y2
			//나오는데 걸리는 시간 T
			for(int i = 0; i<E;i++) {
				str = br.readLine();
				sp = str.split(" ");
				
				//귀신 구멍의 위치
				int X1 = Integer.parseInt(sp[0]);
				int Y1 = Integer.parseInt(sp[1]);
				int start = cToN(X1,Y1); //귀신 구멍 위치의 노드 번호
				
				// 귀신 구멍 빠져나오는 위치
				int X2 = Integer.parseInt(sp[2]);
				int Y2 = Integer.parseInt(sp[3]);
				int end = cToN(X2,Y2); //귀신 구멍 도착 위치의 노드 번호
				
				//걸리는 시간 == cost
				int T = Integer.parseInt(sp[4]);
				
				//귀신구멍 정보 저장
				//귀신 구멍에 들어가면 동서남북으로 이동하지 못하고 귀신구멍 빠져나오는 구멍으로
				//빠져나와야 한다.
				
				//따라서 귀신구멍 start 위치에서 동서남북에 연결되어있는 간선은 제거
				cemetry.get(start).clear();
				
				
				//귀신 구멍 도착 위치와 그 cost를 다시 저장
				cemetry.get(start).add(new data(end,T));
				
				
			}
			
			//edge에 최종 간선을 배열 형식으로 저장
			int total = 0; //간선의 개수
			for(int i = 1; i<=W*H;i++) {
				total += cemetry.get(i).size();
			}
			
			int edge[][] = new int[total][3];
			int cnt = 0;
			for(int i = 1; i<=W*H;i++) {
				for(int j = 0; j<cemetry.get(i).size();j++) {
					edge[cnt][0] = i; //start 노드
					edge[cnt][1] = cemetry.get(i).get(j).node; //end 노드
					edge[cnt++][2] = cemetry.get(i).get(j).cost; //cost
				}
			}
			
			//음수의 가중치 존재 && 음의 사이클 존재 && 최단 거리 => 벨만 포드 알고리즘 사용!
			for(int i = 1; i<=W*H;i++) { //정점의 개수만큼 돌아본다.
				for(int j = 0; j<total;j++) { //간선을 하나씩 돌아본다.
					if(visit[edge[j][0]] < INF && visit[edge[j][1]] > visit[edge[j][0]]+edge[j][2]) {
						visit[edge[j][1]] = visit[edge[j][0]]+edge[j][2];
					}
				}
			}
			
			//음의 사이클이 존재하는지 체크
			boolean isNegativeCycle = false;
			for(int j = 0; j<total;j++) { //간선을 하나씩 돌아본다.
				if(visit[edge[j][0]] < INF && visit[edge[j][1]] > visit[edge[j][0]]+edge[j][2]) {
					isNegativeCycle = true;
					break;
				}
			}
			
			//출력
			//1.음의 사이클이 존재한다면
			if(isNegativeCycle) {
				bw.write("Never\n");
			}
			//2. (W-1,H-1) 최단거리가 INF라면
			else if(visit[W*H] == INF) {
				bw.write("Impossible\n");
			}
			//3. 도달 가능 == 최단거리가 존재한다면
			else {
				bw.write(visit[W*H]+"\n");
			}
			
		}
		
		bw.flush();
	}
	
	//x,y 좌표를 하나의 숫자로 나타내는 함수
	public static int cToN(int x, int y) {
		return H*x+(y+1);
	}

}
