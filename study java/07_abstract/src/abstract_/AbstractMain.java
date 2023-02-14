package abstract_;

public class AbstractMain extends AbstractTest {
	
	@Override
	public void setName(String name) {
		this.name = name;
	}
	public static void main(String[] args) {
		//추상클레스는 직접적으로(자신의 클래스로) 메모리에 생성(new)할수 없다
		AbstractTest at = new AbstractMain(); //at -> AbstractTest
		at.setName("홍길동");
		System.out.println(at.getName());

	}

}
