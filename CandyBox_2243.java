package al_07_06;

import java.io.*;

public class CandyBox_2243 {
	
	public static long[] tree;
	public static int tmpN;
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		
		//1부터 1,000,000까지 + 999999 + 1
		for(tmpN=1; tmpN<1000000; tmpN*=2);
		
		tree = new long[2*tmpN];
		
		for(int i = 0; i<n;i++) {
			String str = br.readLine();
			String[] sp = str.split(" ");
			
			//1이 입력되었을 경우
			if(sp.length == 2) {
				int B = Integer.parseInt(sp[1]); //꺼낼 사탕의 순위
				int result = find(B);
				bw.write(result+"\n");
				update(result,-1);
			}
			
			//2가 입력되었을 경우
			else {
				int B = Integer.parseInt(sp[1]); //사탕의 순위
				int C = Integer.parseInt(sp[2]); //넣거나 꺼내는 개수
				
				// B 순위 사탕을 리프노드에 넣고 업데이트
				update(B,C);
			}
		}
		
		bw.flush();

	}
	
	// [] level의 사탕의 개수를 n만큼 조정
	public static void update(int level,long n) {
		level = tmpN + level - 1; //리프노드화
		//부모로 거슬러 올라가면서 순차적으로 업데이트
		while(level >= 1) {
			tree[level] += n;
			level /= 2;
		}
	}
	
	public static int find(int num) {
		int index = 1;
		while(index < tmpN*2) {
			if(tree[index] < num) num -= tree[index++];			
			index = index*2;
		}
		index /= 2;
		num = index - tmpN + 1;
		return num;
	}

}
