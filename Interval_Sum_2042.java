package al_07_06;

import java.io.*;

public class Interval_Sum_2042 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//INPUT
		String str = br.readLine();
		String[] split = str.split(" ");
		
		int N = Integer.parseInt(split[0]); // 수의 개수
		int M = Integer.parseInt(split[1]); // 변경이 일어나는 횟수
		int K = Integer.parseInt(split[2]); // 구간의 합을 구하는 횟수
		
		int lp = M+K; //변경 OR 구간의 합 구하는 정보를 입력받을 횟수
		
		int tempN = N;
		int cnt = 0; //N이 2의 몇 승인지 알기 위해
		
		//N이 2의 몇 승인지 판단
		//만약 2^k로 나누어떨어지지 않으면 나머지는 0으로 채운다.
		while(true) {
			if(tempN == 1) break;
			if(tempN % 2 == 0) {
				cnt++;
				tempN /= 2;
			}
			else tempN += 1;
		}
				
		
		int temp_Num = (int) Math.pow(2, cnt);
		long [] tree = new long[2*temp_Num]; // tree 생성
		tree[0] = 0; //0th는 사용X
		
		//tree에 숫자 채워넣기
		
		//원래의 배열은 leaf node에 넣기
		for(int i = 0 ; i < temp_Num;i++) {
			if (i<N) tree[temp_Num+i] = Long.parseLong(br.readLine()); //leaf node에 저장
			else tree[i+temp_Num] = 0;
		}
		
		//parent node를 순차적으로 채워넣기
		for(int i = temp_Num-1; i > 0;i--) {
			
			tree[i] = tree[i*2]+tree[i*2+1]; //left child와 right child를 더해서 저장
		}
		
		for(int i = 0;i<lp;i++) {
			str= br.readLine();
			split = str.split(" ");
			int a = Integer.parseInt(split[0]);
			int b = Integer.parseInt(split[1]);
			long c = Long.parseLong(split[2]);
			
			// a==1이면 값 변경
			if(a == 1) {
				long tmp = tree[temp_Num-1+b];// 해당 값 임시 저장
				int point  = temp_Num-1+(int)b;
				while(point >= 1) {
					tree[point] = tree[point] - tmp + c;
					point /= 2; //parent도 모두 바꿔주기
				}
			}
			//a == 2이면 b~c 구간의 합 구하기
			else {
				b = temp_Num-1+b;
				int d = temp_Num-1+(int)c;
				// b~d까지 구간합 구하기
				long sum = 0;
				//b와 d가 엇갈리면 loop를 종료!
				while(b<=d) {
					
					//left child의 index는 짝수, right child의 index는 홀수임을 이용한다.
					
					//b가 right child라면 parent가 구간을 포함하지 않는 것이므로
					//sum에 b번째의 값을 더해준다.
					if(b%2 == 1) {
						sum += tree[b];
						//옆의 부모로 올라간다.
						b = (b+1)/2;
					}
					
					//left child라면 자신의 parent로 올라간다.
					else {
						b = b/2;
					}
					
					//d가 left child라면 parent가 구간을 포함하지 않는 것므로
					//sum에 d번째의 값을 더해준다.
					if(d%2 == 0) {
						sum+= tree[d];
						d = (d-1)/2; //자신의 왼쪽 옆의 부모로 올라간다.
					}
					else {
						d = d/2; //자신의 부모로 올라간다.
					}
										
				}
				//sum을 모두 구하고 출력
				bw.write(sum+"\n");
			}
		}

		//종료
		bw.flush();
	}

}
