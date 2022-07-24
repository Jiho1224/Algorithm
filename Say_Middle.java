package al_07_06;

import java.io.*;
import java.util.*;

public class Say_Middle {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int num = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> min = new PriorityQueue<Integer>();
		PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
		
		
		int []ans = new int[num];
		for(int i = 0; i<num;i++) {
			int tmp = Integer.parseInt(br.readLine());
			
			if(i%2 == 0)max.add(tmp);
			else min.add(tmp);
			
			if(max.size() != 0 && min.size() != 0) {
				if(max.peek() > min.peek()) {
					int cmp = max.poll();
					max.add(min.poll());
					min.add(cmp);
				}
			}
			
			ans[i] = max.peek();
		}
		
		for(int i = 0; i<num;i++) {
			System.out.println(ans[i]);
		}
		
	}

}
