package al_07_13;

import java.io.*;


public class Floyd_11404 {
	public static int INF = Integer.MAX_VALUE;
	public static long[][] bus;
	public static int n;
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine();
		String []sp;
		
		n = Integer.parseInt(str); //������ ����
		int m = Integer.parseInt(br.readLine()); //������ ����
		
		//bus �׷��� : �������
		//�ʱ�ȭ bus[a][b] = c�� a->b �ִ� ��� c�̴�.
		//ó�� ����� ��� INF�� ��´�.
		
		bus = new long[n+1][n+1];
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if (i == j) bus[i][j] = 0; //�ڱ� �ڽſ��� ���� cost�� 0
				else bus[i][j] = INF;
			}
		}
		
		//������ ������ŭ ���� ������ �޴´�.
		for(int i = 0; i<m;i++) {
			str = br.readLine();
			sp = str.split(" ");
			//�Է� : a b c
			//�ǹ� : a->b ���� (cost: c)
			int a = Integer.parseInt(sp[0]);
			int b = Integer.parseInt(sp[1]);
			int c = Integer.parseInt(sp[2]);
			
			//�Է¹��� ������ ������� ����
			if(bus[a][b] == INF) bus[a][b] = c; //���� ���� �߰��� �뼱�̸� �׳� �ִ´�.
			else bus[a][b] = c > bus[a][b]? bus[a][b]:c; //�̹� �ִ� �뼱�̸� �� ���� ������ ����
		}
		
		//�÷��̵� �Լ� -> �÷��̵� ���� �˰����� �̿��Ͽ�
		//��� �������� �ּ� ����� ����� �� �ֵ��� bus graph ����
		Floyd();
		
		//��� ���
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				//�� �� ���� ���
				if(bus[i][j] == INF) bw.write("0 ");
				else bw.write(bus[i][j]+" ");		
			}
			bw.write("\n");
		}
		
		bw.flush();
	}
	
	//���� i���� j�� ���µ� �ʿ��� �ּҺ���� ����ϴ� �Լ�
	//�÷��̵� ���� �˰��� �̿�
	public static void Floyd() {
		for(int k = 1; k<=n; k++) { //������
			for(int i = 1; i<=n ; i++) { //�����
				for(int j = 1; j<=n; j++) { //������
					if(bus[i][j] > bus[i][k]+bus[k][j])
						bus[i][j] = bus[i][k]+bus[k][j];
				}
			}
		}
		
	}

}
