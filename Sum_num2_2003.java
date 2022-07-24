package al_07_05;

import java.io.*;


public class Sum_num2_2003 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		//Input
		int N = Integer.parseInt(str.split(" ")[0]);
		long M = Integer.parseInt(str.split(" ")[1]);
		
		str = br.readLine(); // ���� ���� �о��ش�.
		String[] array = str.split(" "); //�迭�� ����ش�.
		
		int start = 0; // start ������
		int end = 0; // end ������
		
		int[] arr = new int[N];
		
		for(int i = 0; i<N;i++) {
			arr[i] = Integer.parseInt(array[i]);
		}
		
		
		long sum = 0; // ��
		int result = 0; // �� ����� ��
		
		while(start < N) { //start�� ������ ���� ������ 

			if(sum >= M || end == N) { //���� sum�� M���� ũ�ų� ������
				sum -= arr[start]; //start ����Ʈ�� ������ �Ű����Ƿ� ���� point�� ���� �������� ���� --> sum���� ���ش�.
				start++;// start point�� ������ �Ű��ش�.
			}
			//���� sum�� M���� �۴ٸ�
			else {
				sum += arr[end]; // end point�� ������ ��
				end++;// end point�� ������ �Ű��ش�.
			}
			
			//���� ���� ��ǥ �հ� ���ٸ� 
			if (sum == M){
				result++; // ����� �ϳ� �����ش�.
				
			}
		}
		
		System.out.print(result);
		
	}

}


