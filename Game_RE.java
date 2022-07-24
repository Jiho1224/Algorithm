package al_07_04;

import java.io.*;


public class Game_RE {
	public static int row;
	public static int col;
	public static char [][] board; //���� ���� �Է�
	public static boolean [][] visited; // cycle�� �����Ǵ��� ���θ� �˱� ���� ����
	public static int max = 0; // �ִ� �̵� Ƚ�� ����
	 
	public static boolean flag = false; // cycle�� �����ȴٸ� flag = true -> -1  ���
	// x������ Ȥ�� y������ �����̱� ���� ����
	// ����, ����,������,�Ʒ��� ������..
	public static int[] dx = {0,-1,0,1};
	public static int[] dy = {-1,0,1,0};
	
	public static void main(String[] args) throws IOException {
		//�Է��� �ޱ� ���� BufferedReader
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		// Input
		row = Integer.parseInt(str.split(" ")[0]);
		col = Integer.parseInt(str.split(" ")[1]);
		
		//�Էµ� row�� col�� �´� board�� visited ����
		visited = new boolean[row][col];
		board = new char[row][col];
		
		for(int i = 0; i<row;i++) {
			str = br.readLine();
			for(int j = 0; j<col;j++) {
				board[i][j] = str.charAt(j);
				visited[i][j] = false; // ��� �湮���� ���� ������ �ʱ�ȭ
			}
		}
		
		visited[0][0] = true; // ���� ����Ʈ�� �湮�������� ����.
		dfs(0,0,1); // dfs�� ������.
        //( cnt�� �� �������� board���� out�Ǵ��� �����̱� ������ 1�� �ʱⰪ���� ����)
		if (flag == true) System.out.println("-1");
		else System.out.println(max);
	}
	
	public static void dfs(int x,int y,int cnt) {
		// ���� ��ġ�ϴ� ���� ���ڸ� �д´�. (���� ���������� ĭ ����)
		int move_num = Character.getNumericValue(board[x][y]); 
		if (max < cnt) max = cnt; // �ִ� �̵�Ƚ�� ����
		
		//��,��,��,��
		for(int i = 0; i<4;i++) {
			
			//�̵��ؾ��� ��ǥ
			int nx = dx[i]*move_num + x;
			int ny = dy[i]*move_num + y;
			
			//out of boundary
			if (nx <0 || ny <0 || nx >= row || ny >= col) {
				continue; // pass
			}
			
			// ����(H)�� ������ ���
			if (board[nx][ny] == 'H') {
				continue; //pass
			}
			
			// (nx,ny)�� �̹� �� �� �湮���� ��� -> cycle! -> ���� �� ����.
			if(visited[nx][ny] == true) {
				flag = true;
				return;
			}
			
			// �̿��� ��쿡�� ������ �� �ִ�.
			
			visited[nx][ny] = true;
			dfs(nx,ny,cnt+1); // �̵��� ������ ���� �ٽ� ���� // ���� recursion�� �����ٸ� �ٸ� root Ȯ�� (���)
			visited[nx][ny] = false; // ���� ��츦 ���� ���� ���� �ٽ� backtracking
		}
	}
}
