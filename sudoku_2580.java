package al_07_04;

import java.io.*;
import java.util.*;

//��ǥ ������ ��� class Point
class Point{
	int x,y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class sudoku_2580 {
	
	public static int[][] board; // board ���¸� ����
	public static ArrayList<Point> zero; //��ĭ ��ǥ�� ��� ArrayList
	static BufferedWriter bw; //����� ���� BufferedWriter ����
	
	public static void main(String[] args) throws IOException {
		
		//INPUT
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str;
		String[] str_split;
		zero = new ArrayList<>();
		
		board = new int[9][9];
		
		//board ���� ����
		for(int i = 0; i<9;i++) {
			str = br.readLine();
			str_split = str.split(" ");
			for(int j = 0; j<9;j++) {
				board[i][j] = Integer.parseInt(str_split[j]);
				if(board[i][j] == 0) { //0�̶�� �ش� ��ǥ�� zero ����Ʈ�� �߰��Ѵ�.
					zero.add(new Point(i,j));
				}
			}	
		}
		
		//0�� ��ġ �ľ��ϰ�, ���� ����, ���簢�� �ľ�
		//0�� ���� �� �ִ� ���ڸ� �ľ��� �� �־�� ���� 0 Ž��. ���� ��ĭ�� 1~9���� ��� �ȵ���
		//��Ʈ��ŷ�� �Ͽ��� ���� 0�� �ٸ� ���� �־ ��.
		
		sudoku(0);
		
		br.close();
	}
	
	public static void sudoku(int idx) throws IOException{ //Ž���� x,y��ǥ�� index�� ���ڸ� ������� ������ �Է¹���
				
		//���࿡ ������ ������ ���´ٸ� �ش� ������ board�� ��� ������ְ�
		//�ڹ� ���� ����
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
		
		int tmpX = zero.get(idx).x; // ��ĭ�� x��ǥ
		int tmpY = zero.get(idx).y; // ��ĭ�� y��ǥ
		
		for(int i = 1; i<=9;i++) {
			
			//�����ϴٸ� (�������� ��Ģ�� �������� �ʴ´ٸ�)		
			if(is_ok(idx,i)) {
				board[tmpX][tmpY] = i; //board�� �ش� ���ڸ� �ְ�
				sudoku(idx+1); //���� zero �κ��� �ľ����ش�.
				board[tmpX][tmpY] = 0; //backtrack�Ѵ�. (�ٽ� ��ĭ���� �ʱ�ȭ)
			}
		}
		
		
	}
	
	//��ȿ�� �˻� �Լ�
	public static boolean is_ok(int idx,int num) {
		int tmpX = zero.get(idx).x;
		int tmpY = zero.get(idx).y;
		
		
		for(int i = 0;i<9;i++) {
			//���� �࿡ ���� ���ڰ� �����ϴ��� �˻�
			if(board[tmpX][i] == num) return false;
			//���� ���� ���� ���ڰ� �����ϴ��� �˻�
			if(board[i][tmpY] == num) return false;
		}

		
		//3*3 �ڽ� �ȿ� ���� ���ڰ� �����ϴ��� �˻�
		int row = (tmpX/3)*3;
		int col = (tmpY/3)*3;
		
		for(int i = row;i<row+3;i++) {
			for(int j = col; j<col+3; j++) {
				if(board[i][j] == num) return false;
			}
		}
		
		//������ ��쿡 ���� ��� �����ϴٸ� return
		return true;
	}

}
