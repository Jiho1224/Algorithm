package al_07_13;

import java.io.*;
import java.util.*;

class Data2{
	int to; long cost;
	public Data2(int to,long cost) {
		this.to = to;
		this.cost = cost;
	}
}

public class shortestRoute_1753 {
	public static ArrayList<ArrayList<Data2>> aL;
	public static long[] result;
	public static boolean[] visit;
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine();
		String []sp = str.split(" ");
		
		int V = Integer.parseInt(sp[0]); //정점의 개수
		int E = Integer.parseInt(sp[1]); //간선의 개수
		
		str = br.readLine();
		int start = Integer.parseInt(str); //시작 노드
		
		//인접 리스트 초기화
		aL = new ArrayList<>();
		for(int i = 0; i<=V;i++)
			aL.add(new ArrayList<Data2>());
		
		for(int i = 0; i<E;i++) {
			str = br.readLine();
			sp = str.split(" ");
			
			// a->b (cost : c)
			int a = Integer.parseInt(sp[0]);
			int b = Integer.parseInt(sp[1]);
			int c = Integer.parseInt(sp[2]);
			
			aL.get(a).add(new Data2(b,c));
		}
		
		// 음의 가중치는 가지지 않고,
		// 한 정점에서의 각 정점까지의 최단 거리를 구하는 것이므로 
		// 다익스트라 알고리즘을 사용하면 된다.
		
		//결과 list 초기화, 방문 정보 초기화
		result = new long[V+1];
		visit = new boolean[V+1];
		
		
		for(int i = 0; i<V+1;i++) result[i] = Integer.MAX_VALUE;
		result[start] = 0;
		
		//다익스트라 실행
		Dijkstra(start);
		
		//결과 출력
		for(int i = 1; i<=V;i++) {
			if(result[i] == Integer.MAX_VALUE) {bw.write("INF"+"\n"); continue;}
			bw.write(result[i]+"\n");
		}
		
		bw.flush();
	}
	
	public static void Dijkstra(int v) {
		
		//cost 기준으로 정렬하는 우선순위 큐 (min heap) 생성
		PriorityQueue<Data2> q = new PriorityQueue<>(new Comparator<Data2>() {
			@Override
			public int compare(Data2 a,Data2 b) {
				if(a.cost > b.cost) return 1;
				else if(a.cost < b.cost) return -1;
				else return 0;
			}
		});
		
		//시작 노드와 시작노드-시작노드의 cost인 0을 넣는다.
		q.add(new Data2(v,0));

		
		while(!q.isEmpty()) {
			Data2 tmp = q.poll(); //q에서 하나를 꺼낸다.

			//꺼낸 data 확인
			int to = tmp.to;
			
			if(visit[to]) continue; //이미 방문했다면 pass
			visit[to] = true; //정점을 방문했음을 표시
			
			//꺼낸 node와 연결되어있는 간선을 체크한다.
			for(int i = 0; i<aL.get(to).size();i++) {
				Data2 next = aL.get(to).get(i);
				
				//이 방향으로 오는게 원래 저장되어있던 최소 cost값보다 작다면
				//이 경로로 오는 것이 더 최단 거리이다. --> 갱신
				if(result[next.to] > result[to]+next.cost) {
					result[next.to] = result[to]+next.cost;
					q.add(new Data2(next.to,result[next.to])); //힙에 추가
				}
			}
		}
	}

}
