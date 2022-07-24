package al_07_11;

import java.io.*;

public class set_1717 {
	public static int parent[];
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine();
		String []sp = str.split(" ");
		
		int n = Integer.parseInt(sp[0]);
		int m = Integer.parseInt(sp[1]);
		
		parent = new int[n+1];
		
		//Initialize
		for(int i = 0; i<n+1;i++) {
			parent[i] = i;
		}
		
		//연산 m번 시작
		for(int i = 0; i<m;i++) {
			str = br.readLine();
			sp = str.split(" ");
			
			int con = Integer.parseInt(sp[0]);
			int a = Integer.parseInt(sp[1]);
			int b = Integer.parseInt(sp[2]);
			
			// 0 a b의 형태일 경우 합집합
			if(con == 0) {
				if (a != b) union(a,b);
			}
			
			// 1 a b의 형태일 경우 a와 b가 같은 집합인지 check
			else {
				int rootA = find(a);
				int rootB = find(b);
				
				if(rootA == rootB) bw.write("YES\n");
				else bw.write("NO\n");
			}
		}
		
		bw.flush();
		
	}
	
	// a의 집합과 b의 집합을 합치는 함수
	public static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		parent[rootB] = rootA; //B를 A의 집합에 합친다.
	}
	
	// a가 속해있는 그룹의 대표 root를 찾는 함수
	public static int find(int a) {
		if(parent[a] == a) return a;
		else return parent[a] = find(parent[a]);
	}

}
