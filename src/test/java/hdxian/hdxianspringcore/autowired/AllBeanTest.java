package hdxian.hdxianspringcore.autowired;

import hdxian.hdxianspringcore.AutoAppConfig;
import hdxian.hdxianspringcore.discount.DiscountPolicy;
import hdxian.hdxianspringcore.member.Grade;
import hdxian.hdxianspringcore.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

// 의도적으로 모든 빈을 조회해서 전략 패턴을 구현해보는 테스트 코드
public class AllBeanTest {

    @Test
    void allBeanTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        // AutoAppConfig에서 컴포넌트 스캔 방식으로 빈을 등록해놔서 빈 이름은 앞글자가 소문자인 클래스 이름으로 등록됨
        int discount = discountService.calcDiscount(member, 10000, "fixDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discount).isEqualTo(1000);

        int discount2 = discountService.calcDiscount(member, 20000, "rateDiscountPolicy");
        assertThat(discount2).isEqualTo(2000);
    }


    static class DiscountService {

        // 의존성을 Map이나 List로 받으면 해당하는 빈을 모두 전달받을 수 있음.
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;

            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int calcDiscount(Member member, int price, String policyCode) {
            DiscountPolicy discountPolicy = policyMap.get(policyCode);

            System.out.println("policyCode = " + policyCode);
            System.out.println("discountPolicy = " + discountPolicy);

            return discountPolicy.discount(member, price);
        }
    }
}
