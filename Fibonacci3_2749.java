package al_07_05;

import java.io.*;


public class Fibonacci3_2749 {
	public static long[][] arr = {{1,1},{1,0}};
	public static void main(String[] args) throws IOException {
		
		//INPUT
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long num = Long.parseLong(br.readLine());
		
		// IDEA : Çà·Ä °ö
		
		if(num == 1) System.out.print(1);
		else {
			long[][] result = calcul(num-1);
			System.out.print(result[0][0]);
		}
		
		
	}
	public static long[][] calcul(long num){
		long[][] tmp = new long[2][2];
		
		
		if(num == 1) {
			for(int i = 0;i<2;i++) {
				for(int j = 0;j<2;j++) {
					tmp[i][j] = arr[i][j];
				}
			}
			
			return tmp;
		}
		
		long[][] m;
		m = calcul(num/2);
		
		for(int i = 0; i<2;i++) {			
			for(int j = 0; j<2;j++) {
				for(int k = 0; k<2;k++)
					tmp[i][j] += m[i][k] * m[k][j];
				tmp[i][j] %= 1000000;
			}			
		}
		
		//È¦¼ö¶ó¸é
		if(num % 2 == 1) {
			long[][] tmp2 = new long[2][2];
			for(int i = 0; i<2;i++) {			
				for(int j = 0; j<2;j++) {
					for(int k = 0; k<2;k++)
						tmp2[i][j] += tmp[i][k] * arr[k][j];
					tmp2[i][j] %= 1000000;
				}			
			}
			return tmp2;
		}
		
		return tmp;
	}
}
