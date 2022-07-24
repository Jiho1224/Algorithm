package al_07_07;

import java.io.*;
import java.util.*;

public class Sieve_of_Eratosthenes_2960 {
	public static ArrayList<Integer> sieve;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
		String str = br.readLine();
		String []str_split = str.split(" ");
		
		int N = Integer.parseInt(str_split[0]);
		int K = Integer.parseInt(str_split[1]);
		
		//2부터 N까지의 숫자를 배열에 담는다.
		sieve = new ArrayList<>();
		for(int i = 0; i<N-1;i++) {
			sieve.add(i+2);
		}
		int result = Eratos(N,K);
		bw.write(result+"");
		
		br.close();
		bw.flush();
	}
	
	public static int Eratos(int N,int K) {
		int cnt = 0; //지우는 숫자 카운트
		
		for(int i = 0; i<Math.sqrt(N);i++) {
			int tmp = sieve.get(0); // 가장 작은 수
			for(int j = tmp; j<=N;j++) {
				if(j % tmp == 0 && sieve.contains(j)) {
					sieve.remove(Integer.valueOf(j));
					cnt++;
				}
				if(cnt == K) return j;
			}
		}
		return -1;
	}
}
