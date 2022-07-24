package al_07_14;

import java.io.*;

public class IntervalSum4_11659 {
	
	public static int N;
	public static int[] num;
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine();
		String[] sp = str.split(" ");
		
		N = Integer.parseInt(sp[0]); //���� ����
		int M = Integer.parseInt(sp[1]); //���� ���ؾ� �ϴ� Ƚ��
		
		num = new int[N+1]; //�� �迭
		
		//�迭�� �� �ֱ� + �������� ���·�
		str = br.readLine();
		sp = str.split(" ");

		for(int i = 1; i<=N;i++) {
			num[i] = num[i-1]+ Integer.parseInt(sp[i-1]);
		}
	
		//������ M�� ���ϱ�
		int[] result = new int[M]; //������� ��� �迭
		for(int i = 0; i<M; i++) {
			str = br.readLine();
			sp = str.split(" ");
			
			//a���� b������ ������
			int a = Integer.parseInt(sp[0]);
			int b = Integer.parseInt(sp[1]);
			
			result[i] = num[b]-num[a-1];
					
		}
		
		//���
		for(int i =0; i<M;i++) bw.write(result[i]+"\n");
		bw.flush();
	}

}
