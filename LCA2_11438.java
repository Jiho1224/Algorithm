package al_07_12;

import java.io.*;
import java.util.*;


public class LCA2_11438 {
	
	public static int parent[][];
	public static ArrayList<ArrayList<Integer>> list;
	public static int []depth;
	public static int min;
	public static int max_depth = 0;

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine();
		String[] sp;
		int N = Integer.parseInt(str); //노드의 개수
		list = new ArrayList<>();
		
		depth = new int[N+1]; // 깊이 저장
		parent = new int[18][N+1]; //parent[x][y] 는 node y의 2^x칸 조상
		
		
		for(int i = 0; i<=N;i++) {
			list.add(new ArrayList<Integer>());
		}
		
		//이후 N-1개의 줄 --> edge 정보를 받는다.
		for(int i = 0; i<N-1;i++) {
			str = br.readLine();
			sp = str.split(" ");
			int a = Integer.parseInt(sp[0]);
			int b = Integer.parseInt(sp[1]);
			
			//a와 b 간선 연결
			list.get(a).add(b);
			list.get(b).add(a);
			
		}
		
		//bfs를 이용하여 depth를 구한다. 탐색의 시작은 Root Node = 1
		for(int i = 1; i<=N;i++) {
			depth[i] = -1;
		}
		
		Queue<Integer> q = new LinkedList<>();
		q.add(1);
		depth[1] = 0;
		
		while(!q.isEmpty()) {
			int curr = q.poll();
			for(int i = 0; i<list.get(curr).size();i++) {
				int next = list.get(curr).get(i);
				//미방문시
				if(depth[next] == -1) {
					
					q.add(next); //연결된 노드를 q에 추가
					depth[next] = depth[curr] + 1; //depth = parent의 depth + 1
					
					parent[0][next] = curr; //한 칸 부모 저장
					max_depth = Math.max(max_depth, depth[next]);
				}
			}
		}
		
		//점핑 테이블 생성
		for(int i = 1; i<18;i++) {
			for(int j = 1; j<=N;j++) {
				//parent[i][j] 는 노드 j의 2^(i)칸 부모
				//parent[i][j] 는 parent[i-1][j] (2^(i-1)칸 부모) 의 2^(i-1)칸 부모
				parent[i][j] = parent[i-1][parent[i-1][j]];
			}
		}
		
		str = br.readLine();
		int M = Integer.parseInt(str); // 공통 조상을 찾는 연산 M번 시행
		
		//공통조상 찾기
		for(int i = 0; i<M;i++) {
			str = br.readLine();
			sp = str.split(" ");
			
			int a = Integer.parseInt(sp[0]);
			int b = Integer.parseInt(sp[1]);
						
			min = 0;
			//a와 b의 공통 조상을 찾는다.
			LCA(a,b);
			
			bw.write(min+"\n");
		}
		
		bw.flush();
	}
	
	//공통조상을 찾는 함수
	public static void LCA(int a, int b) {

		//depthA가 더 크도록!
		//depthA가 더 얕은 depth라면 depthA와 depthB를 바꾼다.
		if(depth[a] < depth[b]) {
			int tmp = a;
			a = b;
			b = tmp;
		}		
		
		//1. depth 맞추기
		int diff = depth[a]-depth[b];
		String binary = Integer.toBinaryString(diff);
		for(int i = 0; i <binary.length(); i++) {
			char c = binary.charAt(binary.length()-1-i);
			//1이라면
			if(c == '1') {
				a = parent[i][a]; //a를 올린다.
			}
		}
		
		//2. LCA 찾기
		while(a!=b) {
			int r;
			for(r = 0; r<18;r++) {
				if(parent[r][a] == parent[r][b]) break;
			}
			
			if(r>=1) r--;
			
			a = parent[r][a];
			b = parent[r][b];
			
		}		
		min = a;	
		return;
	
	}
}
