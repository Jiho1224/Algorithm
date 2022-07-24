package al_07_04;

import java.io.*;

public class Glacier {
	
	public static int[][] glacier;
	public static int[] dx = {-1,0,1,0};
	public static int[] dy = {0,-1,0,1};
	public static boolean [][] visit;
	public static int result = 0;
	public static int row, col;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine();
		String []sp = str.split(" ");
		
		row = Integer.parseInt(sp[0]);
		col = Integer.parseInt(sp[1]);
		
		glacier = new int[row][col];
		
		
		for(int i = 0; i<row;i++) {
			str = br.readLine();
			sp = str.split(" ");
			for(int j = 0; j<col;j++) {
				glacier[i][j] = Integer.parseInt(sp[j]);
			}
		}
		
		//�湮 ���� �ʱ�ȭ
		visit = new boolean[row][col];
		boolean flag = true;
		while(true) {
			int sum = 0; //���� ��� ���� �ʱ�ȭ
			visit = new boolean[row][col]; //�湮 ���� �ʱ�ȭ
			
			for(int i = 0; i<row;i++) {
				for(int j = 0; j<col;j++) {
					//������ �����ϰ� �湮���� �ʾҴٸ�
					if(glacier[i][j] != 0 && !visit[i][j]) {
						dfs(i,j);
						sum++;
					}
				}
			}
			
			//������ �и��Ǿ��ٸ� ��� ����
			if(sum > 1) break;
			
			//������ �ƿ� 0�� ���
			if(sum == 0) {
				flag = false;
				break;
			}
			//������ �и����� �ʾҴٸ� 1�� �� �Ǵ�
			result++;
			
			//���� ���̱�
			melt();
		}
		if(flag)
			bw.write(result+"");
		else
			bw.write(0+"");
		bw.flush();
		
	}
	
	//���� ��� ���� Ȯ���ϱ� ���� dfs �˰���
	public static void dfs(int x,int y) {
		//��� ���� ����
		if(!visit[x][y]) visit[x][y] = true;
		else return;
		
		for(int i = 0; i<4;i++) {
			int nx = dx[i]+x;
			int ny = dy[i]+y;
			
			if(nx<0 || ny<0 || nx>=row||ny>=col) continue;
			//���� glacier�� �ش� �κ��� ���̶�� continue
			if(glacier[nx][ny] == 0) continue;
			
			//���� glacier�� �ش� �κ��� �̹� �湮�ߴٸ� continue;
			if(visit[nx][ny]) continue;
			
			dfs(nx,ny);
		}
	}
	
	public static void melt() {
		int [][] tmp2 = new int[row][col];
		//glacier ���� + deep copy
		for(int i = 0; i<row;i++) {
			for(int j = 0; j<col;j++)
				tmp2[i][j] = glacier[i][j];
		}

		for(int i = 0; i<row;i++) {
			for(int j = 0; j<col;j++) {
				if(glacier[i][j] != 0) {
					int n = 0;
					
					//���������� ���캻 �� 
					//0�� ������ ����.
					for(int p = 0 ;p<4;p++) {
						int nx = dx[p]+i;
						int ny = dy[p]+j;
						if(nx<0 || ny<0 || nx>=row||ny>=col) continue;
						if(tmp2[nx][ny] == 0) n++;
					}
					
					if(glacier[i][j] <= n) glacier[i][j] = 0;
					else glacier[i][j] -= n; //0�� ������ŭ ������ �쿩�ش�.
				}
			}
		}
	}
	
	
}
