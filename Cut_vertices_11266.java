package al_07_13;

import java.io.*;
import java.util.*;

public class Cut_vertices_11266 {
	
	public static ArrayList<ArrayList<Integer>> aL; //���� ����Ʈ
	public static boolean CutVertex[]; //i��° ��尡 ���������� ���� (false: ������ X, true: ������ O)
	public static int[] visitOrder; //i��° ����� �湮 ���� ����
									//���� 0�� ��� �̹湮, ���� a�� ��� ��� i�� a��° �湮
	
	public static int order;
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine();
		String []sp = str.split(" ");
		
		int V = Integer.parseInt(sp[0]); //������ ����
		int E = Integer.parseInt(sp[1]); //������ ����
		
		
		//1�� ������ V�� ������ ����Ʈ �����
		aL = new ArrayList<>();
		for(int i = 0; i<=V;i++) {
			aL.add(new ArrayList<Integer>());
		}
		
		//���� ������ŭ ������ ������ �޴´�.
		for(int i = 0; i<E;i++) {
			str = br.readLine();
			sp = str.split(" ");
			
			int a = Integer.parseInt(sp[0]);
			int b = Integer.parseInt(sp[1]);
			
			// a -- b (���⼺ X)
			aL.get(a).add(b);
			aL.get(b).add(a);
		}
		
		CutVertex = new boolean[V+1]; //�ʱ�ȭ
		visitOrder = new int[V+1]; //�ʱ�ȭ
		order = 0; //���� �ʱ�ȭ
		
		//���� �׷������ ������ �����Ƿ�
		//������ ��� �����ָ鼭 �湮������ ǥ�����־�� �Ѵ�.
		
		for(int i = 1; i<=V;i++) {
			//�湮 ������ 0�� ��� ��, ���ο� ���� ������ ���
			if(visitOrder[i] == 0)
				dfs(i,0); //�ش� ������ dfs�� �����ϰ� �ڽĵ���� ������� order�� �Ű��ش�.
		}
		
		
		//������ ã�Ƽ� ���� �����ϰ� ���信 �߰�
		
		int ans_N = 0;
		int[] ans = new int[10001];
		
		for(int i = 1; i<=V ;i++) {
			if(CutVertex[i]) ans[ans_N++] = i; //i��° ��尡 �������̸� ans�� �߰�
		}

		//���
		
		bw.write(ans_N+"\n");
		for(int i = 0; i<ans_N;i++)
			bw.write(ans[i]+" ");
		bw.flush();
	}
	
	//dfs �Լ�
	public static int dfs(int curr,int parent) { //���� ��� ��ȣ�� parent ������ �޴´�.
		
		visitOrder[curr] = ++order; //�湮 ������ ǥ�����ش�.
		
		//�ش� ��带 �湮�ϱ� ���� ���� �ּ� �湮������ �����ϱ� ���� ���� minOrder
		int minOrder = visitOrder[curr]; 
		int child = 0;
		//����Ǿ��ִ� ������ �ϳ��� Ž�����ش�.
		for(int i = 0; i<aL.get(curr).size();i++) {
			//���� ����Ǿ� �ִ� ������ �̹� �湮�� �����̶��
			//order�� curr node�� order���� ���� ���̰�
			//curr ����� �θ��� ���̴�.
			
			int next = aL.get(curr).get(i);
			
			if(next == parent) continue;
			
			if(visitOrder[next] > 0) {
				minOrder = Math.min(minOrder, visitOrder[next]);
			}
			
			//���� ����Ǿ� �ִ� ������ �湮���� ���� �����̶�� 
			//�ڽ� ����� ���̰� �湮���ش�.
			else {
				++child; //�ڼ� �� +1
				//curr node�� ���������� Ȯ���ϱ� ���ؼ���
				//�ڽ� ��� �� curr node�� ��ġ�� �ʰ� �湮������ �������� order �� ���� ���� 
				//order�� �ľ��ؼ� low�� �־��ش�.
				
				//�� �� ���� low�� order���� ũ�ٸ� curr node�� �������� �Ǵ� ���̴�.
				
				int low = dfs(next,curr); //�θ���� curr node��!
				
				if(parent != 0 && low >= visitOrder[curr]) CutVertex[curr] = true;
				
				minOrder = Math.min(minOrder, low);
				
			}
		}
		if(parent == 0 && child >= 2) CutVertex[curr] = true;
		return minOrder;
	}
	
	

}
