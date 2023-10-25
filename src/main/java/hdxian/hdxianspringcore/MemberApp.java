package hdxian.hdxianspringcore;

import hdxian.hdxianspringcore.member.Grade;
import hdxian.hdxianspringcore.member.Member;
import hdxian.hdxianspringcore.member.MemberService;
import hdxian.hdxianspringcore.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
//        // AppConfig 객체를 생성하고, AppConfig 객체로부터 구현체를 생성한다.
//        AppConfig appConfig = new AppConfig();
//
//        // memberService에는 memberServiceImpl 객체가 들어있음.
//        MemberService memberService = appConfig.memberService();
//        MemberService memberService = new MemberServiceImpl();
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);

        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member: " + member.getName());
        System.out.println("findMember = " + findMember.getName());

    }

}
