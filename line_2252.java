package al_07_11;

import java.io.*;
import java.util.*;


public class line_2252 {

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine();
		String[] sp = str.split(" ");
		int N = Integer.parseInt(sp[0]);
		int M = Integer.parseInt(sp[1]); //�� Ƚ��
		
		int degree[] = new int[N+1];

		ArrayList<ArrayList<Integer>> edge = new ArrayList<>();
		for(int i = 0; i<=N;i++) {
			edge.add(new ArrayList<Integer>()); // ���� ������ŭ edge �߰�
		}
		for(int i = 0; i<M;i++) {
			str = br.readLine();
			sp = str.split(" ");
			
			//���� �߰�
			int a = Integer.parseInt(sp[0]);
			int b = Integer.parseInt(sp[1]);
			edge.get(a).add(b); // a->b
			degree[b] += 1; //���� ���� �߰�
		}
		
		Queue<Integer> q = new LinkedList<>();
		
		//���������� 0�� �� q�� �߰�
		for(int i = 1; i<=N;i++) {
			if(degree[i] == 0) {
				q.add(i);
				degree[i] -= 1;
			}
		}
		
		//q�� �������� ����
		while(!q.isEmpty()) {
			int tmp = q.poll();
			bw.write(tmp+" "); // ��� �ۼ�
			
			//tmp->() �϶� tmp���� ����ϴ� ������ �˻�
			for(int i = 0; i<edge.get(tmp).size();i++) {
				
				//���� tmp->b �� ����� ������ �ִٸ� �������ְ�
				// b�� ���������� �ϳ� �ٿ��ش�.
				int tmp2 = edge.get(tmp).get(i);
				degree[tmp2] -= 1;
				
				if(degree[tmp2] == 0) {
					q.add(tmp2);
					degree[tmp2] -= 1;
				}
				
			}			
		}
		bw.write("\n");
		bw.flush();
	}

}
