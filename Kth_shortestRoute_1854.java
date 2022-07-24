package al_07_13;

import java.io.*;
import java.util.*;

class road{
	int node;
	int cost;
	public road(int node,int cost) {
		this.node = node;
		this.cost = cost;
	}
}


public class Kth_shortestRoute_1854 {
	
	public static ArrayList<ArrayList<road>> list;
	public static int n;
	public static ArrayList<Integer> route;
	public static int visited[]; //�湮�ߴ��� Ȯ��
	public static ArrayList<ArrayList<Integer>> result; //������ �������� �ֱ� ���� ���� ����Ʈ
	public static int[] during;
	public static int k;
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine();
		String[] sp = str.split(" ");
		
		n = Integer.parseInt(sp[0]); // ���õ��� ����
		int m = Integer.parseInt(sp[1]); // ������ ����
		k = Integer.parseInt(sp[2]); // k��° �ִܰ��
		
		//���� ����Ʈ�� ����?
		//road���� ���� ������ �ð� ��� ������ ��� �ִ�.
		list = new ArrayList<>();
		
		for(int i = 0; i<=n;i++) {
			list.add(new ArrayList<road>());
		}
		
		//���� �Է�
		for(int i = 0; i<m;i++) {
			str = br.readLine();
			sp = str.split(" ");
			
			int a = Integer.parseInt(sp[0]);
			int b = Integer.parseInt(sp[1]);
			int c = Integer.parseInt(sp[2]);
			
			list.get(a).add(new road(b,c));
		}
		
		//�ϴ� �ִܰ�� ���� �����̰�, ����ġ�� ��� ����� �����Ƿ� ���ͽ�Ʈ��?
		route = new ArrayList<>(); // ������ ��δ� route�� ��´�.
		visited = new int[n+1]; //0�̸� �湮 X 1�̸� �湮 O
		
		result = new ArrayList<>();
		for(int i = 0; i<=n;i++) {
			result.add(new ArrayList<Integer>());
		}
		during = new int[n+1];
		for(int i = 0; i<n+1;i++) during[i] = Integer.MAX_VALUE;
		
		
		Dijkstra(1);
		
		for(int i = 1; i<=n;i++) {
			Collections.sort(result.get(i));
			if(result.get(i).size() < k) bw.write(-1+"\n");
			else bw.write(result.get(i).get(k-1)+"\n");
		}
		bw.flush();
	}
	
	//���ͽ�Ʈ�� �˰���
	public static void Dijkstra(int s) {
		
		//min heap�� ����� ���� �켱���� ť ����
		PriorityQueue<road> pq = new PriorityQueue<>(new Comparator<road>() {
			@Override
			public int compare(road a,road b) {
				if(a.cost > b.cost) return 1;
				else if(a.cost < b.cost) return -1;
				else return 0;
			}
		});
		
		pq.add(new road(s,0)); //���۳��� �� cost(0)�� min heap�� ��´�.
		
		//q�� �������� �ݺ�
		while(!pq.isEmpty()) {
			//�� ù��°�� ���Ҹ� ������.
			road curr = pq.poll();
			
			//�湮�߾����� üũ X --> �ι�° �湮�� ù��° �湮���� COST�� �� Ŭ ��.
			//���� �� K�� ���� �����Ѵ�.
			
			//�ش� ���� ����� �ٸ� �������� Ȯ���Ѵ�.
			for(int i = 0; i<list.get(curr.node).size();i++) {
				//�������� �� ����
				road next = list.get(curr.node).get(i);
				int next_node = next.node; //���� ���
				int cost = next.cost; // ���� ������ ���� ���
				
				int ncost = cost + curr.cost;
				
				if(result.get(next_node).size() < k) {
					result.get(next_node).add(ncost);
					pq.add(new road(next_node,ncost));
				}
				
				
				
			}
		}
		
		
	}
}
