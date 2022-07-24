package al_07_13;

import java.io.*;
import java.util.*;

class road{
	int node;
	int cost;
	public road(int node,int cost) {
		this.node = node;
		this.cost = cost;
	}
}


public class Kth_shortestRoute_1854 {
	
	public static ArrayList<ArrayList<road>> list;
	public static int n;
	public static ArrayList<Integer> route;
	public static int visited[]; //방문했는지 확인
	public static ArrayList<ArrayList<Integer>> result; //나오는 누적값을 넣기 위한 연결 리스트
	public static int[] during;
	public static int k;
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine();
		String[] sp = str.split(" ");
		
		n = Integer.parseInt(sp[0]); // 도시들의 개수
		int m = Integer.parseInt(sp[1]); // 도로의 개수
		k = Integer.parseInt(sp[2]); // k번째 최단경로
		
		//인접 리스트로 구현?
		//road에는 도착 지점과 시간 비용 정보가 담겨 있다.
		list = new ArrayList<>();
		
		for(int i = 0; i<=n;i++) {
			list.add(new ArrayList<road>());
		}
		
		//도로 입력
		for(int i = 0; i<m;i++) {
			str = br.readLine();
			sp = str.split(" ");
			
			int a = Integer.parseInt(sp[0]);
			int b = Integer.parseInt(sp[1]);
			int c = Integer.parseInt(sp[2]);
			
			list.get(a).add(new road(b,c));
		}
		
		//일단 최단경로 관련 문제이고, 가중치가 모두 양수만 나오므로 다익스트라?
		route = new ArrayList<>(); // 나오는 경로는 route에 담는다.
		visited = new int[n+1]; //0이면 방문 X 1이면 방문 O
		
		result = new ArrayList<>();
		for(int i = 0; i<=n;i++) {
			result.add(new ArrayList<Integer>());
		}
		during = new int[n+1];
		for(int i = 0; i<n+1;i++) during[i] = Integer.MAX_VALUE;
		
		
		Dijkstra(1);
		
		for(int i = 1; i<=n;i++) {
			Collections.sort(result.get(i));
			if(result.get(i).size() < k) bw.write(-1+"\n");
			else bw.write(result.get(i).get(k-1)+"\n");
		}
		bw.flush();
	}
	
	//다익스트라 알고리즘
	public static void Dijkstra(int s) {
		
		//min heap을 만들기 위해 우선순위 큐 선언
		PriorityQueue<road> pq = new PriorityQueue<>(new Comparator<road>() {
			@Override
			public int compare(road a,road b) {
				if(a.cost > b.cost) return 1;
				else if(a.cost < b.cost) return -1;
				else return 0;
			}
		});
		
		pq.add(new road(s,0)); //시작노드와 그 cost(0)을 min heap에 담는다.
		
		//q가 빌때까지 반복
		while(!pq.isEmpty()) {
			//맨 첫번째의 원소를 꺼낸다.
			road curr = pq.poll();
			
			//방문했었는지 체크 X --> 두번째 방문은 첫번째 방문보다 COST가 더 클 것.
			//정점 당 K개 까지 저장한다.
			
			//해당 노드와 연결된 다른 간선들을 확인한다.
			for(int i = 0; i<list.get(curr.node).size();i++) {
				//다음으로 갈 간선
				road next = list.get(curr.node).get(i);
				int next_node = next.node; //다음 노드
				int cost = next.cost; // 다음 노드까지 가는 비용
				
				int ncost = cost + curr.cost;
				
				if(result.get(next_node).size() < k) {
					result.get(next_node).add(ncost);
					pq.add(new road(next_node,ncost));
				}
				
				
				
			}
		}
		
		
	}
}
