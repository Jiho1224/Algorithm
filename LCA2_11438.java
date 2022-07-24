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
		int N = Integer.parseInt(str); //����� ����
		list = new ArrayList<>();
		
		depth = new int[N+1]; // ���� ����
		parent = new int[18][N+1]; //parent[x][y] �� node y�� 2^xĭ ����
		
		
		for(int i = 0; i<=N;i++) {
			list.add(new ArrayList<Integer>());
		}
		
		//���� N-1���� �� --> edge ������ �޴´�.
		for(int i = 0; i<N-1;i++) {
			str = br.readLine();
			sp = str.split(" ");
			int a = Integer.parseInt(sp[0]);
			int b = Integer.parseInt(sp[1]);
			
			//a�� b ���� ����
			list.get(a).add(b);
			list.get(b).add(a);
			
		}
		
		//bfs�� �̿��Ͽ� depth�� ���Ѵ�. Ž���� ������ Root Node = 1
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
				//�̹湮��
				if(depth[next] == -1) {
					
					q.add(next); //����� ��带 q�� �߰�
					depth[next] = depth[curr] + 1; //depth = parent�� depth + 1
					
					parent[0][next] = curr; //�� ĭ �θ� ����
					max_depth = Math.max(max_depth, depth[next]);
				}
			}
		}
		
		//���� ���̺� ����
		for(int i = 1; i<18;i++) {
			for(int j = 1; j<=N;j++) {
				//parent[i][j] �� ��� j�� 2^(i)ĭ �θ�
				//parent[i][j] �� parent[i-1][j] (2^(i-1)ĭ �θ�) �� 2^(i-1)ĭ �θ�
				parent[i][j] = parent[i-1][parent[i-1][j]];
			}
		}
		
		str = br.readLine();
		int M = Integer.parseInt(str); // ���� ������ ã�� ���� M�� ����
		
		//�������� ã��
		for(int i = 0; i<M;i++) {
			str = br.readLine();
			sp = str.split(" ");
			
			int a = Integer.parseInt(sp[0]);
			int b = Integer.parseInt(sp[1]);
						
			min = 0;
			//a�� b�� ���� ������ ã�´�.
			LCA(a,b);
			
			bw.write(min+"\n");
		}
		
		bw.flush();
	}
	
	//���������� ã�� �Լ�
	public static void LCA(int a, int b) {

		//depthA�� �� ũ����!
		//depthA�� �� ���� depth��� depthA�� depthB�� �ٲ۴�.
		if(depth[a] < depth[b]) {
			int tmp = a;
			a = b;
			b = tmp;
		}		
		
		//1. depth ���߱�
		int diff = depth[a]-depth[b];
		String binary = Integer.toBinaryString(diff);
		for(int i = 0; i <binary.length(); i++) {
			char c = binary.charAt(binary.length()-1-i);
			//1�̶��
			if(c == '1') {
				a = parent[i][a]; //a�� �ø���.
			}
		}
		
		//2. LCA ã��
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
