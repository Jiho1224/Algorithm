package al_07_06;

import java.io.*;
import java.util.*;

public class Min_Heap_1927 {
	public static ArrayList<Integer> heap;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		int num = Integer.parseInt(str); // ������ ���� N
		heap = new ArrayList<>();
		ArrayList<Integer> ans = new ArrayList<>() ;
		heap.add(0); //0th ���� ������� �ʴ´�.
		int point; //heap�� ����ִ� ���� ����
		
		for(int i = 0; i<num;i++) {
			str = br.readLine();
			if(str.equals("")) str = br.readLine();
			int temp = Integer.parseInt(str); //�ԷµǴ� ���ڸ� �д´�.
			point = heap.size() -1; //heap�� �ִ� ������ ����
			
			//���࿡ temp�� 0�̶�� 
			//heap�� ����ִ� ��쿡�� 0�� ��� �ƴ϶�� ���� ���� �� ���
			//heap�� root node�� ��� �� ����
			if(temp == 0) {
				if(point <= 0) ans.add(0); //����ִٸ� 0�� ���
				else {
					//���� ���� ��带 ���� --> root node
					ans.add(heap.remove(1));
					
					//heap�� ������
					delete();
				}
			}
			//���� 0�� �ƴ� �ڿ����� ���´ٸ� �迭�� �ش� ���� �߰�
			//min heap�� ��Ģ�� �°� (�θ� ����� key < �ڽ� ����� key)
			else {
				//�ϴ� �迭�� �� �������� �߰�
				heap.add(temp);
				
				//heap�� ������
				insert();
			}
		}
		
		for(int i = 0; i<ans.size();i++) {
			System.out.println(ans.get(i));
		}

	}
	
	//ith ���ҿ� jth ���Ҹ� �ٲٴ� �Լ�
	public static void swap(int i,int j) {
		// swap���ش�.
		Collections.swap(heap, i, j);
	}
	
	public static void insert() {
		int position = heap.size()-1; //���� ��ġ
		int check;
		while(true) {
			check = position/2; //check ��� = �θ���
			if(position <= 1) break; //��Ʈ��忡 �����Ͽ��ٸ� �ݺ��� ����
			
			//���� ���� �߰��� ��尡 �θ��� ���� �۴ٸ� swap
			if(heap.get(check) > heap.get(position)) {
				swap(check,position); // heap�� ���Ҹ� �ٲ۴�.
				position = check; //���� ��ġ�� �ٲ��ش�.
			}
			//���ų� ũ�ٸ� --> min heap ���� ���� --> �ݺ��� ����
			else {
				break;
			}
		}
	}
	
	public static void delete() {
		if(heap.size() == 1) return; // ���� �� heap�� �����ִ� ���� ���̻� ���ٸ� �׳� return
		heap.add(1,heap.get(heap.size()-1)); //�Ǹ����� ��带 ��Ʈ�ڸ��� ����
		heap.remove(heap.size()-1); //�Ǹ����� ��� ����
		
		int position = 1; //���� ��ġ�� root node
		while(true) {
			int left = position*2; //���� ����� ��ġ
			int right = position*2+1; //������ ����� ��ġ
			if(is_Leaf(position)) break; //leaf ��忡 �����Ͽ��ٸ� �ݺ��� ����
			//���� ������ Ȯ��
			int cmp;
			
			//right node�� left node �� �� ���� ���ڿ� ��
			if(right < heap.size()) {
				cmp = left;
			}
			else if(heap.get(left)>heap.get(right)) {
				cmp = right;
			}
			else {
				cmp = left;
			}
			
			if(heap.get(cmp)<heap.get(position) && heap.size()>=cmp) { //���� ���Ұ� left child���� ũ�ٸ�
				swap(cmp,position); // swap
				position = cmp; //��ġ ����
			}
			else {
				break;
			}
		}
	}
	
	//���� ��ġ�� leaf�� ��ġ�ϴ��� Ȯ���ϴ� �Լ�
	public static boolean is_Leaf(int position) {
		//���� ��ġ�� heap�� size�� �ݺ��� ũ�ٸ� leaf ���
		//���� ���� ��ġ�� ��ü heap�� size���� �۾ƾ��Ѵ�.
		if(position >= (heap.size()/2) && position <= heap.size()) {
			return true;
		}
		return false;
	}

}
