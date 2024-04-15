package hdxian.hdxianspringcore;

import hdxian.hdxianspringcore.discount.DiscountPolicy;
import hdxian.hdxianspringcore.discount.FixDiscountPolicy;
import hdxian.hdxianspringcore.discount.RateDiscountPolicy;
import hdxian.hdxianspringcore.member.MemberRepository;
import hdxian.hdxianspringcore.member.MemberService;
import hdxian.hdxianspringcore.member.MemberServiceImpl;
import hdxian.hdxianspringcore.member.MemoryMemberRepository;
import hdxian.hdxianspringcore.order.OrderService;
import hdxian.hdxianspringcore.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 애플리케이션 전체를 설정하고 구성함.
// 앞으로 애플리케이션에 대한 환경 설정은 모두 이 클래스에서 수행한다.
@Configuration
public class AppConfig {

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository()");
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        System.out.println("call AppConfig.discountPolicy()");
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService()");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService()");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }


}
