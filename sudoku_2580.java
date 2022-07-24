package al_07_04;

import java.io.*;
import java.util.*;

//좌표 정보를 담는 class Point
class Point{
	int x,y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class sudoku_2580 {
	
	public static int[][] board; // board 상태를 저장
	public static ArrayList<Point> zero; //빈칸 좌표를 담는 ArrayList
	static BufferedWriter bw; //출력을 위해 BufferedWriter 선언
	
	public static void main(String[] args) throws IOException {
		
		//INPUT
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str;
		String[] str_split;
		zero = new ArrayList<>();
		
		board = new int[9][9];
		
		//board 상태 저장
		for(int i = 0; i<9;i++) {
			str = br.readLine();
			str_split = str.split(" ");
			for(int j = 0; j<9;j++) {
				board[i][j] = Integer.parseInt(str_split[j]);
				if(board[i][j] == 0) { //0이라면 해당 좌표를 zero 리스트에 추가한다.
					zero.add(new Point(i,j));
				}
			}	
		}
		
		//0의 위치 파악하고, 가로 세로, 정사각형 파악
		//0에 넣을 수 있는 숫자를 파악한 후 넣어보고 다음 0 탐색. 만약 빈칸에 1~9까지 모두 안들어가면
		//백트래킹을 하여서 이전 0에 다른 숫자 넣어볼 것.
		
		sudoku(0);
		
		br.close();
	}
	
	public static void sudoku(int idx) throws IOException{ //탐색할 x,y좌표의 index와 숫자를 집어넣은 개수를 입력받음
				
		//만약에 스도쿠 정답이 나온다면 해당 정답인 board를 모두 출력해주고
		//자바 강제 종료
		if(zero.size() == idx) {
			for(int i = 0; i<9;i++) {
				for(int j = 0; j<9;j++) {
					bw.write(board[i][j]+" ");
				}
				bw.write("\n");
			}
			bw.flush();
			System.exit(0);
		}
		
		int tmpX = zero.get(idx).x; // 빈칸의 x좌표
		int tmpY = zero.get(idx).y; // 빈칸의 y좌표
		
		for(int i = 1; i<=9;i++) {
			
			//안전하다면 (스도쿠의 규칙을 위배하지 않는다면)		
			if(is_ok(idx,i)) {
				board[tmpX][tmpY] = i; //board에 해당 숫자를 넣고
				sudoku(idx+1); //다음 zero 부분을 파악해준다.
				board[tmpX][tmpY] = 0; //backtrack한다. (다시 빈칸으로 초기화)
			}
		}
		
		
	}
	
	//유효성 검사 함수
	public static boolean is_ok(int idx,int num) {
		int tmpX = zero.get(idx).x;
		int tmpY = zero.get(idx).y;
		
		
		for(int i = 0;i<9;i++) {
			//같은 행에 같은 숫자가 존재하는지 검사
			if(board[tmpX][i] == num) return false;
			//같은 열에 같은 숫자가 존재하는지 검사
			if(board[i][tmpY] == num) return false;
		}

		
		//3*3 박스 안에 같은 숫자가 존재하는지 검사
		int row = (tmpX/3)*3;
		int col = (tmpY/3)*3;
		
		for(int i = row;i<row+3;i++) {
			for(int j = col; j<col+3; j++) {
				if(board[i][j] == num) return false;
			}
		}
		
		//세가지 경우에 대해 모두 안전하다면 return
		return true;
	}

}
