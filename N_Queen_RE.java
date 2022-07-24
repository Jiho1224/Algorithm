package al_07_04;

import java.io.*;

public class N_Queen_RE {
	
	// 1���� �迭�� �����Ѵ�.
	// index�� column(��)�� ��Ÿ���� �� index�� ���Ұ��� row�� ��Ÿ����.
	// �׷��� �ڵ������� ���� ���� �����ϴ����� üũ�� �� �ʿ䰡 ��������.
	// ��, �� index�� ���Ұ��� ��� �ٸ����� �������ָ� (���� �࿡ ���� �������� �ʵ���)
	// �߰������� �밢���� �������� �ʵ��� �������ָ� �ȴ�.
	
	public static int[] board; //�������� queen�� ���¸� �����ϴ� ����
	public static int N; // board�� size�� ���� ������ �����ϴ� ����
	public static int ans = 0; //���� �����ϴ� ���� + 0���� �ʱ�ȭ
	
	public static void main(String[] args) throws IOException {
		// INPUT
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		//1���� �迭 ����
		// N*N�� board�̹Ƿ� N ũ���� �迭�� �������ְ� �� index�� ���Ҵ� N-1(0���� �����ϹǷ�)
		// ������ ���� �� �ֵ��� �Ѵ�.
		
		board = new int[N];
		
		//N_Queen ȣ��
		//0��° ������ ���� ���������� ���ƺ��� ���� N_Queen(0)�� ȣ���Ѵ�.
		N_Queen(0);
		
		//��� ���
		System.out.println(ans);
	}
	
	public static void N_Queen(int r) {
		// ��� ���� ���� : ��� ���� ���� ���������� ������ ��!
		if(r == N) {
			ans++; // ����� �� + 1
			return;
		}
		
		// ���� ������ ���� �ϳ��� ���ƺ���.
		for(int i = 0; i < N;i++) {
			//���࿡ ���� ������ �� �����ϴٸ� (���ݹ��� �ʴ� ��ġ���) ���� ���� ���� ���� ����
			// N_Queen(���� ��) �� ȣ���Ѵ�.
			board[r] = i;
			if(is_attack(r) == false) N_Queen(r+1);
			
			//���� ���� ������ �� ������ ���ϴ� ��ġ���
			//�ٸ� �࿡ ���� ���ƺ���.
		}
	}
	
	//���࿡ ���� ������ ���Ѵٸ� true�� ��ȯ�ϰ�, ���� ������ ������ �ʴ´ٸ� false�� ��ȯ�Ѵ�.
	public static boolean is_attack(int r) {
		
		//���� ���������� �����Ƿ� �̹� ���� ����
		// 1. ���� �࿡ �����ϴ���    2. �밢���� �����ϴ��� 
		// �� Ȯ���غ��� �ȴ�.
		for(int i = 0; i<r;i++) {
			
			//���� �࿡ �����Ѵٸ� attack�� ���� �� �����Ƿ� true ��ȯ
			if(board[i] == board[r]) return true;
			
			// �밢���� �����ϴ� ��� :
			// (a,b)�� (c,d)��� �ξ��� �� |a-c| = |b-d| ��� (a,b)�� (c,d)�� �밢���� ��ġ
			// ��, ���� ���� ���� ���� ���ٸ� �밢���� ����!
			if(Math.abs(i-r) == Math.abs(board[i] - board[r])) return true;
		}
		return false;
	}
}
