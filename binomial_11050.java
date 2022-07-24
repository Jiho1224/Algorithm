package al_07_08;

import java.io.*;
public class binomial_11050 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine();
		String[] split_Line = str.split(" ");
		int N = Integer.parseInt(split_Line[0]);
		int K = Integer.parseInt(split_Line[1]);
		
		// N C K = N! / (N-K)!K!
		if(K == 0) {
			bw.write(1+"");
		}
		else {
			int tmp = fact(N) / (fact(N-K)*fact(K));
			bw.write(tmp+"");
		}
		bw.flush();	
	}
	
	public static int fact(int num) {
		if (num == 1 || num == 0) return 1;
		else return num*fact(num-1);
	}

}
