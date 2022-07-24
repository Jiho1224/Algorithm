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
		
		//방문 정보 초기화
		visit = new boolean[row][col];
		boolean flag = true;
		while(true) {
			int sum = 0; //빙하 덩어리 개수 초기화
			visit = new boolean[row][col]; //방문 정보 초기화
			
			for(int i = 0; i<row;i++) {
				for(int j = 0; j<col;j++) {
					//빙산이 존재하고 방문하지 않았다면
					if(glacier[i][j] != 0 && !visit[i][j]) {
						dfs(i,j);
						sum++;
					}
				}
			}
			
			//빙산이 분리되었다면 재귀 종료
			if(sum > 1) break;
			
			//빙산이 아예 0개 라면
			if(sum == 0) {
				flag = false;
				break;
			}
			//빙산이 분리되지 않았다면 1년 후 판단
			result++;
			
			//빙산 녹이기
			melt();
		}
		if(flag)
			bw.write(result+"");
		else
			bw.write(0+"");
		bw.flush();
		
	}
	
	//빙하 덩어리 개수 확인하기 위한 dfs 알고리즘
	public static void dfs(int x,int y) {
		//재귀 종료 조건
		if(!visit[x][y]) visit[x][y] = true;
		else return;
		
		for(int i = 0; i<4;i++) {
			int nx = dx[i]+x;
			int ny = dy[i]+y;
			
			if(nx<0 || ny<0 || nx>=row||ny>=col) continue;
			//만약 glacier의 해당 부분이 물이라면 continue
			if(glacier[nx][ny] == 0) continue;
			
			//만약 glacier의 해당 부분을 이미 방문했다면 continue;
			if(visit[nx][ny]) continue;
			
			dfs(nx,ny);
		}
	}
	
	public static void melt() {
		int [][] tmp2 = new int[row][col];
		//glacier 저장 + deep copy
		for(int i = 0; i<row;i++) {
			for(int j = 0; j<col;j++)
				tmp2[i][j] = glacier[i][j];
		}

		for(int i = 0; i<row;i++) {
			for(int j = 0; j<col;j++) {
				if(glacier[i][j] != 0) {
					int n = 0;
					
					//동서남북을 살펴본 후 
					//0의 개수를 센다.
					for(int p = 0 ;p<4;p++) {
						int nx = dx[p]+i;
						int ny = dy[p]+j;
						if(nx<0 || ny<0 || nx>=row||ny>=col) continue;
						if(tmp2[nx][ny] == 0) n++;
					}
					
					if(glacier[i][j] <= n) glacier[i][j] = 0;
					else glacier[i][j] -= n; //0의 개수만큼 빙산을 녹여준다.
				}
			}
		}
	}
	
	
}
