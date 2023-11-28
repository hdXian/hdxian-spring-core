package hdxian.hdxianspringcore.beanfind;

import hdxian.hdxianspringcore.AppConfig;
import hdxian.hdxianspringcore.member.MemberService;
import hdxian.hdxianspringcore.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType() {
        // 이름 없이 타입으로만 조회 가능하다. MemberService.class를 전달하면 MemberService 타입의 빈만 조회 가능.
        // 근데 MemberService 타입의 빈이 여러개면 어떡하지?
        MemberService memberService = ac.getBean(MemberService.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구현체 타입으로 조회")
    void findBeanByName2() {
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구현체 타입으로만 조회")
    void findBeanByName3() {
        MemberServiceImpl memberService = ac.getBean(MemberServiceImpl.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }


    @Test
    @DisplayName("빈 이름으로 조회 실패")
    void findBeanByNameX() {
        // 아래 코드가 실행될 때 예외가 제대로 터지는지도 테스트해야 한다.
        // ac.getBean("xxxxx", MemberService.class);

        // 이딴 빈은 없다. 그렇다면?
        // MemberService noneBean = ac.getBean("xxxxx", MemberService.class);

        // 해당 람다식을 실행하면 NoSuchBeanDefinitionException이 터지는지 테스트하는 코드.
        // 해당 예외가 발생하면 테스트 통과.
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxxx", MemberService.class));

    }

    @Test
    @DisplayName("빈 타입 없이 이름만으로 조회")
    void findBeanByName4() {
        Object bean = ac.getBean("memberService");

        System.out.println("bean = " + bean);
        System.out.println("bean.getClass() = " + bean.getClass());

        assertThat(bean).isInstanceOf(MemberService.class);

    }


}
