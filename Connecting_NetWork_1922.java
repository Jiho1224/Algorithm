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
		N = Integer.parseInt(str); //��ǻ���� ��
		
		str = br.readLine();
		M = Integer.parseInt(str); // ������ �� �ִ� ���� ��
		
		edge = new int[M][3]; //M���� edge //[0] -- [1] (����) && [2] : cost
		
		String []sp;
		
		//���� ����Ʈ ����
		for(int i = 0;i<M;i++) {
			str = br.readLine();
			sp = str.split(" ");
			
			edge[i][0] = Integer.parseInt(sp[0]);
			edge[i][1] = Integer.parseInt(sp[1]);
			edge[i][2] = Integer.parseInt(sp[2]);
		}
		
		//processing
		//1. sort
		//2. edge ���� : ���μ� ���� �̿�
		
		
		// cost�� �������� ���� ����
		Arrays.sort(edge, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1,int[] o2) {
				if(o1[2] > o2[2]) return 1;
				else if(o1[2]<o2[2]) return -1;
				return 0;
			}
		});
		
		
		//union-find�� ���� ���� ���� ����
		parent = new int[N+1]; //1~N���� 
		
		//parent �ʱ�ȭ
		for(int i = 1;i<=N;i++)
			parent[i] = i;
		
		int cnt_v = 0;
		int sum = 0;
		//������ ���������� Ž��
		for(int i = 0; i<M;i++) {
			//�������� - 1 ��ŭ�� ������ �����Ͽ����� spanning tree ���� �Ϸ�
			//�ݺ� ����
			if(cnt_v == N-1) break;
			
			//���� a -- b
			int a = edge[i][0];
			int b = edge[i][1];
			
			//���� ��Ʈ�� ���ٸ� ��, �̹� ����Ǿ��ִٸ� 
			//pass
			if(find(a) == find(b)) continue;
			
			//����Ǿ����� �ʴٸ�
			
			//1.a�� b ������ ��ģ��.
			union(a,b);
			//2.������ ���õǾ����Ƿ� cost�� ���Ѵ�.
			sum += edge[i][2];
			//3.������ ���� ��ǥ�� �ϳ� �߰��Ѵ�.
			cnt_v++;
			
		}
		
		bw.write(sum+"");
		bw.flush();
		
	}
	
	//���� a�� ���� b�� ��ģ��.
	public static void union(int a,int b) {
		int rootA = find(a); //������ root�� ã�´�
		int rootB = find(b);
		
		parent[rootB] = rootA; //B�� A�� ��ģ��.
	}
	
	//a�� root�� ã�´�.
	public static int find(int a) {
		if (parent[a] == a) return a;
		else return parent[a] = find(parent[a]);
	}
	
	

}
