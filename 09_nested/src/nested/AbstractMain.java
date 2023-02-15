package nested;

public class AbstractMain {

	public static void main(String[] args) {
		AbstractTest at = new AbstractTest() {//익명 inner 클래스
			
			public void setName(String name) {//구현
				this.name = name;
			}
		};
		
		InterA in = new InterA() {
			public void aa() {}
			public void bb() {}
			
		};
		
		AbstractExam ae = new AbstractExam() {
			//추상메소드가 없기 때문에원하는 메소드를 골라서 Override를 한다.
		};
	}

}
