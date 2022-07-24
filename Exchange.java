package al_07_04;

import java.io.*;
import java.util.*;

class Pair{
	int num,cnt;
	
	//Pair 클래스 생성
	// num = 교환한 후의 숫자 & cnt = 총 교환한 횟수
	public Pair(int num, int cnt) {
		this.num = num;
		this.cnt = cnt;
	}
}

public class Exchange {
	public static int M;
	public static int K;
	public static int len;
	public static boolean[][] visit;
	public static int result = -1;  // max값 저장 
	
	public static void main(String[] args) throws IOException {
		
		//INPUT
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		String[] str_split = str.split(" ");
		
		len = str_split[0].length(); // 자릿수
		M = Integer.parseInt(str_split[0]); // 입력받은 정수 저장
		K = Integer.parseInt(str_split[1]); // 입력받은 교환 횟수 저장 
		
		visit = new boolean[1000001][K+1];
		
		//bfs 실행
		bfs(); 
		
		System.out.print(result);
	}
	
	// idea: 가장 큰 숫자를 만드는 logic을 생각하기 보다
	// K번 교환한 모든 수 중 가장 최댓값을 구한다. 
	
	public static void bfs() {
		// Queue 생성
		Queue<Pair> queue = new LinkedList<>();
		
		//교환을 하나도 하지 않은 초기의 숫자인 M을 queue에 넣어준다.
		//따라서 num = M이고 cnt = 0인 Pair를 queue에 넣는다.
		queue.add(new Pair(M,0));
		visit[M][0] = true; //교환 숫자 : M & 교환 횟수 :0 은 방문 완료!!
		
		//queue가 모두 빌 때 까지.. 즉, 방문이 끝날때까지 반복
		while(!queue.isEmpty()) {
			Pair tmp = queue.poll(); // queue에서 하나를 뺀다.
			
			
			//만약 교환횟수를 충족하였다면
			if(tmp.cnt == K) {
				result = Math.max(result, tmp.num); // 해당 숫자가 기존 result보다 크다면 max값이므로 result 갱신
				continue; //더 큰 값이 나올 수도 있으므로.
			}
			
			for(int i = 0; i<len-1;i++) {
				for(int j = i+1; j<len;j++) {
					int tmp_num = change(tmp.num,i,j); //ith와 jth 원소를 교환한 수
					
					// 첫번째 자리가 0이 나와서 교환이 불가한 상황이거나
					// 이미 해당 교환을 한 적이 있는 경우에는 아무것도 하지 않는다.
					if(tmp_num == -1 || visit[tmp_num][tmp.cnt+1] == true) {
						
					}
					else {
						queue.add(new Pair(tmp_num,tmp.cnt+1)); //queue에 추가한다.
						visit[tmp_num][tmp.cnt+1] = true; //해당 수를 방문하였음을 표시
						
					}
					
				}
			}
		}
		
	}
	
	public static int change(int num,int i, int j) {
		char[] array = String.valueOf(num).toCharArray(); //숫자를 문자 배열로 63421 -> [6,3,4,2,1]
		
		//배열 swap
		char temp = array[j];
		array[j] = array[i];
		array[i] = temp;
		
		if(array[0] == '0') return -1; //만약 첫번째 수가 0이라면 -1 return --> false
		
		
		return Integer.parseInt(new String(array));
	}
	
}
