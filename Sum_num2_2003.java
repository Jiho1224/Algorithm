package al_07_05;

import java.io.*;


public class Sum_num2_2003 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		//Input
		int N = Integer.parseInt(str.split(" ")[0]);
		long M = Integer.parseInt(str.split(" ")[1]);
		
		str = br.readLine(); // 다음 줄을 읽어준다.
		String[] array = str.split(" "); //배열을 담아준다.
		
		int start = 0; // start 포인터
		int end = 0; // end 포인터
		
		int[] arr = new int[N];
		
		for(int i = 0; i<N;i++) {
			arr[i] = Integer.parseInt(array[i]);
		}
		
		
		long sum = 0; // 합
		int result = 0; // 총 경우의 수
		
		while(start < N) { //start가 끝까지 훑을 때까지 

			if(sum >= M || end == N) { //만약 sum이 M보다 크거나 같으면
				sum -= arr[start]; //start 포인트가 앞으로 옮겨지므로 현재 point의 값은 수열에서 제외 --> sum에서 빼준다.
				start++;// start point를 앞으로 옮겨준다.
			}
			//만약 sum이 M보다 작다면
			else {
				sum += arr[end]; // end point를 더해준 후
				end++;// end point를 앞으로 옮겨준다.
			}
			
			//만약 합이 목표 합과 같다면 
			if (sum == M){
				result++; // 결과를 하나 더해준다.
				
			}
		}
		
		System.out.print(result);
		
	}

}


