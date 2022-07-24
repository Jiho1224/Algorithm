package al_07_05;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class sum_zero_7453 {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		
		long []A = new long[num];
		long []B = new long[num];
		long []C = new long[num];
		long []D = new long[num];
		int temp = 0;
		
		for(int i = 0; i < num;i++) {
			String str = br.readLine();
			String[] split_line = str.split(" ");
			
			A[temp] = Long.parseLong(split_line[0]);
			B[temp] = Long.parseLong(split_line[1]);
			C[temp] = Long.parseLong(split_line[2]);
			D[temp] = Long.parseLong(split_line[3]);
			
			temp++;
		}
			
		
		long []temp_AB = new long[num*num];
		long []temp_CD = new long[num*num];
		int cnt = 0;
		
		//A�� B �迭 ���Ҹ� ���� ����� �迭 ����� + C�� D �迭 ���Ҹ� ���� ����� �迭 �����
		for(int i = 0; i< num;i++) {
			for(int j = 0;j<num;j++) {
				temp_AB[cnt] = A[i] + B[j];
				temp_CD[cnt] = C[i] + D[j];
				cnt++;
			}
		}
		
		//��ģ �� �迭�� ����
		//����ִ� ���ڰ� �ٸ����� �� �迭�� ���ڴ� ������ �ٸ��� ������ �ߺ� ���Ҵ� �������� �ʴ´�!
		Arrays.sort(temp_AB);
		Arrays.sort(temp_CD);
		
		int start = 0; // A�� B �迭�� ó������ �����Ѵ�.
		int end = num*num-1; // C�� D �迭�� ���������� �����Ѵ�. (����� ���ɼ��� �����Ƿ�)
		long result = 0;
		long last = num*num;
		
		while(start<last && 0<= end) {
			
			
			long tmp1 = temp_AB[start];
			long tmp2 = temp_CD[end];
			
			long cal = tmp1 + tmp2;
			
			if(cal> 0) end -= 1;
			else if(cal <0) start +=1;
			else {
				long temp_x =0, temp_y = 0;
				while(start < last && tmp1 == temp_AB[start]) {
					start++;
					temp_x++;
				}
				while(end>=0 && tmp2 == temp_CD[end]) {
					end--;
					temp_y++;
				}
				result += temp_x*temp_y;
			}
		}
		
		System.out.print(result);
		
			
		
	}
	
	

}
