package hdxian.hdxianspringcore;

import hdxian.hdxianspringcore.member.Grade;
import hdxian.hdxianspringcore.member.Member;
import hdxian.hdxianspringcore.member.MemberService;
import hdxian.hdxianspringcore.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "memberA", Grade.VIP);

        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member: " + member.getName());
        System.out.println("findMember = " + findMember.getName());

    }


}
