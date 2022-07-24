package al_07_06;

import java.io.*;

public class CandyBox_2243 {
	
	public static long[] tree;
	public static int tmpN;
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		
		//1���� 1,000,000���� + 999999 + 1
		for(tmpN=1; tmpN<1000000; tmpN*=2);
		
		tree = new long[2*tmpN];
		
		for(int i = 0; i<n;i++) {
			String str = br.readLine();
			String[] sp = str.split(" ");
			
			//1�� �ԷµǾ��� ���
			if(sp.length == 2) {
				int B = Integer.parseInt(sp[1]); //���� ������ ����
				int result = find(B);
				bw.write(result+"\n");
				update(result,-1);
			}
			
			//2�� �ԷµǾ��� ���
			else {
				int B = Integer.parseInt(sp[1]); //������ ����
				int C = Integer.parseInt(sp[2]); //�ְų� ������ ����
				
				// B ���� ������ ������忡 �ְ� ������Ʈ
				update(B,C);
			}
		}
		
		bw.flush();

	}
	
	// [] level�� ������ ������ n��ŭ ����
	public static void update(int level,long n) {
		level = tmpN + level - 1; //�������ȭ
		//�θ�� �Ž��� �ö󰡸鼭 ���������� ������Ʈ
		while(level >= 1) {
			tree[level] += n;
			level /= 2;
		}
	}
	
	public static int find(int num) {
		int index = 1;
		while(index < tmpN*2) {
			if(tree[index] < num) num -= tree[index++];			
			index = index*2;
		}
		index /= 2;
		num = index - tmpN + 1;
		return num;
	}

}
