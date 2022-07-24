package al_07_05;

import java.io.*;

public class Tree_2805 {
	
	public static long result = 0;
	public static long tree[];
	public static long N,M;
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		
		//Input ; ù ��° ��
		String str = br.readLine();
		String[] sp = str.split(" ");
		N = Long.parseLong(sp[0]); // ������ ����
		M = Long.parseLong(sp[1]); // ���������� �ϴ� ���� �ּ� M����
		
		//Input ; �� ��° ��
		str = br.readLine();
		sp = str.split(" ");
		
		tree = new long[(int)N]; //���� ������ ���̸� �����ϴ� �迭
		long max = 0;
		for(int i = 0; i<N;i++) {
			tree[i] = Long.parseLong(sp[i]);
			max = Math.max(max, tree[i]); // ���� �� ������ ���̸� ���ؼ� ����
		}
		
		binary(0,max);
		
		bw.write(result+" ");
		bw.flush();

	}
	
	public static void binary(long start,long end) {
		long mid = (start+end)/2; //�߰����� ���ܱ��� ���̷� �����Ѵ�.
		long sum = 0;
		
		if(start > end) return; //���� Ž���ϴٰ� start>end��� Ž���� �����.
		
		for(int i = 0; i<N;i++) {
			//���� ���ܱ� ���̺��� tree�� ���̰� ��ٸ� 
			//���ܱ⿡ ���� tree�� �߶����Ƿ� result�� �߰��Ѵ�.
			if(tree[i]>mid) sum += (tree[i]-mid); 
		}
		
		//���� ���� M���� ũ�ٸ� �ּҰ��� ã�� ���� ���ܱ��� ������ ��������
		if(sum >= M) {
			result = mid; //�ϴ� ����
			if(sum == M) return;
			binary(mid+1,end);	
		}
		//���� ���� M���� �۴ٸ� ���� ���� X
		//���ܱ��� ������ �����.
		else {
			binary(start,mid-1);
		}
	}

}
