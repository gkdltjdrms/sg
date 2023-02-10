package constructor;

public class MemberDTO {
			//1 인분//DTO(Data Transfer Object)
			// 는 계층간 데이터 교환을 하기 위해 사용하는 객체로,
			// DTO는 로직을 가지지 않는 순수한 데이터 객체(getter & setter 만 가진 클래스)입니다.
		private String name; 
		private int age; 
		private String phone; 
		private String address;
		
		public MemberDTO(String name, int age, String phone, String address) {
			this.name = name;
			this.age = age;
			this.phone = phone;
			this.address = address;
		}
		
		public String getName() {
			return name;
		}
		public int getAge() {
			return age;
		}
		public String getPhone() {
			return phone;
		}
		public String getAddress() {
			return address;
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public void setAddress(String address) {
			this.address = address;
		}
	}


