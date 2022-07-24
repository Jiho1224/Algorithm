package al_07_13;

import java.io.*;
import java.util.*;

class Data2{
	int to; long cost;
	public Data2(int to,long cost) {
		this.to = to;
		this.cost = cost;
	}
}

public class shortestRoute_1753 {
	public static ArrayList<ArrayList<Data2>> aL;
	public static long[] result;
	public static boolean[] visit;
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine();
		String []sp = str.split(" ");
		
		int V = Integer.parseInt(sp[0]); //������ ����
		int E = Integer.parseInt(sp[1]); //������ ����
		
		str = br.readLine();
		int start = Integer.parseInt(str); //���� ���
		
		//���� ����Ʈ �ʱ�ȭ
		aL = new ArrayList<>();
		for(int i = 0; i<=V;i++)
			aL.add(new ArrayList<Data2>());
		
		for(int i = 0; i<E;i++) {
			str = br.readLine();
			sp = str.split(" ");
			
			// a->b (cost : c)
			int a = Integer.parseInt(sp[0]);
			int b = Integer.parseInt(sp[1]);
			int c = Integer.parseInt(sp[2]);
			
			aL.get(a).add(new Data2(b,c));
		}
		
		// ���� ����ġ�� ������ �ʰ�,
		// �� ���������� �� ���������� �ִ� �Ÿ��� ���ϴ� ���̹Ƿ� 
		// ���ͽ�Ʈ�� �˰����� ����ϸ� �ȴ�.
		
		//��� list �ʱ�ȭ, �湮 ���� �ʱ�ȭ
		result = new long[V+1];
		visit = new boolean[V+1];
		
		
		for(int i = 0; i<V+1;i++) result[i] = Integer.MAX_VALUE;
		result[start] = 0;
		
		//���ͽ�Ʈ�� ����
		Dijkstra(start);
		
		//��� ���
		for(int i = 1; i<=V;i++) {
			if(result[i] == Integer.MAX_VALUE) {bw.write("INF"+"\n"); continue;}
			bw.write(result[i]+"\n");
		}
		
		bw.flush();
	}
	
	public static void Dijkstra(int v) {
		
		//cost �������� �����ϴ� �켱���� ť (min heap) ����
		PriorityQueue<Data2> q = new PriorityQueue<>(new Comparator<Data2>() {
			@Override
			public int compare(Data2 a,Data2 b) {
				if(a.cost > b.cost) return 1;
				else if(a.cost < b.cost) return -1;
				else return 0;
			}
		});
		
		//���� ���� ���۳��-���۳���� cost�� 0�� �ִ´�.
		q.add(new Data2(v,0));

		
		while(!q.isEmpty()) {
			Data2 tmp = q.poll(); //q���� �ϳ��� ������.

			//���� data Ȯ��
			int to = tmp.to;
			
			if(visit[to]) continue; //�̹� �湮�ߴٸ� pass
			visit[to] = true; //������ �湮������ ǥ��
			
			//���� node�� ����Ǿ��ִ� ������ üũ�Ѵ�.
			for(int i = 0; i<aL.get(to).size();i++) {
				Data2 next = aL.get(to).get(i);
				
				//�� �������� ���°� ���� ����Ǿ��ִ� �ּ� cost������ �۴ٸ�
				//�� ��η� ���� ���� �� �ִ� �Ÿ��̴�. --> ����
				if(result[next.to] > result[to]+next.cost) {
					result[next.to] = result[to]+next.cost;
					q.add(new Data2(next.to,result[next.to])); //���� �߰�
				}
			}
		}
	}

}
