package al_07_07;

import java.io.*;
import java.util.*;
import java.math.*;

public class Make_Cipher_1837 {
	public static boolean notPrime[];
	public static int prime[];
	public static int cnt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
		String str = br.readLine();
		String []str_split = str.split(" ");
		
		BigInteger P = new BigInteger(str_split[0]);
		long K = Long.parseLong(str_split[1]);
		
		sieve(K);
		
		//K 이하 소수 list 생성
		prime = new int[cnt];
		int tmp = 0;
		for(int i = 2; i<K;i++) {
			if(!notPrime[i]) prime[tmp++] = i;
		}
		
		boolean isOk = true;
		int notOK_NUM = -1;
		for(int i = 0; i<cnt;i++) {
			BigInteger tmp2 = new BigInteger(Integer.toString(prime[i]));
			if(P.mod(tmp2).compareTo(BigInteger.ZERO) == 0) {
				isOk = false;
				notOK_NUM = prime[i];
				break;
			}
		}
		
		if(isOk) bw.write("GOOD");
		else bw.write("BAD "+notOK_NUM);
		bw.flush();
	}
	
	public static void sieve(long num) {
		notPrime = new boolean[(int) num];
		int tmp = (int)num;
		
		for(int i = 2; i<Math.sqrt(tmp);i++) {
			if(!notPrime[i]) {
				for(int j = i*2; j < tmp; j +=i) {
					notPrime[j] = true;
				}
			}
		}
		
		for(int i = 2; i<tmp;i++) {
			if(!notPrime[i]) cnt++;
		}
	}
	

}
