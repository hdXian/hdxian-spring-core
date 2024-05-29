package hdxian.hdxianspringcore.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SingletonWithPrototypeTest {

    @Test
    public void prototypeFind() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        System.out.println("call getBean()");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        int count1 = prototypeBean1.getCount();

        assertThat(count1).isEqualTo(1);

        System.out.println("call getBean()");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        int count2 = prototypeBean1.getCount();

        assertThat(count2).isEqualTo(1);

        ac.close();

    }


    @Test
    public void singletonClientUsePrototype() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);


        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(2);

    }

    // 프로토타입 빈을 의도대로 사용하는 1차원적인 방법 -> ApplicationContext를 주입받아 필요할 때마다 getBean() 주입받기
    @Test
    public void singletonClientUsePrototype2() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean2.class, PrototypeBean.class);

        ClientBean2 clientBean1 = ac.getBean(ClientBean2.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);


        ClientBean2 clientBean2 = ac.getBean(ClientBean2.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }


    @Test
    public void singletonClientUsePrototype3() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean3.class, PrototypeBean.class);

        ClientBean3 clientBean1 = ac.getBean(ClientBean3.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);


        ClientBean3 clientBean2 = ac.getBean(ClientBean3.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);

    }

    @Test
    public void singletonClientUsePrototype4() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean4.class, PrototypeBean.class);

        ClientBean4 clientBean1 = ac.getBean(ClientBean4.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);


        ClientBean4 clientBean2 = ac.getBean(ClientBean4.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);

    }

    @Scope("singleton")
    static class ClientBean {
        private final PrototypeBean prototypeBean; // 생성 시점에 주입

        @Autowired
        public ClientBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }

        public int logic() {
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }

    }


    // 1차원적인 방법 -> ApplicationContext를 주입받아서 필요할 때마다 getBean()을 호출한다.
    @Scope("singleton")
    static class ClientBean2 {

        private final ApplicationContext applicationContext;

        @Autowired
        public ClientBean2(ApplicationContext applicationContext) {
            this.applicationContext = applicationContext;
        }

        public int logic() {
            PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class);
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }

    }


    // ObjectProvider를 이용한 프로토타입 빈 조회
    @Scope("singleton")
    static class ClientBean3 {
        private final ObjectProvider<PrototypeBean> prototypeBeans;

        @Autowired
        public ClientBean3(ObjectProvider<PrototypeBean> prototypeBeans) {
            this.prototypeBeans = prototypeBeans;
        }

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeans.getObject();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }

    }


    // 자바 표준 Provider를 이용한 프로토타입 빈 조회
    @Scope("singleton")
    static class ClientBean4 {
        private final Provider<PrototypeBean> prototypeBeans;

        @Autowired
        public ClientBean4(Provider<PrototypeBean> prototypeBeans) {
            this.prototypeBeans = prototypeBeans;
        }

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeans.get();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }

    }


    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            this.count++;
        }

        public int getCount() {
            return this.count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init, " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }

    }

}
