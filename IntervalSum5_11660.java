package al_07_14;

import java.io.*;

public class IntervalSum5_11660 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine();
		String[] sp = str.split(" ");
		
		int N = Integer.parseInt(sp[0]); //N*N matrix
		int M = Integer.parseInt(sp[1]); //구간합 구하는 횟수
		
		//행렬 생성 및 입력 정보 저장 (누적합 2차원 테이블)
		long matrix[][] = new long[N+1][N+1];
		for(int i = 1;i<=N;i++) {
			str = br.readLine();
			sp = str.split(" ");
			
			for(int j = 1; j<=N;j++) {
				int tmp = Integer.parseInt(sp[j-1]); //현재 누적합 행렬의 값
				
				
				//(1,[])일 경우 자기자신의 값만 넣어준다.
				if(j == 1) matrix[i][j] = tmp;

				else {
					matrix[i][j] = tmp + matrix[i][j-1];
				}
			}
		}
		
		//구간합 구하기 M번 반복
		long result[] = new long[M];
		
		for(int i = 0; i<M;i++) {
			str = br.readLine();
			sp = str.split(" ");
			
			//(X1,Y1)
			int X1 = Integer.parseInt(sp[0]);
			int Y1 = Integer.parseInt(sp[1]);
			
			//(X2,Y2)
			int X2 = Integer.parseInt(sp[2]);
			int Y2 = Integer.parseInt(sp[3]);
			
			
			int sum = 0;
			
			for(int j = X1; j<=X2;j++) {
				sum += matrix[j][Y2]-matrix[j][Y1-1];
			}
			result[i] = sum;
		}
		
		//출력
		for(int i = 0; i<M;i++) bw.write(result[i]+"\n");
		bw.flush();
	}

}
