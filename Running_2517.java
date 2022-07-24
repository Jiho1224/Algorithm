package al_07_06;

import java.io.*;
import java.util.*;

class Person{
	int idx; long skill;
	public Person(int idx,long skill){
		this.idx = idx;
		this.skill = skill;
	}
	
}
public class Running_2517 {
	public static ArrayList<Person> people;
	public static int[] tree;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//INPUT
		String str = br.readLine();
		int num = Integer.parseInt(str); // ������ ��
		
		people = new ArrayList<>();
		//�Ƿ� ���� + ��� ������ ���� ����
		for(int i = 0; i<num;i++) {
			str = br.readLine();
			people.add(new Person(i,Long.parseLong(str))); //idx�� skill�� ����
		}
		//skill ������ ���� ArrayList sorting + skill�� ������
		Collections.sort(people,new peopleComparator());
		
		for(int i = 0;i<num;i++) {	
			people.get(i).skill = i+1;
		}
		
		
		Collections.sort(people,new com_idx());
		
//		for(int i = 0;i<num;i++) {
//			System.out.println(people.get(i).idx +" " + people.get(i).skill);
//		}
		
		int tmpN;
		for(tmpN=1; tmpN<num; tmpN*=2);
		
		int []Run = new int[num]; //���� ����� ���� �迭.
		tree = new int[tmpN*2]; //index tree ����
		
		//�ʱ�ȭ
		for(int i = 0;i<2*tmpN;i++) {
			tree[i] = 0;
		}
		
		//people ����Ʈ���� �ϳ��� �������鼭 tree�� ����� ��
		// �������� ���ؼ� run�� �־��ش�.
		
		//���� ù��° ������ skill ����� 2���
		//�ι�° ������忡 +1�� ���ְ� 1������ �������� ���� �� index-���� ���� �ȴ�.
		for(int i = 0; i<num;i++) {
			//������ skill�� ������� ���� ���Ѵ�.
			int skill = (int) people.get(i).skill;
			int tmp_skill = (int) (skill+ tmpN -1);
			
			//���� ���� �ݿ��Ͽ� tree�� ������Ʈ
			update(tmp_skill,tmpN);
			//������ ���ϱ�
			Run[i] = (i+1) - sum(1,skill-1,tmpN); //(i+1)���� skill���� (skill-1)�� ������ ���ϱ�
		}
		
		for(int i = 0; i<num;i++) {
			bw.write(Run[i]+"\n");
		}
		br.close();
		bw.flush();
	}
	//idx ��° ���� +1 �Ѵ�.
	public static void update(int idx,int tmpN) {
		while(idx>=1) {
			tree[idx] += 1;
			idx /= 2;
		}
	}
	//i��°���� j��°���� �������� ���ϴ� �Լ�
	public static int sum(int i, int j,int tmpN) {
		
		//�� ��°�� �´� ������Ʈ ã��
		i = i + tmpN -1;
		j = j + tmpN -1;
		
		//�� ���
		int sum = 0;
		
		//�� ��ǥ�� ������ �� ���� �ݺ�
		while(i<=j) {
			//i�� right child���
			if(i % 2 == 1) {
				sum += tree[i]; //parent�� ������ �������� �����Ƿ� i�� ���� sum�� ����
			}
			//j�� left child���
			if(j % 2 == 0) {
				sum += tree[j];
			}
			i = (i+1)/2;
			j = (j-1)/2;
		}
		return sum;
	}

}

class peopleComparator implements Comparator<Person>{

	@Override
	public int compare(Person o1, Person o2) {
		if(o1.skill>o2.skill) return 1;
		else if(o1.skill < o2.skill) return -1;
		else return 0;
	}
	
}

class com_idx implements Comparator<Person>{

	@Override
	public int compare(Person o1, Person o2) {
		if(o1.idx>o2.idx) return 1;
		else if(o1.idx < o2.idx) return -1;
		else return 0;
	}
	
}
