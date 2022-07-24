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
			
			N = Integer.parseInt(sp[0]); // ������ ������ ����
			int M = Integer.parseInt(sp[1]); // ����̰� ����ǿ��� �� ���� ��
			
			if(N == 0 && M == 0) break; //���� 0 0�� ������ ���α׷� ����
			
			//���� ���� �� �ʱ�ȭ
			parent = new Data[N+1];
			for(int i = 0; i<N+1;i++) {
				parent[i] = new Data(i,0);
			}
			
			
			//����̰� ����ǿ��� �� ���� �� ��ŭ ��� ���� ���
			for(int i = 0; i<M;i++) {
				str = br.readLine();
				sp = str.split(" ");
				
				//���� ����̰� ���Ը� �� ���̶�� �ش� ���� ���´�
				// ! a b w 
				//��, ���̴� 4�� �� ��.
				if(sp.length == 4) {
					int a = Integer.parseInt(sp[1]);
					int b = Integer.parseInt(sp[2]);
					int w = Integer.parseInt(sp[3]);
					
					union(a,b,w);
				}
				
				//�������� ������ ���
				else {
					int a = Integer.parseInt(sp[1]);
					int b = Integer.parseInt(sp[2]);
					
					//���� root�� �ƴ϶�� �� �Ұ��̹Ƿ�
					if(find(a) != find(b)) bw.write("UNKNOWN\n");
					//���� ��Ʈ���
					else {
						int tmp = parent[b].weight-parent[a].weight;
						bw.write(tmp+"\n");
					}
				}
			}
			
			
		}
		bw.flush();
		
		
		
		
	}
	
	//������ ��ġ�� weight�� �������ִ� �Լ�
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
	
	//a�� root�� ã���ִ� �Լ�
	public static int find(int a) {
		if(parent[a].parent == a) return a;
		else {
			int tmp = find(parent[a].parent);
			parent[a].weight += parent[parent[a].parent].weight;
			return parent[a].parent = tmp;
		}
		
		
	}

}
