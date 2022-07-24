package al_07_05;

import java.io.*;

public class Fibonacci2_2748 {
	
	public static long result = 0; // 결과값
	public static long[] d;
	public static void main(String[] args) throws IOException {
		
		//INPUT
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		
		//Memoization을 위한 배열
		d = new long[91];
		
		for(int i=0;i<num;i++) d[i] = 0; 
		
		System.out.println(fibonacci(num));
	}
	
	public static long fibonacci(int num) {
		if (num == 1 || num == 2) return 1;
		
		if (d[num] != 0) {
			return d[num];
		}
		d[num] = fibonacci(num-1)+fibonacci(num-2);
				
		return d[num];
	}

}
