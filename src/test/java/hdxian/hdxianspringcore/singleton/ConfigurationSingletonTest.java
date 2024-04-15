package hdxian.hdxianspringcore.singleton;

import hdxian.hdxianspringcore.AppConfig;
import hdxian.hdxianspringcore.member.MemberRepository;
import hdxian.hdxianspringcore.member.MemberServiceImpl;
import hdxian.hdxianspringcore.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 편의상 구현 클래스에만 따로 getMemberRepository()를 정의했기 때문에 Impl 형태로 가져옴.
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository repo = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository repo1 = memberService.getMemberRepository();
        MemberRepository repo2 = orderService.getMemberRepository();

        // 둘이 같은 객체를 참조하고 있음. 즉 MemberRepository 빈은 싱글톤으로 관리되는 중.
        System.out.println("repo1 = " + repo1);
        System.out.println("repo2 = " + repo2);

        // 얘도 위 2개 객체랑 같은 인스턴스임. 싱글톤임.
        System.out.println("repo = " + repo);

        Assertions.assertThat(memberService.getMemberRepository()).isEqualTo(repo);
        Assertions.assertThat(orderService.getMemberRepository()).isEqualTo(repo);
    }

    @Test
    void configurationAnnotationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean);
    }

}
