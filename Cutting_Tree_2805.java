package al_07_05;

import java.io.*;
public class Cutting_Tree_2805 {
	public static long[] tree;
	public static long M;
	public static long sum = 0;
	public static long result = 0;
	
	public static void main(String[] args) throws IOException{
		
		//INPUT
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		if(str.equals("")) str= br.readLine();
		int N = Integer.parseInt(str.split(" ")[0]);
		M = Integer.parseInt(str.split(" ")[1]);
		
		tree = new long[N];
		long max = -1;
		
		str = br.readLine();
		if(str.equals("")) str= br.readLine();
		
		String[] temp_split = str.split(" ");
		
		for(int i = 0 ; i <N;i++) {
			tree[i] = Integer.parseInt(temp_split[i]);
			max = Math.max(tree[i], max);
		}
		
		cutting(0,max);
		System.out.println(result);
	}
	
	//��� M���͸� ������������ ���ܱ� ������ �ִ븦 ���ϴ� ���̴�.
	//���ܱ� ���̰� ����� ���� ������ �� �ִ� ������ ���̴� �۾��� ���̴�.
	//���� M�� �������� ������ �� �ִ� ������ ���� (sum)�� ũ�ٸ� ���ܱ⸦ �� ��� �����ϰ�
	// M�� �������� ������ �� �ִ� ������ ���̰� �۴ٸ� ���ܱ��� ���̸� �� ª�� �������ش�.
	
	public static void cutting(long start,long end) {
		long mid = (start+end) / 2; //���ܱ��� ����
		sum = 0;
		if(start > end) return; //����Լ� ���� ���� ; ��� Ž���� ������ ��
		
		//������ �� �ִ� ������ ���� ���
		for(int i = 0; i<tree.length ;i++) {
			//������ ���̰� ���ܱ��� ���̺��� ��ٸ� ������ �߸��Ƿ� ������ �� �ִ�.
			if(tree[i] > mid) sum += (tree[i]- mid);
		}
		
		// ���� ���� M <= ������ �� �ִ� ���� 
		// ���ܱ⸦ �� ���� �����Ѵ�. --> ������ �ø���.
		if(sum >= M) {
			result = mid; // result�� �ϴ� �ִ� ���̷� �����Ѵ�.
			if(sum == M) return;
			cutting(mid+1, end); //�� ������ �������� �ִ��� Ȯ���Ѵ�
		}
		//���� ���� M > ������ �� �ִ� ���� --> ���ǿ� ���� �����Ƿ� result���� ���� X
		else{
			cutting(start,mid-1);
		}
		
	}
	
}
