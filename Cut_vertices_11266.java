package al_07_13;

import java.io.*;
import java.util.*;

public class Cut_vertices_11266 {
	
	public static ArrayList<ArrayList<Integer>> aL; //인접 리스트
	public static boolean CutVertex[]; //i번째 노드가 단절점인지 여부 (false: 단절점 X, true: 단절점 O)
	public static int[] visitOrder; //i번째 노드의 방문 순서 저장
									//값이 0인 경우 미방문, 값이 a인 경우 노드 i는 a번째 방문
	
	public static int order;
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine();
		String []sp = str.split(" ");
		
		int V = Integer.parseInt(sp[0]); //정점의 개수
		int E = Integer.parseInt(sp[1]); //간선의 개수
		
		
		//1번 노드부터 V번 노드까지 리스트 만들기
		aL = new ArrayList<>();
		for(int i = 0; i<=V;i++) {
			aL.add(new ArrayList<Integer>());
		}
		
		//간선 개수만큼 간선의 정보를 받는다.
		for(int i = 0; i<E;i++) {
			str = br.readLine();
			sp = str.split(" ");
			
			int a = Integer.parseInt(sp[0]);
			int b = Integer.parseInt(sp[1]);
			
			// a -- b (방향성 X)
			aL.get(a).add(b);
			aL.get(b).add(a);
		}
		
		CutVertex = new boolean[V+1]; //초기화
		visitOrder = new int[V+1]; //초기화
		order = 0; //순서 초기화
		
		//연결 그래프라는 조건이 없으므로
		//섬마다 모두 돌아주면서 방문순서를 표기해주어야 한다.
		
		for(int i = 1; i<=V;i++) {
			//방문 순서가 0인 경우 즉, 새로운 섬에 도달한 경우
			if(visitOrder[i] == 0)
				dfs(i,0); //해당 노드부터 dfs를 실행하고 자식들까지 순서대로 order를 매겨준다.
		}
		
		
		//단절점 찾아서 개수 갱신하고 정답에 추가
		
		int ans_N = 0;
		int[] ans = new int[10001];
		
		for(int i = 1; i<=V ;i++) {
			if(CutVertex[i]) ans[ans_N++] = i; //i번째 노드가 단절점이면 ans에 추가
		}

		//출력
		
		bw.write(ans_N+"\n");
		for(int i = 0; i<ans_N;i++)
			bw.write(ans[i]+" ");
		bw.flush();
	}
	
	//dfs 함수
	public static int dfs(int curr,int parent) { //현재 노드 번호와 parent 정보를 받는다.
		
		visitOrder[curr] = ++order; //방문 순서를 표기해준다.
		
		//해당 노드를 방문하기 위한 가장 최소 방문순서를 저장하기 위한 변수 minOrder
		int minOrder = visitOrder[curr]; 
		int child = 0;
		//연결되어있는 간선을 하나씩 탐색해준다.
		for(int i = 0; i<aL.get(curr).size();i++) {
			//만약 연결되어 있는 간선이 이미 방문한 간선이라면
			//order는 curr node의 order보다 작을 것이고
			//curr 노드의 부모일 것이다.
			
			int next = aL.get(curr).get(i);
			
			if(next == parent) continue;
			
			if(visitOrder[next] > 0) {
				minOrder = Math.min(minOrder, visitOrder[next]);
			}
			
			//만약 연결되어 있는 간선이 방문하지 않은 간선이라면 
			//자식 노드인 것이고 방문해준다.
			else {
				++child; //자손 수 +1
				//curr node가 단절점인지 확인하기 위해서는
				//자식 노드 중 curr node를 거치지 않고 방문가능한 정점들의 order 중 가장 작은 
				//order를 파악해서 low에 넣어준다.
				
				//그 후 만약 low가 order보다 크다면 curr node는 단절점이 되는 것이다.
				
				int low = dfs(next,curr); //부모노드는 curr node로!
				
				if(parent != 0 && low >= visitOrder[curr]) CutVertex[curr] = true;
				
				minOrder = Math.min(minOrder, low);
				
			}
		}
		if(parent == 0 && child >= 2) CutVertex[curr] = true;
		return minOrder;
	}
	
	

}
