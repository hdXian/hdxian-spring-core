package hdxian.hdxianspringcore.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StateFulServiceTest {

    @Test
    void stateFulSingletonTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StateFulService stateFulService1 = ac.getBean("stateFulService", StateFulService.class);
        StateFulService stateFulService2 = ac.getBean("stateFulService", StateFulService.class);

        // ThreadA가 10000원짜리 주문을 진행
        stateFulService1.order("nameA", 10000);

        // ThreadB가 20000원짜리 주문을 진행
        stateFulService2.order("nameB", 20000);

        // 이 때 ThreadA에서 사용자의 주문 금액을 읽으려고 하면?
        int price = stateFulService1.getPrice();
        System.out.println("price = " + price);

        Assertions.assertThat(price).isEqualTo(20000);
    }

    @Test
    void StatelessSingletonTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatelessService service1 = ac.getBean("statelessService", StatelessService.class);
        StatelessService service2 = ac.getBean("statelessService", StatelessService.class);

        int priceA = service1.order("nameA", 10000);
        int priceB = service2.order("nameB", 20000);

        System.out.println("priceA = " + priceA);
        System.out.println("priceB = " + priceB);

        Assertions.assertThat(priceA).isEqualTo(10000);
        Assertions.assertThat(priceB).isEqualTo(20000);

    }


    static class TestConfig {
        @Bean
        public StateFulService stateFulService() {
            return new StateFulService();
        }

        @Bean
        public StatelessService statelessService() {
            return new StatelessService();
        }
    }

}