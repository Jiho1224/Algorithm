package al_07_11;

import java.io.*;
import java.util.*;


public class line_2252 {

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine();
		String[] sp = str.split(" ");
		int N = Integer.parseInt(sp[0]);
		int M = Integer.parseInt(sp[1]); //비교 횟수
		
		int degree[] = new int[N+1];

		ArrayList<ArrayList<Integer>> edge = new ArrayList<>();
		for(int i = 0; i<=N;i++) {
			edge.add(new ArrayList<Integer>()); // 정점 개수만큼 edge 추가
		}
		for(int i = 0; i<M;i++) {
			str = br.readLine();
			sp = str.split(" ");
			
			//간선 추가
			int a = Integer.parseInt(sp[0]);
			int b = Integer.parseInt(sp[1]);
			edge.get(a).add(b); // a->b
			degree[b] += 1; //진입 차수 추가
		}
		
		Queue<Integer> q = new LinkedList<>();
		
		//진입차수가 0인 것 q에 추가
		for(int i = 1; i<=N;i++) {
			if(degree[i] == 0) {
				q.add(i);
				degree[i] -= 1;
			}
		}
		
		//q가 빌때까지 실행
		while(!q.isEmpty()) {
			int tmp = q.poll();
			bw.write(tmp+" "); // 결과 작성
			
			//tmp->() 일때 tmp에서 출발하는 간선만 검색
			for(int i = 0; i<edge.get(tmp).size();i++) {
				
				//만약 tmp->b 로 연결된 간선이 있다면 삭제해주고
				// b의 진입차수를 하나 줄여준다.
				int tmp2 = edge.get(tmp).get(i);
				degree[tmp2] -= 1;
				
				if(degree[tmp2] == 0) {
					q.add(tmp2);
					degree[tmp2] -= 1;
				}
				
			}			
		}
		bw.write("\n");
		bw.flush();
	}

}
