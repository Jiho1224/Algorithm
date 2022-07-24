package al_07_12;

import java.io.*;
import java.util.*;

public class Connecting_NetWork_1922 {
	
	public static int N,M;
	public static int [][]edge;
	public static int parent[];
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine();
		N = Integer.parseInt(str); //컴퓨터의 수
		
		str = br.readLine();
		M = Integer.parseInt(str); // 연결할 수 있는 선의 수
		
		edge = new int[M][3]; //M개의 edge //[0] -- [1] (간선) && [2] : cost
		
		String []sp;
		
		//간선 리스트 생성
		for(int i = 0;i<M;i++) {
			str = br.readLine();
			sp = str.split(" ");
			
			edge[i][0] = Integer.parseInt(sp[0]);
			edge[i][1] = Integer.parseInt(sp[1]);
			edge[i][2] = Integer.parseInt(sp[2]);
		}
		
		//processing
		//1. sort
		//2. edge 선택 : 서로소 집합 이용
		
		
		// cost를 기준으로 간선 정렬
		Arrays.sort(edge, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1,int[] o2) {
				if(o1[2] > o2[2]) return 1;
				else if(o1[2]<o2[2]) return -1;
				return 0;
			}
		});
		
		
		//union-find를 위한 정점 집합 생성
		parent = new int[N+1]; //1~N까지 
		
		//parent 초기화
		for(int i = 1;i<=N;i++)
			parent[i] = i;
		
		int cnt_v = 0;
		int sum = 0;
		//간선을 순차적으로 탐색
		for(int i = 0; i<M;i++) {
			//정점개수 - 1 만큼의 간선을 선택하였으면 spanning tree 생성 완료
			//반복 종료
			if(cnt_v == N-1) break;
			
			//간선 a -- b
			int a = edge[i][0];
			int b = edge[i][1];
			
			//만약 루트가 같다면 즉, 이미 연결되어있다면 
			//pass
			if(find(a) == find(b)) continue;
			
			//연결되어있지 않다면
			
			//1.a와 b 집합을 합친다.
			union(a,b);
			//2.간선이 선택되었으므로 cost를 더한다.
			sum += edge[i][2];
			//3.선택한 간선 지표를 하나 추가한다.
			cnt_v++;
			
		}
		
		bw.write(sum+"");
		bw.flush();
		
	}
	
	//집합 a와 집합 b를 합친다.
	public static void union(int a,int b) {
		int rootA = find(a); //집합의 root를 찾는다
		int rootB = find(b);
		
		parent[rootB] = rootA; //B를 A에 합친다.
	}
	
	//a의 root를 찾는다.
	public static int find(int a) {
		if (parent[a] == a) return a;
		else return parent[a] = find(parent[a]);
	}
	
	

}
