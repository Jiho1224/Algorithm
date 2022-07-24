package al_07_11;

import java.io.*;
import java.util.*;

public class make_Game_1516 {

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine();
		String []sp;
		int N = Integer.parseInt(str);
		
		//�������� �迭 �� ���� arrayList �ʱ�ȭ
		int degree[] = new int[N+1];
		int time[] = new int[N+1];
		int result[] = new int[N+1];
		ArrayList<ArrayList<Integer>> edge = new ArrayList<>();
		
		for(int i = 0; i<= N;i++) {
			edge.add(new ArrayList<Integer>());
		}
		
		//�Է¹��� �ð� �� ���� ����
		//time[i]�� ith �ǹ��� �ð� ����
		//edge.get(i)���� i��° -> []��° ���� �߰�
		
		for(int i = 1; i<= N;i++) {
			str = br.readLine();
			//[] -> i
			sp = str.split(" ");
			for(int j = 0; j<sp.length-1;j++) {
				
				//�ҿ�Ǵ� �ð�
				if(j == 0) {
					//ith �ǹ��� �ҿ�Ǵ� �ð��� time[i]�� ��´�.
					time[i] = Integer.parseInt(sp[0]); 
					continue;
				}
				//j != 0�� ���, ����Ǵ� edge []->i
				int tmp = Integer.parseInt(sp[j]);
				edge.get(tmp).add(i); // tmp -> i ���� �߰�
				degree[i] ++; // i�� �������� +1
			}
		}
		
		//q �ʱ�ȭ
		Queue<Integer> q = new LinkedList<>();
		
		//���������� 0�� ������ q�� ��´�.
		for(int i = 1; i<=N;i++) {
			if(degree[i] == 0) {
				q.add(i);
				result[i] = time[i];
				degree[i]--; //�̹� ���� ������ �ٽ� ���� �ʱ� ����
			}
		}
		
		//q�� �������� ��, ��� ������ �������� �ݺ�
		while(!q.isEmpty()) {
			int tmp = q.poll();
			
			for(int i = 0; i<edge.get(tmp).size();i++) {
				int tmp2 = edge.get(tmp).get(i);
				degree[tmp2]--; //�������� -1
				result[tmp2] = Math.max(result[tmp2], time[tmp2]+result[tmp]);
				
				if(degree[tmp2] == 0) {
					q.add(tmp2);
					degree[tmp2]--;
				}
			}
		}
		
		for(int i = 1; i<=N;i++) {
			bw.write(result[i]+"\n");
		}
		bw.flush();
		
	}
	

}
