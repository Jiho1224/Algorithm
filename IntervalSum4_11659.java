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
		
		N = Integer.parseInt(sp[0]); //수의 개수
		int M = Integer.parseInt(sp[1]); //합을 구해야 하는 횟수
		
		num = new int[N+1]; //수 배열
		
		//배열에 수 넣기 + 누적합의 형태로
		str = br.readLine();
		sp = str.split(" ");

		for(int i = 1; i<=N;i++) {
			num[i] = num[i-1]+ Integer.parseInt(sp[i-1]);
		}
	
		//구간합 M번 구하기
		int[] result = new int[M]; //결과값을 담는 배열
		for(int i = 0; i<M; i++) {
			str = br.readLine();
			sp = str.split(" ");
			
			//a에서 b까지의 구간합
			int a = Integer.parseInt(sp[0]);
			int b = Integer.parseInt(sp[1]);
			
			result[i] = num[b]-num[a-1];
					
		}
		
		//출력
		for(int i =0; i<M;i++) bw.write(result[i]+"\n");
		bw.flush();
	}

}
