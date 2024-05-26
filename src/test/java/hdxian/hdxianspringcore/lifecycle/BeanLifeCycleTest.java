package hdxian.hdxianspringcore.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient bean = ac.getBean(NetworkClient.class);

        // close(): 스프링 컨테이너 내리는 메서드
        // close() 메서드는 ApplicationContext 타입에는 정의 안돼있음. 하위 인터페이스나 클래스 타입 참조변수로 접근해야함.
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {

        @Bean // (initMethod = "init", destroyMethod = "close") 방법 2
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("https://testUrl.url");
            return networkClient;
        }

    }

}
