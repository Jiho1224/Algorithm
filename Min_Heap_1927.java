package al_07_06;

import java.io.*;
import java.util.*;

public class Min_Heap_1927 {
	public static ArrayList<Integer> heap;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		int num = Integer.parseInt(str); // 연산의 개수 N
		heap = new ArrayList<>();
		ArrayList<Integer> ans = new ArrayList<>() ;
		heap.add(0); //0th 노드는 사용하지 않는다.
		int point; //heap에 담겨있는 수의 개수
		
		for(int i = 0; i<num;i++) {
			str = br.readLine();
			if(str.equals("")) str = br.readLine();
			int temp = Integer.parseInt(str); //입력되는 숫자를 읽는다.
			point = heap.size() -1; //heap에 있는 원소의 개수
			
			//만약에 temp가 0이라면 
			//heap이 비어있는 경우에는 0을 출력 아니라면 가장 작은 값 출력
			//heap의 root node를 출력 후 삭제
			if(temp == 0) {
				if(point <= 0) ans.add(0); //비어있다면 0을 출력
				else {
					//가장 작은 노드를 삭제 --> root node
					ans.add(heap.remove(1));
					
					//heap을 재정렬
					delete();
				}
			}
			//만약 0이 아닌 자연수가 나온다면 배열에 해당 수를 추가
			//min heap의 규칙에 맞게 (부모 노드의 key < 자식 노드의 key)
			else {
				//일단 배열의 맨 마지막에 추가
				heap.add(temp);
				
				//heap을 재정렬
				insert();
			}
		}
		
		for(int i = 0; i<ans.size();i++) {
			System.out.println(ans.get(i));
		}

	}
	
	//ith 원소와 jth 원소를 바꾸는 함수
	public static void swap(int i,int j) {
		// swap해준다.
		Collections.swap(heap, i, j);
	}
	
	public static void insert() {
		int position = heap.size()-1; //현재 위치
		int check;
		while(true) {
			check = position/2; //check 노드 = 부모노드
			if(position <= 1) break; //루트노드에 도달하였다면 반복문 종료
			
			//만약 새로 추가한 노드가 부모노드 보다 작다면 swap
			if(heap.get(check) > heap.get(position)) {
				swap(check,position); // heap의 원소를 바꾼다.
				position = check; //현재 위치를 바꿔준다.
			}
			//같거나 크다면 --> min heap 조건 충족 --> 반복문 종료
			else {
				break;
			}
		}
	}
	
	public static void delete() {
		if(heap.size() == 1) return; // 삭제 후 heap에 남아있는 수가 더이상 없다면 그냥 return
		heap.add(1,heap.get(heap.size()-1)); //맨마지막 노드를 루트자리로 삽입
		heap.remove(heap.size()-1); //맨마지막 노드 삭제
		
		int position = 1; //현재 위치는 root node
		while(true) {
			int left = position*2; //왼쪽 노드의 위치
			int right = position*2+1; //오른쪽 노드의 위치
			if(is_Leaf(position)) break; //leaf 노드에 도달하였다면 반복문 종료
			//왼쪽 노드부터 확인
			int cmp;
			
			//right node와 left node 중 더 작은 숫자와 비교
			if(right < heap.size()) {
				cmp = left;
			}
			else if(heap.get(left)>heap.get(right)) {
				cmp = right;
			}
			else {
				cmp = left;
			}
			
			if(heap.get(cmp)<heap.get(position) && heap.size()>=cmp) { //만약 원소가 left child보다 크다면
				swap(cmp,position); // swap
				position = cmp; //위치 변경
			}
			else {
				break;
			}
		}
	}
	
	//현재 위치가 leaf에 위치하는지 확인하는 함수
	public static boolean is_Leaf(int position) {
		//현재 위치가 heap의 size의 반보다 크다면 leaf 노드
		//물론 현재 위치는 전체 heap의 size보다 작아야한다.
		if(position >= (heap.size()/2) && position <= heap.size()) {
			return true;
		}
		return false;
	}

}
