package al_07_08;


import java.io.*;

public class shingle_13251 {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//Input
		int M  = Integer.parseInt(br.readLine()); //색상의 개수
		String str = br.readLine();
		String[] sp = str.split(" ");
		
		int all = 0;
		int [] num = new int[M]; //각 색상 별 조약돌 개수
		for(int i = 0; i< M;i++) {
			num[i] = Integer.parseInt(sp[i]);
			all += num[i]; //조약돌 총 개수
		}
		int K = Integer.parseInt(br.readLine()); //뽑는 조약돌 수
		
		double result[] = new double[50];
		for(int i = 0; i<M;i++) {
			if(num[i]>=K) {
				double tmp = 1.0;
				for(int j = 0; j<K;j++) {
					tmp *= (double)(num[i]-j) / (all-j);
				}
				result[i] = tmp;
			}
		}
		
		double answer = 0;
		for(int i = 0; i<M;i++) {
			answer += result[i];
		}
		
		System.out.print(answer);
	}
	
	
	
}
