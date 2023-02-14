package interface_;

public class ComputeMain {

	public static void main(String[] args) {
		ComputeService computerservice = new ComputeService();
		computerservice.menu();
		System.out.println("프로그램 종료합니다.");
	}

}
