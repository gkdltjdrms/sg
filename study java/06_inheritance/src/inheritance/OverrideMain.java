package inheritance;

class AA{
	public int a = 3;
	public void disp() {
		a+= 5;
		System.out.println("AA : "+a+" ");
	}
}
//------------------------
class BB extends AA{
	public int a = 8;
	public void disp() {
		a+= 5;
		System.out.println("BB : "+a+" ");
	}
}
public class OverrideMain {

	public static void main(String[] args) {
		BB aa = new BB();
		aa.disp(); //BB : 13
		System.out.println("aa : "+aa.a);//13
		System.out.println();
		AA bb = new BB();
		bb.disp();// 13
		System.out.println("aa : "+bb.a);//3
		BB cc = (BB)bb;
		cc.disp(); //18
		System.out.println();
		AA dd = new AA();
		dd.disp(); //8
	}

}
