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
		int num = Integer.parseInt(str); // 선수의 수
		
		people = new ArrayList<>();
		//실력 저장 + 계수 정렬을 위한 세팅
		for(int i = 0; i<num;i++) {
			str = br.readLine();
			people.add(new Person(i,Long.parseLong(str))); //idx와 skill을 저장
		}
		//skill 순서에 따라 ArrayList sorting + skill값 재정의
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
		
		int []Run = new int[num]; //최종 등수를 담을 배열.
		tree = new int[tmpN*2]; //index tree 생성
		
		//초기화
		for(int i = 0;i<2*tmpN;i++) {
			tree[i] = 0;
		}
		
		//people 리스트에서 하나씩 꺼내가면서 tree에 담아준 후
		// 구간합을 구해서 run에 넣어준다.
		
		//만약 첫번째 주자의 skill 등수가 2라면
		//두번째 리프노드에 +1을 해주고 1까지의 구간합을 구한 후 index-합이 답이 된다.
		for(int i = 0; i<num;i++) {
			//주자의 skill의 리프노드 값을 구한다.
			int skill = (int) people.get(i).skill;
			int tmp_skill = (int) (skill+ tmpN -1);
			
			//더한 값을 반영하여 tree를 업데이트
			update(tmp_skill,tmpN);
			//구간합 구하기
			Run[i] = (i+1) - sum(1,skill-1,tmpN); //(i+1)부터 skill이전 (skill-1)의 구간합 구하기
		}
		
		for(int i = 0; i<num;i++) {
			bw.write(Run[i]+"\n");
		}
		br.close();
		bw.flush();
	}
	//idx 번째 값을 +1 한다.
	public static void update(int idx,int tmpN) {
		while(idx>=1) {
			tree[idx] += 1;
			idx /= 2;
		}
	}
	//i번째부터 j번째까지 구간합을 구하는 함수
	public static int sum(int i, int j,int tmpN) {
		
		//각 번째에 맞는 리프노트 찾기
		i = i + tmpN -1;
		j = j + tmpN -1;
		
		//합 담기
		int sum = 0;
		
		//두 좌표가 엇갈릴 때 까지 반복
		while(i<=j) {
			//i가 right child라면
			if(i % 2 == 1) {
				sum += tree[i]; //parent는 구간을 포함하지 않으므로 i의 값을 sum에 더함
			}
			//j가 left child라면
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
