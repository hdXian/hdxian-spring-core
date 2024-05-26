package hdxian.hdxianspringcore.componentscan;

import hdxian.hdxianspringcore.AutoAppConfig;
import hdxian.hdxianspringcore.member.MemberRepository;
import hdxian.hdxianspringcore.member.MemberService;
import hdxian.hdxianspringcore.member.MemberServiceImpl;
import hdxian.hdxianspringcore.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class AutoAppConfigTest {

    @Test
    void basicScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

        OrderServiceImpl bean = ac.getBean("orderServiceImpl", OrderServiceImpl.class);
        MemberRepository repository = bean.getMemberRepository();
        System.out.println("repository = " + repository);
    }

}
