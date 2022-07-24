package al_07_04;

import java.io.*;
import java.util.*;

class Pair{
	int num,cnt;
	
	//Pair Ŭ���� ����
	// num = ��ȯ�� ���� ���� & cnt = �� ��ȯ�� Ƚ��
	public Pair(int num, int cnt) {
		this.num = num;
		this.cnt = cnt;
	}
}

public class Exchange {
	public static int M;
	public static int K;
	public static int len;
	public static boolean[][] visit;
	public static int result = -1;  // max�� ���� 
	
	public static void main(String[] args) throws IOException {
		
		//INPUT
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		String[] str_split = str.split(" ");
		
		len = str_split[0].length(); // �ڸ���
		M = Integer.parseInt(str_split[0]); // �Է¹��� ���� ����
		K = Integer.parseInt(str_split[1]); // �Է¹��� ��ȯ Ƚ�� ���� 
		
		visit = new boolean[1000001][K+1];
		
		//bfs ����
		bfs(); 
		
		System.out.print(result);
	}
	
	// idea: ���� ū ���ڸ� ����� logic�� �����ϱ� ����
	// K�� ��ȯ�� ��� �� �� ���� �ִ��� ���Ѵ�. 
	
	public static void bfs() {
		// Queue ����
		Queue<Pair> queue = new LinkedList<>();
		
		//��ȯ�� �ϳ��� ���� ���� �ʱ��� ������ M�� queue�� �־��ش�.
		//���� num = M�̰� cnt = 0�� Pair�� queue�� �ִ´�.
		queue.add(new Pair(M,0));
		visit[M][0] = true; //��ȯ ���� : M & ��ȯ Ƚ�� :0 �� �湮 �Ϸ�!!
		
		//queue�� ��� �� �� ����.. ��, �湮�� ���������� �ݺ�
		while(!queue.isEmpty()) {
			Pair tmp = queue.poll(); // queue���� �ϳ��� ����.
			
			
			//���� ��ȯȽ���� �����Ͽ��ٸ�
			if(tmp.cnt == K) {
				result = Math.max(result, tmp.num); // �ش� ���ڰ� ���� result���� ũ�ٸ� max���̹Ƿ� result ����
				continue; //�� ū ���� ���� ���� �����Ƿ�.
			}
			
			for(int i = 0; i<len-1;i++) {
				for(int j = i+1; j<len;j++) {
					int tmp_num = change(tmp.num,i,j); //ith�� jth ���Ҹ� ��ȯ�� ��
					
					// ù��° �ڸ��� 0�� ���ͼ� ��ȯ�� �Ұ��� ��Ȳ�̰ų�
					// �̹� �ش� ��ȯ�� �� ���� �ִ� ��쿡�� �ƹ��͵� ���� �ʴ´�.
					if(tmp_num == -1 || visit[tmp_num][tmp.cnt+1] == true) {
						
					}
					else {
						queue.add(new Pair(tmp_num,tmp.cnt+1)); //queue�� �߰��Ѵ�.
						visit[tmp_num][tmp.cnt+1] = true; //�ش� ���� �湮�Ͽ����� ǥ��
						
					}
					
				}
			}
		}
		
	}
	
	public static int change(int num,int i, int j) {
		char[] array = String.valueOf(num).toCharArray(); //���ڸ� ���� �迭�� 63421 -> [6,3,4,2,1]
		
		//�迭 swap
		char temp = array[j];
		array[j] = array[i];
		array[i] = temp;
		
		if(array[0] == '0') return -1; //���� ù��° ���� 0�̶�� -1 return --> false
		
		
		return Integer.parseInt(new String(array));
	}
	
}
