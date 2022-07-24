package al_07_05;

import java.io.*;

public class Tree_2805 {
	
	public static long result = 0;
	public static long tree[];
	public static long N,M;
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		
		//Input ; 첫 번째 줄
		String str = br.readLine();
		String[] sp = str.split(" ");
		N = Long.parseLong(sp[0]); // 나무의 개수
		M = Long.parseLong(sp[1]); // 가져가려고 하는 나무 최소 M미터
		
		//Input ; 두 번째 줄
		str = br.readLine();
		sp = str.split(" ");
		
		tree = new long[(int)N]; //각각 나무의 길이를 저장하는 배열
		long max = 0;
		for(int i = 0; i<N;i++) {
			tree[i] = Long.parseLong(sp[i]);
			max = Math.max(max, tree[i]); // 가장 긴 나무의 길이를 구해서 저장
		}
		
		binary(0,max);
		
		bw.write(result+" ");
		bw.flush();

	}
	
	public static void binary(long start,long end) {
		long mid = (start+end)/2; //중간값을 절단기의 높이로 설정한다.
		long sum = 0;
		
		if(start > end) return; //만약 탐색하다가 start>end라면 탐색을 멈춘다.
		
		for(int i = 0; i<N;i++) {
			//만약 절단기 길이보다 tree의 길이가 길다면 
			//절단기에 의해 tree가 잘라지므로 result에 추가한다.
			if(tree[i]>mid) sum += (tree[i]-mid); 
		}
		
		//만약 합이 M보다 크다면 최소값을 찾기 위해 절단기의 하한을 높여본다
		if(sum >= M) {
			result = mid; //일단 저장
			if(sum == M) return;
			binary(mid+1,end);	
		}
		//만약 합이 M보다 작다면 조건 충족 X
		//절단기의 상한을 낮춘다.
		else {
			binary(start,mid-1);
		}
	}

}
