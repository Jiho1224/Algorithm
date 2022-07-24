package al_07_04;

import java.io.*;


public class Game_RE {
	public static int row;
	public static int col;
	public static char [][] board; //보드 정보 입력
	public static boolean [][] visited; // cycle이 생성되는지 여부를 알기 위해 선언
	public static int max = 0; // 최대 이동 횟수 저장
	 
	public static boolean flag = false; // cycle이 생성된다면 flag = true -> -1  출력
	// x축으로 혹은 y축으로 움직이기 위한 변수
	// 왼쪽, 위쪽,오른쪽,아래쪽 순으로..
	public static int[] dx = {0,-1,0,1};
	public static int[] dy = {-1,0,1,0};
	
	public static void main(String[] args) throws IOException {
		//입력을 받기 위한 BufferedReader
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		// Input
		row = Integer.parseInt(str.split(" ")[0]);
		col = Integer.parseInt(str.split(" ")[1]);
		
		//입력된 row와 col에 맞는 board와 visited 생성
		visited = new boolean[row][col];
		board = new char[row][col];
		
		for(int i = 0; i<row;i++) {
			str = br.readLine();
			for(int j = 0; j<col;j++) {
				board[i][j] = str.charAt(j);
				visited[i][j] = false; // 모두 방문하지 않은 것으로 초기화
			}
		}
		
		visited[0][0] = true; // 시작 포인트는 방문했음으로 변경.
		dfs(0,0,1); // dfs를 돌린다.
        //( cnt는 맨 마지막에 board에서 out되더라도 움직이기 때문에 1을 초기값으로 설정)
		if (flag == true) System.out.println("-1");
		else System.out.println(max);
	}
	
	public static void dfs(int x,int y,int cnt) {
		// 현재 위치하는 곳의 숫자를 읽는다. (다음 움직여야할 칸 개수)
		int move_num = Character.getNumericValue(board[x][y]); 
		if (max < cnt) max = cnt; // 최대 이동횟수 변경
		
		//왼,위,오,아
		for(int i = 0; i<4;i++) {
			
			//이동해야할 좌표
			int nx = dx[i]*move_num + x;
			int ny = dy[i]*move_num + y;
			
			//out of boundary
			if (nx <0 || ny <0 || nx >= row || ny >= col) {
				continue; // pass
			}
			
			// 구멍(H)를 만났을 경우
			if (board[nx][ny] == 'H') {
				continue; //pass
			}
			
			// (nx,ny)를 이미 한 번 방문했을 경우 -> cycle! -> 무한 개 가능.
			if(visited[nx][ny] == true) {
				flag = true;
				return;
			}
			
			// 이외의 경우에는 움직일 수 있다.
			
			visited[nx][ny] = true;
			dfs(nx,ny,cnt+1); // 이동한 곳으로 가서 다시 동작 // 만약 recursion이 끝난다면 다른 root 확인 (재귀)
			visited[nx][ny] = false; // 다음 경우를 살펴 보기 위해 다시 backtracking
		}
	}
}
