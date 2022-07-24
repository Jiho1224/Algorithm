package al_07_05;

import java.io.*;
public class Cutting_Tree_2805 {
	public static long[] tree;
	public static long M;
	public static long sum = 0;
	public static long result = 0;
	
	public static void main(String[] args) throws IOException{
		
		//INPUT
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		if(str.equals("")) str= br.readLine();
		int N = Integer.parseInt(str.split(" ")[0]);
		M = Integer.parseInt(str.split(" ")[1]);
		
		tree = new long[N];
		long max = -1;
		
		str = br.readLine();
		if(str.equals("")) str= br.readLine();
		
		String[] temp_split = str.split(" ");
		
		for(int i = 0 ; i <N;i++) {
			tree[i] = Integer.parseInt(temp_split[i]);
			max = Math.max(tree[i], max);
		}
		
		cutting(0,max);
		System.out.println(result);
	}
	
	//적어도 M미터를 가져가기위한 절단기 길이의 최대를 구하는 것이다.
	//절단기 길이가 길어질 수록 가져갈 수 있는 나무의 길이는 작아질 것이다.
	//따라서 M을 기준으로 가져갈 수 있는 나무의 길이 (sum)가 크다면 절단기를 더 길게 조정하고
	// M을 기준으로 가져갈 수 있는 나무의 길이가 작다면 절단기의 길이를 더 짧게 조정해준다.
	
	public static void cutting(long start,long end) {
		long mid = (start+end) / 2; //절단기의 길이
		sum = 0;
		if(start > end) return; //재귀함수 종료 조건 ; 모두 탐색을 마쳤을 때
		
		//가져갈 수 있는 나무의 길이 계산
		for(int i = 0; i<tree.length ;i++) {
			//나무의 길이가 절단기의 길이보다 길다면 나무가 잘리므로 가져갈 수 있다.
			if(tree[i] > mid) sum += (tree[i]- mid);
		}
		
		// 기준 길이 M <= 가져갈 수 있는 길이 
		// 절단기를 더 높게 조정한다. --> 하한을 올린다.
		if(sum >= M) {
			result = mid; // result에 일단 최대 길이로 저장한다.
			if(sum == M) return;
			cutting(mid+1, end); //더 적합한 선택지가 있는지 확인한다
		}
		//기준 길이 M > 가져갈 수 있는 길이 --> 조건에 맞지 않으므로 result에는 저장 X
		else{
			cutting(start,mid-1);
		}
		
	}
	
}
