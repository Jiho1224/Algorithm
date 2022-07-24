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
		
		//���� m�� ����
		for(int i = 0; i<m;i++) {
			str = br.readLine();
			sp = str.split(" ");
			
			int con = Integer.parseInt(sp[0]);
			int a = Integer.parseInt(sp[1]);
			int b = Integer.parseInt(sp[2]);
			
			// 0 a b�� ������ ��� ������
			if(con == 0) {
				if (a != b) union(a,b);
			}
			
			// 1 a b�� ������ ��� a�� b�� ���� �������� check
			else {
				int rootA = find(a);
				int rootB = find(b);
				
				if(rootA == rootB) bw.write("YES\n");
				else bw.write("NO\n");
			}
		}
		
		bw.flush();
		
	}
	
	// a�� ���հ� b�� ������ ��ġ�� �Լ�
	public static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		parent[rootB] = rootA; //B�� A�� ���տ� ��ģ��.
	}
	
	// a�� �����ִ� �׷��� ��ǥ root�� ã�� �Լ�
	public static int find(int a) {
		if(parent[a] == a) return a;
		else return parent[a] = find(parent[a]);
	}

}
