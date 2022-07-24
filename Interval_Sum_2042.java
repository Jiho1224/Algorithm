package al_07_06;

import java.io.*;

public class Interval_Sum_2042 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//INPUT
		String str = br.readLine();
		String[] split = str.split(" ");
		
		int N = Integer.parseInt(split[0]); // ���� ����
		int M = Integer.parseInt(split[1]); // ������ �Ͼ�� Ƚ��
		int K = Integer.parseInt(split[2]); // ������ ���� ���ϴ� Ƚ��
		
		int lp = M+K; //���� OR ������ �� ���ϴ� ������ �Է¹��� Ƚ��
		
		int tempN = N;
		int cnt = 0; //N�� 2�� �� ������ �˱� ����
		
		//N�� 2�� �� ������ �Ǵ�
		//���� 2^k�� ����������� ������ �������� 0���� ä���.
		while(true) {
			if(tempN == 1) break;
			if(tempN % 2 == 0) {
				cnt++;
				tempN /= 2;
			}
			else tempN += 1;
		}
				
		
		int temp_Num = (int) Math.pow(2, cnt);
		long [] tree = new long[2*temp_Num]; // tree ����
		tree[0] = 0; //0th�� ���X
		
		//tree�� ���� ä���ֱ�
		
		//������ �迭�� leaf node�� �ֱ�
		for(int i = 0 ; i < temp_Num;i++) {
			if (i<N) tree[temp_Num+i] = Long.parseLong(br.readLine()); //leaf node�� ����
			else tree[i+temp_Num] = 0;
		}
		
		//parent node�� ���������� ä���ֱ�
		for(int i = temp_Num-1; i > 0;i--) {
			
			tree[i] = tree[i*2]+tree[i*2+1]; //left child�� right child�� ���ؼ� ����
		}
		
		for(int i = 0;i<lp;i++) {
			str= br.readLine();
			split = str.split(" ");
			int a = Integer.parseInt(split[0]);
			int b = Integer.parseInt(split[1]);
			long c = Long.parseLong(split[2]);
			
			// a==1�̸� �� ����
			if(a == 1) {
				long tmp = tree[temp_Num-1+b];// �ش� �� �ӽ� ����
				int point  = temp_Num-1+(int)b;
				while(point >= 1) {
					tree[point] = tree[point] - tmp + c;
					point /= 2; //parent�� ��� �ٲ��ֱ�
				}
			}
			//a == 2�̸� b~c ������ �� ���ϱ�
			else {
				b = temp_Num-1+b;
				int d = temp_Num-1+(int)c;
				// b~d���� ������ ���ϱ�
				long sum = 0;
				//b�� d�� �������� loop�� ����!
				while(b<=d) {
					
					//left child�� index�� ¦��, right child�� index�� Ȧ������ �̿��Ѵ�.
					
					//b�� right child��� parent�� ������ �������� �ʴ� ���̹Ƿ�
					//sum�� b��°�� ���� �����ش�.
					if(b%2 == 1) {
						sum += tree[b];
						//���� �θ�� �ö󰣴�.
						b = (b+1)/2;
					}
					
					//left child��� �ڽ��� parent�� �ö󰣴�.
					else {
						b = b/2;
					}
					
					//d�� left child��� parent�� ������ �������� �ʴ� �͹Ƿ�
					//sum�� d��°�� ���� �����ش�.
					if(d%2 == 0) {
						sum+= tree[d];
						d = (d-1)/2; //�ڽ��� ���� ���� �θ�� �ö󰣴�.
					}
					else {
						d = d/2; //�ڽ��� �θ�� �ö󰣴�.
					}
										
				}
				//sum�� ��� ���ϰ� ���
				bw.write(sum+"\n");
			}
		}

		//����
		bw.flush();
	}

}
