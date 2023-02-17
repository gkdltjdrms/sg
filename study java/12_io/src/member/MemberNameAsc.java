package member;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MemberNameAsc implements Member {
    private ArrayList<MemberDTO> members;
    
    public MemberNameAsc(ArrayList<MemberDTO> members) {
        this.members = members;
    }
    
    @Override
    public void execute(ArrayList<MemberDTO> arrayList) {
        // sort the members list by name in ascending order
        Collections.sort(arrayList, new Comparator<MemberDTO>() {
            public int compare(MemberDTO member1, MemberDTO member2) {
                return member1.getName().compareTo(member2.getName());
            }
        });
        
        // print the sorted list
        System.out.println("이름순으로 회원 목록을 출력합니다.");
        for (MemberDTO member : arrayList) {
            System.out.println("이름: " + member.getName() + ", 나이: " + member.getAge()
                    + ", 핸드폰: " + member.getPhone() + ", 주소: " + member.getAddress());
        }
    }
}
