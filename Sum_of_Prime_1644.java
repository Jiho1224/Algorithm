package al_07_07;

import java.io.*;
import java.util.*;

public class Sum_of_Prime_1644 {
	public static boolean []notPrime;
	public static int cnt; //소수의 개수
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int num = Integer.parseInt(br.readLine());
		notPrime = new boolean[(int)num+1];
		
		//소수 list 구해서 구간합 구하기?
		sieve(num);
		int[] primeList = new int[cnt];
		
		int tmp = 0;
		for(int i = 2; i<=num;i++) {
			if(notPrime[i] == false) {
				primeList[tmp++] = i;
			}
		}

		int start = 0;
		int end = 0;
		
		int sum = 0;
		int result = 0;
		
		while(start<cnt) {
			if(sum >= num || end == cnt) {
				sum -= primeList[start];
				start++;
			}
			else {
				sum += primeList[end];
				end++;
			}
			if(sum == num) {
				result++;
			}
		}
		
		bw.write(result+"");
		bw.flush();
	}
	
	public static void sieve(int num) {
		for(int i = 2;i<=Math.sqrt(num);i++) {
			if(!notPrime[i]) {
				for(int j = i*2;j<=num;j +=i) {
					if(j % i == 0) notPrime[j] = true;
				}
			}
		}
		for(int i = 2; i<=num;i++) {
			if(notPrime[i] == false) cnt++;
		}
	}
}


