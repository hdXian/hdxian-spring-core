package hdxian.hdxianspringcore.beandefinition;

import hdxian.hdxianspringcore.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BeanDefinitionTest {

    AnnotationConfigApplicationContext aac = new AnnotationConfigApplicationContext(AppConfig.class);
    GenericXmlApplicationContext gac = new GenericXmlApplicationContext("appConfig.xml");

    @Test
    @DisplayName("빈 설정 메타정보 확인(annotation)")
    void findApplicationBean() {
        String[] beanDefinitionNames = aac.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = aac.getBeanDefinition(beanDefinitionName);

            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("beanDefinitionName = " + beanDefinitionName
                        + ", beanDefinition = " + beanDefinition);
            }

        }

    }

    @Test
    @DisplayName("빈 설정 메타정보 확인(xml)")
    void findApplicationBeanXml() {
        String[] beanDefinitionNames = gac.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = gac.getBeanDefinition(beanDefinitionName);

            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("beanDefinitionName = " + beanDefinitionName
                        + ", beanDefinition = " + beanDefinition);
            }

        }

    }

}
