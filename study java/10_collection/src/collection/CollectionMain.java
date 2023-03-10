package collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CollectionMain  {
	
	@SuppressWarnings("all")
	public static void main(String[] args) {
		Collection coll = new ArrayList();
		coll.add("호랑이");
		coll.add("사자");
		coll.add("호랑이"); //중복 허용
		coll.add(25);
		coll.add(43.8);
		coll.add("기린");
		coll.add("코끼리");
		
		System.out.println(coll);
		Iterator it = coll.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}//while 
		
		//	it.hasNext() - 항목이 있냐? (true), 없으면 (false)	
		//	it.next() -> 항목을 꺼내고 다음 항목으로 이동
		
	}

}
