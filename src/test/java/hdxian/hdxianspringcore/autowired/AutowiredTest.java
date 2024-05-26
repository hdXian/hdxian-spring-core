package hdxian.hdxianspringcore.autowired;

import hdxian.hdxianspringcore.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {

        // @Component를 안 붙여도 TestBean이 빈으로 등록된 컨테이너가 생성됨
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }

    static class TestBean {

        // Member는 빈이 아니기 때문에 주입 안되는 상황
        @Autowired(required = false) // 자동 주입할 대상이 없으면 아예 실행 안 됨
        public void setNoBean1(Member member) {
            System.out.println("setNoBean1 = " + member);
        }

        @Autowired // 자동 주입할 대상이 없으면 null이 주입됨
        public void setNoBean2(@Nullable Member member) {
            System.out.println("setNoBean2 = " + member);
        }

        @Autowired // 자동 주입할 대상이 없으면 Optional.empty가 주입됨
        public void setNoBean3(Optional<Member> member) {
            System.out.println("setNoBean3 = " + member);
        }


    }

}
