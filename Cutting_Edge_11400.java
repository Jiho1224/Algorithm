package al_07_13;

import java.io.*;
import java.util.*;

class Pair{
	int from,to;
	public Pair(int from,int to) {
		//���� A B���� B�� �� �۴ٸ� swap
		if(to<from) {
			int tmp = to;
			to = from;
			from = tmp;
		}
		
		this.from = from;
		this.to = to;
	}
}

public class Cutting_Edge_11400 {
	
	public static ArrayList<ArrayList<Integer>> aL;
	public static ArrayList<Pair> cuttingEdge;
	public static int visitOrder[];
	public static int order = 0;
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine();
		String[] sp = str.split(" ");
		
		int V = Integer.parseInt(sp[0]); //���� ����
		int E = Integer.parseInt(sp[1]); //���� ����
		
		//���� ��� �ʱ�ȭ
		aL = new ArrayList<>();
		for(int i = 0; i<=V;i++) aL.add(new ArrayList<Integer>());
		
		//���� ���� �Է� �޾Ƽ� ������Ŀ� ����
		for(int i = 0; i<E;i++) {
			str = br.readLine();
			sp = str.split(" ");
			
			int a = Integer.parseInt(sp[0]); //���� ����
			int b = Integer.parseInt(sp[1]); //���� ����
			
			//�Է� : a b  <=>  ���� a <--> b
			aL.get(a).add(b);
			aL.get(b).add(a);
			
		}
		//�湮 ���� �ʱ�ȭ
		visitOrder = new int[V+1]; //������ �湮 ���� �� �湮 ���� üũ
		
		//���ܼ� �ִ� arrayList �ʱ�ȭ
		cuttingEdge = new ArrayList<>();
		
		//dfs�� �׷��� �ѷ�����
		//�׷����� all connected�� �ƴ� ���� �����Ƿ� �ݺ����� �̿��Ͽ� �����ش�.
		for(int i = 1; i<=V;i++) {
			//�湮�� �� ���� �����̶��
			if(visitOrder[i] == 0) dfs(i,0); 
		}
		
		//��� ���
		bw.write(cuttingEdge.size()+"\n");
		
		Collections.sort(cuttingEdge,new Comparator<Pair>() {

			@Override
			public int compare(Pair o1, Pair o2) {
				if(o1.from > o2.from) return 1;
				else if(o1.from < o2.from) return -1;
				
				if(o1.from == o2.from) {
					if(o1.to > o2.to) return 1;
					else if(o1.to < o2.to) return -1;
				}
				return 0;
			}
			
		});
		
		for(int i = 0; i<cuttingEdge.size();i++) {
			bw.write(cuttingEdge.get(i).from + " "+cuttingEdge.get(i).to+"\n");
		}
		
		bw.flush();
	}
	public static int dfs(int node, int parent) {
		
		visitOrder[node] = ++order;
		int minOrder = visitOrder[node]; //�ּ� �湮Ƚ�� �ʱ�ȭ (low�� ���ϱ� ����)
		
		//���� node�� ������ Ž���Ѵ�.
		for(int i = 0; i<aL.get(node).size();i++) {
			int next = aL.get(node).get(i); //����� ��带 ������.
			
			if(next == parent) continue;
			//visitOrder�� 0�� �ƴ϶�� �̹� �湮�� order
			//��, ���� ����̴�.
			if(visitOrder[next]>0) {
				//���� ���� visit�� ������ ���� �����̴�.
				//minOrder ����
				minOrder = minOrder > visitOrder[next]?visitOrder[next]:minOrder;
			}
			//visitOrder�� 0�̶�� �ڽĳ���̴�.
			else {
				int low = dfs(next,node); //�ڽ� ��带 Ž���Ѵ� (�θ�� ���� ���� �ִ´�.)
				
				if(low > visitOrder[node]) cuttingEdge.add(new Pair(node,next)); // ������ ������� �ִ´�.
				
				minOrder = low < minOrder?low:minOrder;
				
			}
		}
		
		return minOrder;
	}

}
