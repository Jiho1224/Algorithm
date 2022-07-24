package al_07_11;

import java.io.*;

class Data{
	int parent, weight;
	
	public Data(int parent,int weight) {
		this.parent = parent;
		this.weight = weight;
	}
}

public class professor_3830 {
	public static Data[] parent;
	public static int N;
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			
		
		while(true){
			String str = br.readLine();
			String[] sp = str.split(" ");
			
			N = Integer.parseInt(sp[0]); // 샘플의 종류의 개수
			int M = Integer.parseInt(sp[1]); // 상근이가 실험실에서 한 일의 수
			
			if(N == 0 && M == 0) break; //만약 0 0이 들어오면 프로그램 종료
			
			//샘플 생성 및 초기화
			parent = new Data[N+1];
			for(int i = 0; i<N+1;i++) {
				parent[i] = new Data(i,0);
			}
			
			
			//상근이가 실험실에서 한 일의 수 만큼 결과 각각 출력
			for(int i = 0; i<M;i++) {
				str = br.readLine();
				sp = str.split(" ");
				
				//만약 상근이가 무게를 잰 것이라면 해당 줄의 형태는
				// ! a b w 
				//즉, 길이는 4가 될 것.
				if(sp.length == 4) {
					int a = Integer.parseInt(sp[1]);
					int b = Integer.parseInt(sp[2]);
					int w = Integer.parseInt(sp[3]);
					
					union(a,b,w);
				}
				
				//교수님의 질문인 경우
				else {
					int a = Integer.parseInt(sp[1]);
					int b = Integer.parseInt(sp[2]);
					
					//같은 root가 아니라면 비교 불가이므로
					if(find(a) != find(b)) bw.write("UNKNOWN\n");
					//같은 루트라면
					else {
						int tmp = parent[b].weight-parent[a].weight;
						bw.write(tmp+"\n");
					}
				}
			}
			
			
		}
		bw.flush();
		
		
		
		
	}
	
	//집합을 합치고 weight를 설정해주는 함수
	public static void union(int a,int b,int w) {
		
		int rootA = find(a);
		int rootB = find(b);
		
		if(rootA == rootB) return;
		// b = a+w
		// a = rootA + a.weight
		// b = rootA + a.weight+w
		parent[rootB].weight = parent[a].weight - parent[b].weight + w;
		parent[rootB].parent = rootA;		
		
		
	}
	
	//a의 root를 찾아주는 함수
	public static int find(int a) {
		if(parent[a].parent == a) return a;
		else {
			int tmp = find(parent[a].parent);
			parent[a].weight += parent[parent[a].parent].weight;
			return parent[a].parent = tmp;
		}
		
		
	}

}
