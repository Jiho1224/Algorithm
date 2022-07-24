package al_07_04;

import java.io.*;

public class N_Queen_RE {
	
	// 1차원 배열로 접근한다.
	// index는 column(열)을 나타내고 각 index의 원소값은 row를 나타낸다.
	// 그러면 자동적으로 같은 열에 존재하는지는 체크를 할 필요가 없어진다.
	// 즉, 각 index의 원소값이 모두 다르도록 조정해주면 (같은 행에 퀸이 존재하지 않도록)
	// 추가적으로 대각선에 존재하지 않도록 조정해주면 된다.
	
	public static int[] board; //놓아지는 queen의 상태를 저장하는 변수
	public static int N; // board의 size와 퀸의 개수를 결정하는 변수
	public static int ans = 0; //답을 저장하는 변수 + 0으로 초기화
	
	public static void main(String[] args) throws IOException {
		// INPUT
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		//1차원 배열 생성
		// N*N의 board이므로 N 크기의 배열을 생성해주고 각 index의 원소는 N-1(0부터 시작하므로)
		// 까지만 가질 수 있도록 한다.
		
		board = new int[N];
		
		//N_Queen 호출
		//0번째 열부터 퀸을 순차적으로 놓아보기 위해 N_Queen(0)을 호출한다.
		N_Queen(0);
		
		//결과 출력
		System.out.println(ans);
	}
	
	public static void N_Queen(int r) {
		// 재귀 종료 조건 : 모든 열에 퀸을 정상적으로 놓았을 때!
		if(r == N) {
			ans++; // 경우의 수 + 1
			return;
		}
		
		// 열의 끝까지 퀸을 하나씩 놓아본다.
		for(int i = 0; i < N;i++) {
			//만약에 퀸을 놓았을 때 안전하다면 (공격받지 않는 위치라면) 다음 열에 퀸을 놓기 위해
			// N_Queen(다음 열) 을 호출한다.
			board[r] = i;
			if(is_attack(r) == false) N_Queen(r+1);
			
			//만약 퀸을 놓았을 때 공격을 당하는 위치라면
			//다른 행에 퀸을 놓아본다.
		}
	}
	
	//만약에 퀸이 공격을 당한다면 true를 반환하고, 퀸이 공격을 당하지 않는다면 false를 반환한다.
	public static boolean is_attack(int r) {
		
		//열에 순차적으로 놓으므로 이미 놓은 퀸과
		// 1. 같은 행에 존재하는지    2. 대각선에 존재하는지 
		// 만 확인해보면 된다.
		for(int i = 0; i<r;i++) {
			
			//같은 행에 존재한다면 attack을 받을 수 있으므로 true 반환
			if(board[i] == board[r]) return true;
			
			// 대각선에 존재하는 경우 :
			// (a,b)와 (c,d)라고 두었을 때 |a-c| = |b-d| 라면 (a,b)와 (c,d)는 대각선에 위치
			// 즉, 행의 차와 열의 차가 같다면 대각선에 존재!
			if(Math.abs(i-r) == Math.abs(board[i] - board[r])) return true;
		}
		return false;
	}
}
