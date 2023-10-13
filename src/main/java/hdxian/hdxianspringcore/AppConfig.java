package hdxian.hdxianspringcore;

import hdxian.hdxianspringcore.discount.DiscountPolicy;
import hdxian.hdxianspringcore.discount.FixDiscountPolicy;
import hdxian.hdxianspringcore.member.MemberRepository;
import hdxian.hdxianspringcore.member.MemberService;
import hdxian.hdxianspringcore.member.MemberServiceImpl;
import hdxian.hdxianspringcore.member.MemoryMemberRepository;
import hdxian.hdxianspringcore.order.OrderService;
import hdxian.hdxianspringcore.order.OrderServiceImpl;

// 애플리케이션 전체를 설정하고 구성함.
// 앞으로 애플리케이션에 대한 환경 설정은 모두 이 클래스에서 수행한다.
public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }


}
