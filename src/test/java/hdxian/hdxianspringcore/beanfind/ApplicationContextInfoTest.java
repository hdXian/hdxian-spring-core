package hdxian.hdxianspringcore.beanfind;

import hdxian.hdxianspringcore.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        // 등록된 모든 빈의 이름을 배열로 리턴.
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            // 빈 이름이 key, 빈(객체)이 value
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + ", object = " + bean);
        }
    }

    @Test
    @DisplayName("어플리케이션 빈 출력하기")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            // 전달한 이름에 해당하는 빈의 Definition(메타데이터)를 가져옴.
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            // 각 빈의 Definition에는 enum 형태의 Role이 정의되어 있다.
            // Role ROLE_APPLICATION: 직접 등록한 어플리케이션 빈
            // Role ROLE_INFRASTRUCTURE: 스프링이 내부적으로 생성해 사용하는 빈

            // ROLE이 ROLE_APPLICATION인 빈들만 출력하면 내가 만든 빈들만 출력해서 확인할 수 있다.
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + ", object = " + bean);
            }


        } // end of for(beanDefinitionName:Names)
    }


}
