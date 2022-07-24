package al_07_11;

import java.io.*;
import java.util.*;

public class make_Game_1516 {

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine();
		String []sp;
		int N = Integer.parseInt(str);
		
		//진입차수 배열 및 간선 arrayList 초기화
		int degree[] = new int[N+1];
		int time[] = new int[N+1];
		int result[] = new int[N+1];
		ArrayList<ArrayList<Integer>> edge = new ArrayList<>();
		
		for(int i = 0; i<= N;i++) {
			edge.add(new ArrayList<Integer>());
		}
		
		//입력받은 시간 및 간선 저장
		//time[i]는 ith 건물의 시간 저장
		//edge.get(i)에는 i번째 -> []번째 간선 추가
		
		for(int i = 1; i<= N;i++) {
			str = br.readLine();
			//[] -> i
			sp = str.split(" ");
			for(int j = 0; j<sp.length-1;j++) {
				
				//소요되는 시간
				if(j == 0) {
					//ith 건물에 소요되는 시간은 time[i]에 담는다.
					time[i] = Integer.parseInt(sp[0]); 
					continue;
				}
				//j != 0일 경우, 선행되는 edge []->i
				int tmp = Integer.parseInt(sp[j]);
				edge.get(tmp).add(i); // tmp -> i 간선 추가
				degree[i] ++; // i의 진입차수 +1
			}
		}
		
		//q 초기화
		Queue<Integer> q = new LinkedList<>();
		
		//진입차수가 0인 정점을 q에 담는다.
		for(int i = 1; i<=N;i++) {
			if(degree[i] == 0) {
				q.add(i);
				result[i] = time[i];
				degree[i]--; //이미 담은 정점은 다시 담지 않기 위해
			}
		}
		
		//q가 빌때까지 즉, 모든 정점을 돌때까지 반복
		while(!q.isEmpty()) {
			int tmp = q.poll();
			
			for(int i = 0; i<edge.get(tmp).size();i++) {
				int tmp2 = edge.get(tmp).get(i);
				degree[tmp2]--; //진입차수 -1
				result[tmp2] = Math.max(result[tmp2], time[tmp2]+result[tmp]);
				
				if(degree[tmp2] == 0) {
					q.add(tmp2);
					degree[tmp2]--;
				}
			}
		}
		
		for(int i = 1; i<=N;i++) {
			bw.write(result[i]+"\n");
		}
		bw.flush();
		
	}
	

}
