package hdxian.hdxianspringcore.scope;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PrototypeBeanTest {

    @Test
    void prototypeBeanFind() {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        System.out.println("call getBean()");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);

        System.out.println("call getBean()");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        System.out.println("bean1 = " + prototypeBean1);
        System.out.println("bean2 = " + prototypeBean2);

        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        // destroy() 메서드를 동작시키려면 직접 호출해야 함.
        prototypeBean1.destroy();
        prototypeBean2.destroy();

        ac.close(); // 프로토타입 빈은 스프링 컨테이너가 종료되어도 destroy()가 자동으로 호출되지 않음
    }

    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }

    }

}
