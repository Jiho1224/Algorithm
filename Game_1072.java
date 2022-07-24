package al_07_05;

import java.io.*;

public class Game_1072 {

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		String[] split_line = str.split(" ");
		
		long X = Long.parseLong(split_line[0]);
		long Y = Long.parseLong(split_line[1]);
		
		long start = 0;
		long end = X;
		
		long Z = (Y*100)/X;
		long temp = 0;;
		long mid = 0;
		
		if(Z >= 99) {
			System.out.print(-1);
		}
		
		else {
			while(true) {
				if(start > end) break;
				mid = (start+end)/2;
				temp = ((Y+mid)*100)/(X+mid);
				
				//Z가 변하지 않을 경우 --> 하한선을 올린다. (더 많은 게임을 한다)
				if(temp > Z) {				
					end = mid-1;
				}
				//z가 변할 경우 --> 최적의 해를 찾기 위해 상한선을 줄인다.
				else {
					start = mid+1;
				}
				
			}
			
			System.out.print(start);
			
		}
		
	}

}
