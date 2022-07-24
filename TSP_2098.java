package al_07_15;

import java.io.*;

public class TSP_2098 {
	
	public static int SIZE = (1<<16);
	public static int INF = 20000000;
	
	public static int dp[][] = new int[16][SIZE];
	public static int [][] matrix;
	public static int N;
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// INPUT ///////////////////////////////////////////////////////
		
		N = Integer.parseInt(br.readLine()); // ������ ��
		
		matrix = new int[N][N];
		
		
		for(int i = 0; i<N;i++) {
			String str = br.readLine();
			String[] sp = str.split(" ");
			for(int j = 0; j<N;j++) {
				matrix[i][j] = Integer.parseInt(sp[j]);
				
			}		
			
		}
		
		for(int i = 0; i<N;i++) {
			for(int j = 0; j < (1<<N)-1;j++) dp[i][j] = -1;
		}
		
		// Travel Start! ///////////////////////////////////////////////
		
		//0�� ���ú��� Ž�� ���� (��𼭺��� �����ص� �������)
		//check�� 0���� �̹� Ž���� ���̹Ƿ� 0001 ��, 2^(0) = 1�� ����
		int result = travel(0,1);
		
		// ��� ////////////////////////////////////////////////////////
		
		bw.write(result+"\n");
		
		br.close();
		bw.flush();

	}
	public static int travel(int x, int check) {
		//��� ���ø� �湮�Ͽ��ٸ� --> (1111..)�� ���̴� == 2^(N)-1
		if(check == (1<<N)-1) {
			if(matrix[x][0] == 0) return INF; //��ΰ� ���ٴ� ��
			else return matrix[x][0]; //��ΰ� ���� ��� �ִ� �Ÿ� return
		}
		
		//���� �̹� �湮�� ���ö��
		if(dp[x][check] != -1) return dp[x][check]; //����س��� ���� ��ȯ
		
		dp[x][check] = INF; //�湮 üũ
		
		//������ ���õ�� �̷���� subset Ž��
		for(int i = 0; i<N;i++) {
			int nexti = check | (1<<i); //�� ĭ �� �̷ﰡ�鼭 �ٸ� ���� �Ǵ�
			
			// ���� ��ΰ� ��� INF�� �����ְų�
			// �̹� i ���ø� �湮�� ��� (���� ���� check�� i���ø� & ������ ���ؼ� 0�� �ƴ� 1�� ���´ٸ�
			// i���ô� �̹� check�� subset�� ���������̹Ƿ�
			
			if(matrix[x][i] == 0 || (check &(1<<i)) != 0) continue;
			
			dp[x][check] = Math.min(dp[x][check], travel(i,nexti) + matrix[x][i]);
		}
		return dp[x][check];
	}
	
	

}
