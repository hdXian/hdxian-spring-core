package hdxian.hdxianspringcore.beanfind;

import hdxian.hdxianspringcore.AppConfig;
import hdxian.hdxianspringcore.discount.DiscountPolicy;
import hdxian.hdxianspringcore.member.MemberRepository;
import hdxian.hdxianspringcore.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ApplicationSameBeanFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회 시 같은 타입의 빈이 여러 개 있으면 예외가 발생한다.")
    void findBeanByTypeDuplicate() {
        // ac.getBean(MemberRepository.class);
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("같은 타입의 빈이 여러개 있을 경우, 빈 이름을 지정해서 조회한다.")
    void findBeanByName() {
        MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);
        assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findAllBeanByType() {
        // 같은 타입들을 모두 조회하면 Map 형태로 찾아서 리턴한다.
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + ", value = " + beansOfType.get(key));
        }

        System.out.println("beansOfType = " + beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2); // 찾아낸게 2개인지 확인

    }


    @Configuration
    static class SameBeanConfig {

        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        } // 빈 이름은 memberRepository1

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        } // 빈 이름은 memberRepository2

    }



}
