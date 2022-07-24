package al_07_13;

import java.io.*;


public class Time_Machine_11657 {
	public static int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine();
		String[] sp = str.split(" ");
		
		int N = Integer.parseInt(sp[0]); // ������ ����
		int M = Integer.parseInt(sp[1]); // ���� �뼱�� ����
		
		//���� ����Ʈ ����
		int edge[][] = new int[M+1][3]; //[0] ��� [1] ���� [2] cost
		
		//���� ���� �ޱ�
		for(int i = 0; i<M; i++) {
			str = br.readLine();
			sp = str.split(" ");
			
			// a b c <=> a->b (cost: c)
			int a = Integer.parseInt(sp[0]);
			int b = Integer.parseInt(sp[1]);
			int c = Integer.parseInt(sp[2]);
			
			edge[i][0] = a; //���
			edge[i][1] = b; //����
			edge[i][2] = c; //���
		}
		//���ǥ �ʱ�ȭ
		long visit[] = new long[N+1];
		//initializing
		for(int i = 1; i<=N;i++) visit[i] = INF;
		
		visit[1] = 0; //1�����ú��� ����̹Ƿ� �ʱ�ȭ
		//�������� �˰���
		//N�� �ݺ�
		for(int i = 1; i<= N; i++) {
			for(int j = 0; j<M; j++) { //���� ����Ʈ�� �� ���� �ѷ�����
				//���� �ش� ������ ������� �� �� �ְ�
				//���� �ش� ������ �������� ���� �����
				//���� �ش� ������ ������� ���� ��� + ���� �ش� ���� TO ������ ���
				//���� ũ�ٸ� ���� ������ �������ش�.
				if(visit[edge[j][0]] <INF && visit[edge[j][1]] > visit[edge[j][0]] + edge[j][2]) {
					visit[edge[j][1]] = visit[edge[j][0]] + edge[j][2];
				}
			}
		}
		
		//���� ����Ŭ�� �ִ��� Ȯ���Ѵ�.
		boolean isNegativeCycle = false; //���� ������ �ʱ�ȭ
		
		//�� �� �� �ѷ�����
		//���� ���� ���ŵȴٸ� ���� ����Ŭ�� �ִ� ��!
		for(int j = 0; j<M; j++) { //���� ����Ʈ�� �� ���� �ѷ�����

			if(visit[edge[j][0]] <INF && visit[edge[j][1]] > visit[edge[j][0]] + edge[j][2]) {
				isNegativeCycle = true;
				break;
			}
		}
		
		//���
		
		if(isNegativeCycle) bw.write("-1");
		else {
			for(int i = 2; i<=N ;i ++) {
				//���� ��ΰ� ���ٸ� -1 ���
				if(visit[i] == INF) bw.write("-1\n");
				//���� ��ΰ� �ִٸ� �ּҺ�� ���
				else bw.write(visit[i]+"\n");
			}
		}
		bw.flush();
	}

}
