package al_07_13;

import java.io.*;
import java.util.*;

class Pair{
	int from,to;
	public Pair(int from,int to) {
		//만약 A B에서 B가 더 작다면 swap
		if(to<from) {
			int tmp = to;
			to = from;
			from = tmp;
		}
		
		this.from = from;
		this.to = to;
	}
}

public class Cutting_Edge_11400 {
	
	public static ArrayList<ArrayList<Integer>> aL;
	public static ArrayList<Pair> cuttingEdge;
	public static int visitOrder[];
	public static int order = 0;
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine();
		String[] sp = str.split(" ");
		
		int V = Integer.parseInt(sp[0]); //정점 개수
		int E = Integer.parseInt(sp[1]); //간선 개수
		
		//인접 행렬 초기화
		aL = new ArrayList<>();
		for(int i = 0; i<=V;i++) aL.add(new ArrayList<Integer>());
		
		//간선 정보 입력 받아서 인접행렬에 저장
		for(int i = 0; i<E;i++) {
			str = br.readLine();
			sp = str.split(" ");
			
			int a = Integer.parseInt(sp[0]); //정점 개수
			int b = Integer.parseInt(sp[1]); //간선 개수
			
			//입력 : a b  <=>  간선 a <--> b
			aL.get(a).add(b);
			aL.get(b).add(a);
			
		}
		//방문 정보 초기화
		visitOrder = new int[V+1]; //정점의 방문 순서 및 방문 여부 체크
		
		//절단선 넣는 arrayList 초기화
		cuttingEdge = new ArrayList<>();
		
		//dfs로 그래프 둘러보기
		//그래프가 all connected가 아닐 수도 있으므로 반복문을 이용하여 돌려준다.
		for(int i = 1; i<=V;i++) {
			//방문한 적 없는 정점이라면
			if(visitOrder[i] == 0) dfs(i,0); 
		}
		
		//결과 출력
		bw.write(cuttingEdge.size()+"\n");
		
		Collections.sort(cuttingEdge,new Comparator<Pair>() {

			@Override
			public int compare(Pair o1, Pair o2) {
				if(o1.from > o2.from) return 1;
				else if(o1.from < o2.from) return -1;
				
				if(o1.from == o2.from) {
					if(o1.to > o2.to) return 1;
					else if(o1.to < o2.to) return -1;
				}
				return 0;
			}
			
		});
		
		for(int i = 0; i<cuttingEdge.size();i++) {
			bw.write(cuttingEdge.get(i).from + " "+cuttingEdge.get(i).to+"\n");
		}
		
		bw.flush();
	}
	public static int dfs(int node, int parent) {
		
		visitOrder[node] = ++order;
		int minOrder = visitOrder[node]; //최소 방문횟수 초기화 (low를 구하기 위해)
		
		//현재 node의 간선을 탐색한다.
		for(int i = 0; i<aL.get(node).size();i++) {
			int next = aL.get(node).get(i); //연결된 노드를 꺼낸다.
			
			if(next == parent) continue;
			//visitOrder이 0이 아니라면 이미 방문한 order
			//즉, 조상 노드이다.
			if(visitOrder[next]>0) {
				//조상 노드는 visit이 끝나지 않은 상태이다.
				//minOrder 갱신
				minOrder = minOrder > visitOrder[next]?visitOrder[next]:minOrder;
			}
			//visitOrder이 0이라면 자식노드이다.
			else {
				int low = dfs(next,node); //자식 노드를 탐색한다 (부모는 현재 노드로 넣는다.)
				
				if(low > visitOrder[node]) cuttingEdge.add(new Pair(node,next)); // 간선을 결과값에 넣는다.
				
				minOrder = low < minOrder?low:minOrder;
				
			}
		}
		
		return minOrder;
	}

}
