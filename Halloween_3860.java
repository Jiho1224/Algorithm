package al_07_13;

import java.io.*;
import java.util.*;

class data{
	int node;
	int cost;
	
	public Integer getNode() {
		return node;
	}
	public Integer getCost() {
		return cost;
	}
	public data(int node,int cost) {
		this.node = node;
		this.cost = cost;
	}
}

public class Halloween_3860 {
	
	public static int INF = Integer.MAX_VALUE;
	public static int []dx = {-1,0,1,0};
	public static int []dy = {0,-1,0,1};
	public static int W,H;
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str;
		String[] sp;
		
		while(true) {
			str = br.readLine();
			sp = str.split(" ");
			
			W = Integer.parseInt(sp[0]); //������ �ʺ�
			H = Integer.parseInt(sp[1]); //������ ����
			
			if(W == 0 && H == 0) break; // 0 0�� �ԷµǸ� ����
			
			//�ִܰŸ� ���� �ʱ�ȭ
			long visit[] = new long[W*H+1];
			for(int i = 0; i<=W*H;i++) {
				visit[i] = INF;
			}
			
			visit[0] = 0;
			visit[1] = 0;
			
			//������ ���� ����
			
			//Idea
			
			//������ ���� (x,y)�� �� �� �ִ� ���·� �����Ѵ�.
			//�ʱ�ȭ �� (x,y)�� ���������� (x-1,y)(x+1,y)(x,y-1)(x,y+1)�� 
			//���� ����Ʈ�� ������ �߰����ش�. �� ����ġ�� 1�� ��´�.
			//�� �̶�, ������ ��ġ pair�� � �׷����͵� ���� �ʴ´�.
			
			//������ ��ġ�� ������ 
			//�ش� ����� ���õ� ������ ���´�.
			
			//�ͽű����� ��ġ�� ������
			//�� �ͽű��� ��ġ�� ���� ��ġ�� ���� ���� ���ش�.
			
			
			
			
			//���� ���¸� ����� �ش�.
			ArrayList<ArrayList<data>> cemetry = new ArrayList<>();
			
			for(int i = 0; i<= W*H;i++) {
				cemetry.add(new ArrayList<data>());
			}
			
			//�� ����� �������� ���� ������ �̾��ش�.
			for(int i = 0; i<W;i++) {
				for(int j = 0; j<H;j++) {
					int tmp  = cToN(i,j); //���� ����� ���� ��ȣ
					//�������� Ž��
					for(int k = 0; k<4;k++) {
						
						//���������� x��ǥ nx, y��ǥ ny
						int nx = dx[k] + i;
						int ny = dy[k] + j;
						
						if(nx<0||ny<0||nx>=W||ny>=H) continue;
						
						//�⺻������ cost�� 1�̴�.
						cemetry.get(tmp).add(new data(cToN(nx,ny),1)); //���� �߰�
					}
				}
			}
			cemetry.get(W*H).clear(); //�������� ������ ��������.
			int G = Integer.parseInt(br.readLine()); //������ ����
			
			//������ ��ġ

			for(int i = 0; i<G;i++) {
				str = br.readLine();
				sp = str.split(" ");
				
				int a = Integer.parseInt(sp[0]);
				int b = Integer.parseInt(sp[1]);
				
				//������ ��ġ �������� ���� ������ ���´�.
				int tmp = cToN(a,b);
				cemetry.get(tmp).clear();
				
				//�������� Ž��
				for(int k = 0; k<4;k++) {
					
					//���������� x��ǥ nx, y��ǥ ny
					int nx = dx[k] + a;
					int ny = dy[k] + b;
					
					if(nx<0||ny<0||nx>=W||ny>=H) continue;
					
					//tmp�� ���������� ��带 ������ �ִ� ������ ��� ����
					cemetry.get(cToN(nx,ny)).removeIf((item)->item.getNode() == tmp);
				}
			}
			
			
			
			int E = Integer.parseInt(br.readLine()); // �ͽ� ������ ��
			
			//�ͽ� ������ ����
			
			// X1 Y1 X2 Y2 T
			//�ͽ� ������ ��ġ X1,Y1
			//�ͽ� ���� ���������� ��ġ X2,Y2
			//�����µ� �ɸ��� �ð� T
			for(int i = 0; i<E;i++) {
				str = br.readLine();
				sp = str.split(" ");
				
				//�ͽ� ������ ��ġ
				int X1 = Integer.parseInt(sp[0]);
				int Y1 = Integer.parseInt(sp[1]);
				int start = cToN(X1,Y1); //�ͽ� ���� ��ġ�� ��� ��ȣ
				
				// �ͽ� ���� ���������� ��ġ
				int X2 = Integer.parseInt(sp[2]);
				int Y2 = Integer.parseInt(sp[3]);
				int end = cToN(X2,Y2); //�ͽ� ���� ���� ��ġ�� ��� ��ȣ
				
				//�ɸ��� �ð� == cost
				int T = Integer.parseInt(sp[4]);
				
				//�ͽű��� ���� ����
				//�ͽ� ���ۿ� ���� ������������ �̵����� ���ϰ� �ͽű��� ���������� ��������
				//�������;� �Ѵ�.
				
				//���� �ͽű��� start ��ġ���� �������Ͽ� ����Ǿ��ִ� ������ ����
				cemetry.get(start).clear();
				
				
				//�ͽ� ���� ���� ��ġ�� �� cost�� �ٽ� ����
				cemetry.get(start).add(new data(end,T));
				
				
			}
			
			//edge�� ���� ������ �迭 �������� ����
			int total = 0; //������ ����
			for(int i = 1; i<=W*H;i++) {
				total += cemetry.get(i).size();
			}
			
			int edge[][] = new int[total][3];
			int cnt = 0;
			for(int i = 1; i<=W*H;i++) {
				for(int j = 0; j<cemetry.get(i).size();j++) {
					edge[cnt][0] = i; //start ���
					edge[cnt][1] = cemetry.get(i).get(j).node; //end ���
					edge[cnt++][2] = cemetry.get(i).get(j).cost; //cost
				}
			}
			
			//������ ����ġ ���� && ���� ����Ŭ ���� && �ִ� �Ÿ� => ���� ���� �˰��� ���!
			for(int i = 1; i<=W*H;i++) { //������ ������ŭ ���ƺ���.
				for(int j = 0; j<total;j++) { //������ �ϳ��� ���ƺ���.
					if(visit[edge[j][0]] < INF && visit[edge[j][1]] > visit[edge[j][0]]+edge[j][2]) {
						visit[edge[j][1]] = visit[edge[j][0]]+edge[j][2];
					}
				}
			}
			
			//���� ����Ŭ�� �����ϴ��� üũ
			boolean isNegativeCycle = false;
			for(int j = 0; j<total;j++) { //������ �ϳ��� ���ƺ���.
				if(visit[edge[j][0]] < INF && visit[edge[j][1]] > visit[edge[j][0]]+edge[j][2]) {
					isNegativeCycle = true;
					break;
				}
			}
			
			//���
			//1.���� ����Ŭ�� �����Ѵٸ�
			if(isNegativeCycle) {
				bw.write("Never\n");
			}
			//2. (W-1,H-1) �ִܰŸ��� INF���
			else if(visit[W*H] == INF) {
				bw.write("Impossible\n");
			}
			//3. ���� ���� == �ִܰŸ��� �����Ѵٸ�
			else {
				bw.write(visit[W*H]+"\n");
			}
			
		}
		
		bw.flush();
	}
	
	//x,y ��ǥ�� �ϳ��� ���ڷ� ��Ÿ���� �Լ�
	public static int cToN(int x, int y) {
		return H*x+(y+1);
	}

}
