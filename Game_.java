package al_07_04;

import java.io.*;
import java.util.*;

public class Game_ {
	public static char[][] list;
	public static int n;
	public static int m;
	public static int max = 0;
	public static int[][] d;
	public static boolean [][] visited;
	public static int[] nx = {0,-1,0,1};
	public static int[] ny = {-1,0,1,0};
	public static boolean flag;
	
	public static void dfs(int x,int y,int cnt){
		if (max < cnt) max = cnt;
		d[x][y] = cnt;
		int temp = Character.getNumericValue(list[x][y]);
		
		for (int i = 0; i<4;i++) {
			
			
			int temp_nx = nx[i]*temp + x;
			int temp_ny = ny[i]*temp + y;
			
				
			if(temp_nx <0 || temp_nx >= n || temp_ny <0 || temp_ny >=m)
				continue;
			if(list[temp_nx][temp_ny] == 'H')
				continue;
			
			if(visited[temp_nx][temp_ny]) {
				flag = true;
				return;
			}
			
			if(d[temp_nx][temp_ny] > cnt)
				continue;
			visited[temp_nx][temp_ny] = true;		
			
			dfs(temp_nx,temp_ny,cnt+1);
			
			visited[temp_nx][temp_ny] = false;	
		}
		
		
	}
	 public static void main(String[] args) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 String str = br.readLine();
		 
		 n = Integer.parseInt(str.split(" ")[0]);
		 m = Integer.parseInt(str.split(" ")[1]);
		 
		 list = new char[n][m];
		 
		 for(int i = 0; i < n;i++) {
			 str = br.readLine();
			
			 for(int j = 0; j<m;j++) {
				list[i][j] = str.charAt(j);
			 }
		 }
		 int x = 0;
		 int y = 0;
		 d = new int[n][m];
		 visited = new boolean[n][m];
		 
		 for(int i = 0; i<n;i++) {
			 for(int j = 0;j<m;j++) {
				 d[i][j] = 0;
				 visited[i][j] = false;
			 }
		 }
		 visited[0][0] = true;
		 flag = false;
		 dfs(x,y,1);
		 
		 if(flag == true) 
			 System.out.println("-1");
		 else 
			 System.out.println(max);
		 
	 }
}
