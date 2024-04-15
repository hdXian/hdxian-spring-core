package hdxian.hdxianspringcore.singleton;

import hdxian.hdxianspringcore.AppConfig;
import hdxian.hdxianspringcore.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        // 조회: 호출할 때마다 새로운 객체가 생성됨
        MemberService memberService1 = appConfig.memberService();

        // 조회: 호출할 때마다 새로운 객체가 생성됨
        MemberService memberService2 = appConfig.memberService();

        // 참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1과 memeberService2가 서로 다른 객체인지 테스트
        assertThat(memberService1).isNotSameAs(memberService2);

    }

    @Test
    @DisplayName("싱글톤 패턴 적용 사례 테스트")
    void singletonServiceTest() {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        // same: == (참조 비교)
        // equal: equals() (내용 비교)
        assertThat(singletonService1).isSameAs(singletonService2);
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 조회: 스프링 빈을 가져오면 싱글톤 객체가 리턴됨
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);

        // 조회: 스프링 빈을 가져오면 싱글톤 객체가 리턴됨
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        // 참조값이 같은 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1과 memeberService2가 서로 다른 객체인지 테스트
        assertThat(memberService1).isSameAs(memberService2);
    }


}
