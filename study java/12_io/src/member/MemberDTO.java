package member;

import java.io.Serializable;

public class MemberDTO implements Comparable<MemberDTO>, Serializable {
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

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String toString() {
		return name+"\t"+age+"\t"+phone+"\t"+address;
	}

	@Override
	public int compareTo(MemberDTO m) {
		// TODO Auto-generated method stub
		return name.compareTo(m.getName()); //내림차순일경우 * -1
	}
    
}