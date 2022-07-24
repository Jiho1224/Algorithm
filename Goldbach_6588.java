package al_07_07;

import java.io.*;
import java.util.*;

public class Goldbach_6588 {
	public static ArrayList<Integer> prime;
	public static boolean isPrime[];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		prime = new ArrayList<>();
		
		//범위에 대한 소수 목록 구하기
		sieve(1000000);
		
		int size = prime.size();
		
		while(true) {
			
			int num = Integer.parseInt(br.readLine());
			if(num == 0) break;
						
			int start = 0;
			int end =  binary(start, size-1, num);
			
			
			while(true) {
				if(start>end) {
					bw.write("Goldbach's conjecture is wrong."+"\n");
					break;
				}
				
				int tmp = prime.get(start)+prime.get(end);
				
				if(tmp == num) {
					
					int tmpA = prime.get(start);
					int tmpB = prime.get(end);
					
					for(int i = 0; i< start;i++) {
						int tmp2 = prime.get(i);
						if(isPrime[num-tmp2] == false && isPrime[tmp2] == false) {
							tmpA = prime.get(i);
							tmpB = num - tmpA;
						}
					}
					
					bw.write(num +" = "+tmpA+" + "+tmpB+"\n");
					break;
				}
				else if(tmp > num) {
					end--;
				}
				else {
					start++;
				}
			}
		}
		bw.flush();
	}
	public static void sieve(int num) {
		isPrime = new boolean[num+1];
		for(int i = 2; i<Math.sqrt(num);i++) {
			if(isPrime[i] == false) {
				for(int j = i*2; j<num; j+=i) {
					if(j % i == 0) isPrime[j] = true;
				}
			}
		}
		isPrime[2] = true; // true면 prime이 아니고 false여야지 prime이다.
		for(int i = 2;i<num;i++) {
			if(isPrime[i] == false) prime.add(i);
		}
	}
	
	public static int binary(int start, int end, int num){
		int mid = (start+end) / 2;
		if ((prime.get(mid+1)>num && num>= prime.get(mid)) || start == mid) return mid;
		if(prime.get(mid) < num) return binary(mid+1,end,num);
		return binary(start,mid-1,num);
		
	}
	
}
